<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="search-grid">
  <c:if test="${offersPage ne true}">
    <div class="results">
        <h1><spring:theme code="search.page.newSearchText"/> <div class="searched-text"><spring:theme code="search.page.searchText" arguments="${searchPageData.freeTextSearch}" htmlEscape="false"/></div></h1>
    </div>

    <nav:searchSpellingSuggestion spellingSuggestion="${searchPageData.spellingSuggestion}" />
  </c:if>
    <nav:pagination top="true" showpagination="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}"  searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"  numberPagesShown="${numberPagesShown}"/>

    <ul class="list-grid" >
        <c:forEach items="${searchPageData.results}" var="product" varStatus="status">
            <div class="search-grid-item">
            <product:productListerGridItem product="${product}"/>
            </div>
        </c:forEach>
        
    </ul>
    
 
   
	</div>

    <div id="addToCartTitle" class="display-none">
        <div class="add-to-cart-header">
            <div class="headline">
                <span class="headline-text"><spring:theme code="basket.added.to.basket"/></span>
            </div>
        </div>
    </div>

    <nav:pagination top="false" showpagination="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}"  searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"  numberPagesShown="${numberPagesShown}"/>
</div>