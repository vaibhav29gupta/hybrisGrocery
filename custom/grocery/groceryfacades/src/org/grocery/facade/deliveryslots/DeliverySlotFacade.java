package org.grocery.facade.deliveryslots;

import de.hybris.platform.commercefacades.deliveryslot.data.DeliverySlotDayConfigData;
import de.hybris.platform.commercefacades.deliveryslot.data.DeliverySlotTimeConfigData;

import java.util.Date;
import java.util.List;


/**
 * Facade layer related to Delivery Slot on Checkout Address page
 */
public interface DeliverySlotFacade
{

	/**
	 * @param postalCode
	 * @param orderingDate
	 * @return
	 */
	List<DeliverySlotDayConfigData> fetchDeliverySlotDaysForStore(String postalCode, Date orderingDate);

	/**
	 * @param postalCode
	 * @param orderingDate
	 * @return
	 */
	List<DeliverySlotTimeConfigData> fetchDeliverySlotTimesForStore(String postalCode, Date orderingDate);

	/**
	 * @param postalCode
	 * @param processingDate
	 * @param deliveryDate
	 * @return boolean
	 */
	boolean validateDeliveryDate(String postalCode, Date processingDate, Date deliveryDate);

	/**
	 * @param postalCode
	 * @param processingDate
	 * @param deliveryTimeCode
	 * @return boolean
	 */
	boolean validateDeliveryZone(String postalCode, Date processingDate, String deliveryTimeCode);

	/**
	 * @param postalCode
	 * @return
	 */
	boolean validateDeliverySlotTimeForStore(String postalCode, String deliveryTimeCode);

	/**
	 * @param shippingDelDate
	 * @param shippingDelTime
	 * @return
	 */
	boolean updateDateAndTimeSlot(String shippingDelDate, String shippingDelTime);

}
