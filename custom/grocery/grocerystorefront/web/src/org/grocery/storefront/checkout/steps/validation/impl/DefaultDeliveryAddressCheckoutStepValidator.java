/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.storefront.checkout.steps.validation.impl;

import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.validation.AbstractCheckoutStepValidator;
import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.validation.ValidationResults;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.order.CartService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.grocery.core.strategies.impl.GroceryCustomCartValidationStrategy;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


public class DefaultDeliveryAddressCheckoutStepValidator extends AbstractCheckoutStepValidator
{
	private static final Logger LOGGER = Logger.getLogger(DefaultDeliveryAddressCheckoutStepValidator.class);

	private static final String CART_CHECKOUT_ERROR = "cart.checkout.error";

	@Resource(name = "groceryCustomCartValidationStrategy")
	private GroceryCustomCartValidationStrategy groceryCustomCartValidationStrategy;

	@Resource(name = "cartService")
	private CartService cartService;

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@Override
	public ValidationResults validateOnEnter(final RedirectAttributes redirectAttributes)
	{
		final boolean result = validateCart(redirectAttributes);

		if (result == true)
		{
			return ValidationResults.REDIRECT_TO_DUMMY_STEP;
		}

		if (!getCheckoutFlowFacade().hasValidCart())
		{
			return ValidationResults.REDIRECT_TO_CART;
		}


		/*
		 * if (!getCheckoutFacade().hasShippingItems()) { return ValidationResults.REDIRECT_TO_PICKUP_LOCATION; }
		 */

		return ValidationResults.SUCCESS;
	}

	protected boolean validateCart(final RedirectAttributes redirectModel)
	{
		//Validate the cart
		List<CartModificationData> modifications = new ArrayList<>();
		try
		{
			modifications = cartFacade.validateCartData();
		}
		catch (final CommerceCartModificationException e)
		{
			LOGGER.error("Failed to validate cart", e);
		}
		if (!modifications.isEmpty())
		{
			redirectModel.addFlashAttribute("validationData", modifications);
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, CART_CHECKOUT_ERROR, null);
			// Invalid cart. Bounce back to the cart page.
			return true;
		}
		return false;
	}
}
