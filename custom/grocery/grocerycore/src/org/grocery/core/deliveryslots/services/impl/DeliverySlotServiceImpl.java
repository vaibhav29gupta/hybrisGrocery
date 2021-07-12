package org.grocery.core.deliveryslots.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.Date;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.grocery.core.deliveryslots.daos.DeliverySlotDao;
import org.grocery.core.deliveryslots.services.DeliverySlotService;
import org.grocery.core.model.DeliverySlotInfoModel;
import org.grocery.core.model.DeliverySlotTimeConfigModel;


/**
 * Service class for implementing methods for Delivery Slot Selection
 */
public class DeliverySlotServiceImpl implements DeliverySlotService
{
	private static final String PARAMETER_CODE_NOT_NULL = "Parameter code must not be null";

	private DeliverySlotDao deliverySlotDao;

	private ModelService modelService;

	private FlexibleSearchService flexibleSearchService;

	private static final Logger LOGGER = Logger.getLogger(DeliverySlotServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DeliverySlotTimeConfigModel getDeliverySlotTimesForCode(final String code)
	{
		validateParameterNotNull(code, PARAMETER_CODE_NOT_NULL);

		return getDeliverySlotDao().findDeliverySlotTimesForCode(code);
	}

	@Override
	public void updateDeliverySlot(final CartModel cart, final Date startTime, final Date endTime, final Date deliveryTime)
	{
		DeliverySlotInfoModel deliverySlot = cart.getDeliverySlotInfo();
		if (Objects.isNull(deliverySlot))
		{
			deliverySlot = getModelService().create(DeliverySlotInfoModel.class);
			deliverySlot.setDeliveryDate(deliveryTime);
			deliverySlot.setDeliveryStartTime(startTime);
			deliverySlot.setDeliveryEndTime(endTime);
			getModelService().save(deliverySlot);
			cart.setDeliverySlotInfo(deliverySlot);
			getModelService().save(cart);
		}
	}

	/**
	 * @return the deliverySlotDao
	 */
	public DeliverySlotDao getDeliverySlotDao()
	{
		return deliverySlotDao;
	}

	/**
	 * @param deliverySlotDao
	 *           the deliverySlotDao to set
	 */
	public void setDeliverySlotDao(final DeliverySlotDao deliverySlotDayDao)
	{
		this.deliverySlotDao = deliverySlotDayDao;
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

	/**
	 * @return
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	@Override
	public void saveDeliverySlotInfo(final DeliverySlotInfoModel DeliverySlotInfoModel, final CartModel cartModel)
	{
		getModelService().save(DeliverySlotInfoModel);
		cartModel.setDeliverySlotInfo(DeliverySlotInfoModel);
		getModelService().saveAll(cartModel);
		getModelService().refresh(cartModel);
	}
}
