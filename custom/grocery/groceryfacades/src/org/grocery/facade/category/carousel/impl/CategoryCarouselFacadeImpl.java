/**
 *
 */
package org.grocery.facade.category.carousel.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import org.grocery.facade.category.carousel.CategoryCarouselFacade;


/**
 * @author abhishekkumar05
 *
 *  Facade to fetch list of categorys for a given category carousel component.
 */
public class CategoryCarouselFacadeImpl implements CategoryCarouselFacade
{

	private Converter<CategoryModel, CategoryData> categoryConverter;

	@Override
	public List<CategoryData> getCategories(final List<CategoryModel> categoriesModel)
	{
		final List<CategoryData> categoriesData = new ArrayList<CategoryData>();

		for (final CategoryModel category : categoriesModel)
		{
			categoriesData.add(getCategoryConverter().convert(category));
		}

		return categoriesData;
	}

	public Converter<CategoryModel, CategoryData> getCategoryConverter()
	{
		return categoryConverter;
	}

	public void setCategoryConverter(final Converter<CategoryModel, CategoryData> categoryConverter)
	{
		this.categoryConverter = categoryConverter;
	}
}
