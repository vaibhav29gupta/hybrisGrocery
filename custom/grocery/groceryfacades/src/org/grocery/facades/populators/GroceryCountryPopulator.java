/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.storesession.data.CurrencyData;
import de.hybris.platform.commercefacades.storesession.data.LanguageData;
import de.hybris.platform.commercefacades.user.converters.populator.CountryPopulator;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.store.services.BaseStoreService;

/**
 * @author ankitpandey
 *
 */
public class GroceryCountryPopulator extends CountryPopulator
{
	private Converter<CurrencyModel, CurrencyData> currencyConverter;
	private Converter<LanguageModel, LanguageData> languageConverter;
	private BaseStoreService baseStoreService;

	@Override
	public void populate(final CountryModel source, final CountryData target)
	{
		if (source != null)
		{
			if (null != source.getCurrency())
			{

				target.setDefaultcurrency(getCurrencyConverter().convert(source.getCurrency()));
			}
			else
			{
				final CurrencyModel defaultBaseStoreCountryCurrency = getBaseStoreService().getCurrentBaseStore().getDefaultcountry()
						.getCurrency();
				target.setDefaultcurrency(getCurrencyConverter().convert(defaultBaseStoreCountryCurrency));
			}
			if (null != source.getLanguage())
			{
				target.setDefaultlanguage(getLanguageConverter().convert(source.getLanguage()));
			}
			else
			{
				final LanguageModel defaultBaseStoreCountryLanguage = getBaseStoreService().getCurrentBaseStore().getDefaultcountry()
						.getLanguage();
				target.setDefaultlanguage(getLanguageConverter().convert(defaultBaseStoreCountryLanguage));
			}
		}
	}

	public Converter<CurrencyModel, CurrencyData> getCurrencyConverter()
	{
		return currencyConverter;
	}

	public void setCurrencyConverter(final Converter<CurrencyModel, CurrencyData> currencyConverter)
	{
		this.currencyConverter = currencyConverter;
	}

	public Converter<LanguageModel, LanguageData> getLanguageConverter()
	{
		return languageConverter;
	}

	public void setLanguageConverter(final Converter<LanguageModel, LanguageData> languageConverter)
	{
		this.languageConverter = languageConverter;
	}

	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}
}
