<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:url value="${product.url}" var="productUrl" />
<div class="product-item-image">
    <a href="${fn:escapeXml(productUrl)}">
        <product:productPrimaryImage product="${product}" format="thumbnail" />
    </a>
</div>
<div class="product-item-details">
    <a href="${product.purchasable ? fn:escapeXml(productUrl) : ''}">
        <span class="product-item-name">${fn:escapeXml(product.name)}</span>
    </a>
    <div class="brand">${fn:escapeXml(product.brand)}<c:if test="${not empty product.weight}">,</c:if>
        <div class="weight">${fn:escapeXml(product.weight)}</div>
    </div>
    <div class="product-item-price">
        <product:productListerItemPrice product="${product}" />
    </div>
</div>
<div class="product-item-quantity addtocart-component">
    <product:productAddToCart product="${product}" quantity="${product.productCartQuantity}" />
</div>
