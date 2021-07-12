<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<spring:url value="/my-account/orders" var="orderHistoryUrl" htmlEscape="false" />
<div class="back-link account-section-header border">
    <button type="button" class="addressBackBtn" data-back-to-addresses="${fn:escapeXml(orderHistoryUrl)}">
        <span class="fa fa-chevron-circle-left"></span>
    </button>
    <span class="label">
        <spring:theme code="text.account.order.title.details" text="Order Details" />
    </span>
</div>
