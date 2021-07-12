package org.grocery.facade.deliveryslots.populators;

import de.hybris.platform.commercefacades.deliveryslot.data.DeliverySlotTimeConfigData;
import de.hybris.platform.commercefacades.deliveryslot.data.ZoneData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.deliveryzone.model.ZoneModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Objects;

import org.grocery.core.model.DeliverySlotTimeConfigModel;
import org.springframework.util.Assert;


public class DeliverySlotTimeConfigPopulator implements Populator<DeliverySlotTimeConfigModel, DeliverySlotTimeConfigData>
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
	public void populate(final DeliverySlotTimeConfigModel source, final DeliverySlotTimeConfigData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setCode(source.getCode());
		target.setOpeningTime(source.getOpeningTime());
		target.setClosingTime(source.getClosingTime());

		if (Objects.nonNull(source.getZone()))
		{
			target.setZone(zoneConverter.convert(source.getZone()));
		}

	}

}
