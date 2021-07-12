/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.serviceability.data.ServiceabilityAreaData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.grocery.core.model.ServiceabilityAreaModel;


/**
 * @author ankituniyal
 *
 */
public class ServiceabilityAreaPopulator implements Populator<ServiceabilityAreaModel, ServiceabilityAreaData>
{
	private Converter<CountryModel, CountryData> countryConverter;

	/**
	 * @return the countryConverter
	 */
	public Converter<CountryModel, CountryData> getCountryConverter()
	{
		return countryConverter;
	}

	/**
	 * @param countryConverter
	 *           the countryConverter to set
	 */
	public void setCountryConverter(final Converter<CountryModel, CountryData> countryConverter)
	{
		this.countryConverter = countryConverter;
	}

	@Override
	public void populate(final ServiceabilityAreaModel source, final ServiceabilityAreaData target) throws ConversionException
	{
		final String code = source.getCode();
		target.setCode(code);
		
		final String startPostalCode = source.getStartPostalCode().toString();
		target.setStartPostalCode(startPostalCode);

		final String endPostalCode = source.getEndPostalCode().toString();
		target.setEndPostalCode(endPostalCode);

		final RegionModel region = source.getRegion();
		if(null != region) {
			target.setRegion(region.getName());
			target.setRegionCode(region.getIsocode());
			final CountryModel countryModel = region.getCountry();
			CountryData countryData = null;
			if (countryModel != null)
			{
				countryData = countryConverter.convert(countryModel);
			}
			target.setCountry(countryData);
		}

		final WarehouseModel warehouse = source.getWarehouse();
		if(null != warehouse) {
			target.setWarehouseCode(warehouse.getCode());
			final PointOfServiceModel defaultPos=warehouse.getDefaultPOS();
			if(null != defaultPos) {
				target.setDefaultPos(defaultPos.getName());
			}
		}
	}
}
