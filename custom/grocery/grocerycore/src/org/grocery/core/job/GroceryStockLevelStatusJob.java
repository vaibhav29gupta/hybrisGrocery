/**
 *
 */
package org.grocery.core.job;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.customerinterestsservices.model.ProductInterestModel;
import de.hybris.platform.stocknotificationservices.cronjob.StockLevelStatusJob;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.Date;

import org.apache.log4j.Logger;


/**
 * @author ankituniyal
 *
 */
public class GroceryStockLevelStatusJob extends StockLevelStatusJob
{

	private static final Logger LOG = Logger.getLogger(GroceryStockLevelStatusJob.class);

	@Override
	protected boolean isProductInStock(final ProductInterestModel productInterest, final Date now)
	{
		final ProductModel product = productInterest.getProduct();
		if (product == null)
		{
			modelService.remove(productInterest);
			return false;
		}
		if (!isProductOnSale(product, now))
		{
			return false;
		}

		StockLevelStatus stockLevelStatus = null;

		if (productInterest.getPointOfService() != null)
		{
			LOG.debug("Product interest contains pos");
			final PointOfServiceModel pointOfService = productInterest.getPointOfService();
			stockLevelStatus = getCommerceStockService().getStockLevelStatusForProductAndPointOfService(product, pointOfService);
		}
		else
		{
			LOG.debug("Product Interest doesn't contains pos");
			final BaseStoreModel currentBaseStore = productInterest.getBaseStore();
			stockLevelStatus = getCommerceStockService().getStockLevelStatusForProductAndBaseStore(product, currentBaseStore);
		}

		LOG.debug("Stock level status is " + stockLevelStatus);
		return StockLevelStatus.INSTOCK.equals(stockLevelStatus) || StockLevelStatus.LOWSTOCK.equals(stockLevelStatus);
	}

}
