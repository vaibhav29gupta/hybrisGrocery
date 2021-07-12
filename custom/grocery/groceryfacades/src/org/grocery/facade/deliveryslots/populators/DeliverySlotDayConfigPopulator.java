package org.grocery.facade.deliveryslots.populators;

import de.hybris.platform.commercefacades.deliveryslot.data.DeliverySlotDayConfigData;
import de.hybris.platform.commercefacades.deliveryslot.data.ZoneData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.deliveryzone.model.ZoneModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Objects;

import org.grocery.core.model.DeliverySlotDayConfigModel;
import org.springframework.util.Assert;


public class DeliverySlotDayConfigPopulator implements Populator<DeliverySlotDayConfigModel, DeliverySlotDayConfigData>
{

	private Converter<ZoneModel, ZoneData> zoneConverter;

	public Converter<ZoneModel, ZoneData> getZoneConverter()
	{
		return zoneConverter;
	}

	public void setZoneConverter(final Converter<ZoneModel, ZoneData> zoneConverter)
	{
		this.zoneConverter = zoneConverter;
	}

	@Override
	public void populate(final DeliverySlotDayConfigModel source, final DeliverySlotDayConfigData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setDeliveryDate(source.getDeliveryDate());
		target.setOrderingDayStartTime(source.getOrderingDayStartTime());
		target.setOrderingDayEndTime(source.getOrderingDayEndTime());

		if (Objects.nonNull(source.getZone()))
		{
			target.setZone(zoneConverter.convert(source.getZone()));
		}

	}

}
