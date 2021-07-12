/**
 *
 */
package org.grocery.core.warehouse.interceptor;

import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

/**
 * @author ankituniyal
 *
 */
public class GroceryWarehouseRemoveInterceptor implements RemoveInterceptor<WarehouseModel>
{

	private ModelService modelService;

	@Override
	public void onRemove(final WarehouseModel warehouse, final InterceptorContext ctx) throws InterceptorException
	{
		if (warehouse.getDefaultPOS() != null)
		{
			final PointOfServiceModel pointOfService = warehouse.getDefaultPOS();
			getModelService().remove(pointOfService);
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
