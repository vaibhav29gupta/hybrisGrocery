/**
 *
 */
package org.grocery.core.warehouse.service;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.storefinder.data.PointOfServiceDistanceData;
import de.hybris.platform.commerceservices.storefinder.data.StoreFinderSearchPageData;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

//import de.hybris.platform.commerceservices.search.pagedata.PageableData;
//import de.hybris.platform.commerceservices.storefinder.data.PointOfServiceDistanceData;
//import de.hybris.platform.commerceservices.storefinder.data.StoreFinderSearchPageData;
//import de.hybris.platform.store.BaseStoreModel;


/**
 * @author amitsharma09
 *
 */
public interface GroceryStoreFinderService<ITEM extends PointOfServiceDistanceData, RESULT extends StoreFinderSearchPageData<ITEM>>
{
	public RESULT locationSearchPos(final BaseStoreModel baseStore, final String locationText, final PageableData pageableData);

	public PointOfServiceModel getPointOfServiceForName(BaseStoreModel baseStore, String name);

}
