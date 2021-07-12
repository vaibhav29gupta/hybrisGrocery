<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="showDeliveryAddress" required="true" type="java.lang.Boolean"%>
<%@ attribute name="showPotentialPromotions" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="grid" tagdir="/WEB-INF/tags/responsive/grid"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:set var="hasShippedItems" value="true" />
<c:set var="deliveryAddress" value="${cartData.deliveryAddress}" />
<c:if test="${showDeliveryAddress and not empty deliveryAddress}">
    <div class="delivery-details">
        <div class="checkout-order-summary-list">
            <div class="checkout-order-summary-list-heading">
                <spring:theme code="checkout.pickup.items.to.be.shipped" />
            </div>
            <div class="address">
                <c:if test="${ not empty deliveryAddress.title }"> ${fn:escapeXml(deliveryAddress.title)}&nbsp;</c:if>
                ${fn:escapeXml(deliveryAddress.firstName)}&nbsp;${fn:escapeXml(deliveryAddress.lastName)}
                <br>
                <c:if test="${ not empty deliveryAddress.line1 }">
						${fn:escapeXml(deliveryAddress.line1)},&nbsp;
					</c:if>
                <c:if test="${ not empty deliveryAddress.line2 }">
						${fn:escapeXml(deliveryAddress.line2)},&nbsp;
					</c:if>
                <c:if test="${not empty deliveryAddress.town }">
						${fn:escapeXml(deliveryAddress.town)},&nbsp;
					</c:if>
                <c:if test="${ not empty deliveryAddress.region.name }">
						${fn:escapeXml(deliveryAddress.region.name)},&nbsp;
					</c:if>
                <br>
                <c:if test="${ not empty deliveryAddress.postalCode }">
						${fn:escapeXml(deliveryAddress.postalCode)},&nbsp;
					</c:if>
                <c:if test="${ not empty deliveryAddress.country.name }">
						${fn:escapeXml(deliveryAddress.country.name)}
					</c:if>
                <br />
                <c:if test="${ not empty deliveryAddress.phone }">
						${fn:escapeXml(deliveryAddress.phone)}
					</c:if>
            </div>
        </div>
    </div>
</c:if>
