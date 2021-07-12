/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.product.converters.populator.ProductPricePopulator;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.math.BigDecimal;

import org.grocery.core.price.GroceryPriceService;


/**
 * @author bhavya2486
 *
 */
public class ProductDiscountPricePopulator<SOURCE extends ProductModel, TARGET extends ProductData>
		extends ProductPricePopulator<SOURCE, TARGET>
{

	private GroceryPriceService discountPriceService;

	@Override
	public void populate(final SOURCE productModel, final TARGET productData) throws ConversionException
	{

		if (null != productData && null != productData.getPrice())
		{
			final PriceData price = productData.getPrice();
			if (null != price.getValue())
			{
				if (PriceDataType.BUY.equals(price.getPriceType()))
				{
					final PriceInformation discPrice = discountPriceService.getWebDiscountedPriceForProduct(productModel,
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
							productData.setPrice(price);
							productData.setDiscounted(true);
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
