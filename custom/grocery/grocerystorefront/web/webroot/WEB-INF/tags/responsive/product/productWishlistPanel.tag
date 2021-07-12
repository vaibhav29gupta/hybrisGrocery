<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="isOrderForm" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:url value="/p/${product.code}" var="productUrl" />
<c:url value="/wishlist/product/add" var="addToWishlistUrl" />
<c:url value="/wishlist/product/remove" var="removeFromWishlistUrl" />
<c:url value="/login" var="loginUrl" />
<c:choose>
    <c:when test="${not product.isProductPresentInWishlist}">
        <c:set var="hiddenAddToWishlist" value="shownone" />
    </c:when>
    <c:otherwise>
        <c:set var="hiddenRemoveFromWishlist" value="shownone" />
    </c:otherwise>
</c:choose>
<div class="js-attached-dynamic-popup-${product.code} attached-dynamic-popup"></div>
<div class="favorite js-add-to-wishlist add-wishlist-${product.code}" data-productcode="${product.code}" data-login-url="${loginUrl}">
    <i class="list-icon fa fa-list-ol"></i>
    <span class="wishlistText">
        <spring:theme code="add.to.list" />
    </span>
</div>