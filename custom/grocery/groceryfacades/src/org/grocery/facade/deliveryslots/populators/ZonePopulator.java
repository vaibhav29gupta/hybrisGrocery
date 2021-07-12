package org.grocery.facade.deliveryslots.populators;

import de.hybris.platform.commercefacades.deliveryslot.data.ZoneData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.deliveryzone.model.ZoneModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Set;

import org.springframework.util.Assert;


/**
 * Zone populator
 */
public class ZonePopulator implements Populator<ZoneModel, ZoneData>
{
	private Converter<CountryModel, CountryData> countryConverter;

	/**
	 * @return
	 */
	public Converter<CountryModel, CountryData> getCountryConverter()
	{
		return countryConverter;
	}

	/**
	 * @param countryConverter
	 */
	public void setCountryConverter(final Converter<CountryModel, CountryData> countryConverter)
	{
		this.countryConverter = countryConverter;
	}

	@Override
	public void populate(final ZoneModel source, final ZoneData target)
	{

		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setCode(source.getCode());

		CountryData countryData = new CountryData();

		final Set<CountryModel> countryModelSet = source.getCountries();

		for (final CountryModel countryModel : countryModelSet)
		{
			countryData = getCountryConverter().convert(countryModel);
		}

		target.setCountry(countryData);

	}

}
