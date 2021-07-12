/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.selectivecartfacades.data.Wishlist2EntryData;
import de.hybris.platform.selectivecartfacades.populators.WishlistEntryForSelectiveCartPopulator;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;


/**
 * @author ankituniyal
 *
 */
public class GroceryWishlistEntryPopulator extends WishlistEntryForSelectiveCartPopulator
{

	@Override
	public void populate(final Wishlist2EntryModel source, final Wishlist2EntryData target)
	{
		super.populate(source, target);

		final ProductData productData = getProductConverter().convert(source.getProduct());
		target.setQuantity(productData.getProductCartQuantity() != null ? productData.getProductCartQuantity().intValue()
				: source.getQuantity());
	}

}
