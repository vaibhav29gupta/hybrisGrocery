/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 08-Jul-2020, 11:07:38 PM                    ---
 * ----------------------------------------------------------------
 */
package org.grocery.core.jalo;

import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Region;
import de.hybris.platform.ordersplitting.jalo.Warehouse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.grocery.core.constants.GroceryCoreConstants;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem ServiceabilityArea}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedServiceabilityArea extends GenericItem
{
	/** Qualifier of the <code>ServiceabilityArea.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>ServiceabilityArea.startPostalCode</code> attribute **/
	public static final String STARTPOSTALCODE = "startPostalCode";
	/** Qualifier of the <code>ServiceabilityArea.endPostalCode</code> attribute **/
	public static final String ENDPOSTALCODE = "endPostalCode";
	/** Qualifier of the <code>ServiceabilityArea.region</code> attribute **/
	public static final String REGION = "region";
	/** Qualifier of the <code>ServiceabilityArea.warehouse</code> attribute **/
	public static final String WAREHOUSE = "warehouse";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(STARTPOSTALCODE, AttributeMode.INITIAL);
		tmp.put(ENDPOSTALCODE, AttributeMode.INITIAL);
		tmp.put(REGION, AttributeMode.INITIAL);
		tmp.put(WAREHOUSE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceabilityArea.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceabilityArea.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceabilityArea.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceabilityArea.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceabilityArea.endPostalCode</code> attribute.
	 * @return the endPostalCode - End postal code for serviceability area
	 */
	public Long getEndPostalCode(final SessionContext ctx)
	{
		return (Long)getProperty( ctx, ENDPOSTALCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceabilityArea.endPostalCode</code> attribute.
	 * @return the endPostalCode - End postal code for serviceability area
	 */
	public Long getEndPostalCode()
	{
		return getEndPostalCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceabilityArea.endPostalCode</code> attribute. 
	 * @return the endPostalCode - End postal code for serviceability area
	 */
	public long getEndPostalCodeAsPrimitive(final SessionContext ctx)
	{
		Long value = getEndPostalCode( ctx );
		return value != null ? value.longValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceabilityArea.endPostalCode</code> attribute. 
	 * @return the endPostalCode - End postal code for serviceability area
	 */
	public long getEndPostalCodeAsPrimitive()
	{
		return getEndPostalCodeAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceabilityArea.endPostalCode</code> attribute. 
	 * @param value the endPostalCode - End postal code for serviceability area
	 */
	public void setEndPostalCode(final SessionContext ctx, final Long value)
	{
		setProperty(ctx, ENDPOSTALCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceabilityArea.endPostalCode</code> attribute. 
	 * @param value the endPostalCode - End postal code for serviceability area
	 */
	public void setEndPostalCode(final Long value)
	{
		setEndPostalCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceabilityArea.endPostalCode</code> attribute. 
	 * @param value the endPostalCode - End postal code for serviceability area
	 */
	public void setEndPostalCode(final SessionContext ctx, final long value)
	{
		setEndPostalCode( ctx,Long.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceabilityArea.endPostalCode</code> attribute. 
	 * @param value the endPostalCode - End postal code for serviceability area
	 */
	public void setEndPostalCode(final long value)
	{
		setEndPostalCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceabilityArea.region</code> attribute.
	 * @return the region - region of the area
	 */
	public Region getRegion(final SessionContext ctx)
	{
		return (Region)getProperty( ctx, REGION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceabilityArea.region</code> attribute.
	 * @return the region - region of the area
	 */
	public Region getRegion()
	{
		return getRegion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceabilityArea.region</code> attribute. 
	 * @param value the region - region of the area
	 */
	public void setRegion(final SessionContext ctx, final Region value)
	{
		setProperty(ctx, REGION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceabilityArea.region</code> attribute. 
	 * @param value the region - region of the area
	 */
	public void setRegion(final Region value)
	{
		setRegion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceabilityArea.startPostalCode</code> attribute.
	 * @return the startPostalCode - Beginning postal code for serviceability area
	 */
	public Long getStartPostalCode(final SessionContext ctx)
	{
		return (Long)getProperty( ctx, STARTPOSTALCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceabilityArea.startPostalCode</code> attribute.
	 * @return the startPostalCode - Beginning postal code for serviceability area
	 */
	public Long getStartPostalCode()
	{
		return getStartPostalCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceabilityArea.startPostalCode</code> attribute. 
	 * @return the startPostalCode - Beginning postal code for serviceability area
	 */
	public long getStartPostalCodeAsPrimitive(final SessionContext ctx)
	{
		Long value = getStartPostalCode( ctx );
		return value != null ? value.longValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceabilityArea.startPostalCode</code> attribute. 
	 * @return the startPostalCode - Beginning postal code for serviceability area
	 */
	public long getStartPostalCodeAsPrimitive()
	{
		return getStartPostalCodeAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceabilityArea.startPostalCode</code> attribute. 
	 * @param value the startPostalCode - Beginning postal code for serviceability area
	 */
	public void setStartPostalCode(final SessionContext ctx, final Long value)
	{
		setProperty(ctx, STARTPOSTALCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceabilityArea.startPostalCode</code> attribute. 
	 * @param value the startPostalCode - Beginning postal code for serviceability area
	 */
	public void setStartPostalCode(final Long value)
	{
		setStartPostalCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceabilityArea.startPostalCode</code> attribute. 
	 * @param value the startPostalCode - Beginning postal code for serviceability area
	 */
	public void setStartPostalCode(final SessionContext ctx, final long value)
	{
		setStartPostalCode( ctx,Long.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceabilityArea.startPostalCode</code> attribute. 
	 * @param value the startPostalCode - Beginning postal code for serviceability area
	 */
	public void setStartPostalCode(final long value)
	{
		setStartPostalCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceabilityArea.warehouse</code> attribute.
	 * @return the warehouse - warehouse of the postal code
	 */
	public Warehouse getWarehouse(final SessionContext ctx)
	{
		return (Warehouse)getProperty( ctx, WAREHOUSE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceabilityArea.warehouse</code> attribute.
	 * @return the warehouse - warehouse of the postal code
	 */
	public Warehouse getWarehouse()
	{
		return getWarehouse( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceabilityArea.warehouse</code> attribute. 
	 * @param value the warehouse - warehouse of the postal code
	 */
	public void setWarehouse(final SessionContext ctx, final Warehouse value)
	{
		setProperty(ctx, WAREHOUSE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceabilityArea.warehouse</code> attribute. 
	 * @param value the warehouse - warehouse of the postal code
	 */
	public void setWarehouse(final Warehouse value)
	{
		setWarehouse( getSession().getSessionContext(), value );
	}
	
}
