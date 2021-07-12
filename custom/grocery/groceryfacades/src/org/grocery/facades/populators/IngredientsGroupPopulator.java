package org.grocery.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

import org.grocery.core.model.IngredientModel;
import org.grocery.core.model.IngredientsGroupModel;
import org.grocery.facades.recipe.data.IngredientData;
import org.grocery.facades.recipe.data.IngredientsGroupData;
/**
 * @author kirtimittal
 *
 */
public class IngredientsGroupPopulator implements Populator<IngredientsGroupModel, IngredientsGroupData>
{
	private Converter<IngredientModel, IngredientData> ingredientConverter;

	@Override
	public void populate(final IngredientsGroupModel source, final IngredientsGroupData target)
	{
		target.setGroupName(source.getGroupName());
		final List<IngredientModel> ingredientModel = source.getIngredients();

		if (ingredientModel != null)
		{
			final List<IngredientData> ingredientData = getIngredientConverter().convertAll(ingredientModel);
			target.setIngredients(ingredientData);
		}
	}

	/**
	 * @return the ingredientConverter
	 */
	public Converter<IngredientModel, IngredientData> getIngredientConverter()
	{
		return ingredientConverter;
	}

	/**
	 * @param ingredientConverter
	 *           the ingredientConverter to set
	 */
	public void setIngredientConverter(final Converter<IngredientModel, IngredientData> ingredientConverter)
	{
		this.ingredientConverter = ingredientConverter;
	}

}
