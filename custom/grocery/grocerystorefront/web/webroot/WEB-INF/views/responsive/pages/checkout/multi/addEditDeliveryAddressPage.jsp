<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/responsive/address"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">
   <div class="checkout-headline">
      <spring:theme code="checkout.multi.secure.checkout" />
   </div>
   <div class="checkout-multi-step">
      <div id="left-hand-pane" class="checkout-left">
         <multi-checkout:checkoutSteps checkoutSteps="${checkoutSteps}" progressBarId="${progressBarId}">
            <jsp:body>
               <c:if test="${shipmentType eq 'DELIVERY'}">
                  <ycommerce:testId code="checkoutStepOne">
                     <div class="checkout-shipping">
                        <multi-checkout:shipmentItems cartData="${cartData}" showDeliveryAddress="false" />
                        <div id="shipment-type-change-notification" class="shipment-type-change-notification hidden">
                           <label>
                              <spring:theme code="change.shipment.address" />
                           </label>
                           <div class="shipment-change-user-response">
                              <c:url var="changeshipment" value="/checkout/multi/cart-review/show" />
                              <button class="btn btn-small btn-primary" id="shipment-changed-yes-btn" data-changeshipment="${changeshipment}">
                                <spring:theme code="button.text.yes" />
                              </button>
                              <button class="btn btn-small btn-neutral" id="shipment-changed-no-btn">
                               <spring:theme code="button.text.no" />
                              </button>
                           </div>
                        </div>
                        <div class="delivery-address-header">
                           <div class="headline">
                              <spring:theme code="checkout.summary.shippingAddress" />
                           </div>
                           <c:if test="${not empty deliveryAddresses}">
                              <button type="button" class="btn btn-default btn-block js-address-book">
                                 <spring:theme code="checkout.checkout.multi.deliveryAddress.viewAddressBook" />
                              </button>
                           </c:if>
                        </div>
                        <div id="addressbook">
                           <spring:url var="selectDeliveryAddressUrl" value="{contextPath}/checkout/multi/delivery-address/select" htmlEscape="false">
                              <spring:param name="contextPath" value="${request.contextPath}" />
                           </spring:url>
                           <c:forEach items="${deliveryAddresses}" var="deliveryAddress" varStatus="status">
                              <div class="addressEntry">
                                 <form action="${fn:escapeXml(selectDeliveryAddressUrl)}" method="GET">
                                    <input type="hidden" name="selectedAddressCode" value="${fn:escapeXml(deliveryAddress.id)}" />
                                    <ul>
                                       <li>
                                          <strong>
                                             <c:if test="${ not empty deliveryAddress.title }"> ${fn:escapeXml(deliveryAddress.title)}&nbsp;</c:if>
                                             ${fn:escapeXml(deliveryAddress.firstName)}&nbsp;
                                             ${fn:escapeXml(deliveryAddress.lastName)}
                                          </strong>
                                          <br>
                                          ${fn:escapeXml(deliveryAddress.line1)}&nbsp;
                                          ${fn:escapeXml(deliveryAddress.line2)}
                                          <br>
                                          ${fn:escapeXml(deliveryAddress.town)}
                                          <c:if test="${not empty deliveryAddress.region.name}">
                                             &nbsp;${fn:escapeXml(deliveryAddress.region.name)}
                                          </c:if>
                                          <br>
                                          ${fn:escapeXml(deliveryAddress.country.name)}&nbsp;
                                          ${fn:escapeXml(deliveryAddress.postalCode)}
                                       </li>
                                    </ul>
                                    <c:if test="${deliveryAddress.postalCode eq sessionPostalCode }">
                                       <button type="submit" class="btn btn-primary btn-block">
                                          <spring:theme code="checkout.multi.deliveryAddress.useThisAddress" />
                                       </button>
                                    </c:if>
                                    <c:if test="${deliveryAddress.postalCode ne sessionPostalCode }">
                                       <button type="submit" class="btn btn-primary btn-block" disabled="disabled">
                                          <spring:theme code="checkout.multi.deliveryAddress.useThisAddress" />
                                       </button>
                                       <spring:theme code="checkout.multi.deliveryAddress.notforpostalcode" />
                                    </c:if>
                                 </form>
                              </div>
                           </c:forEach>
                        </div>
                        <div class="delivery-address">
                           <address:checkoutAddressFormSelector supportedCountries="${countries}" regions="${regions}" cancelUrl="${currentStepUrl}"
                              country="${country}" />
                        </div>
                        <address:suggestedAddresses selectedAddressUrl="/checkout/multi/delivery-address/select" />
                        <multi-checkout:pickupGroups cartData="${cartData}" />
                     </div>
                     <button id="addressSubmit" type="button" class="btn btn-primary btn-block checkout-next">
                        <spring:theme code="checkout.multi.deliveryAddress.continue" />
                     </button>
                  </ycommerce:testId>
               </c:if>
            </jsp:body>
         </multi-checkout:checkoutSteps>
      </div>
      <div id="right-hand-pane" class="checkout-right">
         <multi-checkout:checkoutOrderDetails cartData="${cartData}" showDeliveryAddress="false" showPaymentInfo="false" showTaxEstimate="false" showTax="true" />
         <div class="contact-us-checkout">
            <cms:pageSlot position="SideContent" var="feature" element="div" class="checkout-help">
               <cms:component component="${feature}" />
            </cms:pageSlot>
         </div>
      </div>
   </div>
</template:page>