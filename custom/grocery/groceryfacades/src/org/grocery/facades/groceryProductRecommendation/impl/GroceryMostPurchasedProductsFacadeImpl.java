/**
 *
 */
package org.grocery.facades.groceryProductRecommendation.impl;

import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.impl.DefaultProductVariantFacade;
import de.hybris.platform.converters.ConfigurablePopulator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.grocery.core.purchagehistory.service.GroceryMostPurchasedProductsService;
import org.grocery.facades.groceryProductRecommendation.GroceryMostPurchasedProductsFacade;


/**
 * @author pappusharma
 *
 */
public class GroceryMostPurchasedProductsFacadeImpl extends DefaultProductVariantFacade
		implements GroceryMostPurchasedProductsFacade
{

	private Converter<ProductModel, ProductData> productConverter;
	private GroceryMostPurchasedProductsService groceryMostPurchasedProductsService;
	private ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator;

	protected static final List<ProductOption> PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE);

	@Override
	public List getMostPurchasedProducts(final int count)
	{
		final List<ProductModel> mostPurchedProducts = groceryMostPurchasedProductsService.getMostPuchasedProduct(count);

		return getProductConverter().convertAll(mostPurchedProducts);
	}

	@Override
	public List getBestSellerProducts(final Integer numberOfOrders, final Integer numberOfDays)
	{
		final List<ProductModel> mostPurchedProducts = getGroceryMostPurchasedProductsService()
				.getBestSellerProducts(numberOfOrders,
				numberOfDays);

		final List<ProductData> products = new ArrayList<>();

		for (final ProductModel productModel : mostPurchedProducts)
		{
			products.add(getProductForOption(productModel, PRODUCT_OPTIONS));
		}
		return products;
	}

	protected ProductData getProductForOption(final ProductModel productModel, final Collection<ProductOption> options)
	{
		final ProductData productData = getProductConverter().convert(productModel);

		if (options != null)
		{
			getProductConfiguredPopulator().populate(productModel, productData, options);
		}
		return productData;
	}


	@Override
	public ConfigurablePopulator<ProductModel, ProductData, ProductOption> getProductConfiguredPopulator()
	{
		return productConfiguredPopulator;
	}

	@Override
	public void setProductConfiguredPopulator(
			final ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator)
	{
		this.productConfiguredPopulator = productConfiguredPopulator;
	}

	@Override
	public Converter<ProductModel, ProductData> getProductConverter()
	{
		return productConverter;
	}

	@Override
	public void setProductConverter(final Converter<ProductModel, ProductData> productConverter)
	{
		this.productConverter = productConverter;
	}

	public GroceryMostPurchasedProductsService getGroceryMostPurchasedProductsService()
	{
		return groceryMostPurchasedProductsService;
	}

	public void setGroceryMostPurchasedProductsService(
			final GroceryMostPurchasedProductsService groceryMostPurchasedProductsService)
	{
		this.groceryMostPurchasedProductsService = groceryMostPurchasedProductsService;
	}

}
