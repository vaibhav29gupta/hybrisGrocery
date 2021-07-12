<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="quantity" required="false" type="java.lang.Long"%>
<%@ attribute name="hasAddToCartComponent" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/responsive/action"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<spring:theme code="text.addToCart" var="addToCartText" />
<c:url value="${product.url}" var="productUrl" />
<c:set value="${not empty product.potentialPromotions}" var="hasPromotion" />
<c:set value="product-item" var="productTagClasses" />
<c:forEach var="tag" items="${product.tags}">
    <c:set value="${productTagClasses} tag-${tag}" var="productTagClasses" />
</c:forEach>
<div class="${fn:escapeXml(productTagClasses)}">
    <product:productEmphasis product="${product}" />
    <div class="border-pdp ${sessionScope.borderColor}">
        <a class="thumb" href="${fn:escapeXml(productUrl)}" title="${fn:escapeXml(product.name)}">
            <product:productPrimaryImage product="${product}" format="product" />
        </a>
        <div class="details">
            <div class="essential-details">
                <ycommerce:testId code="product_productName">
                    <a class="name anchor-as-heading" href="${fn:escapeXml(productUrl)}">
                        <c:out escapeXml="false" value="${ycommerce:sanitizeHTML(product.name)}" />
                    </a>
                </ycommerce:testId>
                <div class="brand">${fn:escapeXml(product.brand)}<c:if test="${not empty product.brand}">,</c:if>
                    <div class="weight">${fn:escapeXml(product.weight)}</div>
                    <c:if test="${not empty quantity}">
                        <div class="quantity">
                            Quantity Added:
                            <spring:theme code="${quantity}" />
                        </div>
                    </c:if>
                </div>
            </div>
            <ycommerce:testId code="product_productPrice">
                <product:productListerItemPrice product="${product}" />
            </ycommerce:testId>
            <c:forEach var="variantOption" items="${product.variantOptions}">
                <c:forEach items="${variantOption.variantOptionQualifiers}" var="variantOptionQualifier">
                    <c:if test="${variantOptionQualifier.qualifier eq 'rollupProperty'}">
                        <c:set var="rollupProperty" value="${variantOptionQualifier.value}" />
                    </c:if>
                    <c:if test="${variantOptionQualifier.qualifier eq 'thumbnail'}">
                        <c:set var="imageUrlHtml" value="${fn:escapeXml(variantOptionQualifier.value)}" />
                    </c:if>
                    <c:if test="${variantOptionQualifier.qualifier eq rollupProperty}">
                        <c:set var="variantNameHtml" value="${fn:escapeXml(variantOptionQualifier.value)}" />
                    </c:if>
                </c:forEach>
                <img style="width: 32px; height: 32px;" src="${imageUrlHtml}" title="${variantNameHtml}" alt="${variantNameHtml}" />
            </c:forEach>
        </div>
    </div>
</div>