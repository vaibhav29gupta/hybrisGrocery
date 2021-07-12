<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="productData" required="true" type="java.util.List"%>
<%@ attribute name="isCarouselContainsServiceableProducts" required="false" type="java.lang.Boolean"%>
<%@ attribute name="grid" required="false" type="java.lang.String"%>

<spring:htmlEscape defaultHtmlEscape="true" />
<c:choose>
    <c:when test="${not empty productData and isCarouselContainsServiceableProducts}">
        <div class="product-carousel">
            <div class="headline">${fn:escapeXml(title)}</div>
            <div class="carousel__component--carousel js-owl-productcarousel js-owl-${grid} js-owl-carousel">
                <c:forEach items="${productData}" var="product">
                    <c:if test="${product.isServiceable}">
                        <product:productListerGridItem product="${product}" hasAddToCartComponent="false" />
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <component:emptyComponent />
    </c:otherwise>
</c:choose>
