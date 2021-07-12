package org.grocery.core.recipe.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.grocery.core.model.RecipeModel;
import org.grocery.core.model.RecipesCategoryModel;
import org.grocery.core.recipe.dao.RecipeDao;
import org.grocery.core.recipe.service.RecipeService;


/**
 * @author kirtimittal
 *
 */
public class RecipeServiceImpl implements RecipeService
{
	private RecipeDao defaultRecipeDao;

	@Override
	public RecipeModel getRecipeByCode(final String code)
	{
		RecipeModel recipe = null;

		final List<RecipeModel> retrieveRecipesByCode = getDefaultRecipeDao().fetchRecipeByCode(code);

		if (CollectionUtils.isNotEmpty(retrieveRecipesByCode))
		{
			recipe = retrieveRecipesByCode.get(0);
		}

		return recipe;
	}

	@Override
	public RecipesCategoryModel retrieveRecipesCategoryByCode(final String code) throws NullPointerException
	{
		final List<RecipesCategoryModel> recipesCategoryByCode = getDefaultRecipeDao().fetchRecipesCategoryByCode(code);

		RecipesCategoryModel recipesCategory = null;

		if (CollectionUtils.isNotEmpty(recipesCategoryByCode))
		{
			recipesCategory = recipesCategoryByCode.get(0);
		}

		return recipesCategory;
	}

	@Override
	public List<RecipeModel> getRecipeListByCodes(final List<String> codes)
	{
		final List<RecipeModel> recipeList = getDefaultRecipeDao().fetchRecipeListByCodes(codes);

		if (CollectionUtils.isNotEmpty(recipeList))
		{
			return recipeList;
		}
		return null;
	}

	public RecipeDao getDefaultRecipeDao()
	{
		return defaultRecipeDao;
	}

	public void setDefaultRecipeDao(final RecipeDao defaultRecipeDao)
	{
		this.defaultRecipeDao = defaultRecipeDao;
	}

}
