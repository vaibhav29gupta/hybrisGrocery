/**
 *
 */
package org.grocery.storefront.wishlist.form;

import java.util.List;


/**
 * @author vaibhavgupta03
 *
 */
public class WishListAddToCartForm
{

	private List<String> entries;
	private String name;

	/**
	 * @return the entries
	 */
	public List<String> getEntries()
	{
		return entries;
	}

	/**
	 * @param entries
	 *           the entries to set
	 */
	public void setEntries(final List<String> entries)
	{
		this.entries = entries;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *           the name to set
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

}
