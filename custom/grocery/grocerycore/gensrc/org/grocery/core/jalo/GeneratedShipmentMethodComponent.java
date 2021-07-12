/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 08-Jul-2020, 11:07:38 PM                    ---
 * ----------------------------------------------------------------
 */
package org.grocery.core.jalo;

import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.media.Media;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.grocery.core.constants.GroceryCoreConstants;

/**
 * Generated class for type {@link org.grocery.core.jalo.ShipmentMethodComponent ShipmentMethodComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedShipmentMethodComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>ShipmentMethodComponent.title</code> attribute **/
	public static final String TITLE = "title";
	/** Qualifier of the <code>ShipmentMethodComponent.emptyCartImage</code> attribute **/
	public static final String EMPTYCARTIMAGE = "emptyCartImage";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(TITLE, AttributeMode.INITIAL);
		tmp.put(EMPTYCARTIMAGE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShipmentMethodComponent.emptyCartImage</code> attribute.
	 * @return the emptyCartImage
	 */
	public Media getEmptyCartImage(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, EMPTYCARTIMAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShipmentMethodComponent.emptyCartImage</code> attribute.
	 * @return the emptyCartImage
	 */
	public Media getEmptyCartImage()
	{
		return getEmptyCartImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShipmentMethodComponent.emptyCartImage</code> attribute. 
	 * @param value the emptyCartImage
	 */
	public void setEmptyCartImage(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, EMPTYCARTIMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShipmentMethodComponent.emptyCartImage</code> attribute. 
	 * @param value the emptyCartImage
	 */
	public void setEmptyCartImage(final Media value)
	{
		setEmptyCartImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShipmentMethodComponent.title</code> attribute.
	 * @return the title - It is localized title of the component.
	 */
	public String getTitle(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedShipmentMethodComponent.getTitle requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShipmentMethodComponent.title</code> attribute.
	 * @return the title - It is localized title of the component.
	 */
	public String getTitle()
	{
		return getTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShipmentMethodComponent.title</code> attribute. 
	 * @return the localized title - It is localized title of the component.
	 */
	public Map<Language,String> getAllTitle(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TITLE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ShipmentMethodComponent.title</code> attribute. 
	 * @return the localized title - It is localized title of the component.
	 */
	public Map<Language,String> getAllTitle()
	{
		return getAllTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShipmentMethodComponent.title</code> attribute. 
	 * @param value the title - It is localized title of the component.
	 */
	public void setTitle(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedShipmentMethodComponent.setTitle requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShipmentMethodComponent.title</code> attribute. 
	 * @param value the title - It is localized title of the component.
	 */
	public void setTitle(final String value)
	{
		setTitle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShipmentMethodComponent.title</code> attribute. 
	 * @param value the title - It is localized title of the component.
	 */
	public void setAllTitle(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ShipmentMethodComponent.title</code> attribute. 
	 * @param value the title - It is localized title of the component.
	 */
	public void setAllTitle(final Map<Language,String> value)
	{
		setAllTitle( getSession().getSessionContext(), value );
	}
	
}
