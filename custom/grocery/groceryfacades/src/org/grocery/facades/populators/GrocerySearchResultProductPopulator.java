/**
 *
 */
package org.grocery.facades.populators;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.commercefacades.product.data.StockData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.wishlist2.Wishlist2Service;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.grocery.core.enums.ShipmentTypeEnum;
import org.grocery.core.warehouse.service.GroceryWarehouseService;
import org.grocery.facades.shipment.ShipmentFacade;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author ankituniyal
 *
 */
public class GrocerySearchResultProductPopulator extends SearchResultProductPopulator
{
	private static final Logger LOG = Logger.getLogger(GrocerySearchResultProductPopulator.class);

	private static final String BRAND = "brand";
	private static final String WEIGHT = "weight";
	private static final String DIETSUITABILITY = "dietSuitability";
	private static final String DISCOUNTED_PRICE = "discountedPrice";
	private static final String POTENTIAL_PROMOTIONS = "potentialPromotions";
	private static final String PRODUCT_STOCK_STATUS = "serviceability";
	private static final String IS_AVAILABLE_DELIVERY = "availableForDelivery";
	private static final String IS_AVAILABLE_PICK_UP = "availableForPickUp";
	private static final String APPROVAL_STATUS = "approvalStatus";
	private static final String STOCK_SPLIT_CHAR = "_";
	private static final String IN_STOCK = "inStock";
	private static final String PACK_SIZE_LABEL = "packSizeLabel";

	private Wishlist2Service wishlistService;
	private ProductService productService;
	private GroceryWarehouseService groceryWarehouseService;
	@Autowired
	private CommonI18NService commonI18NService;
	private ShipmentFacade shipmentFacade;
	@Autowired
	private Converter<ProductModel, ProductData> productConverter;

	@Autowired
	CartFacade cartFacade;


	@Override
	public void populate(final SearchResultValueData source, final ProductData target)
	{
		super.populate(source, target);
		final String brand = this.<String> getValue(source, BRAND);
		target.setBrand(StringUtils.isNotBlank(brand) ? brand : StringUtils.EMPTY);

		final String ispurchaseable = this.<String> getValue(source, APPROVAL_STATUS);
		target.setPurchasable(ArticleApprovalStatus.APPROVED.getCode().equals(ispurchaseable));


		final String weight = this.<String> getValue(source, WEIGHT);
		final String packSizeLabel = this.<String> getValue(source, PACK_SIZE_LABEL);
		
		if(packSizeLabel != null) {
			
		target.setWeight(StringUtils.isNotBlank(packSizeLabel) ? packSizeLabel : StringUtils.EMPTY);
		
		}else {
			
			target.setWeight(StringUtils.isNotBlank(weight) ? weight : StringUtils.EMPTY);
		}

		setProductCartQuantity(target);
		setIsProductInWishlist(target);
		setDiscountedPrice(source, target);
		setDietSuitability(source, target);
		setServiceability(source, target);
		target.setPotentialPromotions(Collections.singletonList(populatePromotionDescription(source)));
		populateStock(source, target);
	}

	@Override
	protected void populateStock(final SearchResultValueData source, final ProductData target)
	{
		super.populateStock(source, target);
		final ProductModel productModel = productService.getProductForCode(target.getCode());
		final StockData stockLevel = getStockConverter().convert(productModel);
		target.setStock(stockLevel);
	}

	/**
	 * @param source
	 * @param target
	 */
	private void setServiceability(final SearchResultValueData source, final ProductData target)
	{
		final List<String> productStockInPos = this.<List<String>> getValue(source, PRODUCT_STOCK_STATUS);
		if (CollectionUtils.isEmpty(productStockInPos))
		{
			return;
		}

		target.setIsServiceable(Boolean.FALSE);
		final String shipmentType = getShipmentFacade().getCurrentShipmentMethod();

		if (StringUtils.isBlank(shipmentType))
		{
			target.setIsServiceable(Boolean.TRUE);
		}
		else
		{
			populateServiceability(target, source, shipmentType, productStockInPos);
		}

	}

