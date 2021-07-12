/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.commercefacades.product.converters.populator.StockPopulator;
import de.hybris.platform.commercefacades.product.data.StockData;
import de.hybris.platform.commerceservices.stock.CommerceStockService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.stock.strategy.StockLevelStatusStrategy;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.grocery.core.enums.ShipmentTypeEnum;
import org.grocery.core.warehouse.service.GroceryStoreFinderService;
import org.grocery.core.warehouse.service.GroceryWarehouseService;
import org.grocery.facades.shipment.ShipmentFacade;


/**
 * @author ojasmodi
 *
 */
public class GroceryStockPopulator<SOURCE extends ProductModel, TARGET extends StockData>
		extends StockPopulator<SOURCE, TARGET>
{
	private static final Logger LOG = Logger.getLogger(GroceryStockPopulator.class);

	private StockLevelStatusStrategy stockLevelStatusStrategy;

	private ShipmentFacade shipmentFacade;

	private GroceryWarehouseService groceryWarehouseService;

	private CommerceStockService commerceStockService;

	private BaseStoreService baseStoreService;

	private GroceryStoreFinderService groceryStoreFinderService;

	@Override
	public void populate(final SOURCE productModel, final TARGET stockData) throws ConversionException
	{
		final BaseStoreModel baseStore = getBaseStoreService().getCurrentBaseStore();
		stockData.setStockLevel(Long.valueOf(0));
		if (!isStockSystemEnabled(baseStore))
		{
			stockData.setStockLevelStatus(StockLevelStatus.INSTOCK);
		}
		else
		{
			final String shipmentType = getShipmentFacade().getCurrentShipmentMethod();
			if (StringUtils.isBlank(shipmentType))
			{
				LOG.debug("Shipment type is not present in session");
				stockData.setStockLevel(Long.valueOf(1));
				stockData.setStockLevelStatus(StockLevelStatus.INSTOCK);
			}
			else
			{
				stockData.setStockLevelStatus(StockLevelStatus.OUTOFSTOCK);
				long availableStockLevel = Long.valueOf(0);
				if (ShipmentTypeEnum.DELIVERY.equals(ShipmentTypeEnum.valueOf(shipmentType)) && productModel.isAvailableForDelivery())
				{
					final String postCode = shipmentFacade.getCurrentPostalCode();

					LOG.debug("Fetch stock levels for the product in stock and the postcode " + postCode);
					try
					{
						final List<StockLevelModel> stockLevels = StringUtils.isNotBlank(postCode)
								? getGroceryWarehouseService().getStockLevelForProductAndPost(productModel.getCode(),
										Long.parseLong(postCode))
								: Collections.emptyList();
						if (CollectionUtils.isNotEmpty(stockLevels))
						{
							for (final StockLevelModel s : stockLevels)
							{
								availableStockLevel += s.getAvailable();
							}
						}
						stockData.setStockLevel(availableStockLevel);
						stockData.setStockLevelStatus(getStockLevelStatusStrategy().checkStatus(stockLevels));
					}
					catch (final NumberFormatException e)
					{
						LOG.debug("Parse error in postal code" + e);
					}
				}

				if (ShipmentTypeEnum.PICKUP.equals(ShipmentTypeEnum.valueOf(shipmentType)) && productModel.isAvailableForPickUp())
				{
					final String storeName = getShipmentFacade().getCurrentStoreID();
					final PointOfServiceModel pointOfService = StringUtils.isNotBlank(storeName)
							? getGroceryStoreFinderService().getPointOfServiceForName(getBaseStoreService().getCurrentBaseStore(), storeName)
							: null;
					if (pointOfService != null && CollectionUtils.isNotEmpty(pointOfService.getWarehouses()))
					{
						stockData.setStockLevel(
								getCommerceStockService().getStockLevelForProductAndPointOfService(productModel, pointOfService));
						stockData.setStockLevelStatus(
								getCommerceStockService().getStockLevelStatusForProductAndPointOfService(productModel, pointOfService));
					}
				}
			}
		}
	}

	@Override
	protected boolean isStockSystemEnabled()
	{
		return getCommerceStockService().isStockSystemEnabled(getBaseStoreService().getCurrentBaseStore());
	}

	@Override
	protected boolean isStockSystemEnabled(final BaseStoreModel baseStore)
	{
		return getCommerceStockService().isStockSystemEnabled(baseStore);
	}

	/**
	 * @return the stockLevelStatusStrategy
	 */
	public StockLevelStatusStrategy getStockLevelStatusStrategy()
	{
		return stockLevelStatusStrategy;
	}

	/**
	 * @param stockLevelStatusStrategy
	 *           the stockLevelStatusStrategy to set
	 */
	public void setStockLevelStatusStrategy(final StockLevelStatusStrategy stockLevelStatusStrategy)
	{
		this.stockLevelStatusStrategy = stockLevelStatusStrategy;
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
	 * @return the commerceStockService
	 */
	@Override
	public CommerceStockService getCommerceStockService()
	{
		return commerceStockService;
	}

	/**
	 * @param commerceStockService
	 *           the commerceStockService to set
	 */
	@Override
	public void setCommerceStockService(final CommerceStockService commerceStockService)
	{
		this.commerceStockService = commerceStockService;
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

	/**
	 * @return the baseStoreService
	 */
	@Override
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService the baseStoreService to set
	 */
	@Override
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}




}
