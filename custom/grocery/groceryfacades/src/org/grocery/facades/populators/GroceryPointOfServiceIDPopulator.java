/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;


public class GroceryPointOfServiceIDPopulator<SOURCE extends PointOfServiceModel, TARGET extends PointOfServiceData>
		implements Populator<SOURCE, TARGET>
{
	private static final String EXTERNAL = "external";

	@Override
	public void populate(final SOURCE posModel, final TARGET posData) throws ConversionException
	{
		posData.setStoreID(posModel.getName());

		final String external = BooleanUtils.isTrue(posModel.isIsExternal()) ? EXTERNAL : StringUtils.EMPTY;
		posData.setExternal(external);
	}


}
