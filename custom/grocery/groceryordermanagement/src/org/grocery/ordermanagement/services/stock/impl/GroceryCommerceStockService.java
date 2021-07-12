package org.grocery.ordermanagement.services.stock.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.warehousing.atp.services.impl.WarehousingCommerceStockService;

public class GroceryCommerceStockService extends WarehousingCommerceStockService
{
	@Override
	public Long getStockLevelForProductAndPointOfService(final ProductModel product, final PointOfServiceModel pointOfService)
	{
		validateParameterNotNull(product, "product cannot be null");
		validateParameterNotNull(pointOfService, "pointOfService cannot be null");

		return getCommerceStockLevelCalculationStrategy().calculateAvailability(
				getStockService().getStockLevels(product, pointOfService.getWarehouses()));
	}
}
