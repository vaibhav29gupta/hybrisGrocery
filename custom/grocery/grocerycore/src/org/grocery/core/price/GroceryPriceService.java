/**
 *
 */
package org.grocery.core.price;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.PriceInformation;


/**
 * @author bhavya2486
 *
 */
public interface GroceryPriceService
{
	PriceInformation getFromDiscountedPriceForProduct(ProductModel product, double price);

	PriceInformation getWebDiscountedPriceForProduct(ProductModel product, double price);
}
