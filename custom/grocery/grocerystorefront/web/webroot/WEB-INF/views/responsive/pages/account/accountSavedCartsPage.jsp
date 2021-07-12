<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<spring:url value="/my-account/saved-carts/" var="savedCartsLink" htmlEscape="false" />
<c:set var="searchUrl" value="/my-account/saved-carts?sort=${ycommerce:encodeUrl(searchPageData.pagination.sort)}" />
<div class="account-section-header ${empty searchPageData.results ? '':'no-border'}">
    <spring:theme code="text.account.savedCarts" />
</div>
<div class="account-savedcarts account-list account-section-content">
    <c:if test="${empty searchPageData.results}">
        <div class="account-cards card-select">
            <div class="card saved-cart-card">
                <div class="account-section-content content-empty">
                    <ycommerce:testId code="savedCarts_noOrders_label">
                        <spring:theme code="text.account.savedCarts.noSavedCarts" />
                    </ycommerce:testId>
                </div>
            </div>
        </div>
    </c:if>
    <div class="customer-saved-carts account-overview-table account-cards card-select">
        <c:if test="${not empty searchPageData.results}">
            <div class="account-pagination-header card saved-cart-card">
                <nav:pagination top="true" msgKey="text.account.savedCarts.page" showCurrentPageInfo="true" hideRefineButton="true" supportShowPaged="${isShowPageAllowed}"
                    supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchUrl}" numberPagesShown="${numberPagesShown}" />
            </div>
            <div class="account-listing card saved-cart-card mobile-cards">
                <div class="site-responsive-table">
                    <c:set var="cartIdRowMapping" value='' />
                    <div class="srt-heading">
                        <div class="srt-heading-cell srt-cell">
                            <spring:theme code="text.account.savedCart.name" />
                        </div>
                        <div class="srt-heading-cell srt-cell">
                            <spring:theme code="text.account.savedCart.dateSaved" />
                        </div>
                        <div class="srt-heading-cell srt-cell fcenter">
                            <spring:theme code="text.account.savedCart.qty" />
                        </div>
                        <div class="supportTicketsTableState srt-heading-cell srt-cell fright">
                            <spring:theme code="text.account.savedCart.total" />
                        </div>
                        <div class="srt-heading-cell srt-cell fcenter"></div>
                    </div>
                    <div class="srt-body">
                        <c:forEach items="${searchPageData.results}" var="savedCart">
                            <c:choose>
                                <c:when test="${savedCart.importStatus eq 'PROCESSING' }">
                                    <c:set var="importCartIsProcessing" value="true" />
                                    <c:set var="cartIdRowMapping" value="${cartIdRowMapping}${savedCart.code}:${loop.index}," />
                                </c:when>
                                <c:otherwise>
                                    <c:set var="importCartIsProcessing" value="false" />
                                </c:otherwise>
                            </c:choose>
                            <div class="srt-row" id="row-${loop.index}">
                                <div class="srt-row-cell srt-cell">
                                    <div class="srt-mobile-label">
                                        <spring:theme code="text.account.savedCart.name" />
                                    </div>
                                    <div class="break-word srt-value">
                                        <c:choose>
                                            <c:when test="${importCartIsProcessing}">
                                                <span class="file-importing js-file-importing">
                                                    <img src="${fn:escapeXml(commonResourcePath)}/images/3dots.gif" width="25" height="25" />
                                                </span>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${fn:escapeXml(savedCartsLink)}${ycommerce:encodeUrl(savedCart.code)}"
                                                    class="responsive-table-link js-saved-cart-name ${importCartIsProcessing ? 'not-active' : '' }">
                                                    ${fn:escapeXml(savedCart.name)} </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <div class="srt-row-cell srt-cell">
                                    <div class="srt-mobile-label">
                                        <spring:theme code="text.account.savedCart.dateSaved" />
                                    </div>
                                    <div class="srt-value js-saved-cart-date ${importCartIsProcessing ? 'hidden' : '' }">
                                        <ycommerce:testId code="savedCarts_created_label">
                                            <fmt:formatDate value="${savedCart.saveTime}" dateStyle="medium" type="date" />
                                        </ycommerce:testId>
                                    </div>
                                </div>
                                <div class="srt-row-cell srt-cell fcenter">
                                    <div class="srt-mobile-label">
                                        <spring:theme code="text.account.savedCart.qty" />
                                    </div>
                                    <div class="srt-value fcenter">
                                        <ycommerce:testId code="savedCarts_noOfItems_label">
                                            <span class="js-saved-cart-number-of-items">
                                                <c:if test="${importCartIsProcessing eq false}">
										${fn:length(savedCart.entries)}
									</c:if>
                                            </span>
                                        </ycommerce:testId>
                                    </div>
                                </div>
                                <div class="srt-row-cell srt-cell fright">
                                    <div class="srt-mobile-label">
                                        <spring:theme code="text.account.savedCart.total" />
                                    </div>
                                    <div class="srt-value fright">
                                        <ycommerce:testId code="savedCarts_totalProductPrice_label">
                                            <span class="js-saved-cart-total">
                                                <c:if test="${importCartIsProcessing eq false}">
											${fn:escapeXml(savedCart.totalPrice.formattedValue)}
										</c:if>
                                            </span>
                                        </ycommerce:testId>
                                    </div>
                                </div>
                                <div class="srt-row-cell srt-cell fcenter">
                                    <div class="srt-value fcenter saved-cart-actions">
                                        <ycommerce:testId code="savedCarts_restore_link">
                                            <a href="#"
                                                class=" btn btn-a js-restore-saved-cart ${importCartIsProcessing || fn:length(savedCart.entries) < 1 ? 'hidden' : '' }"
                                                data-savedcart-id="${fn:escapeXml(savedCart.code)}"
                                                data-restore-popup-title="<spring:theme code='text.account.savedcart.restore.popuptitle'/>">
                                                    <spring:theme code='text.account.savedCart.restore' />
                                            </a>
                                        </ycommerce:testId>
                                        <ycommerce:testId code="savedCarts_delete_link">
                                            <a href="#" class="btn btn-ac js-delete-saved-cart remove-item-link ${importCartIsProcessing ? 'hidden' : '' }"
                                                data-savedcart-id="${fn:escapeXml(savedCart.code)}"
                                                data-delete-popup-title="<spring:theme code='text.account.savedcart.delete.popuptitle'/>">
                                                <span class="fa fa-trash-o"></span>
                                            </a>
                                            <cart:savedCartDeleteModal savedCart="${savedCart}" />
                                        </ycommerce:testId>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="js-uploading-saved-carts-update" data-id-row-mapping="${fn:escapeXml(cartIdRowMapping)}" data-refresh-cart="${fn:escapeXml(refreshSavedCart)}"
                        data-refresh-interval="${fn:escapeXml(refreshSavedCartInterval)}"></div>
                </div>
            </div>
            <div class="account-pagination-header card saved-cart-card">
                <nav:pagination top="false" msgKey="text.account.savedCarts.page" showCurrentPageInfo="true" hideRefineButton="true" supportShowPaged="${isShowPageAllowed}"
                    supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchUrl}" numberPagesShown="${numberPagesShown}" />
            </div>
        </c:if>
    </div>
</div>
