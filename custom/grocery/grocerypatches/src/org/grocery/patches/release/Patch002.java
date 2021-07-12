package org.grocery.patches.release;

import org.grocery.patches.structure.StructureState;

/**
 * Patch for release 0.0.2
 */
public class Patch002 extends AbstractGroceryPatch {
  private static final String RELEASE_001_DESC = "Release 0.0.2 patch";

  public Patch002() {
    super("0.0.2", "0.0.2", StructureState.V1);
  }

  @Override
  public String getPatchDescription() {
    return RELEASE_001_DESC;
  }

  @Override
  public void createGlobalData(boolean runAgain) {
    importGlobalData("0.0.2_dummy_allEnv.impex", runAgain);
  }

  @Override
  public void createEnvironmentData(boolean runAgain) {
    //no environment specific data
  }

  @Override
  public void performSynchronization() {
    //no need
  }
}
