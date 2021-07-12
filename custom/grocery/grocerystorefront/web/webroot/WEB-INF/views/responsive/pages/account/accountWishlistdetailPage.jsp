<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<spring:url value="/wishlist/all" var="wishlistURL" htmlEscape="false" />
<div class="back-link account-section-header border wishlist-header">
    <span class="account-header-left">
        <button type="button" class="addressBackBtn" data-back-to-addresses="${fn:escapeXml(wishlistURL)}">
            <span class="fa fa-chevron-circle-left"></span>
        </button>
        <span id="wishlistHeader" class="account-section-header-name label" data-wishlist-name="${wishlistName}">${wishlistName}</span>
    </span>
    <span class="account-header-right">
        <c:if test="${not empty userWishLists && userWishLists ne null }">
            <div id="wishlist-add-to-cart" class="btn btn-primary cartButton js-add-to-cart-btn" data-wishlist-name="${wishlistName }">
                <span id="spnAddToCart">
                    <spring:theme code="wishlist.add.all.to.cart" />
                    (<span class="cart-product-count">${fn:length(userWishLists)}</span>)
                </span>
            </div>
        </c:if>
    </span>
</div>
<div class="wishlist-success-message shownone"></div>
<div class="wishlist-error-message shownone"></div>
<input type="hidden" class="success-deleted" value="<spring:theme code="text.wishlist.success.delete.message"/>">
<input type="hidden" class="error-deleted" value="<spring:theme code="text.wishlist.error.delete.message"/>">
<input type="hidden" class="success-added" value="<spring:theme code="text.wishlist.success.addtoCarr.message"/>">
<input type="hidden" class="error-added" value="<spring:theme code="text.wishlist.error.addtoCarr.message"/>">
<input type="hidden" class="error-not-added" value="<spring:theme code="text.wishlist.error.productsNotAdded.message"/>">
<div class="account-wishlist-details account-list account-section-content" data-wishlist-name="${wishlistName}">
    <c:choose>
        <c:when test="${not empty userWishLists && userWishLists ne null }">
            <div class="account-cards card-select">
                <c:forEach items="${userWishLists}" var="wishlist" varStatus="loop">
                    <div class="wishlist-details card wishlist-details-card item__list affected-item-${fn:escapeXml(wishlist.product.code)}"
                        data-product-code="${wishlist.product.code}">
                        <product:groceryProductEntry product="${wishlist.product}" />
                        <div class="wishlist-action">
                            <div class="btn-icon btn-contrast">
                                <span class="fa fa-trash-o removeWishlist-details js-remove-product"></span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="account-cards card-select">
                <div class="card address-card">
                    <div class="account-section-content content-empty">
                        <spring:theme code="text.account.wishlist.no.product" />
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<!-- </div> -->