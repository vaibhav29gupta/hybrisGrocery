<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<spring:url value="/my-account/orders" var="orderHistoryUrl" htmlEscape="false"/>

<div class="row">
    <div class="reorder-button-group">
        <ycommerce:testId code="orderDetails_backToOrderHistory_button">
            <div class="orderBackBtn">
                <button type="button" class="btn btn-default btn-block" data-back-to-orders="${fn:escapeXml(orderHistoryUrl)}">
                    <spring:theme code="text.account.orderDetails.backToOrderHistory"/>
                </button>
                
            </div>
            <div>
           <%--  <button type="button" class="btn btn-default btn-block reorder-btn" data-reorder-code="${fn:escapeXml(orderData.code)}">Reorder
            </button> --%>
             <button type="button" class="btn btn-default btn-block reorder-btn" data-reorder-code="${fn:escapeXml(orderData.code)}">
				<spring:theme code="text.account.orderDetails.reorder.btn"/>
            </button>
            
            </div>
 </ycommerce:testId>
    </div>
    <div class="reorder-cart-content">
          
    
    
            </div>
            <div class="reorder-cart-error-msg hidden">
            <spring:theme code="text.cart.reorder.error.msg"/>
            </div>
</div>

