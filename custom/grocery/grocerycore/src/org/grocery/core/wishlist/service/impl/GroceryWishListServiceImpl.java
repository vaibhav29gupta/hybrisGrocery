/**
 *
 */
package org.grocery.core.wishlist.service.impl;


import de.hybris.platform.wishlist2.impl.DefaultWishlist2Service;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.grocery.core.wishlist.service.GroceryWishListService;




/**
 * @author vaibhavgupta03
 *
 */


public class GroceryWishListServiceImpl extends DefaultWishlist2Service implements GroceryWishListService
{
	@Override
	public Wishlist2Model getDetailsOfWishlist(final String wishlistName)
	{
		final List<Wishlist2Model> wishlistForCurrentUser = this.getWishlists();
		Wishlist2Model wishlistModel = null;
		if (CollectionUtils.isNotEmpty(wishlistForCurrentUser))
		{
			final Optional<Wishlist2Model> optional = wishlistForCurrentUser.stream()
					.filter(wishlist -> wishlistName.equals(wishlist.getName())).findFirst();
			if (optional.isPresent())
			{
				wishlistModel = optional.get();
			}
		}
		return wishlistModel;
	}

	@Override
	public void removeWishlist(final String wishlistName)
	{
		final Wishlist2Model wishlistModel = getDetailsOfWishlist(wishlistName);
		if (null != wishlistModel)
		{
			getModelService().remove(wishlistModel);
		}

	}

	@Override
	public void renameWishlist(final String name, final String newName)
	{
		final Wishlist2Model wishlist = getDetailsOfWishlist(name);
		if (null != wishlist)
		{
			wishlist.setName(newName);
			getModelService().save(wishlist);
		}
	}

}
