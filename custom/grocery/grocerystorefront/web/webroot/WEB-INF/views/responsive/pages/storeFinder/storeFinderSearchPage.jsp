<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="store" tagdir="/WEB-INF/tags/responsive/store"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${searchPageData ne null and !empty searchPageData.results}">
	{"countryLatitude": ${countryLattitude},"countryLongitude":${countryLongitude} ,"total":${ycommerce:encodeJSON(searchPageData.pagination.totalNumberOfResults)},"data":[
	<c:forEach items="${searchPageData.results}" var="pos" varStatus="loopStatus">
        <c:set value="${ycommerce:storeImage(pos, 'cartIcon')}" var="storeImage" />
        <c:url value="${pos.url}" var="storeUrl" scope="request" />
		{
			"displayName" : "${ycommerce:encodeJSON(pos.displayName)}",
			"name" : "${ycommerce:encodeJSON(pos.name)}",
			"url" : "${ycommerce:encodeJSON(pos.url)}",
			"phone" : "${ycommerce:encodeJSON(pos.address.phone)}",
			"formattedDistance" : "${ycommerce:encodeJSON(pos.formattedDistance)}",
			"line1" : "${ycommerce:encodeJSON(pos.address.line1)}",
			"line2" : "${ycommerce:encodeJSON(pos.address.line2)}",
			"town" : "${ycommerce:encodeJSON(pos.address.town)}",
			"postalCode" : "${ycommerce:encodeJSON(pos.address.postalCode)}",
			"latitude" : "${ycommerce:encodeJSON(pos.geoPoint.latitude)}",
			"longitude" : "${ycommerce:encodeJSON(pos.geoPoint.longitude)}",
			<c:if test="${not empty pos.openingHours}">
				"openings":<store:openingSchedule openingSchedule="${pos.openingHours}" />
        </c:if>
        <c:if test="${not empty pos.features}">
				"features" :[
						<c:forEach items="${pos.features}" var="feature" varStatus="featureNumber">
							"${ycommerce:encodeJSON(feature.value)}"<c:if test="${!featureNumber.last}">,</c:if>
            </c:forEach>
						],
					
			</c:if>
			"image" : "${ycommerce:encodeJSON(storeImage.url)}"
		}<c:if test="${!loopStatus.last}">,</c:if>
    </c:forEach>
	]}
</c:if>
<c:if test="${empty searchPageData.results}">
    <template:page pageTitle="${pageTitle}">
        <cms:pageSlot position="TopContent" var="feature">
            <cms:component component="${feature}" element="div" class="top-content-slot cms_disp-img_slot" />
        </cms:pageSlot>
        <div id="storeFinder">
            <cms:pageSlot position="MiddleContent" var="feature">
                <cms:component component="${feature}" element="div" />
            </cms:pageSlot>
        </div>
        <div class="search-empty no-stores-found shownone">
            <div class="heading">
                <spring:theme code="search.no.results.for.storelocator" var="noSearchResultsheading" htmlEscape="false" />
                ${ycommerce:sanitizeHTML(noSearchResultsheading)}
            </div>
            <div class="sub-heading">
                <spring:theme code="search.no.results.tagline.storelocator" var="noSearchResults" htmlEscape="false" />
                ${ycommerce:sanitizeHTML(noSearchResults)}
            </div>
        </div>
    </template:page>
</c:if>