package org.grocery.facades.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commerceservices.model.process.AbandonedShoppingCartEmailProcessModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

/**
 * Custom {@link AbstractEmailContext} to map the {@link AbandonedShoppingCartEmailProcessModel}.
 */
public class AbandonedShoppingCartEmailContext extends AbstractEmailContext<AbandonedShoppingCartEmailProcessModel> {

    private Converter<CartModel, CartData> cartConverter;
    private CartData cartData;
    private String generatedCouponCode;
    private String promotionMessage;

    @Override
    public void init(AbandonedShoppingCartEmailProcessModel abandonedShoppingCartEmailProcess, EmailPageModel emailPageModel) {
        super.init(abandonedShoppingCartEmailProcess, emailPageModel);

        setCartData(getCartConverter().convert(abandonedShoppingCartEmailProcess.getAbandonedCart()));
        setGeneratedCouponCode(abandonedShoppingCartEmailProcess.getGeneratedCouponCode());
        setPromotionMessage(abandonedShoppingCartEmailProcess.getPromotionMessage());
    }

    @Override
    protected BaseSiteModel getSite(AbandonedShoppingCartEmailProcessModel businessProcessModel) {
        return businessProcessModel.getSite();
    }

    @Override
    protected CustomerModel getCustomer(AbandonedShoppingCartEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(AbandonedShoppingCartEmailProcessModel businessProcessModel) {
        return businessProcessModel.getLanguage();
    }

    public Converter<CartModel, CartData> getCartConverter() {
        return cartConverter;
    }

    public void setCartConverter(Converter<CartModel, CartData> cartConverter) {
        this.cartConverter = cartConverter;
    }

    public CartData getCartData() {
        return cartData;
    }

    public void setCartData(CartData cartData) {
        this.cartData = cartData;
    }

    public String getGeneratedCouponCode() {
        return generatedCouponCode;
    }

    public void setGeneratedCouponCode(String generatedCouponCode) {
        this.generatedCouponCode = generatedCouponCode;
    }

    public String getPromotionMessage() {
        return promotionMessage;
    }

    public void setPromotionMessage(String promotionMessage) {
        this.promotionMessage = promotionMessage;
    }
}
