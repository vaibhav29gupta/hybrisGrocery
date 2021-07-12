<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="pickupinstore" tagdir="/WEB-INF/tags/responsive/pickupinstore"%>
<c:url value="/" var="homePageUrl" />
<div class="shipment-selector-box js-full-pageheight">
    <div id="cboxClose" class="cboxClose">
        <i class="fa fa-times"></i>
    </div>
    <c:choose>
        <c:when test="${empty shipmentType}">
            <c:set var="selectedDelivery" value="shownone" />
            <c:set var="selectedPickup" value="shownone" />
            <c:set var="showcart" value="shownone" />
        </c:when>
        <c:when test="${shipmentType eq 'DELIVERY'}">
            <c:set var="chooseShipment" value="shownone" />
            <c:set var="selectedPickup" value="shownone" />
            <c:set var="chosenDelivery" value="selected" />
        </c:when>
        <c:otherwise>
            <c:set var="chooseShipment" value="shownone" />
            <c:set var="selectedDelivery" value="shownone" />
            <c:set var="chosenPickup" value="selected" />
            <c:set var="placeholderStoreID" value="${storeName}" />
        </c:otherwise>
    </c:choose>
    <!-- STEP 1: Shipment Selection -->
    <div class="choose-shipment ${chooseShipment}">
        <div class="heading">
            <spring:theme code="shipment.selection.heading" />
        </div>
        <div class="sub-heading">
            <spring:theme code="shipment.selection.subheading" />
        </div>
        <div class="error-container"></div>
        <div class="shipment-options">
            <div class="choose-option choose-pickup ${chosenPickup}">
                <div class="icon">
                    <i class="fa fa-shopping-basket" aria-hidden="true"></i>
                </div>
                <div class="shipment-text">
                    <spring:theme code="shipment.pickup" />
                </div>
            </div>
            <div class="choose-option choose-delivery ${chosenDelivery}">
                <div class="icon">
                    <i class="fa fa-truck" aria-hidden="true"></i>
                </div>
                <div class="shipment-text">
                    <spring:theme code="shipment.delivery" />
                </div>
            </div>
        </div>
        <div class="shipment-section">
            <div class="pickup-selection ${selectedPickup}">
                <div class="sub-text">
                    <spring:theme code="shipment.pickup.subtext" />
                </div>
                <c:set var="defaultUrl" value="/store-pickup/pointOfServices" />
                <c:url var="pickUpInStoreFormActionUrl" value="${empty actionUrl ? defaultUrl : actionUrl}" />
                <div class="searchPOSForm clearfix">
                    <c:url value="/shipment/setStoreID" var="submitStoreURL" />
                    <c:url value="/shipment/isValidStore" var="validateStoreURL" />
                    <c:url value="/shipment/findAffectedCartProducts" var="cartUpdateURL" />
                    <spring:theme code="text.initial.placeholder.text.of.POS" var="inputPlaceholderTextPOS" />
                    <input class="form-control input-pickup" placeholder="${inputPlaceholderTextPOS}" data-exactmatch="false" id="locationForSearch" name="locationQuery"
                        data-findstoresurl="${pickUpInStoreFormActionUrl}" data-validateurl="${validateStoreURL}" data-submissionurl="${submitStoreURL}"
                        data-cartupdateurl="${cartUpdateURL}"  value="${storeName }"/>
                    <div class="submit-pickup fa fa-angle-down search-for-pos">
<%--                         <spring:theme code="shipment.search" /> --%>
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
            <div class="delivery-selection ${selectedDelivery}">
                <div class="sub-text">
                    <spring:theme code="shipment.delivery.subtext" />
                </div>
                <c:url value="/shipment/setPostalCode" var="submitPostalCodeURL" />
                <c:url value="/shipment/isValidPostalCode" var="validatePostalCodeURL" />
                <c:url value="/shipment/findAffectedCartProducts" var="cartUpdateURL" />
                <input class="form-control input-postalcode" value="${postalCode}" data-exactmatch="false" data-validateurl="${validatePostalCodeURL}"
                    data-submissionurl="${submitPostalCodeURL}" data-cartupdateurl="${cartUpdateURL}" />
                <div class="error-message-submit-delivery shownone">
                    <spring:theme code="shipment.delivery.error" />
                </div>
                <div class="success-message-submit-delivery shownone">
                    <spring:theme code="shipment.delivery.success" />
                </div>
                <div class="submit-delivery btn-primary disable-action">
                    <spring:theme code="shipment.confirm" />
                </div>
            </div>
        </div>
        <sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')">
            <div class="prompt-login-footer">
                <c:url value="/login" var="loginUrl" />
                <a href="${fn:escapeXml(loginUrl)}">
                    <spring:theme code="shipment.login.highlight" />
                    <font class="anchor-as-text">
                        <spring:theme code="shipment.login.prompt.message" />
                    </font>
                </a>
            </div>
        </sec:authorize>
    </div>
    <!-- STEP 2: Cart Screen -->
    <div class="cart-screen ${showcart}">
        <div class="cart-heading">
            <spring:theme code="basket.page.title.yourItems" />
        </div>
        <div class="affected-products-heading shownone">
            <spring:theme code="affected.products.heading" />
        </div>
        <div class="selected-delivery ${selectedDelivery}">
            <!-- 		<div class="icon"> -->
            <!-- 			<i class="fa fa-truck" aria-hidden="true"></i> -->
            <!-- 		</div> -->
            <div class="shipment-text">
                <spring:theme code="shipment.delivery.chosen" />
            </div>
            <div class="shipment-text-postcode">${postalCode}</div>
            <div class="change-shipment btn-a">
                <spring:theme code="shipment.change" />
            </div>
        </div>
        <div class="selected-pickup ${selectedPickup}">
            <!-- 	<div class="icon"> -->
            <!-- 			<i class="fa fa-shopping-basket" aria-hidden="true"></i> -->
            <!-- 		</div> -->
            <div class="shipment-text">
                <spring:theme code="shipment.pickup.chosen" />
            </div>
            <div class="shipment-text-postcode">${storeName}</div>
            <div class="change-shipment btn-a">
                <spring:theme code="shipment.change" />
            </div>
        </div>
        <div class="cart-image-url" data-emptyCartImageUrl="${emptyCartImage.url }"></div>
        <div class="latest-cart">
            <cart:cartDetails cartData="${cartData }" emptyCartImageUrl="${emptyCartImage.url  }" />
        </div>
        <div class="affected-cart shownone">
            <cart:affectedCartDetails cartData="${cartData }" emptyCartImageUrl="${emptyCartImage.url  }" />
        </div>
    </div>
</div>
