/**
 *
 */
package org.grocery.core.strategies.impl;

import de.hybris.platform.commerceservices.strategies.impl.DefaultDeliveryModeLookupStrategy;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;


public class GroceryDeliveryModeLookupStrategy extends DefaultDeliveryModeLookupStrategy
{
	@Override
	protected boolean isPickUpOnlyOrder(final AbstractOrderModel abstractOrderModel)
	{
		if (CollectionUtils.isNotEmpty(abstractOrderModel.getEntries()))
		{
			for (final AbstractOrderEntryModel entry : abstractOrderModel.getEntries())
			{
				final PointOfServiceModel pointOfService = entry.getDeliveryPointOfService();
				final List<WarehouseModel> warehouses = pointOfService != null ? pointOfService.getWarehouses()
						: Collections.EMPTY_LIST;
				if (Objects.nonNull(pointOfService) && CollectionUtils.isNotEmpty(warehouses))
				{
					if (warehouses.stream().map(w -> w.getDefaultPOS()).collect(Collectors.toSet()).contains(pointOfService))
					{
						return false;
					}
				}
			}
		}

		return true;
	}
}
