<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<div class="account-section-header ${empty supportTickets ? '':'no-border'}">
    <spring:theme code="text.account.supporttickets" text="Support Tickets" />
    <div class="account-section-header-add pull-right">
        <a href="add-support-ticket" class="add-address" id="add-support-ticket-btn">
            <spring:theme code="text.account.supporttickets.requestSupport" text="Request Support" />
        </a>
    </div>
</div>
<c:set var="searchUrl" value="/my-account/support-tickets?sort=${searchPageData.pagination.sort}" />
<div class="account-tickets account-list account-section-content">
    <c:if test="${empty searchPageData.results}">
        <div class="account-cards card-select">
            <div class="card ticket-card">
                <div class="account-section-content content-empty">
                    <spring:theme code="text.account.supporttickets.noSupporttickets" text="You have no support tickets" />
                </div>
            </div>
        </div>
    </c:if>
    <div class="customer-ticketing account-overview-table account-cards card-select">
        <c:if test="${not empty searchPageData.results}">
            <div class="account-pagination-header card ticket-card">
                <nav:pagination top="true" msgKey="text.account.supportTickets.page" showCurrentPageInfo="true" hideRefineButton="true" supportShowPaged="${isShowPageAllowed}"
                    supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchUrl}" numberPagesShown="${numberPagesShown}" />
            </div>
            <div class="account-listing card ticket-card mobile-cards">
                <div class="site-responsive-table">
                    <div class="srt-heading">
                        <div class="srt-heading-cell srt-cell">
                            <spring:theme code="text.account.supporttickets.ticketId" text="Ticket ID" />
                        </div>
                        <div class="srt-heading-cell srt-cell f2x">
                            <spring:theme code="text.account.supporttickets.subject" text="Subject" />
                        </div>
                        <div class="srt-heading-cell srt-cell fcenter">
                            <spring:theme code="text.account.supporttickets.dateCreated" text="Date Created" />
                        </div>
                        <div class="srt-heading-cell srt-cell fcenter">
                            <spring:theme code="text.account.supporttickets.dateUpdated" text="Date Updated" />
                        </div>
                        <div class="supportTicketsTableState srt-heading-cell srt-cell fhalfx fright">
                            <spring:theme code="text.account.supporttickets.status" text="Status" />
                        </div>
                    </div>
                    <div class="srt-body">
                        <c:forEach items="${searchPageData.results}" var="supportTicket">
                            <c:url value="/my-account/support-ticket/${supportTicket.id}" var="myAccountsupportTicketDetailsUrl" />
                            <div class="srt-row">
                                <div class="srt-row-cell srt-cell">
                                    <div class="srt-mobile-label">
                                        <spring:theme code="text.account.supporttickets.ticketId" text="Ticket ID" />
                                    </div>
                                    <div class="srt-value">
                                        <a href="${myAccountsupportTicketDetailsUrl}" class="responsive-table-link">
                                            <c:out value="${supportTicket.id}" />
                                        </a>
                                    </div>
                                </div>
                                <div class="srt-row-cell srt-cell f2x">
                                    <div class="srt-mobile-label">
                                        <spring:theme code="text.account.supporttickets.subject" text="Subject" />
                                    </div>
                                    <div class="break-word srt-value">
                                        <a href="${myAccountsupportTicketDetailsUrl}" class="responsive-table-link">
                                            <c:out value="${supportTicket.subject}" />
                                        </a>
                                    </div>
                                </div>
                                <div class="srt-row-cell srt-cell fcenter">
                                    <div class="srt-mobile-label">
                                        <spring:theme code="text.account.supporttickets.dateCreated" text="Date Created" />
                                    </div>
                                    <div class="srt-value fcenter">
                                        <fmt:formatDate value="${supportTicket.creationDate}" pattern="dd-MM-yy HH:mm" />
                                    </div>
                                </div>
                                <div class="srt-row-cell srt-cell">
                                    <div class="srt-mobile-label">
                                        <spring:theme code="text.account.supporttickets.dateUpdated" text="Date Updated" />
                                    </div>
                                    <div class="srt-value fcenter">
                                        <fmt:formatDate value="${supportTicket.lastModificationDate}" pattern="dd-MM-yy HH:mm" />
                                    </div>
                                </div>
                                <div class="srt-row-cell srt-cell fhalfx fright">
                                    <div class="srt-mobile-label">
                                        <spring:theme code="text.account.supporttickets.status" text="Status" />
                                    </div>
                                    <div class="srt-value fright">
                                        <spring:message code="ticketstatus.${fn:toUpperCase(supportTicket.status.id)}" />
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="account-pagination-header card ticket-card">
                <nav:pagination top="false" msgKey="text.account.supportTickets.page" showCurrentPageInfo="true" hideRefineButton="true"
                    supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchUrl}"
                    numberPagesShown="${numberPagesShown}" />
            </div>
        </c:if>
    </div>
</div>