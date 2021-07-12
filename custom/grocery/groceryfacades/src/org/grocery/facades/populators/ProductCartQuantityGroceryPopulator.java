/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.product.converters.populator.ProductBasicPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.List;


/**
 * @author ojasmodi
 *
 */
public class ProductCartQuantityGroceryPopulator<SOURCE extends ProductModel, TARGET extends ProductData>
		extends ProductBasicPopulator<SOURCE, TARGET>
{

	private CartService cartService;

	@Override
	public void populate(final SOURCE productModel, final TARGET productData) throws ConversionException
	{
		super.populate(productModel, productData);
		final CartModel cartModel = getCartService().getSessionCart();
		productData.setProductCartQuantity((long) 0);
		final List<AbstractOrderEntryModel> entries = cartModel.getEntries();
		if (entries != null)
		{
			for (final AbstractOrderEntryModel orderEntry : entries)
			{
				if (orderEntry.getProduct().getCode().equals(productModel.getCode()))
				{
					productData.setProductCartQuantity(orderEntry.getQuantity());
				}
			}
		}
	}

	/**
	 * @return the cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService
	 *           the cartService to set
	 */
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}


}
