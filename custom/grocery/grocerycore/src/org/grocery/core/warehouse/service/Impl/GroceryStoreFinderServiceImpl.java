/**
 *
 */
package org.grocery.core.warehouse.service.Impl;


import de.hybris.platform.basecommerce.enums.PointOfServiceTypeEnum;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commerceservices.store.data.GeoPoint;
import de.hybris.platform.commerceservices.storefinder.data.PointOfServiceDistanceData;
import de.hybris.platform.commerceservices.storefinder.data.StoreFinderSearchPageData;
import de.hybris.platform.commerceservices.storefinder.impl.DefaultStoreFinderService;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.GPS;
import de.hybris.platform.storelocator.exception.GeoServiceWrapperException;
import de.hybris.platform.storelocator.exception.PointOfServiceDaoException;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.grocery.core.storesession.groceryStoreSessionService;
import org.grocery.core.warehouse.dao.GroceryWarehouseDao;
import org.grocery.core.warehouse.service.GroceryStoreFinderService;
import org.springframework.util.CollectionUtils;


/**
 * @author amitsharma09
 *
 */
public class GroceryStoreFinderServiceImpl<ITEM extends PointOfServiceDistanceData> extends DefaultStoreFinderService<ITEM>
		implements GroceryStoreFinderService<ITEM, StoreFinderSearchPageData<ITEM>>
{
	private static final Logger LOG = Logger.getLogger(GroceryStoreFinderServiceImpl.class);

	private GroceryWarehouseDao groceryWarehouseDao;
	private groceryStoreSessionService groceryStoreSessionService;



	/**
	 * @return the groceryStoreSessionService
	 */
	public groceryStoreSessionService getGroceryStoreSessionService()
	{
		return groceryStoreSessionService;
	}

	/**
	 * @param groceryStoreSessionService
	 *           the groceryStoreSessionService to set
	 */
	public void setGroceryStoreSessionService(final groceryStoreSessionService groceryStoreSessionService)
	{
		this.groceryStoreSessionService = groceryStoreSessionService;
	}

	public GroceryWarehouseDao getGroceryWarehouseDao()
	{
		return groceryWarehouseDao;
	}

	public void setGroceryWarehouseDao(final GroceryWarehouseDao groceryWarehouseDao)
	{
		this.groceryWarehouseDao = groceryWarehouseDao;
	}



	@Override
	public StoreFinderSearchPageData<ITEM> locationSearch(final BaseStoreModel baseStore, final String locationText,
			final PageableData pageableData)
	{
		final GeoPoint geoPoint = new GeoPoint();

		if (locationText != null && !locationText.isEmpty())
		{
			try
			{

				if (groceryStoreSessionService.getCurrentCountry() != null
						&& groceryStoreSessionService.getCurrentCountry().getLatitude() != null
						&& groceryStoreSessionService.getCurrentCountry().getLongitude() != null)
				{

					geoPoint.setLatitude(groceryStoreSessionService.getCurrentCountry().getLatitude());
					geoPoint.setLongitude(groceryStoreSessionService.getCurrentCountry().getLongitude());
				}
				else
				{

					final GPS resolvedPoint = getGeoWebServiceWrapper()
							.geocodeAddress(generateGeoAddressForSearchQuery(baseStore, locationText));
					// Resolve the address to a point
					geoPoint.setLongitude(resolvedPoint.getDecimalLongitude());
					geoPoint.setLatitude(resolvedPoint.getDecimalLatitude());
				}

				final StoreFinderSearchPageData<ITEM> stores = doSearchPos(baseStore, locationText, geoPoint, pageableData,
						Boolean.FALSE);
				if (!CollectionUtils.isEmpty(stores.getResults()))
				{
					return stores;
				}


				return doSearch(baseStore, locationText, geoPoint, pageableData, baseStore.getMaxRadiusForPoSSearch());
			}
			catch (final GeoServiceWrapperException ex)
			{
				LOG.info("Failed to resolve location for [" + locationText + "]", ex);
			}
		}

		// Return no results
		return createSearchResult(locationText, geoPoint, Collections.<ITEM> emptyList(), createPaginationData());
	}


	@Override
	public StoreFinderSearchPageData<ITEM> locationSearchPos(final BaseStoreModel baseStore, final String locationText,
			final PageableData pageableData)
	{
		final GeoPoint geoPoint = new GeoPoint();

		if (locationText != null && !locationText.isEmpty())
		{
			try
			{

				return doSearchPos(baseStore, locationText, geoPoint, pageableData, Boolean.TRUE);
			}
			catch (final GeoServiceWrapperException ex)
			{
				LOG.info("Failed to resolve location for [" + locationText + "]", ex);
			}
		}

		// Return no results
		return createSearchResult(locationText, geoPoint, Collections.<ITEM> emptyList(), createPaginationData());
	}

	protected StoreFinderSearchPageData<ITEM> doSearchPos(final BaseStoreModel baseStore, final String locationText,
			final GeoPoint centerPoint, final PageableData pageableData, final Boolean checkCountry)

	{

		final Collection<PointOfServiceModel> posResults;

		final int resultRangeStart = pageableData.getCurrentPage() * pageableData.getPageSize();
		final int resultRangeEnd = (pageableData.getCurrentPage() + 1) * pageableData.getPageSize();

		posResults = getPointsOfServiceNear(baseStore, locationText, checkCountry);

		if (posResults != null)
		{
			// Sort all the POS
			final List<ITEM> orderedResults = calculateDistances(centerPoint, posResults);
			final PaginationData paginationData = createPagination(pageableData, posResults.size());
			// Slice the required range window out of the results
			final List<ITEM> orderedResultsWindow = orderedResults.subList(Math.min(orderedResults.size(), resultRangeStart),
					Math.min(orderedResults.size(), resultRangeEnd));

			return createSearchResult(locationText, centerPoint, orderedResultsWindow, paginationData);
		}

		// Return no results
		return createSearchResult(locationText, centerPoint, Collections.<ITEM> emptyList(), createPagination(pageableData, 0));
	}


	protected Collection<PointOfServiceModel> getPointsOfServiceNear(final BaseStoreModel baseStore, final String locationText,
			final Boolean checkCountry) throws PointOfServiceDaoException
	{

		final String location = locationText.toLowerCase() + "%";
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("baseStore", baseStore);
		paramMap.put("type", PointOfServiceTypeEnum.STORE);
		paramMap.put("locationText", "%" + location + "%");
		final CountryModel currentCountry = getGroceryStoreSessionService().getCurrentCountry();
		if (currentCountry != null && currentCountry.getIsocode() != null && checkCountry)
		{
			paramMap.put("currentCountryIso", currentCountry.getIsocode());
		}

		return getGroceryWarehouseDao().getPosForLocationText(paramMap);

	}


	protected StoreFinderSearchPageData<ITEM> createSearchResult(final String locationText, final List<ITEM> results,
			final PaginationData paginationData)
	{
		final StoreFinderSearchPageData<ITEM> searchPageData = createStoreFinderSearchPageData();
		searchPageData.setResults(results);
		searchPageData.setPagination(paginationData);

		searchPageData.setLocationText(locationText);


		return searchPageData;
	}

	@Override
	protected StoreFinderSearchPageData<ITEM> createStoreFinderSearchPageData()
	{
		return new StoreFinderSearchPageData<ITEM>();
	}

}
