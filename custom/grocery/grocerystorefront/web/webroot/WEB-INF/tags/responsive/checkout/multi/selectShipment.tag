<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:forEach items="${checkoutSteps}" var="checkoutStep" varStatus="status">
    <c:if test="${checkoutStep.stepNumber < 2}">
        <c:url value="${checkoutStep.url}" var="firstStepurl" />
    </c:if>
</c:forEach>
<c:choose>
	<c:when test="${shipmentType eq 'DELIVERY'}">
    	<c:set var="selectedPickup" value="shownone" />
        <c:set var="chosenDelivery" value="selected" />
	</c:when>
    <c:otherwise>
        <c:set var="selectedDelivery" value="shownone" />
        <c:set var="chosenPickup" value="selected" />
	</c:otherwise>
</c:choose>
<a href="${firstStepurl}" class="step-head preferred-shipment-mode-checkout ${shipmentEnabled eq true ? 'mode-active' : 'mode-inactive'}">
    <c:choose>
        <c:when test="${shipmentEnabled eq true}">
            <div class="title">
                <spring:theme code="preferred.mode" />
            </div>
        </c:when>
        <c:otherwise>
            <div class="title">
                <c:choose>
                    <c:when test="${shipmentType eq 'DELIVERY'}">
                        <i class="fa fa-truck"></i>
                        <spring:theme code="preferred.shipmentmode.${shipmentType}" arguments="${postalCode}" />
                    </c:when>
                    <c:otherwise>
                        <i class="fa fa-shopping-basket"></i>
                        <spring:theme code="preferred.shipmentmode.${shipmentType}" arguments="${storeName}" />
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="edit">
                <spring:theme code="checkout.multi.deliveryAddress.edit" />
            </div>
        </c:otherwise>
    </c:choose>
</a>
<c:if test="${shipmentEnabled eq true}">
    <div class="step-body">
        <div class="switch-field checkout-shipmentmode">
            <div class="checkout-shipment-modes">
                <div class="checkout-shipment-mode choose-delivery ${chosenDelivery }">
                    <div class="shipment-mode">
                        <i class="fa fa-truck shipment-mode-image"></i>
                        <div class="shipment-mode-name">
                            <spring:theme code="shipmentmode.DELIVERY" />
                        </div>
                    </div>
                </div>
                <div class="checkout-shipment-mode choose-pickup ${chosenPickup}">
                    <div class="shipment-mode">
                        <i class="fa fa-shopping-basket shipment-mode-image"></i>
                        <div class="shipment-mode-name">
                            <spring:theme code="shipmentmode.PICKUP" />
                        </div>
                    </div> 
                </div>
            </div>
            
            <div class="delivery-option">
            	<div class="pickup-selection ${selectedPickup }">
	                <c:set var="defaultUrl" value="/store-pickup/pointOfServices" />
	                <c:url var="pickUpInStoreFormActionUrl" value="${empty actionUrl ? defaultUrl : actionUrl}" />
	                <div class="searchPOSForm-checkout clearfix">
	                    <c:url value="/shipment/setStoreID" var="submitStoreURL" />
	                    <c:url value="/shipment/isValidStore" var="validateStoreURL" />
	                    <c:url value="/shipment/findAffectedCartProducts" var="cartUpdateURL" />
	                    <spring:theme code="checkout.pickup.placeholder" var="checkoutPickupPlaceholderHtml" />
	                    <input class="form-control input-pickup" placeholder="${checkoutPickupPlaceholderHtml}" data-exactmatch="false" id="locationForSearch" name="locationQuery"
	                        data-findstoresurl="${pickUpInStoreFormActionUrl}" data-validateurl="${validateStoreURL}" data-submissionurl="${submitStoreURL}"
	                        data-cartupdateurl="${cartUpdateURL}" value="${storeName }" />
	                    <div class="submit-pickup search-for-pos btn-neutral fa fa-angle-down">
<%-- 	                        <spring:theme code="shipment.search" /> --%>
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
	               
	                <div class="error-message-submit-pickup shownone">
	                    <spring:theme code="shipment.pickup.error" />
	                </div>
	                <div class="success-message-submit-pickup shownone">
	                    <spring:theme code="shipment.pickup.success" />
	                </div>
	        	</div>
	            <div class="delivery-selection ${selectedDelivery }">
	                <spring:theme code="checkout.delivery.placeholder" var="checkoutDeliveryPlaceholderHtml" />
	                <c:url value="/shipment/setPostalCode" var="submitPostalCodeURL" />
	                <c:url value="/shipment/isValidPostalCode" var="validatePostalCodeURL" />
	                <c:url value="/shipment/findAffectedCartProducts" var="cartUpdateURL" />
	                <spring:theme code="shipment.delivery.subtext" />
	                <input type="text" class="form-control input-postal-code-checkout input-postalcode" data-url="${submitPostalCodeURL}" placeholder="${checkoutDeliveryPlaceholderHtml}"
	                    data-validateurl="${validatePostalCodeURL}" data-submissionurl="${submitPostalCodeURL}" data-cartupdateurl="${cartUpdateURL}" value="${postalCode }" />
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
        </div>
    </div>
</c:if>
