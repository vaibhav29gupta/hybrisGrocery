/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 08-Jul-2020, 11:07:38 PM                    ---
 * ----------------------------------------------------------------
 */
package org.grocery.core.jalo;

import de.hybris.platform.acceleratorcms.constants.AcceleratorCmsConstants;
import de.hybris.platform.acceleratorcms.jalo.components.FooterNavigationComponent;
import de.hybris.platform.acceleratorcms.jalo.components.NavigationComponent;
import de.hybris.platform.acceleratorcms.jalo.components.SimpleBannerComponent;
import de.hybris.platform.basecommerce.jalo.site.BaseSite;
import de.hybris.platform.category.jalo.Category;
import de.hybris.platform.cms2.jalo.contents.CMSItem;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.cms2.jalo.navigation.CMSNavigationNode;
import de.hybris.platform.cms2.jalo.site.CMSSite;
import de.hybris.platform.cms2lib.components.AbstractBannerComponent;
import de.hybris.platform.commerceservices.jalo.process.CustomerRegistrationEmailProcess;
import de.hybris.platform.commerceservices.jalo.process.StoreFrontCustomerProcess;
import de.hybris.platform.commerceservices.jalo.process.StoreFrontProcess;
import de.hybris.platform.commerceservices.jalo.process.UpdatePasswordProcess;
import de.hybris.platform.constants.CoreConstants;
import de.hybris.platform.couponservices.jalo.MultiCodeCoupon;
import de.hybris.platform.customerinterestsservices.jalo.ProductInterest;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LItem;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Country;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.link.Link;
import de.hybris.platform.jalo.media.AbstractMedia;
import de.hybris.platform.jalo.media.Media;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.payment.PaymentMode;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.ordersplitting.jalo.Warehouse;
import de.hybris.platform.promotionengineservices.jalo.PromotionSourceRule;
import de.hybris.platform.store.BaseStore;
import de.hybris.platform.storelocator.jalo.OpeningSchedule;
import de.hybris.platform.storelocator.jalo.PointOfService;
import de.hybris.platform.util.OneToManyHandler;
import de.hybris.platform.util.PartOfHandler;
import de.hybris.platform.util.Utilities;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.grocery.core.constants.GroceryCoreConstants;
import org.grocery.core.jalo.ApparelProduct;
import org.grocery.core.jalo.ApparelSizeVariantProduct;
import org.grocery.core.jalo.ApparelStyleVariantProduct;
import org.grocery.core.jalo.BestSellersProductsComponent;
import org.grocery.core.jalo.CODPaymentInfo;
import org.grocery.core.jalo.CategoryCarouselComponent;
import org.grocery.core.jalo.DeliverySlotDayConfig;
import org.grocery.core.jalo.DeliverySlotInfo;
import org.grocery.core.jalo.DeliverySlotTimeConfig;
import org.grocery.core.jalo.ElectronicsColorVariantProduct;
import org.grocery.core.jalo.FeatureThreshold;
import org.grocery.core.jalo.ImageOverLayComponent;
import org.grocery.core.jalo.Ingredient;
import org.grocery.core.jalo.IngredientsGroup;
import org.grocery.core.jalo.MostPurchasedProductsComponent;
import org.grocery.core.jalo.Recipe;
import org.grocery.core.jalo.RecipeCategoryComponent;
import org.grocery.core.jalo.RecipeHighlightComponent;
import org.grocery.core.jalo.RecipeThemeComponent;
import org.grocery.core.jalo.RecipesCategory;
import org.grocery.core.jalo.ServiceabilityArea;
import org.grocery.core.jalo.ShipmentMethodComponent;
import org.grocery.core.jalo.StrapLineBannerComponent;
import org.grocery.core.jalo.ThresholdAudit;
import org.grocery.core.jalo.restrictions.SocialMediaCustomerRestriction;

