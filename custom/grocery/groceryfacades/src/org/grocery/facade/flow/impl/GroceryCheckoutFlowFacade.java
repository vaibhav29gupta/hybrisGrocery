/**
 * 
 */
package org.grocery.facade.flow.impl;

import de.hybris.platform.acceleratorfacades.flow.CheckoutFlowFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;

/**
 * @author arshdeepsingh
 *
 */
public interface GroceryCheckoutFlowFacade extends CheckoutFlowFacade
{

	public CCPaymentInfoData getPaymentDetails();
	public boolean hasShippingItems();
}
