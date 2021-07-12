/**
 *
 */
package org.grocery.facades.thresholdcheck.impl;

import org.grocery.core.thresholdcheck.service.ThresholdAuditService;
import org.grocery.facades.thresholdcheck.ThresholdAuditFacade;


/**
 * @author amitsharma09
 *
 */
public class ThresholdAuditFacadeImpl implements ThresholdAuditFacade
{
	private ThresholdAuditService thresholdAuditService;

	public ThresholdAuditService getThresholdAuditService()
	{
		return thresholdAuditService;
	}

	public void setThresholdAuditService(final ThresholdAuditService thresholdAuditService)
	{
		this.thresholdAuditService = thresholdAuditService;
	}


	@Override
	public Boolean isThresholdMet(final String feature)
	{

		return getThresholdAuditService().isThresholdMet(feature);
	}

}
