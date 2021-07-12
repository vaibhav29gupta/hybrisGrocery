package org.grocery.storefront.socialmedia.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import de.hybris.platform.acceleratorstorefrontcommons.security.AutoLoginStrategy;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.grocery.facades.security.SocialMediaLoginFacade;
import org.grocery.facades.shipment.ShipmentFacade;
import org.grocery.facades.wishlist.WishListFacade;
import org.grocery.storefront.controllers.AbstractGroceryLoginPageController;
import org.grocery.storefront.controllers.ControllerConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

/**
 * Handles the post (frontend) Facebook / Google Login actions:
 * <ul>
 *     <li>Identify whether there is a new customer with Facebook / Google login.</li>
 *     <li>In case of a new customer, a new Hybris @{@link CustomerModel} is created.</li>
 *     <li>Perform the login operation.</li>
 *     <li>Return the next page location.</li>
 * </ul>
 */
@Controller
public class SocialMediaLoginController extends AbstractGroceryLoginPageController {

    private static final Logger LOGGER = Logger.getLogger(SocialMediaLoginController.class);
    private static final String FACEBOOK_RESPONSE_NAME_FIELD = "response[name]";
    private static final String FACEBOOK_RESPONSE_EMAIL_FIELD = "response[email]";
    private static final String GOOGLE_RESPONSE_ID_TOKEN = "id_token";

    private static final HttpTransport transport = new NetHttpTransport();
    private static final JsonFactory jsonFactory = new JacksonFactory();

    @Resource(name = "socialMediaLoginFacade")
    private SocialMediaLoginFacade socialMediaLoginFacade;

    @Resource(name = "socialMediaAutoLoginStrategy")
    private AutoLoginStrategy socialMediaAutoLoginStrategy;
    
    @Resource
    private ShipmentFacade shipmentFacade;
    
    @Resource
    private WishListFacade wishListFacade;

    @ResponseBody
    @RequestMapping(value = "/login/facebook", method = RequestMethod.POST)
    public String doLoginWithFacebook(
            @RequestBody final MultiValueMap<String, String> bodyParameterMap,
            @RequestParam(value = "error", defaultValue = "false") final boolean loginError,
            final Model model, final HttpServletRequest request, final HttpServletResponse response, final HttpSession session) throws CMSItemNotFoundException {
        // TODO: ana.neatu Error handling.
        LOGGER.info("Facebook Logged In user: " + bodyParameterMap.toString());
        String name = bodyParameterMap.getFirst(FACEBOOK_RESPONSE_NAME_FIELD);
        String email = bodyParameterMap.getFirst(FACEBOOK_RESPONSE_EMAIL_FIELD);

        ServicesUtil.validateParameterNotNullStandardMessage("name", name);
        ServicesUtil.validateParameterNotNullStandardMessage("email", email);

        try {
            CustomerData customer=getCustomerFacade().getUserForUID(email);
            if (null != customer)
				{
            	shipmentFacade.setShipmentTypeInSession(customer.getCurrentShipmentMode(),
 							customer.getCurrentPostalCode(),customer.getCurrentPos());
				}
            LOGGER.info("Facebook Logged In user already registered. Updating ...");
            // TODO: ana.neatu Decide whether there are any details that can be updated in Hybris (maybe name? uid update?)
            LOGGER.info("Facebook Logged In user already registered. Logging in ...");
            getSocialMediaAutoLoginStrategy().login(email, null, request, response);
        } catch (UnknownIdentifierException exception) {
            LOGGER.info("Facebook Logged In user not registered. Registering ...");

            try {
                getSocialMediaLoginFacade().createNewCustomer(name, email);
                LOGGER.info("Facebook Logged In user already registered. Logging in ...");
                getSocialMediaAutoLoginStrategy().login(email, null, request, response);
                wishListFacade.createDefaultWishlistForNewUser();
            } catch (DuplicateUidException duplicateUidException) {
                LOGGER.warn("Facebook Logged In user already exists!", duplicateUidException);
                return getDefaultLoginPage(loginError, session, model);
            }
        }
        return getSuccessRedirect(request, response);
    }

