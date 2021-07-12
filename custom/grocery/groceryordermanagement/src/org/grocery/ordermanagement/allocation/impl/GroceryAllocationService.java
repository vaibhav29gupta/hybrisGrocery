package org.grocery.ordermanagement.allocation.impl;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.google.common.base.Strings;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.commerceservices.model.PickUpDeliveryModeModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.warehousing.allocation.impl.DefaultAllocationService;
import de.hybris.platform.warehousing.data.sourcing.SourcingResult;


public class GroceryAllocationService extends DefaultAllocationService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(GroceryAllocationService.class);

	public ConsignmentModel createConsignment(final AbstractOrderModel order, final String code, final SourcingResult result)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("result", (Object) result);
		ServicesUtil.validateParameterNotNullStandardMessage("order", (Object) order);
		Assert.isTrue(!Strings.isNullOrEmpty(code), "Parameter code cannot be null or empty");

		LOGGER.debug("Creating consignment for Location: '{}'", (Object) result.getWarehouse().getCode());

		final ConsignmentModel consignment = (ConsignmentModel) this.getModelService().create((Class) ConsignmentModel.class);
		consignment.setCode(code);
		consignment.setOrder(order);

		try
		{
			consignment
					.setFulfillmentSystemConfig(this.getWarehousingFulfillmentConfigDao().getConfiguration(result.getWarehouse()));

			final Set<Map.Entry<AbstractOrderEntryModel, Long>> resultEntries = result.getAllocation().entrySet();

			if (order.getDeliveryMode() instanceof PickUpDeliveryModeModel) {
				consignment.setStatus(ConsignmentStatus.READY);
				consignment.setDeliveryMode(order.getDeliveryMode());
				Predicate<PointOfServiceModel> defaultPosPredicate = p -> result.getWarehouse().getDefaultPOS().equals(p);
				final Optional<PointOfServiceModel> pickupPos = resultEntries.stream()
						.map(entry -> entry.getKey().getDeliveryPointOfService()).filter(Objects::nonNull).filter(defaultPosPredicate.negate()).findFirst();
				consignment.setShippingAddress(pickupPos.get().getAddress());
				consignment.setDeliveryPointOfService((PointOfServiceModel) pickupPos.get());
			} else {
				consignment.setStatus(ConsignmentStatus.READY);
				consignment.setDeliveryMode(order.getDeliveryMode());
				consignment.setShippingAddress(order.getDeliveryAddress());
				consignment.setShippingDate(this.getShippingDateStrategy().getExpectedShippingDate(consignment));
			}
			
			final Set<ConsignmentEntryModel> entries = resultEntries.stream()
					.map(mapEntry -> this.createConsignmentEntry(mapEntry.getKey(), (Long) mapEntry.getValue(), consignment))
					.collect(Collectors.toSet());
			consignment.setConsignmentEntries((Set) entries);
			consignment.setWarehouse(result.getWarehouse());

			if (consignment.getFulfillmentSystemConfig() == null)
			{
				this.getWarehousingConsignmentWorkflowService().startConsignmentWorkflow(consignment);
			}
			if (!consignment.getWarehouse().isExternal())
			{
				this.getInventoryEventService().createAllocationEvents(consignment);
			}
		}
		catch (AmbiguousIdentifierException ex)
		{
			consignment.setStatus(ConsignmentStatus.CANCELLED);
			LOGGER.warn("Cancelling consignment with code " + consignment.getCode()
					+ " since only one fulfillment system configuration is allowed per consignment.");
		}

		this.getModelService().save((Object) consignment);
		return consignment;
	}
}
