package org.grocery.ordermanagement.sourcing.strategy.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hybris.platform.commerceservices.model.PickUpDeliveryModeModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.warehousing.data.sourcing.SourcingContext;
import de.hybris.platform.warehousing.data.sourcing.SourcingLocation;
import de.hybris.platform.warehousing.data.sourcing.SourcingResult;
import de.hybris.platform.warehousing.sourcing.strategy.AbstractSourcingStrategy;


public class VendorSourcingStrategy extends AbstractSourcingStrategy
{
	private static final Logger LOGGER = LoggerFactory.getLogger(VendorSourcingStrategy.class);

	@Override
	public void source(SourcingContext context)
	{
		final Optional<AbstractOrderEntryModel> entryOptional = context.getOrderEntries().stream()
				.filter(orderEntry -> CollectionUtils.isNotEmpty(orderEntry.getProduct().getVendors())
						&& ((OrderEntryModel) orderEntry).getQuantityUnallocated() > 0L)
				.findFirst();
		if (entryOptional.isPresent())
		{
			final AbstractOrderEntryModel entry = entryOptional.get();
			final Optional<SourcingLocation> dropshipLocation = context.getSourcingLocations().stream()
					.filter(location -> entry.getProduct().getVendors().contains(location.getWarehouse().getVendor())).findFirst();

			if (dropshipLocation.isPresent() && !(entry.getOrder().getDeliveryMode() instanceof PickUpDeliveryModeModel))
			{
				SourcingLocation location = dropshipLocation.get();
				boolean isComplete = true;
				final Map<AbstractOrderEntryModel, Long> allocations = new HashMap<AbstractOrderEntryModel, Long>();
				for (final AbstractOrderEntryModel orderEntry : context.getOrderEntries())
				{
					if (CollectionUtils.isNotEmpty(orderEntry.getProduct().getVendors())
							&& ((OrderEntryModel) orderEntry).getQuantityUnallocated() > 0L && context.getSourcingLocations().stream()
									.anyMatch(loc -> orderEntry.getProduct().getVendors().contains(loc.getWarehouse().getVendor())))
					{
						final OrderEntryModel orderEntryModel = (OrderEntryModel) orderEntry;
						final Long stock = location.getAvailability().get(orderEntry.getProduct());
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
									{ orderEntry.getProduct().getCode(), orderEntryModel.getQuantityUnallocated(), stock });
						}
						if (stock > 0L)
						{
							allocations.put(orderEntry, orderQty);
							LOGGER.debug("Created sourcing result allocation for product [{}]: requested qty [{}] at location [{}] ",
									new Object[]
									{ orderEntry.getProduct().getCode(), orderQty, location.getWarehouse().getCode() });
						}
					}
					else
					{
						isComplete = false;
						LOGGER.debug("Incomplete sourcing - Insufficient stock for product [{}]", new Object[]
						{ orderEntry.getProduct().getCode() });
					}
				}
				if (!allocations.isEmpty())
				{
					final SourcingResult result = this.getSourcingResultFactory().create((Map) allocations, location);
					context.getResult().getResults().add(result);
				}
				context.getResult().setComplete(isComplete);
			}
		}
		if (Objects.nonNull(context.getResult()) && !context.getResult().getResults().isEmpty())
		{
			LOGGER.debug("Total order entries sourceable using Vendor Strategy: {}",
					(Object) context.getResult().getResults().iterator().next().getAllocation().size());
		}
	}
}
