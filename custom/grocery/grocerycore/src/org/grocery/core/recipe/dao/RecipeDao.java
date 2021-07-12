package org.grocery.core.recipe.dao;

import java.util.List;

import org.grocery.core.model.RecipeModel;
import org.grocery.core.model.RecipesCategoryModel;


/**
 * @author kirtimittal
 *
 */
public interface RecipeDao
{
	public List<RecipeModel> fetchRecipeByCode(final String code);

	public List<RecipesCategoryModel> fetchRecipesCategoryByCode(final String code);

	public List<RecipeModel> fetchRecipeListByCodes(final List<String> codes);
}
