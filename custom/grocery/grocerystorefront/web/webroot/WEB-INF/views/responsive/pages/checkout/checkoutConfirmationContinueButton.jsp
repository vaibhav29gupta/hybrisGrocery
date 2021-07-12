<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<spring:url value="${continueUrl}" var="continueShoppingUrl" scope="session" htmlEscape="false"/>

<div class="continue__shopping continue-shopping-confirmation">
      <button class="btn btn-primary btn-block btn--continue-shopping js-continue-shopping-button" data-continue-shopping-url="${fn:escapeXml(continueShoppingUrl)}">
	      <spring:theme code="checkout.orderConfirmation.continueShopping" />
      </button>
</div>
