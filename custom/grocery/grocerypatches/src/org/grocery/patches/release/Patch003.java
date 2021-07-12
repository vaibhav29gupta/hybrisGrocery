package org.grocery.patches.release;

import java.util.EnumSet;

import org.grocery.patches.structure.Environment;
import org.grocery.patches.structure.StructureState;

public class Patch003 extends AbstractGroceryPatch
{
	private static final String RELEASE_001_DESC = "Release 0.0.3 patch";

	public Patch003()
	{
		super("0.0.3", "0.0.3", StructureState.V1);
	}

	@Override
	public String getPatchDescription()
	{
		return RELEASE_001_DESC;
	}

	@Override
	public void createGlobalData(final boolean runAgain)
	{
		importGlobalData("0.0.3_marketing_consent_fix.impex", runAgain);

		//My Changes
		importGlobalData("0.0.3_marketing_consent_fix_en.impex", runAgain);
		importGlobalData("0.0.3_marketing_consent_fix_ja.impex", runAgain);

		importGlobalData("asm.impex", runAgain);
		importGlobalData("smartedit.impex", runAgain);

		//My Changes
		importGlobalData("smartedit_en.impex", runAgain);
		importGlobalData("smartedit_ja.impex", runAgain);

		importGlobalData("1-categories.impex", runAgain);
		importGlobalData("2-categorycategoryrelation.impex", runAgain);
		importGlobalData("3-products.impex", runAgain);
		importGlobalData("4-productcategoryrelation.impex", runAgain);
		importGlobalData("5-prices.impex", runAgain);
		importGlobalData("6-productreferences.impex", runAgain);
		//importGlobalData("7-taxarea.impex", runAgain);
		importGlobalData("8-tax.impex", runAgain);
		importGlobalData("9-producttax.impex", runAgain);
		importGlobalData("10-discounts.impex", runAgain);
		importGlobalData("11-productdiscounts.impex", runAgain);
		importGlobalData("12-vendors.impex", runAgain);
		importGlobalData("13-warehouses.impex", runAgain);
		importGlobalData("14-regions.impex", runAgain);
		importGlobalData("15-serviceabilityarea.impex", runAgain);
		importGlobalData("16-pos.impex", runAgain);
		importGlobalData("17b-posaddress.impex", runAgain);
		importGlobalData("18-poswarehouse.impex", runAgain);
		importGlobalData("19-Stocks.impex", runAgain);
		importGlobalData("warehouse-delivery-modes.impex", runAgain);
		importGlobalData("warehouse-pickup-modes.impex", runAgain);
		importGlobalData("21-country-deliveryModes.impex", runAgain);
		importGlobalData("Media.impex", runAgain);
		importGlobalData("MediaContainer.impex", runAgain);
		importGlobalData("Product Media.impex", runAgain);
		importGlobalData("payment-modes.impex", runAgain);
		importGlobalData("payment-modes_en.impex", runAgain);
		importGlobalData("payment-modes_ja.impex", runAgain);
		importGlobalData("baseCategory-flag.impex", runAgain);
		importGlobalData("user-groups.impex", runAgain);
		importGlobalData("checkout-header-fix.impex", runAgain);
		importGlobalData("CartLevelPromotion.impex", runAgain);
		importGlobalData("homepage.impex", runAgain);
		importGlobalData("homepage_en.impex", runAgain);
		importGlobalData("homepage_ja.impex", runAgain);
		importGlobalData("email-content.impex", runAgain);
		importGlobalData("email-content_en.impex", runAgain);
		importGlobalData("email-content_ja.impex", runAgain);
		importGlobalData("delivery-slots-job.impex", runAgain);
		importGlobalData("categorynavigationnav.impex", runAgain);
		importGlobalData("categorynavigationnav_en.impex", runAgain);
		importGlobalData("categorynavigationnav_ja.impex", runAgain);
		importGlobalData("Keyword_EN.impex", runAgain);
		importGlobalData("Keyword_JP.impex", runAgain);

		//importGlobalData("variant-products.impex", runAgain);
	}

	@Override
	public void createEnvironmentData(final boolean runAgain)
	{
		importEnvironmentData("solr-dev.impex", EnumSet.of(Environment.DEV), runAgain);
		importEnvironmentData("solr-qa.impex", EnumSet.of(Environment.QA), runAgain);
	}

	@Override
	public void performSynchronization()
	{
		performSynchronization(GROCERY_PRODUCT_CATALOG);
		performSynchronization(GROCERY_CONTENT_CATALOG);
	}
}
