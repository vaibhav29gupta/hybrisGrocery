/**
 *
 */
package org.grocery.service.publish.event;

import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.dto.BillingInfo;

import org.grocery.core.model.CODPaymentInfoModel;


/**
 * @author ankituniyal
 *
 */
public interface GroceryPublishEventService
{

	/**
	 * @param token
	 */
	void publishUpdatePasswordEvent(String token);

	/*
	 * public CODPaymentInfoModel createPaymentSubscription(final CustomerModel customerModel, final BillingInfo
	 * billingInfo, final String titleCode);
	 */
	public CODPaymentInfoModel createPaymentSubscription(final CustomerModel customerModel);

}
