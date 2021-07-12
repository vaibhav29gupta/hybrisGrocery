package org.grocery.core.deliveryslots.daos;

import java.util.List;

import org.grocery.core.model.DeliverySlotDayConfigModel;
import org.grocery.core.model.DeliverySlotTimeConfigModel;


/**
 * Interface to define methods for Delivery Slot Day DAO methods
 */
public interface DeliverySlotDao
{
	/**
	 * @param monthYear
	 * @return List<DeliverySlotDayConfigModel>
	 *
	 *         This method returns the list of DeliverySlotDays for month & year
	 */
	List<DeliverySlotDayConfigModel> getDeliverySlotDays(final String monthYear);

	/**
	 * API to fetch the Delivery slot time for a given code
	 *
	 * @param code
	 * @return DeliverySlotTimeConfigModel
	 *
	 */
	DeliverySlotTimeConfigModel findDeliverySlotTimesForCode(final String code);
}
