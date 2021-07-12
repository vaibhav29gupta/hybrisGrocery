package org.grocery.ordermanagement.sourcing.strategy.mapper.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hybris.platform.deliveryzone.model.ZoneDeliveryModeModel;
import de.hybris.platform.warehousing.data.sourcing.SourcingContext;
import de.hybris.platform.warehousing.sourcing.strategy.AbstractSourcingStrategyMapper;


public class DeliverySourcingStrategyMapper extends AbstractSourcingStrategyMapper
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DeliverySourcingStrategyMapper.class);

	@Override
	public Boolean isMatch(SourcingContext context)
	{
		if (CollectionUtils.isEmpty(context.getOrderEntries()))
		{
			return Boolean.FALSE;
		}

		final Boolean match = context.getOrderEntries().stream()
				.anyMatch(oe -> CollectionUtils.isNotEmpty(oe.getDeliveryPointOfService().getWarehouses()));

		LOGGER.debug("Match found for Delivery Sourcing");
		
		return match;
	}
}
