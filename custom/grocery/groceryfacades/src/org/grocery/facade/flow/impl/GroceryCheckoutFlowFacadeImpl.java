/**
 *
 */
package org.grocery.facade.flow.impl;

import de.hybris.platform.acceleratorfacades.flow.impl.DefaultCheckoutFlowFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;

import org.grocery.core.enums.ShipmentTypeEnum;
import org.grocery.core.model.CODPaymentInfoModel;
import org.grocery.facades.shipment.ShipmentFacade;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author arshdeepsingh
 *
 */
public class GroceryCheckoutFlowFacadeImpl extends DefaultCheckoutFlowFacade implements GroceryCheckoutFlowFacade
{

	@Autowired
	private Converter<PaymentInfoModel, CCPaymentInfoData> creditCardPaymentInfoConverter;

	@Resource(name = "shipmentFacade")
	private ShipmentFacade shipmentFacade;

	@Override
	public CCPaymentInfoData getPaymentDetails()
	{
		final CartModel cart = getCart();
		if (cart != null)
		{
			final PaymentInfoModel paymentInfo = cart.getPaymentInfo();
			if (paymentInfo instanceof CreditCardPaymentInfoModel || paymentInfo instanceof CODPaymentInfoModel)
			{
				return creditCardPaymentInfoConverter.convert(paymentInfo);
			}
		}
		return null;
	}

	@Override
	public boolean hasShippingItems()
	{
		final String shipmentType = shipmentFacade.getCurrentShipmentMethod();

		if (!ShipmentTypeEnum.DELIVERY.equals(ShipmentTypeEnum.valueOf(shipmentType)) && shipmentType != null)
		{

			return hasItemsMatchingPredicate(e -> e.getDeliveryPointOfService() == null);

		}
		else
		{
			return true;
		}

	}

}
