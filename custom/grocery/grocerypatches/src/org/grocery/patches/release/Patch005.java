package org.grocery.patches.release;

import org.grocery.patches.structure.StructureState;


public class Patch005 extends AbstractGroceryPatch
{
	private static final String RELEASE_005_DESC = "Release 0.0.5 patch";

	public Patch005()
	{
		super("0.0.5", "0.0.5", StructureState.V1);
	}

	@Override
	public String getPatchDescription()
	{
		return RELEASE_005_DESC;
	}

    @Override
    public void createGlobalData(final boolean runAgain) {
        importGlobalData("social_media_links_restrictions.impex", runAgain);
        importGlobalData("featureThreshold.impex", runAgain);
        //importGlobalData("variantProducts.impex", runAgain);
        importGlobalData("1-abandoned_shopping_cart_email.impex", runAgain);
        importGlobalData("1-abandoned_shopping_cart_email_en.impex", runAgain);
        importGlobalData("1-abandoned_shopping_cart_email_ja.impex", runAgain);
        importGlobalData("1-abandoned_shopping_cart_job.impex", runAgain);
		importGlobalData("default_country_change.impex", runAgain);
		importGlobalData("order-confirmation.impex", runAgain);
		importGlobalData("additional-emails-banner.impex", runAgain);
		importGlobalData("newBOUsers", runAgain);
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
	}
}