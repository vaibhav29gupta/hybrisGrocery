package org.grocery.facade.deliveryslots.impl;

import de.hybris.platform.commercefacades.deliveryslot.data.DeliverySlotDayConfigData;
import de.hybris.platform.commercefacades.deliveryslot.data.DeliverySlotTimeConfigData;
import de.hybris.platform.commercefacades.deliveryslot.info.data.DeliverySlotInfoData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.storelocator.model.SpecialOpeningDayModel;
import de.hybris.platform.storelocator.pos.PointOfServiceService;
import de.hybris.platform.util.localization.Localization;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.grocery.core.deliveryslots.services.DeliverySlotService;
import org.grocery.core.model.DeliverySlotDayConfigModel;
import org.grocery.core.model.DeliverySlotInfoModel;
import org.grocery.core.model.DeliverySlotTimeConfigModel;
import org.grocery.facade.deliveryslots.DeliverySlotFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Facade class related to Delivery Slot on Checkout Address page
 */
public class DeliverySlotFacadeImpl implements DeliverySlotFacade
{
	private DeliverySlotService deliverySlotService;

	private CartService cartService;

	private ModelService modelService;

	private Converter<DeliverySlotDayConfigModel, DeliverySlotDayConfigData> deliverySlotDayConfigConverter;

	private Converter<DeliverySlotTimeConfigModel, DeliverySlotTimeConfigData> deliverySlotTimeConfigConverter;

	private Converter<DeliverySlotInfoModel, DeliverySlotInfoData> deliverySlotInfoConverter;

	private PointOfServiceService pointOfServiceService;

	private static final Logger LOGGER = LoggerFactory.getLogger(DeliverySlotFacadeImpl.class);

	@Override
	public List<DeliverySlotDayConfigData> fetchDeliverySlotDaysForStore(final String storeID, final Date orderingDate)
	{
		List<DeliverySlotDayConfigModel> deliverySlotDayConfigModelList = new ArrayList<>();
		final List<DeliverySlotDayConfigData> deliverySlotDayConfigDataList = new ArrayList<>();
		final PointOfServiceModel pointOfServiceModel = getPointOfServiceService().getPointOfServiceForName(storeID);

		try
		{
			deliverySlotDayConfigModelList = pointOfServiceModel.getDeliveryDaySlots().stream()
					.filter(slot -> (Objects.isNull(slot.getOrderingDayStartTime())
							|| orderingDate.equals(slot.getOrderingDayStartTime()) || orderingDate.after(slot.getOrderingDayStartTime()))
							&& (Objects.isNull(slot.getOrderingDayEndTime()) || orderingDate.equals(slot.getOrderingDayEndTime())
									|| orderingDate.before(slot.getOrderingDayEndTime())))
					.collect(Collectors.toList());

			Set<Date> holidays = null;
			if (Objects.nonNull(pointOfServiceModel.getOpeningSchedule())
					&& !pointOfServiceModel.getOpeningSchedule().getOpeningDays().isEmpty())
			{
				holidays = pointOfServiceModel.getOpeningSchedule().getOpeningDays().stream().filter(
						openingDay -> openingDay instanceof SpecialOpeningDayModel && ((SpecialOpeningDayModel) openingDay).isClosed())
						.map(openingDayModel -> ((SpecialOpeningDayModel) openingDayModel).getDate()).collect(Collectors.toSet());
			}

			for (final DeliverySlotDayConfigModel DeliverySlotDayConfigModel : deliverySlotDayConfigModelList)
			{
				final DeliverySlotDayConfigData deliverySlotDayConfigData = getDeliverySlotDayConfigConverter()
						.convert(DeliverySlotDayConfigModel);

				if (CollectionUtils.isNotEmpty(holidays))
				{
					final boolean isHoliday = holidays.stream().anyMatch(holiday -> { //NOSONAR
						final DateFormat formatter = new SimpleDateFormat("ddMMyyyy");
						return (formatter.format(holiday).compareTo(formatter.format(deliverySlotDayConfigData.getDeliveryDate())) == 0)
								? true
								: false;
					});
					deliverySlotDayConfigData.setIsHolidayApplicable(isHoliday);
				}
				deliverySlotDayConfigDataList.add(deliverySlotDayConfigData);
			}

		}
		catch (final Exception e)
		{
			LOGGER.error(Localization.getLocalizedString("Date List not found"));
			return deliverySlotDayConfigDataList;

		}
		return deliverySlotDayConfigDataList;
	}


