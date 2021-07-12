/**
 *
 */
package org.grocery.core.wishlist.service;

import de.hybris.platform.wishlist2.Wishlist2Service;
import de.hybris.platform.wishlist2.model.Wishlist2Model;


/**
 * @author vaibhavgupta03
 *
 */
public interface GroceryWishListService extends Wishlist2Service
{

	Wishlist2Model getDetailsOfWishlist(final String wishlistName);

	/**
	 * @param wishListName
	 */
	void removeWishlist(String wishlistName);

	/**
	 * @param wishlistName
	 * @param wishlistNewName
	 * @return
	 */
	void renameWishlist(String wishlistName, String wishlistNewName);

}
