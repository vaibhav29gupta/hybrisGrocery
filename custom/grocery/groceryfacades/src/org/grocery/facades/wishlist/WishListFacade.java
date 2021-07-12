/**
 *
 */
package org.grocery.facades.wishlist;


import de.hybris.platform.selectivecartfacades.data.Wishlist2Data;
import de.hybris.platform.selectivecartfacades.data.Wishlist2EntryData;
import de.hybris.platform.wishlist2.enums.Wishlist2EntryPriority;

import java.util.List;
import java.util.Map;


/**
 * @author vaibhavgupta03
 *
 */
public interface WishListFacade
{
	void createDefaultWishlistForNewUser();

	List<Wishlist2Data> getAllWishLists();

	/**
	 * @param wishlistName
	 * @return
	 */
	List<Wishlist2EntryData> getDetailsOfWishlist(String wishlistName);

	/**
	 * @param wishListName
	 */
	boolean removeWishlist(String wishlistName);

	/**
	 * @param wishlistName
	 * @param productCode
	 */
	boolean removeWishlistEntryForProduct(String wishlistName, String productCode);

	/**
	 * @param wishListName
	 */
	boolean createWishlist(String wishlistName);

	/**
	 * @param wishListName
	 * @param wishListNewName
	 * @return
	 */
	public boolean renameWishlist(String wishlistName, String wishlistNewName);


	/**
	 * @param wishlistName
	 * @param productCode
	 * @param defaultDesired
	 * @param defaultWishlistEntryPriority
	 * @param defaultWishlistEntryComment
	 */
	boolean addWishlistEntryForProduct(String wishlistName, String productCode, int defaultDesired,
			Wishlist2EntryPriority defaultWishlistEntryPriority, String defaultWishlistEntryComment);

	/**
	 * @param wishlistName
	 * @param storeId
	 */
	List<String> addAllEntriesToCart(String wishlistName, String storeId);

	/**
	 * @param productCode
	 */
	Map<String, Boolean> getAllWishlistsForProductWithStatus(String productCode);



}

