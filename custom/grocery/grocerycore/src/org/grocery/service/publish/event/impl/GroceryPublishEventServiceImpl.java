/**
 *
 */
package org.grocery.service.publish.event.impl;

//import static de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService.LOG;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.commerceservices.security.SecureToken;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.CardInfo;
import de.hybris.platform.payment.dto.NewSubscription;
import de.hybris.platform.servicelayer.event.EventService;

import java.util.Currency;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.grocery.core.event.UpdatedPasswordEvent;
import org.grocery.core.model.CODPaymentInfoModel;
import org.grocery.service.publish.event.GroceryPublishEventService;

/**
 * @author ankituniyal
 *
 */
public class GroceryPublishEventServiceImpl extends DefaultCustomerAccountService implements GroceryPublishEventService
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(DefaultCustomerAccountService.class);

	private EventService eventService;

	@Override
	public void publishUpdatePasswordEvent(final String token)
	{
		final SecureToken data = getSecureTokenService().decryptData(token);
		if (getTokenValiditySeconds() > 0L)
		{
			final long delta = new Date().getTime() - data.getTimeStamp();
			if (delta / 1000 > getTokenValiditySeconds())
			{
				throw new IllegalArgumentException("token expired");
			}
		}

		final CustomerModel customer = getUserService().getUserForUID(data.getData(), CustomerModel.class);
		eventService.publishEvent(initializeEvent(new UpdatedPasswordEvent(), customer));
	}


	
	/*
	 * public CODPaymentInfoModel createPaymentSubscription(final CustomerModel customerModel, final BillingInfo
	 * billingInfo, final String titleCode)
	 */
	public CODPaymentInfoModel createPaymentSubscription(final CustomerModel customerModel)
	{
		validateParameterNotNull(customerModel, "Customer cannot be null");
		//validateParameterNotNull(billingInfo, "billingInfo cannot be null");
		final CurrencyModel currencyModel = getCurrency(customerModel);
		validateParameterNotNull(currencyModel, "Customer session currency cannot be null");

		final Currency currency = getI18nService().getBestMatchingJavaCurrency(currencyModel.getIsocode());

		/*
		 * final AddressModel billingAddress = getModelService().create(AddressModel.class); if
		 * (StringUtils.isNotBlank(titleCode)) { final TitleModel title = new TitleModel(); title.setCode(titleCode);
		 * billingAddress.setTitle(getFlexibleSearchService().getModelByExample(title)); }
		 * billingAddress.setFirstname(billingInfo.getFirstName()); billingAddress.setLastname(billingInfo.getLastName());
		 * billingAddress.setLine1(billingInfo.getStreet1()); billingAddress.setLine2(billingInfo.getStreet2());
		 * billingAddress.setTown(billingInfo.getCity()); billingAddress.setPostalcode(billingInfo.getPostalCode());
		 * billingAddress.setCountry(getCommonI18NService().getCountry(billingInfo.getCountry())); if
		 * (billingInfo.getRegion() != null) {
		 * billingAddress.setRegion(getCommonI18NService().getRegion(billingAddress.getCountry(),
		 * billingInfo.getRegion())); } billingAddress.setPhone1(billingInfo.getPhoneNumber()); final String email =
		 * getCustomerEmailResolutionService().getEmailForCustomer(customerModel); billingAddress.setEmail(email);
		 */
		try
		{
			final CODPaymentInfoModel codPaymentInfoModel = getModelService().create(CODPaymentInfoModel.class);
			codPaymentInfoModel.setCode(customerModel.getUid() + "_" + UUID.randomUUID());
			codPaymentInfoModel.setUser(customerModel);
			//billingAddress.setOwner(codPaymentInfoModel);
			//codPaymentInfoModel.setBillingAddress(billingAddress);

			//getModelService().saveAll(billingAddress, codPaymentInfoModel);
			getModelService().refresh(customerModel);

			addPaymentInfo(customerModel, codPaymentInfoModel);

			return codPaymentInfoModel;

		}
		catch (final AdapterException ae) //NOSONAR
		{
			LOG.error("Failed to create subscription for customer. Customer PK: " + String.valueOf(customerModel.getPk())
					+ " Exception: " + ae.getClass().getName());

		}

		return null;
	}

	/**
	 * @return the eventService
	 */
	@Override
	public EventService getEventService()
	{
		return eventService;
	}

	/**
	 * @param eventService
	 *           the eventService to set
	 */
	@Override
	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}


}
