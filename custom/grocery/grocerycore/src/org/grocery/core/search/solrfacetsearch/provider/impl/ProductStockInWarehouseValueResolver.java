/*
 *
 */
package org.grocery.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.commerceservices.stock.strategies.WarehouseSelectionStrategy;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;


/**
 * Resolver to index the Stock Status for all the warehouses.
 *
 */
public class ProductStockInWarehouseValueResolver extends AbstractValueResolver<ProductModel, Object, Object>
{
	/** The field name provider. */
	private FieldNameProvider fieldNameProvider;

	private BaseStoreService baseStoreService;

	private StockService stockService;

	private WarehouseSelectionStrategy warehouseSelectionStrategy;

	private StockLevelStatusStrategy stockLevelStatusStrategy;

	/** The STOCK_SPLIT_CHAR Constant. */
	private static final String STOCK_SPLIT_CHAR = "_";

	/** The Constant listInstockStatus. */
	private static final List<String> ALLOWEDINSTOCKSTATUS = Arrays.asList("inStock", "lowStock");

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

		final List<String> stockLevelStatusAndWarehouse = getProductWarehouseStockValues(product);

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
	protected List<String> getProductWarehouseStockValues(final ProductModel product)
	{
		final BaseStoreModel baseStore = getBaseStoreService().getCurrentBaseStore();

		final Collection<StockLevelModel> stockLevels = stockService.getStockLevels(product,
				getWarehouseSelectionStrategy().getWarehousesForBaseStore(baseStore));

		final List<String> resultStatusList = new ArrayList<>();

		for (final StockLevelModel stockLevel : stockLevels)
		{
			final StockLevelStatus stockStatus = getStockLevelStatusStrategy().checkStatus(stockLevel);

			final StringBuilder resultStatus = new StringBuilder(stockLevel.getWarehouse().getCode());
			resultStatus.append(STOCK_SPLIT_CHAR);

			if (ALLOWEDINSTOCKSTATUS.contains(stockStatus))
			{
				resultStatus.append(StockLevelStatus.INSTOCK.getCode());
			}
			else
			{
				resultStatus.append(StockLevelStatus.OUTOFSTOCK.getCode());
			}
			resultStatusList.add(resultStatus.toString());
		}

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
}

