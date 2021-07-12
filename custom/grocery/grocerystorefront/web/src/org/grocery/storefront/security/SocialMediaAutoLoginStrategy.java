package org.grocery.storefront.security;

import de.hybris.platform.acceleratorstorefrontcommons.security.AutoLoginStrategy;
import de.hybris.platform.acceleratorstorefrontcommons.security.GUIDCookieStrategy;
import de.hybris.platform.acceleratorstorefrontcommons.strategy.CartRestorationStrategy;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.UserManager;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

/**
 * Custom @{@link AutoLoginStrategy} for handling the login for customers logging in through the Social Media (e.g. Facebook, Google).
 */
public class SocialMediaAutoLoginStrategy implements AutoLoginStrategy {

    private static final Logger LOGGER = Logger.getLogger(SocialMediaAutoLoginStrategy.class);

    private CustomerFacade customerFacade;
    private GUIDCookieStrategy guidCookieStrategy;
    private RememberMeServices rememberMeServices;
    private CartRestorationStrategy cartRestorationStrategy;

    @Override
    public void login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null);
        WebAuthenticationDetails webAuthenticationDetails = new WebAuthenticationDetails(request);
        token.setDetails(webAuthenticationDetails);
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMERGROUP")));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            JaloSession.getCurrentSession().setUser(UserManager.getInstance().getUserByLogin(username));
            getCustomerFacade().loginSuccess();
            getGuidCookieStrategy().setCookie(request, response);
            getCartRestorationStrategy().restoreCart(request);
            getRememberMeServices().loginSuccess(request, response, token);
        } catch (Exception exception) {
            SecurityContextHolder.getContext().setAuthentication(null);
            LOGGER.error("Failure during login", exception);
        }
    }

    public CustomerFacade getCustomerFacade() {
        return customerFacade;
    }

    public void setCustomerFacade(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    public GUIDCookieStrategy getGuidCookieStrategy() {
        return guidCookieStrategy;
    }

    public void setGuidCookieStrategy(GUIDCookieStrategy guidCookieStrategy) {
        this.guidCookieStrategy = guidCookieStrategy;
    }

    public RememberMeServices getRememberMeServices() {
        return rememberMeServices;
    }

    public void setRememberMeServices(RememberMeServices rememberMeServices) {
        this.rememberMeServices = rememberMeServices;
    }

    public CartRestorationStrategy getCartRestorationStrategy() {
        return cartRestorationStrategy;
    }

    public void setCartRestorationStrategy(CartRestorationStrategy cartRestorationStrategy) {
        this.cartRestorationStrategy = cartRestorationStrategy;
    }
}
