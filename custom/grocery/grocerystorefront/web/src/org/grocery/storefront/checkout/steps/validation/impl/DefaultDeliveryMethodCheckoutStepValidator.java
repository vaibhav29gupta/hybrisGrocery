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
import org.grocery.core.enums.ShipmentTypeEnum;
import org.grocery.facades.shipment.ShipmentFacade;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



public class DefaultDeliveryMethodCheckoutStepValidator extends AbstractCheckoutStepValidator
{
	private static final Logger LOGGER = Logger.getLogger(DefaultDeliveryMethodCheckoutStepValidator.class);

	@Resource(name = "shipmentFacade")
	private ShipmentFacade shipmentFacade;

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
			LOGGER.info("Missing, empty or unsupported cart");
			return ValidationResults.REDIRECT_TO_CART;
		}

		/*
		 * if (!getCheckoutFacade().hasShippingItems()) { return ValidationResults.REDIRECT_TO_PICKUP_LOCATION; }
		 */
		final String shipmentType = shipmentFacade.getCurrentShipmentMethod();

		if (ShipmentTypeEnum.DELIVERY.equals(ShipmentTypeEnum.valueOf(shipmentType)))
		{
			if (getCheckoutFlowFacade().hasNoDeliveryAddress())
			{
				GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER,
						"checkout.multi.deliveryAddress.notprovided");
				return ValidationResults.REDIRECT_TO_DELIVERY_ADDRESS;
			}
		}

		return ValidationResults.SUCCESS;
	}

	@Override
	public ValidationResults validateOnExit()
	{
		/*
		 * if (getCheckoutFacade().hasPickUpItems()) { return ValidationResults.REDIRECT_TO_PICKUP_LOCATION; }
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

			// Invalid cart. Bounce back to the cart page.
			return true;
		}
		return false;
	}

}
