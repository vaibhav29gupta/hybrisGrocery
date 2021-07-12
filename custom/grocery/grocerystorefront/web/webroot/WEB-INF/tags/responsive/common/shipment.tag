<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="shipment-method">
    <c:choose>
        <c:when test="${empty shipmentType}">
            <div class="icon">
                <i class="fa fa-store" aria-hidden="true"></i>
            </div>
            <div class="shipment-text hidden-xs hidden-sm">
                <spring:theme code="shipment.choose.delivery" />
            </div>
        </c:when>
        <c:when test="${shipmentType eq 'DELIVERY'}">
            <div class="icon">
                <i class="fa fa-truck" aria-hidden="true"></i>
            </div>
            <div class="shipment-text hidden-xs hidden-sm">
                <div class="shipment-label">
                    <spring:theme code="shipment.delivery.chosen" />
                </div>
                <div class="shipment-location">${postalCode}</div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="icon">
                <i class="fa fa-shopping-basket" aria-hidden="true"></i>
            </div>
            <div class="shipment-text hidden-xs hidden-sm">
                <div class="shipment-label">
                    <spring:theme code="shipment.pickup.chosen" />
                </div>
                <div class="shipment-location">${storeName}</div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<div id="shipment-method-component" class="shownone">
    <cms:pageSlot position="ShipmentMethod" var="component">
        <cms:component component="${component}" />
    </cms:pageSlot>
</div>
<div class="deliveryCookieNotification shownone">
    <c:url value="/shipment/setPostalCode" var="submitPostalCodeURL" />
    <c:url value="/shipment/isValidPostalCode" var="validatePostalCodeURL" />
    <c:url value="/shipment/findAffectedCartProducts" var="cartUpdateURL" />
    <input id="deliveryCookie" class="hidden" data-url="${submitPostalCodeURL}" data-validateurl="${validatePostalCodeURL}" data-submissionurl="${submitPostalCodeURL}"
        data-cartupdateurl="${cartUpdateURL}" value="" />
    <label id="shipmentDeliveryText">
        <spring:theme code="account.cookie.notification.restore"></spring:theme>
        <input class="deliveryCookiePreferances" />
    </label>
    <div class="cookie-shipment-actions">
        <a id="setDeliveryCookieYes" class="btn-a">
            <spring:theme code="cookie.restore.yes"></spring:theme>
        </a>
        <a class="setUserCookieNo btn-ac">
            <spring:theme code="cookie.restore.no"></spring:theme>
        </a>
    </div>
</div>
<div class="pickupCookieNotification shownone">
    <c:url value="/shipment/setStoreID" var="submitStoreURL" />
    <c:url value="/shipment/isValidStore" var="validateStoreURL" />
    <c:url value="/shipment/findAffectedCartProducts" var="cartUpdateURL" />
    <input id="pickupCookie" class="hidden" data-findstoresurl="${pickUpInStoreFormActionUrl}" data-validateurl="${validateStoreURL}" data-submissionurl="${submitStoreURL}"
        data-cartupdateurl="${cartUpdateURL}" value="" />
    <label id="shipmentPickupText">
        <spring:theme code="account.cookie.notification.restore"></spring:theme>
        <input class="pickupCookiePreferances" />
    </label>
    <div class="cookie-shipment-actions">
        <a id="setPickupCookieYes" class="btn-a">
            <spring:theme code="cookie.restore.yes"></spring:theme>
        </a>
        <a class="setUserCookieNo btn-ac">
            <spring:theme code="cookie.restore.no"></spring:theme>
        </a>
    </div>
</div>
