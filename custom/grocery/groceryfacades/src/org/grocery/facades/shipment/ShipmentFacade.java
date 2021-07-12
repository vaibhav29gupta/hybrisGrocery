/**
 *
 */
package org.grocery.facades.shipment;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.serviceability.data.ServiceabilityAreaData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;

import java.util.List;


public interface ShipmentFacade
{

	Boolean setPostalCode(final long postalCode);

	Boolean setStoreID(final String storeID);

	List<ProductData> getUnAvailableProductsForPostCode(final String postalCode);

	List<ProductData> getUnAvailableProductsForStore(final String storeID);

	List<ProductData> getAffectedCartProducts(final String locationText, final String shipmentType);

	Boolean checkValidity(final String locationText, final String shipmentType);

	String getCurrentPostalCode();

	String getCurrentStoreID();

	String getCurrentStoreName();

	String getCurrentShipmentMethod();

	Boolean isValidStoreID(String storeID);

	Boolean updatePOS(final String storeName) throws CommerceCartModificationException;

	/**
	 * @param postalCode
	 * @return
	 */
	Boolean isValidPostalCode(String postalCode);

	/**
	 * @param postalCode
	 * @return
	 */
	ServiceabilityAreaData getServiceabilityAreaForPostalCode(long postalCode);

	Boolean validateShipmentSelection(final String shipmentType, final String postalCode, final String pos);

	void setShipmentTypeInSession(final String shipmentType, final String postalCode, final String pos);

	void setShipmentInCustomerProfile(final String shipmentType, final String postalCode,
			final String pos);

	String getStoreName(final String storeId);

}