/**
 * Generated class for type <code>GroceryCoreManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedGroceryCoreManager extends Extension
{
	/** Relation ordering override parameter constants for StoreDeliverySlotDay from ((grocerycore))*/
	protected static String STOREDELIVERYSLOTDAY_SRC_ORDERED = "relation.StoreDeliverySlotDay.source.ordered";
	protected static String STOREDELIVERYSLOTDAY_TGT_ORDERED = "relation.StoreDeliverySlotDay.target.ordered";
	/** Relation disable markmodifed parameter constants for StoreDeliverySlotDay from ((grocerycore))*/
	protected static String STOREDELIVERYSLOTDAY_MARKMODIFIED = "relation.StoreDeliverySlotDay.markmodified";
	/** Relation ordering override parameter constants for StoreDeliverySlotTime from ((grocerycore))*/
	protected static String STOREDELIVERYSLOTTIME_SRC_ORDERED = "relation.StoreDeliverySlotTime.source.ordered";
	protected static String STOREDELIVERYSLOTTIME_TGT_ORDERED = "relation.StoreDeliverySlotTime.target.ordered";
	/** Relation disable markmodifed parameter constants for StoreDeliverySlotTime from ((grocerycore))*/
	protected static String STOREDELIVERYSLOTTIME_MARKMODIFIED = "relation.StoreDeliverySlotTime.markmodified";
	/**
	* {@link OneToManyHandler} for handling 1:n BOTTOMRIGHTMEDIA's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<Media> FOOTERNAVIGATIONCOMPONENT2RIGHTIMAGERELATIONBOTTOMRIGHTMEDIAHANDLER = new OneToManyHandler<Media>(
	CoreConstants.TC.MEDIA,
	false,
	"subFooterRight",
	null,
	false,
	true,
	CollectionType.SET
	).withRelationQualifier("bottomRightMedia");
	/**
	* {@link OneToManyHandler} for handling 1:n BOTTOMLEFTMEDIA's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<SimpleBannerComponent> FOOTERNAVIGATIONCOMPONENT2LEFTIMAGERELATIONBOTTOMLEFTMEDIAHANDLER = new OneToManyHandler<SimpleBannerComponent>(
	AcceleratorCmsConstants.TC.SIMPLEBANNERCOMPONENT,
	false,
	"subFooterLeft",
	null,
	false,
	true,
	CollectionType.SET
	).withRelationQualifier("bottomLeftMedia");
	/**
	* {@link OneToManyHandler} for handling 1:n LINKMEDIA's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<Media> CATEGORYNAVIGATIONCOMPONENTICONLINKMEDIAHANDLER = new OneToManyHandler<Media>(
	CoreConstants.TC.MEDIA,
	false,
	"linkIcon",
	null,
	false,
	true,
	CollectionType.SET
	).withRelationQualifier("linkMedia");
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("brand", AttributeMode.INITIAL);
		tmp.put("expiry", AttributeMode.INITIAL);
		tmp.put("dietSuitability", AttributeMode.INITIAL);
		tmp.put("weight", AttributeMode.INITIAL);
		tmp.put("ingredients", AttributeMode.INITIAL);
		tmp.put("usageGuidelines", AttributeMode.INITIAL);
		tmp.put("availableForDelivery", AttributeMode.INITIAL);
		tmp.put("availableForPickUp", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.product.Product", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("defaultPOS", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.ordersplitting.jalo.Warehouse", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("isExternal", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.storelocator.jalo.PointOfService", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("isRootCategory", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.category.jalo.Category", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("currency", AttributeMode.INITIAL);
		tmp.put("language", AttributeMode.INITIAL);
		tmp.put("latitude", AttributeMode.INITIAL);
		tmp.put("longitude", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.c2l.Country", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("preferredShipmentMode", AttributeMode.INITIAL);
		tmp.put("preferredPostalCode", AttributeMode.INITIAL);
		tmp.put("preferredPos", AttributeMode.INITIAL);
		tmp.put("socialMediaRegistered", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.user.Customer", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("type", AttributeMode.INITIAL);
		tmp.put("paymentModeImage", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.order.payment.PaymentMode", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("pointOfService", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.customerinterestsservices.jalo.ProductInterest", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("bottomCenterMedia", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.acceleratorcms.jalo.components.FooterNavigationComponent", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("navigationIcon", AttributeMode.INITIAL);
		tmp.put("sideBanner", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.cms2.jalo.navigation.CMSNavigationNode", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("enableGoogleSignUp", AttributeMode.INITIAL);
		tmp.put("enableFacebookSignup", AttributeMode.INITIAL);
		tmp.put("enableOTPSignup", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.cms2.jalo.site.CMSSite", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("generatedCouponCode", AttributeMode.INITIAL);
		tmp.put("promotionMessage", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.commerceservices.jalo.process.StoreFrontCustomerProcess", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("openingSchedule", AttributeMode.INITIAL);
		tmp.put("defaultcountry", AttributeMode.INITIAL);
		tmp.put("registrationCouponEnabled", AttributeMode.INITIAL);
		tmp.put("registrationCoupon", AttributeMode.INITIAL);
		tmp.put("registrationPromotion", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.store.BaseStore", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("deliverySlotInfo", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.order.AbstractOrder", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("subFooterRight", AttributeMode.INITIAL);
		tmp.put("linkIcon", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.media.Media", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("subFooterLeft", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.acceleratorcms.jalo.components.SimpleBannerComponent", Collections.unmodifiableMap(tmp));
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.availableForDelivery</code> attribute.
	 * @return the availableForDelivery
	 */
	public Boolean isAvailableForDelivery(final SessionContext ctx, final Product item)
	{
		return (Boolean)item.getProperty( ctx, GroceryCoreConstants.Attributes.Product.AVAILABLEFORDELIVERY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.availableForDelivery</code> attribute.
	 * @return the availableForDelivery
	 */
	public Boolean isAvailableForDelivery(final Product item)
	{
		return isAvailableForDelivery( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.availableForDelivery</code> attribute. 
	 * @return the availableForDelivery
	 */
	public boolean isAvailableForDeliveryAsPrimitive(final SessionContext ctx, final Product item)
	{
		Boolean value = isAvailableForDelivery( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.availableForDelivery</code> attribute. 
	 * @return the availableForDelivery
	 */
	public boolean isAvailableForDeliveryAsPrimitive(final Product item)
	{
		return isAvailableForDeliveryAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.availableForDelivery</code> attribute. 
	 * @param value the availableForDelivery
	 */
	public void setAvailableForDelivery(final SessionContext ctx, final Product item, final Boolean value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Product.AVAILABLEFORDELIVERY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.availableForDelivery</code> attribute. 
	 * @param value the availableForDelivery
	 */
	public void setAvailableForDelivery(final Product item, final Boolean value)
	{
		setAvailableForDelivery( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.availableForDelivery</code> attribute. 
	 * @param value the availableForDelivery
	 */
	public void setAvailableForDelivery(final SessionContext ctx, final Product item, final boolean value)
	{
		setAvailableForDelivery( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.availableForDelivery</code> attribute. 
	 * @param value the availableForDelivery
	 */
	public void setAvailableForDelivery(final Product item, final boolean value)
	{
		setAvailableForDelivery( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.availableForPickUp</code> attribute.
	 * @return the availableForPickUp
	 */
	public Boolean isAvailableForPickUp(final SessionContext ctx, final Product item)
	{
		return (Boolean)item.getProperty( ctx, GroceryCoreConstants.Attributes.Product.AVAILABLEFORPICKUP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.availableForPickUp</code> attribute.
	 * @return the availableForPickUp
	 */
	public Boolean isAvailableForPickUp(final Product item)
	{
		return isAvailableForPickUp( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.availableForPickUp</code> attribute. 
	 * @return the availableForPickUp
	 */
	public boolean isAvailableForPickUpAsPrimitive(final SessionContext ctx, final Product item)
	{
		Boolean value = isAvailableForPickUp( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.availableForPickUp</code> attribute. 
	 * @return the availableForPickUp
	 */
	public boolean isAvailableForPickUpAsPrimitive(final Product item)
	{
		return isAvailableForPickUpAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.availableForPickUp</code> attribute. 
	 * @param value the availableForPickUp
	 */
	public void setAvailableForPickUp(final SessionContext ctx, final Product item, final Boolean value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Product.AVAILABLEFORPICKUP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.availableForPickUp</code> attribute. 
	 * @param value the availableForPickUp
	 */
	public void setAvailableForPickUp(final Product item, final Boolean value)
	{
		setAvailableForPickUp( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.availableForPickUp</code> attribute. 
	 * @param value the availableForPickUp
	 */
	public void setAvailableForPickUp(final SessionContext ctx, final Product item, final boolean value)
	{
		setAvailableForPickUp( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.availableForPickUp</code> attribute. 
	 * @param value the availableForPickUp
	 */
	public void setAvailableForPickUp(final Product item, final boolean value)
	{
		setAvailableForPickUp( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FooterNavigationComponent.bottomCenterMedia</code> attribute.
	 * @return the bottomCenterMedia - Media for footer.
	 */
	public Media getBottomCenterMedia(final SessionContext ctx, final FooterNavigationComponent item)
	{
		return (Media)item.getProperty( ctx, GroceryCoreConstants.Attributes.FooterNavigationComponent.BOTTOMCENTERMEDIA);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FooterNavigationComponent.bottomCenterMedia</code> attribute.
	 * @return the bottomCenterMedia - Media for footer.
	 */
	public Media getBottomCenterMedia(final FooterNavigationComponent item)
	{
		return getBottomCenterMedia( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FooterNavigationComponent.bottomCenterMedia</code> attribute. 
	 * @param value the bottomCenterMedia - Media for footer.
	 */
	public void setBottomCenterMedia(final SessionContext ctx, final FooterNavigationComponent item, final Media value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.FooterNavigationComponent.BOTTOMCENTERMEDIA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FooterNavigationComponent.bottomCenterMedia</code> attribute. 
	 * @param value the bottomCenterMedia - Media for footer.
	 */
	public void setBottomCenterMedia(final FooterNavigationComponent item, final Media value)
	{
		setBottomCenterMedia( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FooterNavigationComponent.bottomLeftMedia</code> attribute.
	 * @return the bottomLeftMedia
	 */
	public Set<SimpleBannerComponent> getBottomLeftMedia(final SessionContext ctx, final FooterNavigationComponent item)
	{
		return (Set<SimpleBannerComponent>)FOOTERNAVIGATIONCOMPONENT2LEFTIMAGERELATIONBOTTOMLEFTMEDIAHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FooterNavigationComponent.bottomLeftMedia</code> attribute.
	 * @return the bottomLeftMedia
	 */
	public Set<SimpleBannerComponent> getBottomLeftMedia(final FooterNavigationComponent item)
	{
		return getBottomLeftMedia( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FooterNavigationComponent.bottomLeftMedia</code> attribute. 
	 * @param value the bottomLeftMedia
	 */
	public void setBottomLeftMedia(final SessionContext ctx, final FooterNavigationComponent item, final Set<SimpleBannerComponent> value)
	{
		FOOTERNAVIGATIONCOMPONENT2LEFTIMAGERELATIONBOTTOMLEFTMEDIAHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FooterNavigationComponent.bottomLeftMedia</code> attribute. 
	 * @param value the bottomLeftMedia
	 */
	public void setBottomLeftMedia(final FooterNavigationComponent item, final Set<SimpleBannerComponent> value)
	{
		setBottomLeftMedia( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to bottomLeftMedia. 
	 * @param value the item to add to bottomLeftMedia
	 */
	public void addToBottomLeftMedia(final SessionContext ctx, final FooterNavigationComponent item, final SimpleBannerComponent value)
	{
		FOOTERNAVIGATIONCOMPONENT2LEFTIMAGERELATIONBOTTOMLEFTMEDIAHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to bottomLeftMedia. 
	 * @param value the item to add to bottomLeftMedia
	 */
	public void addToBottomLeftMedia(final FooterNavigationComponent item, final SimpleBannerComponent value)
	{
		addToBottomLeftMedia( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from bottomLeftMedia. 
	 * @param value the item to remove from bottomLeftMedia
	 */
	public void removeFromBottomLeftMedia(final SessionContext ctx, final FooterNavigationComponent item, final SimpleBannerComponent value)
	{
		FOOTERNAVIGATIONCOMPONENT2LEFTIMAGERELATIONBOTTOMLEFTMEDIAHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from bottomLeftMedia. 
	 * @param value the item to remove from bottomLeftMedia
	 */
	public void removeFromBottomLeftMedia(final FooterNavigationComponent item, final SimpleBannerComponent value)
	{
		removeFromBottomLeftMedia( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FooterNavigationComponent.bottomRightMedia</code> attribute.
	 * @return the bottomRightMedia
	 */
	public Set<Media> getBottomRightMedia(final SessionContext ctx, final FooterNavigationComponent item)
	{
		return (Set<Media>)FOOTERNAVIGATIONCOMPONENT2RIGHTIMAGERELATIONBOTTOMRIGHTMEDIAHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FooterNavigationComponent.bottomRightMedia</code> attribute.
	 * @return the bottomRightMedia
	 */
	public Set<Media> getBottomRightMedia(final FooterNavigationComponent item)
	{
		return getBottomRightMedia( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FooterNavigationComponent.bottomRightMedia</code> attribute. 
	 * @param value the bottomRightMedia
	 */
	public void setBottomRightMedia(final SessionContext ctx, final FooterNavigationComponent item, final Set<Media> value)
	{
		FOOTERNAVIGATIONCOMPONENT2RIGHTIMAGERELATIONBOTTOMRIGHTMEDIAHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FooterNavigationComponent.bottomRightMedia</code> attribute. 
	 * @param value the bottomRightMedia
	 */
	public void setBottomRightMedia(final FooterNavigationComponent item, final Set<Media> value)
	{
		setBottomRightMedia( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to bottomRightMedia. 
	 * @param value the item to add to bottomRightMedia
	 */
	public void addToBottomRightMedia(final SessionContext ctx, final FooterNavigationComponent item, final Media value)
	{
		FOOTERNAVIGATIONCOMPONENT2RIGHTIMAGERELATIONBOTTOMRIGHTMEDIAHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to bottomRightMedia. 
	 * @param value the item to add to bottomRightMedia
	 */
	public void addToBottomRightMedia(final FooterNavigationComponent item, final Media value)
	{
		addToBottomRightMedia( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from bottomRightMedia. 
	 * @param value the item to remove from bottomRightMedia
	 */
	public void removeFromBottomRightMedia(final SessionContext ctx, final FooterNavigationComponent item, final Media value)
	{
		FOOTERNAVIGATIONCOMPONENT2RIGHTIMAGERELATIONBOTTOMRIGHTMEDIAHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from bottomRightMedia. 
	 * @param value the item to remove from bottomRightMedia
	 */
	public void removeFromBottomRightMedia(final FooterNavigationComponent item, final Media value)
	{
		removeFromBottomRightMedia( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.brand</code> attribute.
	 * @return the brand - Brand Name for the Product
	 */
	public String getBrand(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, GroceryCoreConstants.Attributes.Product.BRAND);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.brand</code> attribute.
	 * @return the brand - Brand Name for the Product
	 */
	public String getBrand(final Product item)
	{
		return getBrand( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.brand</code> attribute. 
	 * @param value the brand - Brand Name for the Product
	 */
	public void setBrand(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Product.BRAND,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.brand</code> attribute. 
	 * @param value the brand - Brand Name for the Product
	 */
	public void setBrand(final Product item, final String value)
	{
		setBrand( getSession().getSessionContext(), item, value );
	}
	
	public ApparelProduct createApparelProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.APPARELPRODUCT );
			return (ApparelProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ApparelProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ApparelProduct createApparelProduct(final Map attributeValues)
	{
		return createApparelProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public ApparelSizeVariantProduct createApparelSizeVariantProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.APPARELSIZEVARIANTPRODUCT );
			return (ApparelSizeVariantProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ApparelSizeVariantProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ApparelSizeVariantProduct createApparelSizeVariantProduct(final Map attributeValues)
	{
		return createApparelSizeVariantProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public ApparelStyleVariantProduct createApparelStyleVariantProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.APPARELSTYLEVARIANTPRODUCT );
			return (ApparelStyleVariantProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ApparelStyleVariantProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ApparelStyleVariantProduct createApparelStyleVariantProduct(final Map attributeValues)
	{
		return createApparelStyleVariantProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public BestSellersProductsComponent createBestSellersProductsComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.BESTSELLERSPRODUCTSCOMPONENT );
			return (BestSellersProductsComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating BestSellersProductsComponent : "+e.getMessage(), 0 );
		}
	}
	
	public BestSellersProductsComponent createBestSellersProductsComponent(final Map attributeValues)
	{
		return createBestSellersProductsComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public CategoryCarouselComponent createCategoryCarouselComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.CATEGORYCAROUSELCOMPONENT );
			return (CategoryCarouselComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating CategoryCarouselComponent : "+e.getMessage(), 0 );
		}
	}
	
	public CategoryCarouselComponent createCategoryCarouselComponent(final Map attributeValues)
	{
		return createCategoryCarouselComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public CODPaymentInfo createCODPaymentInfo(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.CODPAYMENTINFO );
			return (CODPaymentInfo)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating CODPaymentInfo : "+e.getMessage(), 0 );
		}
	}
	
	public CODPaymentInfo createCODPaymentInfo(final Map attributeValues)
	{
		return createCODPaymentInfo( getSession().getSessionContext(), attributeValues );
	}
	
	public CustomerRegistrationEmailProcess createCustomerRegistrationEmailProcess(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.CUSTOMERREGISTRATIONEMAILPROCESS );
			return (CustomerRegistrationEmailProcess)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating CustomerRegistrationEmailProcess : "+e.getMessage(), 0 );
		}
	}
	
	public CustomerRegistrationEmailProcess createCustomerRegistrationEmailProcess(final Map attributeValues)
	{
		return createCustomerRegistrationEmailProcess( getSession().getSessionContext(), attributeValues );
	}
	
	public DeliverySlotDayConfig createDeliverySlotDayConfig(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.DELIVERYSLOTDAYCONFIG );
			return (DeliverySlotDayConfig)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating DeliverySlotDayConfig : "+e.getMessage(), 0 );
		}
	}
	
	public DeliverySlotDayConfig createDeliverySlotDayConfig(final Map attributeValues)
	{
		return createDeliverySlotDayConfig( getSession().getSessionContext(), attributeValues );
	}
	
	public DeliverySlotInfo createDeliverySlotInfo(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.DELIVERYSLOTINFO );
			return (DeliverySlotInfo)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating DeliverySlotInfo : "+e.getMessage(), 0 );
		}
	}
	
	public DeliverySlotInfo createDeliverySlotInfo(final Map attributeValues)
	{
		return createDeliverySlotInfo( getSession().getSessionContext(), attributeValues );
	}
	
	public DeliverySlotTimeConfig createDeliverySlotTimeConfig(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.DELIVERYSLOTTIMECONFIG );
			return (DeliverySlotTimeConfig)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating DeliverySlotTimeConfig : "+e.getMessage(), 0 );
		}
	}
	
	public DeliverySlotTimeConfig createDeliverySlotTimeConfig(final Map attributeValues)
	{
		return createDeliverySlotTimeConfig( getSession().getSessionContext(), attributeValues );
	}
	
	public ElectronicsColorVariantProduct createElectronicsColorVariantProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.ELECTRONICSCOLORVARIANTPRODUCT );
			return (ElectronicsColorVariantProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ElectronicsColorVariantProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ElectronicsColorVariantProduct createElectronicsColorVariantProduct(final Map attributeValues)
	{
		return createElectronicsColorVariantProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public FeatureThreshold createFeatureThreshold(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.FEATURETHRESHOLD );
			return (FeatureThreshold)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating FeatureThreshold : "+e.getMessage(), 0 );
		}
	}
	
	public FeatureThreshold createFeatureThreshold(final Map attributeValues)
	{
		return createFeatureThreshold( getSession().getSessionContext(), attributeValues );
	}
	
	public ImageOverLayComponent createImageOverLayComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.IMAGEOVERLAYCOMPONENT );
			return (ImageOverLayComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ImageOverLayComponent : "+e.getMessage(), 0 );
		}
	}
	
	public ImageOverLayComponent createImageOverLayComponent(final Map attributeValues)
	{
		return createImageOverLayComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public Ingredient createIngredient(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.INGREDIENT );
			return (Ingredient)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating Ingredient : "+e.getMessage(), 0 );
		}
	}
	
	public Ingredient createIngredient(final Map attributeValues)
	{
		return createIngredient( getSession().getSessionContext(), attributeValues );
	}
	
	public IngredientsGroup createIngredientsGroup(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.INGREDIENTSGROUP );
			return (IngredientsGroup)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating IngredientsGroup : "+e.getMessage(), 0 );
		}
	}
	
	public IngredientsGroup createIngredientsGroup(final Map attributeValues)
	{
		return createIngredientsGroup( getSession().getSessionContext(), attributeValues );
	}
	
	public MostPurchasedProductsComponent createMostPurchasedProductsComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.MOSTPURCHASEDPRODUCTSCOMPONENT );
			return (MostPurchasedProductsComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating MostPurchasedProductsComponent : "+e.getMessage(), 0 );
		}
	}
	
	public MostPurchasedProductsComponent createMostPurchasedProductsComponent(final Map attributeValues)
	{
		return createMostPurchasedProductsComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public Recipe createRecipe(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.RECIPE );
			return (Recipe)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating Recipe : "+e.getMessage(), 0 );
		}
	}
	
	public Recipe createRecipe(final Map attributeValues)
	{
		return createRecipe( getSession().getSessionContext(), attributeValues );
	}
	
	public RecipeCategoryComponent createRecipeCategoryComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.RECIPECATEGORYCOMPONENT );
			return (RecipeCategoryComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating RecipeCategoryComponent : "+e.getMessage(), 0 );
		}
	}
	
	public RecipeCategoryComponent createRecipeCategoryComponent(final Map attributeValues)
	{
		return createRecipeCategoryComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public RecipeHighlightComponent createRecipeHighlightComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.RECIPEHIGHLIGHTCOMPONENT );
			return (RecipeHighlightComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating RecipeHighlightComponent : "+e.getMessage(), 0 );
		}
	}
	
	public RecipeHighlightComponent createRecipeHighlightComponent(final Map attributeValues)
	{
		return createRecipeHighlightComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public RecipesCategory createRecipesCategory(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.RECIPESCATEGORY );
			return (RecipesCategory)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating RecipesCategory : "+e.getMessage(), 0 );
		}
	}
	
	public RecipesCategory createRecipesCategory(final Map attributeValues)
	{
		return createRecipesCategory( getSession().getSessionContext(), attributeValues );
	}
	
	public RecipeThemeComponent createRecipeThemeComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.RECIPETHEMECOMPONENT );
			return (RecipeThemeComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating RecipeThemeComponent : "+e.getMessage(), 0 );
		}
	}
	
	public RecipeThemeComponent createRecipeThemeComponent(final Map attributeValues)
	{
		return createRecipeThemeComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public ServiceabilityArea createServiceabilityArea(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.SERVICEABILITYAREA );
			return (ServiceabilityArea)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ServiceabilityArea : "+e.getMessage(), 0 );
		}
	}
	
	public ServiceabilityArea createServiceabilityArea(final Map attributeValues)
	{
		return createServiceabilityArea( getSession().getSessionContext(), attributeValues );
	}
	
	public ShipmentMethodComponent createShipmentMethodComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.SHIPMENTMETHODCOMPONENT );
			return (ShipmentMethodComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ShipmentMethodComponent : "+e.getMessage(), 0 );
		}
	}
	
	public ShipmentMethodComponent createShipmentMethodComponent(final Map attributeValues)
	{
		return createShipmentMethodComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public SocialMediaCustomerRestriction createSocialMediaCustomerRestriction(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.SOCIALMEDIACUSTOMERRESTRICTION );
			return (SocialMediaCustomerRestriction)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating SocialMediaCustomerRestriction : "+e.getMessage(), 0 );
		}
	}
	
	public SocialMediaCustomerRestriction createSocialMediaCustomerRestriction(final Map attributeValues)
	{
		return createSocialMediaCustomerRestriction( getSession().getSessionContext(), attributeValues );
	}
	
	public StrapLineBannerComponent createStrapLineBannerComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.STRAPLINEBANNERCOMPONENT );
			return (StrapLineBannerComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating StrapLineBannerComponent : "+e.getMessage(), 0 );
		}
	}
	
	public StrapLineBannerComponent createStrapLineBannerComponent(final Map attributeValues)
	{
		return createStrapLineBannerComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public ThresholdAudit createThresholdAudit(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.THRESHOLDAUDIT );
			return (ThresholdAudit)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ThresholdAudit : "+e.getMessage(), 0 );
		}
	}
	
	public ThresholdAudit createThresholdAudit(final Map attributeValues)
	{
		return createThresholdAudit( getSession().getSessionContext(), attributeValues );
	}
	
	public UpdatePasswordProcess createUpdatePasswordEmailProcess(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( GroceryCoreConstants.TC.UPDATEPASSWORDEMAILPROCESS );
			return (UpdatePasswordProcess)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating UpdatePasswordEmailProcess : "+e.getMessage(), 0 );
		}
	}
	
	public UpdatePasswordProcess createUpdatePasswordEmailProcess(final Map attributeValues)
	{
		return createUpdatePasswordEmailProcess( getSession().getSessionContext(), attributeValues );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Country.currency</code> attribute.
	 * @return the currency
	 */
	public Currency getCurrency(final SessionContext ctx, final Country item)
	{
		return (Currency)item.getProperty( ctx, GroceryCoreConstants.Attributes.Country.CURRENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Country.currency</code> attribute.
	 * @return the currency
	 */
	public Currency getCurrency(final Country item)
	{
		return getCurrency( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Country.currency</code> attribute. 
	 * @param value the currency
	 */
	public void setCurrency(final SessionContext ctx, final Country item, final Currency value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Country.CURRENCY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Country.currency</code> attribute. 
	 * @param value the currency
	 */
	public void setCurrency(final Country item, final Currency value)
	{
		setCurrency( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.defaultcountry</code> attribute.
	 * @return the defaultcountry
	 */
	public Country getDefaultcountry(final SessionContext ctx, final BaseStore item)
	{
		return (Country)item.getProperty( ctx, GroceryCoreConstants.Attributes.BaseStore.DEFAULTCOUNTRY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.defaultcountry</code> attribute.
	 * @return the defaultcountry
	 */
	public Country getDefaultcountry(final BaseStore item)
	{
		return getDefaultcountry( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.defaultcountry</code> attribute. 
	 * @param value the defaultcountry
	 */
	public void setDefaultcountry(final SessionContext ctx, final BaseStore item, final Country value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.BaseStore.DEFAULTCOUNTRY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.defaultcountry</code> attribute. 
	 * @param value the defaultcountry
	 */
	public void setDefaultcountry(final BaseStore item, final Country value)
	{
		setDefaultcountry( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Warehouse.defaultPOS</code> attribute.
	 * @return the defaultPOS - Default Point of service
	 */
	public PointOfService getDefaultPOS(final SessionContext ctx, final Warehouse item)
	{
		return (PointOfService)item.getProperty( ctx, GroceryCoreConstants.Attributes.Warehouse.DEFAULTPOS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Warehouse.defaultPOS</code> attribute.
	 * @return the defaultPOS - Default Point of service
	 */
	public PointOfService getDefaultPOS(final Warehouse item)
	{
		return getDefaultPOS( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Warehouse.defaultPOS</code> attribute. 
	 * @param value the defaultPOS - Default Point of service
	 */
	public void setDefaultPOS(final SessionContext ctx, final Warehouse item, final PointOfService value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Warehouse.DEFAULTPOS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Warehouse.defaultPOS</code> attribute. 
	 * @param value the defaultPOS - Default Point of service
	 */
	public void setDefaultPOS(final Warehouse item, final PointOfService value)
	{
		setDefaultPOS( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PointOfService.deliveryDaySlots</code> attribute.
	 * @return the deliveryDaySlots
	 */
	public List<DeliverySlotDayConfig> getDeliveryDaySlots(final SessionContext ctx, final PointOfService item)
	{
		final List<DeliverySlotDayConfig> items = item.getLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTDAY,
			"DeliverySlotDayConfig",
			null,
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTDAY_SRC_ORDERED, true),
			false
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PointOfService.deliveryDaySlots</code> attribute.
	 * @return the deliveryDaySlots
	 */
	public List<DeliverySlotDayConfig> getDeliveryDaySlots(final PointOfService item)
	{
		return getDeliveryDaySlots( getSession().getSessionContext(), item );
	}
	
	public long getDeliveryDaySlotsCount(final SessionContext ctx, final PointOfService item)
	{
		return item.getLinkedItemsCount(
			ctx,
			true,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTDAY,
			"DeliverySlotDayConfig",
			null
		);
	}
	
	public long getDeliveryDaySlotsCount(final PointOfService item)
	{
		return getDeliveryDaySlotsCount( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PointOfService.deliveryDaySlots</code> attribute. 
	 * @param value the deliveryDaySlots
	 */
	public void setDeliveryDaySlots(final SessionContext ctx, final PointOfService item, final List<DeliverySlotDayConfig> value)
	{
		item.setLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTDAY,
			null,
			value,
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTDAY_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(STOREDELIVERYSLOTDAY_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PointOfService.deliveryDaySlots</code> attribute. 
	 * @param value the deliveryDaySlots
	 */
	public void setDeliveryDaySlots(final PointOfService item, final List<DeliverySlotDayConfig> value)
	{
		setDeliveryDaySlots( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to deliveryDaySlots. 
	 * @param value the item to add to deliveryDaySlots
	 */
	public void addToDeliveryDaySlots(final SessionContext ctx, final PointOfService item, final DeliverySlotDayConfig value)
	{
		item.addLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTDAY,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTDAY_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(STOREDELIVERYSLOTDAY_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to deliveryDaySlots. 
	 * @param value the item to add to deliveryDaySlots
	 */
	public void addToDeliveryDaySlots(final PointOfService item, final DeliverySlotDayConfig value)
	{
		addToDeliveryDaySlots( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from deliveryDaySlots. 
	 * @param value the item to remove from deliveryDaySlots
	 */
	public void removeFromDeliveryDaySlots(final SessionContext ctx, final PointOfService item, final DeliverySlotDayConfig value)
	{
		item.removeLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTDAY,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTDAY_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(STOREDELIVERYSLOTDAY_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from deliveryDaySlots. 
	 * @param value the item to remove from deliveryDaySlots
	 */
	public void removeFromDeliveryDaySlots(final PointOfService item, final DeliverySlotDayConfig value)
	{
		removeFromDeliveryDaySlots( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.deliverySlotInfo</code> attribute.
	 * @return the deliverySlotInfo - Delivery Slot Selected
	 */
	public DeliverySlotInfo getDeliverySlotInfo(final SessionContext ctx, final AbstractOrder item)
	{
		return (DeliverySlotInfo)item.getProperty( ctx, GroceryCoreConstants.Attributes.AbstractOrder.DELIVERYSLOTINFO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.deliverySlotInfo</code> attribute.
	 * @return the deliverySlotInfo - Delivery Slot Selected
	 */
	public DeliverySlotInfo getDeliverySlotInfo(final AbstractOrder item)
	{
		return getDeliverySlotInfo( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.deliverySlotInfo</code> attribute. 
	 * @param value the deliverySlotInfo - Delivery Slot Selected
	 */
	public void setDeliverySlotInfo(final SessionContext ctx, final AbstractOrder item, final DeliverySlotInfo value)
	{
		new PartOfHandler<DeliverySlotInfo>()
		{
			@Override
			protected DeliverySlotInfo doGetValue(final SessionContext ctx)
			{
				return getDeliverySlotInfo( ctx, item );
			}
			@Override
			protected void doSetValue(final SessionContext ctx, final DeliverySlotInfo _value)
			{
				final DeliverySlotInfo value = _value;
				item.setProperty(ctx, GroceryCoreConstants.Attributes.AbstractOrder.DELIVERYSLOTINFO,value);
			}
		}.setValue( ctx, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.deliverySlotInfo</code> attribute. 
	 * @param value the deliverySlotInfo - Delivery Slot Selected
	 */
	public void setDeliverySlotInfo(final AbstractOrder item, final DeliverySlotInfo value)
	{
		setDeliverySlotInfo( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PointOfService.deliveryTimeSlots</code> attribute.
	 * @return the deliveryTimeSlots
	 */
	public List<DeliverySlotTimeConfig> getDeliveryTimeSlots(final SessionContext ctx, final PointOfService item)
	{
		final List<DeliverySlotTimeConfig> items = item.getLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTTIME,
			"DeliverySlotTimeConfig",
			null,
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTTIME_SRC_ORDERED, true),
			false
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PointOfService.deliveryTimeSlots</code> attribute.
	 * @return the deliveryTimeSlots
	 */
	public List<DeliverySlotTimeConfig> getDeliveryTimeSlots(final PointOfService item)
	{
		return getDeliveryTimeSlots( getSession().getSessionContext(), item );
	}
	
	public long getDeliveryTimeSlotsCount(final SessionContext ctx, final PointOfService item)
	{
		return item.getLinkedItemsCount(
			ctx,
			true,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTTIME,
			"DeliverySlotTimeConfig",
			null
		);
	}
	
	public long getDeliveryTimeSlotsCount(final PointOfService item)
	{
		return getDeliveryTimeSlotsCount( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PointOfService.deliveryTimeSlots</code> attribute. 
	 * @param value the deliveryTimeSlots
	 */
	public void setDeliveryTimeSlots(final SessionContext ctx, final PointOfService item, final List<DeliverySlotTimeConfig> value)
	{
		item.setLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTTIME,
			null,
			value,
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTTIME_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(STOREDELIVERYSLOTTIME_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PointOfService.deliveryTimeSlots</code> attribute. 
	 * @param value the deliveryTimeSlots
	 */
	public void setDeliveryTimeSlots(final PointOfService item, final List<DeliverySlotTimeConfig> value)
	{
		setDeliveryTimeSlots( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to deliveryTimeSlots. 
	 * @param value the item to add to deliveryTimeSlots
	 */
	public void addToDeliveryTimeSlots(final SessionContext ctx, final PointOfService item, final DeliverySlotTimeConfig value)
	{
		item.addLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTTIME,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTTIME_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(STOREDELIVERYSLOTTIME_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to deliveryTimeSlots. 
	 * @param value the item to add to deliveryTimeSlots
	 */
	public void addToDeliveryTimeSlots(final PointOfService item, final DeliverySlotTimeConfig value)
	{
		addToDeliveryTimeSlots( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from deliveryTimeSlots. 
	 * @param value the item to remove from deliveryTimeSlots
	 */
	public void removeFromDeliveryTimeSlots(final SessionContext ctx, final PointOfService item, final DeliverySlotTimeConfig value)
	{
		item.removeLinkedItems( 
			ctx,
			true,
			GroceryCoreConstants.Relations.STOREDELIVERYSLOTTIME,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(STOREDELIVERYSLOTTIME_SRC_ORDERED, true),
			false,
			Utilities.getMarkModifiedOverride(STOREDELIVERYSLOTTIME_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from deliveryTimeSlots. 
	 * @param value the item to remove from deliveryTimeSlots
	 */
	public void removeFromDeliveryTimeSlots(final PointOfService item, final DeliverySlotTimeConfig value)
	{
		removeFromDeliveryTimeSlots( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.dietSuitability</code> attribute.
	 * @return the dietSuitability
	 */
	public EnumerationValue getDietSuitability(final SessionContext ctx, final Product item)
	{
		return (EnumerationValue)item.getProperty( ctx, GroceryCoreConstants.Attributes.Product.DIETSUITABILITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.dietSuitability</code> attribute.
	 * @return the dietSuitability
	 */
	public EnumerationValue getDietSuitability(final Product item)
	{
		return getDietSuitability( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.dietSuitability</code> attribute. 
	 * @param value the dietSuitability
	 */
	public void setDietSuitability(final SessionContext ctx, final Product item, final EnumerationValue value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Product.DIETSUITABILITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.dietSuitability</code> attribute. 
	 * @param value the dietSuitability
	 */
	public void setDietSuitability(final Product item, final EnumerationValue value)
	{
		setDietSuitability( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.enableFacebookSignup</code> attribute.
	 * @return the enableFacebookSignup - It is property representing a facebook login is enable or not
	 */
	public Boolean isEnableFacebookSignup(final SessionContext ctx, final CMSSite item)
	{
		return (Boolean)item.getProperty( ctx, GroceryCoreConstants.Attributes.CMSSite.ENABLEFACEBOOKSIGNUP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.enableFacebookSignup</code> attribute.
	 * @return the enableFacebookSignup - It is property representing a facebook login is enable or not
	 */
	public Boolean isEnableFacebookSignup(final CMSSite item)
	{
		return isEnableFacebookSignup( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.enableFacebookSignup</code> attribute. 
	 * @return the enableFacebookSignup - It is property representing a facebook login is enable or not
	 */
	public boolean isEnableFacebookSignupAsPrimitive(final SessionContext ctx, final CMSSite item)
	{
		Boolean value = isEnableFacebookSignup( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.enableFacebookSignup</code> attribute. 
	 * @return the enableFacebookSignup - It is property representing a facebook login is enable or not
	 */
	public boolean isEnableFacebookSignupAsPrimitive(final CMSSite item)
	{
		return isEnableFacebookSignupAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.enableFacebookSignup</code> attribute. 
	 * @param value the enableFacebookSignup - It is property representing a facebook login is enable or not
	 */
	public void setEnableFacebookSignup(final SessionContext ctx, final CMSSite item, final Boolean value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.CMSSite.ENABLEFACEBOOKSIGNUP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.enableFacebookSignup</code> attribute. 
	 * @param value the enableFacebookSignup - It is property representing a facebook login is enable or not
	 */
	public void setEnableFacebookSignup(final CMSSite item, final Boolean value)
	{
		setEnableFacebookSignup( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.enableFacebookSignup</code> attribute. 
	 * @param value the enableFacebookSignup - It is property representing a facebook login is enable or not
	 */
	public void setEnableFacebookSignup(final SessionContext ctx, final CMSSite item, final boolean value)
	{
		setEnableFacebookSignup( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.enableFacebookSignup</code> attribute. 
	 * @param value the enableFacebookSignup - It is property representing a facebook login is enable or not
	 */
	public void setEnableFacebookSignup(final CMSSite item, final boolean value)
	{
		setEnableFacebookSignup( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.enableGoogleSignUp</code> attribute.
	 * @return the enableGoogleSignUp - It is property representing a google login is enable or not
	 */
	public Boolean isEnableGoogleSignUp(final SessionContext ctx, final CMSSite item)
	{
		return (Boolean)item.getProperty( ctx, GroceryCoreConstants.Attributes.CMSSite.ENABLEGOOGLESIGNUP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.enableGoogleSignUp</code> attribute.
	 * @return the enableGoogleSignUp - It is property representing a google login is enable or not
	 */
	public Boolean isEnableGoogleSignUp(final CMSSite item)
	{
		return isEnableGoogleSignUp( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.enableGoogleSignUp</code> attribute. 
	 * @return the enableGoogleSignUp - It is property representing a google login is enable or not
	 */
	public boolean isEnableGoogleSignUpAsPrimitive(final SessionContext ctx, final CMSSite item)
	{
		Boolean value = isEnableGoogleSignUp( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.enableGoogleSignUp</code> attribute. 
	 * @return the enableGoogleSignUp - It is property representing a google login is enable or not
	 */
	public boolean isEnableGoogleSignUpAsPrimitive(final CMSSite item)
	{
		return isEnableGoogleSignUpAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.enableGoogleSignUp</code> attribute. 
	 * @param value the enableGoogleSignUp - It is property representing a google login is enable or not
	 */
	public void setEnableGoogleSignUp(final SessionContext ctx, final CMSSite item, final Boolean value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.CMSSite.ENABLEGOOGLESIGNUP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.enableGoogleSignUp</code> attribute. 
	 * @param value the enableGoogleSignUp - It is property representing a google login is enable or not
	 */
	public void setEnableGoogleSignUp(final CMSSite item, final Boolean value)
	{
		setEnableGoogleSignUp( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.enableGoogleSignUp</code> attribute. 
	 * @param value the enableGoogleSignUp - It is property representing a google login is enable or not
	 */
	public void setEnableGoogleSignUp(final SessionContext ctx, final CMSSite item, final boolean value)
	{
		setEnableGoogleSignUp( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.enableGoogleSignUp</code> attribute. 
	 * @param value the enableGoogleSignUp - It is property representing a google login is enable or not
	 */
	public void setEnableGoogleSignUp(final CMSSite item, final boolean value)
	{
		setEnableGoogleSignUp( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.enableOTPSignup</code> attribute.
	 * @return the enableOTPSignup - It is property representing a otp login is enable or not
	 */
	public Boolean isEnableOTPSignup(final SessionContext ctx, final CMSSite item)
	{
		return (Boolean)item.getProperty( ctx, GroceryCoreConstants.Attributes.CMSSite.ENABLEOTPSIGNUP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.enableOTPSignup</code> attribute.
	 * @return the enableOTPSignup - It is property representing a otp login is enable or not
	 */
	public Boolean isEnableOTPSignup(final CMSSite item)
	{
		return isEnableOTPSignup( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.enableOTPSignup</code> attribute. 
	 * @return the enableOTPSignup - It is property representing a otp login is enable or not
	 */
	public boolean isEnableOTPSignupAsPrimitive(final SessionContext ctx, final CMSSite item)
	{
		Boolean value = isEnableOTPSignup( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.enableOTPSignup</code> attribute. 
	 * @return the enableOTPSignup - It is property representing a otp login is enable or not
	 */
	public boolean isEnableOTPSignupAsPrimitive(final CMSSite item)
	{
		return isEnableOTPSignupAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.enableOTPSignup</code> attribute. 
	 * @param value the enableOTPSignup - It is property representing a otp login is enable or not
	 */
	public void setEnableOTPSignup(final SessionContext ctx, final CMSSite item, final Boolean value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.CMSSite.ENABLEOTPSIGNUP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.enableOTPSignup</code> attribute. 
	 * @param value the enableOTPSignup - It is property representing a otp login is enable or not
	 */
	public void setEnableOTPSignup(final CMSSite item, final Boolean value)
	{
		setEnableOTPSignup( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.enableOTPSignup</code> attribute. 
	 * @param value the enableOTPSignup - It is property representing a otp login is enable or not
	 */
	public void setEnableOTPSignup(final SessionContext ctx, final CMSSite item, final boolean value)
	{
		setEnableOTPSignup( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.enableOTPSignup</code> attribute. 
	 * @param value the enableOTPSignup - It is property representing a otp login is enable or not
	 */
	public void setEnableOTPSignup(final CMSSite item, final boolean value)
	{
		setEnableOTPSignup( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.expiry</code> attribute.
	 * @return the expiry - Expiry for the Product
	 */
	public String getExpiry(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, GroceryCoreConstants.Attributes.Product.EXPIRY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.expiry</code> attribute.
	 * @return the expiry - Expiry for the Product
	 */
	public String getExpiry(final Product item)
	{
		return getExpiry( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.expiry</code> attribute. 
	 * @param value the expiry - Expiry for the Product
	 */
	public void setExpiry(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Product.EXPIRY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.expiry</code> attribute. 
	 * @param value the expiry - Expiry for the Product
	 */
	public void setExpiry(final Product item, final String value)
	{
		setExpiry( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StoreFrontCustomerProcess.generatedCouponCode</code> attribute.
	 * @return the generatedCouponCode - Attribute contains a generated coupon code to be used in customer related processes
	 */
	public String getGeneratedCouponCode(final SessionContext ctx, final StoreFrontCustomerProcess item)
	{
		return (String)item.getProperty( ctx, GroceryCoreConstants.Attributes.StoreFrontCustomerProcess.GENERATEDCOUPONCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StoreFrontCustomerProcess.generatedCouponCode</code> attribute.
	 * @return the generatedCouponCode - Attribute contains a generated coupon code to be used in customer related processes
	 */
	public String getGeneratedCouponCode(final StoreFrontCustomerProcess item)
	{
		return getGeneratedCouponCode( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StoreFrontCustomerProcess.generatedCouponCode</code> attribute. 
	 * @param value the generatedCouponCode - Attribute contains a generated coupon code to be used in customer related processes
	 */
	public void setGeneratedCouponCode(final SessionContext ctx, final StoreFrontCustomerProcess item, final String value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.StoreFrontCustomerProcess.GENERATEDCOUPONCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StoreFrontCustomerProcess.generatedCouponCode</code> attribute. 
	 * @param value the generatedCouponCode - Attribute contains a generated coupon code to be used in customer related processes
	 */
	public void setGeneratedCouponCode(final StoreFrontCustomerProcess item, final String value)
	{
		setGeneratedCouponCode( getSession().getSessionContext(), item, value );
	}
	
	@Override
	public String getName()
	{
		return GroceryCoreConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.ingredients</code> attribute.
	 * @return the ingredients
	 */
	public String getIngredients(final SessionContext ctx, final Product item)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedProduct.getIngredients requires a session language", 0 );
		}
		return (String)item.getLocalizedProperty( ctx, GroceryCoreConstants.Attributes.Product.INGREDIENTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.ingredients</code> attribute.
	 * @return the ingredients
	 */
	public String getIngredients(final Product item)
	{
		return getIngredients( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.ingredients</code> attribute. 
	 * @return the localized ingredients
	 */
	public Map<Language,String> getAllIngredients(final SessionContext ctx, final Product item)
	{
		return (Map<Language,String>)item.getAllLocalizedProperties(ctx,GroceryCoreConstants.Attributes.Product.INGREDIENTS,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.ingredients</code> attribute. 
	 * @return the localized ingredients
	 */
	public Map<Language,String> getAllIngredients(final Product item)
	{
		return getAllIngredients( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.ingredients</code> attribute. 
	 * @param value the ingredients
	 */
	public void setIngredients(final SessionContext ctx, final Product item, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedProduct.setIngredients requires a session language", 0 );
		}
		item.setLocalizedProperty(ctx, GroceryCoreConstants.Attributes.Product.INGREDIENTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.ingredients</code> attribute. 
	 * @param value the ingredients
	 */
	public void setIngredients(final Product item, final String value)
	{
		setIngredients( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.ingredients</code> attribute. 
	 * @param value the ingredients
	 */
	public void setAllIngredients(final SessionContext ctx, final Product item, final Map<Language,String> value)
	{
		item.setAllLocalizedProperties(ctx,GroceryCoreConstants.Attributes.Product.INGREDIENTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.ingredients</code> attribute. 
	 * @param value the ingredients
	 */
	public void setAllIngredients(final Product item, final Map<Language,String> value)
	{
		setAllIngredients( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PointOfService.isExternal</code> attribute.
	 * @return the isExternal
	 */
	public Boolean isIsExternal(final SessionContext ctx, final PointOfService item)
	{
		return (Boolean)item.getProperty( ctx, GroceryCoreConstants.Attributes.PointOfService.ISEXTERNAL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PointOfService.isExternal</code> attribute.
	 * @return the isExternal
	 */
	public Boolean isIsExternal(final PointOfService item)
	{
		return isIsExternal( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PointOfService.isExternal</code> attribute. 
	 * @return the isExternal
	 */
	public boolean isIsExternalAsPrimitive(final SessionContext ctx, final PointOfService item)
	{
		Boolean value = isIsExternal( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PointOfService.isExternal</code> attribute. 
	 * @return the isExternal
	 */
	public boolean isIsExternalAsPrimitive(final PointOfService item)
	{
		return isIsExternalAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PointOfService.isExternal</code> attribute. 
	 * @param value the isExternal
	 */
	public void setIsExternal(final SessionContext ctx, final PointOfService item, final Boolean value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.PointOfService.ISEXTERNAL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PointOfService.isExternal</code> attribute. 
	 * @param value the isExternal
	 */
	public void setIsExternal(final PointOfService item, final Boolean value)
	{
		setIsExternal( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PointOfService.isExternal</code> attribute. 
	 * @param value the isExternal
	 */
	public void setIsExternal(final SessionContext ctx, final PointOfService item, final boolean value)
	{
		setIsExternal( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PointOfService.isExternal</code> attribute. 
	 * @param value the isExternal
	 */
	public void setIsExternal(final PointOfService item, final boolean value)
	{
		setIsExternal( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Category.isRootCategory</code> attribute.
	 * @return the isRootCategory
	 */
	public Boolean isIsRootCategory(final SessionContext ctx, final Category item)
	{
		return (Boolean)item.getProperty( ctx, GroceryCoreConstants.Attributes.Category.ISROOTCATEGORY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Category.isRootCategory</code> attribute.
	 * @return the isRootCategory
	 */
	public Boolean isIsRootCategory(final Category item)
	{
		return isIsRootCategory( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Category.isRootCategory</code> attribute. 
	 * @return the isRootCategory
	 */
	public boolean isIsRootCategoryAsPrimitive(final SessionContext ctx, final Category item)
	{
		Boolean value = isIsRootCategory( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Category.isRootCategory</code> attribute. 
	 * @return the isRootCategory
	 */
	public boolean isIsRootCategoryAsPrimitive(final Category item)
	{
		return isIsRootCategoryAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Category.isRootCategory</code> attribute. 
	 * @param value the isRootCategory
	 */
	public void setIsRootCategory(final SessionContext ctx, final Category item, final Boolean value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Category.ISROOTCATEGORY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Category.isRootCategory</code> attribute. 
	 * @param value the isRootCategory
	 */
	public void setIsRootCategory(final Category item, final Boolean value)
	{
		setIsRootCategory( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Category.isRootCategory</code> attribute. 
	 * @param value the isRootCategory
	 */
	public void setIsRootCategory(final SessionContext ctx, final Category item, final boolean value)
	{
		setIsRootCategory( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Category.isRootCategory</code> attribute. 
	 * @param value the isRootCategory
	 */
	public void setIsRootCategory(final Category item, final boolean value)
	{
		setIsRootCategory( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Country.language</code> attribute.
	 * @return the language
	 */
	public Language getLanguage(final SessionContext ctx, final Country item)
	{
		return (Language)item.getProperty( ctx, GroceryCoreConstants.Attributes.Country.LANGUAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Country.language</code> attribute.
	 * @return the language
	 */
	public Language getLanguage(final Country item)
	{
		return getLanguage( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Country.language</code> attribute. 
	 * @param value the language
	 */
	public void setLanguage(final SessionContext ctx, final Country item, final Language value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Country.LANGUAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Country.language</code> attribute. 
	 * @param value the language
	 */
	public void setLanguage(final Country item, final Language value)
	{
		setLanguage( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Country.latitude</code> attribute.
	 * @return the latitude
	 */
	public Double getLatitude(final SessionContext ctx, final Country item)
	{
		return (Double)item.getProperty( ctx, GroceryCoreConstants.Attributes.Country.LATITUDE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Country.latitude</code> attribute.
	 * @return the latitude
	 */
	public Double getLatitude(final Country item)
	{
		return getLatitude( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Country.latitude</code> attribute. 
	 * @return the latitude
	 */
	public double getLatitudeAsPrimitive(final SessionContext ctx, final Country item)
	{
		Double value = getLatitude( ctx,item );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Country.latitude</code> attribute. 
	 * @return the latitude
	 */
	public double getLatitudeAsPrimitive(final Country item)
	{
		return getLatitudeAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Country.latitude</code> attribute. 
	 * @param value the latitude
	 */
	public void setLatitude(final SessionContext ctx, final Country item, final Double value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Country.LATITUDE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Country.latitude</code> attribute. 
	 * @param value the latitude
	 */
	public void setLatitude(final Country item, final Double value)
	{
		setLatitude( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Country.latitude</code> attribute. 
	 * @param value the latitude
	 */
	public void setLatitude(final SessionContext ctx, final Country item, final double value)
	{
		setLatitude( ctx, item, Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Country.latitude</code> attribute. 
	 * @param value the latitude
	 */
	public void setLatitude(final Country item, final double value)
	{
		setLatitude( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Media.linkIcon</code> attribute.
	 * @return the linkIcon
	 */
	public CMSLinkComponent getLinkIcon(final SessionContext ctx, final Media item)
	{
		return (CMSLinkComponent)item.getProperty( ctx, GroceryCoreConstants.Attributes.Media.LINKICON);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Media.linkIcon</code> attribute.
	 * @return the linkIcon
	 */
	public CMSLinkComponent getLinkIcon(final Media item)
	{
		return getLinkIcon( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Media.linkIcon</code> attribute. 
	 * @param value the linkIcon
	 */
	public void setLinkIcon(final SessionContext ctx, final Media item, final CMSLinkComponent value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Media.LINKICON,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Media.linkIcon</code> attribute. 
	 * @param value the linkIcon
	 */
	public void setLinkIcon(final Media item, final CMSLinkComponent value)
	{
		setLinkIcon( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkComponent.linkMedia</code> attribute.
	 * @return the linkMedia
	 */
	public Set<Media> getLinkMedia(final SessionContext ctx, final CMSLinkComponent item)
	{
		return (Set<Media>)CATEGORYNAVIGATIONCOMPONENTICONLINKMEDIAHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkComponent.linkMedia</code> attribute.
	 * @return the linkMedia
	 */
	public Set<Media> getLinkMedia(final CMSLinkComponent item)
	{
		return getLinkMedia( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkComponent.linkMedia</code> attribute. 
	 * @param value the linkMedia
	 */
	public void setLinkMedia(final SessionContext ctx, final CMSLinkComponent item, final Set<Media> value)
	{
		CATEGORYNAVIGATIONCOMPONENTICONLINKMEDIAHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkComponent.linkMedia</code> attribute. 
	 * @param value the linkMedia
	 */
	public void setLinkMedia(final CMSLinkComponent item, final Set<Media> value)
	{
		setLinkMedia( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to linkMedia. 
	 * @param value the item to add to linkMedia
	 */
	public void addToLinkMedia(final SessionContext ctx, final CMSLinkComponent item, final Media value)
	{
		CATEGORYNAVIGATIONCOMPONENTICONLINKMEDIAHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to linkMedia. 
	 * @param value the item to add to linkMedia
	 */
	public void addToLinkMedia(final CMSLinkComponent item, final Media value)
	{
		addToLinkMedia( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from linkMedia. 
	 * @param value the item to remove from linkMedia
	 */
	public void removeFromLinkMedia(final SessionContext ctx, final CMSLinkComponent item, final Media value)
	{
		CATEGORYNAVIGATIONCOMPONENTICONLINKMEDIAHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from linkMedia. 
	 * @param value the item to remove from linkMedia
	 */
	public void removeFromLinkMedia(final CMSLinkComponent item, final Media value)
	{
		removeFromLinkMedia( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Country.longitude</code> attribute.
	 * @return the longitude
	 */
	public Double getLongitude(final SessionContext ctx, final Country item)
	{
		return (Double)item.getProperty( ctx, GroceryCoreConstants.Attributes.Country.LONGITUDE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Country.longitude</code> attribute.
	 * @return the longitude
	 */
	public Double getLongitude(final Country item)
	{
		return getLongitude( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Country.longitude</code> attribute. 
	 * @return the longitude
	 */
	public double getLongitudeAsPrimitive(final SessionContext ctx, final Country item)
	{
		Double value = getLongitude( ctx,item );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Country.longitude</code> attribute. 
	 * @return the longitude
	 */
	public double getLongitudeAsPrimitive(final Country item)
	{
		return getLongitudeAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Country.longitude</code> attribute. 
	 * @param value the longitude
	 */
	public void setLongitude(final SessionContext ctx, final Country item, final Double value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Country.LONGITUDE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Country.longitude</code> attribute. 
	 * @param value the longitude
	 */
	public void setLongitude(final Country item, final Double value)
	{
		setLongitude( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Country.longitude</code> attribute. 
	 * @param value the longitude
	 */
	public void setLongitude(final SessionContext ctx, final Country item, final double value)
	{
		setLongitude( ctx, item, Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Country.longitude</code> attribute. 
	 * @param value the longitude
	 */
	public void setLongitude(final Country item, final double value)
	{
		setLongitude( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSNavigationNode.navigationIcon</code> attribute.
	 * @return the navigationIcon - Icon for navigation node
	 */
	public Media getNavigationIcon(final SessionContext ctx, final CMSNavigationNode item)
	{
		return (Media)item.getProperty( ctx, GroceryCoreConstants.Attributes.CMSNavigationNode.NAVIGATIONICON);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSNavigationNode.navigationIcon</code> attribute.
	 * @return the navigationIcon - Icon for navigation node
	 */
	public Media getNavigationIcon(final CMSNavigationNode item)
	{
		return getNavigationIcon( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSNavigationNode.navigationIcon</code> attribute. 
	 * @param value the navigationIcon - Icon for navigation node
	 */
	public void setNavigationIcon(final SessionContext ctx, final CMSNavigationNode item, final Media value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.CMSNavigationNode.NAVIGATIONICON,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSNavigationNode.navigationIcon</code> attribute. 
	 * @param value the navigationIcon - Icon for navigation node
	 */
	public void setNavigationIcon(final CMSNavigationNode item, final Media value)
	{
		setNavigationIcon( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.openingSchedule</code> attribute.
	 * @return the openingSchedule - It is property representing a schedule of holidays for the BaseStore
	 */
	public OpeningSchedule getOpeningSchedule(final SessionContext ctx, final BaseStore item)
	{
		return (OpeningSchedule)item.getProperty( ctx, GroceryCoreConstants.Attributes.BaseStore.OPENINGSCHEDULE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.openingSchedule</code> attribute.
	 * @return the openingSchedule - It is property representing a schedule of holidays for the BaseStore
	 */
	public OpeningSchedule getOpeningSchedule(final BaseStore item)
	{
		return getOpeningSchedule( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.openingSchedule</code> attribute. 
	 * @param value the openingSchedule - It is property representing a schedule of holidays for the BaseStore
	 */
	public void setOpeningSchedule(final SessionContext ctx, final BaseStore item, final OpeningSchedule value)
	{
		new PartOfHandler<OpeningSchedule>()
		{
			@Override
			protected OpeningSchedule doGetValue(final SessionContext ctx)
			{
				return getOpeningSchedule( ctx, item );
			}
			@Override
			protected void doSetValue(final SessionContext ctx, final OpeningSchedule _value)
			{
				final OpeningSchedule value = _value;
				item.setProperty(ctx, GroceryCoreConstants.Attributes.BaseStore.OPENINGSCHEDULE,value);
			}
		}.setValue( ctx, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.openingSchedule</code> attribute. 
	 * @param value the openingSchedule - It is property representing a schedule of holidays for the BaseStore
	 */
	public void setOpeningSchedule(final BaseStore item, final OpeningSchedule value)
	{
		setOpeningSchedule( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentMode.paymentModeImage</code> attribute.
	 * @return the paymentModeImage
	 */
	public Media getPaymentModeImage(final SessionContext ctx, final PaymentMode item)
	{
		return (Media)item.getProperty( ctx, GroceryCoreConstants.Attributes.PaymentMode.PAYMENTMODEIMAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentMode.paymentModeImage</code> attribute.
	 * @return the paymentModeImage
	 */
	public Media getPaymentModeImage(final PaymentMode item)
	{
		return getPaymentModeImage( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentMode.paymentModeImage</code> attribute. 
	 * @param value the paymentModeImage
	 */
	public void setPaymentModeImage(final SessionContext ctx, final PaymentMode item, final Media value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.PaymentMode.PAYMENTMODEIMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentMode.paymentModeImage</code> attribute. 
	 * @param value the paymentModeImage
	 */
	public void setPaymentModeImage(final PaymentMode item, final Media value)
	{
		setPaymentModeImage( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductInterest.pointOfService</code> attribute.
	 * @return the pointOfService
	 */
	public PointOfService getPointOfService(final SessionContext ctx, final GenericItem item)
	{
		return (PointOfService)item.getProperty( ctx, GroceryCoreConstants.Attributes.ProductInterest.POINTOFSERVICE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductInterest.pointOfService</code> attribute.
	 * @return the pointOfService
	 */
	public PointOfService getPointOfService(final ProductInterest item)
	{
		return getPointOfService( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductInterest.pointOfService</code> attribute. 
	 * @param value the pointOfService
	 */
	public void setPointOfService(final SessionContext ctx, final GenericItem item, final PointOfService value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.ProductInterest.POINTOFSERVICE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductInterest.pointOfService</code> attribute. 
	 * @param value the pointOfService
	 */
	public void setPointOfService(final ProductInterest item, final PointOfService value)
	{
		setPointOfService( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.preferredPos</code> attribute.
	 * @return the preferredPos - Preferred point of sale
	 */
	public String getPreferredPos(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, GroceryCoreConstants.Attributes.Customer.PREFERREDPOS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.preferredPos</code> attribute.
	 * @return the preferredPos - Preferred point of sale
	 */
	public String getPreferredPos(final Customer item)
	{
		return getPreferredPos( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.preferredPos</code> attribute. 
	 * @param value the preferredPos - Preferred point of sale
	 */
	public void setPreferredPos(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Customer.PREFERREDPOS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.preferredPos</code> attribute. 
	 * @param value the preferredPos - Preferred point of sale
	 */
	public void setPreferredPos(final Customer item, final String value)
	{
		setPreferredPos( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.preferredPostalCode</code> attribute.
	 * @return the preferredPostalCode - Preferred postal code
	 */
	public String getPreferredPostalCode(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, GroceryCoreConstants.Attributes.Customer.PREFERREDPOSTALCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.preferredPostalCode</code> attribute.
	 * @return the preferredPostalCode - Preferred postal code
	 */
	public String getPreferredPostalCode(final Customer item)
	{
		return getPreferredPostalCode( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.preferredPostalCode</code> attribute. 
	 * @param value the preferredPostalCode - Preferred postal code
	 */
	public void setPreferredPostalCode(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Customer.PREFERREDPOSTALCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.preferredPostalCode</code> attribute. 
	 * @param value the preferredPostalCode - Preferred postal code
	 */
	public void setPreferredPostalCode(final Customer item, final String value)
	{
		setPreferredPostalCode( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.preferredShipmentMode</code> attribute.
	 * @return the preferredShipmentMode - Preferred mode of shipment for customer
	 */
	public EnumerationValue getPreferredShipmentMode(final SessionContext ctx, final Customer item)
	{
		return (EnumerationValue)item.getProperty( ctx, GroceryCoreConstants.Attributes.Customer.PREFERREDSHIPMENTMODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.preferredShipmentMode</code> attribute.
	 * @return the preferredShipmentMode - Preferred mode of shipment for customer
	 */
	public EnumerationValue getPreferredShipmentMode(final Customer item)
	{
		return getPreferredShipmentMode( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.preferredShipmentMode</code> attribute. 
	 * @param value the preferredShipmentMode - Preferred mode of shipment for customer
	 */
	public void setPreferredShipmentMode(final SessionContext ctx, final Customer item, final EnumerationValue value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Customer.PREFERREDSHIPMENTMODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.preferredShipmentMode</code> attribute. 
	 * @param value the preferredShipmentMode - Preferred mode of shipment for customer
	 */
	public void setPreferredShipmentMode(final Customer item, final EnumerationValue value)
	{
		setPreferredShipmentMode( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StoreFrontCustomerProcess.promotionMessage</code> attribute.
	 * @return the promotionMessage - Attribute contains a promotion message to be used in customer related processes
	 */
	public String getPromotionMessage(final SessionContext ctx, final StoreFrontCustomerProcess item)
	{
		return (String)item.getProperty( ctx, GroceryCoreConstants.Attributes.StoreFrontCustomerProcess.PROMOTIONMESSAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StoreFrontCustomerProcess.promotionMessage</code> attribute.
	 * @return the promotionMessage - Attribute contains a promotion message to be used in customer related processes
	 */
	public String getPromotionMessage(final StoreFrontCustomerProcess item)
	{
		return getPromotionMessage( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StoreFrontCustomerProcess.promotionMessage</code> attribute. 
	 * @param value the promotionMessage - Attribute contains a promotion message to be used in customer related processes
	 */
	public void setPromotionMessage(final SessionContext ctx, final StoreFrontCustomerProcess item, final String value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.StoreFrontCustomerProcess.PROMOTIONMESSAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StoreFrontCustomerProcess.promotionMessage</code> attribute. 
	 * @param value the promotionMessage - Attribute contains a promotion message to be used in customer related processes
	 */
	public void setPromotionMessage(final StoreFrontCustomerProcess item, final String value)
	{
		setPromotionMessage( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.registrationCoupon</code> attribute.
	 * @return the registrationCoupon
	 */
	public MultiCodeCoupon getRegistrationCoupon(final SessionContext ctx, final BaseStore item)
	{
		return (MultiCodeCoupon)item.getProperty( ctx, GroceryCoreConstants.Attributes.BaseStore.REGISTRATIONCOUPON);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.registrationCoupon</code> attribute.
	 * @return the registrationCoupon
	 */
	public MultiCodeCoupon getRegistrationCoupon(final BaseStore item)
	{
		return getRegistrationCoupon( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.registrationCoupon</code> attribute. 
	 * @param value the registrationCoupon
	 */
	public void setRegistrationCoupon(final SessionContext ctx, final BaseStore item, final MultiCodeCoupon value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.BaseStore.REGISTRATIONCOUPON,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.registrationCoupon</code> attribute. 
	 * @param value the registrationCoupon
	 */
	public void setRegistrationCoupon(final BaseStore item, final MultiCodeCoupon value)
	{
		setRegistrationCoupon( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.registrationCouponEnabled</code> attribute.
	 * @return the registrationCouponEnabled
	 */
	public Boolean isRegistrationCouponEnabled(final SessionContext ctx, final BaseStore item)
	{
		return (Boolean)item.getProperty( ctx, GroceryCoreConstants.Attributes.BaseStore.REGISTRATIONCOUPONENABLED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.registrationCouponEnabled</code> attribute.
	 * @return the registrationCouponEnabled
	 */
	public Boolean isRegistrationCouponEnabled(final BaseStore item)
	{
		return isRegistrationCouponEnabled( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.registrationCouponEnabled</code> attribute. 
	 * @return the registrationCouponEnabled
	 */
	public boolean isRegistrationCouponEnabledAsPrimitive(final SessionContext ctx, final BaseStore item)
	{
		Boolean value = isRegistrationCouponEnabled( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.registrationCouponEnabled</code> attribute. 
	 * @return the registrationCouponEnabled
	 */
	public boolean isRegistrationCouponEnabledAsPrimitive(final BaseStore item)
	{
		return isRegistrationCouponEnabledAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.registrationCouponEnabled</code> attribute. 
	 * @param value the registrationCouponEnabled
	 */
	public void setRegistrationCouponEnabled(final SessionContext ctx, final BaseStore item, final Boolean value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.BaseStore.REGISTRATIONCOUPONENABLED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.registrationCouponEnabled</code> attribute. 
	 * @param value the registrationCouponEnabled
	 */
	public void setRegistrationCouponEnabled(final BaseStore item, final Boolean value)
	{
		setRegistrationCouponEnabled( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.registrationCouponEnabled</code> attribute. 
	 * @param value the registrationCouponEnabled
	 */
	public void setRegistrationCouponEnabled(final SessionContext ctx, final BaseStore item, final boolean value)
	{
		setRegistrationCouponEnabled( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.registrationCouponEnabled</code> attribute. 
	 * @param value the registrationCouponEnabled
	 */
	public void setRegistrationCouponEnabled(final BaseStore item, final boolean value)
	{
		setRegistrationCouponEnabled( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.registrationPromotion</code> attribute.
	 * @return the registrationPromotion
	 */
	public PromotionSourceRule getRegistrationPromotion(final SessionContext ctx, final BaseStore item)
	{
		return (PromotionSourceRule)item.getProperty( ctx, GroceryCoreConstants.Attributes.BaseStore.REGISTRATIONPROMOTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.registrationPromotion</code> attribute.
	 * @return the registrationPromotion
	 */
	public PromotionSourceRule getRegistrationPromotion(final BaseStore item)
	{
		return getRegistrationPromotion( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.registrationPromotion</code> attribute. 
	 * @param value the registrationPromotion
	 */
	public void setRegistrationPromotion(final SessionContext ctx, final BaseStore item, final PromotionSourceRule value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.BaseStore.REGISTRATIONPROMOTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.registrationPromotion</code> attribute. 
	 * @param value the registrationPromotion
	 */
	public void setRegistrationPromotion(final BaseStore item, final PromotionSourceRule value)
	{
		setRegistrationPromotion( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSNavigationNode.sideBanner</code> attribute.
	 * @return the sideBanner - Side banner for navigation node
	 */
	public SimpleBannerComponent getSideBanner(final SessionContext ctx, final CMSNavigationNode item)
	{
		return (SimpleBannerComponent)item.getProperty( ctx, GroceryCoreConstants.Attributes.CMSNavigationNode.SIDEBANNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSNavigationNode.sideBanner</code> attribute.
	 * @return the sideBanner - Side banner for navigation node
	 */
	public SimpleBannerComponent getSideBanner(final CMSNavigationNode item)
	{
		return getSideBanner( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSNavigationNode.sideBanner</code> attribute. 
	 * @param value the sideBanner - Side banner for navigation node
	 */
	public void setSideBanner(final SessionContext ctx, final CMSNavigationNode item, final SimpleBannerComponent value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.CMSNavigationNode.SIDEBANNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSNavigationNode.sideBanner</code> attribute. 
	 * @param value the sideBanner - Side banner for navigation node
	 */
	public void setSideBanner(final CMSNavigationNode item, final SimpleBannerComponent value)
	{
		setSideBanner( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.socialMediaRegistered</code> attribute.
	 * @return the socialMediaRegistered - Flag indicating whether the customer has been registered by Social Media (Facebook
	 * 							/ Google) Sign In.
	 */
	public Boolean isSocialMediaRegistered(final SessionContext ctx, final Customer item)
	{
		return (Boolean)item.getProperty( ctx, GroceryCoreConstants.Attributes.Customer.SOCIALMEDIAREGISTERED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.socialMediaRegistered</code> attribute.
	 * @return the socialMediaRegistered - Flag indicating whether the customer has been registered by Social Media (Facebook
	 * 							/ Google) Sign In.
	 */
	public Boolean isSocialMediaRegistered(final Customer item)
	{
		return isSocialMediaRegistered( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.socialMediaRegistered</code> attribute. 
	 * @return the socialMediaRegistered - Flag indicating whether the customer has been registered by Social Media (Facebook
	 * 							/ Google) Sign In.
	 */
	public boolean isSocialMediaRegisteredAsPrimitive(final SessionContext ctx, final Customer item)
	{
		Boolean value = isSocialMediaRegistered( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.socialMediaRegistered</code> attribute. 
	 * @return the socialMediaRegistered - Flag indicating whether the customer has been registered by Social Media (Facebook
	 * 							/ Google) Sign In.
	 */
	public boolean isSocialMediaRegisteredAsPrimitive(final Customer item)
	{
		return isSocialMediaRegisteredAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.socialMediaRegistered</code> attribute. 
	 * @param value the socialMediaRegistered - Flag indicating whether the customer has been registered by Social Media (Facebook
	 * 							/ Google) Sign In.
	 */
	public void setSocialMediaRegistered(final SessionContext ctx, final Customer item, final Boolean value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Customer.SOCIALMEDIAREGISTERED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.socialMediaRegistered</code> attribute. 
	 * @param value the socialMediaRegistered - Flag indicating whether the customer has been registered by Social Media (Facebook
	 * 							/ Google) Sign In.
	 */
	public void setSocialMediaRegistered(final Customer item, final Boolean value)
	{
		setSocialMediaRegistered( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.socialMediaRegistered</code> attribute. 
	 * @param value the socialMediaRegistered - Flag indicating whether the customer has been registered by Social Media (Facebook
	 * 							/ Google) Sign In.
	 */
	public void setSocialMediaRegistered(final SessionContext ctx, final Customer item, final boolean value)
	{
		setSocialMediaRegistered( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.socialMediaRegistered</code> attribute. 
	 * @param value the socialMediaRegistered - Flag indicating whether the customer has been registered by Social Media (Facebook
	 * 							/ Google) Sign In.
	 */
	public void setSocialMediaRegistered(final Customer item, final boolean value)
	{
		setSocialMediaRegistered( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SimpleBannerComponent.subFooterLeft</code> attribute.
	 * @return the subFooterLeft
	 */
	public FooterNavigationComponent getSubFooterLeft(final SessionContext ctx, final SimpleBannerComponent item)
	{
		return (FooterNavigationComponent)item.getProperty( ctx, GroceryCoreConstants.Attributes.SimpleBannerComponent.SUBFOOTERLEFT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SimpleBannerComponent.subFooterLeft</code> attribute.
	 * @return the subFooterLeft
	 */
	public FooterNavigationComponent getSubFooterLeft(final SimpleBannerComponent item)
	{
		return getSubFooterLeft( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SimpleBannerComponent.subFooterLeft</code> attribute. 
	 * @param value the subFooterLeft
	 */
	public void setSubFooterLeft(final SessionContext ctx, final SimpleBannerComponent item, final FooterNavigationComponent value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.SimpleBannerComponent.SUBFOOTERLEFT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SimpleBannerComponent.subFooterLeft</code> attribute. 
	 * @param value the subFooterLeft
	 */
	public void setSubFooterLeft(final SimpleBannerComponent item, final FooterNavigationComponent value)
	{
		setSubFooterLeft( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Media.subFooterRight</code> attribute.
	 * @return the subFooterRight
	 */
	public FooterNavigationComponent getSubFooterRight(final SessionContext ctx, final Media item)
	{
		return (FooterNavigationComponent)item.getProperty( ctx, GroceryCoreConstants.Attributes.Media.SUBFOOTERRIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Media.subFooterRight</code> attribute.
	 * @return the subFooterRight
	 */
	public FooterNavigationComponent getSubFooterRight(final Media item)
	{
		return getSubFooterRight( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Media.subFooterRight</code> attribute. 
	 * @param value the subFooterRight
	 */
	public void setSubFooterRight(final SessionContext ctx, final Media item, final FooterNavigationComponent value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Media.SUBFOOTERRIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Media.subFooterRight</code> attribute. 
	 * @param value the subFooterRight
	 */
	public void setSubFooterRight(final Media item, final FooterNavigationComponent value)
	{
		setSubFooterRight( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentMode.type</code> attribute.
	 * @return the type
	 */
	public EnumerationValue getType(final SessionContext ctx, final PaymentMode item)
	{
		return (EnumerationValue)item.getProperty( ctx, GroceryCoreConstants.Attributes.PaymentMode.TYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentMode.type</code> attribute.
	 * @return the type
	 */
	public EnumerationValue getType(final PaymentMode item)
	{
		return getType( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentMode.type</code> attribute. 
	 * @param value the type
	 */
	public void setType(final SessionContext ctx, final PaymentMode item, final EnumerationValue value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.PaymentMode.TYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentMode.type</code> attribute. 
	 * @param value the type
	 */
	public void setType(final PaymentMode item, final EnumerationValue value)
	{
		setType( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.usageGuidelines</code> attribute.
	 * @return the usageGuidelines
	 */
	public String getUsageGuidelines(final SessionContext ctx, final Product item)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedProduct.getUsageGuidelines requires a session language", 0 );
		}
		return (String)item.getLocalizedProperty( ctx, GroceryCoreConstants.Attributes.Product.USAGEGUIDELINES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.usageGuidelines</code> attribute.
	 * @return the usageGuidelines
	 */
	public String getUsageGuidelines(final Product item)
	{
		return getUsageGuidelines( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.usageGuidelines</code> attribute. 
	 * @return the localized usageGuidelines
	 */
	public Map<Language,String> getAllUsageGuidelines(final SessionContext ctx, final Product item)
	{
		return (Map<Language,String>)item.getAllLocalizedProperties(ctx,GroceryCoreConstants.Attributes.Product.USAGEGUIDELINES,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.usageGuidelines</code> attribute. 
	 * @return the localized usageGuidelines
	 */
	public Map<Language,String> getAllUsageGuidelines(final Product item)
	{
		return getAllUsageGuidelines( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.usageGuidelines</code> attribute. 
	 * @param value the usageGuidelines
	 */
	public void setUsageGuidelines(final SessionContext ctx, final Product item, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedProduct.setUsageGuidelines requires a session language", 0 );
		}
		item.setLocalizedProperty(ctx, GroceryCoreConstants.Attributes.Product.USAGEGUIDELINES,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.usageGuidelines</code> attribute. 
	 * @param value the usageGuidelines
	 */
	public void setUsageGuidelines(final Product item, final String value)
	{
		setUsageGuidelines( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.usageGuidelines</code> attribute. 
	 * @param value the usageGuidelines
	 */
	public void setAllUsageGuidelines(final SessionContext ctx, final Product item, final Map<Language,String> value)
	{
		item.setAllLocalizedProperties(ctx,GroceryCoreConstants.Attributes.Product.USAGEGUIDELINES,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.usageGuidelines</code> attribute. 
	 * @param value the usageGuidelines
	 */
	public void setAllUsageGuidelines(final Product item, final Map<Language,String> value)
	{
		setAllUsageGuidelines( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.weight</code> attribute.
	 * @return the weight - Weight of the product
	 */
	public String getWeight(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, GroceryCoreConstants.Attributes.Product.WEIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.weight</code> attribute.
	 * @return the weight - Weight of the product
	 */
	public String getWeight(final Product item)
	{
		return getWeight( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.weight</code> attribute. 
	 * @param value the weight - Weight of the product
	 */
	public void setWeight(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, GroceryCoreConstants.Attributes.Product.WEIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.weight</code> attribute. 
	 * @param value the weight - Weight of the product
	 */
	public void setWeight(final Product item, final String value)
	{
		setWeight( getSession().getSessionContext(), item, value );
	}
	
}
