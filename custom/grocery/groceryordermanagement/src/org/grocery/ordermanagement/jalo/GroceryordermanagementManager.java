package org.grocery.ordermanagement.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;
import org.grocery.ordermanagement.constants.GroceryordermanagementConstants;

public class GroceryordermanagementManager extends GeneratedGroceryordermanagementManager
{
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger( GroceryordermanagementManager.class.getName() );
	
	public static final GroceryordermanagementManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (GroceryordermanagementManager) em.getExtension(GroceryordermanagementConstants.EXTENSIONNAME);
	}
	
}
