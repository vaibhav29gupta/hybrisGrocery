/**
 *
 */
package org.grocery.core.shipment;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 * @author ankituniyal
 *
 */
public interface ShipmentService
{

	/**
	 * @param storeName
	 * @return
	 * @throws CommerceCartModificationException
	 */
	List<CommerceCartModification> updateEntriesForPOS(final String storeName) throws CommerceCartModificationException;

	/**
	 * @return
	 */
	List<ProductModel> getUnavailableProducts(final String storeName);

	/**
	 * @return
	 */
	Boolean isCartEmpty();
}
