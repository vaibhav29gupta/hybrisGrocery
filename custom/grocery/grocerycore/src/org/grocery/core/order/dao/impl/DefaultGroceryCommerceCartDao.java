package org.grocery.core.order.dao.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.order.dao.impl.DefaultCommerceCartDao;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;
import org.grocery.core.order.dao.GroceryCommerceCartDao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultGroceryCommerceCartDao extends DefaultCommerceCartDao implements GroceryCommerceCartDao {

    protected final static String NOT_SAVED_CART_CLAUSE = "AND {" + CartModel.SAVETIME + "} IS NULL ";

    protected final static String FIND_OLD_CARTS_FOR_SITE_AND_USER = SELECTCLAUSE + "WHERE {" + CartModel.USER + "} = ?user AND {"
            + CartModel.MODIFIEDTIME + "} <= ?modifiedBefore AND {" + CartModel.SITE + "} = ?site " + NOT_SAVED_CART_CLAUSE
            + ORDERBYCLAUSE;

    protected final static String FIND_ABANDONED_CART_FOR_SITE = SELECTCLAUSE + "WHERE {" + CartModel.MODIFIEDTIME
            + "} <= ?modifiedBefore AND {" + CartModel.SITE + "} = ?site AND {" + CartModel.USER + "} <> ?user AND {"
            + CartModel.EXPIRATIONTIME + "} <= ?currentDate " + ORDERBYCLAUSE;

    protected final static String FIND_EXPIRED_CARTS_FOR_SITE_AND_USER = SELECTCLAUSE + "WHERE {" + CartModel.MODIFIEDTIME
            + "} <= ?modifiedBefore AND {" + CartModel.SITE + "} = ?site AND {" + CartModel.EXPIRATIONTIME + "} <= ?currentDate " + ORDERBYCLAUSE;

    protected final static String FIND_ABANDONED_CARTS_FOR_SITE_NON_ANONYMOUS_USER = SELECTCLAUSE + " WHERE {" + CartModel.USER + "} <> ?user AND {"
            + CartModel.MODIFIEDTIME + "} <= ?modifiedBefore AND {" + CartModel.SITE + "} = ?site " + NOT_SAVED_CART_CLAUSE + ORDERBYCLAUSE;

    @Override
    public List<CartModel> getAbandonedCartsForSiteAndUser(Date modifiedBefore, BaseSiteModel site, UserModel user) {
        Map<String, Object> params = new HashMap<>();
        params.put("site", site);
        params.put("modifiedBefore", modifiedBefore);
        params.put("user", user);
        return doSearch(FIND_ABANDONED_CARTS_FOR_SITE_NON_ANONYMOUS_USER, params, CartModel.class);
    }
}
