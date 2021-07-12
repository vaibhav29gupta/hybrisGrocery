/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.storefront.controllers.pages.checkout.steps;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.PreValidateCheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.PreValidateQuoteCheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.CheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.deliveryslot.data.DeliverySlotDayConfigData;
import de.hybris.platform.commercefacades.deliveryslot.data.DeliverySlotTimeConfigData;
import de.hybris.platform.commercefacades.order.data.CartData;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.grocery.facade.deliveryslots.DeliverySlotFacade;
import org.grocery.facades.shipment.ShipmentFacade;
import org.grocery.storefront.controllers.AbstractGroceryCheckoutStepController;
import org.grocery.storefront.controllers.ControllerConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "/checkout/multi/delivery-method")
public class DeliveryMethodCheckoutStepController extends AbstractGroceryCheckoutStepController
{
	private static final String DELIVERY_METHOD = "delivery-method";
	private static final String ERROR_MSG_TYPE = "errorMsg";

	private static final Logger LOGGER = Logger.getLogger(DeliveryMethodCheckoutStepController.class);

	@Resource(name = "deliverySlotFacade")
	private DeliverySlotFacade deliverySlotFacade;

	@Resource(name = "shipmentFacade")
	private ShipmentFacade shipmentFacade;

	@RequestMapping(value = "/choose", method = RequestMethod.GET)
	@RequireHardLogIn
	@Override
	@PreValidateQuoteCheckoutStep
	@PreValidateCheckoutStep(checkoutStep = DELIVERY_METHOD)
	public String enterStep(final Model model, final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{
		// Try to set default delivery mode
		getCheckoutFacade().setDeliveryModeIfAvailable();

		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		model.addAttribute("cartData", cartData);
		model.addAttribute("deliveryMethods", getCheckoutFacade().getSupportedDeliveryModes());
		this.prepareDataForPage(model);
		final ContentPageModel multiCheckoutSummaryPage = getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL);
		storeCmsPageInModel(model, multiCheckoutSummaryPage);
		setUpMetaDataForContentPage(model, multiCheckoutSummaryPage);
		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
				getResourceBreadcrumbBuilder().getBreadcrumbs("checkout.multi.deliveryMethod.breadcrumb"));
		model.addAttribute("metaRobots", "noindex,nofollow");
		setCheckoutStepLinksForModel(model, getCheckoutStep());

		final String storeID = shipmentFacade.getCurrentStoreID();
		if (StringUtils.isEmpty(storeID))
		{
			model.addAttribute(ERROR_MSG_TYPE, "no.store.found");
			return ControllerConstants.Views.Pages.MultiStepCheckout.ChooseDeliveryMethodPage;
		}
		setDeliveryDateAndTimeSlot(storeID, model);

		return ControllerConstants.Views.Pages.MultiStepCheckout.ChooseDeliveryMethodPage;
	}

	private void setDeliveryDateAndTimeSlot(final String storeID, final Model model)
	{
		/*
		 * if (!getCheckoutCustomerStrategy().isAnonymousCheckout()) {
		 */
			final List<DeliverySlotDayConfigData> deliverySlotDayConfigData = getDeliverySlotFacade()
					.fetchDeliverySlotDaysForStore(storeID, new Date());

			/* Fetching Delivery Time */
			final List<DeliverySlotTimeConfigData> deliverySlotTimeConfigData = getDeliverySlotFacade()
					.fetchDeliverySlotTimesForStore(storeID, new Date());
			if (CollectionUtils.isNotEmpty(deliverySlotDayConfigData) && CollectionUtils.isNotEmpty(deliverySlotTimeConfigData))
			{
				model.addAttribute("deliverySlotDate", deliverySlotDayConfigData);
				model.addAttribute("deliverySlotTime", deliverySlotTimeConfigData);
			}
		/* } */
	}

	/**
	 * This method gets called when the "Use Selected Delivery Method" button is clicked. It sets the selected delivery
	 * mode on the checkout facade and reloads the page highlighting the selected delivery Mode.
	 *
	 * @param selectedDeliveryMethod
	 *           - the id of the delivery mode.
	 * @return - a URL to the page to load.
	 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@RequireHardLogIn
	public String doSelectDeliveryMode(@RequestParam(name = "delivery_method", required = false)
	final String selectedDeliveryMethod, @RequestParam(name = "shippingDelDate", required = false)
	final String shippingDelDate, @RequestParam(name = "shippingDelTime", required = false)
	final String shippingDelTime)
	{
		if (StringUtils.isNotEmpty(selectedDeliveryMethod))
		{
			getCheckoutFacade().setDeliveryMode(selectedDeliveryMethod);
		}

		getDeliverySlotFacade().updateDateAndTimeSlot(shippingDelDate, shippingDelTime);

		return getCheckoutStep().nextStep();
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	@RequireHardLogIn
	@Override
	public String back(final RedirectAttributes redirectAttributes)
	{
		return getCheckoutStep().previousStep();
	}

	@RequestMapping(value = "/next", method = RequestMethod.GET)
	@RequireHardLogIn
	@Override
	public String next(final RedirectAttributes redirectAttributes)
	{
		return getCheckoutStep().nextStep();
	}

	protected CheckoutStep getCheckoutStep()
	{
		return getCheckoutStep(DELIVERY_METHOD);
	}

	/**
	 * @return the deliverySlotFacade
	 */
	public DeliverySlotFacade getDeliverySlotFacade()
	{
		return deliverySlotFacade;
	}

	/**
	 * @param deliverySlotFacade
	 *           the deliverySlotFacade to set
	 */
	public void setDeliverySlotFacade(final DeliverySlotFacade deliverySlotFacade)
	{
		this.deliverySlotFacade = deliverySlotFacade;
	}
}
