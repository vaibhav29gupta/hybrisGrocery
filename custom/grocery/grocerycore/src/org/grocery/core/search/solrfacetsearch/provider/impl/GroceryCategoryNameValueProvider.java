/**
 *
 */
package org.grocery.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.impl.CategoryNameValueProvider;

import java.util.Collection;
import java.util.Iterator;


/**
 * @author ankituniyal
 *
 */
public class GroceryCategoryNameValueProvider extends CategoryNameValueProvider
{
	private static final String ROOT = "root";

	@Override
	public void addValuesForCategory(final Collection<FieldValue> fieldValues, final IndexConfig indexConfig,
			final IndexedProperty indexedProperty, final CategoryModel category, final LanguageModel language)
	{
		if (category != null && !ROOT.equalsIgnoreCase(category.getCode()))
		{
			fieldValues.addAll(createFieldValue(category, language, indexedProperty));
			final Iterator<CategoryModel> superCategories = category.getAllSupercategories().iterator();

			while (superCategories.hasNext())
			{
				final CategoryModel superCategory = superCategories.next();
				if (superCategory != null && !ROOT.equalsIgnoreCase(superCategory.getCode()))
				{
					fieldValues.addAll(createFieldValue(superCategory, language, indexedProperty));
				}
			}
		}
	}
}