	/**
	 * @param target
	 * @param source
	 * @param shipmentType
	 * @param productStockInPos
	 * @param availability
	 */
	private void populateServiceability(final ProductData target, final SearchResultValueData source, final String shipmentType,
			final List<String> productStockInPos)
	{
		final Boolean isAvailableForDelivery = this.<Boolean> getValue(source, IS_AVAILABLE_DELIVERY);
		final Boolean isAvailableForPickUp = this.<Boolean> getValue(source, IS_AVAILABLE_PICK_UP);

		if (ShipmentTypeEnum.PICKUP.equals(ShipmentTypeEnum.valueOf(shipmentType)) && isAvailableForPickUp)
		{
			populateServiceabilityForPickup(target, productStockInPos);
		}

		if (ShipmentTypeEnum.DELIVERY.equals(ShipmentTypeEnum.valueOf(shipmentType)) && isAvailableForDelivery)
		{
			populateServiceabilityForDelivery(target);

		}
	}

	/**
	 * @param target
	 */
	private void populateServiceabilityForDelivery(final ProductData target)
	{
		final String postCode = shipmentFacade.getCurrentPostalCode();
		LOG.debug("Fetch warehouse list for the product in stock and the postcode " + postCode);
		try
		{
			final List<WarehouseModel> warehouses = StringUtils.isNotBlank(postCode)
					? getGroceryWarehouseService().getWarehouseForProductAndPost(target.getCode(), Long.parseLong(postCode))
					: (List<WarehouseModel>) CollectionUtils.EMPTY_COLLECTION;

			target.setIsServiceable(CollectionUtils.isNotEmpty(warehouses));
		}
		catch (final NumberFormatException e)
		{
			LOG.debug("Can't parse postCode" + e);
		}

	}

	/**
	 * @param target
	 * @param productStockInPos
	 *
	 */
	private void populateServiceabilityForPickup(final ProductData target, final List<String> productStockInPos)
	{
		final String storeName = shipmentFacade.getCurrentStoreID();

		for (final String storeString : productStockInPos)
		{
			final String store = storeString.substring(0, storeString.lastIndexOf(STOCK_SPLIT_CHAR));
			final String stockStatus = storeString.substring(storeString.lastIndexOf(STOCK_SPLIT_CHAR) + 1);
			if (store.equals(storeName) && IN_STOCK.equalsIgnoreCase(stockStatus))
			{
				target.setIsServiceable(Boolean.TRUE);
			}
		}
	}

	/**
	 * @param source
	 * @param target
	 */
	private PromotionData populatePromotionDescription(final SearchResultValueData source)
	{
		final PromotionData promoData = createProductPromotionData();
		final String promoDesc = this.getValue(source, POTENTIAL_PROMOTIONS);
		final String promoDescString = this.getValue(source, POTENTIAL_PROMOTIONS + "_string");
		final String promotionDescription = StringUtils.isNotBlank(promoDesc) ? promoDesc : promoDescString;
		if (StringUtils.isNotEmpty(promotionDescription))
		{
			promoData.setDescription(promotionDescription);
			return promoData;
		}
		return null;
	}

	protected PromotionData createProductPromotionData()
	{
		return new PromotionData();
	}

	/**
	 * @param source
	 * @param target
	 */
	private void setDietSuitability(final SearchResultValueData source, final ProductData target)
	{
		final String dietSuitability = this.<String> getValue(source, DIETSUITABILITY);
		target.setDietSuitability(StringUtils.isNotBlank(dietSuitability) ? dietSuitability : StringUtils.EMPTY);
	}



