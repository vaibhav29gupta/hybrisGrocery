/**
 *
 */
package org.grocery.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.ConsentForm;


/**
 * @author kirtimittal
 *
 */
public class RegisterForm
{
	private String titleCode;
	private String firstName;
	private String lastName;
	private String email;
	private String pwd;
	private String checkPwd;
	private ConsentForm consentForm;
	private boolean termsCheck;
	private String preferredShipmentMode;
	private String preferredPostalCode;
	private String preferredPos;

	/**
	 * @return the titleCode
	 */
	public String getTitleCode()
	{
		return titleCode;
	}

	/**
	 * @param titleCode
	 *           the titleCode to set
	 */
	public void setTitleCode(final String titleCode)
	{
		this.titleCode = titleCode;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName
	 *           the firstName to set
	 */
	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName
	 *           the lastName to set
	 */
	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 *           the email to set
	 */
	public void setEmail(final String email)
	{
		this.email = email;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd()
	{
		return pwd;
	}

	/**
	 * @param pwd
	 *           the pwd to set
	 */
	public void setPwd(final String pwd)
	{
		this.pwd = pwd;
	}

	/**
	 * @return the checkPwd
	 */
	public String getCheckPwd()
	{
		return checkPwd;
	}

	/**
	 * @param checkPwd
	 *           the checkPwd to set
	 */
	public void setCheckPwd(final String checkPwd)
	{
		this.checkPwd = checkPwd;
	}

	/**
	 * @return the consentForm
	 */
	public ConsentForm getConsentForm()
	{
		return consentForm;
	}

	/**
	 * @param consentForm
	 *           the consentForm to set
	 */
	public void setConsentForm(final ConsentForm consentForm)
	{
		this.consentForm = consentForm;
	}

	/**
	 * @return the termsCheck
	 */
	public boolean isTermsCheck()
	{
		return termsCheck;
	}

	/**
	 * @param termsCheck
	 *           the termsCheck to set
	 */
	public void setTermsCheck(final boolean termsCheck)
	{
		this.termsCheck = termsCheck;
	}

	/**
	 * @return the preferredShipmentMode
	 */
	public String getPreferredShipmentMode()
	{
		return preferredShipmentMode;
	}

	/**
	 * @param preferredShipmentMode
	 *           the preferredShipmentMode to set
	 */
	public void setPreferredShipmentMode(final String preferredShipmentMode)
	{
		this.preferredShipmentMode = preferredShipmentMode;
	}

	/**
	 * @return the preferredPostalCode
	 */
	public String getPreferredPostalCode()
	{
		return preferredPostalCode;
	}

	/**
	 * @param preferredPostalCode
	 *           the preferredPostalCode to set
	 */
	public void setPreferredPostalCode(final String preferredPostalCode)
	{
		this.preferredPostalCode = preferredPostalCode;
	}

	/**
	 * @return the preferredPos
	 */
	public String getPreferredPos()
	{
		return preferredPos;
	}

	/**
	 * @param preferredPos
	 *           the preferredPos to set
	 */
	public void setPreferredPos(final String preferredPos)
	{
		this.preferredPos = preferredPos;
	}
}
