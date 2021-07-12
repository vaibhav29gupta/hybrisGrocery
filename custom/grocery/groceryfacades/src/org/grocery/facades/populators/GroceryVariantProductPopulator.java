/**
 * 
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.product.data.VariantOptionData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.grocery.core.model.GroceryVariantProductModel;

/**
 * @author arshdeepsingh
 *
 */
public class GroceryVariantProductPopulator implements Populator<GroceryVariantProductModel,VariantOptionData> 
{

	@Override
	public void populate(GroceryVariantProductModel source, VariantOptionData target) throws ConversionException
	{
		
		target.setPackSizeLabel(source.getPackSizeLabel());
	}
}

