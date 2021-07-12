/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.facades.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.commerceservices.model.process.UpdatePasswordEmailProcessModel;


/**
 * Velocity context for a forgotten password email.
 */
public class UpdatePasswordEmailContext extends CustomerEmailContext
{
	private String firstName;

	@Override
	public void init(final StoreFrontCustomerProcessModel processModel, final EmailPageModel emailPageModel)
	{
		super.init(processModel, emailPageModel);
		if (processModel instanceof UpdatePasswordEmailProcessModel)
		{
			setFirstName(((UpdatePasswordEmailProcessModel) processModel).getFirstName());
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
}
