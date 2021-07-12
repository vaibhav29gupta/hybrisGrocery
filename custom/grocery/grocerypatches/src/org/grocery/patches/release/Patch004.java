package org.grocery.patches.release;

import org.grocery.patches.structure.StructureState;

public class Patch004 extends AbstractGroceryPatch
{
	private static final String RELEASE_004_DESC = "Release 0.0.4 patch";

	public Patch004()
	{
		super("0.0.4", "0.0.4", StructureState.V1);
	}

	@Override
	public String getPatchDescription()
	{
		return RELEASE_004_DESC;
	}

	@Override
	public void createGlobalData(final boolean runAgain)
	{
		importGlobalData("1-recipe.impex", runAgain);

		//My Changes
		importGlobalData("2-recipe_en.impex", runAgain);

		importGlobalData("2-recipe_ja.impex", runAgain);
		importGlobalData("enable_express_checkout.impex", runAgain);
		importGlobalData("3-countryCoordinates.impex", runAgain);
		importGlobalData("searchemptypage.impex", runAgain);

		//My Changes
		importGlobalData("searchemptypage_en.impex", runAgain);
		importGlobalData("searchemptypage_ja.impex", runAgain);

		importGlobalData("site.impex", runAgain);
		importGlobalData("nutition-information.impex", runAgain);
		importGlobalData("nutition-information_en.impex", runAgain);
		importGlobalData("nutition-information_ja.impex", runAgain);
		importGlobalData("document_page_templates.impex", runAgain);
		importGlobalData("document_page_templates_en.impex", runAgain);
	}

	@Override
	public void createEnvironmentData(final boolean runAgain)
	{
		//no environment specific data
	}

	@Override
	public void performSynchronization()
	{
		performSynchronization(GROCERY_PRODUCT_CATALOG);
		performSynchronization(GROCERY_CONTENT_CATALOG);
		performSynchronization(GROCERY_PRODUCT_CATALOG);
	}
}
