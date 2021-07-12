/**
 * 
 */
package org.grocery.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.spi.InputDocument;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider.FieldType;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractValueResolver;

import org.apache.commons.lang.StringUtils;
import org.grocery.core.model.GroceryVariantProductModel;

/**
 * @author arshdeepsingh
 *
 */
public class ProductVariantLabelValueResolver extends AbstractValueResolver<ProductModel, Object, Object>
{


	private FieldNameProvider fieldNameProvider;

	@Override
	protected void addFieldValues(final InputDocument document, final IndexerBatchContext paramIndexerBatchContext,
			final IndexedProperty indexedProperty, final ProductModel product,
			final ValueResolverContext<Object, Object> paramValueResolverContext) throws FieldValueProviderException
	{
		String productLabelAndWeight;
		if (product instanceof GroceryVariantProductModel)
		{
			GroceryVariantProductModel variantProduct = (GroceryVariantProductModel) product;
			productLabelAndWeight = variantProduct.getPackSizeLabel() != null ? variantProduct.getPackSizeLabel()
					: StringUtils.EMPTY;
		}
		else
		{
			productLabelAndWeight = product.getWeight() != null ? product.getWeight() : StringUtils.EMPTY;
		}
		if(StringUtils.isNotEmpty(productLabelAndWeight)) {
		document.addField(getFieldNameProvider().getFieldName(indexedProperty, null, FieldType.INDEX), productLabelAndWeight);
		}
	}

	/**
	 * @return the fieldNameProvider
	 */
	public FieldNameProvider getFieldNameProvider()
	{
		return fieldNameProvider;
	}

	/**
	 * @param fieldNameProvider
	 *           the fieldNameProvider to set
	 */
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}



}
