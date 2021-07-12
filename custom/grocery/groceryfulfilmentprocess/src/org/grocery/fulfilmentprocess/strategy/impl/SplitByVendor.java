/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.fulfilmentprocess.strategy.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.VendorModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.ordersplitting.strategy.AbstractSplittingStrategy;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;


public class SplitByVendor extends AbstractSplittingStrategy
{
	@Override
	public Object getGroupingObject(final AbstractOrderEntryModel orderEntry)
	{
		if (orderEntry.getDeliveryPointOfService() != null)
		{
			final List<WarehouseModel> warehouses = orderEntry.getDeliveryPointOfService().getWarehouses();
			if (CollectionUtils.isNotEmpty(warehouses))
			{
				final VendorModel vendor = warehouses.iterator().next().getVendor();
				final Set<VendorModel> productVendors = orderEntry.getProduct().getVendors();
				if (CollectionUtils.isNotEmpty(productVendors) && productVendors.contains(vendor))
				{
					return vendor;
				}
			}
		}
		else if (orderEntry.getChosenVendor() != null)
		{
			return orderEntry.getChosenVendor();
		}

		return null;
	}

	@Override
	public void afterSplitting(final Object groupingObject, final ConsignmentModel createdOne)
	{
		//nothing to do
	}
}
