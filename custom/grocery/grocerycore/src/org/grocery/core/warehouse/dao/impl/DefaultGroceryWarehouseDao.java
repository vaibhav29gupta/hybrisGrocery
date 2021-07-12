/**
 *
 */
package org.grocery.core.warehouse.dao.impl;

import de.hybris.platform.ordersplitting.daos.impl.DefaultWarehouseDao;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.grocery.core.model.ServiceabilityAreaModel;
import org.grocery.core.warehouse.dao.GroceryWarehouseDao;


/**
 * @author ankituniyal
 *
 */
public class DefaultGroceryWarehouseDao extends DefaultWarehouseDao implements GroceryWarehouseDao
{
	private static final String WAREHOUSE_FOR_PRODUCT_AND_POSTCODE = "SELECT {W:pk}   from {Product as P "
			+ "   JOIN STOCKLEVEL as S on {S.PRODUCTCODE}={P.CODE}  JOIN WAREHOUSE as W on {S.WAREHOUSE}={W.pk} \r\n"
			+ "   JOIN ServiceabilityArea as SA on {W.pk}={SA.WAREHOUSE} }\r\n"
			+ "   WHERE {P.code}=?productCode AND {S.available} > 0 AND ?postalCode >= {SA.startPostalCode} AND ?postalCode <= {SA.endPostalCode}";

	private static final String STOCKLEVEL_FOR_PRODUCT_AND_POSTCODE = "SELECT {S:pk}   from {Product as P "
			+ "   JOIN STOCKLEVEL as S on {S.PRODUCTCODE}={P.CODE}  JOIN WAREHOUSE as W on {S.WAREHOUSE}={W.pk} \r\n"
			+ "   JOIN ServiceabilityArea as SA on {W.pk}={SA.WAREHOUSE} }\r\n"
			+ "   WHERE {P.code}=?productCode AND {S.available} > 0 AND ?postalCode >= {SA.startPostalCode} AND ?postalCode <= {SA.endPostalCode}";

	private static final String POS_FOR_LOCATION_TEXT_QUERY_1 = "select {p.pk} from {PointOfService as p Join PoS2WarehouseRel AS rel ON {p.pk}={rel.source} "
			+ " Join Warehouse as wh ON {rel.target} = {wh.pk} LEFT JOIN Address as a ON {p.address} = {a.pk} "
			+ " LEFT JOIN  Country as c ON {a.country} = {c.pk}} " + " WHERE {p.pk} not in ({wh.defaultpos}) "
			+ " AND {p.pk} in ({rel.source})" + " AND {p.type}=?type AND {p.basestore} = ?baseStore";

	private static final String POS_FOR_LOCATION_TEXT_QUERY_2 = " AND {c.isocode} = ?currentCountryIso";

	private static final String POS_FOR_LOCATION_TEXT_QUERY_3 = " AND (lower({p.name}) LIKE ?locationText OR lower({p.displayName}) LIKE ?locationText OR lower({c.name}) LIKE ?locationText OR "
			+ " lower({a.town}) like ?locationText OR lower({a.district}) like ?locationText )";

	private static final String SERVICEABILITY_AREA_FOR_POSTAL_CODE_QUERY = "SELECT {S:pk} from {ServiceabilityArea as S} where ?postalCode >= {S.startPostalCode} AND ?postalCode <= {S.endPostalCode} ";

	@Override
	public List<WarehouseModel> getWarehouseForProductAndPost(final String productCode, final long postalCode)
	{
		final Map<String, Object> params = new HashMap<>();
		params.put("productCode", productCode);
		params.put("postalCode", postalCode);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(WAREHOUSE_FOR_PRODUCT_AND_POSTCODE, params);
		final SearchResult<WarehouseModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public List<StockLevelModel> getStockLevelForProductAndPost(final String productCode, final long postalCode)
	{
		final Map<String, Object> params = new HashMap<>();
		params.put("productCode", productCode);
		params.put("postalCode", postalCode);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(STOCKLEVEL_FOR_PRODUCT_AND_POSTCODE, params);
		final SearchResult<StockLevelModel> searchResult = getFlexibleSearchService().search(query);
		return searchResult.getResult();
	}

	@Override
	public Collection<PointOfServiceModel> getPosForLocationText(final Map<String, Object> paramMap)
	{
		if (paramMap.get("currentCountryIso") != null)
		{
			final FlexibleSearchQuery query = new FlexibleSearchQuery(
					POS_FOR_LOCATION_TEXT_QUERY_1 + POS_FOR_LOCATION_TEXT_QUERY_2 + POS_FOR_LOCATION_TEXT_QUERY_3, paramMap);
			final SearchResult<PointOfServiceModel> searchResult = getFlexibleSearchService().search(query);
			return searchResult.getResult();
		}
		else
		{
			final FlexibleSearchQuery query = new FlexibleSearchQuery(POS_FOR_LOCATION_TEXT_QUERY_1 + POS_FOR_LOCATION_TEXT_QUERY_3,
					paramMap);
			final SearchResult<PointOfServiceModel> searchResult = getFlexibleSearchService().search(query);
			return searchResult.getResult();
		}
	}

	@Override
	public ServiceabilityAreaModel getServiceabilityAreaForPostalCode(final long postalCode)
	{
		final Map<String, Object> params = new HashMap<>();
		params.put("postalCode", postalCode);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(SERVICEABILITY_AREA_FOR_POSTAL_CODE_QUERY, params);
		final SearchResult<ServiceabilityAreaModel> searchResult = getFlexibleSearchService().search(query);
		if (searchResult.getCount() > 0)
		{
			return searchResult.getResult().get(0);
		}
		else
		{
			return null;
		}
	}

}
