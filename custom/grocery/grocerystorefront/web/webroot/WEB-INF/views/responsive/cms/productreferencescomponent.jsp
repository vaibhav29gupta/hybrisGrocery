<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:set var="showComponent" value="false" />
<c:forEach end="${component.maximumNumberProducts}" items="${productReferences}" var="productReference">
    <c:set var="product" value="${productReference.target}" />
    <c:if test="${product.isServiceable}">
        <c:set var="showComponent" value="true" />
    </c:if>
</c:forEach>
<c:choose>
    <c:when test="${not empty productReferences and component.maximumNumberProducts > 0 and showComponent}">
        <div class="carousel-component">
            <div class="headline">${fn:escapeXml(component.title)}</div>
            <div class="carousel js-owl-carousel js-owl-productreferences js-owl-carousel-reference">
                <c:forEach end="${component.maximumNumberProducts}" items="${productReferences}" var="productReference">
                    <c:set var="product" value="${productReference.target}" />
                    <c:if test="${product.isServiceable}">
                        <product:productListerGridItem product="${product}" />
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <component:emptyComponent />
    </c:otherwise>
</c:choose>