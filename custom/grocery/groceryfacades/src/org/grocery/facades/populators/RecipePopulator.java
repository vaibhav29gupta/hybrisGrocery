package org.grocery.facades.populators;

import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.grocery.core.model.IngredientModel;
import org.grocery.core.model.IngredientsGroupModel;
import org.grocery.core.model.RecipeModel;
import org.grocery.facades.constants.GroceryFacadesConstants;
import org.grocery.facades.recipe.data.IngredientData;
import org.grocery.facades.recipe.data.IngredientsGroupData;
import org.grocery.facades.recipe.data.RecipeData;
import org.springframework.util.Assert;


/**
 * @author kirtimittal
 *
 */
public class RecipePopulator implements Populator<RecipeModel, RecipeData>
{
	private Converter<MediaModel, MediaData> mediaModelConverter;
	private Converter<RecipeModel, RecipeData> recipeConverter;
	private Converter<IngredientsGroupModel, IngredientsGroupData> ingredientsGroupConverter;
	private Converter<IngredientModel, IngredientData> ingredientConverter;
	private ProductFacade productFacade;

	protected static final List<ProductOption> PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE,
			ProductOption.STOCK, ProductOption.URL, ProductOption.IMAGES, ProductOption.PROMOTIONS);

	@Override
	public void populate(final RecipeModel source, final RecipeData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		setRecipe(source, target);

		final List<ProductData> products = new ArrayList<>();
		final List<IngredientsGroupModel> ingredientsGroupModel = source.getGroup();
		final List<IngredientModel> ingredientModel = source.getUncategorizedIngredient();
		final List<IngredientModel> ingredientsModel = new ArrayList<>();
		final Set<ProductData> essentialProduct = new HashSet<>();

		if (ingredientsGroupModel != null && ingredientsGroupModel.size() > 0)
		{
			for (final IngredientsGroupModel ingredientsGroup : ingredientsGroupModel)
			{
				for (final IngredientModel ingredient : ingredientsGroup.getIngredients())
				{
					if (ingredient.getProduct() != null && ingredient.getProduct().getCode() != null)
					{
						ingredientsModel.add(ingredient);
					}
				}
			}

			final List<IngredientsGroupData> ingredientsGroupData = getIngredientsGroupConverter().convertAll(ingredientsGroupModel);
			target.setGroup(ingredientsGroupData);
			setProductForRecipes(ingredientsModel, target, products, essentialProduct);
		}

		if (ingredientModel != null)
		{
			final List<IngredientData> uncategorizedIngredient = getIngredientConverter().convertAll(ingredientModel);
			target.setUncategorizedIngredient(uncategorizedIngredient);
			setProductForRecipes(ingredientModel, target, products, essentialProduct);
		}
	}

	private void setProductForRecipes(final List<IngredientModel> ingredientsModel, final RecipeData target,
			final List<ProductData> products, final Set<ProductData> essentialProduct)
	{
		for (final IngredientModel ingredients : ingredientsModel)
		{
			if (ingredients.getProduct() != null && ingredients.getProduct().getCode() != null)
			{
				final ProductModel productModel = ingredients.getProduct();
				final ProductData productData = getProductData(productModel);

				products.add(productData);

				if (ingredients.getIsEssential() != null && ingredients.getIsEssential() == true)
				{
					essentialProduct.add(productData);
				}
			}
		}

		target.setProduct(products);
		target.setEssentialProduct(essentialProduct);
	}

	private void setRecipe(final RecipeModel source, final RecipeData target)
	{
		target.setCode(source.getCode());
		target.setCookingTime(source.getCookingTime());
		target.setDescription(source.getDescription());
		target.setIngredientsAsText(source.getIngredientsAsText());
		target.setName(source.getName());
		target.setPrepTime(source.getPrepTime());
		target.setServes(source.getServes());
		target.setVideoURL(source.getVideoURL());
		final MediaModel media = source.getMedia();

		if (source.getMethod() != null)
		{
			target.setMethod(List.of(source.getMethod().split(GroceryFacadesConstants.RECIPE_METHOD_DELIMITTER)));
		}

		if (media != null)
		{
			target.setMedia(getMediaModelConverter().convert(media));
		}

		target.setUrl(GroceryFacadesConstants.RECIPE_URL + source.getCode());
	}

	private ProductData getProductData(final ProductModel productModel)
	{
		return getProductFacade().getProductForCodeAndOptions(productModel.getCode(), PRODUCT_OPTIONS);
	}

	/**
	 * @return the ingredientsGroupConverter
	 */
	public Converter<IngredientsGroupModel, IngredientsGroupData> getIngredientsGroupConverter()
	{
		return ingredientsGroupConverter;
	}

	/**
	 * @param ingredientsGroupConverter
	 *           the ingredientsGroupConverter to set
	 */
	public void setIngredientsGroupConverter(
			final Converter<IngredientsGroupModel, IngredientsGroupData> ingredientsGroupConverter)
	{
		this.ingredientsGroupConverter = ingredientsGroupConverter;
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

	/**
	 * @return the productFacade
	 */
	public ProductFacade getProductFacade()
	{
		return productFacade;
	}

	/**
	 * @param productFacade
	 *           the productFacade to set
	 */
	public void setProductFacade(final ProductFacade productFacade)
	{
		this.productFacade = productFacade;
	}

	/**
	 * @return the mediaModelConverter
	 */
	public Converter<MediaModel, MediaData> getMediaModelConverter()
	{
		return mediaModelConverter;
	}

	/**
	 * @param mediaModelConverter
	 *           the mediaModelConverter to set
	 */
	public void setMediaModelConverter(final Converter<MediaModel, MediaData> mediaModelConverter)
	{
		this.mediaModelConverter = mediaModelConverter;
	}

	/**
	 * @return the recipeConverter
	 */
	public Converter<RecipeModel, RecipeData> getRecipeConverter()
	{
		return recipeConverter;
	}

	/**
	 * @param recipeConverter
	 *           the recipeConverter to set
	 */
	public void setRecipeConverter(final Converter<RecipeModel, RecipeData> recipeConverter)
	{
		this.recipeConverter = recipeConverter;
	}
}
