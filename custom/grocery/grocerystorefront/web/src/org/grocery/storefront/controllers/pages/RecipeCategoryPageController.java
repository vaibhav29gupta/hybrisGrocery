/**
 *
 */
package org.grocery.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.grocery.facades.recipe.RecipeFacade;
import org.grocery.facades.recipe.data.RecipesCategoryData;
import org.grocery.storefront.controllers.AbstractGroceryPageController;
import org.grocery.storefront.controllers.ControllerConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author abhishekkumar05
 *
 */
@Controller
@RequestMapping(value = "/**/rc")
public class RecipeCategoryPageController extends AbstractGroceryPageController
{
	private static final String RECIPE_CATEGORY_DATA = "recipeCategoryEntries";

	private static final String RECIPE_CATEGORY_CODE_PATH_VARIABLE_PATTERN = "/{recipeCategoryCode:.*}";

	private static final String RECIPE_CATEGORY_CMS_PAGE = "recipesCategory";

	private static final Logger LOG = Logger.getLogger(RecipeCategoryPageController.class);

	@Resource(name = "simpleBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder resourceBreadcrumbBuilder;

	@Resource(name = "recipeFacade")
	private RecipeFacade recipeFacade;

	@RequestMapping(value = RECIPE_CATEGORY_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String recipeCategoryDetail(@PathVariable("recipeCategoryCode")
	final String rcCode, final Model model) throws CMSItemNotFoundException
	{

		final RecipesCategoryData rcData = recipeFacade.getRecipesCategoryByCode(rcCode);
		model.addAttribute(RECIPE_CATEGORY_DATA, rcData);
		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
				resourceBreadcrumbBuilder.getBreadcrumbs("breadcrumb.recipecategory.page"));

		final ContentPageModel rcPage = getContentPageForLabelOrId(RECIPE_CATEGORY_CMS_PAGE);
		storeCmsPageInModel(model, rcPage);
		setUpMetaDataForContentPage(model, rcPage);


		return ControllerConstants.Views.Pages.Recipe.RecipeCategoryPage;
	}
}
