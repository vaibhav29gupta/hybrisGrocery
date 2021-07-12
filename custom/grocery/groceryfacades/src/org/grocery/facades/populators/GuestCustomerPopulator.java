/**
 * 
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.model.ContactEmailAttribute;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;

import org.springframework.util.Assert;

/**
 * @author arshdeepsingh
 *
 */
public class GuestCustomerPopulator implements Populator<CustomerModel, CustomerData> 
{

	public void populate(final CustomerModel source, final CustomerData target)
	{
	  target.setEmail(source.getContactEmail());
	}
}
