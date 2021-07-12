<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="groupData" required="true" type="de.hybris.platform.commercefacades.order.data.OrderEntryGroupData"%>
<%@ attribute name="showPotentialPromotions" required="false" type="java.lang.Boolean"%>
<%@ attribute name="showHead" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:if test="${showHead}">
    <div class="delivery-details">
        <div class="checkout-order-summary-list">
            <div class="checkout-order-summary-list-heading">
                <spring:theme code="checkout.multi.items.to.pickup" />
            </div>
            <div class="address">
                ${fn:escapeXml(groupData.deliveryPointOfService.displayName)}
                <br>
                <c:if test="${ not empty groupData.deliveryPointOfService.address.line1 }">
                        ${fn:escapeXml(groupData.deliveryPointOfService.address.line1)},&nbsp;
            </c:if>
                <c:if test="${ not empty groupData.deliveryPointOfService.address.line2 }">
                ${fn:escapeXml(groupData.deliveryPointOfService.address.line2)},&nbsp;
            </c:if>
                <c:if test="${not empty groupData.deliveryPointOfService.address.town }">
                ${fn:escapeXml(groupData.deliveryPointOfService.address.town)},&nbsp;
            </c:if>
                <c:if test="${ not empty groupData.deliveryPointOfService.address.region.name }">
                ${fn:escapeXml(groupData.deliveryPointOfService.address.region.name)},&nbsp;
            </c:if>
                <c:if test="${ not empty groupData.deliveryPointOfService.address.postalCode }">
                ${fn:escapeXml(groupData.deliveryPointOfService.address.postalCode)},&nbsp;
            </c:if>
                <c:if test="${ not empty groupData.deliveryPointOfService.address.country.name }">
                ${fn:escapeXml(groupData.deliveryPointOfService.address.country.name)}
            </c:if>
                <br />
                <c:if test="${ not empty groupData.deliveryPointOfService.address.phone }">
                ${fn:escapeXml(groupData.deliveryPointOfService.address.phone)}
            </c:if>
            </div>
        </div>
    </div>
</c:if>
