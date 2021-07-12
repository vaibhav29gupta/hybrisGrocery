/**
 *
 */
package org.grocery.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
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
import java.util.List;

import org.apache.commons.lang.time.DateUtils;


/**
 * @author nitinkumar09
 *
 */
public class PotentialPromotionValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider
{
	private FieldNameProvider fieldNameProvider;
	private PromotionsService promotionsService;
	private TimeService timeService;


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

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		if (model instanceof ProductModel)
		{
			final ProductModel product = (ProductModel) model;

			final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
			if (indexedProperty.isMultiValue())
			{
				fieldValues.addAll(createFieldValues(product, indexConfig, indexedProperty));
			}
			else
			{
				fieldValues.addAll(createFieldValue(product, indexConfig, indexedProperty));
			}
			return fieldValues;
		}
		else
		{
			throw new FieldValueProviderException("Cannot get promotion codes of non-product item");
		}
	}

	/**
	 * @param product
	 * @param indexConfig
	 * @param indexedProperty
	 * @return
	 */
	private Collection<? extends FieldValue> createFieldValue(final ProductModel product, final IndexConfig indexConfig,
			final IndexedProperty indexedProperty)
	{

		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
		final BaseSiteModel baseSiteModel = indexConfig.getBaseSite();
		final PromotionGroupModel defaultPromotionGroup = baseSiteModel.getDefaultPromotionGroup();
		final Date currentTimeRoundedToMinute = DateUtils.round(getTimeService().getCurrentTime(), Calendar.MINUTE);
		if (baseSiteModel != null && baseSiteModel.getDefaultPromotionGroup() != null)
		{
			for (final AbstractPromotionModel promotion : getPromotionsService().getAbstractProductPromotions(
					Collections.singletonList(defaultPromotionGroup), product, true, currentTimeRoundedToMinute))
			{
				if (promotion instanceof RuleBasedPromotionModel)
				{
					final RuleBasedPromotionModel rbp = (RuleBasedPromotionModel) promotion;
					addFieldValues(fieldValues, indexedProperty, null, rbp.getPromotionDescription());
					break;
				}
				else
				{
					addFieldValues(fieldValues, indexedProperty, null, promotion.getDescription());
					break;
				}
			}
		}
		return fieldValues;
	}

	/**
	 * @param product
	 * @param indexConfig
	 * @param indexedProperty
	 * @return
	 */
	private Collection<? extends FieldValue> createFieldValues(final ProductModel product, final IndexConfig indexConfig,
			final IndexedProperty indexedProperty)
	{
		final List<FieldValue> fieldValues = new ArrayList<>();
		final BaseSiteModel baseSiteModel = indexConfig.getBaseSite();

		if (baseSiteModel != null && baseSiteModel.getDefaultPromotionGroup() != null)
		{
			final PromotionGroupModel defaultPromotionGroup = baseSiteModel.getDefaultPromotionGroup();
			final Date currentTimeRoundedToMinute = DateUtils.round(getTimeService().getCurrentTime(), Calendar.MINUTE);

			for (final AbstractPromotionModel promotion : getPromotionsService().getAbstractProductPromotions(
					Collections.singletonList(defaultPromotionGroup), product, true, currentTimeRoundedToMinute))
			{
				if (promotion instanceof RuleBasedPromotionModel)
				{
					final RuleBasedPromotionModel rbp = (RuleBasedPromotionModel) promotion;
					addFieldValues(fieldValues, indexedProperty, null, rbp.getPromotionDescription());
				}
				else
				{
					addFieldValues(fieldValues, indexedProperty, null, promotion.getDescription());
				}
			}
		}
		return fieldValues;
	}

	protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty,
			final LanguageModel language, final Object value)
	{
		final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty,
				language == null ? null : language.getIsocode());
		for (final String fieldName : fieldNames)
		{
			fieldValues.add(new FieldValue(fieldName, value));
		}
	}

}

