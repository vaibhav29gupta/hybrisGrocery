/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.patches.setup;

import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.patches.AbstractPatchesSystemSetup;
import de.hybris.platform.patches.internal.logger.PatchLogger;
import de.hybris.platform.patches.internal.logger.PatchLoggerFactory;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.SystemSetupUtils;

import org.grocery.patches.actions.GroceryImportPatchAction;
import org.grocery.patches.constants.GrocerypatchesConstants;

import java.util.List;

import javax.annotation.Resource;

import static de.hybris.platform.patches.internal.logger.PatchLogger.LoggingMode.HAC_CONSOLE;


@SystemSetup(extension = GrocerypatchesConstants.EXTENSIONNAME)
public class GrocerypatchesSystemSetup extends AbstractPatchesSystemSetup {
  private static final PatchLogger LOG = PatchLoggerFactory.getLogger(GroceryImportPatchAction.class);
  private static final String EXECUTE_AT_INIT_CONFIG = "patches.execute.on.platform.initialization";
  private static final String EXECUTION_ON_INIT_DISABLED_MSG =
      "Patch execution on platform initialization is disabled.";

  @Override
  @SystemSetup(type = SystemSetup.Type.ESSENTIAL, process = SystemSetup.Process.ALL)
  public void createEssentialData(final SystemSetupContext setupContext) {
    if (!isPatchExecutionDisabled()) {
      super.createEssentialData(setupContext);
    }
  }

  @Override
  @SystemSetup(type = SystemSetup.Type.PROJECT, process = SystemSetup.Process.ALL)
  public void createProjectData(final SystemSetupContext setupContext) {
    if (!isPatchExecutionDisabled()) {
      super.createProjectData(setupContext);
    }
  }

  @Override
  @SystemSetupParameterMethod
  public List<SystemSetupParameter> getInitializationOptions() {
    return super.getInitializationOptions();
  }

  private boolean isPatchExecutionDisabled() {
    boolean isDisabled = false; ;
    final boolean isExecuteOnInitDisabled =
        Boolean.FALSE.equals(Boolean.valueOf(Config.getParameter(EXECUTE_AT_INIT_CONFIG)));
    final boolean isInit = SystemSetupUtils.isInit(null);

    if (isExecuteOnInitDisabled && isInit) {
      LOG.info(HAC_CONSOLE, EXECUTION_ON_INIT_DISABLED_MSG);
      isDisabled = true;
    }
    return isDisabled;
  }
}
