package org.grocery.patches.release;

import org.grocery.patches.structure.Environment;
import org.grocery.patches.structure.StructureState;

import java.util.EnumSet;

/**
 * Patch for release 0.0.2
 */
public class Patch001 extends AbstractGroceryPatch {
  private static final String RELEASE_001_DESC = "Release 0.0.1 patch";

  public Patch001() {
    super("0.0.1", "0.0.1", StructureState.V1);
  }

  @Override
  public String getPatchDescription() {
    return RELEASE_001_DESC;
  }

  @Override
  public void createGlobalData(boolean runAgain) {
    importGlobalData("0.0.1_dummy_allEnv.impex", runAgain);
  }

  @Override
  public void createEnvironmentData(boolean runAgain) {
    importEnvironmentData("0.0.1_dummy_dev.impex", EnumSet.of(Environment.DEV), runAgain);
    importEnvironmentData("0.0.1_dummy_qa.impex", EnumSet.of(Environment.QA), runAgain);
  }

  @Override
  public void performSynchronization() {
    performSynchronization(GROCERY_PRODUCT_CATALOG);
    performSynchronization(GROCERY_CONTENT_CATALOG);
  }
}
