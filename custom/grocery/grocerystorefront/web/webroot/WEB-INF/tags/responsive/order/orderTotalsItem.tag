<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.AbstractOrderData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ attribute name="containerCSS" required="false" type="java.lang.String" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="orderTotal">
	<div class="order-item-card">
        <div class="order-item-label">
            <spring:theme code="text.account.order.subtotal"/>
        </div>
        <div class="order-item-value">
            <div class="text-right">
                <ycommerce:testId code="orderTotal_subTotal_label">
                    <format:price priceData="${order.subTotal}"/>
                </ycommerce:testId>
            </div>
        </div>
     </div>
     
     <c:if test="${order.totalDiscounts.value > 0}">
     	<div class="order-item-card">
            <div class="order-items-label">
                <div class="subtotals__item--state-discount">
                    <spring:theme code="text.account.order.discount"/>
                </div>
            </div>
            <div class="order-items-value">
                <div class="text-right subtotals__item--state-discount">
                    <ycommerce:testId code="orderTotal_discount_label">
                        <format:price priceData="${order.totalDiscounts}" displayNegationForDiscount="true" />
                    </ycommerce:testId>
                </div>
            </div>
         </div>
      </c:if>
      
      <c:if test="${order.quoteDiscounts.value > 0}">
      	<div class="order-item-card">
            <div class="order-items-label cart-totals-left discount">
                <spring:theme code="basket.page.quote.discounts" />
            </div>
            <div class="order-items-value cart-totals-right text-right discount">
                <ycommerce:testId code="Quote_Quote_Savings">
                    <format:price priceData="${order.quoteDiscounts}" displayNegationForDiscount="true" />
                </ycommerce:testId>
            </div>
         </div>
      </c:if>
      
      <div class="order-item-card">
        <div class="order-items-label">
            <spring:theme code="text.account.order.shipping"/>
        </div>
        <div class="order-items-value">
            <div class="text-right">
                <ycommerce:testId code="orderTotal_devlieryCost_label">
                    <format:price priceData="${order.deliveryCost}" displayFreeForZero="true"/>
                </ycommerce:testId>
            </div>
        </div>
	</div>
	
    <c:if test="${order.net}">
    	<div class="order-item-card">
            <div class="order-items-label">
                <spring:theme code="text.account.order.netTax"/>
            </div>
            <div class="order-items-value">
                <div class="text-right">
                    <format:price priceData="${order.totalTax}"/>
                </div>
            </div>
       	</div>
    </c:if>

	<div class="order-item-card">
        <div class="order-items-label">
            <div class="totals">
                <spring:theme code="text.account.order.orderTotals"/>
            </div>
        </div>

        <c:choose>
            <c:when test="${order.net}">
                <div class="order-items-label">
                    <div class="text-right totals">
                        <format:price priceData="${order.totalPriceWithTax}"/>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="order-items-value text-right">
                    <div class="totals">
                        <ycommerce:testId code="orderTotal_totalPrice_label">
                            <format:price priceData="${order.totalPrice}"/>
                        </ycommerce:testId>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
       </div>
</div>

<div class="account-orderdetail-orderTotalDiscount-section">
    <c:if test="${not order.net}">
        <div class="order-total__taxes">
            <ycommerce:testId code="orderTotal_includesTax_label">
                <spring:theme code="text.account.order.total.includesTax" argumentSeparator=";"
                              arguments="${order.totalTax.formattedValue}"/>
            </ycommerce:testId>
        </div>
        <br/>
    </c:if>
    <c:if test="${order.totalDiscountsWithQuoteDiscounts.value > 0}">
    
        <ycommerce:testId code="order_totalDiscount_label">
            <div class="order-savings__info text-right">
            <c:set var="totalDiscountWithQuoteDiscount">
            	<format:price priceData="${order.totalDiscountsWithQuoteDiscounts}"/>
				</c:set>
				
                <spring:theme code="text.account.order.totalSavings" argumentSeparator=";"
                              arguments="${ycommerce:sanitizeHTML(totalDiscountWithQuoteDiscount)}"/>
            </div>
        </ycommerce:testId>
    </c:if>
</div>