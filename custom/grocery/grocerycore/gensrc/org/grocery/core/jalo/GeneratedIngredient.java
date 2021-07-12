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
import de.hybris.platform.jalo.product.Product;
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
import org.grocery.core.jalo.IngredientsGroup;
import org.grocery.core.jalo.Recipe;

/**
 * Generated class for type {@link org.grocery.core.jalo.Ingredient Ingredient}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedIngredient extends GenericItem
{
	/** Qualifier of the <code>Ingredient.quantity</code> attribute **/
	public static final String QUANTITY = "quantity";
	/** Qualifier of the <code>Ingredient.name</code> attribute **/
	public static final String NAME = "name";
	/** Qualifier of the <code>Ingredient.specialInstruction</code> attribute **/
	public static final String SPECIALINSTRUCTION = "specialInstruction";
	/** Qualifier of the <code>Ingredient.cutStyle</code> attribute **/
	public static final String CUTSTYLE = "cutStyle";
	/** Qualifier of the <code>Ingredient.product</code> attribute **/
	public static final String PRODUCT = "product";
	/** Qualifier of the <code>Ingredient.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>Ingredient.catalogVersion</code> attribute **/
	public static final String CATALOGVERSION = "catalogVersion";
	/** Qualifier of the <code>Ingredient.ingredientGroup</code> attribute **/
	public static final String INGREDIENTGROUP = "ingredientGroup";
	/** Qualifier of the <code>Ingredient.recipes</code> attribute **/
	public static final String RECIPES = "recipes";
	/** Relation ordering override parameter constants for Recipe2IngredientsRelation from ((grocerycore))*/
	protected static String RECIPE2INGREDIENTSRELATION_SRC_ORDERED = "relation.Recipe2IngredientsRelation.source.ordered";
	protected static String RECIPE2INGREDIENTSRELATION_TGT_ORDERED = "relation.Recipe2IngredientsRelation.target.ordered";
	/** Relation disable markmodifed parameter constants for Recipe2IngredientsRelation from ((grocerycore))*/
	protected static String RECIPE2INGREDIENTSRELATION_MARKMODIFIED = "relation.Recipe2IngredientsRelation.markmodified";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n INGREDIENTGROUP's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedIngredient> INGREDIENTGROUPHANDLER = new BidirectionalOneToManyHandler<GeneratedIngredient>(
	GroceryCoreConstants.TC.INGREDIENT,
	false,
	"ingredientGroup",
	null,
	false,
	true,
	CollectionType.LIST
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(QUANTITY, AttributeMode.INITIAL);
		tmp.put(NAME, AttributeMode.INITIAL);
		tmp.put(SPECIALINSTRUCTION, AttributeMode.INITIAL);
		tmp.put(CUTSTYLE, AttributeMode.INITIAL);
		tmp.put(PRODUCT, AttributeMode.INITIAL);
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(CATALOGVERSION, AttributeMode.INITIAL);
		tmp.put(INGREDIENTGROUP, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.catalogVersion</code> attribute.
	 * @return the catalogVersion
	 */
	public CatalogVersion getCatalogVersion(final SessionContext ctx)
	{
		return (CatalogVersion)getProperty( ctx, CATALOGVERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.catalogVersion</code> attribute.
	 * @return the catalogVersion
	 */
	public CatalogVersion getCatalogVersion()
	{
		return getCatalogVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.catalogVersion</code> attribute. 
	 * @param value the catalogVersion
	 */
	public void setCatalogVersion(final SessionContext ctx, final CatalogVersion value)
	{
		setProperty(ctx, CATALOGVERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.catalogVersion</code> attribute. 
	 * @param value the catalogVersion
	 */
	public void setCatalogVersion(final CatalogVersion value)
	{
		setCatalogVersion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		INGREDIENTGROUPHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.cutStyle</code> attribute.
	 * @return the cutStyle - Cut Style
	 */
	public String getCutStyle(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedIngredient.getCutStyle requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, CUTSTYLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.cutStyle</code> attribute.
	 * @return the cutStyle - Cut Style
	 */
	public String getCutStyle()
	{
		return getCutStyle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.cutStyle</code> attribute. 
	 * @return the localized cutStyle - Cut Style
	 */
	public Map<Language,String> getAllCutStyle(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,CUTSTYLE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.cutStyle</code> attribute. 
	 * @return the localized cutStyle - Cut Style
	 */
	public Map<Language,String> getAllCutStyle()
	{
		return getAllCutStyle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.cutStyle</code> attribute. 
	 * @param value the cutStyle - Cut Style
	 */
	public void setCutStyle(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedIngredient.setCutStyle requires a session language", 0 );
		}
		setLocalizedProperty(ctx, CUTSTYLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.cutStyle</code> attribute. 
	 * @param value the cutStyle - Cut Style
	 */
	public void setCutStyle(final String value)
	{
		setCutStyle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.cutStyle</code> attribute. 
	 * @param value the cutStyle - Cut Style
	 */
	public void setAllCutStyle(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,CUTSTYLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.cutStyle</code> attribute. 
	 * @param value the cutStyle - Cut Style
	 */
	public void setAllCutStyle(final Map<Language,String> value)
	{
		setAllCutStyle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.ingredientGroup</code> attribute.
	 * @return the ingredientGroup
	 */
	public IngredientsGroup getIngredientGroup(final SessionContext ctx)
	{
		return (IngredientsGroup)getProperty( ctx, INGREDIENTGROUP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.ingredientGroup</code> attribute.
	 * @return the ingredientGroup
	 */
	public IngredientsGroup getIngredientGroup()
	{
		return getIngredientGroup( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.ingredientGroup</code> attribute. 
	 * @param value the ingredientGroup
	 */
	public void setIngredientGroup(final SessionContext ctx, final IngredientsGroup value)
	{
		INGREDIENTGROUPHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.ingredientGroup</code> attribute. 
	 * @param value the ingredientGroup
	 */
	public void setIngredientGroup(final IngredientsGroup value)
	{
		setIngredientGroup( getSession().getSessionContext(), value );
	}
	
	@Override
	public boolean isMarkModifiedDisabled(final Item referencedItem)
	{
		ComposedType relationSecondEnd0 = TypeManager.getInstance().getComposedType("Recipe");
		if(relationSecondEnd0.isAssignableFrom(referencedItem.getComposedType()))
		{
			return Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTSRELATION_MARKMODIFIED);
		}
		return true;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.name</code> attribute.
	 * @return the name - Ingredient Name
	 */
	public String getName(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedIngredient.getName requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, NAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.name</code> attribute.
	 * @return the name - Ingredient Name
	 */
	public String getName()
	{
		return getName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.name</code> attribute. 
	 * @return the localized name - Ingredient Name
	 */
	public Map<Language,String> getAllName(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,NAME,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.name</code> attribute. 
	 * @return the localized name - Ingredient Name
	 */
	public Map<Language,String> getAllName()
	{
		return getAllName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.name</code> attribute. 
	 * @param value the name - Ingredient Name
	 */
	public void setName(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedIngredient.setName requires a session language", 0 );
		}
		setLocalizedProperty(ctx, NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.name</code> attribute. 
	 * @param value the name - Ingredient Name
	 */
	public void setName(final String value)
	{
		setName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.name</code> attribute. 
	 * @param value the name - Ingredient Name
	 */
	public void setAllName(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.name</code> attribute. 
	 * @param value the name - Ingredient Name
	 */
	public void setAllName(final Map<Language,String> value)
	{
		setAllName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.product</code> attribute.
	 * @return the product - Product sellable on Website
	 */
	public Product getProduct(final SessionContext ctx)
	{
		return (Product)getProperty( ctx, PRODUCT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.product</code> attribute.
	 * @return the product - Product sellable on Website
	 */
	public Product getProduct()
	{
		return getProduct( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.product</code> attribute. 
	 * @param value the product - Product sellable on Website
	 */
	public void setProduct(final SessionContext ctx, final Product value)
	{
		setProperty(ctx, PRODUCT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.product</code> attribute. 
	 * @param value the product - Product sellable on Website
	 */
	public void setProduct(final Product value)
	{
		setProduct( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.quantity</code> attribute.
	 * @return the quantity - Quantity
	 */
	public String getQuantity(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedIngredient.getQuantity requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, QUANTITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.quantity</code> attribute.
	 * @return the quantity - Quantity
	 */
	public String getQuantity()
	{
		return getQuantity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.quantity</code> attribute. 
	 * @return the localized quantity - Quantity
	 */
	public Map<Language,String> getAllQuantity(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,QUANTITY,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.quantity</code> attribute. 
	 * @return the localized quantity - Quantity
	 */
	public Map<Language,String> getAllQuantity()
	{
		return getAllQuantity( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.quantity</code> attribute. 
	 * @param value the quantity - Quantity
	 */
	public void setQuantity(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedIngredient.setQuantity requires a session language", 0 );
		}
		setLocalizedProperty(ctx, QUANTITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.quantity</code> attribute. 
	 * @param value the quantity - Quantity
	 */
	public void setQuantity(final String value)
	{
		setQuantity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.quantity</code> attribute. 
	 * @param value the quantity - Quantity
	 */
	public void setAllQuantity(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,QUANTITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.quantity</code> attribute. 
	 * @param value the quantity - Quantity
	 */
	public void setAllQuantity(final Map<Language,String> value)
	{
		setAllQuantity( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.recipes</code> attribute.
	 * @return the recipes - Recipe
	 */
	public Collection<Recipe> getRecipes(final SessionContext ctx)
	{
		final List<Recipe> items = getLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTSRELATION,
			"Recipe",
			null,
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTSRELATION_SRC_ORDERED, true),
			false
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.recipes</code> attribute.
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
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTSRELATION,
			"Recipe",
			null
		);
	}
	
	public long getRecipesCount()
	{
		return getRecipesCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.recipes</code> attribute. 
	 * @param value the recipes - Recipe
	 */
	public void setRecipes(final SessionContext ctx, final Collection<Recipe> value)
	{
		setLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTSRELATION,
			null,
			value,
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTSRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTSRELATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.recipes</code> attribute. 
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
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTSRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTSRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTSRELATION_MARKMODIFIED)
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
			GroceryCoreConstants.Relations.RECIPE2INGREDIENTSRELATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(RECIPE2INGREDIENTSRELATION_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(RECIPE2INGREDIENTSRELATION_MARKMODIFIED)
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
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.specialInstruction</code> attribute.
	 * @return the specialInstruction - Special Instruction
	 */
	public String getSpecialInstruction(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedIngredient.getSpecialInstruction requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, SPECIALINSTRUCTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.specialInstruction</code> attribute.
	 * @return the specialInstruction - Special Instruction
	 */
	public String getSpecialInstruction()
	{
		return getSpecialInstruction( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.specialInstruction</code> attribute. 
	 * @return the localized specialInstruction - Special Instruction
	 */
	public Map<Language,String> getAllSpecialInstruction(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,SPECIALINSTRUCTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredient.specialInstruction</code> attribute. 
	 * @return the localized specialInstruction - Special Instruction
	 */
	public Map<Language,String> getAllSpecialInstruction()
	{
		return getAllSpecialInstruction( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.specialInstruction</code> attribute. 
	 * @param value the specialInstruction - Special Instruction
	 */
	public void setSpecialInstruction(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedIngredient.setSpecialInstruction requires a session language", 0 );
		}
		setLocalizedProperty(ctx, SPECIALINSTRUCTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.specialInstruction</code> attribute. 
	 * @param value the specialInstruction - Special Instruction
	 */
	public void setSpecialInstruction(final String value)
	{
		setSpecialInstruction( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.specialInstruction</code> attribute. 
	 * @param value the specialInstruction - Special Instruction
	 */
	public void setAllSpecialInstruction(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,SPECIALINSTRUCTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredient.specialInstruction</code> attribute. 
	 * @param value the specialInstruction - Special Instruction
	 */
	public void setAllSpecialInstruction(final Map<Language,String> value)
	{
		setAllSpecialInstruction( getSession().getSessionContext(), value );
	}
	
}
