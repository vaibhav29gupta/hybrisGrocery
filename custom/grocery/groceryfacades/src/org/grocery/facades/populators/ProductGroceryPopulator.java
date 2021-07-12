/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.commercefacades.product.converters.populator.ProductBasicPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.grocery.core.model.GroceryVariantProductModel;

import com.microsoft.sqlserver.jdbc.StringUtils;


/**
 * @author bhavya2486
 *
 */
public class ProductGroceryPopulator<SOURCE extends ProductModel, TARGET extends ProductData>
		extends ProductBasicPopulator<SOURCE, TARGET>
{

	@Override
	public void populate(final SOURCE productModel, final TARGET productData) throws ConversionException
	{
		if (productModel instanceof GroceryVariantProductModel)
		{
			GroceryVariantProductModel variantProduct = (GroceryVariantProductModel) productModel;
			ProductModel baseProduct = variantProduct.getBaseProduct();

			productData.setWeight(variantProduct.getPackSizeLabel() != null ? variantProduct.getPackSizeLabel() : StringUtils.EMPTY);
			productData.setBrand(baseProduct.getBrand() != null ? baseProduct.getBrand() : StringUtils.EMPTY);
			productData.setExpiry(baseProduct.getExpiry() != null ? baseProduct.getExpiry() : StringUtils.EMPTY);
			productData.setDietSuitability(
					baseProduct.getDietSuitability() != null ? baseProduct.getDietSuitability().toString() : StringUtils.EMPTY);
			productData.setIngredients(baseProduct.getIngredients());
			productData.setUsageGuidelines(baseProduct.getUsageGuidelines());

		}
		else
		{
			productData.setWeight(productModel.getWeight() != null ? productModel.getWeight() : StringUtils.EMPTY);
			productData.setBrand(productModel.getBrand() != null ? productModel.getBrand() : StringUtils.EMPTY);
			productData.setExpiry(productModel.getExpiry() != null ? productModel.getExpiry() : StringUtils.EMPTY);
			productData.setDietSuitability(
					productModel.getDietSuitability() != null ? productModel.getDietSuitability().toString() : StringUtils.EMPTY);
			productData.setIngredients(productModel.getIngredients());
			productData.setUsageGuidelines(productModel.getUsageGuidelines());

		}
	}
}
