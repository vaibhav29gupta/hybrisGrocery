/**
 *
 */
package org.grocery.core.storesession;

import de.hybris.platform.commerceservices.storesession.StoreSessionService;
import de.hybris.platform.core.model.c2l.CountryModel;

import java.util.List;


/**
 * @author ankitpandey
 *
 */
public interface groceryStoreSessionService extends StoreSessionService
{
	List<CountryModel> getCountryForIsoCode(String code);

	void setCurrentCountry(CountryModel currentCountry);

	CountryModel getCurrentCountry();
}
