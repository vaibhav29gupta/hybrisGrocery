<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<div class="emphasis">
    <c:set var="borderColor" value="primary" scope="session" />
    <c:if test="${product.isServiceable and product.purchasable}">
        <c:choose>
            <c:when test="${not empty product.potentialPromotions and (not empty product.potentialPromotions[0].couldFireMessages or not empty product.potentialPromotions[0].description)}">
                <c:choose>
                    <c:when test="${not empty product.potentialPromotions[0].couldFireMessages}">
                        <c:set var="borderColor" value="positive" scope="session" />
                        <div class="promotion">${ycommerce:sanitizeHTML(product.potentialPromotions[0].couldFireMessages[0])}</div>
                    </c:when>
                    <c:when test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].description}">
                        <c:set var="borderColor" value="positive" scope="session" />
                        <div class="promotion">${ycommerce:sanitizeHTML(product.potentialPromotions[0].description)}</div>
                    </c:when>
                </c:choose>
            </c:when>
            <c:when test="${product.stock.stockLevelStatus.code eq 'lowStock' and not isForceInStock}">
                <c:set var="borderColor" value="urgency" scope="session" />
                <c:set var="productStockLevelHtml">
                    <spring:theme code="product.variants.only.left" arguments="${product.stock.stockLevel}" />
                </c:set>
                <div class="stock-wrapper clearfix">${productStockLevelHtml}</div>
            </c:when>
            <c:when test="${product.discounted}">
                <c:set var="borderColor" value="positive" scope="session" />
                <div class="discounted">
                    <spring:theme code="product.discounted" />
                </div>
            </c:when>
        </c:choose>
    </c:if>
</div>