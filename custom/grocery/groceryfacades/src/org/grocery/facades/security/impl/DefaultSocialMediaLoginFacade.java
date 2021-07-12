package org.grocery.facades.security.impl;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import org.grocery.facades.security.SocialMediaLoginFacade;

import javax.annotation.Resource;
import java.util.UUID;

public class DefaultSocialMediaLoginFacade implements SocialMediaLoginFacade {

    @Resource(name = "modelService")
    private ModelService modelService;

    @Resource(name = "commonI18NService")
    private CommonI18NService commonI18NService;

    @Resource(name = "customerAccountService")
    private CustomerAccountService customerAccountService;

    @Override
    public UserModel createNewCustomer(String name, String email) throws DuplicateUidException {

        ServicesUtil.validateParameterNotNullStandardMessage("name", name);
        ServicesUtil.validateParameterNotNullStandardMessage("email", email);

        CustomerModel socialMediaCustomer = getModelService().create(CustomerModel.class);
        socialMediaCustomer.setName(name);
        socialMediaCustomer.setUid(email);
        socialMediaCustomer.setOriginalUid(email);
        socialMediaCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
        socialMediaCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
        socialMediaCustomer.setCustomerID(UUID.randomUUID().toString());
        socialMediaCustomer.setSocialMediaRegistered(Boolean.TRUE);

        getCustomerAccountService().register(socialMediaCustomer, null);

        return socialMediaCustomer;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public CommonI18NService getCommonI18NService() {
        return commonI18NService;
    }

    public void setCommonI18NService(CommonI18NService commonI18NService) {
        this.commonI18NService = commonI18NService;
    }

    public CustomerAccountService getCustomerAccountService() {
        return customerAccountService;
    }

    public void setCustomerAccountService(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }
}
