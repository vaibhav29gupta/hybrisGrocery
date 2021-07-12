/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 08-Jul-2020, 11:07:38 PM                    ---
 * ----------------------------------------------------------------
 */
package org.grocery.core.jalo;

import de.hybris.platform.cms2lib.components.ProductCarouselComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.grocery.core.constants.GroceryCoreConstants;

/**
 * Generated class for type {@link de.hybris.platform.cms2lib.components.ProductCarouselComponent BestSellersProductsComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedBestSellersProductsComponent extends ProductCarouselComponent
{
	/** Qualifier of the <code>BestSellersProductsComponent.numberOfDays</code> attribute **/
	public static final String NUMBEROFDAYS = "numberOfDays";
	/** Qualifier of the <code>BestSellersProductsComponent.numberOfOrders</code> attribute **/
	public static final String NUMBEROFORDERS = "numberOfOrders";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(ProductCarouselComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(NUMBEROFDAYS, AttributeMode.INITIAL);
		tmp.put(NUMBEROFORDERS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BestSellersProductsComponent.numberOfDays</code> attribute.
	 * @return the numberOfDays - Number Of Days
	 */
	public Integer getNumberOfDays(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, NUMBEROFDAYS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BestSellersProductsComponent.numberOfDays</code> attribute.
	 * @return the numberOfDays - Number Of Days
	 */
	public Integer getNumberOfDays()
	{
		return getNumberOfDays( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BestSellersProductsComponent.numberOfDays</code> attribute. 
	 * @return the numberOfDays - Number Of Days
	 */
	public int getNumberOfDaysAsPrimitive(final SessionContext ctx)
	{
		Integer value = getNumberOfDays( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BestSellersProductsComponent.numberOfDays</code> attribute. 
	 * @return the numberOfDays - Number Of Days
	 */
	public int getNumberOfDaysAsPrimitive()
	{
		return getNumberOfDaysAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BestSellersProductsComponent.numberOfDays</code> attribute. 
	 * @param value the numberOfDays - Number Of Days
	 */
	public void setNumberOfDays(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, NUMBEROFDAYS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BestSellersProductsComponent.numberOfDays</code> attribute. 
	 * @param value the numberOfDays - Number Of Days
	 */
	public void setNumberOfDays(final Integer value)
	{
		setNumberOfDays( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BestSellersProductsComponent.numberOfDays</code> attribute. 
	 * @param value the numberOfDays - Number Of Days
	 */
	public void setNumberOfDays(final SessionContext ctx, final int value)
	{
		setNumberOfDays( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BestSellersProductsComponent.numberOfDays</code> attribute. 
	 * @param value the numberOfDays - Number Of Days
	 */
	public void setNumberOfDays(final int value)
	{
		setNumberOfDays( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BestSellersProductsComponent.numberOfOrders</code> attribute.
	 * @return the numberOfOrders - Number Of Orders
	 */
	public Integer getNumberOfOrders(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, NUMBEROFORDERS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BestSellersProductsComponent.numberOfOrders</code> attribute.
	 * @return the numberOfOrders - Number Of Orders
	 */
	public Integer getNumberOfOrders()
	{
		return getNumberOfOrders( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BestSellersProductsComponent.numberOfOrders</code> attribute. 
	 * @return the numberOfOrders - Number Of Orders
	 */
	public int getNumberOfOrdersAsPrimitive(final SessionContext ctx)
	{
		Integer value = getNumberOfOrders( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BestSellersProductsComponent.numberOfOrders</code> attribute. 
	 * @return the numberOfOrders - Number Of Orders
	 */
	public int getNumberOfOrdersAsPrimitive()
	{
		return getNumberOfOrdersAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BestSellersProductsComponent.numberOfOrders</code> attribute. 
	 * @param value the numberOfOrders - Number Of Orders
	 */
	public void setNumberOfOrders(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, NUMBEROFORDERS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BestSellersProductsComponent.numberOfOrders</code> attribute. 
	 * @param value the numberOfOrders - Number Of Orders
	 */
	public void setNumberOfOrders(final Integer value)
	{
		setNumberOfOrders( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BestSellersProductsComponent.numberOfOrders</code> attribute. 
	 * @param value the numberOfOrders - Number Of Orders
	 */
	public void setNumberOfOrders(final SessionContext ctx, final int value)
	{
		setNumberOfOrders( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BestSellersProductsComponent.numberOfOrders</code> attribute. 
	 * @param value the numberOfOrders - Number Of Orders
	 */
	public void setNumberOfOrders(final int value)
	{
		setNumberOfOrders( getSession().getSessionContext(), value );
	}
	
}
