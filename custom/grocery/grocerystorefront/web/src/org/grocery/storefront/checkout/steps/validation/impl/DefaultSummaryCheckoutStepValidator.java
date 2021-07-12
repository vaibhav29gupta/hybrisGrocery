/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.storefront.checkout.steps.validation.impl;

import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.validation.AbstractCheckoutStepValidator;
import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.validation.ValidationResults;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.order.CartService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.grocery.core.enums.ShipmentTypeEnum;
import org.grocery.core.strategies.impl.GroceryCustomCartValidationStrategy;
import org.grocery.facades.shipment.ShipmentFacade;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


public class DefaultSummaryCheckoutStepValidator extends AbstractCheckoutStepValidator
{
	@Resource(name = "shipmentFacade")
	private ShipmentFacade shipmentFacade;

	@Resource(name = "groceryCustomCartValidationStrategy")
	private GroceryCustomCartValidationStrategy groceryCustomCartValidationStrategy;

	@Resource(name = "cartService")
	private CartService cartService;


	private static final Logger LOGGER = Logger.getLogger(DefaultSummaryCheckoutStepValidator.class);

	@Override
	public ValidationResults validateOnEnter(final RedirectAttributes redirectAttributes)
	{
		final List<CartModificationData> modifications = new ArrayList<>();
		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(cartService.getSessionCart());

		final boolean result = groceryCustomCartValidationStrategy.validateCartForOOSProducts(parameter);

		if (result == true)
		{
			return ValidationResults.REDIRECT_TO_DUMMY_STEP;
		}
		final ValidationResults cartResult = checkCartAndDelivery(redirectAttributes);
		if (cartResult != null) {
			return cartResult;
		}

		final ValidationResults paymentResult = checkPaymentMethodAndPickup(redirectAttributes);
		if (paymentResult != null) {
			return paymentResult;
		}

		return ValidationResults.SUCCESS;
	}

	protected ValidationResults checkPaymentMethodAndPickup(final RedirectAttributes redirectAttributes) {
		if (getCheckoutFlowFacade().hasNoPaymentInfo())
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.INFO_MESSAGES_HOLDER,
					"checkout.multi.paymentDetails.notprovided");
			return ValidationResults.REDIRECT_TO_PAYMENT_METHOD;
		}

		final CartData cartData = getCheckoutFacade().getCheckoutCart();

		if (!getCheckoutFacade().hasShippingItems())
		{
			cartData.setDeliveryAddress(null);
		}

		/*
		 * if (!getCheckoutFacade().hasPickUpItems() && "pickup".equals(cartData.getDeliveryMode().getCode())) { return
		 * ValidationResults.REDIRECT_TO_PICKUP_LOCATION; }
		 */
		return null;
	}

	protected ValidationResults checkCartAndDelivery(final RedirectAttributes redirectAttributes) {
		if (!getCheckoutFlowFacade().hasValidCart())
		{
			LOGGER.info("Missing, empty or unsupported cart");
			return ValidationResults.REDIRECT_TO_CART;
		}

		final String shipmentType = shipmentFacade.getCurrentShipmentMethod();

		if (ShipmentTypeEnum.DELIVERY.equals(ShipmentTypeEnum.valueOf(shipmentType)))
		{
		if (getCheckoutFlowFacade().hasNoDeliveryAddress())
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.INFO_MESSAGES_HOLDER,
					"checkout.multi.deliveryAddress.notprovided");
			return ValidationResults.REDIRECT_TO_DELIVERY_ADDRESS;
		}

		if (getCheckoutFlowFacade().hasNoDeliveryMode())
		{
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.INFO_MESSAGES_HOLDER,
					"checkout.multi.deliveryMethod.notprovided");
			return ValidationResults.REDIRECT_TO_DELIVERY_METHOD;
		}
		}
		return null;
	}
}
