/**
 *
 */
package org.grocery.core.warehouse.dao.impl;

import de.hybris.platform.basecommerce.enums.PointOfServiceTypeEnum;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.GPS;
import de.hybris.platform.storelocator.exception.GeoLocatorException;
import de.hybris.platform.storelocator.exception.PointOfServiceDaoException;
import de.hybris.platform.storelocator.impl.DefaultPointOfServiceDao;
import de.hybris.platform.storelocator.impl.GeometryUtils;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.List;

import org.apache.log4j.Logger;


/**
 * @author divyanshusingh
 *
 */
public class GroceryPointOfServiceDao extends DefaultPointOfServiceDao
{

	private static final Logger LOG = Logger.getLogger(GroceryPointOfServiceDao.class);

	protected String buildQueryString(final BaseStoreModel baseStore)
	{
		final StringBuilder query = new StringBuilder(163);
		query.append("SELECT {" + PointOfServiceModel.PK + "} FROM {").append(PointOfServiceModel._TYPECODE).append("} WHERE {")
				.append(PointOfServiceModel.LATITUDE).append("} IS NOT null AND {").append(PointOfServiceModel.LONGITUDE)
				.append("} IS NOT null AND {").append(PointOfServiceModel.LATITUDE).append("} >= ?latMin AND {")
				.append(PointOfServiceModel.LATITUDE).append("} <= ?latMax AND {").append(PointOfServiceModel.LONGITUDE)
				.append("} >= ?lonMin AND {").append(PointOfServiceModel.LONGITUDE).append("} <= ?lonMax AND {")
				.append(PointOfServiceModel.TYPE).append("} = ?storeType AND {").append(PointOfServiceModel.PK)
				.append("} NOT IN ({{ SELECT {").append(WarehouseModel.DEFAULTPOS).append("} FROM {").append(WarehouseModel._TYPECODE)
				.append("}}})");

		if (baseStore != null)
		{
			query.append(" AND {").append(PointOfServiceModel.BASESTORE).append("} = ?baseStore");
		}

		return query.toString();
	}

	@Override
	protected FlexibleSearchQuery buildQuery(final GPS center, final double radius, final BaseStoreModel baseStore)
			throws PointOfServiceDaoException
	{
		try
		{
			final List<GPS> corners = GeometryUtils.getSquareOfTolerance(center, radius);
			if (corners == null || corners.isEmpty() || corners.size() != 2)
			{
				throw new PointOfServiceDaoException("Could not fetch locations from database. Unexpected neighborhood");
			}

			final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(buildQueryString(baseStore));
			fQuery.addQueryParameter("latMax", Double.valueOf(corners.get(1).getDecimalLatitude()));
			fQuery.addQueryParameter("latMin", Double.valueOf(corners.get(0).getDecimalLatitude()));
			fQuery.addQueryParameter("lonMax", Double.valueOf(corners.get(1).getDecimalLongitude()));
			fQuery.addQueryParameter("lonMin", Double.valueOf(corners.get(0).getDecimalLongitude()));
			fQuery.addQueryParameter("storeType", PointOfServiceTypeEnum.STORE);
			if (baseStore != null)
			{
				fQuery.addQueryParameter("baseStore", baseStore);
			}

			return fQuery;
		}
		catch (final GeoLocatorException e)
		{
			throw new PointOfServiceDaoException("Could not fetch locations from database, due to :" + e.getMessage(), e);
		}
	}
}
