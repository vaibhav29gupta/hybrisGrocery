package org.grocery.storefront.controllers.cms;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.grocery.core.model.RecipeCategoryComponentModel;
import org.grocery.core.model.RecipesCategoryModel;
import org.grocery.facades.recipe.RecipeFacade;
import org.grocery.facades.recipe.data.RecipesCategoryData;
import org.grocery.storefront.controllers.ControllerConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author kirtimittal
 *
 */

@Controller("RecipeCategoryComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.RecipeCategoryComponent)
public class RecipeCategoryComponentController extends AbstractAcceleratorCMSComponentController<RecipeCategoryComponentModel>
{
	@Resource(name = "recipeFacade")
	private RecipeFacade recipeFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final RecipeCategoryComponentModel component)
	{
		final List<RecipesCategoryData> recipesCategory = new ArrayList<>();
		recipesCategory.addAll(collectRecipes(component.getCategories()));
		model.addAttribute("recipesCategory", recipesCategory);
	}

	protected List<RecipesCategoryData> collectRecipes(final List<RecipesCategoryModel> recipesCategoryModel)
	{
		return recipeFacade.collectRecipesCategory(recipesCategoryModel);
	}



}
