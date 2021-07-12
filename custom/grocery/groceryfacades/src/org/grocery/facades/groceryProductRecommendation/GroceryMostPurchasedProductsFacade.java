/**
 *
 */
package org.grocery.facades.groceryProductRecommendation;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 * @author pappusharma
 *
 */
public interface GroceryMostPurchasedProductsFacade
{
	/**
	 *
	 * @param user
	 * @param pageableData
	 */
	List<ProductModel> getMostPurchasedProducts(int count);

	List<ProductData> getBestSellerProducts(Integer numberOfOrders, Integer numberOfDays);

}
