package org.grocery.storefront.controllers.misc;

import de.hybris.platform.acceleratorfacades.order.AcceleratorCheckoutFacade;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.forms.SaveCartForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.SaveCartFacade;
import de.hybris.platform.selectivecartfacades.data.Wishlist2Data;
import de.hybris.platform.selectivecartfacades.data.Wishlist2EntryData;
import de.hybris.platform.wishlist2.enums.Wishlist2EntryPriority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.grocery.facades.shipment.ShipmentFacade;
import org.grocery.facades.wishlist.WishListFacade;
import org.grocery.storefront.controllers.ControllerConstants;
import org.grocery.storefront.controllers.pages.AccountPageController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * @author ojasmodi
 *
 */
@Controller
@RequestMapping(value = "/wishlist")
public class WishlistController extends AccountPageController
{
	private static final int DEFAULT_DESIRED = 1;
	private static final Wishlist2EntryPriority DEFAULT_WISHLIST_ENTRY_PRIORITY = Wishlist2EntryPriority.MEDIUM;
	private static final String DEFAULT_WISHLIST_ENTRY_COMMENT = "";
	private static final String USER_WISHLIST_DATA = "userWishLists";
	private static final String WISHLIST_ACCOUNT_CMS_PAGE = "wishlist";
	private static final String WISHLIST_DETAILS_ACCOUNT_CMS_PAGE = "wishlist-detail";
	private static final String PRODUCT_CODE = "{productCode:.*}";
	private static final String WISHLIST_DETAIL_PATH_VARIABLE_PATTERN = "{wishlistName:.*}";
	private static final String BREADCRUMBS_ATTR = "breadcrumbs";

	@Resource
	private WishListFacade wishListFacade;

	@Resource
	private ShipmentFacade shipmentFacade;

	@Resource
	private CartFacade cartFacade;

	@Resource(name = "saveCartFacade")
	private SaveCartFacade saveCartFacade;

	@Resource(name = "acceleratorCheckoutFacade")
	private AcceleratorCheckoutFacade checkoutFacade;

	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;


	private static final Logger LOG = Logger.getLogger(WishlistController.class);

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@RequireHardLogIn
	public String getAllWishlists(final Model model) throws CMSItemNotFoundException
	{
		final List<Wishlist2Data> wishlistData = wishListFacade.getAllWishLists();
		model.addAttribute(USER_WISHLIST_DATA, wishlistData);
		final ContentPageModel wishlistAccountPage = getContentPageForLabelOrId(WISHLIST_ACCOUNT_CMS_PAGE);
		storeCmsPageInModel(model, wishlistAccountPage);
		setUpMetaDataForContentPage(model, wishlistAccountPage);
		model.addAttribute(BREADCRUMBS_ATTR, accountBreadcrumbBuilder.getBreadcrumbs("text.account.wishlist.myWishlist"));
		return getViewForPage(model);
	}

