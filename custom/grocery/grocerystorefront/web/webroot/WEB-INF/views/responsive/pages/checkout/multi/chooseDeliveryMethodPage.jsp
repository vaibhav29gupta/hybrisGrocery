<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">
   <div class="checkout-headline">
      <spring:theme code="checkout.multi.secure.checkout" />
   </div>
   <div class="checkout-multi-step">
      <div id="left-hand-pane" class="checkout-left">
         <multi-checkout:checkoutSteps checkoutSteps="${checkoutSteps}" progressBarId="${progressBarId}">
            <jsp:body>
               <ycommerce:testId code="checkoutStepTwo">
                  <div class="checkout-shipping">
                     <multi-checkout:shipmentItems cartData="${cartData}" showDeliveryAddress="true" />
                     <spring:url var="selectDeliveryMethodUrl" value="{contextPath}/checkout/multi/delivery-method/select" htmlEscape="false">
                        <spring:param name="contextPath" value="${request.contextPath}" />
                     </spring:url>
                     <c:choose>
                        <c:when test="${shipmentType ne 'PICKUP'}">
                           <div class="headline">
                              <spring:theme code="checkout.summary.deliveryMode.selectDeliveryMethodForOrder" />
                           </div>
                           <c:choose>
                           <c:when test="${fn:length(deliveryMethods) > 0}">
                           <form id="selectDeliveryMethodForm" action="${fn:escapeXml(selectDeliveryMethodUrl)}" method="get">
                              <div class="form-group checkout-delivery-modes">
                                 <multi-checkout:deliveryMethodSelector deliveryMethods="${deliveryMethods}" selectedDeliveryMethodId="${cartData.deliveryMode.code}" />
                              </div>
                              <c:choose>
                                 <c:when test="${deliverySlotDate eq null or deliverySlotTime eq null}">
                                    <div class="empty-delivery-slots">
                                       <div class="no-slots-available">
                                          <spring:theme code="checkout.summary.deliverySlot.notavailable" />
                                       </div>
                                       <div class="expected-delivery-time">
                                          <spring:theme code="checkout.summary.deliveryMode.selectDeliverySlotsForOrderDefault" />
                                       </div>
                                    </div>
                                 </c:when>
                                 <c:otherwise>
                                    <div class="headline-delivery-slots">
                                       <spring:theme code="checkout.summary.deliveryMode.selectDeliverySlotsForOrder" />
                                    </div>
                                    <div class="form-group">
                                       <multi-checkout:deliverySlotDateSelector deliverySlotDate="${deliverySlotDate}" />
                                       <multi-checkout:deliverySlotTimeSelector deliverySlotTime="${deliverySlotTime}" />
                                    </div>
                                 </c:otherwise>
                              </c:choose>
                           </form>
                           </c:when>
                           <c:otherwise>
                           <div class="empty-delivery-slots">
                                       <div class="no-slots-available">
                                          <spring:theme code="checkout.summary.deliverySlot.notavailable" />
                                       </div>
                                    </div>
                           </c:otherwise>
                           </c:choose>
                        </c:when>
                        <c:otherwise>
                           <form id="selectDeliveryMethodForm" action="${fn:escapeXml(selectDeliveryMethodUrl)}" method="get">
                              <c:choose>
                                 <c:when test="${deliverySlotDate eq null or deliverySlotTime eq null}">
                                    <div class="empty-delivery-slots">
                                       <div class="no-slots-available">
                                          <spring:theme code="checkout.summary.deliverySlot.notavailable" />
                                       </div>
                                       <div class="expected-delivery-time">
                                          <spring:theme code="checkout.summary.deliveryMode.selectPickupSlotsForOrderDefault" />
                                       </div>
                                    </div>
                                 </c:when>
                                 <c:otherwise>
                                    <div class="headline-delivery-slots">
                                       <spring:theme code="checkout.summary.deliveryMode.selectPickupSlotsForOrder" />
                                    </div>
                                    <div class="form-group">
                                       <multi-checkout:deliverySlotDateSelector deliverySlotDate="${deliverySlotDate}" />
                                       <multi-checkout:deliverySlotTimeSelector deliverySlotTime="${deliverySlotTime}" />
                                    </div>
                                 </c:otherwise>
                              </c:choose>
                           </form>
                        </c:otherwise>
                     </c:choose>
                     <%--                                     <spring:theme var="deliveryMethodMessageHtml" code="checkout.multi.deliveryMethod.message" htmlEscape="false" /> --%>
                     <%--                             <p>${ycommerce:sanitizeHTML(deliveryMethodMessageHtml)}</p> --%>
                  </div>
                  <button id="${((shipmentType eq 'PICKUP') or (shipmentType ne 'PICKUP' and fn:length(deliveryMethods) > 0)) ? 'deliveryMethodSubmit' : ''}" type="button" class="btn btn-primary btn-block checkout-next ${((shipmentType eq 'PICKUP') or (shipmentType ne 'PICKUP' and fn:length(deliveryMethods) > 0)) ? '' : 'disabled'}">
                     <spring:theme code="checkout.multi.deliveryMethod.continue" />
                  </button>
               </ycommerce:testId>
            </jsp:body>
         </multi-checkout:checkoutSteps>
      </div>
      <div id="right-hand-pane" class="checkout-right">
         <multi-checkout:checkoutOrderDetails cartData="${cartData}" showDeliveryAddress="true" showPaymentInfo="false" showTaxEstimate="false" showTax="true" />
         <div class="contact-us-checkout">
            <cms:pageSlot position="SideContent" var="feature" element="div" class="checkout-help">
               <cms:component component="${feature}" />
            </cms:pageSlot>
         </div>
      </div>
   </div>
</template:page>