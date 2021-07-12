/**
 *
 */
package org.grocery.facade.storesession.impl;

import de.hybris.platform.commercefacades.storesession.data.CurrencyData;
import de.hybris.platform.commercefacades.storesession.data.LanguageData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.grocery.core.storesession.groceryStoreSessionService;
import org.grocery.facade.storesession.GroceryStoreSessionFacade;


/**
 * @author ankitpandey
 *
 */
public class DefaultGroceryStoreSessionFacade implements GroceryStoreSessionFacade
{
	private groceryStoreSessionService groceryStoreSessionService;
	private Converter<CountryModel, CountryData> countryConverter;

	@Override
	public CountryData getLanguageAndCurrencyForCountry(final String code)
	{
		final List<CountryModel> countries = getGroceryStoreSessionService().getCountryForIsoCode(code);
		final CountryModel currentCountry = countries.get(0);

		final CountryData countryData = getCountryConverter().convert(currentCountry);
		return countryData;
	}

	@Override
	public CountryModel getCountryForIsoCode(final String code)
	{
		final List<CountryModel> countries = getGroceryStoreSessionService().getCountryForIsoCode(code);
		final CountryModel currentCountry = countries.get(0);
		return currentCountry;
	}

	@Override
	public Double getLatitudeForCountry()
   {
		final CountryModel country = getGroceryStoreSessionService().getCurrentCountry();
		final double latitude = 0;
		if (null != country && country.getLatitude() != null)
		{
			return country.getLatitude();
		}
		return latitude;
   }



	@Override
	public Double getLongitudeForCountry()
   {
       final CountryModel country = getGroceryStoreSessionService().getCurrentCountry();
		double longitude = 0;
		if (null != country && country.getLongitude() != null)
       {
			longitude = country.getLongitude();
       }
		return longitude;
   }

	@Override
	public void setCurrentCountry(final CountryModel currentCountry)
	{
		this.getGroceryStoreSessionService().setCurrentCountry(currentCountry);
	}

	@Override
	public CountryModel getCurrentCountry()
	{
		return this.getGroceryStoreSessionService().getCurrentCountry();
	}

	@Override
	public LanguageData getCurrentLanguage()
	{
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public CurrencyData getCurrentCurrency()
	{
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public Collection<LanguageData> getAllLanguages()
	{
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CurrencyData> getAllCurrencies()
	{
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public void setCurrentLanguage(final String isocode)
	{
		// XXX Auto-generated method stub

	}

	@Override
	public void setCurrentCurrency(final String isocode)
	{
		// XXX Auto-generated method stub

	}

	@Override
	public LanguageData getDefaultLanguage()
	{
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public CurrencyData getDefaultCurrency()
	{
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public void initializeSession(final List<Locale> preferredLocales)
	{
		// XXX Auto-generated method stub

	}

	public groceryStoreSessionService getGroceryStoreSessionService()
	{
		return groceryStoreSessionService;
	}

	public void setGroceryStoreSessionService(final groceryStoreSessionService groceryStoreSessionService)
	{
		this.groceryStoreSessionService = groceryStoreSessionService;
	}

	public Converter<CountryModel, CountryData> getCountryConverter()
	{
		return countryConverter;
	}

	public void setCountryConverter(final Converter<CountryModel, CountryData> countryConverter)
	{
		this.countryConverter = countryConverter;
	}


}
