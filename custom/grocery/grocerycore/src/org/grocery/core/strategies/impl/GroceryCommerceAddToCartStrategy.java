/**
 *
 */
package org.grocery.core.strategies.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartModificationStatus;
import de.hybris.platform.commerceservices.order.impl.DefaultCommerceAddToCartStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.selectivecartservices.strategies.SelectiveCartAddToCartStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ojasmodi
 *
 */
public class GroceryCommerceAddToCartStrategy extends SelectiveCartAddToCartStrategy
{

	@Override
	protected CommerceCartModification doAddToCart(final CommerceCartParameter parameter) throws CommerceCartModificationException
	{
		CommerceCartModification modification;

		final CartModel cartModel = parameter.getCart();
		final ProductModel productModel = parameter.getProduct();
		final long quantityToAdd = parameter.getQuantity();
		final PointOfServiceModel deliveryPointOfService = parameter.getPointOfService();

		this.beforeAddToCart(parameter);
		validateAddToCart(parameter);

		if (isProductForCode(parameter).booleanValue())
		{
			// So now work out what the maximum allowed to be added is (note that this may be negative!)
			long actualAllowedQuantityChange = getAllowedCartAdjustmentForProduct(cartModel, productModel, quantityToAdd,
					deliveryPointOfService);
			final Integer maxOrderQuantity = productModel.getMaxOrderQuantity();
			final long cartLevel = checkCartLevel(productModel, cartModel, deliveryPointOfService);
			final long cartLevelAfterQuantityChange = actualAllowedQuantityChange;
			if (actualAllowedQuantityChange <= 0)
			{
				actualAllowedQuantityChange += cartLevel;
				final Map<Integer, Long> quantities = new HashMap<>();
				final List<AbstractOrderEntryModel> entries = cartModel.getEntries();
				for (final AbstractOrderEntryModel oem : entries)
				{
					if (parameter.getProduct().getCode().equals(oem.getProduct().getCode()))
					{
						quantities.put(oem.getEntryNumber(), actualAllowedQuantityChange);
						getCartService().updateQuantities(cartModel, quantities);
						parameter.setEntryNumber(oem.getEntryNumber());
					}
				}
				final String statusCode = getStatusCodeAllowedQuantityChange(actualAllowedQuantityChange, maxOrderQuantity,
						actualAllowedQuantityChange);
				modification = createAddToCartResp(parameter, statusCode, createEmptyCartEntry(parameter),
						actualAllowedQuantityChange);
				modification.setQuantity(actualAllowedQuantityChange);
			}
			else
			{
				final CartEntryModel entryModel = addCartEntry(parameter, actualAllowedQuantityChange);
				getModelService().save(entryModel);
				final String statusCode = getStatusCodeAllowedQuantityChange(actualAllowedQuantityChange, maxOrderQuantity,
						quantityToAdd);
				modification = createAddToCartResp(parameter, statusCode, entryModel, actualAllowedQuantityChange);
				if (maxOrderQuantity != null && modification.getQuantity() >= maxOrderQuantity)
				{
					modification.setQuantity(maxOrderQuantity);
				}
			}
		}
		else
		{
			modification = createAddToCartResp(parameter, CommerceCartModificationStatus.UNAVAILABLE,
					createEmptyCartEntry(parameter), 0);
		}
		return modification;
	}


	@Override
	protected void validateAddToCart(final CommerceCartParameter parameters) throws CommerceCartModificationException
	{
		final CartModel cartModel = parameters.getCart();
		final ProductModel productModel = parameters.getProduct();
		validateParameterNotNull(cartModel, "Cart model cannot be null");
		validateParameterNotNull(productModel, "Product model cannot be null");
		if (productModel.getVariantType() != null)
		{
			throw new CommerceCartModificationException("Choose a variant instead of the base product");
		}
	}


	protected String getStatusCodeAllowedQuantityChange(final long actualAllowedQuantityChange, final Integer maxOrderQuantity,
			final long quantityToAdd)
	{
		// Are we able to add the quantity we requested?
		if (isMaxOrderQuantitySet(maxOrderQuantity) && (actualAllowedQuantityChange == maxOrderQuantity.longValue()))
		{
			return CommerceCartModificationStatus.MAX_ORDER_QUANTITY_EXCEEDED;
		}
		else if (actualAllowedQuantityChange <= quantityToAdd)
		{
			return CommerceCartModificationStatus.SUCCESS;
		}
		else
		{
			return CommerceCartModificationStatus.LOW_STOCK;
		}
	}

	@Override
	protected long getAllowedCartAdjustmentForProduct(final CartModel cartModel, final ProductModel productModel,
			final long newTotalQuantity, final PointOfServiceModel pointOfServiceModel)
	{
		final long cartLevel = checkCartLevel(productModel, cartModel, pointOfServiceModel);
		final long stockLevel = getAvailableStockLevel(productModel, pointOfServiceModel);

		// Now limit that to the total available in stock
		final long newTotalQuantityAfterStockLimit = Math.min(newTotalQuantity, stockLevel);

		// So now work out what the maximum allowed to be added is (note that
		// this may be negative!)
		final Integer maxOrderQuantity = productModel.getMaxOrderQuantity();

		if (isMaxOrderQuantitySet(maxOrderQuantity))
		{
			final long newTotalQuantityAfterProductMaxOrder = Math.min(newTotalQuantityAfterStockLimit,
					maxOrderQuantity.longValue());
			return (newTotalQuantityAfterProductMaxOrder - cartLevel);
		}
		return (newTotalQuantityAfterStockLimit - cartLevel);
	}

}
