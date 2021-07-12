/**
 *
 */
package org.grocery.core.warehouse.dao;

import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.grocery.core.model.ServiceabilityAreaModel;


/**
 * @author ankituniyal
 *
 */
public interface GroceryWarehouseDao
{

	/**
	 * @param code
	 * @param postCode
	 * @return
	 */
	List<WarehouseModel> getWarehouseForProductAndPost(String code, long postCode);


	Collection<PointOfServiceModel> getPosForLocationText(Map<String, Object> paramMap);

	/**
	 * @param postalCode
	 * @return
	 */
	ServiceabilityAreaModel getServiceabilityAreaForPostalCode(long postalCode);

	/**
	 * @param productCode
	 * @param postalCode
	 * @return
	 */
	List<StockLevelModel> getStockLevelForProductAndPost(String productCode, long postalCode);

}
