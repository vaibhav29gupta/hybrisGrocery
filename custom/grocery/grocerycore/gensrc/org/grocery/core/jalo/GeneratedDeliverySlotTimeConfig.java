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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem DeliverySlotTimeConfig}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedDeliverySlotTimeConfig extends GenericItem
{
	/** Qualifier of the <code>DeliverySlotTimeConfig.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>DeliverySlotTimeConfig.openingTime</code> attribute **/
	public static final String OPENINGTIME = "openingTime";
	/** Qualifier of the <code>DeliverySlotTimeConfig.closingTime</code> attribute **/
	public static final String CLOSINGTIME = "closingTime";
	/** Qualifier of the <code>DeliverySlotTimeConfig.carrierCode</code> attribute **/
	public static final String CARRIERCODE = "carrierCode";
	/** Qualifier of the <code>DeliverySlotTimeConfig.zone</code> attribute **/
	public static final String ZONE = "zone";
	/** Qualifier of the <code>DeliverySlotTimeConfig.pointOfServices</code> attribute **/
	public static final String POINTOFSERVICES = "pointOfServices";
	/** Relation ordering override parameter constants for StoreDeliverySlotTime from ((grocerycore))*/
	protected static String STOREDELIVERYSLOTTIME_SRC_ORDERED = "relation.StoreDeliverySlotTime.source.ordered";
	protected static String STOREDELIVERYSLOTTIME_TGT_ORDERED = "relation.StoreDeliverySlotTime.target.ordered";
	/** Relation disable markmodifed parameter constants for StoreDeliverySlotTime from ((grocerycore))*/
	protected static String STOREDELIVERYSLOTTIME_MARKMODIFIED = "relation.StoreDeliverySlotTime.markmodified";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(OPENINGTIME, AttributeMode.INITIAL);
		tmp.put(CLOSINGTIME, AttributeMode.INITIAL);
		tmp.put(CARRIERCODE, AttributeMode.INITIAL);
		tmp.put(ZONE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotTimeConfig.carrierCode</code> attribute.
	 * @return the carrierCode - Carrier code for which the delivery time slot
	 * 							belongs.
	 */
	public String getCarrierCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CARRIERCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotTimeConfig.carrierCode</code> attribute.
	 * @return the carrierCode - Carrier code for which the delivery time slot
	 * 							belongs.
	 */
	public String getCarrierCode()
	{
		return getCarrierCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotTimeConfig.carrierCode</code> attribute. 
	 * @param value the carrierCode - Carrier code for which the delivery time slot
	 * 							belongs.
	 */
	public void setCarrierCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CARRIERCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotTimeConfig.carrierCode</code> attribute. 
	 * @param value the carrierCode - Carrier code for which the delivery time slot
	 * 							belongs.
	 */
	public void setCarrierCode(final String value)
	{
		setCarrierCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotTimeConfig.closingTime</code> attribute.
	 * @return the closingTime
	 */
	public Date getClosingTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, CLOSINGTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotTimeConfig.closingTime</code> attribute.
	 * @return the closingTime
	 */
	public Date getClosingTime()
	{
		return getClosingTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotTimeConfig.closingTime</code> attribute. 
	 * @param value the closingTime
	 */
	public void setClosingTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, CLOSINGTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotTimeConfig.closingTime</code> attribute. 
	 * @param value the closingTime
	 */
	public void setClosingTime(final Date value)
	{
		setClosingTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotTimeConfig.code</code> attribute.
	 * @return the code - Code for which this delivery time slot belongs.
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotTimeConfig.code</code> attribute.
	 * @return the code - Code for which this delivery time slot belongs.
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotTimeConfig.code</code> attribute. 
	 * @param value the code - Code for which this delivery time slot belongs.
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotTimeConfig.code</code> attribute. 
	 * @param value the code - Code for which this delivery time slot belongs.
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	@Override
	public boolean isMarkModifiedDisabled(final Item referencedItem)
	{
		ComposedType relationSecondEnd0 = TypeManager.getInstance().getComposedType("PointOfService");
		if(relationSecondEnd0.isAssignableFrom(referencedItem.getComposedType()))
		{
			return Utilities.getMarkModifiedOverride(STOREDELIVERYSLOTTIME_MARKMODIFIED);
		}
		return true;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotTimeConfig.openingTime</code> attribute.
	 * @return the openingTime
	 */
	public Date getOpeningTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, OPENINGTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotTimeConfig.openingTime</code> attribute.
	 * @return the openingTime
	 */
	public Date getOpeningTime()
	{
		return getOpeningTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotTimeConfig.openingTime</code> attribute. 
	 * @param value the openingTime
	 */
	public void setOpeningTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, OPENINGTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotTimeConfig.openingTime</code> attribute. 
	 * @param value the openingTime
	 */
	public void setOpeningTime(final Date value)
	{
		setOpeningTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotTimeConfig.pointOfServices</code> attribute.
	 * @return the pointOfServices
	 */
	public Collection<PointOfService> getPointOfServices(final SessionContext ctx)
	{
		final List<PointOfService> items = getLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTTIME,
			"PointOfService",
			null,
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTTIME_SRC_ORDERED, true),
			false
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotTimeConfig.pointOfServices</code> attribute.
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
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTTIME,
			"PointOfService",
			null
		);
	}
	
	public long getPointOfServicesCount()
	{
		return getPointOfServicesCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotTimeConfig.pointOfServices</code> attribute. 
	 * @param value the pointOfServices
	 */
	public void setPointOfServices(final SessionContext ctx, final Collection<PointOfService> value)
	{
		setLinkedItems( 
			ctx,
			false,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTTIME,
			null,
			value,
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTTIME_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(STOREDELIVERYSLOTTIME_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotTimeConfig.pointOfServices</code> attribute. 
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
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTTIME,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTTIME_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(STOREDELIVERYSLOTTIME_MARKMODIFIED)
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
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTTIME,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTTIME_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(STOREDELIVERYSLOTTIME_MARKMODIFIED)
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
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotTimeConfig.zone</code> attribute.
	 * @return the zone - Zone for which this delivery time slot belongs.
	 */
	public Zone getZone(final SessionContext ctx)
	{
		return (Zone)getProperty( ctx, ZONE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DeliverySlotTimeConfig.zone</code> attribute.
	 * @return the zone - Zone for which this delivery time slot belongs.
	 */
	public Zone getZone()
	{
		return getZone( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotTimeConfig.zone</code> attribute. 
	 * @param value the zone - Zone for which this delivery time slot belongs.
	 */
	public void setZone(final SessionContext ctx, final Zone value)
	{
		setProperty(ctx, ZONE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DeliverySlotTimeConfig.zone</code> attribute. 
	 * @param value the zone - Zone for which this delivery time slot belongs.
	 */
	public void setZone(final Zone value)
	{
		setZone( getSession().getSessionContext(), value );
	}
	
}
