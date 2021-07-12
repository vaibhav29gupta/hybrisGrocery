<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<template:page pageTitle="${pageTitle}">
    <c:url value="/" var="homePageUrl" />
    <div class="error-page-404">
        <cms:pageSlot position="MiddleContent" var="comp">
            <cms:component component="${comp}" />
        </cms:pageSlot>
        <div class="error-message-404">
            <spring:theme text="Sorry, We couldn't find what you were looking for!" code="error.404" />
        </div>
        <div class="error-button-404">
            <a class="btn btn-neutral js-shopping-button" href="${fn:escapeXml(homePageUrl)}">
                <spring:theme text="Continue Shopping" code="general.continue.shopping" />
            </a>
        </div>
    </div>
</template:page>