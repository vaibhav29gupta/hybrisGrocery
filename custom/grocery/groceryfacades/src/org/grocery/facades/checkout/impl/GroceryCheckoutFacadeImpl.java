/**
 *
 */
package org.grocery.facades.checkout.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.acceleratorfacades.flow.impl.DefaultCheckoutFlowFacade;
import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.impl.DefaultCheckoutFacade;
import de.hybris.platform.commercefacades.payment.info.data.CODPaymentInfoData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.CardInfo;
import de.hybris.platform.payment.dto.CardType;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.grocery.core.enums.ShipmentTypeEnum;
import org.grocery.core.model.CODPaymentInfoModel;
import org.grocery.facades.publish.event.GroceryPublishEventFacade;
import org.grocery.facades.shipment.ShipmentFacade;
import org.grocery.service.publish.event.GroceryPublishEventService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author arshdeepsingh
 *
 */
public class GroceryCheckoutFacadeImpl extends DefaultCheckoutFlowFacade implements GroceryCheckoutFacade
{

	@Autowired
	GroceryPublishEventService groceryPublishEventService;
	
	@Resource(name = "shipmentFacade")
	private ShipmentFacade shipmentFacade;
	
	private Converter<CODPaymentInfoModel, CODPaymentInfoData> codPaymentInfoConverter;
	
	
	public CODPaymentInfoData createPaymentSubscription(final CODPaymentInfoData codPaymentInfoData)
	{
		validateParameterNotNullStandardMessage("paymentInfoData", codPaymentInfoData);
		/*
		 * final AddressData billingAddressData = codPaymentInfoData.getBillingAddress();
		 * validateParameterNotNullStandardMessage("billingAddress", billingAddressData);
		 */

		if (checkIfCurrentUserIsTheCartUser())
		{

			//TO BY PASS BILLING ADDRESS IN COD CASE
			/*
			 * final BillingInfo billingInfo = new BillingInfo(); // DELivery final AddressData billingAddressData =
			 * getCheckoutCart().getDeliveryAddress();
			 * 
			 * final String shipmentType = shipmentFacade.getCurrentShipmentMethod();
			 * 
			 * if (ShipmentTypeEnum.DELIVERY.equals(ShipmentTypeEnum.valueOf(shipmentType))) {
			 * 
			 * billingInfo.setCity(billingAddressData.getTown()); billingInfo.setCountry(billingAddressData.getCountry() ==
			 * null ? null : billingAddressData.getCountry().getIsocode());
			 * billingInfo.setRegion(billingAddressData.getRegion() == null ? null :
			 * billingAddressData.getRegion().getIsocode()); billingInfo.setFirstName(billingAddressData.getFirstName());
			 * billingInfo.setLastName(billingAddressData.getLastName());
			 * billingInfo.setEmail(billingAddressData.getEmail());
			 * billingInfo.setPhoneNumber(billingAddressData.getPhone());
			 * billingInfo.setPostalCode(billingAddressData.getPostalCode());
			 * billingInfo.setStreet1(billingAddressData.getLine1());
			 * billingInfo.setStreet2(billingAddressData.getLine2());
			 * 
			 * }
			 */

			//final CODPaymentInfoModel codPaymentInfoModel = groceryPublishEventService.createPaymentSubscription(getCurrentUserForCheckout(), billingInfo, billingAddressData.getTitleCode());
			final CODPaymentInfoModel codPaymentInfoModel = groceryPublishEventService
					.createPaymentSubscription(getCurrentUserForCheckout());
			setPaymentDetails(codPaymentInfoModel);
			return codPaymentInfoModel == null ? null : getCodPaymentInfoConverter().convert(codPaymentInfoModel);
		}
		return null;
	}
	
	protected boolean setPaymentDetails(CODPaymentInfoModel codPaymentInfo) 
	{

		final CartModel cartModel = getCart();
		if (codPaymentInfo != null)
		{
			final CommerceCheckoutParameter parameter = createCommerceCheckoutParameter(cartModel, true);
			parameter.setPaymentInfo(codPaymentInfo);
			return getCommerceCheckoutService().setPaymentInfo(parameter);
		}
		return false;
	}
	
	@Override
	public boolean authorizePayment(final String securityCode)
	{
		final CartModel cartModel = getCart();
		final PaymentInfoModel paymentInfoModel = cartModel == null ? null : cartModel.getPaymentInfo();

		if (checkIfCurrentUserIsTheCartUser() && paymentInfoModel != null)
		{
			final CommerceCheckoutParameter parameter = createCommerceCheckoutParameter(cartModel, true);
			parameter.setSecurityCode(securityCode);
			parameter.setPaymentProvider(getPaymentProvider());
			final PaymentTransactionEntryModel paymentTransactionEntryModel = getCommerceCheckoutService()
					.authorizePayment(parameter);

			return paymentTransactionEntryModel != null
					&& (TransactionStatus.ACCEPTED.name().equals(paymentTransactionEntryModel.getTransactionStatus())
							|| TransactionStatus.REVIEW.name().equals(paymentTransactionEntryModel.getTransactionStatus()));
		}
		return false;
	}
	
	/**
	 * @return the groceryPublishEventService
	 */
	public GroceryPublishEventService getGroceryPublishEventService()
	{
		return groceryPublishEventService;
	}


	/**
	 * @param groceryPublishEventService the groceryPublishEventService to set
	 */
	public void setGroceryPublishEventService(GroceryPublishEventService groceryPublishEventService)
	{
		this.groceryPublishEventService = groceryPublishEventService;
	}


	/**
	 * @return the codPaymentInfoConverter
	 */
	public Converter<CODPaymentInfoModel, CODPaymentInfoData> getCodPaymentInfoConverter()
	{
		return codPaymentInfoConverter;
	}


	/**
	 * @param codPaymentInfoConverter the codPaymentInfoConverter to set
	 */
	public void setCodPaymentInfoConverter(Converter<CODPaymentInfoModel, CODPaymentInfoData> codPaymentInfoConverter)
	{
		this.codPaymentInfoConverter = codPaymentInfoConverter;
	}


	

}
