package org.grocery.facades.security;

import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.UserModel;

/**
 * Define specific social media login operations.
 */
public interface SocialMediaLoginFacade {

    /**
     * Creates (registers) a new @{@link UserModel} based on the details retrieved from the Social Media IDP.
     *
     * @param name  the name of the customer
     * @param email the email of the customer - which will become the @{@link UserModel} UID.
     * @return returns the newly created @{@link UserModel}
     * @throws DuplicateUidException thrown in case a customer with the given email already exists
     */
    UserModel createNewCustomer(String name, String email) throws DuplicateUidException;
}
