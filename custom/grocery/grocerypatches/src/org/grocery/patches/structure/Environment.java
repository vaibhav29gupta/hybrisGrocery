package org.grocery.patches.structure;

import de.hybris.platform.patches.organisation.ImportLanguage;
import de.hybris.platform.patches.organisation.ImportOrganisationUnit;

import java.util.Collection;

/**
 * Environments enumeration that can be used in import process.
 */
public enum Environment implements ImportOrganisationUnit<Environment, Environment> {

  DEV("dev", "DEVELOP", StructureState.V1),
  QA("qa", "QA", StructureState.V1);

  private final String code;
  private final String name;
  private final StructureState structureState;

  Environment(final String code, final String name, final StructureState structureState) {
    this.code = code;
    this.name = name;
    this.structureState = structureState;
  }

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getFolderName() {
    return null;
  }

  @Override
  public String getCommonFolderName() {
    return null;
  }

  @Override
  public Collection<Environment> getChildren() {
    return null;
  }

  @Override
  public Collection<ImportLanguage> getLanguages() {
    return null;
  }

  @Override
  public Environment getParent() {
    return null;
  }

  @Override
  public void setParent(Environment environment) {
    throw new UnsupportedOperationException("Parent can't be set for Environment");
  }

  @Override
  public StructureState getStructureState() {
    return this.structureState;
  }
}
