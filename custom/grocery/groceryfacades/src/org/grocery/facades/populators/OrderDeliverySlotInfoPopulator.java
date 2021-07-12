/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.deliveryslot.info.data.DeliverySlotInfoData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.grocery.core.model.DeliverySlotInfoModel;

/**
 * @author ojasmodi
 *
 */
public class OrderDeliverySlotInfoPopulator implements Populator<OrderModel, OrderData>
{

	private Converter<DeliverySlotInfoModel, DeliverySlotInfoData> deliverySlotInfoConverter;

	@Override
	public void populate(final OrderModel source, final OrderData target) throws ConversionException
	{
		if(source.getDeliverySlotInfo()!=null) {
			target.setDeliverySlotInfo(getDeliverySlotInfoConverter().convert(source.getDeliverySlotInfo()));
		}
	}

	/**
	 * @return the deliverySlotInfoConverter
	 */
	public Converter<DeliverySlotInfoModel, DeliverySlotInfoData> getDeliverySlotInfoConverter()
	{
		return deliverySlotInfoConverter;
	}

	/**
	 * @param deliverySlotInfoConverter
	 *           the deliverySlotInfoConverter to set
	 */
	public void setDeliverySlotInfoConverter(
			final Converter<DeliverySlotInfoModel, DeliverySlotInfoData> deliverySlotInfoConverter)
	{
		this.deliverySlotInfoConverter = deliverySlotInfoConverter;
	}



}
