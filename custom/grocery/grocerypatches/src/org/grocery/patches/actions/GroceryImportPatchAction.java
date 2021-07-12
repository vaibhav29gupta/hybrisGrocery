package org.grocery.patches.actions;

import de.hybris.platform.patches.Patch;
import de.hybris.platform.patches.actions.ImportPatchAction;
import de.hybris.platform.patches.data.ImpexDataFile;
import de.hybris.platform.patches.data.ImpexHeaderFile;
import de.hybris.platform.patches.data.ImpexImportUnit;

public class GroceryImportPatchAction extends ImportPatchAction {

  private static final String IMPEX_HEADER_FILE_NAME = "globalHeader.impex";

  /**
   * This method was overridden to change the path for patches
   */
  @Override
  protected String getBasePath(Patch patch) {
    return this.getExtensionPath() + this.getReleasesPath() + this.getPatchPath(patch);
  }

  /**
   * This method was overridden to change the name of impex file for global header
   */
  @Override
  protected ImpexImportUnit createGlobalUnit(String basePath, String fileName, String filePrefix) {
    ImpexImportUnit unit = new ImpexImportUnit();
    ImpexDataFile dataFile = new ImpexDataFile();
    String globalPath = this.getGlobalPath();
    dataFile.setFilePath(basePath + globalPath + "/" + fileName);
    dataFile.setName(fileName);
    unit.setImpexDataFile(dataFile);
    ImpexHeaderFile[] headerFileArray = new ImpexHeaderFile[1];
    ImpexHeaderFile headerFile = new ImpexHeaderFile();
    headerFile.setFilePath(basePath + globalPath + "/" + IMPEX_HEADER_FILE_NAME);
    headerFile.setName(IMPEX_HEADER_FILE_NAME);
    headerFileArray[0] = headerFile;
    unit.setImpexHeaderFiles(headerFileArray);
    return unit;
  }
}
