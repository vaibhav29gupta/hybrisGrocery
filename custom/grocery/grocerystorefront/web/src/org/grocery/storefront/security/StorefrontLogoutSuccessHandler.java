/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.storefront.security;

import de.hybris.platform.acceleratorstorefrontcommons.security.GUIDCookieStrategy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.servicelayer.session.SessionService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;


public class StorefrontLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler
{

	public static final String SITE_PARAMETER_NAME = "?site=";
	public static final String LOGIN_URL = "/grocerystorefront/spruce/login/";
	private GUIDCookieStrategy guidCookieStrategy;
	private List<String> restrictedPages;
	private SessionService sessionService;
	private CMSSiteService cmsSiteService;

	private static final String DEFAULT_SITE_UID="spruce";
	public static final String USER_CONSENTS = "user-consents";

	protected GUIDCookieStrategy getGuidCookieStrategy()
	{
		return guidCookieStrategy;
	}

	@Required
	public void setGuidCookieStrategy(final GUIDCookieStrategy guidCookieStrategy)
	{
		this.guidCookieStrategy = guidCookieStrategy;
	}

	protected List<String> getRestrictedPages()
	{
		return restrictedPages;
	}

	public void setRestrictedPages(final List<String> restrictedPages)
	{
		this.restrictedPages = restrictedPages;
	}

	@Override
	public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException
	{
		getGuidCookieStrategy().deleteCookie(request, response);
		this.getSessionService().removeAttribute(USER_CONSENTS);
		String redirectPath=determineTargetUrl(request,response);
		response.sendRedirect(redirectPath);
	}




	@Override
	protected String determineTargetUrl(final HttpServletRequest request, final HttpServletResponse response)
	{
		String targetUrl = super.determineTargetUrl(request, response);
		final CMSSiteModel currentSiteModel = getCmsSiteService().getCurrentSite();
		if (currentSiteModel != null)
		{
			targetUrl += SITE_PARAMETER_NAME + currentSiteModel.getUid();
		}
		else
		{
			targetUrl += SITE_PARAMETER_NAME+DEFAULT_SITE_UID;
		}
		for (final String restrictedPage : getRestrictedPages())
		{
			// When logging out from a restricted page, return user to homepage.
			if (targetUrl.contains(restrictedPage))
			{
				targetUrl = LOGIN_URL+SITE_PARAMETER_NAME+DEFAULT_SITE_UID;
			}
		}

		return targetUrl;
	}

	protected SessionService getSessionService() {
		return this.sessionService;
	}

	@Required
	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}
	protected CMSSiteService getCmsSiteService() {
		return cmsSiteService;
	}
	@Required
	public void setCmsSiteService(final CMSSiteService cmsSiteService) {
		this.cmsSiteService = cmsSiteService;
	}

}
