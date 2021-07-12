/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.product.converters.populator.ProductStockPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.stock.CommerceStockService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.storelocator.pos.PointOfServiceService;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.grocery.core.enums.ShipmentTypeEnum;
import org.grocery.core.warehouse.service.GroceryStoreFinderService;
import org.grocery.core.warehouse.service.GroceryWarehouseService;
import org.grocery.facades.shipment.ShipmentFacade;


/**
 * @author ankituniyal
 *
 */
public class ProductServiceabilityGroceryPopulator<SOURCE extends ProductModel, TARGET extends ProductData>
		extends ProductStockPopulator<SOURCE, TARGET>
{
	private static final Logger LOG = Logger.getLogger(ProductServiceabilityGroceryPopulator.class);

	private ShipmentFacade shipmentFacade;

	private BaseStoreService baseStoreService;

	private PointOfServiceService pointOfServiceService;

	private GroceryWarehouseService groceryWarehouseService;

	private CommerceStockService commerceStockService;

	private GroceryStoreFinderService groceryStoreFinderService;

	@Override
	public void populate(final SOURCE productModel, final TARGET productData)
	{
		productData.setIsServiceable(Boolean.FALSE);
		long availableStockLevel = 0;
		final String shipmentType = shipmentFacade.getCurrentShipmentMethod();
		if (StringUtils.isBlank(shipmentType))
		{
			LOG.debug("shipment type is null in session");
			productData.setIsServiceable(Boolean.TRUE);
		}
		else
		{
			if (ShipmentTypeEnum.DELIVERY.equals(ShipmentTypeEnum.valueOf(shipmentType)) && productModel.isAvailableForDelivery())
			{
				final String postCode = shipmentFacade.getCurrentPostalCode();

				LOG.debug("Fetch warehouse list for the product in stock and the postcode " + postCode);
				try
				{
					final List<WarehouseModel> warehouses = StringUtils.isNotBlank(postCode)
							? getGroceryWarehouseService().getWarehouseForProductAndPost(productData.getCode(), Long.parseLong(postCode))
							: (List<WarehouseModel>) CollectionUtils.EMPTY_COLLECTION;

					if (CollectionUtils.isNotEmpty(warehouses))
					{
						productData.setIsServiceable(Boolean.TRUE);
					}
				}
				catch (final NumberFormatException e)
				{
					LOG.debug("Can't parse postCode" + e);
				}
			}

			if (ShipmentTypeEnum.PICKUP.equals(ShipmentTypeEnum.valueOf(shipmentType)) && productModel.isAvailableForPickUp())
			{
				final String storeName = shipmentFacade.getCurrentStoreID();
				final BaseStoreModel currentBaseStore = getBaseStoreService().getCurrentBaseStore();
				LOG.debug("Get POS for store " + storeName);

				final PointOfServiceModel pointOfService = StringUtils.isNotBlank(storeName)
						? getGroceryStoreFinderService().getPointOfServiceForName(currentBaseStore, storeName)
						: null;

				if (pointOfService != null && CollectionUtils.isNotEmpty(pointOfService.getWarehouses()))
				{
					availableStockLevel = getCommerceStockService().getStockLevelForProductAndPointOfService(productModel,
							pointOfService);

					productData.setIsServiceable(availableStockLevel > 0L);
				}
			}

		}
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
	 * @return the groceryWarehouseService
	 */
	public GroceryWarehouseService getGroceryWarehouseService()
	{
		return groceryWarehouseService;
	}

	/**
	 * @param groceryWarehouseService
	 *           the groceryWarehouseService to set
	 */
	public void setGroceryWarehouseService(final GroceryWarehouseService groceryWarehouseService)
	{
		this.groceryWarehouseService = groceryWarehouseService;
	}


	/**
	 * @return the shipmentFacade
	 */
	public ShipmentFacade getShipmentFacade()
	{
		return shipmentFacade;
	}


	/**
	 * @param shipmentFacade
	 *           the shipmentFacade to set
	 */
	public void setShipmentFacade(final ShipmentFacade shipmentFacade)
	{
		this.shipmentFacade = shipmentFacade;
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


	/**
	 * @return the baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}


	/**
	 * @param baseStoreService
	 *           the baseStoreService to set
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}


	/**
	 * @return the groceryStoreFinderService
	 */
	public GroceryStoreFinderService getGroceryStoreFinderService()
	{
		return groceryStoreFinderService;
	}


	/**
	 * @param groceryStoreFinderService
	 *           the groceryStoreFinderService to set
	 */
	public void setGroceryStoreFinderService(final GroceryStoreFinderService groceryStoreFinderService)
	{
		this.groceryStoreFinderService = groceryStoreFinderService;
	}



}
