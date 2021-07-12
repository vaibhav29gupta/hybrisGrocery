/**
 * 
 */
package org.grocery.core.category;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.classification.ClassificationSystemVersionModel;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.category.impl.DefaultCommerceCategoryService;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

/**
 * @author arshdeepsingh
 *
 */
public class GroceryCommerceCategoryServiceImpl extends DefaultCommerceCategoryService   implements GroceryCommerceCategoryService 
{
	private static final Logger LOG = Logger.getLogger(GroceryCommerceCategoryServiceImpl.class);
	private CategoryService categoryService;
	private CatalogVersionService catalogVersionService;
	
	
	
	/**
	 * @return the categoryService
	 */
	public CategoryService getCategoryService()
	{
		return categoryService;
	}

	/**
	 * @param categoryService the categoryService to set
	 */
	public void setCategoryService(CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}

	/**
	 * @return the catalogVersionService
	 */
	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	/**
	 * @param catalogVersionService the catalogVersionService to set
	 */
	public void setCatalogVersionService(CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	@Override
	public Collection<List<CategoryModel>> getPathsForCategory(final CategoryModel category)
	{
		validateParameterNotNull(category, "Parameter 'category' was null.");
		return getPathsInternal(category, new HashSet<CategoryModel>(Collections.singleton(category)));
	}
	
	protected Collection<List<CategoryModel>> getPathsInternal(final CategoryModel category, final Set<CategoryModel> controlSet)
	{
		Collection<List<CategoryModel>> result = null;

		final Collection<CategoryModel> superCategories = category.getSupercategories();
		if (CollectionUtils.isNotEmpty(superCategories))
		{
			for (final CategoryModel parent : superCategories)
			{
				if (isSupportedCategory(parent) && !parent.isIsRootCategory())
				{
					if (notVisited(parent, controlSet))
					{
						if (result == null)
						{
							result = new LinkedList<List<CategoryModel>>();
						}
						visitSuperCategory(category, parent, controlSet, result);
						markNotVisited(parent, controlSet);
					}
					else
					{
						LOG.warn("path cycle found for category: [" + category.getCode() + "]");
					}
				}
			}
		}

		return result == null ? Collections.singletonList(Collections.singletonList(category)) : result;
	}

	protected void markNotVisited(final CategoryModel cat, final Set<CategoryModel> visitedCategories)
	{
		visitedCategories.remove(cat);
	}

	protected boolean notVisited(final CategoryModel cat, final Set<CategoryModel> visitedCategories)
	{
		return visitedCategories.add(cat);
	}

	protected void visitSuperCategory(final CategoryModel category, final CategoryModel parent, final Set<CategoryModel> controlSet,
	                                final Collection<List<CategoryModel>> result)
	{
		for (List<CategoryModel> parentPath : getPathsInternal(parent, controlSet))
		{
			if (!(parentPath instanceof LinkedList))
			{
				parentPath = new LinkedList<CategoryModel>(parentPath);
			}
			parentPath.add(category);
			result.add(parentPath);
		}
	}

	protected boolean isSupportedCategory(final CategoryModel categoryModel)
	{
		return (!(categoryModel.getCatalogVersion() instanceof ClassificationSystemVersionModel));
	}
}
