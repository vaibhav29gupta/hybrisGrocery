/**
 *
 */
package org.grocery.facades.shipment;

import de.hybris.platform.commercefacades.storefinder.data.StoreFinderStockSearchPageData;
import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData;
import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceStockData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.storefinder.data.StoreFinderSearchPageData;


/**
 * @author bhavya2486
 *
 */
public interface GroceryStoreFinderStockFacade
{
	/**
	 * Returns result set with point of services for the given location free text search term
	 *
	 * @param location
	 * @param pageableData
	 * @return {@link StoreFinderStockSearchPageData} with {@link PointOfServiceStockData}
	 */
	StoreFinderSearchPageData<PointOfServiceData> posSearch(String location, PageableData pageableData);


}
