/**
 * 
 */
package org.grocery.core.strategies.impl;


import de.hybris.platform.commerceservices.order.impl.DefaultCommercePaymentAuthorizationStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.grocery.core.model.CODPaymentInfoModel;

/**
 * @author arshdeepsingh
 *
 */
public class GroceryCommercePaymentAuthorizationStrategy extends DefaultCommercePaymentAuthorizationStrategy
{
	
	@Override
	public PaymentTransactionEntryModel authorizePaymentAmount(final CommerceCheckoutParameter parameter)
	{
		final CartModel cartModel = parameter.getCart();
		final BigDecimal amount = parameter.getAuthorizationAmount();
		final String securityCode = parameter.getSecurityCode();
		final String paymentProvider = parameter.getPaymentProvider();

		PaymentTransactionEntryModel transactionEntryModel = null;
		try
		{
			beforeAuthorizePaymentAmount(parameter);
			final PaymentInfoModel paymentInfo = cartModel.getPaymentInfo();
			if (paymentInfo instanceof CreditCardPaymentInfoModel
					&& StringUtils.isNotBlank(((CreditCardPaymentInfoModel) paymentInfo).getSubscriptionId()))
			{
				final Currency currency = getI18nService().getBestMatchingJavaCurrency(cartModel.getCurrency().getIsocode());
				final String merchantTransactionCode = getGenerateMerchantTransactionCodeStrategy().generateCode(cartModel);
				transactionEntryModel = getPaymentService().authorize(merchantTransactionCode, amount, currency,
						cartModel.getDeliveryAddress(), ((CreditCardPaymentInfoModel) paymentInfo).getSubscriptionId(), securityCode,
						paymentProvider);
				if (transactionEntryModel != null)
				{
					final PaymentTransactionModel paymentTransaction = transactionEntryModel.getPaymentTransaction();

					if (TransactionStatus.ACCEPTED.name().equals(transactionEntryModel.getTransactionStatus())
							|| TransactionStatus.REVIEW.name().equals(transactionEntryModel.getTransactionStatus()))
					{
						paymentTransaction.setOrder(cartModel);
						paymentTransaction.setInfo(paymentInfo);
						getModelService().saveAll(cartModel, paymentTransaction);
					}
					else
					{
						// TransactionStatus is error or reject remove the PaymentTransaction and TransactionEntry
						getModelService().removeAll(Arrays.asList(paymentTransaction, transactionEntryModel));
					}
				}
			}
			//COD CASE
			else if (paymentInfo instanceof CODPaymentInfoModel)
			{

				final Currency currency = getI18nService().getBestMatchingJavaCurrency(cartModel.getCurrency().getIsocode());
				final String merchantTransactionCode = getGenerateMerchantTransactionCodeStrategy().generateCode(cartModel);


				transactionEntryModel = getPaymentService().authorize(merchantTransactionCode, amount, currency,
						cartModel.getDeliveryAddress(), UUID.randomUUID().toString());

				if (transactionEntryModel != null)
				{
					final PaymentTransactionModel paymentTransaction = transactionEntryModel.getPaymentTransaction();

					if (TransactionStatus.ACCEPTED.name().equals(transactionEntryModel.getTransactionStatus())
							|| TransactionStatus.REVIEW.name().equals(transactionEntryModel.getTransactionStatus()))
					{
						paymentTransaction.setOrder(cartModel);
						paymentTransaction.setInfo(paymentInfo);
						getModelService().saveAll(cartModel, paymentTransaction);
					}
					else
					{
						// TransactionStatus is error or reject remove the PaymentTransaction and TransactionEntry
						getModelService().removeAll(Arrays.asList(paymentTransaction, transactionEntryModel));
					}
				}
			}
			return transactionEntryModel;
		}
		finally
		{
			afterAuthorizePaymentAmount(parameter, transactionEntryModel);
		}
	}

}