	@RequestMapping(value = "/wishlist-detail/" + WISHLIST_DETAIL_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String getDetailsOfWishlist(@PathVariable("wishlistName")
	final String wishlistName, final Model model) throws CMSItemNotFoundException
	{
		final List<Wishlist2EntryData> wishlistEntryData = wishListFacade.getDetailsOfWishlist(wishlistName.trim());
		model.addAttribute(USER_WISHLIST_DATA, wishlistEntryData);
		final ContentPageModel wishlistAccountPage = getContentPageForLabelOrId(WISHLIST_DETAILS_ACCOUNT_CMS_PAGE);
		storeCmsPageInModel(model, wishlistAccountPage);
		setUpMetaDataForContentPage(model, wishlistAccountPage);
		final List<Breadcrumb> breadcrumbs = accountBreadcrumbBuilder.getBreadcrumbs(null);
		breadcrumbs.add(new Breadcrumb("/wishlist/all",
				getMessageSource().getMessage("text.account.wishlist.myWishlist", null, getI18nService().getCurrentLocale()), null));
		breadcrumbs.add(new Breadcrumb("#",
				getMessageSource().getMessage(wishlistName, null, getI18nService().getCurrentLocale()),
				null));
		model.addAttribute(BREADCRUMBS_ATTR, breadcrumbs);
		return getViewForPage(model);
	}

	@ResponseBody
	@RequestMapping(value = "/create-wishlist", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Boolean> createWishlist(@RequestParam("wishlistName")
	final String wishlistName) throws CMSItemNotFoundException
	{
		final Map<String, Boolean> responseBody = new HashMap<>();
		Boolean result = Boolean.FALSE;
		if (StringUtils.isNotBlank(wishlistName) && wishListFacade.createWishlist(wishlistName.trim()))
		{
			result = Boolean.TRUE;
		}
		responseBody.put("result", result);
		return responseBody;
	}

	@ResponseBody
	@RequestMapping(value = "/remove-wishlist/"
			+ WISHLIST_DETAIL_PATH_VARIABLE_PATTERN, method = RequestMethod.GET, produces = "application/json")
	public Map<String, Boolean> removeWishlist(@PathVariable(value = "wishlistName")
	final String wishlistName, final RedirectAttributes redirectAttrs, final Model model) throws CMSItemNotFoundException
	{
		final Map<String, Boolean> responseBody = new HashMap<>();
		Boolean result = Boolean.FALSE;
		if (StringUtils.isNotBlank(wishlistName) && wishListFacade.removeWishlist(wishlistName.trim()))
		{
			result = Boolean.TRUE;
		}
		responseBody.put("result", result);
		return responseBody;
	}

	@ResponseBody
	@RequestMapping(value = "/rename-wishlist", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Boolean> renameWishlist(@RequestParam("wishlistName")
	final String wishlistName, @RequestParam("wishListNewName")
	final String wishlistNewName)
	{
		final Map<String, Boolean> responseBody = new HashMap<>();
		Boolean result = Boolean.FALSE;
		if (StringUtils.isNotBlank(wishlistName) && StringUtils.isNotBlank(wishlistNewName)
				&& wishListFacade.renameWishlist(wishlistName.trim(), wishlistNewName.trim()))
		{
			result = Boolean.TRUE;
		}
		responseBody.put("result", result);
		return responseBody;
	}

	@ResponseBody
	@RequestMapping(value = "/addWishlistentryByName", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Boolean> addWishlistEntryForProduct(@RequestParam("wishlistName")
	final String wishlistName, @RequestParam("productCode")
	final String productCode)
	{
		final Map<String, Boolean> responseBody = new HashMap<>();
		Boolean result = Boolean.FALSE;
		if (StringUtils.isNotBlank(wishlistName) && StringUtils.isNotBlank(productCode)
				&& wishListFacade.addWishlistEntryForProduct(wishlistName, productCode, DEFAULT_DESIRED,
						DEFAULT_WISHLIST_ENTRY_PRIORITY, DEFAULT_WISHLIST_ENTRY_COMMENT))
		{
			result = Boolean.TRUE;

		}
		responseBody.put("result", result);
		return responseBody;
	}

	@ResponseBody
	@RequestMapping(value = "/remove-wishlist-product/", method = RequestMethod.POST)
	public Map<String, Boolean> removeWishlistEntryForProduct(@RequestParam("wishlistName")
	final String wishlistName, @RequestParam("productCode")
	final String productCode) throws CMSItemNotFoundException
	{
		final Map<String, Boolean> responseBody = new HashMap<>();
		Boolean result = Boolean.FALSE;
		if (StringUtils.isNotBlank(wishlistName) && StringUtils.isNotBlank(productCode)
				&& wishListFacade.removeWishlistEntryForProduct(wishlistName, productCode))
		{
			result = Boolean.TRUE;
		}
		responseBody.put("result", result);
		return responseBody;
	}

	@RequestMapping(value = "/product/get", method = RequestMethod.GET, produces = "application/json")
	public String getAllWishlistsForProductWithStatus(@RequestParam("productCode")
	final String productCode, final Model model) throws CMSItemNotFoundException
	{
		if (getUserFacade().isAnonymousUser())
		{
			model.addAttribute("userNotFound", true);
		}
		else
		{
			model.addAttribute("allAvailableWishlist", wishListFacade.getAllWishlistsForProductWithStatus(productCode));
		}

		model.addAttribute("productCode", productCode);
		return ControllerConstants.Views.Fragments.Wishlist.addWishlistPopup;
	}

	@RequestMapping(value = "/entries/add/all/"
			+ WISHLIST_DETAIL_PATH_VARIABLE_PATTERN, method = RequestMethod.POST, produces = "application/json")
	public String addAllEntriesToCart(@PathVariable("wishlistName")
	final String wishlistName, final Model model, final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		final String storeId = shipmentFacade.getCurrentStoreID();
		List<String> listOfUnAffectedProducts = new ArrayList<>();
		if (StringUtils.isBlank(storeId) || StringUtils.isBlank(wishlistName))
		{
			model.addAttribute("error", "some.error.occured");
		}
		else
		{
			listOfUnAffectedProducts = wishListFacade.addAllEntriesToCart(wishlistName.trim(), storeId);
			model.addAttribute("listOfUnAffectedProducts", listOfUnAffectedProducts);
		}
		if (!model.containsAttribute("saveCartForm"))
		{
			model.addAttribute("saveCartForm", new SaveCartForm());
		}
		model.addAttribute("savedCartCount", saveCartFacade.getSavedCartsCountForCurrentUser());
		model.addAttribute("cartData", cartFacade.getSessionCart());
		model.addAttribute("expressCheckoutAllowed", Boolean.valueOf(checkoutFacade.isExpressCheckoutAllowedForCart()));
		return ControllerConstants.Views.Fragments.Wishlist.addAllProductsToWishlistPopup;
	}

	@ResponseBody
	@RequestMapping(value = "/create-wishlist/product/add/", method = RequestMethod.POST)
	public Map<String, Boolean> addProductAfterCreatingWishlist(@RequestParam("productCode")
	final String productCode, @RequestParam("wishlistName")
	final String wishlistName) throws CMSItemNotFoundException
	{
		final Map<String, Boolean> responseBody = new HashMap<>();
		Boolean result = Boolean.FALSE;
		if (StringUtils.isNotBlank(productCode) && StringUtils.isNotBlank(wishlistName)
				&& wishListFacade.createWishlist(wishlistName) && wishListFacade.addWishlistEntryForProduct(wishlistName, productCode,
						DEFAULT_DESIRED, DEFAULT_WISHLIST_ENTRY_PRIORITY, DEFAULT_WISHLIST_ENTRY_COMMENT))
		{
			result = Boolean.TRUE;
		}
		responseBody.put("result", result);
		return responseBody;
	}



}
