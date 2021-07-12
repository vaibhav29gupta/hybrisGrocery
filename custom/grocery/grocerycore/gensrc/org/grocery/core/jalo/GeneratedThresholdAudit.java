/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 08-Jul-2020, 11:07:38 PM                    ---
 * ----------------------------------------------------------------
 */
package org.grocery.core.jalo;

import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.grocery.core.constants.GroceryCoreConstants;
import org.grocery.core.jalo.FeatureThreshold;

/**
 * Generated class for type {@link org.grocery.core.jalo.ThresholdAudit ThresholdAudit}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedThresholdAudit extends GenericItem
{
	/** Qualifier of the <code>ThresholdAudit.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>ThresholdAudit.month</code> attribute **/
	public static final String MONTH = "month";
	/** Qualifier of the <code>ThresholdAudit.counter</code> attribute **/
	public static final String COUNTER = "counter";
	/** Qualifier of the <code>ThresholdAudit.featureThreshold</code> attribute **/
	public static final String FEATURETHRESHOLD = "featureThreshold";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n FEATURETHRESHOLD's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedThresholdAudit> FEATURETHRESHOLDHANDLER = new BidirectionalOneToManyHandler<GeneratedThresholdAudit>(
	GroceryCoreConstants.TC.THRESHOLDAUDIT,
	false,
	"featureThreshold",
	null,
	false,
	true,
	CollectionType.LIST
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(MONTH, AttributeMode.INITIAL);
		tmp.put(COUNTER, AttributeMode.INITIAL);
		tmp.put(FEATURETHRESHOLD, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ThresholdAudit.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ThresholdAudit.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ThresholdAudit.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ThresholdAudit.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ThresholdAudit.counter</code> attribute.
	 * @return the counter - number of API requests made
	 */
	public Long getCounter(final SessionContext ctx)
	{
		return (Long)getProperty( ctx, COUNTER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ThresholdAudit.counter</code> attribute.
	 * @return the counter - number of API requests made
	 */
	public Long getCounter()
	{
		return getCounter( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ThresholdAudit.counter</code> attribute. 
	 * @return the counter - number of API requests made
	 */
	public long getCounterAsPrimitive(final SessionContext ctx)
	{
		Long value = getCounter( ctx );
		return value != null ? value.longValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ThresholdAudit.counter</code> attribute. 
	 * @return the counter - number of API requests made
	 */
	public long getCounterAsPrimitive()
	{
		return getCounterAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ThresholdAudit.counter</code> attribute. 
	 * @param value the counter - number of API requests made
	 */
	public void setCounter(final SessionContext ctx, final Long value)
	{
		setProperty(ctx, COUNTER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ThresholdAudit.counter</code> attribute. 
	 * @param value the counter - number of API requests made
	 */
	public void setCounter(final Long value)
	{
		setCounter( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ThresholdAudit.counter</code> attribute. 
	 * @param value the counter - number of API requests made
	 */
	public void setCounter(final SessionContext ctx, final long value)
	{
		setCounter( ctx,Long.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ThresholdAudit.counter</code> attribute. 
	 * @param value the counter - number of API requests made
	 */
	public void setCounter(final long value)
	{
		setCounter( getSession().getSessionContext(), value );
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		FEATURETHRESHOLDHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ThresholdAudit.featureThreshold</code> attribute.
	 * @return the featureThreshold
	 */
	public FeatureThreshold getFeatureThreshold(final SessionContext ctx)
	{
		return (FeatureThreshold)getProperty( ctx, FEATURETHRESHOLD);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ThresholdAudit.featureThreshold</code> attribute.
	 * @return the featureThreshold
	 */
	public FeatureThreshold getFeatureThreshold()
	{
		return getFeatureThreshold( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ThresholdAudit.featureThreshold</code> attribute. 
	 * @param value the featureThreshold
	 */
	public void setFeatureThreshold(final SessionContext ctx, final FeatureThreshold value)
	{
		FEATURETHRESHOLDHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ThresholdAudit.featureThreshold</code> attribute. 
	 * @param value the featureThreshold
	 */
	public void setFeatureThreshold(final FeatureThreshold value)
	{
		setFeatureThreshold( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ThresholdAudit.month</code> attribute.
	 * @return the month - month for threshold
	 */
	public String getMonth(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MONTH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ThresholdAudit.month</code> attribute.
	 * @return the month - month for threshold
	 */
	public String getMonth()
	{
		return getMonth( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ThresholdAudit.month</code> attribute. 
	 * @param value the month - month for threshold
	 */
	public void setMonth(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MONTH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ThresholdAudit.month</code> attribute. 
	 * @param value the month - month for threshold
	 */
	public void setMonth(final String value)
	{
		setMonth( getSession().getSessionContext(), value );
	}
	
}
