/**
 *
 */
package org.grocery.core.purchagehistory.service;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 * @author pappusharma
 *
 */
public interface GroceryMostPurchasedProductsService
{

	List<ProductModel> getMostPuchasedProduct(int count);

	List<ProductModel> getBestSellerProducts(Integer numberOfOrders, Integer numberOfDays);
}
