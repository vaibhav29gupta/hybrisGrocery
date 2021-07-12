package org.grocery.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.core.model.order.CartModel;

/**
 * Event to indicate an abandoned shopping cart.
 */
public class AbandonedShoppingCartEvent extends AbstractCommerceUserEvent<BaseSiteModel> {

    private CartModel abandonedCart;

    public AbandonedShoppingCartEvent(CartModel abandonedCart) {
        this.abandonedCart = abandonedCart;
    }

    public CartModel getAbandonedCart() {
        return abandonedCart;
    }

    public void setAbandonedCart(CartModel abandonedCart) {
        this.abandonedCart = abandonedCart;
    }
}
