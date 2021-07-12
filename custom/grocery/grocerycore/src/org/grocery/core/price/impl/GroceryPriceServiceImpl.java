/**
 *
 */
package org.grocery.core.price.impl;

import de.hybris.platform.commerceservices.price.impl.DefaultCommercePriceService;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.DiscountInformation;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.jalo.order.price.ProductPriceInformations;
import de.hybris.platform.product.PriceCriteria;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.util.DiscountValue;
import de.hybris.platform.util.PriceValue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.grocery.core.price.GroceryPriceService;


/**
 * @author bhavya2486
 *
 */
public class GroceryPriceServiceImpl extends DefaultCommercePriceService implements GroceryPriceService
{

	private CommonI18NService commonI18NService;

	@Override
	public PriceInformation getFromDiscountedPriceForProduct(final ProductModel product, final double price)
	{
		return getProductPriceInformations(product, price);
	}

	@Override
	public PriceInformation getWebDiscountedPriceForProduct(final ProductModel product, final double price)
	{
		return getProductPriceInformations(product, price);
	}

	protected PriceInformation getProductPriceInformations(final ProductModel product, final double price)
	{
		final PriceCriteria criteria = PriceCriteria.DefaultPriceCriteria.forProduct(product);
		final ProductPriceInformations priceInformations = getPriceService().getAllPriceInformation(criteria);
		if (null != priceInformations && CollectionUtils.isNotEmpty(priceInformations.getDiscounts()))
		{
			final List<DiscountInformation> discounts = new ArrayList<>(priceInformations.getDiscounts());
			if (discounts.isEmpty())
			{
				return null;
			}
			final CurrencyModel currency = commonI18NService.getCurrentCurrency();
			double discountPrice = price;
			for (final DiscountInformation dicount : discounts)
			{
				//get unapplied DiscountValue (template instance)
				final DiscountValue unApplied = dicount.getDiscountValue();

				if (unApplied.getCurrencyIsoCode().equals(currency.getIsocode()))
				{
					//create applied DiscountValue (concrete instance)
					final DiscountValue applied = unApplied.apply(1, discountPrice, currency.getDigits(), currency.getIsocode());

					//adjust final price
					discountPrice -= applied.getAppliedValue();
				}
			}
			final PriceValue priceValue = new PriceValue(currency.getIsocode(), discountPrice, false);
			return new PriceInformation(priceValue);
		}
		return null;

	}

	/**
	 * @return the commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	/**
	 * @param commonI18NService
	 *           the commonI18NService to set
	 */
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

}
