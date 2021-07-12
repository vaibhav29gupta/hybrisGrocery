<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="groupData" required="false" type="de.hybris.platform.commercefacades.order.data.OrderEntryGroupData"%>
<%@ attribute name="showPotentialPromotions" required="false" type="java.lang.Boolean"%>
<%@ attribute name="showHead" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:if test="${empty isCartReviewStep}">
    <div class="checkout-items-list">
        <div class="checkout-order-summary-list-heading">
            <spring:theme code="checkout.multi.items.preview" />
        </div>
        <div class="checkout-right-products">
            <c:choose>
                <c:when test="${shipmentType ne 'PICKUP' and not empty cartData.deliveryAddress}">
                    <c:forEach items="${cartData.entries}" var="entry" varStatus="loop">
                        <multi-checkout:checkoutCartItem cartData="${cartData}" entry="${entry}" entrynumber="${loop}" showPotentialPromotions="${showPotentialPromotions}" />
                    </c:forEach>
                    <c:if test="${fn:length(cartData.entries) > 3}">
                        <div class="hide-show-link show-initial btn btn-a">
                            <spring:theme code="mobile.checkout.items.show" />
                        </div>
                        <div class="hide-show-link hide-initial btn btn-a">
                            <spring:theme code="mobile.checkout.items.hide" />
                        </div>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${cartData.pickupOrderGroups}" var="groupData" varStatus="status">
                        <c:forEach items="${groupData.entries}" var="entry" varStatus="loop">
                            <multi-checkout:checkoutCartItem cartData="${cartData}" entry="${entry}" entrynumber="${loop}" showPotentialPromotions="${showPotentialPromotions}" />
                        </c:forEach>
                    </c:forEach>
                    <c:if test="${fn:length(cartData.entries) > 3}">
                        <div class="hide-show-link show-initial btn btn-a">
                            <spring:theme code="mobile.checkout.items.show" />
                        </div>
                        <div class="hide-show-link hide-initial btn btn-a">
                            <spring:theme code="mobile.checkout.items.hide" />
                        </div>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</c:if>
