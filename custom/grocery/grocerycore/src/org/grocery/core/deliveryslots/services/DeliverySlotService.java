package org.grocery.core.deliveryslots.services;

import de.hybris.platform.core.model.order.CartModel;

import java.util.Date;

import org.grocery.core.model.DeliverySlotInfoModel;
import org.grocery.core.model.DeliverySlotTimeConfigModel;


/**
 * Service for defining methods for Delivery Slots related information
 */
public interface DeliverySlotService
{
	/**
	 * API to fetch Delivery Time list for a given code
	 *
	 * @param code
	 * @return DeliverySlotTimeConfigModel
	 */

	DeliverySlotTimeConfigModel getDeliverySlotTimesForCode(String code);

	/**
	 * Update or create new Delivery Slot.
	 *
	 * @param cart
	 * @param startDate
	 * @param endDate
	 * @param deliveryTime
	 */
	public void updateDeliverySlot(CartModel cart, Date startDate, Date endDate, Date deliveryTime);

	/**
	 * Save delivery slot to cart
	 *
	 * @param deliverySlotInfoModel
	 * @param cartModel
	 */
	void saveDeliverySlotInfo(DeliverySlotInfoModel deliverySlotInfoModel, CartModel cartModel);
}
