package org.grocery.service.promotions.impl;

import de.hybris.platform.commons.renderer.exceptions.RendererException;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.couponservices.model.MultiCodeCouponModel;
import de.hybris.platform.couponservices.services.impl.DefaultCouponCodeGenerationService;
import de.hybris.platform.promotionengineservices.model.PromotionSourceRuleModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.BaseStoreModel;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.grocery.service.promotions.ProcessesRelatedPromotionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class ProcessesRelatedPromotionServiceImpl implements ProcessesRelatedPromotionService {

    private static final Logger LOG = Logger.getLogger(ProcessesRelatedPromotionServiceImpl.class);
    private DefaultCouponCodeGenerationService couponCodeGenerationService;
    private MediaService mediaService;
    private PromotionSourceRuleModel promotion;
    private MultiCodeCouponModel coupon;

    /**
     * This method validates the promotion related flag on the store.
     * If the flag is enabled the private promotion/coupon attributes are set/taken from the store.
     * If flag is enabled, but still the promotion and/or coupon are not present on the store, then the method will return false. Logging is also performed for this case.
     */
    private boolean validateFlagsAndSetPromotionDetailsForProcess(BaseStoreModel store, PromotionProcess process) {
        switch (process) {
            case REGISTRATION:
                if (store.getRegistrationCouponEnabled()) {
                    if (store.getRegistrationCoupon() != null && store.getRegistrationPromotion() != null) {
                        promotion = store.getRegistrationPromotion();
                        coupon = store.getRegistrationCoupon();
                        return true;
                    } else {
                        LOG.error("The RegistrationCouponEnabled flag is set to TRUE for store " + store.getUid() + ", however, promotion and/or cart is missing from the store! ");
                    }
                }
            case CART_ABANDONED:
                if (store.getAbandonedShoppingCartCouponEnabled()) {
                    if (store.getAbandonedShoppingCartCoupon() != null && store.getAbandonedShoppingCartPromotion() != null) {
                        promotion = store.getAbandonedShoppingCartPromotion();
                        coupon = store.getAbandonedShoppingCartCoupon();
                        return true;
                    } else {
                        LOG.error("The AbandonedShoppingCartCouponEnabled flag is set to TRUE for store " + store.getUid() + ", however, promotion and/or cart is missing from the store! ");
                    }
                }
            default:
                return false;
        }
    }

    @Override
    public String getPromotionMessage(BaseStoreModel store, PromotionProcess process) {
        ServicesUtil.validateParameterNotNull(store, "BaseStore cannot be null!");

        if (validateFlagsAndSetPromotionDetailsForProcess(store, process)) {
            return promotion.getDescription();
        }
        return "";
    }

    @Override
    public String getNewCouponCode(BaseStoreModel store, PromotionProcess process) {
        ServicesUtil.validateParameterNotNull(store, "BaseStore cannot be null!");
        if (validateFlagsAndSetPromotionDetailsForProcess(store, process)) {
            return generateNewCodeForCoupon(coupon);
        }
        return "";
    }

    /**
     * Generates new code for the given coupon and returns the code value;
     */
    private String generateNewCodeForCoupon(MultiCodeCouponModel coupon) {
        Optional<MediaModel> generatedCodeMedia = couponCodeGenerationService.generateCouponCodes(coupon, 1);
        final AtomicReference<String> couponCode = new AtomicReference<>("");

        generatedCodeMedia.ifPresentOrElse(mediaModel -> couponCode.set(getGeneratedCodeForMedia(mediaModel)),
                () -> LOG.error(" Error while generating coupon Code !! "));
        return couponCode.get();
    }

    /**
     * Reads the generated coupon code from the InputStream file associated to given medialModel.
     *
     * @return - the content of inputStream associated to mediaModel - which represents the generated coupon code.
     */
    private String getGeneratedCodeForMedia(MediaModel mediaModel) {
        final StringBuilder messageSources = new StringBuilder();
        if (mediaModel != null) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(mediaService.getStreamFromMedia(mediaModel), StandardCharsets.UTF_8));
                final String line = reader.readLine();
                messageSources.append(line);
            } catch (final IOException e) {
                LOG.error("Problem during reading generated coupon code from coupon media file.", e);
                throw new RendererException("Problem during reading generated coupon code from coupon media file. ", e);
            } finally {
                IOUtils.closeQuietly(reader);
            }
        }
        return messageSources.toString();
    }

    public DefaultCouponCodeGenerationService getCouponCodeGenerationService() {
        return couponCodeGenerationService;
    }

    public void setCouponCodeGenerationService(DefaultCouponCodeGenerationService couponCodeGenerationService) {
        this.couponCodeGenerationService = couponCodeGenerationService;
    }

    public MediaService getMediaService() {
        return mediaService;
    }

    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }
}
