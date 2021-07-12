package org.grocery.core.socialmedia.restrictions;


import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.log4j.Logger;
import org.grocery.core.model.restrictions.SocialMediaCustomerRestrictionModel;

import javax.annotation.Resource;

/**
 * Custom {@link CMSRestrictionEvaluator} which will restrict a component if the {@link CustomerModel} is a customer registered through Social Media.
 */
public class SocialMediaCustomerRestrictionEvaluator implements CMSRestrictionEvaluator<SocialMediaCustomerRestrictionModel> {

    private static final Logger LOGGER = Logger.getLogger(SocialMediaCustomerRestrictionEvaluator.class);

    @Resource
    private UserService userService;

    public boolean evaluate(SocialMediaCustomerRestrictionModel restrictionModel, RestrictionData restrictionData) {
        Boolean socialMediaRegistered = ((CustomerModel) getUserService().getCurrentUser()).getSocialMediaRegistered();
        if (socialMediaRegistered == null) {
            return true;
        }
        boolean allowed = Boolean.FALSE.equals(socialMediaRegistered);
        if (!allowed) {
            LOGGER.debug("Social Media Registered User: " + getUserService().getCurrentUser().getUid());
        }
        return allowed;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
