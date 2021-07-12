/**
 *
 */
package org.grocery.core.storesession.impl;

import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.servicelayer.i18n.daos.CountryDao;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.grocery.core.constants.GroceryCoreConstants;
import org.grocery.core.storesession.groceryStoreSessionService;


/**
 * @author ankitpandey
 *
 */
public class DefaultGrocerySessionService implements groceryStoreSessionService
{
	private CountryDao countryDao;
	private final Map<String, Object> attributes = new ConcurrentHashMap<>();
	private SessionService sessionService;
	private BaseStoreService baseStoreService;

	@Override
	public List<CountryModel> getCountryForIsoCode(final String code)
	{
		final List<CountryModel> countryList = getCountryDao().findCountriesByCode(code);
		return CollectionUtils.isNotEmpty(countryList) ? countryList : Collections.EMPTY_LIST;
	}

	@Override
	public void setCurrentCountry(final CountryModel currentCountry)
	{
		this.getSessionService().setAttribute(GroceryCoreConstants.COUNTRY_SESSION_ATTR_KEY, currentCountry);
	}

	@Override
	public CountryModel getCurrentCountry()
	{
		final CountryModel currentCountry = (CountryModel) this.getSessionService()
				.getAttribute(GroceryCoreConstants.COUNTRY_SESSION_ATTR_KEY);
		if (currentCountry == null)
		{
			return getBaseStoreService().getCurrentBaseStore().getDefaultcountry();
		}

		return (CountryModel) this.getSessionService().getAttribute(GroceryCoreConstants.COUNTRY_SESSION_ATTR_KEY);
	}


	public CountryDao getCountryDao()
	{
		return countryDao;
	}

	public void setCountryDao(final CountryDao countryDao)
	{
		this.countryDao = countryDao;
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

	public SessionService getSessionService()
	{
		return sessionService;
	}

	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
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
