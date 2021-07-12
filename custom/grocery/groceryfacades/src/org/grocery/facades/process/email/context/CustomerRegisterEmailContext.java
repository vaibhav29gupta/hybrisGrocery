package org.grocery.facades.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commerceservices.model.process.CustomerRegistrationEmailProcessModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;

/**
 * Velocity context for Customer Registration email
 */
public class CustomerRegisterEmailContext extends CustomerEmailContext {
  private String generatedCouponCode;
  private String promotionMessage;

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

  @Override
  public void init(final StoreFrontCustomerProcessModel storeFrontCustomerProcessModel, final EmailPageModel emailPageModel)
  {
    super.init(storeFrontCustomerProcessModel, emailPageModel);
    if (storeFrontCustomerProcessModel instanceof CustomerRegistrationEmailProcessModel)
    {
      setGeneratedCouponCode(storeFrontCustomerProcessModel.getGeneratedCouponCode());
      setPromotionMessage(storeFrontCustomerProcessModel.getPromotionMessage());
    } else {
      setPromotionMessage("");
      setGeneratedCouponCode("");
    }
  }
}
