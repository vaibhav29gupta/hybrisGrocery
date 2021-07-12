/**
 *
 */
package org.grocery.storefront.controllers.cms;

import de.hybris.platform.category.model.CategoryModel;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.grocery.core.model.CategoryCarouselComponentModel;
import org.grocery.facade.category.carousel.CategoryCarouselFacade;
import org.grocery.storefront.controllers.ControllerConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author abhishekkumar05
 *
 */
@Controller("CategoryCarouselComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.CategoryCarouselComponent)
public class CategoryCarouselComponentController extends AbstractAcceleratorCMSComponentController<CategoryCarouselComponentModel>
{

	@Resource(name = "categoryCarouselFacade")
	CategoryCarouselFacade categoryCarouselFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final CategoryCarouselComponentModel component)
	{
		final List<CategoryModel> categories = component.getCategories();
		//recipesCategory.addAll(collectRecipes(component.getCategories()));
		model.addAttribute("categoryCarousel", categoryCarouselFacade.getCategories(categories));
		model.addAttribute("title", component.getTitle());

	}

	/*
	 * protected List<CategoryData> collectCategories(final List<CategoryModel> categoryModel) { return
	 * categoryCarouselFacade.getCategorys(categoryModel); }
	 */

}
