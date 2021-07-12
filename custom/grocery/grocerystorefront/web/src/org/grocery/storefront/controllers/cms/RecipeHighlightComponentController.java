/**
 *
 */
package org.grocery.storefront.controllers.cms;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.grocery.core.model.RecipeHighlightComponentModel;
import org.grocery.core.model.RecipeModel;
import org.grocery.facades.recipe.RecipeFacade;
import org.grocery.facades.recipe.data.RecipeData;
import org.grocery.storefront.controllers.ControllerConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author divyanshusingh
 *
 */
@Controller("RecipeHighlightComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.RecipeHighlightComponent)
public class RecipeHighlightComponentController  extends
AbstractAcceleratorCMSComponentController<RecipeHighlightComponentModel>
{

	private static final Logger LOG = Logger.getLogger(RecipeHighlightComponentController.class);

	@Resource(name = "recipeFacade")
	private RecipeFacade recipeFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final RecipeHighlightComponentModel component)
	{

		final RecipeModel highlightRecipe = component.getHighlightRecipe();

		model.addAttribute("highlightRecipe", highlightRecipe);

		final List<String> recipeCodesList = new ArrayList<String>();
		final List<RecipeModel> recipeModelList = component.getRecipes();

		for (final RecipeModel recipe : recipeModelList)
		{
			recipeCodesList.add(recipe.getCode());
		}

		final List<RecipeData> recipeList = recipeFacade.getRecipeListByCodes(recipeCodesList);
		model.addAttribute("recipeList", recipeList);
	}
}
