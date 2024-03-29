/**
 * 
 */
package org.grocery.storefront.breadcrumb;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.history.BrowseHistory;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.helper.ProductAndCategoryHelper;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author arshdeepsingh
 *
 */
public class GroceryProductBreadcrumbBuilder
{

	private static final String LAST_LINK_CLASS = "active";

	private UrlResolver<ProductModel> productModelUrlResolver;
	private UrlResolver<CategoryModel> categoryModelUrlResolver;
	private BrowseHistory browseHistory;
	private ProductService productService;
	private ProductAndCategoryHelper productAndCategoryHelper;

	/**
	 * Returns a list of breadcrumbs for the given product.
	 *
	 * @param productCode
	 * @return breadcrumbs for the given product
	 */
	public List<Breadcrumb> getBreadcrumbs(final String productCode)
	{
		final ProductModel productModel = getProductService().getProductForCode(productCode);
		final List<Breadcrumb> breadcrumbs = new ArrayList<>();

		final Collection<CategoryModel> categoryModels = new ArrayList<>();
		final Breadcrumb last;

		final ProductModel baseProductModel = getProductAndCategoryHelper().getBaseProduct(productModel);
		last = getProductBreadcrumb(baseProductModel);
		categoryModels.addAll(baseProductModel.getSupercategories());
		last.setLinkClass(LAST_LINK_CLASS);

		breadcrumbs.add(last);

		while (!categoryModels.isEmpty())
		{
			CategoryModel toDisplay = null;
			toDisplay = processCategoryModels(categoryModels, toDisplay);
			categoryModels.clear();
			if (toDisplay != null)
			{
				breadcrumbs.add(getCategoryBreadcrumb(toDisplay));
				categoryModels.addAll(toDisplay.getSupercategories());
			}
		}
		Collections.reverse(breadcrumbs);
		return breadcrumbs;
	}

	protected CategoryModel processCategoryModels(final Collection<CategoryModel> categoryModels, final CategoryModel toDisplay)
	{
		CategoryModel categoryToDisplay = toDisplay;

		for (final CategoryModel categoryModel : categoryModels)
		{
			if (getProductAndCategoryHelper().isValidProductCategory(categoryModel))
			{
				if (categoryToDisplay == null && ! categoryModel.isIsRootCategory())
				{
					categoryToDisplay = categoryModel;
				}
				if (getBrowseHistory().findEntryMatchUrlEndsWith(categoryModel.getCode()) != null)
				{
					break;
				}
			}
		}
		return categoryToDisplay;
	}

	/**
	 * @deprecated As of 1905 - use {@link ProductAndCategoryHelper#getBaseProduct(ProductModel)} instead
	 */
	@Deprecated
	protected ProductModel getBaseProduct(final ProductModel product)
	{
		if (product instanceof VariantProductModel)
		{
			return getBaseProduct(((VariantProductModel) product).getBaseProduct());
		}
		return product;
	}

	protected Breadcrumb getProductBreadcrumb(final ProductModel product)
	{
		final String productUrl = getProductModelUrlResolver().resolve(product);
		return new Breadcrumb(productUrl, product.getName(), null);
	}

	protected Breadcrumb getCategoryBreadcrumb(final CategoryModel category)
	{
		final String categoryUrl = getCategoryModelUrlResolver().resolve(category);
		return new Breadcrumb(categoryUrl, category.getName(), null, category.getCode());
	}

	protected UrlResolver<ProductModel> getProductModelUrlResolver()
	{
		return productModelUrlResolver;
	}

	@Required
	public void setProductModelUrlResolver(final UrlResolver<ProductModel> productModelUrlResolver)
	{
		this.productModelUrlResolver = productModelUrlResolver;
	}

	protected UrlResolver<CategoryModel> getCategoryModelUrlResolver()
	{
		return categoryModelUrlResolver;
	}

	@Required
	public void setCategoryModelUrlResolver(final UrlResolver<CategoryModel> categoryModelUrlResolver)
	{
		this.categoryModelUrlResolver = categoryModelUrlResolver;
	}

	protected BrowseHistory getBrowseHistory()
	{
		return browseHistory;
	}

	@Required
	public void setBrowseHistory(final BrowseHistory browseHistory)
	{
		this.browseHistory = browseHistory;
	}

	protected ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	protected ProductAndCategoryHelper getProductAndCategoryHelper()
	{
		return productAndCategoryHelper;
	}

	@Required
	public void setProductAndCategoryHelper(final ProductAndCategoryHelper productAndCategoryHelper)
	{
		this.productAndCategoryHelper = productAndCategoryHelper;
	}



}
