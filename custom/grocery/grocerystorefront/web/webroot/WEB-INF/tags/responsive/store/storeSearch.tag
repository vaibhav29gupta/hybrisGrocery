<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="errorNoResults" required="true" type="java.lang.String"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:url value="/store-finder" var="storeFinderFormAction" />
<div class="store-finder-heading">
    <div class="store-finder-overlay"></div>
    <div class="headline">
        <spring:theme code="storeFinder.find.a.store" />
    </div>
    <div class="store-finder-search">
        <form:form action="${storeFinderFormAction}" method="get" modelAttribute="storeFinderForm" class="store-finder-form">
            <ycommerce:testId code="storeFinder_search_box">
                <formElement:formInputBox idKey="storelocator-query" labelKey="storelocator.query" path="q" labelCSS="sr-only"
                    inputCSS="form-control js-store-finder-search-input store-finder-search-input" mandatory="true" placeholder="pickup.search.message" />
                <button class="btn btn-neutral storefinder-search-button" type="submit" data-search-empty="<spring:theme code="storelocator.error.no.results.subtitle" />">
                    <span class="fa fa-search"></span>
                </button>
            </ycommerce:testId>
        </form:form>
        <ycommerce:testId code="storeFinder_nearMe_button">
            <button id="findStoresNearMe" class="btn btn-primary btn-block" type="button" disabled>
                <%--                 <spring:theme code="storeFinder.findStoresNearMe" /> --%>
                <i class="fa fa-location-arrow"></i>
            </button>
        </ycommerce:testId>
    </div>
</div>
<div id="map"></div>
