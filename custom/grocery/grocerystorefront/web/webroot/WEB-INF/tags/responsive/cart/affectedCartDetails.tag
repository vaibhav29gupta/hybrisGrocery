<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="emptyCartImageUrl" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="grid" tagdir="/WEB-INF/tags/responsive/grid"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order"%>
<%--
    Represents single cart item on side cart
 --%>
<spring:htmlEscape defaultHtmlEscape="true" />
<div class="affected-mini-cart mini-cart">
    <div class="mini-cart-body">
        <ol class="cart-layer-list mini-cart-list">
            <c:if test="${not empty cartData.entries }">
                <c:forEach items="${cartData.entries}" var="entry" end="${cartData.totalItems - 1}">
                    <c:url value="${entry.product.url}" var="entryProductUrl" />
                    <li class="cart-layer-item affected-item affected-item-${entry.product.code }">
                        <div class="cart-icon">
                            <a href="${entryProductUrl}">
                                <product:productPrimaryImage product="${entry.product}" format="thumbnail" />
                            </a>
                        </div>
                        <div class="cart-details">
                            <a class="name" href="${entryProductUrl}">${fn:escapeXml(entry.product.name)}</a>
                            <div class="cart-additional-details">
                                <div class="cart-brand">${fn:escapeXml(entry.product.brand)}<c:if test="${not empty entry.product.weight}">,
                                    </c:if>
                                </div>
                                    <div class="cart-weight">${fn:escapeXml(entry.product.weight)}</div>
                            </div>
                        </div>
                        <div class="cart-quantity">
                            <product:productAddToCart product="${entry.product}" quantity="${fn:escapeXml(entry.quantity)}" />
                        </div>
                        <div class="cart-price">
                            <format:price priceData="${entry.totalPrice}" />
                        </div>
                    </li>
                </c:forEach>
            </c:if>
        </ol>
    </div>
</div>
<div class="fixed-cart-footer">
    <div class="affected-items-check-label">
        <spring:theme code="affected.items.check.label" />
    </div>
    <div class="affected-items-check-actions">
        <button class="btn btn-default rounded cancel-shipment-change">
            <spring:theme text="Checkout" code="abort.mission" />
        </button>
        <button class="btn btn-primary rounded proceed-with-shipment-change">
            <spring:theme text="Proceed with Shipment Change" code="continue.with.change" />
        </button>
    </div>
</div>
