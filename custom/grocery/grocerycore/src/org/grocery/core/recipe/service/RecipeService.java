package org.grocery.core.recipe.service;

import java.util.List;

import org.grocery.core.model.RecipeModel;
import org.grocery.core.model.RecipesCategoryModel;


/**
 * @author kirtimittal
 *
 */
public interface RecipeService
{
	public RecipeModel getRecipeByCode(final String code);

	public RecipesCategoryModel retrieveRecipesCategoryByCode(final String code) throws NullPointerException;

	public List<RecipeModel> getRecipeListByCodes(final List<String> codes);
}
