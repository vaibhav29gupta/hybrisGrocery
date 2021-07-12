/**
 *
 */
package org.grocery.core.purchagehistory.service.impl;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

import org.grocery.core.purchagehistory.dao.GroceryMostPurchasedProductsDao;
import org.grocery.core.purchagehistory.service.GroceryMostPurchasedProductsService;


/**
 * @author pappusharma
 *
 */
public class GroceryMostPurchasedProductsServiceImpl implements GroceryMostPurchasedProductsService
{
	private GroceryMostPurchasedProductsDao groceryMostPurchasedProductsDao;

	@Override
	public List<ProductModel> getMostPuchasedProduct(final int count)
	{
		return groceryMostPurchasedProductsDao.getMostPuchasedProduct(count);
	}

	@Override
	public List<ProductModel> getBestSellerProducts(final Integer numberOfOrders, final Integer numberOfDays)
	{
		return getGroceryMostPurchasedProductsDao().getBestSellerProducts(numberOfOrders, numberOfDays);
	}

	public GroceryMostPurchasedProductsDao getGroceryMostPurchasedProductsDao()
	{
		return groceryMostPurchasedProductsDao;
	}

	public void setGroceryMostPurchasedProductsDao(final GroceryMostPurchasedProductsDao groceryMostPurchasedProductsDao)
	{
		this.groceryMostPurchasedProductsDao = groceryMostPurchasedProductsDao;
	}

}
