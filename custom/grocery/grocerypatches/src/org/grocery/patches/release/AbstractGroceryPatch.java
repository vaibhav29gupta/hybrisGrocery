/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package org.grocery.patches.release;

import de.hybris.platform.commerceservices.setup.SetupSyncJobService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.patches.AbstractPatch;
import de.hybris.platform.patches.internal.logger.PatchLogger;
import de.hybris.platform.patches.internal.logger.PatchLoggerFactory;
import de.hybris.platform.patches.organisation.ImportLanguage;
import de.hybris.platform.patches.organisation.StructureState;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import org.grocery.patches.structure.Environment;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;

import javax.annotation.Resource;

import static de.hybris.platform.patches.internal.logger.PatchLogger.LoggingMode.HAC_CONSOLE;


/**
 * Project specific parent class for all patches. It works as facade giving nice API for Patches class and translate
 * calls to not project specific format.
 */
public abstract class AbstractGroceryPatch extends AbstractPatch implements GroceryPatch {

  protected static final String GROCERY_PRODUCT_CATALOG = "spruceProductCatalog";
  protected static final String GROCERY_CONTENT_CATALOG = "spruceContentCatalog";
  private static final PatchLogger LOG = PatchLoggerFactory.getLogger(AbstractGroceryPatch.class);
  private static final String ENV_DIRECTORY = "env/";
  private static final Collection<ImportLanguage> LANGS= Collections.emptySet();
  private String environment;

  @Resource
  private SetupSyncJobService setupSyncJobService;

  public AbstractGroceryPatch(final String patchId, final String patchName, final StructureState structureState) {
    super(patchId, patchName, null, structureState);
  }

  @Override
  public void createProjectData(final StructureState structureState) {
    final boolean update = structureState != this.structureState;
    createGlobalData(update);
    createEnvironmentData(update);
    performSynchronization();
  }

  /**
   * Run the catalog sync for the specified catalog.
   *
   * @param catalogName catalog name
   */
  protected void performSynchronization(final String catalogName) {
    final PerformResult syncCronJobResult = setupSyncJobService.executeCatalogSyncJob(catalogName);
    if (isSyncRerunNeeded(syncCronJobResult)) {
      LOG.warn(HAC_CONSOLE, String.format("Catalog [%s] sync has issues.", catalogName));
    }
  }

  /**
   * This method is responsible to import impex data for given environments and languages.
   *
   * @param fileName     of impex to be imported
   * @param environments collection of {@link Environment}
   */
  protected void importEnvironmentData(final String fileName, final EnumSet<Environment> environments, boolean runAgain) {
    if (containsCurrentEnvironment(environments)) {
      importData(ENV_DIRECTORY + fileName, null, LANGS, runAgain, null, null);
    }
  }

  private boolean containsCurrentEnvironment(final EnumSet<Environment> environments) {
    return environments.stream().map(Environment::getCode).anyMatch(s -> s.equals(getEnvironment()));
  }


  protected void importGlobalData(String fileName, boolean runAgain) {
    super.importGlobalData(fileName, LANGS, runAgain, null);
  }


  private boolean isSyncRerunNeeded(final PerformResult syncCronJobResult) {
    return syncCronJobResult == null || (CronJobStatus.FINISHED.equals(syncCronJobResult.getStatus())
                                         && !CronJobResult.SUCCESS.equals(syncCronJobResult.getResult()));
  }

  public String getEnvironment() {
    return environment;
  }

  @Required
  public void setEnvironment(String environment) {
    this.environment = environment;
  }
}
