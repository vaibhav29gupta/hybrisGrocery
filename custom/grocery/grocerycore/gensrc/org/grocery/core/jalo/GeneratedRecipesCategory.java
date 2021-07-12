/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 08-Jul-2020, 11:07:38 PM                    ---
 * ----------------------------------------------------------------
 */
package org.grocery.core.jalo;

import de.hybris.platform.category.jalo.Category;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.TypeManager;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import de.hybris.platform.util.Utilities;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.grocery.core.constants.GroceryCoreConstants;
import org.grocery.core.jalo.Recipe;
import org.grocery.core.jalo.RecipeCategoryComponent;

/**
 * Generated class for type {@link de.hybris.platform.category.jalo.Category RecipesCategory}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedRecipesCategory extends Category
{
	/** Qualifier of the <code>RecipesCategory.recipes</code> attribute **/
	public static final String RECIPES = "recipes";
	/** Relation ordering override parameter constants for RecipesCategory2RecipesRelation from ((grocerycore))*/
	protected static String RECIPESCATEGORY2RECIPESRELATION_SRC_ORDERED = "relation.RecipesCategory2RecipesRelation.source.ordered";
	protected static String RECIPESCATEGORY2RECIPESRELATION_TGT_ORDERED = "relation.RecipesCategory2RecipesRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for RecipesCategory2RecipesRelation from ((grocerycore))*/
	protected static String RECIPESCATEGORY2RECIPESRELATION_MARKMODIFIED = "relation.RecipesCategory2RecipesRelation.markmodified";
	/** Qualifier of the <code>RecipesCategory.recipeCategoryComponent</code> attribute **/
	public static final String RECIPECATEGORYCOMPONENT = "recipeCategoryComponent";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n RECIPECATEGORYCOMPONENT's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedRecipesCategory> RECIPECATEGORYCOMPONENTHANDLER = new BidirectionalOneToManyHandler<GeneratedRecipesCategory>(
	GroceryCoreConstants.TC.RECIPESCATEGORY,
	false,
	"recipeCategoryComponent",
	null,
	false,
	true,
	CollectionType.LIST
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Category.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(RECIPECATEGORYCOMPONENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		RECIPECATEGORYCOMPONENTHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	@Override
	public boolean isMarkModifiedDisabled(final Item referencedItem)
	{
		ComposedType relationSecondEnd0 = TypeManager.getInstance().getComposedType("Recipe");
		if(relationSecondEnd0.isAssignableFrom(referencedItem.getComposedType()))
		{
			return Utilities.getMarkModifiedOverride(RECIPESCATEGORY2RECIPESRELATION_MARKMODIFIED);
		}
		return true;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipesCategory.recipeCategoryComponent</code> attribute.
	 * @return the recipeCategoryComponent
	 */
	public RecipeCategoryComponent getRecipeCategoryComponent(final SessionContext ctx)
	{
		return (RecipeCategoryComponent)getProperty( ctx, RECIPECATEGORYCOMPONENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipesCategory.recipeCategoryComponent</code> attribute.
	 * @return the recipeCategoryComponent
	 */
	public RecipeCategoryComponent getRecipeCategoryComponent()
	{
		return getRecipeCategoryComponent( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipesCategory.recipeCategoryComponent</code> attribute. 
	 * @param value the recipeCategoryComponent
	 */
	public void setRecipeCategoryComponent(final SessionContext ctx, final RecipeCategoryComponent value)
	{
		RECIPECATEGORYCOMPONENTHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipesCategory.recipeCategoryComponent</code> attribute. 
	 * @param value the recipeCategoryComponent
	 */
	public void setRecipeCategoryComponent(final RecipeCategoryComponent value)
	{
		setRecipeCategoryComponent( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipesCategory.recipes</code> attribute.
	 * @return the recipes - Recipe
	 */
	public List<Recipe> getRecipes(final SessionContext ctx)
	{
		final List<Recipe> items = getLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.RECIPESCATEGORY2RECIPESRELATION,
			"Recipe",
			null,
			Utilities.getRelationOrderingOverride(RECIPESCATEGORY2RECIPESRELATION_SRC_ORDERED, true),
			false
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipesCategory.recipes</code> attribute.
	 * @return the recipes - Recipe
	 */
	public List<Recipe> getRecipes()
	{
		return getRecipes( getSession().getSessionContext() );
	}
	
	public long getRecipesCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			true,
			GroceryCoreConstants.Relations.RECIPESCATEGORY2RECIPESRELATION,
			"Recipe",
			null
		);
	}
	
	public long getRecipesCount()
	{
		return getRecipesCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipesCategory.recipes</code> attribute. 
	 * @param value the recipes - Recipe
	 */
	public void setRecipes(final SessionContext ctx, final List<Recipe> value)
	{
		setLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.RECIPESCATEGORY2RECIPESRELATION,
			null,
			value,
			Utilities.getRelationOrderingOverride(RECIPESCATEGORY2RECIPESRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPESCATEGORY2RECIPESRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipesCategory.recipes</code> attribute. 
	 * @param value the recipes - Recipe
	 */
	public void setRecipes(final List<Recipe> value)
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
			true,
			GroceryCoreConstants.Relations.RECIPESCATEGORY2RECIPESRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(RECIPESCATEGORY2RECIPESRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPESCATEGORY2RECIPESRELATION_MARKMODIFIED)
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
			true,
			GroceryCoreConstants.Relations.RECIPESCATEGORY2RECIPESRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(RECIPESCATEGORY2RECIPESRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPESCATEGORY2RECIPESRELATION_MARKMODIFIED)
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
