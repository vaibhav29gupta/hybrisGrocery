/**
 *
 */
package org.grocery.storefront.forms.validation;


import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator;

import org.apache.commons.lang.StringUtils;
import org.grocery.storefront.forms.RegisterForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@Component("groceryRegistrationValidator")
public class GroceryRegistrationValidator extends RegistrationValidator
{
	@Override
	public void validate(final Object object, final Errors errors)
	{
		final RegisterForm registerForm = (RegisterForm) object;
		final String titleCode = registerForm.getTitleCode();
		final String firstName = registerForm.getFirstName();
		final String lastName = registerForm.getLastName();
		final String email = registerForm.getEmail();
		final String pwd = registerForm.getPwd();
		final String checkPwd = registerForm.getCheckPwd();
		final boolean termsCheck = registerForm.isTermsCheck();

		validateTitleCode(errors, titleCode);
		validateName(errors, firstName, "firstName", "register.firstName.invalid");
		validateName(errors, lastName, "lastName", "register.lastName.invalid");

		if (StringUtils.length(firstName) + StringUtils.length(lastName) > 255)
		{
			errors.rejectValue("lastName", "register.name.invalid");
			errors.rejectValue("firstName", "register.name.invalid");
		}

		validateEmail(errors, email);
		validatePassword(errors, pwd);
		comparePasswords(errors, pwd, checkPwd);
		validateTermsAndConditions(errors, termsCheck);
	}
}
