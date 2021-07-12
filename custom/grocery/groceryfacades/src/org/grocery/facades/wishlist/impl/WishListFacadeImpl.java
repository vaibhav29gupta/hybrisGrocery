
package org.grocery.facades.wishlist.impl;

import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.selectivecartfacades.data.Wishlist2Data;
import de.hybris.platform.selectivecartfacades.data.Wishlist2EntryData;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.wishlist2.enums.Wishlist2EntryPriority;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.grocery.core.wishlist.service.GroceryWishListService;
import org.grocery.facades.wishlist.WishListFacade;
import org.springframework.context.MessageSource;
import org.springframework.ui.context.Theme;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;



/**
 * @author vaibhavgupta03
 *
 */
public class WishListFacadeImpl implements WishListFacade
{
	private static final Logger LOG = Logger.getLogger(WishListFacadeImpl.class);

	private Converter<Wishlist2Model, Wishlist2Data> wishlistConverter;
	private Converter<Wishlist2EntryModel, Wishlist2EntryData> wishlistEntryConverter;
	private Converter<ProductModel, ProductData> productConverter;
	private GroceryWishListService groceryWishListService;
	private CartFacade cartFacade;
	private ProductService productService;
	private MessageSource messageSource;
	private I18NService i18nService;

	@Override
	public void createDefaultWishlistForNewUser()
	{
		final String localizedWishlistName = getMessageSource().getMessage("text.default.wishlist.name", null,
				getI18nService().getCurrentLocale());
		createWishlist(localizedWishlistName);
	}


