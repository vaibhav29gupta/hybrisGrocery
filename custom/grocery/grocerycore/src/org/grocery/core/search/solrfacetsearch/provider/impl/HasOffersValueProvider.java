/**
 * 
 */
package org.grocery.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.product.PriceService;
import de.hybris.platform.promotionengineservices.model.RuleBasedPromotionModel;
import de.hybris.platform.promotions.PromotionsService;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.promotions.model.PromotionGroupModel;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.grocery.core.price.GroceryPriceService;

/**
 * @author arshdeepsingh
 *
 */
public class HasOffersValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider
{
	private static final Logger LOG = Logger.getLogger(ProductDiscountedPriceValueProvider.class);

	private FieldNameProvider fieldNameProvider;
	private PromotionsService promotionsService;
	private TimeService timeService;
	private PriceService priceService;
	private GroceryPriceService discountPriceService;


	/**
	 * @return the fieldNameProvider
	 */
	public FieldNameProvider getFieldNameProvider()
	{
		return fieldNameProvider;
	}

	/**
	 * @return the timeService
	 */
	public TimeService getTimeService()
	{
		return timeService;
	}

	/**
	 * @param timeService
	 *           the timeService to set
	 */
	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
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
	 * @return the promotionService
	 */
	public PromotionsService getPromotionsService()
	{
		return promotionsService;
	}

	/**
	 * @param promotionService
	 *           the promotionService to set
	 */
	public void setPromotionsService(final PromotionsService promotionService)
	{
		this.promotionsService = promotionService;
	}
	
	

	/**
	 * @return the priceService
	 */
	public PriceService getPriceService()
	{
		return priceService;
	}

	/**
	 * @param priceService the priceService to set
	 */
	public void setPriceService(PriceService priceService)
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
	 * @param discountPriceService the discountPriceService to set
	 */
	public void setDiscountPriceService(GroceryPriceService discountPriceService)
	{
		this.discountPriceService = discountPriceService;
	}

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		if (model instanceof ProductModel)
		{
			final ProductModel product = (ProductModel) model;

			final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
			
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
						fieldValues.addAll(createFieldValues(product, indexConfig, indexedProperty, currency));
						fieldValues.addAll(addDiscountedFieldValues(product, indexedProperty, currency));
					}
				}
				finally
				{
					this.i18nService.setCurrentCurrency(sessionCurrency);
				}
			}
			else
			{
				fieldValues.addAll(addDiscountedFieldValues(product, indexedProperty, null));
				fieldValues.addAll(createFieldValues(product, indexConfig, indexedProperty, null));
			}
			return fieldValues;
		}
		else
		{
			throw new FieldValueProviderException("Cannot get offers of non-product item");
		}
	}

	/**
	 * @param product
	 * @param indexConfig
	 * @param indexedProperty
	 * @return
	 */
	/*
	 * private Collection<? extends FieldValue> createFieldValue(final ProductModel product, final IndexConfig
	 * indexConfig, final IndexedProperty indexedProperty) {
	 * 
	 * final List<FieldValue> fieldValues = new ArrayList<FieldValue>(); final BaseSiteModel baseSiteModel =
	 * indexConfig.getBaseSite(); final PromotionGroupModel defaultPromotionGroup =
	 * baseSiteModel.getDefaultPromotionGroup(); final Date currentTimeRoundedToMinute =
	 * DateUtils.round(getTimeService().getCurrentTime(), Calendar.MINUTE); if (baseSiteModel != null &&
	 * baseSiteModel.getDefaultPromotionGroup() != null) { for (final AbstractPromotionModel promotion :
	 * getPromotionsService().getAbstractProductPromotions( Collections.singletonList(defaultPromotionGroup), product,
	 * true, currentTimeRoundedToMinute)) { if (promotion instanceof RuleBasedPromotionModel) { if (promotion != null) {
	 * addFieldValues(fieldValues, indexedProperty, null, true); break; } } } } return fieldValues; }
	 */

	/**
	 * @param product
	 * @param indexConfig
	 * @param indexedProperty
	 * @return
	 */
	private Collection<? extends FieldValue> createFieldValues(final ProductModel product, final IndexConfig indexConfig,
			final IndexedProperty indexedProperty, final CurrencyModel currency)
	{
		final List<FieldValue> fieldValues = new ArrayList<>();
		final BaseSiteModel baseSiteModel = indexConfig.getBaseSite();
		boolean hasPromotion = false;

		if (baseSiteModel != null && baseSiteModel.getDefaultPromotionGroup() != null)
		{
			final PromotionGroupModel defaultPromotionGroup = baseSiteModel.getDefaultPromotionGroup();
			final Date currentTimeRoundedToMinute = DateUtils.round(getTimeService().getCurrentTime(), Calendar.MINUTE);

			for (final AbstractPromotionModel promotion : getPromotionsService().getAbstractProductPromotions(
					Collections.singletonList(defaultPromotionGroup), product, true, currentTimeRoundedToMinute))
			{
				if (promotion != null)
				{
					addFieldValues(fieldValues, indexedProperty, currency, "true");
               break;
				}
			}
		}
		return fieldValues;
	}
	
	protected Collection<? extends FieldValue> addDiscountedFieldValues(final ProductModel productModel,
			final IndexedProperty indexedProperty, final CurrencyModel currency) throws FieldValueProviderException
	{

			final List<FieldValue> fieldValues = new ArrayList<>();
			final List<PriceInformation> priceInfo = priceService.getPriceInformationsForProduct(productModel);
			

			final Optional<PriceInformation> optionalPrice = priceInfo.stream()
					.filter(info -> info.getPriceValue().getCurrencyIso().equals(currency.getIsocode())).findFirst();

			if (optionalPrice.isPresent())
			{
				final Collection<String> fieldNames = this.fieldNameProvider.getFieldNames(indexedProperty,
						currency.getIsocode() == null ? null : currency.getIsocode().toLowerCase(Locale.ROOT));
				final Double productPrice = optionalPrice.get().getPriceValue().getValue();
				final PriceInformation discPriceInfo = discountPriceService.getWebDiscountedPriceForProduct(productModel,
						productPrice);
				
				if(discPriceInfo != null && discPriceInfo.getPriceValue() != null && discPriceInfo.getPriceValue().getCurrencyIso().equals(currency.getIsocode())) {
				   addFieldValues(fieldValues, indexedProperty, currency, "true");
             }
		   }
			return fieldValues;
	}

	protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty,
			final CurrencyModel currency, final Object value)
	{
		final Collection<String> fieldNames = this.fieldNameProvider.getFieldNames(indexedProperty,
				currency.getIsocode() == null ? null : currency.getIsocode().toLowerCase(Locale.ROOT));

		if (value != null)
		{
			for (final String fieldName : fieldNames)
			{
				fieldValues.add(new FieldValue(fieldName, value));
			}
		}
	}

}