    @ResponseBody
    @RequestMapping(value = "/login/google", method = RequestMethod.POST)
    public String doLoginWithGoogle(
            @RequestBody final MultiValueMap<String, String> bodyParameterMap,
            @RequestParam(value = "error", defaultValue = "false") final boolean loginError,
            final Model model, final HttpServletRequest request, final HttpServletResponse response, final HttpSession session) throws CMSItemNotFoundException {
        // TODO: ana.neatu Error handling.
        LOGGER.info("Google Logged In user: " + bodyParameterMap.toString());
        String idToken = bodyParameterMap.getFirst(GOOGLE_RESPONSE_ID_TOKEN);

        ServicesUtil.validateParameterNotNullStandardMessage("idToken", idToken);

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(getGoogleClientId()))
                .build();

        try {
            // (Receive idTokenString by HTTPS POST)
            GoogleIdToken googleIdToken = verifier.verify(idToken);
            if (googleIdToken == null) {
                LOGGER.error("Unable to retrieve Google Logged In user details! The *googleIdToken* object is null !!!");
                return getDefaultLoginPage(loginError, session, model);
            }

            GoogleIdToken.Payload payload = googleIdToken.getPayload();
            if (!Boolean.valueOf(payload.getEmailVerified())) {
                LOGGER.error("Unable to retrieve Google Logged In user details! The *email* is not verified !!!");
                return getDefaultLoginPage(loginError, session, model);
            }

            String email = payload.getEmail();
            String name = (String) payload.get("name");
            try {
            	 CustomerData customer=getCustomerFacade().getUserForUID(email);
                if (null != customer)
    				{
    					shipmentFacade.setShipmentTypeInSession(customer.getCurrentShipmentMode(),
    							customer.getCurrentPostalCode(),customer.getCurrentPos());
    				}
                LOGGER.info("Google Logged In user already registered. Updating ...");
                // TODO: ana.neatu Decide whether there are any details that can be updated in Hybris (maybe name? uid update?)
                LOGGER.info("Google Logged In user already registered. Logging in ...");
                getSocialMediaAutoLoginStrategy().login(email, null, request, response);
            } catch (UnknownIdentifierException unknownIdentifierException) {
                LOGGER.info("Google Logged In user not registered. Registering ...");

                try {
                    getSocialMediaLoginFacade().createNewCustomer(name, email);
                    LOGGER.info("Google Logged In user already registered. Logging in ...");
                    getSocialMediaAutoLoginStrategy().login(email, null, request, response);
                    wishListFacade.createDefaultWishlistForNewUser();
                } catch (DuplicateUidException duplicateUidException) {
                    LOGGER.warn("Google Logged In user already exists!", duplicateUidException);
                    return getDefaultLoginPage(loginError, session, model);
                }
            }
        } catch (GeneralSecurityException | IOException exception) {
            LOGGER.error("Unable to validate Google Logged In user details!", exception);
            return getDefaultLoginPage(loginError, session, model);
        }

        return getSuccessRedirect(request, response);
    }

    @Override
    protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException {
        return getContentPageForLabelOrId("login");
    }

    @Override
    protected String getSuccessRedirect(HttpServletRequest request, HttpServletResponse response) {
        String referer = request.getHeader("Referer");
        if (StringUtils.isNotBlank(referer) && StringUtils.endsWith(referer, "/login/checkout")
                && StringUtils.contains(referer, request.getServerName())) {
            return "/checkout/multi/cart-review/show";
        }
        if (StringUtils.isNotBlank(referer) && !StringUtils.endsWith(referer, "/login")
                && StringUtils.contains(referer, request.getServerName())) {
            return referer;
        }
        return "/";
    }

    @Override
    protected String getView() {
        return ControllerConstants.Views.Pages.Account.AccountLoginPage;
    }

    public SocialMediaLoginFacade getSocialMediaLoginFacade() {
        return socialMediaLoginFacade;
    }

    public void setSocialMediaLoginFacade(SocialMediaLoginFacade socialMediaLoginFacade) {
        this.socialMediaLoginFacade = socialMediaLoginFacade;
    }

    public AutoLoginStrategy getSocialMediaAutoLoginStrategy() {
        return socialMediaAutoLoginStrategy;
    }

    public void setSocialMediaAutoLoginStrategy(AutoLoginStrategy socialMediaAutoLoginStrategy) {
        this.socialMediaAutoLoginStrategy = socialMediaAutoLoginStrategy;
    }

}
