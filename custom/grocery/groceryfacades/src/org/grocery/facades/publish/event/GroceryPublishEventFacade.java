/**
 *
 */
package org.grocery.facades.publish.event;

/**
 * @author ankituniyal
 *
 */
public interface GroceryPublishEventFacade
{

	/**
	 * @param token
	 */
	void publishUpdatePasswordEvent(String token);

}
