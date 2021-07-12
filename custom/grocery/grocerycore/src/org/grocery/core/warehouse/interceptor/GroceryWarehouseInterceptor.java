/**
 *
 */
package org.grocery.core.warehouse.interceptor;

import de.hybris.platform.basecommerce.enums.PointOfServiceTypeEnum;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.grocery.core.constants.GroceryCoreConstants;


/**
 * @author ankituniyal
 *
 */
public class GroceryWarehouseInterceptor implements PrepareInterceptor<WarehouseModel>
{

	private static final Logger LOG = Logger.getLogger(GroceryWarehouseInterceptor.class);

	private ModelService modelService;

	@Override
	public void onPrepare(final WarehouseModel warehouse, final InterceptorContext ctx) throws InterceptorException
	{
		final String warehouseCode = warehouse.getCode();
		if (warehouse.getDefaultPOS() == null)
		{
			if (StringUtils.isBlank(warehouseCode))
			{
				throw new InterceptorException("Warehouse code is blank");
			}
			else
			{
				LOG.debug("Creating new POS using warehouse :: " + warehouseCode);
				final PointOfServiceModel pointOfServiceModel = getModelService().create(PointOfServiceModel.class);
				pointOfServiceModel.setName(warehouseCode + GroceryCoreConstants.DEFAULT_SERVICEABILITY_STORE_APPEND);
				pointOfServiceModel.setDisplayName(warehouseCode +  GroceryCoreConstants.DEFAULT_SERVICEABILITY_STORE_APPEND);
				pointOfServiceModel.setType(PointOfServiceTypeEnum.STORE);
				pointOfServiceModel.setLatitude(0.0);
				pointOfServiceModel.setLongitude(0.0);

				LOG.debug("Setting base store for POS");
				final BaseStoreModel baseStore = CollectionUtils.isNotEmpty(warehouse.getBaseStores())
						? warehouse.getBaseStores().iterator().next()
						: null;
				pointOfServiceModel.setBaseStore(baseStore);

				final List<WarehouseModel> warehouses = new ArrayList<>();
				warehouses.add(warehouse);

				LOG.debug("Setting warehouse ");
				pointOfServiceModel.setWarehouses(warehouses);
				warehouse.setDefaultPOS(pointOfServiceModel);
			}
		}
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}
}
