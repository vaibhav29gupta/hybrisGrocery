/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.grocery.core.model.RecipeModel;
import org.grocery.core.model.RecipesCategoryModel;
import org.grocery.facades.constants.GroceryFacadesConstants;
import org.grocery.facades.recipe.data.RecipeData;
import org.grocery.facades.recipe.data.RecipesCategoryData;
import org.springframework.util.Assert;


/**
 * @author abhishekkumar05
 *
 */
public class RecipeCategoryPopulator implements Populator<RecipesCategoryModel, RecipesCategoryData>
{
	private Converter<RecipesCategoryModel, RecipesCategoryData> recipesCategoryConverter;

	private Converter<MediaModel, MediaData> mediaModelConverter;

	private Converter<MediaModel, ImageData> imageConverter;

	private Converter<RecipeModel, RecipeData> recipeConverter;

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
	 * @return the recipesCategoryConverter
	 */
	public Converter<RecipesCategoryModel, RecipesCategoryData> getRecipesCategoryConverter()
	{
		return recipesCategoryConverter;
	}

	/**
	 * @param recipesCategoryConverter
	 *           the recipesCategoryConverter to set
	 */
	public void setRecipesCategoryConverter(final Converter<RecipesCategoryModel, RecipesCategoryData> recipesCategoryConverter)
	{
		this.recipesCategoryConverter = recipesCategoryConverter;
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

	/**
	 * @return the imageConverter
	 */
	public Converter<MediaModel, ImageData> getImageConverter()
	{
		return imageConverter;
	}

	/**
	 * @param imageConverter
	 *           the imageConverter to set
	 */
	public void setImageConverter(final Converter<MediaModel, ImageData> imageConverter)
	{
		this.imageConverter = imageConverter;
	}

	@Override
	public void populate(final RecipesCategoryModel source, final RecipesCategoryData target)
	{
		final List<RecipeModel> viewAllRecipeModelList = new ArrayList<RecipeModel>();

		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setCode(source.getCode());
		target.setDescription(source.getDescription());
		target.setName(source.getName());

		final List<MediaModel> images = source.getMedias();
		if (CollectionUtils.isNotEmpty(images))
		{
			target.setImage(getImageConverter().convert(images.get(0)));
		}

		final MediaModel thumbnail = source.getThumbnail();
		if (thumbnail != null)
		{
			target.setThumbnail(getMediaModelConverter().convert(thumbnail));
		}

		target.setUrl(GroceryFacadesConstants.RECIPE_CATEGORY_URL + source.getCode());

		if (CollectionUtils.isNotEmpty(source.getRecipes()))
		{
			target.setRecipesCount(source.getRecipes().size());
			viewAllRecipeModelList.addAll(source.getRecipes());
		}

		if (CollectionUtils.isNotEmpty(source.getCategories()))
		{
			for (final CategoryModel category : source.getCategories())
			{
				if (category instanceof RecipesCategoryModel)
				{
					final RecipesCategoryModel recipeCategory = (RecipesCategoryModel) category;
					if (CollectionUtils.isNotEmpty(recipeCategory.getRecipes()))
					{
						viewAllRecipeModelList.addAll(recipeCategory.getRecipes());
					}
				}
			}
		}

		final List<RecipeData> recipesData = new ArrayList<RecipeData>();

		recipesData.addAll(getRecipeConverter().convertAll(viewAllRecipeModelList));
		target.setRecipes(recipesData);

	}
}
