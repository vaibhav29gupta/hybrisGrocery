/**
 *
 */
package org.grocery.core.warehouse.interceptor;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.grocery.core.model.ServiceabilityAreaModel;
import org.grocery.core.warehouse.service.GroceryWarehouseService;


/**
 * @author ojasmodi
 *
 */
public class GroceryServiceabilityAreaInterceptor implements PrepareInterceptor<ServiceabilityAreaModel>
{
	private static final Logger LOG = Logger.getLogger(GroceryServiceabilityAreaInterceptor.class);


	GroceryWarehouseService groceryWarehouseService;

	@Override
	public void onPrepare(final ServiceabilityAreaModel serviceabilityArea, final InterceptorContext arg1)
			throws InterceptorException
	{
		final String serviceabilityAreaCode = serviceabilityArea.getCode();
		if (StringUtils.isBlank(serviceabilityAreaCode))
		{
			throw new InterceptorException("Serviceability area code is blank");
		}
		else
		{
			LOG.debug("Checking for duplicate postal codes for serviceabilitya area :: " + serviceabilityAreaCode);
			ServiceabilityAreaModel serviceabilityAreaModel = getGroceryWarehouseService()
					.getServiceabilityAreaForPostalCode(serviceabilityArea.getStartPostalCode());
			if (null == serviceabilityAreaModel)
			{
				serviceabilityAreaModel = getGroceryWarehouseService()
						.getServiceabilityAreaForPostalCode(serviceabilityArea.getEndPostalCode());
				if (null != serviceabilityAreaModel)
				{
					throw new InterceptorException("Ending postal code is already set for another warehouse.");
				}
			}
			else
			{
				throw new InterceptorException("Starting postal code is already set for another warehouse.");
			}
		}
	}

	/**
	 * @return the groceryWarehouseService
	 */
	public GroceryWarehouseService getGroceryWarehouseService()
	{
		return groceryWarehouseService;
	}


	/**
	 * @param groceryWarehouseService
	 *           the groceryWarehouseService to set
	 */
	public void setGroceryWarehouseService(final GroceryWarehouseService groceryWarehouseService)
	{
		this.groceryWarehouseService = groceryWarehouseService;
	}

}
