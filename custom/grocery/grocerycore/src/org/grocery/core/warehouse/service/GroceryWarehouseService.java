/**
 *
 */
package org.grocery.core.warehouse.service;

import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.List;

import org.grocery.core.model.ServiceabilityAreaModel;


/**
 * @author ankituniyal
 *
 */
public interface GroceryWarehouseService
{
	/**
	 * @param code
	 * @param postCode
	 * @return
	 */
	List<WarehouseModel> getWarehouseForProductAndPost(final String productCode, final long postCode);

	List<StockLevelModel> getStockLevelForProductAndPost(final String productCode, final long postCode);

	/**
	 * @param postalCode
	 * @return
	 */
	ServiceabilityAreaModel getServiceabilityAreaForPostalCode(final long postalCode);

}
