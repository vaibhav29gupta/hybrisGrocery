/**
 * 
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.product.converters.populator.VariantOptionDataPopulator;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.VariantOptionData;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.variants.model.VariantProductModel;

import java.math.BigDecimal;

import org.grocery.core.price.GroceryPriceService;

/**
 * @author arshdeepsingh
 *
 */
public class VariantProductDiscountPricePopulator extends VariantOptionDataPopulator
{


	private GroceryPriceService discountPriceService;

	@Override
	public void populate(final VariantProductModel variantModel, final VariantOptionData variantData) throws ConversionException
	{

		if (null != variantData && null != variantData.getPriceData())
		{
			final PriceData price = variantData.getPriceData();
			if (null != price.getValue())
			{
				if (PriceDataType.BUY.equals(price.getPriceType()))
				{
					final PriceInformation discPrice = discountPriceService.getWebDiscountedPriceForProduct(variantModel,
							price.getValue().doubleValue());

					if (null != discPrice && null != discPrice.getPriceValue() && discPrice.getPriceValue().getValue() > 0)
					{
						final PriceData discountPrice = getPriceDataFactory().create(PriceDataType.BUY,
								BigDecimal.valueOf(discPrice.getPriceValue().getValue()), price.getCurrencyIso());

						if (null != discountPrice && null != discountPrice.getValue()
								&& !BigDecimal.ZERO.equals(discountPrice.getValue())
								&& discountPrice.getValue().doubleValue() < price.getValue().doubleValue())
						{
							price.setSalesPrice(discountPrice.getValue());
							price.setFormattedSalesPrice(discountPrice.getFormattedValue());
							variantData.setPriceData(price);
							variantData.setDiscounted(true);
							return;
						}
					}

				}
			}

		}
	}

	/**
	 * @return the discountPriceService
	 */
	public GroceryPriceService getDiscountPriceService()
	{
		return discountPriceService;
	}

	/**
	 * @param discountPriceService
	 *           the discountPriceService to set
	 */
	public void setDiscountPriceService(final GroceryPriceService discountPriceService)
	{
		this.discountPriceService = discountPriceService;
	}


}
