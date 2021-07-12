/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.commercefacades.order.converters.populator.PaymentModePopulator;
import de.hybris.platform.commercefacades.order.data.PaymentModeData;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

/**
 * @author arshdeepsingh
 *
 */
public class GroceryPaymentModePopulator extends PaymentModePopulator
{


	public Converter<MediaModel, MediaData> mediaModelConverter;


	/**
	 * @return the mediaModelConverter
	 */
	public Converter<MediaModel, MediaData> getMediaModelConverter()
	{
		return mediaModelConverter;
	}


	/**
	 * @param mediaModelConverter the mediaModelConverter to set
	 */
	public void setMediaModelConverter(Converter<MediaModel, MediaData> mediaModelConverter)
	{
		this.mediaModelConverter = mediaModelConverter;
	}


	@Override
	public void populate(final PaymentModeModel source, final PaymentModeData target) throws ConversionException
	{
		super.populate(source, target);
		if(source.getType() != null) {
		target.setType(source.getType().getCode());
		}
		final MediaData mediaData = mediaModelConverter.convert(source.getPaymentModeImage());
		target.setPaymentModeImage(mediaData);
	}
}
