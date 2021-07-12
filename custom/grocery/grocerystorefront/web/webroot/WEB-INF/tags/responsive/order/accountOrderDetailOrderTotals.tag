<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.AbstractOrderData" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:if test="${not empty order}">
    <div class="account-orderdetail cart-total-display">
        <div class="cart-total-footer">
                <div class="cart-promotions-vouchers">
                    <order:appliedVouchers order="${order}" />
                    <order:receivedPromotions order="${order}" />
                </div>
                <div class="cart-totals-items">
                    <order:orderTotalsItem order="${order}" />
                </div>
        </div>
    </div>
</c:if>