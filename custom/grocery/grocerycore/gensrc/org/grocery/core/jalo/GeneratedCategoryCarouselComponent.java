/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 08-Jul-2020, 11:07:38 PM                    ---
 * ----------------------------------------------------------------
 */
package org.grocery.core.jalo;

import de.hybris.platform.cms2lib.components.ProductCarouselComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.cms2lib.components.ProductCarouselComponent CategoryCarouselComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedCategoryCarouselComponent extends ProductCarouselComponent
{
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(ProductCarouselComponent.DEFAULT_INITIAL_ATTRIBUTES);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
}
