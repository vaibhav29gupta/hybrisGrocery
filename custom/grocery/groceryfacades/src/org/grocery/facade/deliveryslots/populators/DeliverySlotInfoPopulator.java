package org.grocery.facade.deliveryslots.populators;

import de.hybris.platform.commercefacades.deliveryslot.info.data.DeliverySlotInfoData;
import de.hybris.platform.converters.Populator;

import org.grocery.core.model.DeliverySlotInfoModel;
import org.springframework.util.Assert;


/**
 * Populator corresponding to Delivery Slot info
 */
public class DeliverySlotInfoPopulator implements Populator<DeliverySlotInfoModel, DeliverySlotInfoData>
{


	@Override
	public void populate(final DeliverySlotInfoModel source, final DeliverySlotInfoData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setDeliveryDate(source.getDeliveryDate());
		target.setDeliveryStartTime(source.getDeliveryStartTime());
		target.setDeliveryEndTime(source.getDeliveryEndTime());

	}

}