	@Override
	public List<DeliverySlotTimeConfigData> fetchDeliverySlotTimesForStore(final String storeID, final Date orderingDate)
	{
		List<DeliverySlotTimeConfigModel> deliverySlotTimeConfigModelList = new ArrayList<>();
		final List<DeliverySlotTimeConfigData> deliverySlotTimeConfigDataList = new ArrayList<>();
		final PointOfServiceModel pointOfServiceModel = getPointOfServiceService().getPointOfServiceForName(storeID);

		try
		{
			deliverySlotTimeConfigModelList = pointOfServiceModel.getDeliveryTimeSlots();

			for (final DeliverySlotTimeConfigModel DeliverySlotTimeConfigModel : deliverySlotTimeConfigModelList)
			{

				final DeliverySlotTimeConfigData DeliverySlotTimeConfigData = getDeliverySlotTimeConfigConverter()
						.convert(DeliverySlotTimeConfigModel);

				deliverySlotTimeConfigDataList.add(DeliverySlotTimeConfigData);
			}
		}

		catch (final Exception e)
		{
			LOGGER.error(Localization.getLocalizedString("Date List not found"));
			return deliverySlotTimeConfigDataList;
		}
		return deliverySlotTimeConfigDataList;
	}

	@Override
	public boolean validateDeliveryDate(final String storeID, final Date processingDate, final Date deliveryDate)
	{
		boolean result = false;
		final List<DeliverySlotDayConfigData> deliveryDaySlots = this.fetchDeliverySlotDaysForStore(storeID, processingDate);
		if (CollectionUtils.isNotEmpty(deliveryDaySlots))
		{
			for (final DeliverySlotDayConfigData deliveryDaySlot : deliveryDaySlots)
			{
				if (DateUtils.isSameDay(deliveryDaySlot.getDeliveryDate(), deliveryDate))
				{
					result = true;
					break;
				}
			}
		}
		return result;
	}


