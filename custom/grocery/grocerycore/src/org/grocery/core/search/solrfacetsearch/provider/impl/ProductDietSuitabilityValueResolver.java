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
 * @author ankituniyal
 *
 */
public class ProductDietSuitabilityValueResolver extends AbstractValueResolver<ProductModel, Object, Object>
{
	private FieldNameProvider fieldNameProvider;

	@Override
	protected void addFieldValues(final InputDocument document, final IndexerBatchContext paramIndexerBatchContext,
			final IndexedProperty indexedProperty, final ProductModel product,
			final ValueResolverContext<Object, Object> paramValueResolverContext)
			throws FieldValueProviderException
	{
		String dietSuitability;
		if (product instanceof GroceryVariantProductModel)
		{
			GroceryVariantProductModel variantProduct = (GroceryVariantProductModel) product;
			ProductModel baseProduct = variantProduct.getBaseProduct();
			dietSuitability = baseProduct.getDietSuitability() != null ? baseProduct.getDietSuitability().getCode()
					: StringUtils.EMPTY;
		}
		else
		{
			dietSuitability = product.getDietSuitability() != null ? product.getDietSuitability().getCode() : StringUtils.EMPTY;
		}
		if(StringUtils.isNotBlank(dietSuitability)) {
		document.addField(getFieldNameProvider().getFieldName(indexedProperty, null, FieldType.INDEX), dietSuitability);
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
