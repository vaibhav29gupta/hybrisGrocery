/**
 *
 */
package org.grocery.core.strategies.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartModificationStatus;
import de.hybris.platform.commerceservices.order.impl.DefaultCommerceUpdateCartEntryStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import org.apache.log4j.Logger;


/**
 * @author ankituniyal
 *
 */
public class GroceryCommerceUpdateCartEntryStrategy extends DefaultCommerceUpdateCartEntryStrategy
{

	private static final Logger LOG = Logger.getLogger(GroceryCommerceUpdateCartEntryStrategy.class);

	@Override
	public CommerceCartModification updatePointOfServiceForCartEntry(final CommerceCartParameter parameters)
			throws CommerceCartModificationException
	{
		final CartModel cartModel = parameters.getCart();
		final PointOfServiceModel pointOfServiceModel = parameters.getPointOfService();
		validateParameterNotNull(cartModel, "Cart model cannot be null");
		validateParameterNotNull(pointOfServiceModel, "PointOfService Model cannot be null");

		final AbstractOrderEntryModel entryToUpdate = getEntryForNumber(cartModel, (int) parameters.getEntryNumber());

		if (entryToUpdate == null)
		{
			throw new CommerceCartModificationException("Unknown entry number");
		}

		if (!isOrderEntryUpdatable(entryToUpdate))
		{
			throw new CommerceCartModificationException("Entry is not updatable");
		}

		final AbstractOrderEntryModel mergeTarget = getEntryMergeStrategy().getEntryToMerge(cartModel.getEntries(), entryToUpdate);
		if (mergeTarget != null)
		{
			return mergeEntries(entryToUpdate, mergeTarget, cartModel);
		}
		else
		{
			final CommerceCartModification modification = new CommerceCartModification();
			final long stockLevel = getAvailableStockLevel(entryToUpdate.getProduct(), parameters.getPointOfService());
			if (stockLevel == 0)
			{
				LOG.debug("Removing the cart entry as the stock level is 0 ");
				final AbstractOrderEntryModel cloneEntry = getModelService().clone(entryToUpdate);
				getModelService().detach(cloneEntry);
				getModelService().remove(entryToUpdate);
				modification.setEntry(cloneEntry);
				modification.setQuantityAdded(-cloneEntry.getQuantity().longValue());
				modification.setQuantity(0);
				modification.setStatusCode(CommerceCartModificationStatus.NO_STOCK);

				getModelService().refresh(cartModel);
				final CommerceCartParameter parameter = new CommerceCartParameter();
				parameter.setEnableHooks(true);
				parameter.setCart(cartModel);
				getCommerceCartCalculationStrategy().calculateCart(parameter);
				modification.setEntry(entryToUpdate);
				return modification;
			}
			else if (stockLevel < entryToUpdate.getQuantity().longValue())
			{
				LOG.debug("Stock Level is less than the quantities in entry");
				entryToUpdate.setQuantity(Long.valueOf(stockLevel));
				entryToUpdate.setDeliveryPointOfService(pointOfServiceModel);
				getModelService().save(entryToUpdate);
				modification.setEntry(entryToUpdate);
				modification.setQuantity(stockLevel);
				modification.setStatusCode(CommerceCartModificationStatus.LOW_STOCK);
			}
			else
			{
				entryToUpdate.setDeliveryPointOfService(pointOfServiceModel);
				getModelService().save(entryToUpdate);
				modification.setEntry(entryToUpdate);
				modification.setStatusCode(CommerceCartModificationStatus.SUCCESS);
			}
			getModelService().refresh(cartModel);
			final CommerceCartParameter parameter = new CommerceCartParameter();
			parameter.setEnableHooks(true);
			parameter.setCart(cartModel);
			getCommerceCartCalculationStrategy().calculateCart(parameter);
			getModelService().refresh(entryToUpdate);
			modification.setEntry(entryToUpdate);
			return modification;
		}
	}
}
