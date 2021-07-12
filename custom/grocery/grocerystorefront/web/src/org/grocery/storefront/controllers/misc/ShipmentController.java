/**
 *
 */
package org.grocery.storefront.controllers.misc;

import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.grocery.facades.shipment.ShipmentFacade;
import org.grocery.storefront.controllers.AbstractGroceryPageController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/shipment")
public class ShipmentController extends AbstractGroceryPageController
{
	private static final Logger LOG = Logger.getLogger(ShipmentController.class);

	@Resource
	ShipmentFacade shipmentFacade;

	@RequestMapping(value = "/isValidPostalCode", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Boolean> isValidPostalCode(@RequestParam("postalCode")
	final String postalCode)
	{
		final Map<String, Boolean> responseBody = new HashMap<>();
		Boolean result = Boolean.FALSE;

		try
		{
			if (StringUtils.isNotBlank(postalCode) && shipmentFacade.isValidPostalCode(postalCode))
			{
				result = Boolean.TRUE;
			}
		}
		catch (final NumberFormatException e)
		{
			LOG.debug("Can't parse postal code.");
		}
		responseBody.put("result", result);
		return responseBody;
	}

	@RequestMapping(value = "/isValidStore", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Boolean> isValidStore(@RequestParam("storeID")
	final String storeID)
	{
		final Map<String, Boolean> responseBody = new HashMap<>();
		Boolean result = Boolean.FALSE;
		if (StringUtils.isNotBlank(storeID) && shipmentFacade.isValidStoreID(storeID))
		{
			result = Boolean.TRUE;
		}
		responseBody.put("result", result);
		return responseBody;
	}

	@RequestMapping(value = "/setPostalCode", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Boolean> setDelivery(@RequestParam("postalCode")
	final String postalCode)
	{
		final Map<String, Boolean> responseBody = new HashMap<>();
		Boolean result = Boolean.FALSE;

		try
		{
			if (StringUtils.isNotBlank(postalCode) && shipmentFacade.setPostalCode(Long.parseLong(postalCode)))
			{
				result = Boolean.TRUE;
			}
		}
		catch (final NumberFormatException e)
		{
			LOG.debug("Can't parse postal code.");
		}
		responseBody.put("result", result);
		return responseBody;
	}

	@RequestMapping(value = "/setStoreID", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Boolean> setStore(@RequestParam("storeID")
	final String storeID)
	{
		final Map<String, Boolean> responseBody = new HashMap<>();
		Boolean result = Boolean.FALSE;
		if (StringUtils.isNotBlank(storeID))
		{
			if (shipmentFacade.setStoreID(storeID))
			{
				result = Boolean.TRUE;
			}
		}
		responseBody.put("result", result);
		return responseBody;

	}

	@RequestMapping(value = "/checkValidityOfText", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Boolean> checkValidity(@RequestParam("locationText")
	final String locationText, @RequestParam("shipmentType")
	final String shipmentType, final Model model)
	{
		final Map<String, Boolean> responseBody = new HashMap<>();
		Boolean result = Boolean.FALSE;
		if (StringUtils.isNotBlank(locationText))
		{
			final Boolean validity = shipmentFacade.checkValidity(locationText, shipmentType);
			if (validity)
			{
				result = Boolean.TRUE;
			}
		}
		responseBody.put("result", result);
		return responseBody;
	}

	@RequestMapping(value = "/findAffectedCartProducts", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<ProductData> findAffectedCartProducts(@RequestParam("locationText")
	final String locationText, @RequestParam("shipmentType")
	final String shipmentType, final Model model)
	{
		List<ProductData> affectedProducts = Collections.EMPTY_LIST;

		affectedProducts = shipmentFacade.getAffectedCartProducts(locationText, shipmentType);
		return affectedProducts;
	}
}
