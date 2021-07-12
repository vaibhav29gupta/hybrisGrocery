/**
 *
 */
package org.grocery.facade.productInterest;

import de.hybris.platform.customerinterestsfacades.data.ProductInterestData;
import de.hybris.platform.customerinterestsfacades.productinterest.impl.DefaultProductInterestFacade;
import de.hybris.platform.customerinterestsservices.model.ProductInterestModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.storelocator.pos.PointOfServiceService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.grocery.facades.shipment.ShipmentFacade;


/**
 * @author ankituniyal
 *
 */
public class GroceryProductInterestFacade extends DefaultProductInterestFacade
{

	private ShipmentFacade shipmentFacade;
	private PointOfServiceService pointOfServiceService;

	private static final Logger LOG = Logger.getLogger(GroceryProductInterestFacade.class);

	@Override
	public void saveProductInterest(final ProductInterestData productInterest)
	{
		final ProductInterestModel modifiedproductInterest = getProductInterest(productInterest.getProduct().getCode(),
				productInterest.getNotificationType()).orElse(new ProductInterestModel());

		final String currentStoreId = getShipmentFacade().getCurrentStoreID();
		LOG.debug("Store Id to be set in product interest " + currentStoreId);

		if (StringUtils.isNotBlank(currentStoreId))
		{
			final PointOfServiceModel pointOfService = getPointOfServiceService().getPointOfServiceForName(currentStoreId);
			modifiedproductInterest.setPointOfService(pointOfService);
			LOG.debug("POS set in product interest");
		}

		getProductInterestReverseConverter().convert(productInterest, modifiedproductInterest);
		getProductInterestService().saveProductInterest(modifiedproductInterest);
	}

	/**
	 * @return the pointOfServiceService
	 */
	public PointOfServiceService getPointOfServiceService()
	{
		return pointOfServiceService;
	}


	/**
	 * @param pointOfServiceService
	 *           the pointOfServiceService to set
	 */
	public void setPointOfServiceService(final PointOfServiceService pointOfServiceService)
	{
		this.pointOfServiceService = pointOfServiceService;
	}


	/**
	 * @return the shipmentFacade
	 */
	public ShipmentFacade getShipmentFacade()
	{
		return shipmentFacade;
	}

	/**
	 * @param shipmentFacade
	 *           the shipmentFacade to set
	 */
	public void setShipmentFacade(final ShipmentFacade shipmentFacade)
	{
		this.shipmentFacade = shipmentFacade;
	}


}
