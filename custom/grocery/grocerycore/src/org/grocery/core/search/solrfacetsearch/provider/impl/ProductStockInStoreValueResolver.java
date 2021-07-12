/*
 *
 */
package org.grocery.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.commerceservices.stock.CommerceStockService;
import de.hybris.platform.commerceservices.stock.strategies.WarehouseSelectionStrategy;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.spi.InputDocument;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider.FieldType;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractValueResolver;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.stock.strategy.StockLevelStatusStrategy;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;


/**
 * Resolver to index the Stock Status for all the warehouses.
 *
 */
public class ProductStockInStoreValueResolver extends AbstractValueResolver<ProductModel, Object, Object>
{
	/** The field name provider. */
	private FieldNameProvider fieldNameProvider;

	private BaseStoreService baseStoreService;

	private StockService stockService;

	private CommerceStockService commerceStockService;

	private StockLevelStatusStrategy stockLevelStatusStrategy;

	private WarehouseSelectionStrategy warehouseSelectionStrategy;

	/** The STOCK_SPLIT_CHAR Constant. */
	private static final String STOCK_SPLIT_CHAR = "_";
	private static final String STOCK_FOR_ALL = "all";


	/**
	 * Resolver to index Stock status for all warehouses.
	 *
	 * @param document
	 *           the document
	 * @param paramIndexerBatchContext
	 *           the param indexer batch context
	 * @param indexedProperty
	 *           the indexed property
	 * @param product
	 *           the product
	 * @param paramValueResolverContext
	 *           the param value resolver context
	 * @throws FieldValueProviderException
	 *            the field value provider exception
	 */
	@Override
	protected void addFieldValues(final InputDocument document, final IndexerBatchContext paramIndexerBatchContext,
			final IndexedProperty indexedProperty, final ProductModel product,
			final de.hybris.platform.solrfacetsearch.provider.impl.AbstractValueResolver.ValueResolverContext<Object, Object> paramValueResolverContext)
			throws FieldValueProviderException
	{

		final List<String> stockLevelStatusAndWarehouse = getProductPosStockValues(product);

		if (CollectionUtils.isNotEmpty(stockLevelStatusAndWarehouse))
		{
			document.addField(getFieldNameProvider().getFieldName(indexedProperty, null, FieldType.INDEX),
					stockLevelStatusAndWarehouse);
		}

	}

	/**
	 * Gets the product warehouse stock values.
	 *
	 * @param product
	 *           the product
	 * @return the product warehouse stock values
	 */
	protected List<String> getProductPosStockValues(final ProductModel product)
	{
		final BaseStoreModel baseStore = getBaseStoreService().getCurrentBaseStore();
		final List<WarehouseModel> warehouses = baseStore.getWarehouses();
		if (CollectionUtils.isEmpty(warehouses))
		{
			return Collections.emptyList();
		}

		Boolean available = false;
		final List<String> resultStatusList = new ArrayList<>();
		for (final WarehouseModel warehouse : warehouses)
		{
			final Collection<PointOfServiceModel> posList = warehouse.getPointsOfService();

			if (CollectionUtils.isNotEmpty(posList))
			{

				for (final PointOfServiceModel pointOfService : posList)
				{

					final Long availableStockLevel = getCommerceStockService().getStockLevelForProductAndPointOfService(product,
							pointOfService);

					final StringBuilder resultStatus = new StringBuilder(pointOfService.getName());
					resultStatus.append(STOCK_SPLIT_CHAR);

					if (availableStockLevel != null && availableStockLevel > 0L)
					{
						available = true;
						resultStatus.append(StockLevelStatus.INSTOCK.getCode());
					}
					else
					{
						resultStatus.append(StockLevelStatus.OUTOFSTOCK.getCode());
					}
					resultStatusList.add(resultStatus.toString());

				}
			}
		}

		final StringBuilder resultStatus = new StringBuilder(STOCK_FOR_ALL);
		resultStatus.append(STOCK_SPLIT_CHAR);

		if (BooleanUtils.isTrue(available))
		{
			resultStatus.append(StockLevelStatus.INSTOCK.getCode());
		}
		else
		{
			resultStatus.append(StockLevelStatus.OUTOFSTOCK.getCode());
		}
		resultStatusList.add(resultStatus.toString());

		return resultStatusList;
	}

	/**
	 * Gets the field name provider.
	 *
	 * @return the fieldNameProvider
	 */
	public FieldNameProvider getFieldNameProvider()
	{
		return fieldNameProvider;
	}

	/**
	 * Sets the field name provider.
	 *
	 * @param fieldNameProvider
	 *           the fieldNameProvider to set
	 */
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
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
	 * @return the stockService
	 */
	public StockService getStockService()
	{
		return stockService;
	}

	/**
	 * @param stockService
	 *           the stockService to set
	 */
	public void setStockService(final StockService stockService)
	{
		this.stockService = stockService;
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
	 * @return the warehouseSelectionStrategy
	 */
	public WarehouseSelectionStrategy getWarehouseSelectionStrategy()
	{
		return warehouseSelectionStrategy;
	}

	/**
	 * @param warehouseSelectionStrategy
	 *           the warehouseSelectionStrategy to set
	 */
	public void setWarehouseSelectionStrategy(final WarehouseSelectionStrategy warehouseSelectionStrategy)
	{
		this.warehouseSelectionStrategy = warehouseSelectionStrategy;
	}


}

