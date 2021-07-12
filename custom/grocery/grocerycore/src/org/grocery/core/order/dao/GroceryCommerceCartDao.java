package org.grocery.core.order.dao;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.Date;
import java.util.List;

/**
 *
 */
public interface GroceryCommerceCartDao {

    /**
     * @param modifiedBefore
     * @param site
     * @param user
     * @return
     */
    List<CartModel> getAbandonedCartsForSiteAndUser(Date modifiedBefore, BaseSiteModel site, UserModel user);
}
