/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.core.event;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.model.process.UpdatePasswordEmailProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.springframework.beans.factory.annotation.Required;


/**
 * Listener for "forgotten password" functionality event.
 */
public class UpdatePasswordEventListener extends AbstractAcceleratorSiteEventListener<UpdatedPasswordEvent>
{

	private ModelService modelService;

	private BusinessProcessService businessProcessService;


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

	@Override
	protected void onSiteEvent(final UpdatedPasswordEvent event)
	{
		final UpdatePasswordEmailProcessModel updatePasswordEmailProcessModel = (UpdatePasswordEmailProcessModel) getBusinessProcessService()
				.createProcess("updatePassword-" + event.getCustomer().getUid() + "-" + System.currentTimeMillis(),
						"updatePasswordEmailProcess");
		updatePasswordEmailProcessModel.setSite(event.getSite());
		updatePasswordEmailProcessModel.setCustomer(event.getCustomer());
		updatePasswordEmailProcessModel.setLanguage(event.getLanguage());
		updatePasswordEmailProcessModel.setCurrency(event.getCurrency());
		updatePasswordEmailProcessModel.setStore(event.getBaseStore());
		getModelService().save(updatePasswordEmailProcessModel);
		getBusinessProcessService().startProcess(updatePasswordEmailProcessModel);
	}

	@Override
	protected SiteChannel getSiteChannelForEvent(final UpdatedPasswordEvent event)
	{
		final BaseSiteModel site = event.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.site", site);
		return site.getChannel();
	}
}
