/**
 *
 */
package org.grocery.storefront.controllers.cms;

import de.hybris.platform.acceleratorfacades.productcarousel.ProductCarouselFacade;
import de.hybris.platform.cms2lib.model.components.ProductCarouselComponentModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.grocery.core.model.BestSellersProductsComponentModel;
import org.grocery.facades.groceryProductRecommendation.GroceryMostPurchasedProductsFacade;
import org.grocery.storefront.controllers.ControllerConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author amitsharma09
 *
 */
@Controller("BestSellersProductsComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.BestSellersProductsComponent)
public class BestSellersProductsComponentController extends AbstractAcceleratorCMSComponentController<BestSellersProductsComponentModel>
{

	@Resource(name = "groceryMostPurchasedproductsFacade")
	private GroceryMostPurchasedProductsFacade groceryMostPurchasedproductsFacade;

	@Resource(name = "configurationService")
	private ConfigurationService configurationService;

	@Resource(name = "productCarouselFacade")
	private ProductCarouselFacade productCarouselFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final BestSellersProductsComponentModel component)
	{

		Integer numberOfOrders = component.getNumberOfOrders();
		Integer numberOfDays = component.getNumberOfDays();

		if (numberOfOrders == null)
		{
			/* Fetch default number of orders */
			numberOfOrders = configurationService.getConfiguration().getInt("grocery.default.bestSeller.numberOfOrders", 10);
		}
		if (numberOfDays == null)
		{
			/* Fetch default number of days */
			numberOfDays = configurationService.getConfiguration().getInt("grocery.default.bestSeller.numberOfDays", 10);
		}

		final List<ProductData> bestSellerProducts = groceryMostPurchasedproductsFacade.getBestSellerProducts(numberOfOrders,
				numberOfDays);

		if (bestSellerProducts.size() < 5)
		{
			bestSellerProducts.addAll(collectLinkedProducts(component));
		}
		model.addAttribute("title", component.getTitle());
		model.addAttribute("bestSellerProducts", bestSellerProducts);

		boolean isCarouselContainsServiceableProducts = Boolean.FALSE;
		for (final ProductData product : bestSellerProducts)
		{
			if (product.getIsServiceable())
			{
				isCarouselContainsServiceableProducts = Boolean.TRUE;
				break;
			}
		}
		model.addAttribute("isCarouselContainsServiceableProducts", isCarouselContainsServiceableProducts);
	}

	protected List<ProductData> collectLinkedProducts(final ProductCarouselComponentModel component)
	{
		return productCarouselFacade.collectProducts(component);
	}
}
