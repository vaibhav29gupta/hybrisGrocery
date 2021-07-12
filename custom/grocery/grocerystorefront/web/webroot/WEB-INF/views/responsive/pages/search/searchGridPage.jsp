<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="storepickup"
	tagdir="/WEB-INF/tags/responsive/storepickup"%>


<template:page pageTitle="${pageTitle}">

    <div class="search-results-page">
        <div class="facets search-results-left">
            <cms:pageSlot position="ProductLeftRefinements" var="feature">
                <cms:component component="${feature}" />
            </cms:pageSlot>
        </div>
        <div class="search-results-right">
            <cms:pageSlot position="SearchResultsGridSlot" var="feature" element="div" class="product-list-right-slot">
                <cms:component component="${feature}" element="div" class="product-list-right-component" />
            </cms:pageSlot>
        </div>
    </div>
<%--  <c:if test="${searchPageData.pagination.currentPage < (searchPageData.pagination.numberOfPages - 1)}"> --%>
 <div class="text-center show-more-products-wrapper see-more-list-view">
 	<form action="${requestScope['javax.servlet.forward.servlet_path']}" method="get">
 		<input type="hidden" name="q" value="${searchPageData.currentQuery.query.value}"/>
 		<input type="hidden" name="sort" value=""/>
 		<input type="hidden" name="page" id="pageNo" value="${searchPageData.pagination.currentPage + 1}"/>
 		
      <button type="button" id="show-more" class="btn btn-link btn-show-more-product-listing ">
         <%--  <span class="text-wrap"><spring:theme code="plp.show.more"/></span>
          <span class="icon icon-arrow-dropdown"></span> --%>
          <span id="endNotification" style="display:none">Congratulations We reached at the end </span>
      </button>
 	</form>
 </div>
	<%-- 
	<storepickup:pickupStorePopup />
 --%>
</template:page>