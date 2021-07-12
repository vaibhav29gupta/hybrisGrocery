/**
 *
 */
package org.grocery.core.thresholdcheck.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.grocery.core.model.FeatureThresholdModel;
import org.grocery.core.model.ThresholdAuditModel;
import org.grocery.core.thresholdcheck.dao.ThresholdAuditDao;


/**
 * @author amitsharma09
 *
 */
public class ThresholdAuditDaoImpl implements ThresholdAuditDao
{
	private FlexibleSearchService flexibleSearchService;

	private static final String GET_THRESHOLD_AUDIT = "select {th.pk} from {ThresholdAudit as th JOIN FeatureThreshold as ft "
			+ " ON {ft.pk}={th.featureThreshold}} "
			+ " Where {ft.feature} = ({{select {t.pk} from {DependentFeature as t} WHERE {t.code} = ?feature}}) "
			+ " AND {th.month} = ?month";

	private static final String GET_MONTHLY_THRESHOLD = "SELECT {ft.pk} FROM {FeatureThreshold as ft} "
			+ " WHERE {ft.feature}= ({{select {t.pk} from {DependentFeature as t} WHERE {t.code} = ?feature}})";


	@Override
	public List<ThresholdAuditModel> getThresholdAudit(final String month, final String feature)
	{

		final Map<String, Object> params = new HashMap<>();

		params.put("month", month);
		params.put("feature", feature);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(GET_THRESHOLD_AUDIT, params);
		final SearchResult<ThresholdAuditModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public List<FeatureThresholdModel> getMonthlyThreshold(final String feature)
	{
		final Map<String, Object> params = new HashMap<>();

		params.put("feature", feature);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(GET_MONTHLY_THRESHOLD, params);
		final SearchResult<FeatureThresholdModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
