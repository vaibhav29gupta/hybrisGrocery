/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 08-Jul-2020, 11:07:38 PM                    ---
 * ----------------------------------------------------------------
 */
package org.grocery.core.jalo;

import de.hybris.platform.deliveryzone.jalo.Zone;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.TypeManager;
import de.hybris.platform.storelocator.jalo.PointOfService;
import de.hybris.platform.util.Utilities;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.grocery.core.constants.GroceryCoreConstants;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem DeliverySlotDayConfig}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedDeliverySlotDayConfig extends GenericItem
{
	/** Qualifier of the <code>DeliverySlotDayConfig.carrierCode</code> attribute **/
	public static final String CARRIERCODE = "carrierCode";
	/** Qualifier of the <code>DeliverySlotDayConfig.orderingDayStartTime</code> attribute **/
	public static final String ORDERINGDAYSTARTTIME = "orderingDayStartTime";
	/** Qualifier of the <code>DeliverySlotDayConfig.orderingDayEndTime</code> attribute **/
	public static final String ORDERINGDAYENDTIME = "orderingDayEndTime";
	/** Qualifier of the <code>DeliverySlotDayConfig.deliveryDate</code> attribute **/
	public static final String DELIVERYDATE = "deliveryDate";
	/** Qualifier of the <code>DeliverySlotDayConfig.zone</code> attribute **/
	public static final String ZONE = "zone";
	/** Qualifier of the <code>DeliverySlotDayConfig.pointOfServices</code> attribute **/
	public static final String POINTOFSERVICES = "pointOfServices";
	/** Relation ordering override parameter constants for StoreDeliverySlotDay from ((grocerycore))*/
	protected static String STOREDELIVERYSLOTDAY_SRC_ORDERED = "relation.StoreDeliverySlotDay.source.ordered";
	protected static String STOREDELIVERYSLOTDAY_TGT_ORDERED = "relation.StoreDeliverySlotDay.target.ordered";
	/** Relation disable markmodifed parameter constants for StoreDeliverySlotDay from ((grocerycore))*/
	protected static String STOREDELIVERYSLOTDAY_MARKMODIFIED = "relation.StoreDeliverySlotDay.markmodified";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CARRIERCODE, AttributeMode.INITIAL);
		tmp.put(ORDERINGDAYSTARTTIME, AttributeMode.INITIAL);
		tmp.put(ORDERINGDAYENDTIME, AttributeMode.INITIAL);
		tmp.put(DELIVERYDATE, AttributeMode.INITIAL);
		tmp.put(ZONE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotDayConfig.carrierCode</code> attribute.
	 * @return the carrierCode - Carrier code for which the delivery slots is
	 * 							applicable
	 */
	public String getCarrierCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CARRIERCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotDayConfig.carrierCode</code> attribute.
	 * @return the carrierCode - Carrier code for which the delivery slots is
	 * 							applicable
	 */
	public String getCarrierCode()
	{
		return getCarrierCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotDayConfig.carrierCode</code> attribute. 
	 * @param value the carrierCode - Carrier code for which the delivery slots is
	 * 							applicable
	 */
	public void setCarrierCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CARRIERCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotDayConfig.carrierCode</code> attribute. 
	 * @param value the carrierCode - Carrier code for which the delivery slots is
	 * 							applicable
	 */
	public void setCarrierCode(final String value)
	{
		setCarrierCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotDayConfig.deliveryDate</code> attribute.
	 * @return the deliveryDate - Delivery Date available for selection
	 */
	public Date getDeliveryDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, DELIVERYDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotDayConfig.deliveryDate</code> attribute.
	 * @return the deliveryDate - Delivery Date available for selection
	 */
	public Date getDeliveryDate()
	{
		return getDeliveryDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotDayConfig.deliveryDate</code> attribute. 
	 * @param value the deliveryDate - Delivery Date available for selection
	 */
	public void setDeliveryDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, DELIVERYDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotDayConfig.deliveryDate</code> attribute. 
	 * @param value the deliveryDate - Delivery Date available for selection
	 */
	public void setDeliveryDate(final Date value)
	{
		setDeliveryDate( getSession().getSessionContext(), value );
	}
	
	@Override
	public boolean isMarkModifiedDisabled(final Item referencedItem)
	{
		ComposedType relationSecondEnd0 = TypeManager.getInstance().getComposedType("PointOfService");
		if(relationSecondEnd0.isAssignableFrom(referencedItem.getComposedType()))
		{
			return Utilities.getMarkModifiedOverride(STOREDELIVERYSLOTDAY_MARKMODIFIED);
		}
		return true;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotDayConfig.orderingDayEndTime</code> attribute.
	 * @return the orderingDayEndTime - Ordering date time until which the delivery slot is
	 * 							valid
	 */
	public Date getOrderingDayEndTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ORDERINGDAYENDTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotDayConfig.orderingDayEndTime</code> attribute.
	 * @return the orderingDayEndTime - Ordering date time until which the delivery slot is
	 * 							valid
	 */
	public Date getOrderingDayEndTime()
	{
		return getOrderingDayEndTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotDayConfig.orderingDayEndTime</code> attribute. 
	 * @param value the orderingDayEndTime - Ordering date time until which the delivery slot is
	 * 							valid
	 */
	public void setOrderingDayEndTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ORDERINGDAYENDTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotDayConfig.orderingDayEndTime</code> attribute. 
	 * @param value the orderingDayEndTime - Ordering date time until which the delivery slot is
	 * 							valid
	 */
	public void setOrderingDayEndTime(final Date value)
	{
		setOrderingDayEndTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotDayConfig.orderingDayStartTime</code> attribute.
	 * @return the orderingDayStartTime - Ordering date time after which the delivery slot is
	 * 							valid
	 */
	public Date getOrderingDayStartTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, ORDERINGDAYSTARTTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotDayConfig.orderingDayStartTime</code> attribute.
	 * @return the orderingDayStartTime - Ordering date time after which the delivery slot is
	 * 							valid
	 */
	public Date getOrderingDayStartTime()
	{
		return getOrderingDayStartTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotDayConfig.orderingDayStartTime</code> attribute. 
	 * @param value the orderingDayStartTime - Ordering date time after which the delivery slot is
	 * 							valid
	 */
	public void setOrderingDayStartTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, ORDERINGDAYSTARTTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotDayConfig.orderingDayStartTime</code> attribute. 
	 * @param value the orderingDayStartTime - Ordering date time after which the delivery slot is
	 * 							valid
	 */
	public void setOrderingDayStartTime(final Date value)
	{
		setOrderingDayStartTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotDayConfig.pointOfServices</code> attribute.
	 * @return the pointOfServices
	 */
	public Collection<PointOfService> getPointOfServices(final SessionContext ctx)
	{
		final List<PointOfService> items = getLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTDAY,
			"PointOfService",
			null,
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTDAY_SRC_ORDERED, true),
			false
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotDayConfig.pointOfServices</code> attribute.
	 * @return the pointOfServices
	 */
	public Collection<PointOfService> getPointOfServices()
	{
		return getPointOfServices( getSession().getSessionContext() );
	}
	
	public long getPointOfServicesCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			false,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTDAY,
			"PointOfService",
			null
		);
	}
	
	public long getPointOfServicesCount()
	{
		return getPointOfServicesCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotDayConfig.pointOfServices</code> attribute. 
	 * @param value the pointOfServices
	 */
	public void setPointOfServices(final SessionContext ctx, final Collection<PointOfService> value)
	{
		setLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTDAY,
			null,
			value,
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTDAY_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(STOREDELIVERYSLOTDAY_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotDayConfig.pointOfServices</code> attribute. 
	 * @param value the pointOfServices
	 */
	public void setPointOfServices(final Collection<PointOfService> value)
	{
		setPointOfServices( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to pointOfServices. 
	 * @param value the item to add to pointOfServices
	 */
	public void addToPointOfServices(final SessionContext ctx, final PointOfService value)
	{
		addLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTDAY,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTDAY_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(STOREDELIVERYSLOTDAY_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to pointOfServices. 
	 * @param value the item to add to pointOfServices
	 */
	public void addToPointOfServices(final PointOfService value)
	{
		addToPointOfServices( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from pointOfServices. 
	 * @param value the item to remove from pointOfServices
	 */
	public void removeFromPointOfServices(final SessionContext ctx, final PointOfService value)
	{
		removeLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTDAY,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTDAY_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(STOREDELIVERYSLOTDAY_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from pointOfServices. 
	 * @param value the item to remove from pointOfServices
	 */
	public void removeFromPointOfServices(final PointOfService value)
	{
		removeFromPointOfServices( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotDayConfig.zone</code> attribute.
	 * @return the zone - Zone for which this delivery time slot belongs.
	 */
	public Zone getZone(final SessionContext ctx)
	{
		return (Zone)getProperty( ctx, ZONE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotDayConfig.zone</code> attribute.
	 * @return the zone - Zone for which this delivery time slot belongs.
	 */
	public Zone getZone()
	{
		return getZone( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotDayConfig.zone</code> attribute. 
	 * @param value the zone - Zone for which this delivery time slot belongs.
	 */
	public void setZone(final SessionContext ctx, final Zone value)
	{
		setProperty(ctx, ZONE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotDayConfig.zone</code> attribute. 
	 * @param value the zone - Zone for which this delivery time slot belongs.
	 */
	public void setZone(final Zone value)
	{
		setZone( getSession().getSessionContext(), value );
	}
	
}
