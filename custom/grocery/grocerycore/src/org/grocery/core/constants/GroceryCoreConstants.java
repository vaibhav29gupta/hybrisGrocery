/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.core.constants;

/**
 * Global class for all GroceryCore constants. You can add global constants for your extension into this class.
 */
public final class GroceryCoreConstants extends GeneratedGroceryCoreConstants
{
	public static final String EXTENSIONNAME = "grocerycore";


	private GroceryCoreConstants()
	{
		//empty
	}

	// implement here constants used by this extension
	public static final String QUOTE_BUYER_PROCESS = "quote-buyer-process";
	public static final String QUOTE_SALES_REP_PROCESS = "quote-salesrep-process";
	public static final String QUOTE_USER_TYPE = "QUOTE_USER_TYPE";
	public static final String QUOTE_SELLER_APPROVER_PROCESS = "quote-seller-approval-process";
	public static final String QUOTE_TO_EXPIRE_SOON_EMAIL_PROCESS = "quote-to-expire-soon-email-process";
	public static final String QUOTE_EXPIRED_EMAIL_PROCESS = "quote-expired-email-process";
	public static final String QUOTE_POST_CANCELLATION_PROCESS = "quote-post-cancellation-process";
	public static final String DEFAULT_SERVICEABILITY_STORE_APPEND = "-store";
	public static final String COUNTRY_SESSION_ATTR_KEY = "country".intern();

}
