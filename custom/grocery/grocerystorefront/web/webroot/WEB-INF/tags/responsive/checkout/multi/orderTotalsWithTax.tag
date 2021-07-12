<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="showTax" required="false" type="java.lang.Boolean"%>
<%@ attribute name="showTaxEstimate" required="false" type="java.lang.Boolean"%>
<%@ attribute name="subtotalsCssClasses" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<div class="order-totals ${fn:escapeXml(subtotalsCssClasses)}">
    <div class="totals">
        <spring:theme code="basket.page.totals.total" />
        <multi-checkout:orderTotalPrice showTax="${showTax}" cartData="${cartData}" />
    </div>
   
    <c:if test="${not cartData.net}">
        <div class="realTotals">
            <ycommerce:testId code="cart_taxes_label">
                <p>
                    <spring:theme code="basket.page.totals.grossTax" arguments="${cartData.totalTax.formattedValue}" argumentSeparator="!!!!" />
                </p>
            </ycommerce:testId>
        </div>
    </c:if>
    <c:if test="${cartData.net && not showTax }">
        <div class="realTotals">
            <ycommerce:testId code="cart_taxes_label">
                <p>
                    <spring:theme code="basket.page.totals.noNetTax" />
                </p>
            </ycommerce:testId>
        </div>
    </c:if>
     <c:if test="${cartData.totalDiscounts.value > 0}">
		<div class="discounts">
        <div class="discount-label">
            <i class="fa fa-certificate"></i>
            <spring:theme code="text.account.order.discount" />
        </div>
        <span>
				<ycommerce:testId code="Order_Totals_Savings">
					<format:price priceData="${cartData.totalDiscounts}" displayNegationForDiscount="true" />
				</ycommerce:testId>
			</span>
		</div>
	</c:if> 
</div>
