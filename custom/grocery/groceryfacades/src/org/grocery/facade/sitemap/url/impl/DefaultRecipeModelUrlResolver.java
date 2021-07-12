package org.grocery.facade.url.impl;

import de.hybris.platform.commerceservices.url.impl.AbstractUrlResolver;

import org.grocery.core.model.RecipeModel;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author kirtimittal
 *
 */
public class DefaultRecipeModelUrlResolver extends AbstractUrlResolver<RecipeModel>
{
	private final String CACHE_KEY = DefaultRecipeModelUrlResolver.class.getName();
	private String pattern;

	protected String getPattern()
	{
		return pattern;
	}

	@Required
	public void setPattern(final String pattern)
	{
		this.pattern = pattern;
	}

	@Override
	protected String getKey(final RecipeModel source)
	{
		return CACHE_KEY + "." + source.getPk().toString();
	}

	@Override
	protected String resolveInternal(final RecipeModel recipe)
	{
		// example url: /r/recipe_code
		String url = getPattern();

		if (url.contains("{recipe_code}"))
		{
			url = url.replace("{recipe_code}", recipe.getCode());
		}
		return url;
	}
}
