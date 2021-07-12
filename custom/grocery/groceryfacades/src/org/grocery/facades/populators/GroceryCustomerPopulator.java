/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.user.converters.populator.CustomerPopulator;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;

import org.springframework.util.StringUtils;


/**
 * @author abhishekkumar05
 *
 */
public class GroceryCustomerPopulator extends CustomerPopulator
{
	@Override
	public void populate(final CustomerModel source, final CustomerData target)
	{
		super.populate(source, target);

		if (!StringUtils.isEmpty(source.getPreferredShipmentMode()))
		{
			target.setPreferredShipmentMode(source.getPreferredShipmentMode().toString());
		}
		if (!StringUtils.isEmpty(source.getCurrentShipmentMode()))
		{
			target.setCurrentShipmentMode(source.getCurrentShipmentMode().toString());
		}
		target.setPreferredPostalCode(source.getPreferredPostalCode());
		target.setCurrentPostalCode(source.getCurrentPostalCode());
		target.setPreferredPos(source.getPreferredPos());
		target.setCurrentPos(source.getCurrentPos());
	}

}
