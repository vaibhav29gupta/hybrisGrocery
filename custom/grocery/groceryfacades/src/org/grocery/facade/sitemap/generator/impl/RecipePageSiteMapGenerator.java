package org.grocery.facade.sitemap.generator.impl;

import de.hybris.platform.acceleratorservices.sitemap.data.SiteMapUrlData;
import de.hybris.platform.acceleratorservices.sitemap.generator.impl.AbstractSiteMapGenerator;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.converters.Converters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.grocery.core.model.RecipeModel;


/**
 * @author kirtimittal
 *
 */
public class RecipePageSiteMapGenerator extends AbstractSiteMapGenerator<RecipeModel>
{
	@Override
	protected List<RecipeModel> getDataInternal(final CMSSiteModel siteModel)
	{
		final String query = "SELECT {r.pk} FROM {Recipe AS r JOIN CatalogVersion AS cv ON {r.catalogVersion}={cv.pk} "
				+ " JOIN Catalog AS cat ON {cv.pk}={cat.activeCatalogVersion} "
				+ " JOIN CMSSite AS site ON {cat.pk}={site.defaultCatalog}}  WHERE {site.pk} = ?site";

		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("site", siteModel);
		return doSearch(query, params, RecipeModel.class);
	}

	@Override
	public List<SiteMapUrlData> getSiteMapUrlData(final List<RecipeModel> models)
	{
		return Converters.convertAll(models, getSiteMapUrlDataConverter());
	}
}

