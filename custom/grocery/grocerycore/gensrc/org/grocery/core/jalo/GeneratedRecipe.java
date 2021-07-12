/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 08-Jul-2020, 11:07:38 PM                    ---
 * ----------------------------------------------------------------
 */
package org.grocery.core.jalo;

import de.hybris.platform.catalog.jalo.CatalogVersion;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.media.Media;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.TypeManager;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import de.hybris.platform.util.Utilities;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.grocery.core.constants.GroceryCoreConstants;
import org.grocery.core.jalo.Ingredient;
import org.grocery.core.jalo.IngredientsGroup;
import org.grocery.core.jalo.RecipeHighlightComponent;
import org.grocery.core.jalo.RecipeThemeComponent;
import org.grocery.core.jalo.RecipesCategory;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem Recipe}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedRecipe extends GenericItem
{
	/** Qualifier of the <code>Recipe.method</code> attribute **/
	public static final String METHOD = "method";
	/** Qualifier of the <code>Recipe.ingredientsAsText</code> attribute **/
	public static final String INGREDIENTSASTEXT = "ingredientsAsText";
	/** Qualifier of the <code>Recipe.serves</code> attribute **/
	public static final String SERVES = "serves";
	/** Qualifier of the <code>Recipe.cookingTime</code> attribute **/
	public static final String COOKINGTIME = "cookingTime";
	/** Qualifier of the <code>Recipe.prepTime</code> attribute **/
	public static final String PREPTIME = "prepTime";
	/** Qualifier of the <code>Recipe.name</code> attribute **/
	public static final String NAME = "name";
	/** Qualifier of the <code>Recipe.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/** Qualifier of the <code>Recipe.media</code> attribute **/
	public static final String MEDIA = "media";
	/** Qualifier of the <code>Recipe.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>Recipe.videoURL</code> attribute **/
	public static final String VIDEOURL = "videoURL";
	/** Qualifier of the <code>Recipe.catalogVersion</code> attribute **/
	public static final String CATALOGVERSION = "catalogVersion";
	/** Qualifier of the <code>Recipe.category</code> attribute **/
	public static final String CATEGORY = "category";
	/** Relation ordering override parameter constants for RecipesCategory2RecipesRelation from ((grocerycore))*/
	protected static String RECIPESCATEGORY2RECIPESRELATION_SRC_ORDERED = "relation.RecipesCategory2RecipesRelation.source.ordered";
	protected static String RECIPESCATEGORY2RECIPESRELATION_TGT_ORDERED = "relation.RecipesCategory2RecipesRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for RecipesCategory2RecipesRelation from ((grocerycore))*/
	protected static String RECIPESCATEGORY2RECIPESRELATION_MARKMODIFIED = "relation.RecipesCategory2RecipesRelation.markmodified";
	/** Qualifier of the <code>Recipe.group</code> attribute **/
	public static final String GROUP = "group";
	/** Relation ordering override parameter constants for Recipe2IngredientGroupsRelation from ((grocerycore))*/
	protected static String RECIPE2INGREDIENTGROUPSRELATION_SRC_ORDERED = "relation.Recipe2IngredientGroupsRelation.source.ordered";
	protected static String RECIPE2INGREDIENTGROUPSRELATION_TGT_ORDERED = "relation.Recipe2IngredientGroupsRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for Recipe2IngredientGroupsRelation from ((grocerycore))*/
	protected static String RECIPE2INGREDIENTGROUPSRELATION_MARKMODIFIED = "relation.Recipe2IngredientGroupsRelation.markmodified";
	/** Qualifier of the <code>Recipe.uncategorizedIngredient</code> attribute **/
	public static final String UNCATEGORIZEDINGREDIENT = "uncategorizedIngredient";
	/** Relation ordering override parameter constants for Recipe2IngredientsRelation from ((grocerycore))*/
	protected static String RECIPE2INGREDIENTSRELATION_SRC_ORDERED = "relation.Recipe2IngredientsRelation.source.ordered";
	protected static String RECIPE2INGREDIENTSRELATION_TGT_ORDERED = "relation.Recipe2IngredientsRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for Recipe2IngredientsRelation from ((grocerycore))*/
	protected static String RECIPE2INGREDIENTSRELATION_MARKMODIFIED = "relation.Recipe2IngredientsRelation.markmodified";
	/** Qualifier of the <code>Recipe.recipeHighlightComponent</code> attribute **/
	public static final String RECIPEHIGHLIGHTCOMPONENT = "recipeHighlightComponent";
	/** Qualifier of the <code>Recipe.recipeThemeComponent</code> attribute **/
	public static final String RECIPETHEMECOMPONENT = "recipeThemeComponent";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n RECIPEHIGHLIGHTCOMPONENT's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedRecipe> RECIPEHIGHLIGHTCOMPONENTHANDLER = new BidirectionalOneToManyHandler<GeneratedRecipe>(
	GroceryCoreConstants.TC.RECIPE,
	false,
	"recipeHighlightComponent",
	null,
	false,
	true,
	CollectionType.LIST
	);
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n RECIPETHEMECOMPONENT's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedRecipe> RECIPETHEMECOMPONENTHANDLER = new BidirectionalOneToManyHandler<GeneratedRecipe>(
	GroceryCoreConstants.TC.RECIPE,
	false,
	"recipeThemeComponent",
	null,
	false,
	true,
	CollectionType.LIST
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(METHOD, AttributeMode.INITIAL);
		tmp.put(INGREDIENTSASTEXT, AttributeMode.INITIAL);
		tmp.put(SERVES, AttributeMode.INITIAL);
		tmp.put(COOKINGTIME, AttributeMode.INITIAL);
		tmp.put(PREPTIME, AttributeMode.INITIAL);
		tmp.put(NAME, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		tmp.put(MEDIA, AttributeMode.INITIAL);
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(VIDEOURL, AttributeMode.INITIAL);
		tmp.put(CATALOGVERSION, AttributeMode.INITIAL);
		tmp.put(RECIPEHIGHLIGHTCOMPONENT, AttributeMode.INITIAL);
		tmp.put(RECIPETHEMECOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.catalogVersion</code> attribute.
	 * @return the catalogVersion
	 */
	public CatalogVersion getCatalogVersion(final SessionContext ctx)
	{
		return (CatalogVersion)getProperty( ctx, CATALOGVERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.catalogVersion</code> attribute.
	 * @return the catalogVersion
	 */
	public CatalogVersion getCatalogVersion()
	{
		return getCatalogVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.catalogVersion</code> attribute. 
	 * @param value the catalogVersion
	 */
	public void setCatalogVersion(final SessionContext ctx, final CatalogVersion value)
	{
		setProperty(ctx, CATALOGVERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.catalogVersion</code> attribute. 
	 * @param value the catalogVersion
	 */
	public void setCatalogVersion(final CatalogVersion value)
	{
		setCatalogVersion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.category</code> attribute.
	 * @return the category - Recipes Category
	 */
	public Collection<RecipesCategory> getCategory(final SessionContext ctx)
	{
		final List<RecipesCategory> items = getLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.RECIPESCATEGORY2RECIPESRELATION,
			"RecipesCategory",
			null,
			Utilities.getRelationOrderingOverride(RECIPESCATEGORY2RECIPESRELATION_SRC_ORDERED, true),
			false
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.category</code> attribute.
	 * @return the category - Recipes Category
	 */
	public Collection<RecipesCategory> getCategory()
	{
		return getCategory( getSession().getSessionContext() );
	}
	
	public long getCategoryCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			false,
			GroceryCoreConstants.Relations.RECIPESCATEGORY2RECIPESRELATION,
			"RecipesCategory",
			null
		);
	}
	
	public long getCategoryCount()
	{
		return getCategoryCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.category</code> attribute. 
	 * @param value the category - Recipes Category
	 */
	public void setCategory(final SessionContext ctx, final Collection<RecipesCategory> value)
	{
		setLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.RECIPESCATEGORY2RECIPESRELATION,
			null,
			value,
			Utilities.getRelationOrderingOverride(RECIPESCATEGORY2RECIPESRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPESCATEGORY2RECIPESRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.category</code> attribute. 
	 * @param value the category - Recipes Category
	 */
	public void setCategory(final Collection<RecipesCategory> value)
	{
		setCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to category. 
	 * @param value the item to add to category - Recipes Category
	 */
	public void addToCategory(final SessionContext ctx, final RecipesCategory value)
	{
		addLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.RECIPESCATEGORY2RECIPESRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(RECIPESCATEGORY2RECIPESRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPESCATEGORY2RECIPESRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to category. 
	 * @param value the item to add to category - Recipes Category
	 */
	public void addToCategory(final RecipesCategory value)
	{
		addToCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from category. 
	 * @param value the item to remove from category - Recipes Category
	 */
	public void removeFromCategory(final SessionContext ctx, final RecipesCategory value)
	{
		removeLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.RECIPESCATEGORY2RECIPESRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(RECIPESCATEGORY2RECIPESRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPESCATEGORY2RECIPESRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from category. 
	 * @param value the item to remove from category - Recipes Category
	 */
	public void removeFromCategory(final RecipesCategory value)
	{
		removeFromCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.cookingTime</code> attribute.
	 * @return the cookingTime
	 */
	public String getCookingTime(final SessionContext ctx)
	{
		return (String)getProperty( ctx, COOKINGTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.cookingTime</code> attribute.
	 * @return the cookingTime
	 */
	public String getCookingTime()
	{
		return getCookingTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.cookingTime</code> attribute. 
	 * @param value the cookingTime
	 */
	public void setCookingTime(final SessionContext ctx, final String value)
	{
		setProperty(ctx, COOKINGTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.cookingTime</code> attribute. 
	 * @param value the cookingTime
	 */
	public void setCookingTime(final String value)
	{
		setCookingTime( getSession().getSessionContext(), value );
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		RECIPEHIGHLIGHTCOMPONENTHANDLER.newInstance(ctx, allAttributes);
		RECIPETHEMECOMPONENTHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.description</code> attribute.
	 * @return the description
	 */
	public String getDescription(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipe.getDescription requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.description</code> attribute.
	 * @return the description
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.description</code> attribute. 
	 * @return the localized description
	 */
	public Map<Language,String> getAllDescription(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,DESCRIPTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.description</code> attribute. 
	 * @return the localized description
	 */
	public Map<Language,String> getAllDescription()
	{
		return getAllDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.description</code> attribute. 
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
			throw new JaloInvalidParameterException("GeneratedRecipe.setDescription requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.description</code> attribute. 
	 * @param value the description
	 */
	public void setAllDescription(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.description</code> attribute. 
	 * @param value the description
	 */
	public void setAllDescription(final Map<Language,String> value)
	{
		setAllDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.group</code> attribute.
	 * @return the group - Ingredients Group
	 */
	public List<IngredientsGroup> getGroup(final SessionContext ctx)
	{
		final List<IngredientsGroup> items = getLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTGROUPSRELATION,
			"IngredientsGroup",
			null,
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTGROUPSRELATION_SRC_ORDERED, true),
			false
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.group</code> attribute.
	 * @return the group - Ingredients Group
	 */
	public List<IngredientsGroup> getGroup()
	{
		return getGroup( getSession().getSessionContext() );
	}
	
	public long getGroupCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			true,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTGROUPSRELATION,
			"IngredientsGroup",
			null
		);
	}
	
	public long getGroupCount()
	{
		return getGroupCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.group</code> attribute. 
	 * @param value the group - Ingredients Group
	 */
	public void setGroup(final SessionContext ctx, final List<IngredientsGroup> value)
	{
		setLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTGROUPSRELATION,
			null,
			value,
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTGROUPSRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTGROUPSRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.group</code> attribute. 
	 * @param value the group - Ingredients Group
	 */
	public void setGroup(final List<IngredientsGroup> value)
	{
		setGroup( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to group. 
	 * @param value the item to add to group - Ingredients Group
	 */
	public void addToGroup(final SessionContext ctx, final IngredientsGroup value)
	{
		addLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTGROUPSRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTGROUPSRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTGROUPSRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to group. 
	 * @param value the item to add to group - Ingredients Group
	 */
	public void addToGroup(final IngredientsGroup value)
	{
		addToGroup( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from group. 
	 * @param value the item to remove from group - Ingredients Group
	 */
	public void removeFromGroup(final SessionContext ctx, final IngredientsGroup value)
	{
		removeLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTGROUPSRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTGROUPSRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTGROUPSRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from group. 
	 * @param value the item to remove from group - Ingredients Group
	 */
	public void removeFromGroup(final IngredientsGroup value)
	{
		removeFromGroup( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.ingredientsAsText</code> attribute.
	 * @return the ingredientsAsText
	 */
	public String getIngredientsAsText(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipe.getIngredientsAsText requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, INGREDIENTSASTEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.ingredientsAsText</code> attribute.
	 * @return the ingredientsAsText
	 */
	public String getIngredientsAsText()
	{
		return getIngredientsAsText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.ingredientsAsText</code> attribute. 
	 * @return the localized ingredientsAsText
	 */
	public Map<Language,String> getAllIngredientsAsText(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,INGREDIENTSASTEXT,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.ingredientsAsText</code> attribute. 
	 * @return the localized ingredientsAsText
	 */
	public Map<Language,String> getAllIngredientsAsText()
	{
		return getAllIngredientsAsText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.ingredientsAsText</code> attribute. 
	 * @param value the ingredientsAsText
	 */
	public void setIngredientsAsText(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipe.setIngredientsAsText requires a session language", 0 );
		}
		setLocalizedProperty(ctx, INGREDIENTSASTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.ingredientsAsText</code> attribute. 
	 * @param value the ingredientsAsText
	 */
	public void setIngredientsAsText(final String value)
	{
		setIngredientsAsText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.ingredientsAsText</code> attribute. 
	 * @param value the ingredientsAsText
	 */
	public void setAllIngredientsAsText(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,INGREDIENTSASTEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.ingredientsAsText</code> attribute. 
	 * @param value the ingredientsAsText
	 */
	public void setAllIngredientsAsText(final Map<Language,String> value)
	{
		setAllIngredientsAsText( getSession().getSessionContext(), value );
	}
	
	@Override
	public boolean isMarkModifiedDisabled(final Item referencedItem)
	{
		ComposedType relationSecondEnd0 = TypeManager.getInstance().getComposedType("RecipesCategory");
		if(relationSecondEnd0.isAssignableFrom(referencedItem.getComposedType()))
		{
			return Utilities.getMarkModifiedOverride(RECIPESCATEGORY2RECIPESRELATION_MARKMODIFIED);
		}
		ComposedType relationSecondEnd1 = TypeManager.getInstance().getComposedType("IngredientsGroup");
		if(relationSecondEnd1.isAssignableFrom(referencedItem.getComposedType()))
		{
			return Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTGROUPSRELATION_MARKMODIFIED);
		}
		ComposedType relationSecondEnd2 = TypeManager.getInstance().getComposedType("Ingredient");
		if(relationSecondEnd2.isAssignableFrom(referencedItem.getComposedType()))
		{
			return Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTSRELATION_MARKMODIFIED);
		}
		return true;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.media</code> attribute.
	 * @return the media
	 */
	public Media getMedia(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipe.getMedia requires a session language", 0 );
		}
		return (Media)getLocalizedProperty( ctx, MEDIA);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.media</code> attribute.
	 * @return the media
	 */
	public Media getMedia()
	{
		return getMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.media</code> attribute. 
	 * @return the localized media
	 */
	public Map<Language,Media> getAllMedia(final SessionContext ctx)
	{
		return (Map<Language,Media>)getAllLocalizedProperties(ctx,MEDIA,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.media</code> attribute. 
	 * @return the localized media
	 */
	public Map<Language,Media> getAllMedia()
	{
		return getAllMedia( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.media</code> attribute. 
	 * @param value the media
	 */
	public void setMedia(final SessionContext ctx, final Media value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipe.setMedia requires a session language", 0 );
		}
		setLocalizedProperty(ctx, MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.media</code> attribute. 
	 * @param value the media
	 */
	public void setMedia(final Media value)
	{
		setMedia( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.media</code> attribute. 
	 * @param value the media
	 */
	public void setAllMedia(final SessionContext ctx, final Map<Language,Media> value)
	{
		setAllLocalizedProperties(ctx,MEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.media</code> attribute. 
	 * @param value the media
	 */
	public void setAllMedia(final Map<Language,Media> value)
	{
		setAllMedia( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.method</code> attribute.
	 * @return the method
	 */
	public String getMethod(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipe.getMethod requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, METHOD);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.method</code> attribute.
	 * @return the method
	 */
	public String getMethod()
	{
		return getMethod( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.method</code> attribute. 
	 * @return the localized method
	 */
	public Map<Language,String> getAllMethod(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,METHOD,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.method</code> attribute. 
	 * @return the localized method
	 */
	public Map<Language,String> getAllMethod()
	{
		return getAllMethod( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.method</code> attribute. 
	 * @param value the method
	 */
	public void setMethod(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipe.setMethod requires a session language", 0 );
		}
		setLocalizedProperty(ctx, METHOD,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.method</code> attribute. 
	 * @param value the method
	 */
	public void setMethod(final String value)
	{
		setMethod( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.method</code> attribute. 
	 * @param value the method
	 */
	public void setAllMethod(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,METHOD,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.method</code> attribute. 
	 * @param value the method
	 */
	public void setAllMethod(final Map<Language,String> value)
	{
		setAllMethod( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.name</code> attribute.
	 * @return the name
	 */
	public String getName(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipe.getName requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, NAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.name</code> attribute.
	 * @return the name
	 */
	public String getName()
	{
		return getName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.name</code> attribute. 
	 * @return the localized name
	 */
	public Map<Language,String> getAllName(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,NAME,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.name</code> attribute. 
	 * @return the localized name
	 */
	public Map<Language,String> getAllName()
	{
		return getAllName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedRecipe.setName requires a session language", 0 );
		}
		setLocalizedProperty(ctx, NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final String value)
	{
		setName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.name</code> attribute. 
	 * @param value the name
	 */
	public void setAllName(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.name</code> attribute. 
	 * @param value the name
	 */
	public void setAllName(final Map<Language,String> value)
	{
		setAllName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.prepTime</code> attribute.
	 * @return the prepTime
	 */
	public String getPrepTime(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PREPTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.prepTime</code> attribute.
	 * @return the prepTime
	 */
	public String getPrepTime()
	{
		return getPrepTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.prepTime</code> attribute. 
	 * @param value the prepTime
	 */
	public void setPrepTime(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PREPTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.prepTime</code> attribute. 
	 * @param value the prepTime
	 */
	public void setPrepTime(final String value)
	{
		setPrepTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.recipeHighlightComponent</code> attribute.
	 * @return the recipeHighlightComponent
	 */
	public RecipeHighlightComponent getRecipeHighlightComponent(final SessionContext ctx)
	{
		return (RecipeHighlightComponent)getProperty( ctx, RECIPEHIGHLIGHTCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.recipeHighlightComponent</code> attribute.
	 * @return the recipeHighlightComponent
	 */
	public RecipeHighlightComponent getRecipeHighlightComponent()
	{
		return getRecipeHighlightComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.recipeHighlightComponent</code> attribute. 
	 * @param value the recipeHighlightComponent
	 */
	public void setRecipeHighlightComponent(final SessionContext ctx, final RecipeHighlightComponent value)
	{
		RECIPEHIGHLIGHTCOMPONENTHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.recipeHighlightComponent</code> attribute. 
	 * @param value the recipeHighlightComponent
	 */
	public void setRecipeHighlightComponent(final RecipeHighlightComponent value)
	{
		setRecipeHighlightComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.recipeThemeComponent</code> attribute.
	 * @return the recipeThemeComponent
	 */
	public RecipeThemeComponent getRecipeThemeComponent(final SessionContext ctx)
	{
		return (RecipeThemeComponent)getProperty( ctx, RECIPETHEMECOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.recipeThemeComponent</code> attribute.
	 * @return the recipeThemeComponent
	 */
	public RecipeThemeComponent getRecipeThemeComponent()
	{
		return getRecipeThemeComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.recipeThemeComponent</code> attribute. 
	 * @param value the recipeThemeComponent
	 */
	public void setRecipeThemeComponent(final SessionContext ctx, final RecipeThemeComponent value)
	{
		RECIPETHEMECOMPONENTHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.recipeThemeComponent</code> attribute. 
	 * @param value the recipeThemeComponent
	 */
	public void setRecipeThemeComponent(final RecipeThemeComponent value)
	{
		setRecipeThemeComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.serves</code> attribute.
	 * @return the serves
	 */
	public String getServes(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SERVES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.serves</code> attribute.
	 * @return the serves
	 */
	public String getServes()
	{
		return getServes( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.serves</code> attribute. 
	 * @param value the serves
	 */
	public void setServes(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SERVES,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.serves</code> attribute. 
	 * @param value the serves
	 */
	public void setServes(final String value)
	{
		setServes( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.uncategorizedIngredient</code> attribute.
	 * @return the uncategorizedIngredient - Ingredients Group
	 */
	public List<Ingredient> getUncategorizedIngredient(final SessionContext ctx)
	{
		final List<Ingredient> items = getLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTSRELATION,
			"Ingredient",
			null,
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTSRELATION_SRC_ORDERED, true),
			false
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.uncategorizedIngredient</code> attribute.
	 * @return the uncategorizedIngredient - Ingredients Group
	 */
	public List<Ingredient> getUncategorizedIngredient()
	{
		return getUncategorizedIngredient( getSession().getSessionContext() );
	}
	
	public long getUncategorizedIngredientCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			true,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTSRELATION,
			"Ingredient",
			null
		);
	}
	
	public long getUncategorizedIngredientCount()
	{
		return getUncategorizedIngredientCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.uncategorizedIngredient</code> attribute. 
	 * @param value the uncategorizedIngredient - Ingredients Group
	 */
	public void setUncategorizedIngredient(final SessionContext ctx, final List<Ingredient> value)
	{
		setLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTSRELATION,
			null,
			value,
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTSRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTSRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.uncategorizedIngredient</code> attribute. 
	 * @param value the uncategorizedIngredient - Ingredients Group
	 */
	public void setUncategorizedIngredient(final List<Ingredient> value)
	{
		setUncategorizedIngredient( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to uncategorizedIngredient. 
	 * @param value the item to add to uncategorizedIngredient - Ingredients Group
	 */
	public void addToUncategorizedIngredient(final SessionContext ctx, final Ingredient value)
	{
		addLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTSRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTSRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTSRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to uncategorizedIngredient. 
	 * @param value the item to add to uncategorizedIngredient - Ingredients Group
	 */
	public void addToUncategorizedIngredient(final Ingredient value)
	{
		addToUncategorizedIngredient( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from uncategorizedIngredient. 
	 * @param value the item to remove from uncategorizedIngredient - Ingredients Group
	 */
	public void removeFromUncategorizedIngredient(final SessionContext ctx, final Ingredient value)
	{
		removeLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTSRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTSRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTSRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from uncategorizedIngredient. 
	 * @param value the item to remove from uncategorizedIngredient - Ingredients Group
	 */
	public void removeFromUncategorizedIngredient(final Ingredient value)
	{
		removeFromUncategorizedIngredient( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.videoURL</code> attribute.
	 * @return the videoURL
	 */
	public String getVideoURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, VIDEOURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Recipe.videoURL</code> attribute.
	 * @return the videoURL
	 */
	public String getVideoURL()
	{
		return getVideoURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.videoURL</code> attribute. 
	 * @param value the videoURL
	 */
	public void setVideoURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, VIDEOURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Recipe.videoURL</code> attribute. 
	 * @param value the videoURL
	 */
	public void setVideoURL(final String value)
	{
		setVideoURL( getSession().getSessionContext(), value );
	}
	
}
