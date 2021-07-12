/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.storefront.controllers.pages;

import org.grocery.storefront.controllers.AbstractGroceryPageController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Controller for MyCart functionality.
 */
@Controller
@RequestMapping(value = "/my-cart")
public class MyCartPageController extends AbstractGroceryPageController
{
	private static final String FORWARD_LOGIN_URL = FORWARD_PREFIX + "/login";
	private static final String REDIRECT_CART_URL = REDIRECT_PREFIX + "/cart";

	@RequestMapping(method = RequestMethod.GET)
	public String getLoginCart()
	{
		return this.getUserFacade().isAnonymousUser() ? FORWARD_LOGIN_URL : REDIRECT_CART_URL;
	}
}
