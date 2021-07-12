<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav" %>

<div id="product-facet" class="facets-products js-product-facet">
    <nav:facetNavAppliedFilters pageData="${searchPageData}"/>
    <nav:facetNavRefinements pageData="${searchPageData}"/>
</div>