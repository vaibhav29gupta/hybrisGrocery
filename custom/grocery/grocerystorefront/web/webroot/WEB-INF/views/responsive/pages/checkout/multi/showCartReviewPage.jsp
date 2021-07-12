<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">
    <div class="checkout-headline">
        <spring:theme code="checkout.multi.secure.checkout" />
    </div>
    <div class="checkout-multi-step">
        <div id="left-hand-pane" class="checkout-left">
            <cart:cartValidation />
            <multi-checkout:checkoutSteps checkoutSteps="${checkoutSteps}" progressBarId="${progressBarId}">
                <jsp:body>
		<%-- <multi-checkout:selectShipment /> --%>
	    <ul class="item__list item__list__cart checkout-cart">
<!--             <li class="hidden-xs hidden-sm"> -->
<!--                 <ul class="item__list--header"> -->
<!--                     <li class="item__toggle"></li> -->
<!--                     <li class="item__image"></li> -->
<!--                     <li class="item__info"> -->
<%--                                 <spring:theme code="basket.page.item" /> --%>
<!--                             </li> -->
<!--                     <li class="item__price"> -->
<%--                                 <spring:theme code="basket.page.price" /> --%>
<!--                             </li> -->
<!--                     <li class="item__quantity item-qty"> -->
<%--                                 <spring:theme code="basket.page.qty" /> --%>
<!--                             </li> -->
<%--                     <li class="item__delivery"><spring:theme code="basket.page.delivery"/></li> --%>
<!--                     <li class="item__total--column"> -->
<%--                                 <spring:theme code="basket.page.total" /> --%>
<!--                             </li> -->
<!--                     <li class="item__remove"></li> -->
<!--                 </ul> -->
<!--             </li> -->

	     <c:forEach items="${cartData.rootGroups}" var="group" varStatus="loop">
           	<cart:rootEntryGroup cartData="${cartData}" entryGroup="${group}" />
        </c:forEach>
       </ul>
       
       <c:url var="nexturl" value="${nextStepUrl}" />
       <form:form id="cartReviewFrom" name="cartReviewFrom" action="${nexturl}" method="GET">
		</form:form>
       <button type="button" class="card-submit-button btn btn-neutral btn-block submit_cartReviewFrom checkout-next">
                    <spring:theme code="checkout.multi.paymentMethod.continue" />
       </button>

		</jsp:body>
            </multi-checkout:checkoutSteps>
        </div>
        <div id="right-hand-pane" class="checkout-right">
            <multi-checkout:checkoutOrderDetails cartData="${cartData}" showDeliveryAddress="true" showPaymentInfo="false" showTaxEstimate="false" showTax="true" />
            <div class="contact-us-checkout">
                <cms:pageSlot position="SideContent" var="feature" element="div" class="checkout-help">
                    <cms:component component="${feature}" />
                </cms:pageSlot>
            </div>
        </div>
    </div>
</template:page>