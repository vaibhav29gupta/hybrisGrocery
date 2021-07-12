/**
 *
 */
package org.grocery.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.product.PriceService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.grocery.core.price.GroceryPriceService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author ankituniyal
 *
 */
public class ProductDiscountedPriceValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider
{
	private static final Logger LOG = Logger.getLogger(ProductDiscountedPriceValueProvider.class);

	private FieldNameProvider fieldNameProvider;
	private PriceService priceService;
	private GroceryPriceService discountPriceService;

	@Autowired
	private I18NService i18nService;

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{

		if (!(model instanceof ProductModel))
		{
			throw new FieldValueProviderException("Cannot evaluate price of non-product item");
		}
		else
		{
			final Collection<FieldValue> fieldValues = new ArrayList<>();
			final ProductModel product = (ProductModel) model;
			if (indexedProperty.isCurrency() && CollectionUtils.isNotEmpty(indexConfig.getCurrencies()))
			{
				final CurrencyModel sessionCurrency = this.i18nService.getCurrentCurrency();

				try
				{
					final Iterator<CurrencyModel> itr = indexConfig.getCurrencies().iterator();

					while (itr.hasNext())
					{
						final CurrencyModel currency = itr.next();
						this.i18nService.setCurrentCurrency(currency);
						this.addFieldValues(fieldValues, product, indexedProperty, currency);
					}
				}
				finally
				{
					this.i18nService.setCurrentCurrency(sessionCurrency);
				}
			}
			else
			{
				this.addFieldValues(fieldValues, product, indexedProperty, null);
			}

			return fieldValues;
		}
	}

	protected void addFieldValues(final Collection<FieldValue> fieldValues, final ProductModel productModel,
			final IndexedProperty indexedProperty, final CurrencyModel currency) throws FieldValueProviderException
	{

		try
		{
			final List<PriceInformation> priceInfo = priceService.getPriceInformationsForProduct(productModel);
			if (CollectionUtils.isEmpty(priceInfo))
			{
				return;
			}

			final Optional<PriceInformation> optionalPrice = priceInfo.stream()
					.filter(info -> info.getPriceValue().getCurrencyIso().equals(currency.getIsocode())).findFirst();

			if (optionalPrice.isPresent())
			{
				final Double productPrice = optionalPrice.get().getPriceValue().getValue();
				final PriceInformation discPriceInfo = discountPriceService.getWebDiscountedPriceForProduct(productModel,
						productPrice);
				if (discPriceInfo == null)
				{
					return;
				}
				final double discPrice = discPriceInfo.getValue().getValue();
				final List<String> rangeNameList = this.getRangeNameList(indexedProperty, discPrice, currency.getIsocode());

				final Collection<String> fieldNames = this.fieldNameProvider.getFieldNames(indexedProperty,
						currency.getIsocode() == null ? null : currency.getIsocode().toLowerCase(Locale.ROOT));
				final Iterator<String> itr = fieldNames.iterator();
				while (true)
				{
					while (itr.hasNext())
					{
						final String fieldName = itr.next();
						if (rangeNameList.isEmpty())
						{
							fieldValues.add(new FieldValue(fieldName, discPrice));
						}
						else
						{
							final Iterator<String> var12 = rangeNameList.iterator();

							while (var12.hasNext())
							{
								final String rangeName = var12.next();
								fieldValues.add(new FieldValue(fieldName, rangeName == null ? discPrice : rangeName));
							}
						}
					}

					return;
				}
			}
		}
		catch (final IllegalArgumentException | FieldValueProviderException e)
		{
			LOG.error("Error while indexing for " + productModel.getCode() + " " + e.getMessage());
		}
	}

	/**
	 * @return the fieldNameProvider
	 */
	public FieldNameProvider getFieldNameProvider()
	{
		return fieldNameProvider;
	}

	/**
	 * @param fieldNameProvider
	 *           the fieldNameProvider to set
	 */
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}

	/**
	 * @return the priceService
	 */
	public PriceService getPriceService()
	{
		return priceService;
	}

	/**
	 * @param priceService
	 *           the priceService to set
	 */
	public void setPriceService(final PriceService priceService)
	{
		this.priceService = priceService;
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
