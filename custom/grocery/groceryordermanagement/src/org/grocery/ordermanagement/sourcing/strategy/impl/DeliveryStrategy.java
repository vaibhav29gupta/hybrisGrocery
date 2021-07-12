package org.grocery.ordermanagement.sourcing.strategy.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.deliveryzone.model.ZoneDeliveryModeModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.warehousing.data.sourcing.SourcingContext;
import de.hybris.platform.warehousing.data.sourcing.SourcingLocation;
import de.hybris.platform.warehousing.data.sourcing.SourcingResult;
import de.hybris.platform.warehousing.sourcing.strategy.impl.PickupStrategy;


public class DeliveryStrategy extends PickupStrategy
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryStrategy.class);

	@Override
	protected boolean canSourceOrderEntry(AbstractOrderEntryModel entry, SourcingLocation location)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("entry", (Object) entry);
		ServicesUtil.validateParameterNotNullStandardMessage("location", (Object) location);
		ServicesUtil.validateParameterNotNullStandardMessage("warehouse", (Object) location.getWarehouse());

		final Collection<DeliveryModeModel> warehouseSupportedDeliveryModes = (Collection<DeliveryModeModel>) location
				.getWarehouse().getDeliveryModes();
		final boolean isSupportedDeliveryMode = warehouseSupportedDeliveryModes.stream()
				.anyMatch(deliveryMode -> deliveryMode instanceof ZoneDeliveryModeModel);

		boolean isPosMatch = false;
		if (CollectionUtils.isNotEmpty(location.getWarehouse().getPointsOfService())
				&& Objects.nonNull(entry.getDeliveryPointOfService()))
		{
			isPosMatch = location.getWarehouse().getPointsOfService().contains(entry.getDeliveryPointOfService());
		}

		return isSupportedDeliveryMode && isPosMatch;
	}

	@Override
	protected void createPickupSourcingResult(final SourcingContext sourcingContext, final SourcingLocation location)
	{
		boolean isComplete = true;
		final Map<AbstractOrderEntryModel, Long> allocations = new HashMap<AbstractOrderEntryModel, Long>();
		for (final AbstractOrderEntryModel entry : sourcingContext.getOrderEntries())
		{
			final OrderEntryModel orderEntryModel = (OrderEntryModel) entry;
			if (CollectionUtils.isNotEmpty(orderEntryModel.getProduct().getVendors()))
			{
				continue;
			}

			final Long stock = location.getAvailability().get(entry.getProduct());
			Long orderQty;
			if (stock >= orderEntryModel.getQuantityUnallocated())
			{
				orderQty = orderEntryModel.getQuantityUnallocated();
			}
			else
			{
				orderQty = stock;
				isComplete = false;

				LOGGER.debug("Incomplete sourcing - Insufficient stock for product [{}]: requested qty [{}], stock qty [{}]",
						new Object[]
						{ entry.getProduct().getCode(), orderEntryModel.getQuantityUnallocated(), stock });
			}
			if (stock > 0L)
			{
				allocations.put(entry, orderQty);
				LOGGER.debug("Created sourcing result allocation for product [{}]: requested qty [{}] at location [{}] ", new Object[]
				{ entry.getProduct().getCode(), orderQty, location.getWarehouse().getCode() });
			}
		}
		if (!allocations.isEmpty())
		{
			final SourcingResult result = this.getSourcingResultFactory().create((Map) allocations, location);
			sourcingContext.getResult().getResults().add(result);
		}
		sourcingContext.getResult().setComplete(isComplete);
	}
}
