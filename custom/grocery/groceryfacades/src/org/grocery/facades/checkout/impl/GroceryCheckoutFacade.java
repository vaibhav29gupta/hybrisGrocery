/**
 *
 */
package org.grocery.facades.checkout.impl;

import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.payment.info.data.CODPaymentInfoData;


/**
 * @author arshdeepsingh
 *
 */
public interface GroceryCheckoutFacade extends CheckoutFacade
{

	public CODPaymentInfoData createPaymentSubscription(final CODPaymentInfoData codPaymentInfoData);
}
