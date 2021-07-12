


/**
 *
 */
package org.grocery.core.warehouse.service.Impl;

import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.List;

import org.grocery.core.model.ServiceabilityAreaModel;
import org.grocery.core.warehouse.dao.GroceryWarehouseDao;
import org.grocery.core.warehouse.service.GroceryWarehouseService;


/**
 * @author ankituniyal
 *
 */
public class GroceryWarehouseServiceImpl implements GroceryWarehouseService
{

	private GroceryWarehouseDao groceryWarehouseDao;

	@Override
	public List<WarehouseModel> getWarehouseForProductAndPost(final String productCode, final long postCode)
	{
		return getGroceryWarehouseDao().getWarehouseForProductAndPost(productCode, postCode);
	}

	@Override
	public List<StockLevelModel> getStockLevelForProductAndPost(final String productCode, final long postCode)
	{
		return getGroceryWarehouseDao().getStockLevelForProductAndPost(productCode, postCode);
	}

	@Override
	public ServiceabilityAreaModel getServiceabilityAreaForPostalCode(final long postalCode)
	{
		return getGroceryWarehouseDao().getServiceabilityAreaForPostalCode(postalCode);
	}

	/**
	 * @return the groceryWarehouseDao
	 */
	public GroceryWarehouseDao getGroceryWarehouseDao()
	{
		return groceryWarehouseDao;
	}

	/**
	 * @param groceryWarehouseDao
	 *           the groceryWarehouseDao to set
	 */
	public void setGroceryWarehouseDao(final GroceryWarehouseDao groceryWarehouseDao)
	{
		this.groceryWarehouseDao = groceryWarehouseDao;
	}

}
