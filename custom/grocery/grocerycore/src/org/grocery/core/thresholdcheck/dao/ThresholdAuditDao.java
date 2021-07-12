/**
 *
 */
package org.grocery.core.thresholdcheck.dao;

import java.util.List;

import org.grocery.core.model.FeatureThresholdModel;
import org.grocery.core.model.ThresholdAuditModel;


/**
 * @author amitsharma09
 *
 */
public interface ThresholdAuditDao
{
	List<ThresholdAuditModel> getThresholdAudit(String month, String feature);

	List<FeatureThresholdModel> getMonthlyThreshold(String feature);
}
