package org.grocery.facade.recipe.impl;

import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.grocery.core.model.RecipeModel;
import org.grocery.core.model.RecipesCategoryModel;
import org.grocery.core.recipe.service.RecipeService;
import org.grocery.facades.recipe.RecipeFacade;
import org.grocery.facades.recipe.data.RecipeData;
import org.grocery.facades.recipe.data.RecipesCategoryData;


/**
 * @author kirtimittal
 *
 */
public class RecipeFacadeImpl implements RecipeFacade
{

	private RecipeService recipeService;
	private Converter<RecipeModel, RecipeData> recipeConverter;
	private CartFacade cartFacade;
	private Converter<RecipesCategoryModel, RecipesCategoryData> recipesCategoryConverter;

	/**
	 * @return the cartFacade
	 */
	public CartFacade getCartFacade()
	{
		return cartFacade;
	}


	/**
	 * @param cartFacade
	 *           the cartFacade to set
	 */
	public void setCartFacade(final CartFacade cartFacade)
	{
		this.cartFacade = cartFacade;
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
	 * @return the recipeService
	 */
	public RecipeService getRecipeService()
	{
		return recipeService;
	}


	/**
	 * @param recipeService
	 *           the recipeService to set
	 */
	public void setRecipeService(final RecipeService recipeService)
	{
		this.recipeService = recipeService;
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

	@Override
	public RecipeData getRecipeByCode(final String code)
	{
		final RecipeModel recipeModel = getRecipeService().getRecipeByCode(code);
		if (null != recipeModel)
		{
			final RecipeData recipeData = getRecipeConverter().convert(recipeModel);

			if (null != recipeData)
			{
				return recipeData;
			}
		}
		return null;
	}



	@Override
	public RecipesCategoryData getRecipesCategoryByCode(final String code)
	{
		final RecipesCategoryModel categoryModel = getRecipeService().retrieveRecipesCategoryByCode(code);

		RecipesCategoryData categoryData = null;

		if (categoryModel != null)
		{
			categoryData = getRecipesCategoryConverter().convert(categoryModel);
		}
		return categoryData;
	}

	public List<RecipesCategoryData> collectRecipesCategory(final List<RecipesCategoryModel> recipesCategoryModel)
	{
		List<RecipesCategoryData> recipesCategoryData = null;

		if (recipesCategoryModel != null)
		{
			recipesCategoryData = getRecipesCategoryConverter().convertAll(recipesCategoryModel);
		}

		return recipesCategoryData;
	}

	@Override
	public List<RecipeData> getRecipeListByCodes(final List<String> codes)
	{
		final List<RecipeModel> recipeList = getRecipeService().getRecipeListByCodes(codes);

		final List<RecipeData> highlightRecipeList = new ArrayList<RecipeData>();

		if (recipeList != null)
		{
			for (final RecipeModel recipe : recipeList)
			{

				final RecipeData recipeData = getRecipeConverter().convert(recipe);

				if (recipeData != null)
				{
					highlightRecipeList.add(recipeData);
				}
			}
		}
		return highlightRecipeList;
	}

	@Override
	public List<String> addAllEntriesToCart(final RecipeData recipeData, final String storeId)
	{
		final List<String> listOfUnaffectedProductCodes = new ArrayList<>();

		for (final ProductData product : recipeData.getEssentialProduct())
		{
			if (product.getIsServiceable())
			{
				addProductToCart(product.getCode(), storeId, listOfUnaffectedProductCodes);
			}
			else
			{
				listOfUnaffectedProductCodes.add(product.getCode());
			}
		}
		return CollectionUtils.isNotEmpty(listOfUnaffectedProductCodes) ? listOfUnaffectedProductCodes : Collections.emptyList();
	}

	protected void addProductToCart(final String productCode, final String storeId,
			final List<String> listOfUnaffectedProductCodes)
	{
		boolean isProductPresentInCart = Boolean.FALSE;
		if (getCartFacade().hasEntries())
		{
			final CartData cart = getCartFacade().getSessionCart();
			final List<OrderEntryData> cartEntries = cart.getEntries();
			for (final OrderEntryData abstractOrderEntry : cartEntries)
			{
				final ProductData product = abstractOrderEntry.getProduct();
				if (product.getCode().equals(productCode))
				{
					final long quantity = abstractOrderEntry.getQuantity();
					addRecipeEntryToCart(productCode, quantity + 1, storeId, listOfUnaffectedProductCodes);
					isProductPresentInCart = Boolean.TRUE;
					break;
				}
			}
		}
		if (!isProductPresentInCart)
		{
			addRecipeEntryToCart(productCode, 1L, storeId, listOfUnaffectedProductCodes);
		}
	}

	protected void addRecipeEntryToCart(final String productCode, final Long quantity, final String storeID,
			final List<String> listOfUnaffectedProductCodes)
	{
		try
		{
			final CartModificationData cartModification = getCartFacade().addToCart(productCode, quantity, storeID);
			if (cartModification.getStatusCode().equals("maxOrderQuantityExceeded"))
			{
				listOfUnaffectedProductCodes.add(productCode);
			}
		}
		catch (final CommerceCartModificationException e)
		{
			e.printStackTrace();
			listOfUnaffectedProductCodes.add(productCode);
		}
	}
}
