<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<spring:url value="/my-account/saved-carts" var="savedCartsUrl" htmlEscape="false"/>

<div class="back-link account-section-header border">
    <button type="button" class="addressBackBtn" data-back-to-addresses="${fn:escapeXml(savedCartsUrl)}">
        <span class="fa fa-chevron-circle-left"></span>
    </button>
    <span class="label">
        <spring:theme code="text.account.savedCart.title.details" />
    </span>
</div>