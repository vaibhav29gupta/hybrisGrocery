package org.grocery.facades.populators;

import de.hybris.platform.converters.Populator;

import org.grocery.core.model.IngredientModel;
import org.grocery.facades.constants.GroceryFacadesConstants;
import org.grocery.facades.recipe.data.IngredientData;
/**
 * @author kirtimittal
 *
 */

public class IngredientPopulator implements Populator<IngredientModel, IngredientData>
{

	@Override
	public void populate(final IngredientModel source, final IngredientData target)
	{
		target.setCode(source.getCode());
		target.setCutStyle(source.getCutStyle());
		target.setSpecialInstruction(source.getSpecialInstruction());
		target.setName(source.getName());
		target.setQuantity(source.getQuantity());

		if (source.getProduct() != null && source.getProduct().getCode() != null)
		{
			target.setUrl(GroceryFacadesConstants.RECIPE_PRODUCT_URL + source.getProduct().getCode());
		}

	}
}

