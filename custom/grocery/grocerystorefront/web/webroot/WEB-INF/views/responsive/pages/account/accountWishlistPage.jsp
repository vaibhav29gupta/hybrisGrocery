<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="wishlist" tagdir="/WEB-INF/tags/responsive/wishlist"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<div class="wishlist-header account-section-header wishlist-fn">
    <span id="wishListHeader">
        <spring:theme code="text.account.wishlist.myWishlist" />
        (<span class="wishlist-count">${fn:length(userWishLists)}</span>)
    </span>
    <button type="button" class="btn btn-a wishlist-create-btn btn-icon">
        <spring:theme code="text.wishlist.create.button" />
    </button>
    <input type="hidden" class="create" value="<spring:theme code="text.wishlist.create.button" />">
    <input type="hidden" class="cancel" value="<spring:theme code="text.wishlist.cancel.button" />">
</div>
<div class="wishlist-success-message shownone"></div>
<div class="wishlist-error-message shownone"></div>
<input type="hidden" class="success-deleted" value="<spring:theme code="text.wishlist.success.delete.message"/>">
<input type="hidden" class="error-deleted" value="<spring:theme code="text.wishlist.error.delete.message"/>">
<input type="hidden" class="success-created" value="<spring:theme code="text.wishlist.success.create.message"/>">
<input type="hidden" class="error-created" value="<spring:theme code="text.wishlist.error.create.message"/>">
<input type="hidden" class="success-rename" value="<spring:theme code="text.wishlist.success.rename.message"/>">
<input type="hidden" class="error-rename" value="<spring:theme code="text.wishlist.error.rename.message"/>">
<div class="wishlist-pane create-wishlist-pane shownone ">
    <wishlist:createWishlist iconstyle="icon" createBtnName="${'js-create-page-wishlist-btn' }" />
</div>
<div class="account-wishlist account-list account-section-content">
    <c:choose>
        <c:when test="${not empty userWishLists && userWishLists ne null }">
            <div class="account-cards card-select">
                <c:forEach items="${userWishLists}" var="wishlist" varStatus="loop">
                    <div class="wishlist-card-main wishlist-card-main-${fn:escapeXml(loop.index)}">
                        <div class="card wishlist-card">
                            <div class="wishlist-data wishlist-pane">
                                <c:url var="wishlistDetail" value="/wishlist/wishlist-detail/${wishlist.name}" />
                                <c:url var="wishlistprefix" value="/wishlist/wishlist-detail/" />
                                <c:set var="WishlistEntries" value="${wishlist.entries }"></c:set>
                                <a class="wishlist-details wishlist-name-anchor-${fn:escapeXml(loop.index)}" href="${fn:escapeXml(wishlistDetail)}">
                                    <div class="wishlist-name-${fn:escapeXml(loop.index)} js-wishlist-name" data-wishlist-name="${wishlist.name }"
                                        data-wishlist-url-prefix="${wishlistprefix }">${wishlist.name }</div>
                                    <div class="wishlist-length">
                                        <div class="length-label">
                                            <spring:theme code="wishlist.length.label" />
                                        </div>
                                        <div class="length-value">${fn:length(wishlist.entries)}</div>
                                    </div>
                                </a>
                                <div class="rename-wishlist shownone js-rename-wishlist-${fn:escapeXml(loop.index)} shownone">
                                    <wishlist:createWishlist placeHolderOptional="${wishlist.name}" createBtnName="${'js-rename-page-wishlist-btn' }" />
                                </div>
<%--                                 <div class="close-rename close-rename-${fn:escapeXml(loop.index)} shownone">x</div> --%>
                                <div class="wishlist-action">
                                    <div class="btn-neutral btn-icon rename-wishlist-btn js-rename-wishlist">
                                        <i class="fa fa-pencil-alt"></i>
                                    </div>
                                    <div class="btn-contrast btn-icon js-remove-wishlist">
                                        <i class=" fa fa-trash"></i>
                                    </div>
                                    <div class="btn-primary btn-icon js-rename-page-wishlist-btn disabled-btn shownone">
                                        <i class=" fa fa-check"></i>
                                    </div>
                                    <div class="btn-contrast btn-icon close-rename shownone">
                                        <i class=" fa fa-times"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="wishlist-products carousel__component">
                                <div class="product-images carousel js-owl-wishlist js-owl-carousel">
                                    <c:forEach items="${wishlist.entries}" var="entry">
                                        <div class="product-image">
                                            <product:productPrimaryImage product="${entry.product}" format="thumbnail" />
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="account-cards card-select">
                <div class="card wishlist-card">
                    <div class="account-section-content content-empty">
                        <spring:theme code="text.account.wishlist.no.wishlists" />
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
