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
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.grocery.core.constants.GroceryCoreConstants;
import org.grocery.core.jalo.ThresholdAudit;

/**
 * Generated class for type {@link org.grocery.core.jalo.FeatureThreshold FeatureThreshold}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedFeatureThreshold extends GenericItem
{
	/** Qualifier of the <code>FeatureThreshold.feature</code> attribute **/
	public static final String FEATURE = "feature";
	/** Qualifier of the <code>FeatureThreshold.monthlyThreshold</code> attribute **/
	public static final String MONTHLYTHRESHOLD = "monthlyThreshold";
	/** Qualifier of the <code>FeatureThreshold.enableFeature</code> attribute **/
	public static final String ENABLEFEATURE = "enableFeature";
	/** Qualifier of the <code>FeatureThreshold.thresholdAudit</code> attribute **/
	public static final String THRESHOLDAUDIT = "thresholdAudit";
	/**
	* {@link OneToManyHandler} for handling 1:n THRESHOLDAUDIT's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<ThresholdAudit> THRESHOLDAUDITHANDLER = new OneToManyHandler<ThresholdAudit>(
	GroceryCoreConstants.TC.THRESHOLDAUDIT,
	false,
	"featureThreshold",
	null,
	false,
	true,
	CollectionType.LIST
	).withRelationQualifier("thresholdAudit");
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(FEATURE, AttributeMode.INITIAL);
		tmp.put(MONTHLYTHRESHOLD, AttributeMode.INITIAL);
		tmp.put(ENABLEFEATURE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeatureThreshold.enableFeature</code> attribute.
	 * @return the enableFeature - monthly Threshold value
	 */
	public Boolean isEnableFeature(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ENABLEFEATURE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeatureThreshold.enableFeature</code> attribute.
	 * @return the enableFeature - monthly Threshold value
	 */
	public Boolean isEnableFeature()
	{
		return isEnableFeature( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeatureThreshold.enableFeature</code> attribute. 
	 * @return the enableFeature - monthly Threshold value
	 */
	public boolean isEnableFeatureAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isEnableFeature( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeatureThreshold.enableFeature</code> attribute. 
	 * @return the enableFeature - monthly Threshold value
	 */
	public boolean isEnableFeatureAsPrimitive()
	{
		return isEnableFeatureAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeatureThreshold.enableFeature</code> attribute. 
	 * @param value the enableFeature - monthly Threshold value
	 */
	public void setEnableFeature(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ENABLEFEATURE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeatureThreshold.enableFeature</code> attribute. 
	 * @param value the enableFeature - monthly Threshold value
	 */
	public void setEnableFeature(final Boolean value)
	{
		setEnableFeature( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeatureThreshold.enableFeature</code> attribute. 
	 * @param value the enableFeature - monthly Threshold value
	 */
	public void setEnableFeature(final SessionContext ctx, final boolean value)
	{
		setEnableFeature( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeatureThreshold.enableFeature</code> attribute. 
	 * @param value the enableFeature - monthly Threshold value
	 */
	public void setEnableFeature(final boolean value)
	{
		setEnableFeature( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeatureThreshold.feature</code> attribute.
	 * @return the feature - Threshold feature-email, SMS or Store_locator
	 */
	public EnumerationValue getFeature(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, FEATURE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeatureThreshold.feature</code> attribute.
	 * @return the feature - Threshold feature-email, SMS or Store_locator
	 */
	public EnumerationValue getFeature()
	{
		return getFeature( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeatureThreshold.feature</code> attribute. 
	 * @param value the feature - Threshold feature-email, SMS or Store_locator
	 */
	public void setFeature(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, FEATURE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeatureThreshold.feature</code> attribute. 
	 * @param value the feature - Threshold feature-email, SMS or Store_locator
	 */
	public void setFeature(final EnumerationValue value)
	{
		setFeature( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeatureThreshold.monthlyThreshold</code> attribute.
	 * @return the monthlyThreshold - monthly Threshold value
	 */
	public Long getMonthlyThreshold(final SessionContext ctx)
	{
		return (Long)getProperty( ctx, MONTHLYTHRESHOLD);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeatureThreshold.monthlyThreshold</code> attribute.
	 * @return the monthlyThreshold - monthly Threshold value
	 */
	public Long getMonthlyThreshold()
	{
		return getMonthlyThreshold( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeatureThreshold.monthlyThreshold</code> attribute. 
	 * @return the monthlyThreshold - monthly Threshold value
	 */
	public long getMonthlyThresholdAsPrimitive(final SessionContext ctx)
	{
		Long value = getMonthlyThreshold( ctx );
		return value != null ? value.longValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeatureThreshold.monthlyThreshold</code> attribute. 
	 * @return the monthlyThreshold - monthly Threshold value
	 */
	public long getMonthlyThresholdAsPrimitive()
	{
		return getMonthlyThresholdAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeatureThreshold.monthlyThreshold</code> attribute. 
	 * @param value the monthlyThreshold - monthly Threshold value
	 */
	public void setMonthlyThreshold(final SessionContext ctx, final Long value)
	{
		setProperty(ctx, MONTHLYTHRESHOLD,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeatureThreshold.monthlyThreshold</code> attribute. 
	 * @param value the monthlyThreshold - monthly Threshold value
	 */
	public void setMonthlyThreshold(final Long value)
	{
		setMonthlyThreshold( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeatureThreshold.monthlyThreshold</code> attribute. 
	 * @param value the monthlyThreshold - monthly Threshold value
	 */
	public void setMonthlyThreshold(final SessionContext ctx, final long value)
	{
		setMonthlyThreshold( ctx,Long.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeatureThreshold.monthlyThreshold</code> attribute. 
	 * @param value the monthlyThreshold - monthly Threshold value
	 */
	public void setMonthlyThreshold(final long value)
	{
		setMonthlyThreshold( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeatureThreshold.thresholdAudit</code> attribute.
	 * @return the thresholdAudit
	 */
	public List<ThresholdAudit> getThresholdAudit(final SessionContext ctx)
	{
		return (List<ThresholdAudit>)THRESHOLDAUDITHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FeatureThreshold.thresholdAudit</code> attribute.
	 * @return the thresholdAudit
	 */
	public List<ThresholdAudit> getThresholdAudit()
	{
		return getThresholdAudit( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeatureThreshold.thresholdAudit</code> attribute. 
	 * @param value the thresholdAudit
	 */
	public void setThresholdAudit(final SessionContext ctx, final List<ThresholdAudit> value)
	{
		THRESHOLDAUDITHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FeatureThreshold.thresholdAudit</code> attribute. 
	 * @param value the thresholdAudit
	 */
	public void setThresholdAudit(final List<ThresholdAudit> value)
	{
		setThresholdAudit( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to thresholdAudit. 
	 * @param value the item to add to thresholdAudit
	 */
	public void addToThresholdAudit(final SessionContext ctx, final ThresholdAudit value)
	{
		THRESHOLDAUDITHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to thresholdAudit. 
	 * @param value the item to add to thresholdAudit
	 */
	public void addToThresholdAudit(final ThresholdAudit value)
	{
		addToThresholdAudit( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from thresholdAudit. 
	 * @param value the item to remove from thresholdAudit
	 */
	public void removeFromThresholdAudit(final SessionContext ctx, final ThresholdAudit value)
	{
		THRESHOLDAUDITHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from thresholdAudit. 
	 * @param value the item to remove from thresholdAudit
	 */
	public void removeFromThresholdAudit(final ThresholdAudit value)
	{
		removeFromThresholdAudit( getSession().getSessionContext(), value );
	}
	
}
