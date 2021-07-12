/**
 *
 */
package org.grocery.storefront.controllers.cms;

import de.hybris.platform.acceleratorcms.model.components.ProductReferencesComponentModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.grocery.facades.groceryProductRecommendation.GroceryMostPurchasedProductsFacade;
import org.grocery.storefront.controllers.ControllerConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author pappusharma
 *
 */
@Controller("MostPurchasedProductsComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.MostPurchasedProductsComponent)
public class MostPurchasedProductsComponentController
		extends AbstractAcceleratorCMSComponentController<ProductReferencesComponentModel>
{
	@Resource(name = "groceryMostPurchasedproductsFacade")
	private GroceryMostPurchasedProductsFacade groceryMostPurchasedproductsFacade;


	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final ProductReferencesComponentModel component)
	{
		final List<ProductModel> mostPurchasedProduct = groceryMostPurchasedproductsFacade.getMostPurchasedProducts(10);
		model.addAttribute("mostPurchasedProduct", mostPurchasedProduct);
	}

}
