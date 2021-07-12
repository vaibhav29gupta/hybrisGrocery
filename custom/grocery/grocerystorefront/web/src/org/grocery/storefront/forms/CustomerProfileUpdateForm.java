/**
 *
 */
package org.grocery.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdateProfileForm;


/**
 * @author abhishekkumar05
 *
 */
public class CustomerProfileUpdateForm extends UpdateProfileForm
{

	private String preferredShipmentMode;
	private String preferredPostalCode;
	private String preferredPos;
	private String storeName;

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


	/**
	 * @return the storeName
	 */
	public String getStoreName()
	{
		return storeName;
	}

	/**
	 * @param storeName
	 *           the storeName to set
	 */
	public void setStoreName(final String storeName)
	{
		this.storeName = storeName;
	}
}
