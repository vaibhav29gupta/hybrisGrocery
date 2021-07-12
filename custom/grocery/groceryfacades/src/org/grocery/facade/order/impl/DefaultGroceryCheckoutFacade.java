package org.grocery.facade.order.impl;

import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.session.SessionService;

import org.grocery.core.enums.ShipmentTypeEnum;
import org.grocery.facade.order.GroceryCheckoutFacade;
import org.grocery.facades.constants.GroceryFacadesConstants;

/**
 * Default implementation for {@link GroceryCheckoutFacade}
 */
public class DefaultGroceryCheckoutFacade extends DefaultAcceleratorCheckoutFacade implements GroceryCheckoutFacade {

  private SessionService sessionService;

  /**
   * Overrides the default implementation to enable custom logic
   * Checks if express checkout is enabled for the store,
   * the customer is logged in and if express checkout is enabled for shipping or for pickup
   *
   * @return boolean flag for allowed/not-allowed
   */
  @Override
  public boolean isExpressCheckoutAllowedForCart() {
    final String shipmentType = getSessionService().getAttribute(GroceryFacadesConstants.SHIPMENTTYPE);
    return isExpressCheckoutEnabledForStore() && !getUserService().isAnonymousUser(getUserService().getCurrentUser())
           ? isGroceryExpressCheckoutAllowedForShippingCart(shipmentType)
             || isGroceryExpressCheckoutAllowedPickupOnlyCart(shipmentType)
           : false;
  }

  /**
   * For delivery express checkout is enable if the shipment type is delivery
   * and the selected postal code is the same as the customer's default shipment address postal code
   *
   * @return boolean flag for allowed/not-allowed
   */
  protected boolean isGroceryExpressCheckoutAllowedForShippingCart(String shipmentType) {
    final String postalCode = getSessionService().getAttribute(GroceryFacadesConstants.POSTALCODE);
    final CustomerModel customerModel = (CustomerModel) getUserService().getCurrentUser();
    final AddressModel defaultShipmentAddress = customerModel.getDefaultShipmentAddress();

    return ShipmentTypeEnum.DELIVERY.toString().equalsIgnoreCase(shipmentType)
           && defaultShipmentAddress != null
           && defaultShipmentAddress.getPostalcode().equalsIgnoreCase(postalCode);
  }

  /**
   * Express checkout is always enabled for pickup shipment
   *
   * @return boolean flag for allowed/not-allowed
   */
  protected boolean isGroceryExpressCheckoutAllowedPickupOnlyCart(String shipmentType) {
    return ShipmentTypeEnum.PICKUP.toString().equalsIgnoreCase(shipmentType);
  }

  /**
   * @return the sessionService
   */
  public SessionService getSessionService() {
    return sessionService;
  }

  /**
   * @param sessionService the sessionService to set
   */
  public void setSessionService(final SessionService sessionService) {
    this.sessionService = sessionService;
  }
}
