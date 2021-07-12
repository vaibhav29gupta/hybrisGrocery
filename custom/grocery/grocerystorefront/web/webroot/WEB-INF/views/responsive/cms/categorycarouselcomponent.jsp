<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:choose>
    <c:when test="${not empty categoryCarousel && categoryCarousel ne null}">
        <div class="category-carousel-component">
            <div class="headline">${fn:escapeXml(title)}</div>
            <div class="carousel__component--carousel js-owl-categorycarousel js-owl-carousel">
                <c:forEach items="${categoryCarousel}" var="category">
                    <div class="category-card">
                        <c:url var="categoryLink" value="${category.url }" />
                        <a href="${fn:escapeXml(categoryLink)}" class="category-anchor">
                            <div class="category-image">
                                <c:set var="image" value="${category.image }" />
                                <img class="category-cards-image" src="${image.url}" alt="${category.name }">
                                <div class="category-details">${category.name }</div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <component:emptyComponent />
    </c:otherwise>
</c:choose>