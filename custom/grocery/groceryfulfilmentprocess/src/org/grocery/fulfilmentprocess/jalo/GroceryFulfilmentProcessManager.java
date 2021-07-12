/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.grocery.fulfilmentprocess.constants.GroceryFulfilmentProcessConstants;

public class GroceryFulfilmentProcessManager extends GeneratedGroceryFulfilmentProcessManager
{
	public static final GroceryFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (GroceryFulfilmentProcessManager) em.getExtension(GroceryFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