	/**
	 * @param source
	 * @param target
	 */
	private void setDiscountedPrice(final SearchResultValueData source, final ProductData target)
	{
		final Double discountedPrice = this.<Double> getValue(source, DISCOUNTED_PRICE);
		final Double discPriceDouble = this.<Double> getValue(source, DISCOUNTED_PRICE + "_double");
		final Double discPrice = discountedPrice != null ? discountedPrice : discPriceDouble;

		final CurrencyModel currency = getCommonI18NService().getCurrentCurrency();
		final PriceData price = target.getPrice();

		if (price != null && discPrice != null && discPrice > 0.0 && (price.getCurrencyIso().equals(currency.getIsocode())))
		{
			final PriceData discountPrice = getPriceDataFactory().create(PriceDataType.BUY, BigDecimal.valueOf(discPrice),
					price.getCurrencyIso());

			if (null != discountPrice && null != discountPrice.getValue() && !BigDecimal.ZERO.equals(discountPrice.getValue())
					&& discountPrice.getValue().doubleValue() < price.getValue().doubleValue())
			{
				price.setSalesPrice(new BigDecimal(discPrice));
				price.setFormattedSalesPrice(discountPrice.getFormattedValue());
				target.setPrice(price);
				target.setDiscounted(true);
			}
		}
	}

	/**
	 * @param target
	 */
	private void setProductCartQuantity(final ProductData target)
	{
		final ProductModel productModel = getProductService().getProductForCode(target.getCode());
		final ProductData productData = getProductConverter().convert(productModel);
		target.setProductCartQuantity(productData.getProductCartQuantity());
	}

	/**
	 * @param target
	 */
	private void setIsProductInWishlist(final ProductData target)
	{
		final ProductModel productModel = getProductService().getProductForCode(target.getCode());
		final Wishlist2Model wishlist = getWishlistService().getDefaultWishlist();

		if (wishlist != null)
		{
			try
			{
				final Wishlist2EntryModel wishlist2EntryModel = getWishlistService().getWishlistEntryForProduct(productModel,
						wishlist);
				if (wishlist2EntryModel != null)
				{
					target.setIsProductPresentInWishlist(Boolean.TRUE);
				}
				else
				{
					target.setIsProductPresentInWishlist(Boolean.FALSE);
				}
			}
			catch (final UnknownIdentifierException | AmbiguousIdentifierException e)
			{
				target.setIsProductPresentInWishlist(Boolean.FALSE);
			}
		}
		else
		{
			target.setIsProductPresentInWishlist(Boolean.FALSE);
		}

	}

	/**
	 * @return the productConverter
	 */
	public Converter<ProductModel, ProductData> getProductConverter()
	{
		return productConverter;
	}

	/**
	 * @param productConverter
	 *           the productConverter to set
	 */
	public void setProductConverter(final Converter<ProductModel, ProductData> productConverter)
	{
		this.productConverter = productConverter;
	}

	/**
	 * @return the groceryWarehouseService
	 */
	public GroceryWarehouseService getGroceryWarehouseService()
	{
		return groceryWarehouseService;
	}

	/**
	 * @param groceryWarehouseService
	 *           the groceryWarehouseService to set
	 */
	public void setGroceryWarehouseService(final GroceryWarehouseService groceryWarehouseService)
	{
		this.groceryWarehouseService = groceryWarehouseService;
	}

	/**
	 * @return the shipmentFacade
	 */
	public ShipmentFacade getShipmentFacade()
	{
		return shipmentFacade;
	}

	/**
	 * @param shipmentFacade
	 *           the shipmentFacade to set
	 */
	public void setShipmentFacade(final ShipmentFacade shipmentFacade)
	{
		this.shipmentFacade = shipmentFacade;
	}

	/**
	 * @return the commonI18NService
	 */
	@Override
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	/**
	 * @param commonI18NService
	 *           the commonI18NService to set
	 */
	@Override
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	/**
	 * @return the wishlistService
	 */
	public Wishlist2Service getWishlistService()
	{
		return wishlistService;
	}


	/**
	 * @param wishlistService
	 *           the wishlistService to set
	 */
	public void setWishlistService(final Wishlist2Service wishlistService)
	{
		this.wishlistService = wishlistService;
	}


	/**
	 * @return the productService
	 */
	@Override
	public ProductService getProductService()
	{
		return productService;
	}


	/**
	 * @param productService
	 *           the productService to set
	 */
	@Override
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}


}
