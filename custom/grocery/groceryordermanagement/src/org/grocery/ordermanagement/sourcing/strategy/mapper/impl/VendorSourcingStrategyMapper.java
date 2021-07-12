package org.grocery.ordermanagement.sourcing.strategy.mapper.impl;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.grocery.ordermanagement.allocation.impl.GroceryAllocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hybris.platform.warehousing.data.sourcing.SourcingContext;
import de.hybris.platform.warehousing.sourcing.strategy.AbstractSourcingStrategyMapper;
import de.hybris.platform.warehousing.sourcing.strategy.mapper.impl.PickupSourcingStrategyMapper;


public class VendorSourcingStrategyMapper extends AbstractSourcingStrategyMapper
{
	private static final Logger LOGGER = LoggerFactory.getLogger(VendorSourcingStrategyMapper.class);

	@Override
	public Boolean isMatch(SourcingContext context)
	{
		if (CollectionUtils.isEmpty(context.getOrderEntries()))
		{
			return Boolean.FALSE;
		}

		final Boolean match = context.getOrderEntries().stream()
				.anyMatch(oe -> CollectionUtils.isNotEmpty(oe.getProduct().getVendors())
						&& CollectionUtils.isNotEmpty(oe.getDeliveryPointOfService().getWarehouses())
						&& oe.getProduct().getVendors().contains(oe.getDeliveryPointOfService().getWarehouses().get(0).getVendor()));


		LOGGER.debug("Match found for Vendor Sourcing");
		
		return match;
	}
}
