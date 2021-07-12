<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="showDeliveryAddress" required="true" type="java.lang.Boolean"%>
<%@ attribute name="showPaymentInfo" required="false" type="java.lang.Boolean"%>
<%@ attribute name="showTax" required="false" type="java.lang.Boolean"%>
<%@ attribute name="showTaxEstimate" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<div class="checkout-right-order-details">
    <div class="sticky-checkout-footer">
        <div class="sticky-footer-up fa fa-angle-double-up"></div>
        <div class="checkout-header">
            <div class="checkout-summary-headline">
                <spring:theme code="checkout.multi.order.summary" />
            </div>
            <div class="checkout-order-totals">
                <div class="order-totals-label">
                    <spring:theme code="checkout.multi.total.price" />
                </div>
                <div class="order-totals-value">
                    <multi-checkout:orderTotalPrice showTax="true" cartData="${cartData}" />
                </div>
            </div>
        </div>
        <div class="sticky-footer-down fa fa-angle-double-up"></div>
        <div class="checkout-right-financials-mobile"></div>
    </div>
    <order:appliedVouchers order="${cartData}" />
    <c:if test="${not empty cartData.potentialOrderPromotions or not empty cartData.appliedOrderPromotions}">
        <div class="checkout-promotions">
            <cart:cartPromotions cartData="${cartData}" />
        </div>
    </c:if>
    <c:if test="${shipmentType ne 'PICKUP' and not empty cartData.deliveryAddress}">
        <multi-checkout:deliveryCartItems cartData="${cartData}" showDeliveryAddress="${showDeliveryAddress}" />
    </c:if>
    <c:if test="${shipmentType eq 'PICKUP'}">
        <c:forEach items="${cartData.pickupOrderGroups}" var="groupData" varStatus="status">
            <multi-checkout:pickupCartItems cartData="${cartData}" groupData="${groupData}" showHead="true" />
        </c:forEach>
    </c:if>
    <multi-checkout:checkoutCartItems cartData="${cartData}" groupData="${groupData}" showHead="true" showPotentialPromotions="true" />
    <div class="checkout-right-financials-desktop">
        <div class="checkout-order-summary order-subtotals">
            <multi-checkout:paymentInfo cartData="${cartData}" paymentInfo="${cartData.paymentInfo}" showPaymentInfo="${showPaymentInfo}" />
            <multi-checkout:orderTotals cartData="${cartData}" showTaxEstimate="${showTaxEstimate}" showTax="${showTax}" />
        </div>
        <div class="checkout-order-summary">
            <multi-checkout:orderTotalsWithTax cartData="${cartData}" showTaxEstimate="${showTaxEstimate}" showTax="${showTax}" />
        </div>
    </div>
</div>