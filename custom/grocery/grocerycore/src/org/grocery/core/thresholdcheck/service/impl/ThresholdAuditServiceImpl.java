/**
 *
 */
package org.grocery.core.thresholdcheck.service.impl;

import de.hybris.platform.servicelayer.model.ModelService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.grocery.core.model.FeatureThresholdModel;
import org.grocery.core.model.ThresholdAuditModel;
import org.grocery.core.thresholdcheck.dao.ThresholdAuditDao;
import org.grocery.core.thresholdcheck.service.ThresholdAuditService;


/**
 * @author amitsharma09
 *
 */
public class ThresholdAuditServiceImpl implements ThresholdAuditService
{

	private ThresholdAuditDao thresholdAuditDao;
	private ModelService modelService;


	@Override
	public Boolean isThresholdMet(final String feature)
	{

		Long monthly_threshold = 0L;
		final Date date = new Date();
		final SimpleDateFormat DateFor = new SimpleDateFormat("MMyyyy");
		final String month = DateFor.format(date);

		final List<ThresholdAuditModel> thresholdAudit = getThresholdAuditDao().getThresholdAudit(month, feature);

		final List<FeatureThresholdModel> featureThresholdModel = getThresholdAuditDao().getMonthlyThreshold(feature);

		if (!featureThresholdModel.isEmpty())
		{
			if (!featureThresholdModel.get(0).isEnableFeature())
			{
				return Boolean.FALSE;
			}
			if (featureThresholdModel.get(0).getMonthlyThreshold() != null)
			{
				monthly_threshold = featureThresholdModel.get(0).getMonthlyThreshold();
			}
		}
		else {
			return Boolean.TRUE;
		}

		if (thresholdAudit.isEmpty())
		{
			final Long counter = 1L;
			final String code = month + "_" + feature;

			final ThresholdAuditModel thresholdAuditModel = getModelService().create(ThresholdAuditModel.class);
			thresholdAuditModel.setCode(code);
			thresholdAuditModel.setMonth(month);
			thresholdAuditModel.setCounter(counter);
			thresholdAuditModel.setFeatureThreshold(featureThresholdModel.get(0));

			getModelService().save(thresholdAuditModel);

			return Boolean.TRUE;
		}
		else if (thresholdAudit.size() > 0 && thresholdAudit.get(0).getCounter() < monthly_threshold)
		{

			final ThresholdAuditModel thModel = thresholdAudit.get(0);
			Long counter = thModel.getCounter();
			counter += 1;
			thModel.setCounter(counter);
			getModelService().save(thModel);
			return Boolean.TRUE;
		}
		else
		{
			return Boolean.FALSE;
		}
	}


	public ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public ThresholdAuditDao getThresholdAuditDao()
	{
		return thresholdAuditDao;
	}

	public void setThresholdAuditDao(final ThresholdAuditDao thresholdAuditDao)
	{
		this.thresholdAuditDao = thresholdAuditDao;
	}

}
