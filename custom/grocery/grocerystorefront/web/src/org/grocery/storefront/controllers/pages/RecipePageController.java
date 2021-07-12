package org.grocery.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.grocery.facades.recipe.RecipeFacade;
import org.grocery.facades.recipe.data.RecipeData;
import org.grocery.storefront.controllers.AbstractGroceryPageController;
import org.grocery.storefront.controllers.ControllerConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author kirtimittal
 *
 */
@Controller
@RequestMapping(value = "/**/r")
public class RecipePageController extends AbstractGroceryPageController
{
	private static final String RECIPE_CODE_PATH_VARIABLE_PATTERN = "/{recipeCode:.*}";

	private static final String RECIPE_CMS_PAGE = "recipe";

	@Resource(name = "simpleBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder resourceBreadcrumbBuilder;

	@Resource(name = "recipeFacade")
	private RecipeFacade recipeFacade;

	@RequestMapping(value = RECIPE_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String recipeDetail(@PathVariable("recipeCode")
	final String encodedRecipeCode, final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws CMSItemNotFoundException, UnsupportedEncodingException
	{
		final String recipeCode = decodeWithScheme(encodedRecipeCode, UTF_8);
		final RecipeData recipeData = recipeFacade.getRecipeByCode(recipeCode);

		model.addAttribute("recipeData", recipeData);
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, resourceBreadcrumbBuilder.getBreadcrumbs("breadcrumb.recipe.page"));

		final ContentPageModel rcPage = getContentPageForLabelOrId(RECIPE_CMS_PAGE);
		storeCmsPageInModel(model, rcPage);
		setUpMetaDataForContentPage(model, rcPage);

		return ControllerConstants.Views.Pages.Recipe.RecipePage;
	}
}
