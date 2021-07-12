/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 08-Jul-2020, 11:07:38 PM                    ---
 * ----------------------------------------------------------------
 */
package org.grocery.core.jalo;

import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
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
 * Generated class for type {@link org.grocery.core.jalo.StrapLineBannerComponent StrapLineBannerComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedStrapLineBannerComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>StrapLineBannerComponent.urlLink</code> attribute **/
	public static final String URLLINK = "urlLink";
	/** Qualifier of the <code>StrapLineBannerComponent.backgroundColor</code> attribute **/
	public static final String BACKGROUNDCOLOR = "backgroundColor";
	/** Qualifier of the <code>StrapLineBannerComponent.textColor</code> attribute **/
	public static final String TEXTCOLOR = "textColor";
	/** Qualifier of the <code>StrapLineBannerComponent.textName</code> attribute **/
	public static final String TEXTNAME = "textName";
	/** Qualifier of the <code>StrapLineBannerComponent.isCrossButtonRequired</code> attribute **/
	public static final String ISCROSSBUTTONREQUIRED = "isCrossButtonRequired";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(URLLINK, AttributeMode.INITIAL);
		tmp.put(BACKGROUNDCOLOR, AttributeMode.INITIAL);
		tmp.put(TEXTCOLOR, AttributeMode.INITIAL);
		tmp.put(TEXTNAME, AttributeMode.INITIAL);
		tmp.put(ISCROSSBUTTONREQUIRED, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StrapLineBannerComponent.backgroundColor</code> attribute.
	 * @return the backgroundColor
	 */
	public String getBackgroundColor(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BACKGROUNDCOLOR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StrapLineBannerComponent.backgroundColor</code> attribute.
	 * @return the backgroundColor
	 */
	public String getBackgroundColor()
	{
		return getBackgroundColor( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StrapLineBannerComponent.backgroundColor</code> attribute. 
	 * @param value the backgroundColor
	 */
	public void setBackgroundColor(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BACKGROUNDCOLOR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StrapLineBannerComponent.backgroundColor</code> attribute. 
	 * @param value the backgroundColor
	 */
	public void setBackgroundColor(final String value)
	{
		setBackgroundColor( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StrapLineBannerComponent.isCrossButtonRequired</code> attribute.
	 * @return the isCrossButtonRequired - to check cross button required or not
	 */
	public Boolean isIsCrossButtonRequired(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ISCROSSBUTTONREQUIRED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StrapLineBannerComponent.isCrossButtonRequired</code> attribute.
	 * @return the isCrossButtonRequired - to check cross button required or not
	 */
	public Boolean isIsCrossButtonRequired()
	{
		return isIsCrossButtonRequired( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StrapLineBannerComponent.isCrossButtonRequired</code> attribute. 
	 * @return the isCrossButtonRequired - to check cross button required or not
	 */
	public boolean isIsCrossButtonRequiredAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isIsCrossButtonRequired( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StrapLineBannerComponent.isCrossButtonRequired</code> attribute. 
	 * @return the isCrossButtonRequired - to check cross button required or not
	 */
	public boolean isIsCrossButtonRequiredAsPrimitive()
	{
		return isIsCrossButtonRequiredAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StrapLineBannerComponent.isCrossButtonRequired</code> attribute. 
	 * @param value the isCrossButtonRequired - to check cross button required or not
	 */
	public void setIsCrossButtonRequired(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ISCROSSBUTTONREQUIRED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StrapLineBannerComponent.isCrossButtonRequired</code> attribute. 
	 * @param value the isCrossButtonRequired - to check cross button required or not
	 */
	public void setIsCrossButtonRequired(final Boolean value)
	{
		setIsCrossButtonRequired( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StrapLineBannerComponent.isCrossButtonRequired</code> attribute. 
	 * @param value the isCrossButtonRequired - to check cross button required or not
	 */
	public void setIsCrossButtonRequired(final SessionContext ctx, final boolean value)
	{
		setIsCrossButtonRequired( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StrapLineBannerComponent.isCrossButtonRequired</code> attribute. 
	 * @param value the isCrossButtonRequired - to check cross button required or not
	 */
	public void setIsCrossButtonRequired(final boolean value)
	{
		setIsCrossButtonRequired( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StrapLineBannerComponent.textColor</code> attribute.
	 * @return the textColor
	 */
	public String getTextColor(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TEXTCOLOR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StrapLineBannerComponent.textColor</code> attribute.
	 * @return the textColor
	 */
	public String getTextColor()
	{
		return getTextColor( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StrapLineBannerComponent.textColor</code> attribute. 
	 * @param value the textColor
	 */
	public void setTextColor(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TEXTCOLOR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StrapLineBannerComponent.textColor</code> attribute. 
	 * @param value the textColor
	 */
	public void setTextColor(final String value)
	{
		setTextColor( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StrapLineBannerComponent.textName</code> attribute.
	 * @return the textName
	 */
	public String getTextName(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedStrapLineBannerComponent.getTextName requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TEXTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StrapLineBannerComponent.textName</code> attribute.
	 * @return the textName
	 */
	public String getTextName()
	{
		return getTextName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StrapLineBannerComponent.textName</code> attribute. 
	 * @return the localized textName
	 */
	public Map<Language,String> getAllTextName(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TEXTNAME,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StrapLineBannerComponent.textName</code> attribute. 
	 * @return the localized textName
	 */
	public Map<Language,String> getAllTextName()
	{
		return getAllTextName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StrapLineBannerComponent.textName</code> attribute. 
	 * @param value the textName
	 */
	public void setTextName(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedStrapLineBannerComponent.setTextName requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TEXTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StrapLineBannerComponent.textName</code> attribute. 
	 * @param value the textName
	 */
	public void setTextName(final String value)
	{
		setTextName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StrapLineBannerComponent.textName</code> attribute. 
	 * @param value the textName
	 */
	public void setAllTextName(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TEXTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StrapLineBannerComponent.textName</code> attribute. 
	 * @param value the textName
	 */
	public void setAllTextName(final Map<Language,String> value)
	{
		setAllTextName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StrapLineBannerComponent.urlLink</code> attribute.
	 * @return the urlLink
	 */
	public CMSLinkComponent getUrlLink(final SessionContext ctx)
	{
		return (CMSLinkComponent)getProperty( ctx, URLLINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StrapLineBannerComponent.urlLink</code> attribute.
	 * @return the urlLink
	 */
	public CMSLinkComponent getUrlLink()
	{
		return getUrlLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StrapLineBannerComponent.urlLink</code> attribute. 
	 * @param value the urlLink
	 */
	public void setUrlLink(final SessionContext ctx, final CMSLinkComponent value)
	{
		setProperty(ctx, URLLINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StrapLineBannerComponent.urlLink</code> attribute. 
	 * @param value the urlLink
	 */
	public void setUrlLink(final CMSLinkComponent value)
	{
		setUrlLink( getSession().getSessionContext(), value );
	}
	
}
