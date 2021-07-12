package org.grocery.core.event;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.model.process.AbandonedShoppingCartEmailProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.grocery.service.promotions.ProcessesRelatedPromotionService;

/**
 * {@link AbstractAcceleratorSiteEventListener} for {@link AbandonedShoppingCartEvent}.
 */
public class AbandonedShoppingCartEventListener extends AbstractAcceleratorSiteEventListener<AbandonedShoppingCartEvent> {

    private BusinessProcessService businessProcessService;

    private ModelService modelService;

    private ProcessesRelatedPromotionService promotionService;

    @Override
    protected SiteChannel getSiteChannelForEvent(AbandonedShoppingCartEvent event) {
        BaseSiteModel site = event.getSite();
        return site.getChannel();
    }

    @Override
    protected void onSiteEvent(AbandonedShoppingCartEvent abandonedShoppingCartEvent) {
        final AbandonedShoppingCartEmailProcessModel storeFrontCustomerProcessModel = (AbandonedShoppingCartEmailProcessModel) getBusinessProcessService()
                .createProcess("abandonedShoppingCartEmailProcess-" + abandonedShoppingCartEvent.getCustomer().getUid() + "-" + System.currentTimeMillis(),
                        "abandonedShoppingCartEmailProcess");
        storeFrontCustomerProcessModel.setSite(abandonedShoppingCartEvent.getSite());
        storeFrontCustomerProcessModel.setCustomer(abandonedShoppingCartEvent.getCustomer());
        storeFrontCustomerProcessModel.setLanguage(abandonedShoppingCartEvent.getLanguage());
        storeFrontCustomerProcessModel.setCurrency(abandonedShoppingCartEvent.getCurrency());
        storeFrontCustomerProcessModel.setStore(abandonedShoppingCartEvent.getBaseStore());

        storeFrontCustomerProcessModel.setAbandonedCart(abandonedShoppingCartEvent.getAbandonedCart());

        storeFrontCustomerProcessModel.setGeneratedCouponCode(
                promotionService.getNewCouponCode(abandonedShoppingCartEvent.getBaseStore(),
                        ProcessesRelatedPromotionService.PromotionProcess.CART_ABANDONED));
        storeFrontCustomerProcessModel.setPromotionMessage(
                promotionService.getPromotionMessage(abandonedShoppingCartEvent.getBaseStore(),
                        ProcessesRelatedPromotionService.PromotionProcess.CART_ABANDONED));

        getModelService().save(storeFrontCustomerProcessModel);
        getBusinessProcessService().startProcess(storeFrontCustomerProcessModel);
    }

    public BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    public void setBusinessProcessService(BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public ProcessesRelatedPromotionService getPromotionService() {
        return promotionService;
    }

    public void setPromotionService(ProcessesRelatedPromotionService promotionService) {
        this.promotionService = promotionService;
    }
}
