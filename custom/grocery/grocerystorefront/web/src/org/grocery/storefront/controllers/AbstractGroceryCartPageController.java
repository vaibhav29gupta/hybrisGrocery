/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.storefront.controllers;

import de.hybris.platform.acceleratorfacades.order.AcceleratorCheckoutFacade;
import de.hybris.platform.acceleratorservices.config.SiteConfigService;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.forms.SaveCartForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdateQuantityForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.SaveCartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


public abstract class AbstractGroceryCartPageController extends AbstractGroceryPageController
{

	private static final String CART_CMS_PAGE_LABEL = "cart";
	private static final String CONTINUE_URL = "continueUrl";


	private static final Logger LOGGER = Logger.getLogger(AbstractGroceryCartPageController.class);

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@Resource(name = "siteConfigService")
	private SiteConfigService siteConfigService;

	@Resource(name = "sessionService")
	private SessionService sessionService;

	@Resource(name = "acceleratorCheckoutFacade")
	private AcceleratorCheckoutFacade checkoutFacade;

	@Resource(name = "saveCartFacade")
	private SaveCartFacade saveCartFacade;

	protected void createProductList(final Model model) throws CMSItemNotFoundException
	{
		final CartData cartData = cartFacade.getSessionCartWithEntryOrdering(false);
		createProductEntryList(model, cartData);

		final ContentPageModel cartPage = getContentPageForLabelOrId(CART_CMS_PAGE_LABEL);
		storeCmsPageInModel(model, cartPage);
		setUpMetaDataForContentPage(model, cartPage);
	}

	protected void createProductEntryList(final Model model, final CartData cartData)
	{
		boolean hasPickUpCartEntries = false;
		if (cartData.getEntries() != null && !cartData.getEntries().isEmpty())
		{
			for (final OrderEntryData entry : cartData.getEntries())
			{
				if (!hasPickUpCartEntries && entry.getDeliveryPointOfService() != null)
				{
					hasPickUpCartEntries = true;
				}
				final UpdateQuantityForm uqf = new UpdateQuantityForm();
				uqf.setQuantity(entry.getQuantity());
				model.addAttribute("updateQuantityForm" + entry.getEntryNumber(), uqf);
			}
		}

		model.addAttribute("cartData", cartData);
		model.addAttribute("hasPickUpCartEntries", Boolean.valueOf(hasPickUpCartEntries));
	}

	protected void continueUrl(final Model model) throws CMSItemNotFoundException
	{
		final String continueUrl = (String) sessionService.getAttribute(WebConstants.CONTINUE_URL);
		model.addAttribute(CONTINUE_URL, (continueUrl != null && !continueUrl.isEmpty()) ? continueUrl : ROOT);
	}

	protected void prepareDataForPage(final Model model) throws CMSItemNotFoundException
	{
		continueUrl(model);

		createProductList(model);

		setupCartPageRestorationData(model);
		clearSessionRestorationData();

		model.addAttribute("isOmsEnabled", Boolean.valueOf(getSiteConfigService().getBoolean("oms.enabled", false)));
		model.addAttribute("supportedCountries", cartFacade.getDeliveryCountries());
		model.addAttribute("expressCheckoutAllowed", Boolean.valueOf(checkoutFacade.isExpressCheckoutAllowedForCart()));
		model.addAttribute("taxEstimationEnabled", Boolean.valueOf(checkoutFacade.isTaxEstimationEnabledForCart()));
		model.addAttribute("savedCartCount", saveCartFacade.getSavedCartsCountForCurrentUser());
		if (!model.containsAttribute("saveCartForm"))
		{
			model.addAttribute("saveCartForm", new SaveCartForm());
		}
	}

	/**
	 * Remove the session data of the cart restoration.
	 */
	protected void clearSessionRestorationData()
	{
		getSessionService().removeAttribute(WebConstants.CART_RESTORATION);
		getSessionService().removeAttribute(WebConstants.CART_RESTORATION_ERROR_STATUS);
	}

	/**
	 * Prepare the restoration data and always display any modifications on the cart page.
	 *
	 * @param model
	 *           - the cart page
	 */
	public void setupCartPageRestorationData(final Model model)
	{
		if (getSessionService().getAttribute(WebConstants.CART_RESTORATION) != null)
		{
			if (getSessionService().getAttribute(WebConstants.CART_RESTORATION_ERROR_STATUS) != null)
			{
				model.addAttribute("restorationErrorMsg", getSessionService()
						.getAttribute(WebConstants.CART_RESTORATION_ERROR_STATUS));
			}
			else
			{
				model.addAttribute("restorationData", getSessionService().getAttribute(WebConstants.CART_RESTORATION));
			}
		}
		model.addAttribute("showModifications", Boolean.TRUE);
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

	@Override
	public SiteConfigService getSiteConfigService()
	{
		return siteConfigService;
	}

	protected CartFacade getCartFacade()
	{
		return cartFacade;
	}

	@Override
	protected SessionService getSessionService()
	{
		return sessionService;
	}

	protected AcceleratorCheckoutFacade getCheckoutFacade()
	{
		return checkoutFacade;
	}

}