	@Override
	public boolean validateDeliveryZone(final String storeID, final Date processingDate, final String deliveryTimeCode)
	{
		boolean result = false;
		final List<DeliverySlotTimeConfigData> deliveryTimeSlots = this.fetchDeliverySlotTimesForStore(storeID, processingDate);
		if (CollectionUtils.isNotEmpty(deliveryTimeSlots))
		{
			for (final DeliverySlotTimeConfigData deliverySlotDate : deliveryTimeSlots)
			{
				if (StringUtils.isNotEmpty(deliveryTimeCode) && deliveryTimeCode.equals(deliverySlotDate.getCode()))
				{
					result = true;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public boolean validateDeliverySlotTimeForStore(final String storeID, final String deliveryTimeCode)
	{
		boolean result = false;
		final Date processingDate = new Date();
		final List<DeliverySlotTimeConfigData> deliveryTimeSlots = this.fetchDeliverySlotTimesForStore(storeID, processingDate);
		if (CollectionUtils.isNotEmpty(deliveryTimeSlots))
		{
			for (final DeliverySlotTimeConfigData deliverySlotDate : deliveryTimeSlots)
			{
				if (StringUtils.isNotEmpty(deliveryTimeCode) && deliveryTimeCode.equals(deliverySlotDate.getCode()))
				{
					result = true;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public boolean updateDateAndTimeSlot(final String shippingDelDate, final String shippingDelTime)
	{
		final DeliverySlotInfoData deliverySlotInfoData = new DeliverySlotInfoData();
		final SimpleDateFormat timeFormatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
		try
		{
			deliverySlotInfoData
					.setDeliveryDate(StringUtils.isNotBlank(shippingDelDate) ? timeFormatter.parse(shippingDelDate) : null);
		}
		catch (final ParseException e)
		{
			LOGGER.error(Localization.getLocalizedString("Can not parse the Date"));
		}
		try
		{
			deliverySlotInfoData.setDeliveryStartTime(
					StringUtils.isNotBlank(shippingDelTime) ? timeFormatter.parse(shippingDelTime.split("-")[0]) : null);
			deliverySlotInfoData.setDeliveryEndTime(
					StringUtils.isNotBlank(shippingDelTime) ? timeFormatter.parse(shippingDelTime.split("-")[1]) : null);
		}
		catch (final ParseException e)
		{
			LOGGER.error(Localization.getLocalizedString("Can not parse the Date"));
		}
		if (Objects.nonNull(shippingDelDate) || Objects.nonNull(shippingDelTime) || Objects.nonNull(this.getDeliverySlotInfo()))
		{
			this.saveDeliverySlotInfo(deliverySlotInfoData);
		}
		return true;
	}

	protected DeliverySlotInfoData getDeliverySlotInfo()
	{
		final CartModel cartModel = getCartService().getSessionCart();
		if (cartModel.getDeliverySlotInfo() != null)
		{
			return getDeliverySlotInfoConverter().convert(cartModel.getDeliverySlotInfo());
		}
		return null;
	}

	protected void saveDeliverySlotInfo(final DeliverySlotInfoData deliverySlotInfoData)
	{
		final CartModel cartModel = getCartService().getSessionCart();
		DeliverySlotInfoModel deliverySlotInfoModel = null;
		if (!Objects.nonNull(cartModel.getDeliverySlotInfo()))
		{
			deliverySlotInfoModel = getModelService().create(DeliverySlotInfoModel.class);
		}
		else
		{
			deliverySlotInfoModel = cartModel.getDeliverySlotInfo();
		}
		deliverySlotInfoModel.setDeliveryDate(deliverySlotInfoData.getDeliveryDate());
		deliverySlotInfoModel.setDeliveryStartTime(deliverySlotInfoData.getDeliveryStartTime());
		deliverySlotInfoModel.setDeliveryEndTime(deliverySlotInfoData.getDeliveryEndTime());

		getDeliverySlotService().saveDeliverySlotInfo(deliverySlotInfoModel, cartModel);
	}

	/**
	 * @return deliverySlotService
	 */
	public DeliverySlotService getDeliverySlotService()
	{
		return deliverySlotService;
	}

	/**
	 * @param deliverySlotService
	 */
	public void setDeliverySlotService(final DeliverySlotService deliverySlotService)
	{
		this.deliverySlotService = deliverySlotService;
	}

	/**
	 * @return deliverySlotDayConfigConverter
	 */
	public Converter<DeliverySlotDayConfigModel, DeliverySlotDayConfigData> getDeliverySlotDayConfigConverter()
	{
		return deliverySlotDayConfigConverter;
	}


	/**
	 * @param deliverySlotDayConfigConverter
	 */
	public void setDeliverySlotDayConfigConverter(
			final Converter<DeliverySlotDayConfigModel, DeliverySlotDayConfigData> deliverySlotDayConfigConverter)
	{
		this.deliverySlotDayConfigConverter = deliverySlotDayConfigConverter;
	}

	/**
	 * @return deliverySlotTimeConfigConverter
	 */
	public Converter<DeliverySlotTimeConfigModel, DeliverySlotTimeConfigData> getDeliverySlotTimeConfigConverter()
	{
		return deliverySlotTimeConfigConverter;
	}

	/**
	 * @param deliverySlotTimeConfigConverter
	 */
	public void setDeliverySlotTimeConfigConverter(
			final Converter<DeliverySlotTimeConfigModel, DeliverySlotTimeConfigData> deliverySlotTimeConfigConverter)
	{
		this.deliverySlotTimeConfigConverter = deliverySlotTimeConfigConverter;
	}

	/**
	 * @return the cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}


	/**
	 * @param cartService
	 *           the cartService to set
	 */
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}


	/**
	 * @return the deliverySlotInfoConverter
	 */
	public Converter<DeliverySlotInfoModel, DeliverySlotInfoData> getDeliverySlotInfoConverter()
	{
		return deliverySlotInfoConverter;
	}


	/**
	 * @param deliverySlotInfoConverter
	 *           the deliverySlotInfoConverter to set
	 */
	public void setDeliverySlotInfoConverter(
			final Converter<DeliverySlotInfoModel, DeliverySlotInfoData> deliverySlotInfoConverter)
	{
		this.deliverySlotInfoConverter = deliverySlotInfoConverter;
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
}
