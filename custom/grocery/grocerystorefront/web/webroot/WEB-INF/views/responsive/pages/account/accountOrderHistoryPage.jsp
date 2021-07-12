<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:set var="searchUrl" value="/my-account/orders?sort=${ycommerce:encodeUrl(searchPageData.pagination.sort)}" />
<div class="account-section-header ${empty searchPageData.results ? '':'no-border'}">
    <spring:theme code="text.account.orderHistory" />
</div>
<div class="account-orderhistory account-list account-section-content">
   <c:if test="${empty searchPageData.results}">
        <div class="account-cards card-select">
            <div class="card order-history-card">
                <div class="account-section-content content-empty">
                    <ycommerce:testId code="orderHistory_noOrders_label">
                        <spring:theme code="text.account.orderHistory.noOrders" />
                    </ycommerce:testId>
                </div>
            </div>
        </div>
    </c:if>
    <div class="customer-order-history account-overview-table account-cards card-select">
        <c:if test="${not empty searchPageData.results}">
            <div class="account-pagination-header card order-history-card">
                <nav:pagination top="true" msgKey="text.account.orderHistory.page" showCurrentPageInfo="true" hideRefineButton="true" supportShowPaged="${isShowPageAllowed}"
                    supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchUrl}" numberPagesShown="${numberPagesShown}" />
            </div>
            <div class="account-listing card order-history-card mobile-cards">
                <div class="site-responsive-table">
                    <div class="srt-heading">
                        <div class="srt-heading-cell srt-cell">
                            <spring:theme code="text.account.orderHistory.orderNumber" />
                        </div>
                        <div class="srt-heading-cell srt-cell">
                            <spring:theme code="text.account.orderHistory.orderStatus" />
                        </div>
                        <div class="srt-heading-cell srt-cell fcenter">
                            <spring:theme code="text.account.orderHistory.datePlaced" />
                        </div>
                        <div class="supportTicketsTableState srt-heading-cell srt-cell fright">
                            <spring:theme code="text.account.orderHistory.total" />
                        </div>
                    </div>
                    <div class="srt-body">
                        <c:forEach items="${searchPageData.results}" var="order">
                            <div class="srt-row">
                                <div class="srt-row-cell srt-cell">
                                    <div class="srt-mobile-label">
                                        <spring:theme code="text.account.orderHistory.orderNumber" />
                                    </div>
                                    <div class="srt-value">
                                        <spring:url value="/my-account/order/{/orderCode}" var="orderDetailsUrl" htmlEscape="false">
                                            <spring:param name="orderCode" value="${order.code}" />
                                        </spring:url>
                                        <a href="${fn:escapeXml(orderDetailsUrl)}" class="responsive-table-link"> ${fn:escapeXml(order.code)} </a>
                                    </div>
                                </div>
                                <div class="srt-row-cell srt-cell">
                                    <div class="srt-mobile-label">
                                        <spring:theme code="text.account.orderHistory.orderStatus" />
                                    </div>
                                    <div class="break-word srt-value">
                                        <spring:theme code="text.account.order.status.display.${order.statusDisplay}" />
                                    </div>
                                </div>
                                <div class="srt-row-cell srt-cell fcenter">
                                    <div class="srt-mobile-label">
                                        <spring:theme code="text.account.orderHistory.datePlaced" />
                                    </div>
                                    <div class="srt-value fcenter">
                                        <fmt:formatDate value="${order.placed}" dateStyle="medium" timeStyle="short" type="both" />
                                    </div>
                                </div>
                                <div class="srt-row-cell srt-cell fright">
                                    <div class="srt-mobile-label">
                                        <spring:theme code="text.account.orderHistory.total" />
                                    </div>
                                    <div class="srt-value fright">${fn:escapeXml(order.total.formattedValue)}</div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="account-pagination-header card order-history-card">
                <nav:pagination top="false" msgKey="text.account.orderHistory.page" showCurrentPageInfo="true" hideRefineButton="true" supportShowPaged="${isShowPageAllowed}"
                    supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchUrl}" numberPagesShown="${numberPagesShown}" />
            </div>
        </c:if>
    </div>
</div>