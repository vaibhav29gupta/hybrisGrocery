/**
 *
 */
package org.grocery.facades.publish.event.impl;

import org.grocery.facades.publish.event.GroceryPublishEventFacade;
import org.grocery.service.publish.event.GroceryPublishEventService;


/**
 * @author ankituniyal
 *
 */
public class GroceryPublishEventFacadeImpl implements GroceryPublishEventFacade
{

	private GroceryPublishEventService groceryPublishEventService;

	@Override
	public void publishUpdatePasswordEvent(final String token)
	{

		groceryPublishEventService.publishUpdatePasswordEvent(token);
	}

	/**
	 * @return the groceryPublishEventService
	 */
	public GroceryPublishEventService getGroceryPublishEventService()
	{
		return groceryPublishEventService;
	}

	/**
	 * @param groceryPublishEventService
	 *           the groceryPublishEventService to set
	 */
	public void setGroceryPublishEventService(final GroceryPublishEventService groceryPublishEventService)
	{
		this.groceryPublishEventService = groceryPublishEventService;
	}



}
