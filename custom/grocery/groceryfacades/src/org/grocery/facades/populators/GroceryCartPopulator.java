/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.order.converters.populator.ExtendedCartPopulator;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.core.model.order.AbstractOrderModel;


/**
 * @author amitsharma09
 *
 */
public class GroceryCartPopulator extends ExtendedCartPopulator
{

	@Override
	protected void addTotals(final AbstractOrderModel source, final AbstractOrderData prototype)
	{
		super.addTotals(source, prototype);
		final double subTotalWithoutDiscounts = source.getSubtotal().doubleValue();
		final PriceData subTotalWithoutDiscountsPriceData = createPrice(source, Double.valueOf(subTotalWithoutDiscounts));

		prototype.setSubTotalWithoutDiscount(subTotalWithoutDiscountsPriceData);

	}

}
