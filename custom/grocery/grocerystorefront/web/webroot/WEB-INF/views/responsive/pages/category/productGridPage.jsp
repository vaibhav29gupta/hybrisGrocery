<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>

<template:page pageTitle="${pageTitle}">

	<cms:pageSlot position="Section1" var="feature" element="div" class="product-grid-section1-slot">
		<cms:component component="${feature}" element="div" class="yComponentWrapper map product-grid-section1-component"/>
	</cms:pageSlot>
    <div class="search-results-page">
        <div class="facets search-results-left">
            <cms:pageSlot position="ProductLeftRefinements" var="feature">
                <cms:component component="${feature}" />
            </cms:pageSlot>
        </div>
        <div class="search-results-right">
            <cms:pageSlot position="ProductGridSlot" var="feature" element="div" class="product-grid-right-result-slot">
                <cms:component component="${feature}" element="div" class="product-grid-right-result-component" />
            </cms:pageSlot>
        </div>
    </div>


 <%--  <c:if test="${searchPageData.pagination.currentPage < (searchPageData.pagination.numberOfPages - 1)}"> --%>
 <div class="text-center show-more-products-wrapper see-more-list-view">
 	<form action="${requestScope['javax.servlet.forward.servlet_path']}" method="get">
 		<input type="hidden" name="q" value="${searchPageData.currentQuery.query.value}"/>
 		<input type="hidden" name="sort" value=""/>
 		<input type="hidden" name="page" value="${searchPageData.pagination.currentPage + 1}"  id="pageNo"/>
 		
      <button type="button" id="show-more" class="btn btn-link btn-show-more-product-listing ">
         <%--  <span class="text-wrap"><spring:theme code="plp.show.more"/></span>
          <span class="icon icon-arrow-dropdown"></span> --%>
           <spring:theme code="text.lazyloadingend.notification" var="lazyloadEndOfProductNotification" />
            <span id="endNotification" style="display:none"> "We reached at end !  ${lazyloadEndOfProductNotification} "</span>
           
          
      </button>
 	</form>
 </div>
</template:page>