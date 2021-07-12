/**
 *
 */
package org.grocery.facade.search.populator;

import de.hybris.platform.commercefacades.search.converters.populator.BreadcrumbPopulator;
import de.hybris.platform.commerceservices.search.facetdata.BreadcrumbData;

import org.springframework.beans.factory.annotation.Required;


/**
 * @author ankituniyal
 *
 */
public class GroceryBreadcrumbPopulator<QUERY, STATE> extends BreadcrumbPopulator<QUERY, STATE>
{
	private String facetDisplayName;

	@Override
	public void populate(final BreadcrumbData<QUERY> source, final BreadcrumbData<STATE> target)
	{
		target.setFacetCode(source.getFacetCode());
		target.setFacetName(source.getFacetName());
		target.setFacetValueCode(source.getFacetValueCode());
		target.setFacetValueName(source.getFacetValueName());

		if (this.getFacetDisplayName().equals(source.getFacetCode()))
		{
			target.setFacetValueName(source.getFacetName());
		}

		if (source.getRemoveQuery() != null)
		{
			target.setRemoveQuery(getSearchStateConverter().convert(source.getRemoveQuery()));
		}

		if (source.getTruncateQuery() != null)
		{
			target.setTruncateQuery(getSearchStateConverter().convert(source.getTruncateQuery()));
		}
	}

	/**
	 * @return the facetDisplayName
	 */
	public String getFacetDisplayName()
	{
		return facetDisplayName;
	}

	/**
	 * @param facetDisplayName
	 *           the facetDisplayName to set
	 */
	@Required
	public void setFacetDisplayName(final String facetDisplayName)
	{
		this.facetDisplayName = facetDisplayName;
	}
}
