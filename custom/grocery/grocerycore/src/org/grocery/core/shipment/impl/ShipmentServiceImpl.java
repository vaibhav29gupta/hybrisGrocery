/**
 *
 */
package org.grocery.core.shipment.impl;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartModificationStatus;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.commerceservices.stock.CommerceStockService;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.storelocator.pos.PointOfServiceService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;
import org.grocery.core.shipment.ShipmentService;


/**
 * @author ankituniyal
 *
 */
public class ShipmentServiceImpl implements ShipmentService
{
	private static final Logger LOG = Logger.getLogger(ShipmentServiceImpl.class);

	private CartService cartService;

	private CommerceCartService commerceCartService;

	private PointOfServiceService pointOfServiceService;

	private CommerceStockService commerceStockService;

	/**
	 * @param cartModel
	 * @param entry
	 * @param unsuccessfulModifications
	 */

	@Override
	public List<CommerceCartModification> updateEntriesForPOS(final String storeName) throws CommerceCartModificationException
	{

		LOG.debug("Updating entries for pos:: " + storeName);
		final CartModel cartModel = getCartService().getSessionCart();
		final List<AbstractOrderEntryModel> cartEntries = cartModel.getEntries();
		final List<CommerceCartModification> unsuccessfulModifications = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(cartEntries))
		{
			for (final AbstractOrderEntryModel entry : cartEntries)
			{
				updatePOS(cartModel, entry, storeName, unsuccessfulModifications);
			}
		}
		LOG.debug("Looping through cart entries for updation");
		return unsuccessfulModifications;

	}

	/**
	 * @param cartModel
	 * @param entry
	 * @param storeName
	 * @param unsuccessfulModifications
	 * @throws CommerceCartModificationException
	 */
	private void updatePOS(final CartModel cartModel, final AbstractOrderEntryModel entry, final String storeName,
			final List<CommerceCartModification> unsuccessfulModifications) throws CommerceCartModificationException
	{

		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(cartModel);
		parameter.setEntryNumber(entry.getEntryNumber().longValue());

		final PointOfServiceModel consolidatedPOS = getPointOfServiceService().getPointOfServiceForName(storeName);
		parameter.setPointOfService(consolidatedPOS);

		LOG.debug("Calling OOTB cart service for updating entries");
		final CommerceCartModification modification = getCommerceCartService().updatePointOfServiceForCartEntry(parameter);
		if (!CommerceCartModificationStatus.SUCCESS.equals(modification.getStatusCode()))
		{
			unsuccessfulModifications.add(modification);
		}

	}

	@Override
	public Boolean isCartEmpty()
	{
		final CartModel cartModel = getCartService().getSessionCart();
		final List<AbstractOrderEntryModel> cartEntries = cartModel.getEntries();

		if (CollectionUtils.isEmpty(cartEntries))
		{
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public List<ProductModel> getUnavailableProducts(final String storeName)
	{
		final CartModel cartModel = getCartService().getSessionCart();
		final List<AbstractOrderEntryModel> cartEntries = cartModel.getEntries();

		if (CollectionUtils.isEmpty(cartEntries))
		{
			return Collections.emptyList();
		}

		final List<ProductModel> productList = new ArrayList<>();
		final PointOfServiceModel pos = getPointOfServiceService().getPointOfServiceForName(storeName);

		for (final AbstractOrderEntryModel orderEntry : cartEntries)
		{
			final ProductModel productModel = orderEntry.getProduct();

			final Long stockLevel = getCommerceStockService().getStockLevelForProductAndPointOfService(productModel, pos);

			final boolean isProductAvailable = stockLevel == null || stockLevel.intValue() >= calculateCartLevel(orderEntry);

			if (BooleanUtils.isNotTrue(isProductAvailable))
			{
				LOG.debug("Adding unAvailable products to list");
				productList.add(productModel);
			}
		}
		return productList;
	}

	/**
	 * @param orderEntry
	 * @return
	 */
	private long calculateCartLevel(final AbstractOrderEntryModel entryModel)
	{
		final long cartLevel = entryModel.getQuantity() != null ? entryModel.getQuantity().longValue() : 0;
		return cartLevel;
	}

	/**
	 * @return the cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService
	 *           the cartService to set
	 */
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	/**
	 * @return the commerceCartService
	 */
	public CommerceCartService getCommerceCartService()
	{
		return commerceCartService;
	}

	/**
	 * @param commerceCartService
	 *           the commerceCartService to set
	 */
	public void setCommerceCartService(final CommerceCartService commerceCartService)
	{
		this.commerceCartService = commerceCartService;
	}

	/**
	 * @return the pointOfServiceService
	 */
	public PointOfServiceService getPointOfServiceService()
	{
		return pointOfServiceService;
	}

	/**
	 * @param pointOfServiceService
	 *           the pointOfServiceService to set
	 */
	public void setPointOfServiceService(final PointOfServiceService pointOfServiceService)
	{
		this.pointOfServiceService = pointOfServiceService;
	}

	/**
	 * @return the commerceStockService
	 */
	public CommerceStockService getCommerceStockService()
	{
		return commerceStockService;
	}

	/**
	 * @param commerceStockService
	 *           the commerceStockService to set
	 */
	public void setCommerceStockService(final CommerceStockService commerceStockService)
	{
		this.commerceStockService = commerceStockService;
	}
}
