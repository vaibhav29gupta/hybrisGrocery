/**
 *
 */
package org.grocery.facade.storesession;

import de.hybris.platform.commercefacades.storesession.StoreSessionFacade;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.core.model.c2l.CountryModel;


/**
 * @author ankitpandey
 *
 */
public interface GroceryStoreSessionFacade extends StoreSessionFacade
{

	CountryData getLanguageAndCurrencyForCountry(String code);

	void setCurrentCountry(CountryModel currentCountry);

	CountryModel getCurrentCountry();

	CountryModel getCountryForIsoCode(String code);

	Double getLatitudeForCountry();

	Double getLongitudeForCountry();
}
