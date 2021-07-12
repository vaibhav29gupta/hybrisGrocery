<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageData" required="true" type="de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />


<c:if test="${not empty pageData.breadcrumbs}">
    <c:if test="${offersPage ne true}">
	<div class="facet applied-facet js-facet">

	<div class="facet-name js-facet-name">
		<span class="glyphicon facet__arrow"></span>
		<spring:theme code="search.nav.applied.facets"/>
	</div>
		<div class="facet-values js-facet-values">
			<ul class="facet-list">
				<c:forEach items="${pageData.breadcrumbs}" var="breadcrumb">
					<li>
						<c:url value="${breadcrumb.removeQuery.url}" var="removeQueryUrl"/>
						<c:if test="${offersPage eq true}">
						<c:url var = "removeQueryUrl" value = "${fn:replace(removeQueryUrl, '/search', '/search/offers/')}"/>
						</c:if>
						<a href="${fn:escapeXml(removeQueryUrl)}" ><span class="glyphicon glyphicon-remove"></span>&nbsp;${fn:escapeXml(breadcrumb.facetValueName)}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

   </c:if>
</c:if>

<c:if test="${not empty pageData.breadcrumbs}">
    <c:if test="${offersPage eq true && fn:length(pageData.breadcrumbs) > 1}">
	<div class="facet applied-facet js-facet">

	<div class="facet-name js-facet-name">
		<span class="glyphicon facet__arrow"></span>
		<spring:theme code="search.nav.applied.facets"/>
	</div>
		<div class="facet-values js-facet-values">
			<ul class="facet-list">
				<c:forEach items="${pageData.breadcrumbs}" begin = "1" var="breadcrumb">
					<li>
						<c:url value="${breadcrumb.removeQuery.url}" var="removeQueryUrl"/>
						<c:if test="${offersPage eq true}">
						<c:url var = "removeQueryUrl" value = "${fn:replace(removeQueryUrl, '/search', '/search/offers/')}"/>
						</c:if>
						<a href="${fn:escapeXml(removeQueryUrl)}" ><span class="glyphicon glyphicon-remove"></span>&nbsp;${fn:escapeXml(breadcrumb.facetValueName)}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

   </c:if>
</c:if>