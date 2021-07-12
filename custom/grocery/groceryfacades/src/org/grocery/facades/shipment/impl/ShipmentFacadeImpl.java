/**
 *
 */
package org.grocery.facades.shipment.impl;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.serviceability.data.ServiceabilityAreaData;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.core.Constants.USER;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.storelocator.pos.PointOfServiceService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.grocery.core.enums.ShipmentTypeEnum;
import org.grocery.core.model.ServiceabilityAreaModel;
import org.grocery.core.shipment.ShipmentService;
import org.grocery.core.warehouse.service.GroceryStoreFinderService;
import org.grocery.core.warehouse.service.GroceryWarehouseService;
import org.grocery.facade.storesession.GroceryStoreSessionFacade;
import org.grocery.facades.constants.GroceryFacadesConstants;
import org.grocery.facades.shipment.ShipmentFacade;
import org.springframework.beans.factory.annotation.Required;


public class ShipmentFacadeImpl implements ShipmentFacade
{
	private static final Logger LOG = Logger.getLogger(ShipmentFacadeImpl.class);

	private static final String STATUS_MSG = "statusMsg";

	private SessionService sessionService;

	private GroceryWarehouseService groceryWarehouseService;

	private Converter<ServiceabilityAreaModel, ServiceabilityAreaData> serviceabilityAreaConverter;

	private PointOfServiceService pointOfServiceService;

	private Converter<ProductModel, ProductData> productConverter;

	private ShipmentService shipmentService;

	private GroceryStoreFinderService groceryStoreFinderService;

	private BaseStoreService baseStoreService;

	private UserService userService;

	private ModelService modelService;
	
	@Resource(name = "groceryStoreSessionFacade")
	private GroceryStoreSessionFacade groceryStoreSessionFacade;


