/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 08-Jul-2020, 11:07:38 PM                    ---
 * ----------------------------------------------------------------
 */
package org.grocery.core.jalo;

import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.grocery.core.constants.GroceryCoreConstants;
import org.grocery.core.jalo.Recipe;

/**
 * Generated class for type {@link org.grocery.core.jalo.RecipeHighlightComponent RecipeHighlightComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedRecipeHighlightComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>RecipeHighlightComponent.highlightRecipe</code> attribute **/
	public static final String HIGHLIGHTRECIPE = "highlightRecipe";
	/** Qualifier of the <code>RecipeHighlightComponent.recipes</code> attribute **/
	public static final String RECIPES = "recipes";
	/**
	* {@link OneToManyHandler} for handling 1:n RECIPES's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<Recipe> RECIPESHANDLER = new OneToManyHandler<Recipe>(
	GroceryCoreConstants.TC.RECIPE,
	false,
	"recipeHighlightComponent",
	null,
	false,
	true,
	CollectionType.LIST
	).withRelationQualifier("recipes");
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(HIGHLIGHTRECIPE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeHighlightComponent.highlightRecipe</code> attribute.
	 * @return the highlightRecipe
	 */
	public Recipe getHighlightRecipe(final SessionContext ctx)
	{
		return (Recipe)getProperty( ctx, HIGHLIGHTRECIPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeHighlightComponent.highlightRecipe</code> attribute.
	 * @return the highlightRecipe
	 */
	public Recipe getHighlightRecipe()
	{
		return getHighlightRecipe( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeHighlightComponent.highlightRecipe</code> attribute. 
	 * @param value the highlightRecipe
	 */
	public void setHighlightRecipe(final SessionContext ctx, final Recipe value)
	{
		setProperty(ctx, HIGHLIGHTRECIPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeHighlightComponent.highlightRecipe</code> attribute. 
	 * @param value the highlightRecipe
	 */
	public void setHighlightRecipe(final Recipe value)
	{
		setHighlightRecipe( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeHighlightComponent.recipes</code> attribute.
	 * @return the recipes
	 */
	public List<Recipe> getRecipes(final SessionContext ctx)
	{
		return (List<Recipe>)RECIPESHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecipeHighlightComponent.recipes</code> attribute.
	 * @return the recipes
	 */
	public List<Recipe> getRecipes()
	{
		return getRecipes( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeHighlightComponent.recipes</code> attribute. 
	 * @param value the recipes
	 */
	public void setRecipes(final SessionContext ctx, final List<Recipe> value)
	{
		RECIPESHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecipeHighlightComponent.recipes</code> attribute. 
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
