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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.grocery.core.constants.GroceryCoreConstants;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem DeliverySlotInfo}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedDeliverySlotInfo extends GenericItem
{
	/** Qualifier of the <code>DeliverySlotInfo.deliveryDate</code> attribute **/
	public static final String DELIVERYDATE = "deliveryDate";
	/** Qualifier of the <code>DeliverySlotInfo.deliveryStartTime</code> attribute **/
	public static final String DELIVERYSTARTTIME = "deliveryStartTime";
	/** Qualifier of the <code>DeliverySlotInfo.deliveryEndTime</code> attribute **/
	public static final String DELIVERYENDTIME = "deliveryEndTime";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(DELIVERYDATE, AttributeMode.INITIAL);
		tmp.put(DELIVERYSTARTTIME, AttributeMode.INITIAL);
		tmp.put(DELIVERYENDTIME, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotInfo.deliveryDate</code> attribute.
	 * @return the deliveryDate - Date on order will be delivered
	 */
	public Date getDeliveryDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, DELIVERYDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotInfo.deliveryDate</code> attribute.
	 * @return the deliveryDate - Date on order will be delivered
	 */
	public Date getDeliveryDate()
	{
		return getDeliveryDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotInfo.deliveryDate</code> attribute. 
	 * @param value the deliveryDate - Date on order will be delivered
	 */
	public void setDeliveryDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, DELIVERYDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotInfo.deliveryDate</code> attribute. 
	 * @param value the deliveryDate - Date on order will be delivered
	 */
	public void setDeliveryDate(final Date value)
	{
		setDeliveryDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotInfo.deliveryEndTime</code> attribute.
	 * @return the deliveryEndTime - Time at which the order will be delivered
	 */
	public Date getDeliveryEndTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, DELIVERYENDTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotInfo.deliveryEndTime</code> attribute.
	 * @return the deliveryEndTime - Time at which the order will be delivered
	 */
	public Date getDeliveryEndTime()
	{
		return getDeliveryEndTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotInfo.deliveryEndTime</code> attribute. 
	 * @param value the deliveryEndTime - Time at which the order will be delivered
	 */
	public void setDeliveryEndTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, DELIVERYENDTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotInfo.deliveryEndTime</code> attribute. 
	 * @param value the deliveryEndTime - Time at which the order will be delivered
	 */
	public void setDeliveryEndTime(final Date value)
	{
		setDeliveryEndTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotInfo.deliveryStartTime</code> attribute.
	 * @return the deliveryStartTime - Time at which the order will be delivered
	 */
	public Date getDeliveryStartTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, DELIVERYSTARTTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotInfo.deliveryStartTime</code> attribute.
	 * @return the deliveryStartTime - Time at which the order will be delivered
	 */
	public Date getDeliveryStartTime()
	{
		return getDeliveryStartTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotInfo.deliveryStartTime</code> attribute. 
	 * @param value the deliveryStartTime - Time at which the order will be delivered
	 */
	public void setDeliveryStartTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, DELIVERYSTARTTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotInfo.deliveryStartTime</code> attribute. 
	 * @param value the deliveryStartTime - Time at which the order will be delivered
	 */
	public void setDeliveryStartTime(final Date value)
	{
		setDeliveryStartTime( getSession().getSessionContext(), value );
	}
	
}
