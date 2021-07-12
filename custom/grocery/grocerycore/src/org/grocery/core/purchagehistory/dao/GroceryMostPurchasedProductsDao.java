/**
 *
 */
package org.grocery.core.purchagehistory.dao;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 * @author pappusharma
 *
 */
public interface GroceryMostPurchasedProductsDao
{
	List<ProductModel> getMostPuchasedProduct(int rowCout);

	public List<ProductModel> getBestSellerProducts(final Integer numberOfOrders, final Integer numberOfDays);
}
