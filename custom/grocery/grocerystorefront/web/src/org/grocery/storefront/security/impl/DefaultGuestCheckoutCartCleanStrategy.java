/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.storefront.security.impl;

import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.log4j.Logger;
import org.grocery.storefront.controllers.AbstractGroceryPageController;
import org.grocery.storefront.security.GuestCheckoutCartCleanStrategy;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;


public class DefaultGuestCheckoutCartCleanStrategy implements GuestCheckoutCartCleanStrategy {
    public static final String AJAX_REQUEST_HEADER_NAME = "X-Requested-With";

    private List<String> skipPatterns;
    private CheckoutCustomerStrategy checkoutCustomerStrategy;
    private CartService cartService;
    private SessionService sessionService;
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(DefaultGuestCheckoutCartCleanStrategy.class);

    @Override
    public void cleanGuestCart(final HttpServletRequest request) {
        if (isGuestUser()||isAnonymousCheckout())
			return;

		boolean cleanCartNeeded = StringUtils.isBlank(request.getHeader(AJAX_REQUEST_HEADER_NAME)) && isGetMethod(request)
				&& !checkWhetherURLContainsSkipPattern(request);

		if (cleanCartNeeded) {
			final CartModel cartModel = getCartService().getSessionCart();
			cartModel.setDeliveryAddress(null);
			cartModel.setDeliveryMode(null);
			cartModel.setPaymentInfo(null);
			cartModel.setUser(getUserService().getAnonymousUser());
			getCartService().saveOrder(cartModel);
			getSessionService().removeAttribute(WebConstants.ANONYMOUS_CHECKOUT);
			getSessionService().removeAttribute(WebConstants.ANONYMOUS_CHECKOUT_GUID);
		}

	}

    private boolean isGuestUser() {
        CustomerModel customer = (CustomerModel) getSessionService().getAttribute("user");

        if (customer != null && customer.getType() != null)
            return false;
        return true;
    }


    @Override
    public boolean checkWhetherURLContainsSkipPattern(final HttpServletRequest request) {
        boolean contains = false;
        final String requestUrl = request.getRequestURL().toString();
        for (final String patternStr : skipPatterns) {
            final Pattern pattern = Pattern.compile(patternStr);
            if (pattern.matcher(requestUrl).matches()) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    protected boolean isGetMethod(final HttpServletRequest httpRequest) {
        return "GET".equalsIgnoreCase(httpRequest.getMethod());
    }

    protected CheckoutCustomerStrategy getCheckoutCustomerStrategy() {
        return checkoutCustomerStrategy;
    }

    protected boolean isAnonymousCheckout() {
    return Boolean.TRUE.equals(getSessionService().getAttribute(WebConstants.ANONYMOUS_CHECKOUT))
                && getSessionService().getAttribute(WebConstants.ANONYMOUS_CHECKOUT_GUID) != null;
    }

    @Required
    public void setCheckoutCustomerStrategy(final CheckoutCustomerStrategy checkoutCustomerStrategy) {
        this.checkoutCustomerStrategy = checkoutCustomerStrategy;
    }

    protected CartService getCartService() {
        return cartService;
    }

    @Required
    public void setCartService(final CartService cartService) {
        this.cartService = cartService;
    }

    protected SessionService getSessionService() {
        return sessionService;
    }

    @Required
    public void setSessionService(final SessionService sessionService) {
        this.sessionService = sessionService;
    }

    protected UserService getUserService() {
        return userService;
    }

    @Required
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    public List<String> getSkipPatterns() {
        return skipPatterns;
    }

    @Required
    public void setSkipPatterns(final List<String> skipPatterns) {
        this.skipPatterns = skipPatterns;
    }

}