	@Override
	public Boolean isValidStoreID(final String storeID)
	{
		try
		{
			final PointOfServiceModel pos = pointOfServiceService.getPointOfServiceForName(storeID);
			if (null != pos && CollectionUtils.isNotEmpty(pos.getWarehouses()))
			{
				return Boolean.TRUE;
			}
		}
		catch (final Exception E)
		{
			return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean isValidPostalCode(final String postalCode)
	{
		try
		{
			final ServiceabilityAreaData serviceabilityAreaData = getServiceabilityAreaForPostalCode(Long.parseLong(postalCode));
			
			if (null != serviceabilityAreaData)
			{
				return Boolean.TRUE;
			}
		}
		catch (final Exception E)
		{
			return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}

	@Override
	public ServiceabilityAreaData getServiceabilityAreaForPostalCode(final long postalCode)
	{
		final CountryModel country = groceryStoreSessionFacade.getCurrentCountry();
		final ServiceabilityAreaModel serviceabilityArea = getGroceryWarehouseService()
				.getServiceabilityAreaForPostalCode(postalCode);
		if (null != serviceabilityArea)
		{
			final ServiceabilityAreaData serviceabilityAreaData = getServiceabilityAreaConverter().convert(serviceabilityArea);

			if (null != serviceabilityAreaData && null != serviceabilityAreaData.getWarehouseCode() && 
					serviceabilityAreaData.getCountry().getIsocode().equals(country.getIsocode()))
			{
				return serviceabilityAreaData;
			}
		}
		return null;
	}

	@Override
	public Boolean setPostalCode(final long postalCode)
	{
		final ServiceabilityAreaData serviceabilityArea = getServiceabilityAreaForPostalCode(postalCode);
		if (null != serviceabilityArea)
		{
			final String defaultPosForPostalCode = serviceabilityArea.getDefaultPos();
			if (null != defaultPosForPostalCode && isValidStoreID(defaultPosForPostalCode) && updatePOS(defaultPosForPostalCode))
			{
				getSessionService().setAttribute(GroceryFacadesConstants.STOREID, defaultPosForPostalCode);
				getSessionService().setAttribute(GroceryFacadesConstants.SHIPMENTTYPE, ShipmentTypeEnum.DELIVERY.toString());
				getSessionService().setAttribute(GroceryFacadesConstants.POSTALCODE, String.valueOf(postalCode));

				getSessionService().removeAttribute(GroceryFacadesConstants.STORENAME);
				setShipmentInCustomerProfile(ShipmentTypeEnum.DELIVERY.toString(), String.valueOf(postalCode), StringUtils.EMPTY);
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean setStoreID(final String storeID)
	{
		if (isValidStoreID(storeID) && updatePOS(storeID))
		{
			try
			{
				final BaseStoreModel currentBaseStore = getBaseStoreService().getCurrentBaseStore();
				final PointOfServiceModel pointOfService = getGroceryStoreFinderService().getPointOfServiceForName(currentBaseStore,
						storeID);
				getSessionService().setAttribute(GroceryFacadesConstants.SHIPMENTTYPE, ShipmentTypeEnum.PICKUP.toString());
				getSessionService().setAttribute(GroceryFacadesConstants.STORENAME,
						null != pointOfService.getDisplayName() ? pointOfService.getDisplayName() : StringUtils.EMPTY);
				getSessionService().setAttribute(GroceryFacadesConstants.STOREID, storeID);

				getSessionService().removeAttribute(GroceryFacadesConstants.POSTALCODE);
				setShipmentInCustomerProfile(ShipmentTypeEnum.PICKUP.toString(), StringUtils.EMPTY, storeID);
				return Boolean.TRUE;
			}
			catch (final ModelNotFoundException e)
			{
				LOG.debug(e.getMessage());
			}

		}
		return Boolean.FALSE;
	}

	@Override
	public List<ProductData> getAffectedCartProducts(final String locationText, final String shipmentType)
	{
		if (shipmentService.isCartEmpty())
		{
			return Collections.EMPTY_LIST;
		}
		if (shipmentType.equalsIgnoreCase(ShipmentTypeEnum.DELIVERY.getCode()))
		{
			return getUnAvailableProductsForPostCode(locationText);
		}
		else if (shipmentType.equalsIgnoreCase(ShipmentTypeEnum.PICKUP.getCode()))
		{
			return getUnAvailableProductsForStore(locationText);
		}
		return Collections.EMPTY_LIST;
	}

	@Override
	public Boolean checkValidity(final String locationText, final String shipmentType)
	{
		try
		{
			if (shipmentType.equalsIgnoreCase(ShipmentTypeEnum.DELIVERY.getCode()))
			{
				final ServiceabilityAreaData serviceabilityAreaData = getServiceabilityAreaForPostalCode(
						Long.parseLong(locationText));
				if (null != serviceabilityAreaData)
				{
					return Boolean.TRUE;
				}
			}
			else if (shipmentType.equalsIgnoreCase(ShipmentTypeEnum.PICKUP.getCode()))
			{
				return isValidStoreID(locationText);
			}
			return Boolean.FALSE;
		}
		catch (final NumberFormatException e)
		{
			LOG.debug(e);
			return Boolean.FALSE;
		}

	}


	@Override
	public List<ProductData> getUnAvailableProductsForPostCode(final String postalCode)
	{
		try
		{
			final ServiceabilityAreaData serviceabilityAreaData = getServiceabilityAreaForPostalCode(Long.parseLong(postalCode));
			LOG.debug("Getting the unavailable products, if available for postal code " + postalCode);
			return getProductList(serviceabilityAreaData.getDefaultPos());
		}
		catch (final NumberFormatException e)
		{
			LOG.debug(e);
			return Collections.emptyList();
		}

	}

	@Override
	public List<ProductData> getUnAvailableProductsForStore(final String storeID)
	{
		LOG.debug("Getting the unavailable products, if available for store " + storeID);
		return getProductList(storeID);
	}

	/**
	 * @return
	 */
	private List<ProductData> getProductList(final String storeID)
	{
		final List<ProductModel> productModels = shipmentService.getUnavailableProducts(storeID);
		final List<ProductData> productDataList = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(productModels))
		{
			for (final ProductModel productModel : productModels)
			{
				productDataList.add(productConverter.convert(productModel));
			}
			LOG.debug("Total products unavailable ::" + productDataList.size());
			return productDataList;
		}
		else
		{
			return Collections.emptyList();
		}

	}

	@Override
	public Boolean validateShipmentSelection(final String shipmentType, final String postalCode, final String pos)
	{
		Boolean isShipmentModeValid = true;

		try
		{
			if (shipmentType.equalsIgnoreCase(ShipmentTypeEnum.DELIVERY.toString()))
			{
				isShipmentModeValid = (postalCode == null || postalCode.isEmpty()) ? Boolean.FALSE : isValidPostalCode(postalCode);
			}

			else if (shipmentType.equalsIgnoreCase(ShipmentTypeEnum.PICKUP.toString()))
			{
				isShipmentModeValid = (pos == null || pos.isEmpty()) ? Boolean.FALSE : isValidStoreID(pos);
			}
			return isShipmentModeValid;
		}
		catch (final Exception e)
		{
			LOG.error("Error occurred while validating shipment selection", e);
			return false;
		}
	}

	@Override
	public void setShipmentTypeInSession(final String customerShipmentType, final String postalCode, final String pos)
	{
		try
		{
			final String shipmentType = getSessionService().getAttribute(GroceryFacadesConstants.SHIPMENTTYPE);

			if (shipmentType == null || shipmentType.isEmpty())
			{
				if (customerShipmentType.equalsIgnoreCase(ShipmentTypeEnum.DELIVERY.toString()))
				{
					setPostalCode(Long.valueOf(postalCode));
				}
				else if (customerShipmentType.equalsIgnoreCase(ShipmentTypeEnum.PICKUP.toString()))
				{
					setStoreID(pos);
				}
				setShipmentInCustomerProfile(customerShipmentType, postalCode, pos);
			}

		}
		catch (final Exception e)
		{
			LOG.error("Error occurred while setting data in session", e);
		}
	}

	@Override
	public void setShipmentInCustomerProfile(final String customerShipmentType,
			final String postalCode, final String pos)
	{
		try
		{
			final UserModel user = getUserService().getCurrentUser();
			final boolean anonymous = USER.ANONYMOUS_CUSTOMER.equals(user.getUid());
			if (!anonymous)
			{
				final CustomerModel customer = getUserService().getUserForUID(user.getUid(), CustomerModel.class);
				if (null != customer)
				{
					if (customerShipmentType.equalsIgnoreCase(ShipmentTypeEnum.DELIVERY.toString()))
					{
						customer.setCurrentPostalCode(postalCode);
						customer.setCurrentShipmentMode(ShipmentTypeEnum.DELIVERY);
					}
					else if (customerShipmentType.equalsIgnoreCase(ShipmentTypeEnum.PICKUP.toString()))
					{
						customer.setCurrentPos(pos);
						customer.setCurrentShipmentMode(ShipmentTypeEnum.PICKUP);
					}
					getModelService().save(customer);
				}
			}

		}
		catch (final Exception e)
		{
			LOG.error("Error occurred while setting data in session", e);
		}
	}

	@Override
	public String getStoreName(final String storeId)
	{
		try
		{

			final PointOfServiceModel pos = pointOfServiceService.getPointOfServiceForName(storeId);

			if (null != pos && StringUtils.isNotEmpty(pos.getDisplayName()))
			{
				return pos.getDisplayName();
			}
		}
		catch (final Exception e)
		{
			LOG.error("Error occurred while getting store name", e);
			return null;
		}
		return null;
	}

	@Override
	public Boolean updatePOS(final String storeName)
	{
		try
		{
			final List<CommerceCartModification> commerceCartModificationData = shipmentService.updateEntriesForPOS(storeName);
			return Boolean.TRUE;
		}
		catch (final CommerceCartModificationException e)
		{
			return Boolean.FALSE;
		}
	}

	public String getCurrentPostalCode()
	{
		final String postalCode = getSessionService().getAttribute(GroceryFacadesConstants.POSTALCODE);
		if (null != postalCode)
		{
			return postalCode;
		}
		return StringUtils.EMPTY;
	}

	public String getCurrentStoreID()
	{
		final String storeID = getSessionService().getAttribute(GroceryFacadesConstants.STOREID);
		if (null != storeID)
		{
			return storeID;
		}
		return StringUtils.EMPTY;
	}

	public String getCurrentShipmentMethod()
	{
		final String shipmentType = getSessionService().getAttribute(GroceryFacadesConstants.SHIPMENTTYPE);
		if (null != shipmentType)
		{
			return shipmentType;
		}
		return StringUtils.EMPTY;
	}

	public String getCurrentStoreName()
	{
		final String storeName = getSessionService().getAttribute(GroceryFacadesConstants.STORENAME);
		if (null != storeName)
		{
			return storeName;
		}
		return StringUtils.EMPTY;
	}


	/**
	 * @return the serviceabilityAreaConverter
	 */
	public Converter<ServiceabilityAreaModel, ServiceabilityAreaData> getServiceabilityAreaConverter()
	{
		return serviceabilityAreaConverter;
	}

	/**
	 * @param serviceabilityAreaConverter
	 *           the serviceabilityAreaConverter to set
	 */
	public void setServiceabilityAreaConverter(
			final Converter<ServiceabilityAreaModel, ServiceabilityAreaData> serviceabilityAreaConverter)
	{
		this.serviceabilityAreaConverter = serviceabilityAreaConverter;
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
	 * @return the pointOfServiceService
	 */
	public PointOfServiceService getPointOfServiceService()
	{
		return pointOfServiceService;
	}

	/**
	 * @param pointOfServiceService
	 *           the pointOfServiceService to set
	 */
	public void setPointOfServiceService(final PointOfServiceService pointOfServiceService)
	{
		this.pointOfServiceService = pointOfServiceService;
	}

	protected Converter<ProductModel, ProductData> getProductConverter()
	{
		return productConverter;
	}

	@Required
	public void setProductConverter(final Converter<ProductModel, ProductData> productConverter)
	{
		this.productConverter = productConverter;
	}

	/**
	 * @return the shipmentService
	 */
	public ShipmentService getShipmentService()
	{
		return shipmentService;
	}

	/**
	 * @param shipmentService
	 *           the shipmentService to set
	 */
	public void setShipmentService(final ShipmentService shipmentService)
	{
		this.shipmentService = shipmentService;
	}

	/**
	 * @return the groceryStoreFinderService
	 */
	public GroceryStoreFinderService getGroceryStoreFinderService()
	{
		return groceryStoreFinderService;
	}

	/**
	 * @param groceryStoreFinderService
	 *           the groceryStoreFinderService to set
	 */
	public void setGroceryStoreFinderService(final GroceryStoreFinderService groceryStoreFinderService)
	{
		this.groceryStoreFinderService = groceryStoreFinderService;
	}

	/**
	 * @return the baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService
	 *           the baseStoreService to set
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}










}
