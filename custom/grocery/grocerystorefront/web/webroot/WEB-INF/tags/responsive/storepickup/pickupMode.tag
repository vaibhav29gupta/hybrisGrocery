<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ attribute name="shipmentType" required="true" type="java.lang.String"%>
<%@ attribute name="storeValue" required="true" type="java.lang.String"%>
<%@ attribute name="storeID" required="true" type="java.lang.String"%>
<%@ attribute name="postalCode" required="true" type="java.lang.String"%>
<%@ attribute name="isCheckoutPage" required="true" type="java.lang.Boolean"%>
<div class="radiobutton preferred-shipment-mode-register-profile shipment-pref shipment-pref-options" data-shipment-type="${shipmentType }">
    <label class="control-label shipment-pref-heading">
        <spring:theme code="shipmentmode.selection.heading" />
    </label>
    <div class="shipment-pref-options">
        <div class="control-label">
            <form:radiobutton id="DECIDELATER" path="preferredShipmentMode" name="radioButton" class="preferredShipment" checked="${empty shipmentType  ? 'checked' : ' ' }" value="DECIDELATER" />
            <label for="DECIDELATER"><spring:theme code="shipmentmode.default" /></label>
            <div class="check"></div>
        </div>
        <div class="control-label">
            <form:radiobutton id="PICKUP" path="preferredShipmentMode" name="radioButton" class="preferredShipment" checked="${shipmentType eq 'PICKUP' ? 'checked' : ' ' }" value="PICKUP" />
            <label for="PICKUP"><spring:theme code="shipmentmode.pickup" /></label>
            <div class="check"></div>
        </div>
        <div class="control-label">
            <form:radiobutton id="DELIVERY" path="preferredShipmentMode" name="radioButton" class="preferredShipment" checked="${shipmentType eq  'DELIVERY' ? 'checked' : ' ' }"
                value="DELIVERY" />
            <label for="DELIVERY"><spring:theme code="shipmentmode.delivery" /></label>
            <div class="check"></div>
        </div>
    </div>
    <div class="delivery-option PICKUP">
        <div class="pickup-selection">
            <c:set var="defaultUrl" value="/store-pickup/pointOfServices" />
            <c:url var="pickUpInStoreFormActionUrl" value="${empty actionUrl ? defaultUrl : actionUrl}" />
            <div class="searchPOSForm-customer clearfix">
                <c:url value="/shipment/setStoreID" var="submitStoreURL" />
                <c:url value="/shipment/isValidStore" var="validateStoreURL" />
                <c:url value="/shipment/findAffectedCartProducts" var="cartUpdateURL" />
                <input class="form-control input-pickup" placeholder="Enter pickup store" data-exactmatch="false" id="locationForSearch" name="locationQuery"
                    data-findstoresurl="${pickUpInStoreFormActionUrl}" data-validateurl="${validateStoreURL}" data-submissionurl="${submitStoreURL}"
                    data-cartupdateurl="${cartUpdateURL}" value="${storeValue }" />
                <form:hidden path="preferredPos" id="pickup-form-input" value="${storeID }" />
                <div class="submit-pickup search-for-pos btn-primary fa fa-angle-down">
<%--                     <spring:theme code="shipment.search" /> --%>
                </div>
                <div class="store-navigation shownone">
                    <ul class="pickup-store-list js-pickup-store-list">
                    </ul>
                </div>
                <div class="error-message-submit-pickup shownone">
                    <spring:theme code="text.no.store" />
                </div>
                <div class="noStoreFound" data-message="<spring:theme code="text.no.store" />"></div>
                <div class="curbExternal" data-message="*<spring:theme code="text.is.external" />"></div>
                <div class="self-pos" data-message="*<spring:theme code="text.is.not.external" />"></div>
            </div>
            <%-- 				<pickupinstore:pickupStoreSearch /> --%>
            <div class="error-message-submit-pickup shownone">
                <spring:theme code="shipment.pickup.error" />
            </div>
            <div class="success-message-submit-pickup shownone">
                <spring:theme code="shipment.pickup.success" />
            </div>
        </div>
    </div>
    <div class="delivery-option DELIVERY delivery-selection">
        <c:url value="/shipment/setPostalCode" var="submitPostalCodeURL" />
        <c:url value="/shipment/isValidPostalCode" var="validatePostalCodeURL" />
        <c:url value="/shipment/findAffectedCartProducts" var="cartUpdateURL" />
        <form:input path="preferredPostalCode" class="form-control input-postal-code-customer customerDeliverForm" data-url="${submitPostalCodeURL}"
            placeholder="Enter postcode" data-validateurl="${validatePostalCodeURL}" data-submissionurl="${submitPostalCodeURL}" data-cartupdateurl="${cartUpdateURL}"
            value="${postalCode }" />
        <div class="error-message-submit-delivery shownone">
            <spring:theme code="shipment.delivery.error" />
        </div>
        <div class="success-message-submit-delivery shownone">
            <spring:theme code="shipment.delivery.success" />
        </div>
    </div>
</div>
