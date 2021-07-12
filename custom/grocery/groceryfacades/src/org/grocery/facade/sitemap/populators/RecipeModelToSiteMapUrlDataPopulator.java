package org.grocery.facade.sitemap.populators;

import de.hybris.platform.acceleratorservices.sitemap.data.SiteMapUrlData;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.commons.lang.StringEscapeUtils;
import org.grocery.core.model.RecipeModel;


/**
 * @author kirtimittal
 *
 */
public class RecipeModelToSiteMapUrlDataPopulator implements Populator<RecipeModel, SiteMapUrlData>
{
	private UrlResolver<RecipeModel> urlResolver;

	@Override
	public void populate(final RecipeModel recipeModel, final SiteMapUrlData siteMapUrlData) throws ConversionException
	{
		final String relUrl = StringEscapeUtils.escapeXml(getUrlResolver().resolve(recipeModel));
		siteMapUrlData.setLoc(relUrl);
	}

	public UrlResolver<RecipeModel> getUrlResolver()
	{
		return urlResolver;
	}

	public void setUrlResolver(final UrlResolver<RecipeModel> urlResolver)
	{
		this.urlResolver = urlResolver;
	}
}

