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
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.TypeManager;
import de.hybris.platform.util.OneToManyHandler;
import de.hybris.platform.util.Utilities;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.grocery.core.constants.GroceryCoreConstants;
import org.grocery.core.jalo.Ingredient;
import org.grocery.core.jalo.Recipe;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem IngredientsGroup}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedIngredientsGroup extends GenericItem
{
	/** Qualifier of the <code>IngredientsGroup.groupName</code> attribute **/
	public static final String GROUPNAME = "groupName";
	/** Qualifier of the <code>IngredientsGroup.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>IngredientsGroup.catalogVersion</code> attribute **/
	public static final String CATALOGVERSION = "catalogVersion";
	/** Qualifier of the <code>IngredientsGroup.ingredients</code> attribute **/
	public static final String INGREDIENTS = "ingredients";
	/** Qualifier of the <code>IngredientsGroup.recipes</code> attribute **/
	public static final String RECIPES = "recipes";
	/** Relation ordering override parameter constants for Recipe2IngredientGroupsRelation from ((grocerycore))*/
	protected static String RECIPE2INGREDIENTGROUPSRELATION_SRC_ORDERED = "relation.Recipe2IngredientGroupsRelation.source.ordered";
	protected static String RECIPE2INGREDIENTGROUPSRELATION_TGT_ORDERED = "relation.Recipe2IngredientGroupsRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for Recipe2IngredientGroupsRelation from ((grocerycore))*/
	protected static String RECIPE2INGREDIENTGROUPSRELATION_MARKMODIFIED = "relation.Recipe2IngredientGroupsRelation.markmodified";
	/**
	* {@link OneToManyHandler} for handling 1:n INGREDIENTS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<Ingredient> INGREDIENTSHANDLER = new OneToManyHandler<Ingredient>(
	GroceryCoreConstants.TC.INGREDIENT,
	false,
	"ingredientGroup",
	null,
	false,
	true,
	CollectionType.LIST
	).withRelationQualifier("ingredients");
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(GROUPNAME, AttributeMode.INITIAL);
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(CATALOGVERSION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IngredientsGroup.catalogVersion</code> attribute.
	 * @return the catalogVersion
	 */
	public CatalogVersion getCatalogVersion(final SessionContext ctx)
	{
		return (CatalogVersion)getProperty( ctx, CATALOGVERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IngredientsGroup.catalogVersion</code> attribute.
	 * @return the catalogVersion
	 */
	public CatalogVersion getCatalogVersion()
	{
		return getCatalogVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IngredientsGroup.catalogVersion</code> attribute. 
	 * @param value the catalogVersion
	 */
	public void setCatalogVersion(final SessionContext ctx, final CatalogVersion value)
	{
		setProperty(ctx, CATALOGVERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IngredientsGroup.catalogVersion</code> attribute. 
	 * @param value the catalogVersion
	 */
	public void setCatalogVersion(final CatalogVersion value)
	{
		setCatalogVersion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IngredientsGroup.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IngredientsGroup.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IngredientsGroup.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IngredientsGroup.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IngredientsGroup.groupName</code> attribute.
	 * @return the groupName - Name of Ingredients Group
	 */
	public String getGroupName(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedIngredientsGroup.getGroupName requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, GROUPNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IngredientsGroup.groupName</code> attribute.
	 * @return the groupName - Name of Ingredients Group
	 */
	public String getGroupName()
	{
		return getGroupName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IngredientsGroup.groupName</code> attribute. 
	 * @return the localized groupName - Name of Ingredients Group
	 */
	public Map<Language,String> getAllGroupName(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,GROUPNAME,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IngredientsGroup.groupName</code> attribute. 
	 * @return the localized groupName - Name of Ingredients Group
	 */
	public Map<Language,String> getAllGroupName()
	{
		return getAllGroupName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IngredientsGroup.groupName</code> attribute. 
	 * @param value the groupName - Name of Ingredients Group
	 */
	public void setGroupName(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedIngredientsGroup.setGroupName requires a session language", 0 );
		}
		setLocalizedProperty(ctx, GROUPNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IngredientsGroup.groupName</code> attribute. 
	 * @param value the groupName - Name of Ingredients Group
	 */
	public void setGroupName(final String value)
	{
		setGroupName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IngredientsGroup.groupName</code> attribute. 
	 * @param value the groupName - Name of Ingredients Group
	 */
	public void setAllGroupName(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,GROUPNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IngredientsGroup.groupName</code> attribute. 
	 * @param value the groupName - Name of Ingredients Group
	 */
	public void setAllGroupName(final Map<Language,String> value)
	{
		setAllGroupName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IngredientsGroup.ingredients</code> attribute.
	 * @return the ingredients
	 */
	public List<Ingredient> getIngredients(final SessionContext ctx)
	{
		return (List<Ingredient>)INGREDIENTSHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IngredientsGroup.ingredients</code> attribute.
	 * @return the ingredients
	 */
	public List<Ingredient> getIngredients()
	{
		return getIngredients( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IngredientsGroup.ingredients</code> attribute. 
	 * @param value the ingredients
	 */
	public void setIngredients(final SessionContext ctx, final List<Ingredient> value)
	{
		INGREDIENTSHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IngredientsGroup.ingredients</code> attribute. 
	 * @param value the ingredients
	 */
	public void setIngredients(final List<Ingredient> value)
	{
		setIngredients( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to ingredients. 
	 * @param value the item to add to ingredients
	 */
	public void addToIngredients(final SessionContext ctx, final Ingredient value)
	{
		INGREDIENTSHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to ingredients. 
	 * @param value the item to add to ingredients
	 */
	public void addToIngredients(final Ingredient value)
	{
		addToIngredients( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from ingredients. 
	 * @param value the item to remove from ingredients
	 */
	public void removeFromIngredients(final SessionContext ctx, final Ingredient value)
	{
		INGREDIENTSHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from ingredients. 
	 * @param value the item to remove from ingredients
	 */
	public void removeFromIngredients(final Ingredient value)
	{
		removeFromIngredients( getSession().getSessionContext(), value );
	}
	
	@Override
	public boolean isMarkModifiedDisabled(final Item referencedItem)
	{
		ComposedType relationSecondEnd0 = TypeManager.getInstance().getComposedType("Recipe");
		if(relationSecondEnd0.isAssignableFrom(referencedItem.getComposedType()))
		{
			return Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTGROUPSRELATION_MARKMODIFIED);
		}
		return true;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IngredientsGroup.recipes</code> attribute.
	 * @return the recipes - Recipe
	 */
	public Collection<Recipe> getRecipes(final SessionContext ctx)
	{
		final List<Recipe> items = getLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTGROUPSRELATION,
			"Recipe",
			null,
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTGROUPSRELATION_SRC_ORDERED, true),
			false
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IngredientsGroup.recipes</code> attribute.
	 * @return the recipes - Recipe
	 */
	public Collection<Recipe> getRecipes()
	{
		return getRecipes( getSession().getSessionContext() );
	}
	
	public long getRecipesCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			false,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTGROUPSRELATION,
			"Recipe",
			null
		);
	}
	
	public long getRecipesCount()
	{
		return getRecipesCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IngredientsGroup.recipes</code> attribute. 
	 * @param value the recipes - Recipe
	 */
	public void setRecipes(final SessionContext ctx, final Collection<Recipe> value)
	{
		setLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTGROUPSRELATION,
			null,
			value,
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTGROUPSRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTGROUPSRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IngredientsGroup.recipes</code> attribute. 
	 * @param value the recipes - Recipe
	 */
	public void setRecipes(final Collection<Recipe> value)
	{
		setRecipes( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to recipes. 
	 * @param value the item to add to recipes - Recipe
	 */
	public void addToRecipes(final SessionContext ctx, final Recipe value)
	{
		addLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTGROUPSRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTGROUPSRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTGROUPSRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to recipes. 
	 * @param value the item to add to recipes - Recipe
	 */
	public void addToRecipes(final Recipe value)
	{
		addToRecipes( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from recipes. 
	 * @param value the item to remove from recipes - Recipe
	 */
	public void removeFromRecipes(final SessionContext ctx, final Recipe value)
	{
		removeLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTGROUPSRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTGROUPSRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTGROUPSRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from recipes. 
	 * @param value the item to remove from recipes - Recipe
	 */
	public void removeFromRecipes(final Recipe value)
	{
		removeFromRecipes( getSession().getSessionContext(), value );
	}
	
}
