<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true"%>
<%@ attribute name="checkoutSteps" required="true" type="java.util.List"%>
<%@ attribute name="progressBarId" required="true" type="java.lang.String"%>
<%@ attribute name="cssClass" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<ycommerce:testId code="checkoutSteps">
    <div class="checkout-steps ${fn:escapeXml(cssClass)}">
        <multi-checkout:selectShipment />
        <c:forEach items="${checkoutSteps}" var="checkoutStep" varStatus="status">
            <c:url value="${checkoutStep.url}" var="stepUrl" />
            <c:choose>
                <c:when test="${(checkoutStep.progressBarId eq 'deliveryAddress') && shipmentType eq 'PICKUP'}">
                    <div class="step-head ${checkoutStep.stepNumber > activeCheckoutStepNumber ? '' : 'done'} disabled">
                        <div class="stepNumber">${checkoutStep.stepNumber}</div>
                        <div class="title">
                            <spring:theme code="checkout.multi.${checkoutStep.progressBarId}" />
                        </div>
                    </div>
                </c:when>
                <c:when test="${progressBarId eq checkoutStep.progressBarId}">
                    <c:set scope="page" var="activeCheckoutStepNumber" value="${checkoutStep.stepNumber}" />
                    <a href="${fn:escapeXml(stepUrl)}" class="step-head js-checkout-step active">
                        <div class="stepNumber">${checkoutStep.stepNumber}</div>
                        <div class="title">
                            <spring:theme code="checkout.multi.${checkoutStep.progressBarId}" />
                        </div>
                    </a>
                    <div class="step-body"><jsp:doBody /></div>
                </c:when>
                <c:when test="${checkoutStep.stepNumber > activeCheckoutStepNumber && checkoutStep.stepNumber < 5}">
                    <a href="${fn:escapeXml(stepUrl)}" class="step-head js-checkout-step">
                        <div class="stepNumber">${checkoutStep.stepNumber}</div>
                        <div class="title">
                            <spring:theme code="checkout.multi.${checkoutStep.progressBarId}" />
                        </div>
                    </a>
                </c:when>
                <c:otherwise>
                    <c:if test="${checkoutStep.stepNumber < 5}">
                        <a href="${fn:escapeXml(stepUrl)}" class="step-head js-checkout-step done">
                            <div class="stepNumber">${checkoutStep.stepNumber}</div>
                            <div class="title">
                                <spring:theme code="checkout.multi.${checkoutStep.progressBarId}" />
                            </div>
                            <div class="edit">
                                <spring:theme code="checkout.multi.deliveryAddress.edit" />
                            </div>
                        </a>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</ycommerce:testId>