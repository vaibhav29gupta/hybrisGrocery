/**
 * 
 */
package org.grocery.facade.payment.populators;

import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CardTypeData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.grocery.core.model.CODPaymentInfoModel;

/**
 * @author arshdeepsingh
 *
 */
public class GroceryPaymentInfoDataPopulator implements Populator<PaymentInfoModel, CCPaymentInfoData>
{
	
	private Converter<AddressModel, AddressData> addressConverter;
	private Converter<CreditCardType, CardTypeData> cardTypeConverter;
	/**
	 * @return the addressConverter
	 */
	public Converter<AddressModel, AddressData> getAddressConverter()
	{
		return addressConverter;
	}
	/**
	 * @param addressConverter the addressConverter to set
	 */
	public void setAddressConverter(Converter<AddressModel, AddressData> addressConverter)
	{
		this.addressConverter = addressConverter;
	}
	/**
	 * @return the cardTypeConverter
	 */
	public Converter<CreditCardType, CardTypeData> getCardTypeConverter()
	{
		return cardTypeConverter;
	}
	/**
	 * @param cardTypeConverter the cardTypeConverter to set
	 */
	public void setCardTypeConverter(Converter<CreditCardType, CardTypeData> cardTypeConverter)
	{
		this.cardTypeConverter = cardTypeConverter;
	}
	
	
	public void populate(final PaymentInfoModel source, final CCPaymentInfoData target)
	{
		if (source instanceof CreditCardPaymentInfoModel)
		{
			CreditCardPaymentInfoModel ccPaymentInfo = (CreditCardPaymentInfoModel) source;
			target.setId(ccPaymentInfo.getPk().toString());
			target.setCardNumber(ccPaymentInfo.getNumber());

			if (ccPaymentInfo.getType() != null)
			{
				final CardTypeData cardTypeData = getCardTypeConverter().convert(ccPaymentInfo.getType());
				target.setCardType(cardTypeData.getCode());
				target.setCardTypeData(cardTypeData);
			}

			target.setAccountHolderName(ccPaymentInfo.getCcOwner());
			target.setExpiryMonth(ccPaymentInfo.getValidToMonth());
			target.setExpiryYear(ccPaymentInfo.getValidToYear());
			target.setStartMonth(ccPaymentInfo.getValidFromMonth());
			target.setStartYear(ccPaymentInfo.getValidFromYear());

			target.setSubscriptionId(ccPaymentInfo.getSubscriptionId());
			target.setSaved(ccPaymentInfo.isSaved());

			if (ccPaymentInfo.getBillingAddress() != null)
			{
				target.setBillingAddress(getAddressConverter().convert(ccPaymentInfo.getBillingAddress()));
			}
			if (ccPaymentInfo.getIssueNumber() != null)
			{
				target.setIssueNumber(ccPaymentInfo.getIssueNumber().toString());
			}
		}
		else if (source instanceof CODPaymentInfoModel)
		{
			target.setId(source.getPk().toString());
			if (source.getBillingAddress() != null)
			{
				target.setBillingAddress(getAddressConverter().convert(source.getBillingAddress()));
			}
		}
	}	

}
