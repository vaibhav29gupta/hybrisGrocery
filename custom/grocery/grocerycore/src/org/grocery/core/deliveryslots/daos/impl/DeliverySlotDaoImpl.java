package org.grocery.core.deliveryslots.daos.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.grocery.core.deliveryslots.daos.DeliverySlotDao;
import org.grocery.core.model.DeliverySlotDayConfigModel;
import org.grocery.core.model.DeliverySlotTimeConfigModel;


/**
 * Class for implementing methods of DeliverySlotDao methods for Day
 */
public class DeliverySlotDaoImpl implements DeliverySlotDao
{

	private FlexibleSearchService flexibleSearchService;

	private static final String ORDERINGDATE = "orderingDate";
	private static final String FIND_AVAILABLE_DELIVERY_SLOT_TIME_FOR_CODE_QUERY = "SELECT {pk} FROM {DeliverySlotTimeConfig} WHERE {code}= ?code";
	private static final String CODE = "code";

	@Override
	public List<DeliverySlotDayConfigModel> getDeliverySlotDays(final String monthYear)
	{
		final StringBuilder query = new StringBuilder(
				"SELECT {A:pk} FROM {DeliverySlotDayConfig AS A} WHERE {A:orderingdaystarttime} LIKE '%").append(monthYear)
						.append("%'");
		final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(query.toString());
		return getFlexibleSearchService().<DeliverySlotDayConfigModel> search(flexQuery).getResult();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public DeliverySlotTimeConfigModel findDeliverySlotTimesForCode(final String code)
	{
		DeliverySlotTimeConfigModel deliverySlotTimeConfigModel = null;
		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put(CODE, code);
		final StringBuilder query = new StringBuilder(FIND_AVAILABLE_DELIVERY_SLOT_TIME_FOR_CODE_QUERY);

		final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(query.toString(), queryParams);
		final List<DeliverySlotTimeConfigModel> result = getFlexibleSearchService().<DeliverySlotTimeConfigModel> search(flexQuery)
				.getResult();
		if (CollectionUtils.isNotEmpty(result))
		{
			deliverySlotTimeConfigModel = result.get(0);
		}
		return deliverySlotTimeConfigModel;
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
