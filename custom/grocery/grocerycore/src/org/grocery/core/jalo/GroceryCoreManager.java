/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.core.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.grocery.core.constants.GroceryCoreConstants;
import org.grocery.core.setup.CoreSystemSetup;


/**
 * Do not use, please use {@link CoreSystemSetup} instead.
 * 
 */
public class GroceryCoreManager extends GeneratedGroceryCoreManager
{
	public static final GroceryCoreManager getInstance()
	{
		final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (GroceryCoreManager) em.getExtension(GroceryCoreConstants.EXTENSIONNAME);
	}
}
