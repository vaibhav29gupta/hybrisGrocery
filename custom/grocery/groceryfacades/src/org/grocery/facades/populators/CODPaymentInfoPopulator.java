/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.payment.info.data.CODPaymentInfoData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.grocery.core.model.CODPaymentInfoModel;


/**
 * @author arshdeepsingh
 *
 */
public class CODPaymentInfoPopulator implements Populator<CODPaymentInfoModel, CODPaymentInfoData>
{

	private Converter<AddressModel, AddressData> addressConverter;
	
	
	
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



	@Override
	public void populate(final CODPaymentInfoModel source, final CODPaymentInfoData target)
	{
		/* super.populate(source, target); */
		
		target.setPaymentId(source.getCode());
		
		/*
		 * if (source.getBillingAddress() != null) {
		 * target.setBillingAddress(getAddressConverter().convert(source.getBillingAddress())); }
		 */




	}
}
