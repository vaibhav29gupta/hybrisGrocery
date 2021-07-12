package org.grocery.facades.recipe;

import java.util.List;

import org.grocery.core.model.RecipesCategoryModel;
import org.grocery.facades.recipe.data.RecipeData;
import org.grocery.facades.recipe.data.RecipesCategoryData;


/**
 * @author kirtimittal
 *
 */
public interface RecipeFacade
{
	public RecipeData getRecipeByCode(final String code);

	public RecipesCategoryData getRecipesCategoryByCode(final String code);

	public List<RecipesCategoryData> collectRecipesCategory(final List<RecipesCategoryModel> recipesCategoryModel);

	public List<RecipeData> getRecipeListByCodes(final List<String> codes);

	public List<String> addAllEntriesToCart(final RecipeData recipeData, final String storeId);
}
