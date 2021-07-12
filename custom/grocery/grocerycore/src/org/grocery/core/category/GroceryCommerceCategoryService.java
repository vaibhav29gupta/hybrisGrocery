/**
 * 
 */
package org.grocery.core.category;

import de.hybris.platform.category.model.CategoryModel;

import java.util.Collection;
import java.util.List;

/**
 * @author arshdeepsingh
 *
 */
public interface GroceryCommerceCategoryService
{

	Collection<List<CategoryModel>> getPathsForCategory(CategoryModel category);
}
