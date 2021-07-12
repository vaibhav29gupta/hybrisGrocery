/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 08-Jul-2020, 11:07:38 PM                    ---
 * ----------------------------------------------------------------
 */
package org.grocery.core.jalo;

import de.hybris.platform.acceleratorcms.jalo.components.SimpleResponsiveBannerComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.grocery.core.constants.GroceryCoreConstants;

/**
 * Generated class for type {@link org.grocery.core.jalo.ImageOverLayComponent ImageOverLayComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedImageOverLayComponent extends SimpleResponsiveBannerComponent
{
	/** Qualifier of the <code>ImageOverLayComponent.heading</code> attribute **/
	public static final String HEADING = "heading";
	/** Qualifier of the <code>ImageOverLayComponent.subheading</code> attribute **/
	public static final String SUBHEADING = "subheading";
	/** Qualifier of the <code>ImageOverLayComponent.btnText</code> attribute **/
	public static final String BTNTEXT = "btnText";
	/** Qualifier of the <code>ImageOverLayComponent.btnUrlLink</code> attribute **/
	public static final String BTNURLLINK = "btnUrlLink";
	/** Qualifier of the <code>ImageOverLayComponent.fontSize</code> attribute **/
	public static final String FONTSIZE = "fontSize";
	/** Qualifier of the <code>ImageOverLayComponent.position</code> attribute **/
	public static final String POSITION = "position";
	/** Qualifier of the <code>ImageOverLayComponent.theme</code> attribute **/
	public static final String THEME = "theme";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleResponsiveBannerComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADING, AttributeMode.INITIAL);
		tmp.put(SUBHEADING, AttributeMode.INITIAL);
		tmp.put(BTNTEXT, AttributeMode.INITIAL);
		tmp.put(BTNURLLINK, AttributeMode.INITIAL);
		tmp.put(FONTSIZE, AttributeMode.INITIAL);
		tmp.put(POSITION, AttributeMode.INITIAL);
		tmp.put(THEME, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.btnText</code> attribute.
	 * @return the btnText
	 */
	public String getBtnText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedImageOverLayComponent.getBtnText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, BTNTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.btnText</code> attribute.
	 * @return the btnText
	 */
	public String getBtnText()
	{
		return getBtnText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.btnText</code> attribute. 
	 * @return the localized btnText
	 */
	public Map<Language,String> getAllBtnText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,BTNTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.btnText</code> attribute. 
	 * @return the localized btnText
	 */
	public Map<Language,String> getAllBtnText()
	{
		return getAllBtnText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.btnText</code> attribute. 
	 * @param value the btnText
	 */
	public void setBtnText(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedImageOverLayComponent.setBtnText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, BTNTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.btnText</code> attribute. 
	 * @param value the btnText
	 */
	public void setBtnText(final String value)
	{
		setBtnText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.btnText</code> attribute. 
	 * @param value the btnText
	 */
	public void setAllBtnText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,BTNTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.btnText</code> attribute. 
	 * @param value the btnText
	 */
	public void setAllBtnText(final Map<Language,String> value)
	{
		setAllBtnText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.btnUrlLink</code> attribute.
	 * @return the btnUrlLink
	 */
	public String getBtnUrlLink(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BTNURLLINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.btnUrlLink</code> attribute.
	 * @return the btnUrlLink
	 */
	public String getBtnUrlLink()
	{
		return getBtnUrlLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.btnUrlLink</code> attribute. 
	 * @param value the btnUrlLink
	 */
	public void setBtnUrlLink(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BTNURLLINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.btnUrlLink</code> attribute. 
	 * @param value the btnUrlLink
	 */
	public void setBtnUrlLink(final String value)
	{
		setBtnUrlLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.fontSize</code> attribute.
	 * @return the fontSize
	 */
	public String getFontSize(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FONTSIZE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.fontSize</code> attribute.
	 * @return the fontSize
	 */
	public String getFontSize()
	{
		return getFontSize( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.fontSize</code> attribute. 
	 * @param value the fontSize
	 */
	public void setFontSize(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FONTSIZE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.fontSize</code> attribute. 
	 * @param value the fontSize
	 */
	public void setFontSize(final String value)
	{
		setFontSize( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.heading</code> attribute.
	 * @return the heading
	 */
	public String getHeading(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedImageOverLayComponent.getHeading requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.heading</code> attribute.
	 * @return the heading
	 */
	public String getHeading()
	{
		return getHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.heading</code> attribute. 
	 * @return the localized heading
	 */
	public Map<Language,String> getAllHeading(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADING,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.heading</code> attribute. 
	 * @return the localized heading
	 */
	public Map<Language,String> getAllHeading()
	{
		return getAllHeading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.heading</code> attribute. 
	 * @param value the heading
	 */
	public void setHeading(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedImageOverLayComponent.setHeading requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.heading</code> attribute. 
	 * @param value the heading
	 */
	public void setHeading(final String value)
	{
		setHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.heading</code> attribute. 
	 * @param value the heading
	 */
	public void setAllHeading(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.heading</code> attribute. 
	 * @param value the heading
	 */
	public void setAllHeading(final Map<Language,String> value)
	{
		setAllHeading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.position</code> attribute.
	 * @return the position
	 */
	public String getPosition(final SessionContext ctx)
	{
		return (String)getProperty( ctx, POSITION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.position</code> attribute.
	 * @return the position
	 */
	public String getPosition()
	{
		return getPosition( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.position</code> attribute. 
	 * @param value the position
	 */
	public void setPosition(final SessionContext ctx, final String value)
	{
		setProperty(ctx, POSITION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.position</code> attribute. 
	 * @param value the position
	 */
	public void setPosition(final String value)
	{
		setPosition( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.subheading</code> attribute.
	 * @return the subheading
	 */
	public String getSubheading(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedImageOverLayComponent.getSubheading requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, SUBHEADING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.subheading</code> attribute.
	 * @return the subheading
	 */
	public String getSubheading()
	{
		return getSubheading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.subheading</code> attribute. 
	 * @return the localized subheading
	 */
	public Map<Language,String> getAllSubheading(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,SUBHEADING,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.subheading</code> attribute. 
	 * @return the localized subheading
	 */
	public Map<Language,String> getAllSubheading()
	{
		return getAllSubheading( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.subheading</code> attribute. 
	 * @param value the subheading
	 */
	public void setSubheading(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedImageOverLayComponent.setSubheading requires a session language", 0 );
		}
		setLocalizedProperty(ctx, SUBHEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.subheading</code> attribute. 
	 * @param value the subheading
	 */
	public void setSubheading(final String value)
	{
		setSubheading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.subheading</code> attribute. 
	 * @param value the subheading
	 */
	public void setAllSubheading(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,SUBHEADING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.subheading</code> attribute. 
	 * @param value the subheading
	 */
	public void setAllSubheading(final Map<Language,String> value)
	{
		setAllSubheading( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.theme</code> attribute.
	 * @return the theme
	 */
	public String getTheme(final SessionContext ctx)
	{
		return (String)getProperty( ctx, THEME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ImageOverLayComponent.theme</code> attribute.
	 * @return the theme
	 */
	public String getTheme()
	{
		return getTheme( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.theme</code> attribute. 
	 * @param value the theme
	 */
	public void setTheme(final SessionContext ctx, final String value)
	{
		setProperty(ctx, THEME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ImageOverLayComponent.theme</code> attribute. 
	 * @param value the theme
	 */
	public void setTheme(final String value)
	{
		setTheme( getSession().getSessionContext(), value );
	}
	
}
