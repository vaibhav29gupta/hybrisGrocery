/**
 *
 */
package org.grocery.facades.controllers;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import javax.annotation.Resource;

import org.grocery.core.enums.ShipmentTypeEnum;
import org.springframework.util.Assert;


public class GroceryCustomerFacade extends DefaultCustomerFacade
{
	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "modelService")
	private ModelService modelService;

	@Override
	public void register(final RegisterData registerData) throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("registerData", registerData);
		Assert.hasText(registerData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(registerData.getLastName(), "The field [LastName] cannot be empty");
		Assert.hasText(registerData.getLogin(), "The field [Login] cannot be empty");

		final CustomerModel newCustomer = getModelService().create(CustomerModel.class);
		setCommonPropertiesForRegister(registerData, newCustomer);
		getCustomerAccountService().register(newCustomer, registerData.getPassword());
	}

	@Override
	protected void setCommonPropertiesForRegister(final RegisterData registerData, final CustomerModel customerModel)
	{
		customerModel.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));

		final String shipmentMode = registerData.getPreferredShipmentMode();

		if (shipmentMode.isEmpty())
		{
			customerModel.setPreferredShipmentMode(null);
		}
		else
		{
			customerModel.setPreferredShipmentMode(ShipmentTypeEnum.valueOf(shipmentMode));
		}

		customerModel.setPreferredPostalCode(registerData.getPreferredPostalCode());
		customerModel.setPreferredPos(registerData.getPreferredPos());
		setTitleForRegister(registerData, customerModel);
		setUidForRegister(registerData, customerModel);
		customerModel.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		customerModel.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
	}

	@Override
	public void updateProfile(final CustomerData customerData) throws DuplicateUidException
	{
		super.updateProfile(customerData);

		final CustomerModel customerModel = (CustomerModel) userService.getCurrentUser();

		final String shipmentMode = customerData.getPreferredShipmentMode();

		if (shipmentMode.isEmpty())
		{
			customerModel.setPreferredShipmentMode(null);
		}
		else
		{
			customerModel.setPreferredShipmentMode(ShipmentTypeEnum.valueOf(shipmentMode));
		}

		customerModel.setPreferredPostalCode(customerData.getPreferredPostalCode());
		customerModel.setPreferredPos(customerData.getPreferredPos());

		modelService.save(customerModel);


	}

}
