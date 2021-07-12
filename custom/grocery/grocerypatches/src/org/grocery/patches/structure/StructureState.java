package org.grocery.patches.structure;

/**
 * Enumeration that defines structure state; it may be used in different objects to indicate for which milestone given
 * object was introduced.
 */
public enum StructureState implements de.hybris.platform.patches.organisation.StructureState {

  V1, V2;

  @Override
  public boolean isAfter(final de.hybris.platform.patches.organisation.StructureState structureState) {
    if (this == structureState) {
      return false;
    }
    for (final StructureState iterateValue : values()) {
      if (structureState.equals(iterateValue)) {
        return true;
      }
      if (this.equals(iterateValue)) {
        return false;
      }
    }
    return false;
  }
}
