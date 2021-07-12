/**
 *
 */
package org.grocery.facade.category.carousel;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;

import java.util.List;


/**
 * @author abhishekkumar05
 *
 */
public interface CategoryCarouselFacade
{

	List<CategoryData> getCategories(List<CategoryModel> categorysModel);
}
