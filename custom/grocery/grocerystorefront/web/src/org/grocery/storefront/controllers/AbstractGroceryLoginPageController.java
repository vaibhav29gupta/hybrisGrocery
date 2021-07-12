/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.grocery.storefront.controllers;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.GuestForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import org.grocery.storefront.forms.RegisterForm;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Collections;


/**
 * Abstract base class for login page controllers
 */
public abstract class AbstractGroceryLoginPageController extends AbstractGroceryRegisterPageController
{
	protected static final String SPRING_SECURITY_LAST_USERNAME = "SPRING_SECURITY_LAST_USERNAME";
	private static final String CFG_FACEBOOK_APP_ID_KEY = "social.facebook.app.id";
	private static final String CFG_FACEBOOK_API_VER_KEY = "social.facebook.api.version";
	private static final String CFG_GOOGLE_CLIENT_ID_KEY = "social.google.client.id";

	@Resource(name = "cmsSiteService")
	private CMSSiteService cmsSiteService;

	@ModelAttribute("facebookAppId")
	public String getFacebookAppId() {
		return getSiteConfigService().getProperty(CFG_FACEBOOK_APP_ID_KEY);
	}

	@ModelAttribute("facebookApiVersion")
	public String getFacebookApiVersion() {
		return getSiteConfigService().getProperty(CFG_FACEBOOK_API_VER_KEY);
	}

	@ModelAttribute("googleClientId")
	public String getGoogleClientId() {
		return getSiteConfigService().getProperty(CFG_GOOGLE_CLIENT_ID_KEY);
	}

	protected String getDefaultLoginPage(final boolean loginError, final HttpSession session, final Model model)
			throws CMSItemNotFoundException
	{

		final CMSSiteModel site = cmsSiteService.getCurrentSite();

		model.addAttribute("isFBLoginEnable", site.getEnableFacebookSignup());
		model.addAttribute("isGoogleLoginEnable", site.getEnableGoogleSignUp());
		model.addAttribute("isOTPLoginEnable", site.getEnableOTPSignup());

		final LoginForm loginForm = new LoginForm();
		model.addAttribute(loginForm);
		final var registerForm = new RegisterForm();
		if (registerForm.getPreferredShipmentMode() != null)
		{
			registerForm.setPreferredShipmentMode("");
		}
		model.addAttribute(registerForm);
		model.addAttribute(new GuestForm());

		final String username = (String) session.getAttribute(SPRING_SECURITY_LAST_USERNAME);
		if (username != null)
		{
			session.removeAttribute(SPRING_SECURITY_LAST_USERNAME);
		}

		loginForm.setJ_username(username);
		storeCmsPageInModel(model, getCmsPage());
		setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.INDEX_NOFOLLOW);

		addRegistrationConsentDataToModel(model);

		final Breadcrumb loginBreadcrumbEntry = new Breadcrumb("#",
				getMessageSource().getMessage("header.link.login", null, "header.link.login", getI18nService().getCurrentLocale()),
				null);
		model.addAttribute("breadcrumbs", Collections.singletonList(loginBreadcrumbEntry));

		if (loginError)
		{
			model.addAttribute("loginError", Boolean.valueOf(loginError));
			GlobalMessages.addErrorMessage(model, "login.error.account.not.found.title");
		}

		return getView();
	}
}
