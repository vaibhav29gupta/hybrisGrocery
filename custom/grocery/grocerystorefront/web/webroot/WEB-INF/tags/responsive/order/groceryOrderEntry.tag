<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.AbstractOrderData"%>
<%@ attribute name="entry" required="true" type="de.hybris.platform.commercefacades.order.data.OrderEntryData"%>
<%@ attribute name="consignmentEntry" required="false" type="de.hybris.platform.commercefacades.order.data.ConsignmentEntryData"%>
<%@ attribute name="isSavedCart" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order"%>
<%--
    Represents single cart item on cart page
 --%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:set var="errorStatus" value="<%=de.hybris.platform.catalog.enums.ProductInfoStatus.valueOf(\"ERROR\")%>" />
<c:set var="entryNumberHtml" value="${fn:escapeXml(entry.entryNumber)}" />
<c:set var="productCodeHtml" value="${fn:escapeXml(entry.product.code)}" />
<c:set var="quantityHtml" value="${fn:escapeXml(entry.quantity)}" />
<c:if test="${not empty entry}">
    <c:url value="${entry.product.url}" var="productUrl" />
    <li class="item__list--item cart-item">
        <%-- product image --%>
        <div class="item__image cart-item-image">
            <a href="${fn:escapeXml(productUrl)}">
                <product:productPrimaryImage product="${entry.product}" format="thumbnail" />
            </a>
        </div>
        <%-- product name, code, promotions --%>
        <div class="item__info cart-item-details">
            <ycommerce:testId code="cart_product_name">
                <a href="${orderEntry.product.purchasable ? fn:escapeXml(productUrl) : ''}">
                    <span class="item__name cart-item-name">${fn:escapeXml(entry.product.name)}</span>
                </a>
            </ycommerce:testId>
            <div class="brand">${fn:escapeXml(entry.product.brand)}<c:if test="${not empty entry.product.weight}">,</c:if>
                <div class="weight">${fn:escapeXml(entry.product.weight)}</div>
            </div>
            <div class="item__code">${productCodeHtml}</div>
            <%-- availability --%>
            <div class="item__stock">
                <%-- price --%>
                <div class="item__price cart-item-price">
                    <span class="visible-xs visible-sm">
                        <spring:theme code="basket.page.itemPrice" />
                        :
                    </span>
                    <order:orderEntryPrice orderEntry="${entry}" />
                </div>
                <div class="item__total js-item-total cart-item-total-price-mobile shownone">
                    <span class="visible-xs visible-sm">
                        <spring:theme code="text.account.order.qty" />
                        :
                    </span>
                    <c:choose>
                        <c:when test="${consignmentEntry ne null }">
                                    ${fn:escapeXml(consignmentEntry.quantity)}
                                </c:when>
                        <c:otherwise>
                                    ${fn:escapeXml(entry.quantity)}
                                </c:otherwise>
                    </c:choose>
                </div>
                <div class="item__total js-item-total cart-item-total-price-mobile shownone">
                    <format:price priceData="${entry.totalPrice}" displayFreeForZero="true" />
                </div>
            </div>
            <c:if test="${not empty order.appliedProductPromotions}">
                <c:forEach items="${order.appliedProductPromotions}" var="promotion">
                    <c:set var="displayed" value="false" />
                    <c:forEach items="${promotion.consumedEntries}" var="consumedEntry">
                        <c:if test="${not displayed and consumedEntry.orderEntryNumber == orderEntry.entryNumber}">
                            <c:set var="displayed" value="true" />
                            <div class="applied-promo">
                                <i class="fa fa-certificate"></i>
                                <div class="promo">${ycommerce:sanitizeHTML(promotion.description)}</div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </c:if>
        </div>
        <%-- quantity --%>
        <div class="item__quantity cart-quantity cart-item-quantity cart-item-text-desktop">
        <spring:theme code="text.account.order.qty" /> :
            <c:choose>
                <c:when test="${consignmentEntry ne null }">
                                    ${fn:escapeXml(consignmentEntry.quantity)}
                                </c:when>
                <c:otherwise>
                                    ${fn:escapeXml(entry.quantity)}
                                </c:otherwise>
            </c:choose>
        </div>
        <c:if test="${not isSavedCart}">
            <div class="item__quantity cart-quantity cart-item-quantity">
                <product:productAddToCart product="${entry.product}" quantity="${quantityHtml}" />
            </div>
        </c:if>
        <%-- total --%>
        <ycommerce:testId code="cart_totalProductPrice_label">
            <div class="item__total js-item-total cart-item-total-price">
                <format:price priceData="${entry.totalPrice}" displayFreeForZero="true" />
            </div>
        </ycommerce:testId>
    </li>
</c:if>
