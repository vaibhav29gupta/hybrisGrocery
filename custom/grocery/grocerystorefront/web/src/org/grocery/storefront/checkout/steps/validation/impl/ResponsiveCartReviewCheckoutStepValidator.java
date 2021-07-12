/**
 *
 */
package org.grocery.storefront.checkout.steps.validation.impl;

import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.validation.AbstractCheckoutStepValidator;
import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.validation.ValidationResults;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * @author arshdeepsingh
 *
 */
public class ResponsiveCartReviewCheckoutStepValidator extends AbstractCheckoutStepValidator
{

	@Override
	public ValidationResults validateOnEnter(final RedirectAttributes redirectAttributes)
	{
		if (!getCheckoutFlowFacade().hasValidCart())
		{
			return ValidationResults.REDIRECT_TO_CART;
		}

		/*
		 * if (!getCheckoutFacade().hasShippingItems()) { return ValidationResults.REDIRECT_TO_PAYMENT_METHOD; }
		 */

		return ValidationResults.SUCCESS;
	}
}
