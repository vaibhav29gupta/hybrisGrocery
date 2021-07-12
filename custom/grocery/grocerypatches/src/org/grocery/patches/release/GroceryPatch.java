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

import de.hybris.platform.patches.Patch;
import de.hybris.platform.patches.organisation.ImportLanguage;

import java.util.Set;


/**
 * GroceryPatch specific interface. Adds global and environment specific methods.
 */
public interface GroceryPatch extends Patch {

  /**
   * Hook to perform full catalog sync
   */
  void performSynchronization();

  /**
   * Creates global data
   */
  void createGlobalData(boolean runAgain);

  /**
   * Creates environment data
   */
  void createEnvironmentData(boolean runAgain);
}
