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
import org.grocery.core.jalo.Recipe;

/**
 * Generated class for type {@link org.grocery.core.jalo.RecipeThemeComponent RecipeThemeComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedRecipeThemeComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>RecipeThemeComponent.headline</code> attribute **/
	public static final String HEADLINE = "headline";
	/** Qualifier of the <code>RecipeThemeComponent.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/** Qualifier of the <code>RecipeThemeComponent.linkName</code> attribute **/
	public static final String LINKNAME = "linkName";
	/** Qualifier of the <code>RecipeThemeComponent.linkURL</code> attribute **/
	public static final String LINKURL = "linkURL";
	/** Qualifier of the <code>RecipeThemeComponent.recipes</code> attribute **/
	public static final String RECIPES = "recipes";
	/**
	* {@link OneToManyHandler} for handling 1:n RECIPES's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<Recipe> RECIPESHANDLER = new OneToManyHandler<Recipe>(
	GroceryCoreConstants.TC.RECIPE,
	false,
	"recipeThemeComponent",
	null,
	false,
	true,
	CollectionType.LIST
	).withRelationQualifier("recipes");
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HEADLINE, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		tmp.put(LINKNAME, AttributeMode.INITIAL);
		tmp.put(LINKURL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.description</code> attribute.
	 * @return the description
	 */
	public String getDescription(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipeThemeComponent.getDescription requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.description</code> attribute.
	 * @return the description
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.description</code> attribute. 
	 * @return the localized description
	 */
	public Map<Language,String> getAllDescription(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,DESCRIPTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.description</code> attribute. 
	 * @return the localized description
	 */
	public Map<Language,String> getAllDescription()
	{
		return getAllDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipeThemeComponent.setDescription requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.description</code> attribute. 
	 * @param value the description
	 */
	public void setAllDescription(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.description</code> attribute. 
	 * @param value the description
	 */
	public void setAllDescription(final Map<Language,String> value)
	{
		setAllDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.headline</code> attribute.
	 * @return the headline
	 */
	public String getHeadline(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipeThemeComponent.getHeadline requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, HEADLINE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.headline</code> attribute.
	 * @return the headline
	 */
	public String getHeadline()
	{
		return getHeadline( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.headline</code> attribute. 
	 * @return the localized headline
	 */
	public Map<Language,String> getAllHeadline(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,HEADLINE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.headline</code> attribute. 
	 * @return the localized headline
	 */
	public Map<Language,String> getAllHeadline()
	{
		return getAllHeadline( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.headline</code> attribute. 
	 * @param value the headline
	 */
	public void setHeadline(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipeThemeComponent.setHeadline requires a session language", 0 );
		}
		setLocalizedProperty(ctx, HEADLINE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.headline</code> attribute. 
	 * @param value the headline
	 */
	public void setHeadline(final String value)
	{
		setHeadline( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.headline</code> attribute. 
	 * @param value the headline
	 */
	public void setAllHeadline(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,HEADLINE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.headline</code> attribute. 
	 * @param value the headline
	 */
	public void setAllHeadline(final Map<Language,String> value)
	{
		setAllHeadline( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.linkName</code> attribute.
	 * @return the linkName
	 */
	public String getLinkName(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipeThemeComponent.getLinkName requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, LINKNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.linkName</code> attribute.
	 * @return the linkName
	 */
	public String getLinkName()
	{
		return getLinkName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.linkName</code> attribute. 
	 * @return the localized linkName
	 */
	public Map<Language,String> getAllLinkName(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,LINKNAME,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.linkName</code> attribute. 
	 * @return the localized linkName
	 */
	public Map<Language,String> getAllLinkName()
	{
		return getAllLinkName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.linkName</code> attribute. 
	 * @param value the linkName
	 */
	public void setLinkName(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipeThemeComponent.setLinkName requires a session language", 0 );
		}
		setLocalizedProperty(ctx, LINKNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.linkName</code> attribute. 
	 * @param value the linkName
	 */
	public void setLinkName(final String value)
	{
		setLinkName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.linkName</code> attribute. 
	 * @param value the linkName
	 */
	public void setAllLinkName(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,LINKNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.linkName</code> attribute. 
	 * @param value the linkName
	 */
	public void setAllLinkName(final Map<Language,String> value)
	{
		setAllLinkName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.linkURL</code> attribute.
	 * @return the linkURL
	 */
	public String getLinkURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LINKURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.linkURL</code> attribute.
	 * @return the linkURL
	 */
	public String getLinkURL()
	{
		return getLinkURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.linkURL</code> attribute. 
	 * @param value the linkURL
	 */
	public void setLinkURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LINKURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.linkURL</code> attribute. 
	 * @param value the linkURL
	 */
	public void setLinkURL(final String value)
	{
		setLinkURL( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.recipes</code> attribute.
	 * @return the recipes
	 */
	public List<Recipe> getRecipes(final SessionContext ctx)
	{
		return (List<Recipe>)RECIPESHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeThemeComponent.recipes</code> attribute.
	 * @return the recipes
	 */
	public List<Recipe> getRecipes()
	{
		return getRecipes( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.recipes</code> attribute. 
	 * @param value the recipes
	 */
	public void setRecipes(final SessionContext ctx, final List<Recipe> value)
	{
		RECIPESHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeThemeComponent.recipes</code> attribute. 
	 * @param value the recipes
	 */
	public void setRecipes(final List<Recipe> value)
	{
		setRecipes( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to recipes. 
	 * @param value the item to add to recipes
	 */
	public void addToRecipes(final SessionContext ctx, final Recipe value)
	{
		RECIPESHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to recipes. 
	 * @param value the item to add to recipes
	 */
	public void addToRecipes(final Recipe value)
	{
		addToRecipes( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from recipes. 
	 * @param value the item to remove from recipes
	 */
	public void removeFromRecipes(final SessionContext ctx, final Recipe value)
	{
		RECIPESHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from recipes. 
	 * @param value the item to remove from recipes
	 */
	public void removeFromRecipes(final Recipe value)
	{
		removeFromRecipes( getSession().getSessionContext(), value );
	}
	
}
