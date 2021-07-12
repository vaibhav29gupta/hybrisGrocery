package org.grocery.core.recipe.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.grocery.core.model.RecipeModel;
import org.grocery.core.model.RecipesCategoryModel;
import org.grocery.core.recipe.dao.RecipeDao;


/**
 * @author kirtimittal
 *
 */
/**
 * @author abhishekkumar05
 *
 */
public class RecipeDaoImpl implements RecipeDao
{
	private FlexibleSearchService flexibleSearchService;

	private static final String RECIPE_DESCRIPTION = "SELECT {r:pk} FROM {Recipe AS r} " + "WHERE {r:code} LIKE ?code";

	@Override
	public List<RecipeModel> fetchRecipeByCode(final String code)
	{
		final Map<String, Object> params = new HashMap<>(1);
		params.put("code", code);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(RECIPE_DESCRIPTION, params);
		final SearchResult<RecipeModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}


	@Override
	public List<RecipesCategoryModel> fetchRecipesCategoryByCode(final String code)
	{
		final StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT {").append(RecipesCategoryModel.PK);
		queryBuilder.append("} FROM {").append(RecipesCategoryModel._TYPECODE);
		queryBuilder.append("} WHERE {").append(RecipesCategoryModel.CODE);
		queryBuilder.append("} = ?").append(RecipesCategoryModel.CODE);

		final Map<String, String> queryParams = new HashMap<>(1);

		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(queryBuilder.toString());
		queryParams.put(RecipesCategoryModel.CODE, code);
		fQuery.addQueryParameters(queryParams);


		final SearchResult<RecipesCategoryModel> recipesCategory = getFlexibleSearchService().search(fQuery);

		return recipesCategory.getResult();
	}

	@Override
	public List<RecipeModel> fetchRecipeListByCodes(final List<String> codes)
	{

		final StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT {").append(RecipeModel.PK);
		queryBuilder.append("} FROM {").append(RecipeModel._TYPECODE);
		queryBuilder.append("} WHERE {").append(RecipeModel.CODE);
		queryBuilder.append("} in (");

		if (CollectionUtils.isNotEmpty(codes))
		{
			String codeString = "";

			for (final String code : codes)
			{
				codeString = codeString + "'" + code + "'" + ",";
			}

			codeString = codeString.substring(0, codeString.length() - 1) + ")";
			queryBuilder.append(codeString);

			final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(queryBuilder.toString());
			final SearchResult<RecipeModel> recipes = getFlexibleSearchService().search(fQuery);

			return recipes.getResult();
		}
		return (List<RecipeModel>) CollectionUtils.EMPTY_COLLECTION;
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
