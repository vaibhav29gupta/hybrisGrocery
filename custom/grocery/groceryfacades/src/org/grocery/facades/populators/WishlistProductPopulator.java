/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.product.converters.populator.ProductBasicPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.wishlist2.Wishlist2Service;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;


/**
 * @author ojasmodi
 *
 */
public class WishlistProductPopulator<SOURCE extends ProductModel, TARGET extends ProductData>
		extends ProductBasicPopulator<SOURCE, TARGET>
{

	private Wishlist2Service wishlistService;


	@Override
	public void populate(final SOURCE productModel, final TARGET productData) throws ConversionException
	{
		final boolean isWishListPresent = getWishlistService().hasDefaultWishlist();
		if (isWishListPresent)
		{
			final Wishlist2Model wishlist = getWishlistService().getDefaultWishlist();
			try
			{
				final Wishlist2EntryModel wishlist2EntryModel = getWishlistService().getWishlistEntryForProduct(productModel,
						wishlist);
				if (wishlist2EntryModel != null)
				{
					productData.setIsProductPresentInWishlist(true);
				}
				else
				{
					productData.setIsProductPresentInWishlist(false);
				}
			}
			catch (final UnknownIdentifierException e)
			{
				productData.setIsProductPresentInWishlist(false);
			}
			catch (final AmbiguousIdentifierException e)
			{
				productData.setIsProductPresentInWishlist(false);
			}

		}
		else
		{
			productData.setIsProductPresentInWishlist(false);
		}
	}

	public Wishlist2Service getWishlistService()
	{
		return wishlistService;
	}


	public void setWishlistService(final Wishlist2Service wishlistService)
	{
		this.wishlistService = wishlistService;
	}

}