	@Override
	public List<Wishlist2Data> getAllWishLists()
	{
		final List<Wishlist2Model> wishlists = getGroceryWishListService().getWishlists();
		final List<Wishlist2Data> wishlistDataList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(wishlists))
		{
			for (final Wishlist2Model wishlist2Model : wishlists)
			{
				final Wishlist2Data wishlistData = getWishlistConverter().convert(wishlist2Model);
				wishlistDataList.add(wishlistData);
			}
		}
		return wishlistDataList;
	}

	@Override
	public List<Wishlist2EntryData> getDetailsOfWishlist(final String wishlistName)
	{
		final Wishlist2Model wishlistModel = getGroceryWishListService().getDetailsOfWishlist(wishlistName);
		final List<Wishlist2EntryData> wishlistEntryList = new ArrayList<>();
		final List<Wishlist2EntryModel> wishlistEntries = wishlistModel.getEntries();
		if (CollectionUtils.isNotEmpty(wishlistEntries))
		{
			for (final Wishlist2EntryModel wishlistEntry : wishlistEntries)
			{
				wishlistEntryList.add(getWishlistEntryConverter().convert(wishlistEntry));
			}
		}
		return wishlistEntryList;
	}

	@Override
	public Map<String, Boolean> getAllWishlistsForProductWithStatus(final String productCode)
	{
		final Map<String, Boolean> wishlistAndProductPresence = new HashMap<>();
		final List<Wishlist2Model> wishlists = getGroceryWishListService().getWishlists();
		final ProductModel product = getProductService().getProductForCode(productCode);
		if (null != product && null != wishlists)
		{
			final List<Wishlist2Data> wishlistDataList = new ArrayList<>();
			if (CollectionUtils.isNotEmpty(wishlists))
			{
				for (final Wishlist2Model wishlist : wishlists)
				{
					try
					{
						if (null != getGroceryWishListService().getWishlistEntryForProduct(product, wishlist))
						{
							wishlistAndProductPresence.put(wishlist.getName(), Boolean.TRUE);
						}
					}
					catch (final UnknownIdentifierException e)
					{
						LOG.debug(e.getMessage());
						wishlistAndProductPresence.put(wishlist.getName(), Boolean.FALSE);
					}
					catch (final AmbiguousIdentifierException e)
					{
						LOG.debug(e.getMessage());
						wishlistAndProductPresence.put(wishlist.getName(), Boolean.FALSE);
					}
				}
			}
		}
		return wishlistAndProductPresence;
	}

	@Override
	public boolean removeWishlist(final String wishistName)
	{
		try
		{
			getGroceryWishListService().removeWishlist(wishistName);
			return Boolean.TRUE;
		}
		catch (final Exception e)
		{
			LOG.debug(e.getStackTrace());
			return Boolean.FALSE;
		}
	}

	@Override
	public boolean addWishlistEntryForProduct(final String wishlistName, final String productCode, final int defaultDesired,
			final Wishlist2EntryPriority defaultWishlistEntryPriority, final String defaultWishlistEntryComment)
	{
		try
		{
			final ProductModel product = getProductService().getProductForCode(productCode);
			if (product != null)
			{
				final Wishlist2Model wishlist = getGroceryWishListService().getDetailsOfWishlist(wishlistName);
				if (null != wishlist)
				{
					getGroceryWishListService().addWishlistEntry(wishlist, product, defaultDesired, defaultWishlistEntryPriority,
							defaultWishlistEntryComment);
					return Boolean.TRUE;
				}
			}
		}
		catch (final Exception e)
		{
			LOG.debug(e.getStackTrace());
			return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean removeWishlistEntryForProduct(final String wishlistName, final String productCode)
	{
		try
		{
			final ProductModel product = getProductService().getProductForCode(productCode);
			final Wishlist2Model wishlist = getGroceryWishListService().getDetailsOfWishlist(wishlistName);
			if (null != product && null != wishlist)
			{
				getGroceryWishListService().removeWishlistEntryForProduct(product, wishlist);
				return Boolean.TRUE;
			}
		}
		catch (final Exception e)
		{
			LOG.debug(e.getStackTrace());
			return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean createWishlist(final String wishlistName)
	{
		if (!isWishlistAlreadyExist(wishlistName))
		{
			try
			{
				groceryWishListService.createWishlist(wishlistName, "");
				return Boolean.TRUE;
			}
			catch (final Exception e)
			{
				LOG.debug(e);
				return Boolean.FALSE;
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean renameWishlist(final String wishlistName, final String wishlistNewName)
	{
		if (!isWishlistAlreadyExist(wishlistNewName))
		{
			try
			{
				getGroceryWishListService().renameWishlist(wishlistName, wishlistNewName);
				return Boolean.TRUE;
			}
			catch (final Exception e)
			{
				LOG.debug(e);
				return Boolean.FALSE;
			}

		}
		return Boolean.FALSE;
	}

	protected boolean isWishlistAlreadyExist(final String wishlistName)
	{
		if (StringUtils.isNotBlank(wishlistName))
		{
			final List<Wishlist2Model> wishlists = getGroceryWishListService().getWishlists();
			if (CollectionUtils.isNotEmpty(wishlists))
			{
				for (final Wishlist2Model wishlist : wishlists)
				{
					if (wishlistName.equalsIgnoreCase(wishlist.getName()))
					{
						return Boolean.TRUE;
					}
				}
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public List<String> addAllEntriesToCart(final String wishlistName, final String storeId)
	{
		final List<String> listOfUnaffectedProductCodes = new ArrayList<>();
		final Wishlist2Model wishlist = getGroceryWishListService().getDetailsOfWishlist(wishlistName);
		if (null != wishlist)
		{
			final List<Wishlist2EntryModel> wishlistEntries = wishlist.getEntries();
			for (final Wishlist2EntryModel entry : wishlistEntries)
			{
				final ProductData product = getProductConverter().convert(entry.getProduct());
				if (product.getIsServiceable())
				{
					addWishlistEntryToCart(product.getCode(), storeId, listOfUnaffectedProductCodes);
				}
				else
				{
					listOfUnaffectedProductCodes.add(product.getCode());
				}
			}
		}
		return CollectionUtils.isNotEmpty(listOfUnaffectedProductCodes) ? listOfUnaffectedProductCodes : Collections.emptyList();
	}

	protected void addWishlistEntryToCart(final String productCode, final String storeId,
			final List<String> listOfUnaffectedProductCodes)
	{
		boolean isProductPresentInCart = Boolean.FALSE;
		if (getCartFacade().hasEntries())
		{
			final CartData cart = getCartFacade().getSessionCart();
			final List<OrderEntryData> cartEntries = cart.getEntries();
			for (final OrderEntryData abstractOrderEntry : cartEntries)
			{
				final ProductData product = abstractOrderEntry.getProduct();
				if (product.getCode().equals(productCode))
				{
					final long quantity = abstractOrderEntry.getQuantity();
					addWishlistEntryToCart(productCode, quantity + 1, storeId, listOfUnaffectedProductCodes);
					isProductPresentInCart = Boolean.TRUE;
					break;
				}
			}
		}
		if (!isProductPresentInCart)
		{
			addWishlistEntryToCart(productCode, 1L, storeId, listOfUnaffectedProductCodes);
		}
	}

	protected void addWishlistEntryToCart(final String productCode, final Long quantity, final String storeID,
			final List<String> listOfUnaffectedProductCodes)
	{
		try
		{
			final CartModificationData cartModification = getCartFacade().addToCart(productCode, quantity, storeID);
			if (cartModification.getStatusCode().equals("maxOrderQuantityExceeded"))
			{
				listOfUnaffectedProductCodes.add(productCode);
			}
		}
		catch (final CommerceCartModificationException e)
		{
			e.printStackTrace();
			listOfUnaffectedProductCodes.add(productCode);
		}
	}

	public CartFacade getCartFacade()
	{
		return cartFacade;
	}

	public void setCartFacade(final CartFacade cartFacade)
	{
		this.cartFacade = cartFacade;
	}

	public GroceryWishListService getGroceryWishListService()
	{
		return groceryWishListService;
	}

	public void setGroceryWishListService(final GroceryWishListService groceryWishListService)
	{
		this.groceryWishListService = groceryWishListService;
	}

	public Converter<Wishlist2Model, Wishlist2Data> getWishlistConverter()
	{
		return wishlistConverter;
	}

	public void setWishlistConverter(final Converter<Wishlist2Model, Wishlist2Data> wishlistConverter)
	{
		this.wishlistConverter = wishlistConverter;
	}

	public Converter<Wishlist2EntryModel, Wishlist2EntryData> getWishlistEntryConverter()
	{
		return wishlistEntryConverter;
	}

	public void setWishlistEntryConverter(final Converter<Wishlist2EntryModel, Wishlist2EntryData> wishlistEntryConverter)
	{
		this.wishlistEntryConverter = wishlistEntryConverter;
	}

	public ProductService getProductService()
	{
		return productService;
	}

	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	public Converter<ProductModel, ProductData> getProductConverter()
	{
		return productConverter;
	}

	public void setProductConverter(final Converter<ProductModel, ProductData> productConverter)
	{
		this.productConverter = productConverter;
	}

	public I18NService getI18nService()
	{
		return i18nService;
	}

	public void setI18nService(final I18NService i18nService)
	{
		this.i18nService = i18nService;
	}

	public MessageSource getMessageSource()
	{
		final ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (requestAttributes != null)
		{
			final HttpServletRequest request = requestAttributes.getRequest();
			final Theme theme = RequestContextUtils.getTheme(request);
			if (theme != null)
			{
				return theme.getMessageSource();
			}
		}

		return null;
	}

	public void setMessageSource(final MessageSource messageSource)
	{
		this.messageSource = messageSource;
	}





















}
