<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="multiCheckout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">
    <div class="checkout-headline">
        <spring:theme code="checkout.multi.secure.checkout" />
    </div>
    <div class="checkout-multi-step">
        <div id="left-hand-pane" class="checkout-left">
            <multiCheckout:checkoutSteps checkoutSteps="${checkoutSteps}" progressBarId="${progressBarId}">
                <jsp:body>
                <div class="checkout-paymentmethod">
                 <div class="headline">
                              <spring:theme code="checkout.summary.paymentMode.selectPaymentMethodForOrder" />
                           </div>
                    <form:form id="paymentDetailOrderPostForm" name="paymentDetailOrderPostForm" modelAttribute="paymentDetailsForm" action="${paymentFormUrl}" method="POST">
                        <div class="checkout-payment-modes">
                        <c:forEach var="paymentMode" items="${paymentModeList}" varStatus="loop">
                           <div class="checkout-payment-mode">
                             <input type="radio"  name="paymentId" class="checkout-payment-radio" value="${paymentMode.type}"
                            ${loop.count < 2 ? 'checked="checked"' : ''} />
                           
                            <div class="checkout-payment-cod payment-mode">
                            <div class="payment-mode-content">
                            <img class="checkout-payment-image-cod" src="${paymentMode.paymentModeImage.url}" />
                            <div class="payment-mode-name"><spring:theme code="${paymentMode.name}" /></div>
                            </div>
                            <div class="payment-mode-selected">
                            <i class="far fa-check-circle"></i>
                            </div>
                            </div>
                        </div>
                        </c:forEach>
                      <%-- <div class="checkout-payment-mode">
                            <form:radiobutton path="paymentId" class="checkout-payment-radio" value="CARD" checked="checked" />
                            <div class="checkout-payment-card payment-mode">
                                
                                <div class="payment-mode-name"><spring:theme code="payment.detail.card" /></div>
                                <div class="payment-mode-images">
                            <img class="checkout-payment-image-card" src="${themeResourcePath}/images/mastercard.png" />
                            <img class="checkout-payment-image-card" src="${themeResourcePath}/images/visa.png" />
                                </div>
                            </div>
                        </div>
                        <div class="checkout-payment-mode">
                            <form:radiobutton path="paymentId" class="checkout-payment-radio" value="COD" />
                            <div class="checkout-payment-cod payment-mode">
                            <div class="payment-mode-name"><spring:theme code="payment.detail.cod" /></div>
                            <img class="checkout-payment-image-cod" src="${themeResourcePath}/images/cash-on-delivery.png" />
                            </div>
                        </div>
                            <br> --%> 
                        </div>
                    </form:form>
                </div>
                <button type="button" class="card-submit-button btn btn-primary btn-block submit_paymentDetailOrderPostForm checkout-next" style="width: 145px">
                    <spring:theme code="checkout.multi.placeorder.continue" />
                </button>
                                		   </jsp:body>
            </multiCheckout:checkoutSteps>
        </div>
        <div id="right-hand-pane" class="checkout-right">
            <multiCheckout:checkoutOrderDetails cartData="${cartData}" showDeliveryAddress="true" showPaymentInfo="false" showTaxEstimate="false" showTax="true" />
            <div class="contact-us-checkout">
                <cms:pageSlot position="SideContent" var="feature" element="div" class="checkout-help">
                    <cms:component component="${feature}" />
                </cms:pageSlot>
            </div>
        </div>
    </div>
</template:page>
