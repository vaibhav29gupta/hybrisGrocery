/**
 *
 */
package org.grocery.storefront.controllers.cms;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.grocery.core.model.RecipeModel;
import org.grocery.core.model.RecipeThemeComponentModel;
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
@Controller("RecipeThemeComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.RecipeThemeComponent)
public class RecipeThemeComponentController extends
AbstractAcceleratorCMSComponentController<RecipeThemeComponentModel>
{
	private static final Logger LOG = Logger.getLogger(RecipeThemeComponentController.class);

	@Resource(name = "recipeFacade")
	private RecipeFacade recipeFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final RecipeThemeComponentModel component)
	{

		final String headline = component.getHeadline();
		final String description = component.getDescription();
		final String linkName = component.getLinkName();
		final String linkUrl = component.getLinkURL();

		model.addAttribute("headline", headline);
		model.addAttribute("description", description);
		model.addAttribute("linkName", linkName);
		model.addAttribute("linkUrl", linkUrl);

		final List<String> recipeCodesList = new ArrayList<String>();

		final List<RecipeModel> recipeSet = component.getRecipes();

		for (final RecipeModel recipe : recipeSet)
		{
			recipeCodesList.add(recipe.getCode());
		}

		final List<RecipeData> recipeList = recipeFacade.getRecipeListByCodes(recipeCodesList);
		model.addAttribute("recipeList", recipeList);
	}
}
