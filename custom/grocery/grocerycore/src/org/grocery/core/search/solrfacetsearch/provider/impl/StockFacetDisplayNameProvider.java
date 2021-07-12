/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractFacetValueDisplayNameProvider;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.Collection;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.grocery.core.enums.ShipmentTypeEnum;
import org.grocery.core.model.ServiceabilityAreaModel;
import org.grocery.core.warehouse.service.GroceryWarehouseService;
import org.springframework.beans.factory.annotation.Required;


public class StockFacetDisplayNameProvider extends AbstractFacetValueDisplayNameProvider
{
	private EnumerationService enumerationService;

	private SessionService sessionService;

	private GroceryWarehouseService groceryWarehouseService;

	public static final String SHIPMENTTYPE = "shipment-type";
	public static final String POSTALCODE = "postalCode";
	public static final String STOREID = "storeId";
	public static final String STORENAME = "storeName";

	private static final String STOCK_SPLIT_CHAR = "_";
	private static final String STOCK_FOR_ALL = "all";
	private static final String AVAILABLE = "Available";

	@Override
	public String getDisplayName(final SearchQuery query, final IndexedProperty property, final String facetValue)
	{
		if (facetValue == null)
		{
			return StringUtils.EMPTY;
		}

		final String shipmentType = getCurrentShipmentMethod();
		String storeInSession = StringUtils.EMPTY;

		final int index = facetValue.lastIndexOf(STOCK_SPLIT_CHAR);
		final String storeName = facetValue.substring(0, index);

		if (ShipmentTypeEnum.DELIVERY.equals(ShipmentTypeEnum.valueOf(shipmentType)))
		{
			storeInSession = getPostCodeInSession(storeName);
		}
		if (ShipmentTypeEnum.PICKUP.equals(ShipmentTypeEnum.valueOf(shipmentType)))
		{
			storeInSession = getCurrentStoreID();
		}

		String stockStatus = StringUtils.EMPTY;

		if (StringUtils.isBlank(storeInSession) && StringUtils.isBlank(shipmentType))
		{
			if (storeName.equals(STOCK_FOR_ALL))
			{
				stockStatus = facetValue.substring(index + 1);
			}
		}
		else
		{
			if (storeName.equals(storeInSession))
			{
				stockStatus = getEnumerationService().getEnumerationValue(StockLevelStatus.class, facetValue.substring(index + 1))
						.getCode();
			}
		}
		return stockStatus.equals(StockLevelStatus.INSTOCK.getCode()) ? AVAILABLE : StringUtils.EMPTY;
	}

	/**
	 * @return
	 */
	private String getPostCodeInSession(final String storeName)
	{
		String storeInSession = StringUtils.EMPTY;
		final String postCode = getCurrentPostalCode();
		final ServiceabilityAreaModel serviceabilityArea = getGroceryWarehouseService()
				.getServiceabilityAreaForPostalCode(Long.valueOf(postCode));
		final WarehouseModel warehouse = serviceabilityArea.getWarehouse();
		final Collection<PointOfServiceModel> posList = warehouse.getPointsOfService();
		if (CollectionUtils.isNotEmpty(posList))
		{
			final Optional<PointOfServiceModel> posOptional = posList.stream().filter(pos -> pos.getName().equals(storeName))
					.findFirst();
			if (posOptional.isPresent())
			{
				final PointOfServiceModel pos = posOptional.get();
				storeInSession = pos.getName();
			}
		}

		return storeInSession;
	}

	private String getCurrentStoreID()
	{
		final String storeID = getSessionService().getAttribute(STOREID);
		if (null != storeID)
		{
			return storeID;
		}
		return StringUtils.EMPTY;
	}

	private String getCurrentPostalCode()
	{
		final String postalCode = getSessionService().getAttribute(POSTALCODE);
		if (null != postalCode)
		{
			return postalCode;
		}
		return StringUtils.EMPTY;
	}

	private String getCurrentShipmentMethod()
	{
		final String shipmentType = getSessionService().getAttribute(SHIPMENTTYPE);
		if (null != shipmentType)
		{
			return shipmentType;
		}
		return StringUtils.EMPTY;
	}


	/**
	 * @return the groceryWarehouseService
	 */
	public GroceryWarehouseService getGroceryWarehouseService()
	{
		return groceryWarehouseService;
	}

	/**
	 * @param groceryWarehouseService
	 *           the groceryWarehouseService to set
	 */
	public void setGroceryWarehouseService(final GroceryWarehouseService groceryWarehouseService)
	{
		this.groceryWarehouseService = groceryWarehouseService;
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	protected EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	@Required
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

}
