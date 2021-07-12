/**
 *
 */
package org.grocery.storefront.controllers.cms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.SaveCartForm;
import de.hybris.platform.acceleratorfacades.order.AcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.SaveCartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.grocery.core.model.ShipmentMethodComponentModel;
import org.grocery.storefront.controllers.ControllerConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("ShipmentMethodComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.ShipmentMethodComponent)
public class ShipmentMethodComponentController extends AbstractAcceleratorCMSComponentController<ShipmentMethodComponentModel>
{

	private static final Logger LOG = Logger.getLogger(ShipmentMethodComponentController.class);


	@Resource(name = "acceleratorCheckoutFacade")
	private AcceleratorCheckoutFacade checkoutFacade;

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@Resource(name = "saveCartFacade")
	private SaveCartFacade saveCartFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final ShipmentMethodComponentModel component)
	{

		final CartData cartData = cartFacade.getSessionCart();
		model.addAttribute("savedCartCount", saveCartFacade.getSavedCartsCountForCurrentUser());
		if (!model.containsAttribute("saveCartForm"))
		{
			model.addAttribute("saveCartForm", new SaveCartForm());
		}
		model.addAttribute("cartData", cartData);
		model.addAttribute("emptyCartImage", component.getEmptyCartImage());
		model.addAttribute("expressCheckoutAllowed", Boolean.valueOf(checkoutFacade.isExpressCheckoutAllowedForCart()));
	}

}

