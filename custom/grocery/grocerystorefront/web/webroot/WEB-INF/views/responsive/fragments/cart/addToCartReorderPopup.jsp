<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
 <%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<c:set var="productName" value="${fn:escapeXml(product.name)}" />


{"addToCartLayer":"<spring:escapeBody javaScriptEscape="true" htmlEscape="false">
	<spring:htmlEscape defaultHtmlEscape="true">
	<spring:theme code="text.addToCart" var="addToCartText"/>
	<c:url value="/cart" var="cartUrl"/>
	<ycommerce:testId code="addToCartPopup">
		<div id="addToCartLayer" class="add-to-cart">
            
                <c:if test="${errorMsg ne null and not empty errorMsg}">
                 <div class="cart_popup_reorder_error_msg">
                        <spring:theme code="${errorMsg}" />
                </div>
                </c:if>
                
                <c:if test="${successMsg ne null and not empty successMsg}">
               <div class="alert alert-info alert-dismissable getAccAlert margin-top-reorder-success">
                        <spring:theme code="${successMsg}" />
                </div>
                </c:if>
                <div class="carousel__component">
			<div class="carousel__component--headline">${fn:escapeXml(title)}</div>
                
                <c:if test="${cartModificationData ne null}"> 
                <div class="global-alerts">
		</br>
		 
		<div class="alert alert-danger alert-dismissable getAccAlert">
					 <spring:theme code="text.cart.reorder.addtocart.fail.msg"/></div>
			</div>
                
                
             
                <div class="carousel__component--carousel js-owl-productreoder js-owl-carousel js-owl-carousel-reference">
						<div id="quickViewTitle" class="quickView-header display-none">
							<div class="headline">
								<span class="headline-text"><spring:theme code="popup.quick.view.select"/></span>
							</div>
						</div>
						
                    <c:forEach items="${cartModificationData}" var="modification">
                     <c:set var="product" value="${modification.entry.product}" />
                    <product:miniProductListerGridItem product="${product}" quantity="${modification.quantityAdded}" hasAddToCartComponent="false" />
                              
                    </c:forEach>
                    </div>
               </c:if>
               </div> 
		</div>
	</ycommerce:testId>
	</spring:htmlEscape>
</spring:escapeBody>",

"cartLayer":"<spring:escapeBody javaScriptEscape="true" htmlEscape="false">
	<spring:htmlEscape defaultHtmlEscape="true"><cart:cartDetails cartData="${cartData }" emptyCartImageUrl="${emptyCartImage.url  }" /></spring:htmlEscape>
	</spring:escapeBody>"
}