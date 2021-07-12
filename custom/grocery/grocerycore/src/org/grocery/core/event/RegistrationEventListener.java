/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.core.event;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.RegisterEvent;
import de.hybris.platform.commerceservices.model.process.CustomerRegistrationEmailProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.grocery.service.promotions.ProcessesRelatedPromotionService;
import org.springframework.beans.factory.annotation.Required;


/**
 * Listener for customer registration events.
 */
public class RegistrationEventListener extends AbstractAcceleratorSiteEventListener<RegisterEvent>
{

	private ModelService modelService;
	private BusinessProcessService businessProcessService;
	private ProcessesRelatedPromotionService promotionService;

	protected BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	/**
	 * @return the modelService
	 */
	protected ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public ProcessesRelatedPromotionService getPromotionService() {
		return promotionService;
	}

	public void setPromotionService(ProcessesRelatedPromotionService promotionService) {
		this.promotionService = promotionService;
	}

	@Override
	protected void onSiteEvent(final RegisterEvent registerEvent)
	{
		final CustomerRegistrationEmailProcessModel customerRegistrationEmailProcessModel = (CustomerRegistrationEmailProcessModel) getBusinessProcessService()
				.createProcess(
						"customerRegistrationEmailProcess-" + registerEvent.getCustomer().getUid() + "-" + System.currentTimeMillis(),
						"customerRegistrationEmailProcess");
		customerRegistrationEmailProcessModel.setSite(registerEvent.getSite());
		customerRegistrationEmailProcessModel.setCustomer(registerEvent.getCustomer());
		customerRegistrationEmailProcessModel.setLanguage(registerEvent.getLanguage());
		customerRegistrationEmailProcessModel.setCurrency(registerEvent.getCurrency());
		customerRegistrationEmailProcessModel.setStore(registerEvent.getBaseStore());
		customerRegistrationEmailProcessModel.setGeneratedCouponCode(
				promotionService.getNewCouponCode(registerEvent.getBaseStore(), ProcessesRelatedPromotionService.PromotionProcess.REGISTRATION)
		);
		customerRegistrationEmailProcessModel.setPromotionMessage(
				promotionService.getPromotionMessage(registerEvent.getBaseStore(), ProcessesRelatedPromotionService.PromotionProcess.REGISTRATION)
		);

		getModelService().save(customerRegistrationEmailProcessModel);
		getBusinessProcessService().startProcess(customerRegistrationEmailProcessModel);
	}

	@Override
	protected SiteChannel getSiteChannelForEvent(final RegisterEvent event)
	{
		final BaseSiteModel site = event.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
		return site.getChannel();
	}
}
