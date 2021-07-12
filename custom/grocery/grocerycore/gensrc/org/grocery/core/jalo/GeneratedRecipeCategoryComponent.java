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
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.grocery.core.constants.GroceryCoreConstants;
import org.grocery.core.jalo.RecipesCategory;

/**
 * Generated class for type {@link org.grocery.core.jalo.RecipeCategoryComponent RecipeCategoryComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedRecipeCategoryComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>RecipeCategoryComponent.viewAllLink</code> attribute **/
	public static final String VIEWALLLINK = "viewAllLink";
	/** Qualifier of the <code>RecipeCategoryComponent.categories</code> attribute **/
	public static final String CATEGORIES = "categories";
	/**
	* {@link OneToManyHandler} for handling 1:n CATEGORIES's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<RecipesCategory> CATEGORIESHANDLER = new OneToManyHandler<RecipesCategory>(
	GroceryCoreConstants.TC.RECIPESCATEGORY,
	false,
	"recipeCategoryComponent",
	null,
	false,
	true,
	CollectionType.LIST
	).withRelationQualifier("categories");
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(VIEWALLLINK, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeCategoryComponent.categories</code> attribute.
	 * @return the categories
	 */
	public List<RecipesCategory> getCategories(final SessionContext ctx)
	{
		return (List<RecipesCategory>)CATEGORIESHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeCategoryComponent.categories</code> attribute.
	 * @return the categories
	 */
	public List<RecipesCategory> getCategories()
	{
		return getCategories( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeCategoryComponent.categories</code> attribute. 
	 * @param value the categories
	 */
	public void setCategories(final SessionContext ctx, final List<RecipesCategory> value)
	{
		CATEGORIESHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeCategoryComponent.categories</code> attribute. 
	 * @param value the categories
	 */
	public void setCategories(final List<RecipesCategory> value)
	{
		setCategories( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to categories. 
	 * @param value the item to add to categories
	 */
	public void addToCategories(final SessionContext ctx, final RecipesCategory value)
	{
		CATEGORIESHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to categories. 
	 * @param value the item to add to categories
	 */
	public void addToCategories(final RecipesCategory value)
	{
		addToCategories( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from categories. 
	 * @param value the item to remove from categories
	 */
	public void removeFromCategories(final SessionContext ctx, final RecipesCategory value)
	{
		CATEGORIESHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from categories. 
	 * @param value the item to remove from categories
	 */
	public void removeFromCategories(final RecipesCategory value)
	{
		removeFromCategories( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeCategoryComponent.viewAllLink</code> attribute.
	 * @return the viewAllLink
	 */
	public String getViewAllLink(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipeCategoryComponent.getViewAllLink requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, VIEWALLLINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeCategoryComponent.viewAllLink</code> attribute.
	 * @return the viewAllLink
	 */
	public String getViewAllLink()
	{
		return getViewAllLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeCategoryComponent.viewAllLink</code> attribute. 
	 * @return the localized viewAllLink
	 */
	public Map<Language,String> getAllViewAllLink(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,VIEWALLLINK,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeCategoryComponent.viewAllLink</code> attribute. 
	 * @return the localized viewAllLink
	 */
	public Map<Language,String> getAllViewAllLink()
	{
		return getAllViewAllLink( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeCategoryComponent.viewAllLink</code> attribute. 
	 * @param value the viewAllLink
	 */
	public void setViewAllLink(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipeCategoryComponent.setViewAllLink requires a session language", 0 );
		}
		setLocalizedProperty(ctx, VIEWALLLINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeCategoryComponent.viewAllLink</code> attribute. 
	 * @param value the viewAllLink
	 */
	public void setViewAllLink(final String value)
	{
		setViewAllLink( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeCategoryComponent.viewAllLink</code> attribute. 
	 * @param value the viewAllLink
	 */
	public void setAllViewAllLink(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,VIEWALLLINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeCategoryComponent.viewAllLink</code> attribute. 
	 * @param value the viewAllLink
	 */
	public void setAllViewAllLink(final Map<Language,String> value)
	{
		setAllViewAllLink( getSession().getSessionContext(), value );
	}
	
}
