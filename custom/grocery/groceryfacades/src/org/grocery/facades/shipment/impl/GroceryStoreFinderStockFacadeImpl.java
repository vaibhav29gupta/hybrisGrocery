/**
 *
 */
package org.grocery.facades.shipment.impl;

import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commerceservices.storefinder.StoreFinderService;
import de.hybris.platform.commerceservices.storefinder.data.PointOfServiceDistanceData;
import de.hybris.platform.commerceservices.storefinder.data.StoreFinderSearchPageData;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.ArrayList;
import java.util.List;

import org.grocery.core.warehouse.service.GroceryStoreFinderService;
import org.grocery.facades.shipment.GroceryStoreFinderStockFacade;


/**
 * @author bhavya2486
 *
 */
public class GroceryStoreFinderStockFacadeImpl<ITEM extends PointOfServiceData> implements GroceryStoreFinderStockFacade
{

	private BaseStoreService baseStoreService;
	private Converter<PointOfServiceModel, PointOfServiceData> pointOfServiceConverter;
	private StoreFinderService<PointOfServiceDistanceData, StoreFinderSearchPageData<PointOfServiceDistanceData>> storeFinderService;
	private Converter<PointOfServiceDistanceData, PointOfServiceData> pointOfServiceDistanceDataConverter;
	private GroceryStoreFinderService groceryStoreFinderService;



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

	@Override
	public StoreFinderSearchPageData<PointOfServiceData> posSearch(final String location, final PageableData pageableData)
	{
		final StoreFinderSearchPageData<PointOfServiceDistanceData> storeFinderSearchPageData = getGroceryStoreFinderService()
				.locationSearchPos(getBaseStoreService().getCurrentBaseStore(), location, pageableData);
		final StoreFinderSearchPageData<PointOfServiceData> data = getResultForPOSData(storeFinderSearchPageData);
		return data;
	}

	protected StoreFinderSearchPageData<PointOfServiceData> getResultForPOSData(
			final StoreFinderSearchPageData<PointOfServiceDistanceData> storeFinderSearchPageData)
	{
		final List<PointOfServiceData> result = new ArrayList<PointOfServiceData>(storeFinderSearchPageData.getResults().size());
		for (final PointOfServiceDistanceData distanceData : storeFinderSearchPageData.getResults())
		{
			final PointOfServiceData posStockData = getPointOfServiceConverter().convert(distanceData.getPointOfService());
			posStockData.setFormattedDistance(getPointOfServiceDistanceDataConverter().convert(distanceData).getFormattedDistance());
			result.add(posStockData);
		}
		return createSearchResult(result, storeFinderSearchPageData.getPagination());
	}

	protected StoreFinderSearchPageData<PointOfServiceData> createSearchResult(final List<PointOfServiceData> results,
			final PaginationData paginationData)
	{
		final StoreFinderSearchPageData<PointOfServiceData> searchPageData = new StoreFinderSearchPageData<PointOfServiceData>();
		searchPageData.setResults(results);
		searchPageData.setPagination(paginationData);

		return searchPageData;
	}


	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	public StoreFinderService<PointOfServiceDistanceData, StoreFinderSearchPageData<PointOfServiceDistanceData>> getStoreFinderService()
	{
		return storeFinderService;
	}

	public void setStoreFinderService(
			final StoreFinderService<PointOfServiceDistanceData, StoreFinderSearchPageData<PointOfServiceDistanceData>> storeFinderService)
	{
		this.storeFinderService = storeFinderService;
	}


	/**
	 * @return the pointOfServiceConverter
	 */
	public Converter<PointOfServiceModel, PointOfServiceData> getPointOfServiceConverter()
	{
		return pointOfServiceConverter;
	}


	/**
	 * @param pointOfServiceConverter
	 *           the pointOfServiceConverter to set
	 */
	public void setPointOfServiceConverter(final Converter<PointOfServiceModel, PointOfServiceData> pointOfServiceConverter)
	{
		this.pointOfServiceConverter = pointOfServiceConverter;
	}

	/**
	 * @return the pointOfServiceDistanceDataConverter
	 */
	public Converter<PointOfServiceDistanceData, PointOfServiceData> getPointOfServiceDistanceDataConverter()
	{
		return pointOfServiceDistanceDataConverter;
	}

	/**
	 * @param pointOfServiceDistanceDataConverter
	 *           the pointOfServiceDistanceDataConverter to set
	 */
	public void setPointOfServiceDistanceDataConverter(
			final Converter<PointOfServiceDistanceData, PointOfServiceData> pointOfServiceDistanceDataConverter)
	{
		this.pointOfServiceDistanceDataConverter = pointOfServiceDistanceDataConverter;
	}

}
