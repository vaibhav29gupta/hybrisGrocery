<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="recipeData" required="true" type="org.grocery.facades.recipe.data.RecipeData"%>
<%@ attribute name="listOfUnAffectedProducts" required="false" type="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="recipe-add-to-cart-headline-section">
    <div class="recipe-headline">
        <spring:theme code="unique.product.in.recipes" />
    </div>
    <div class="recipe-add-to-cart-size btn recipe-btn js-recipe-add-to-cart-btn btn-a" data-recipecode="${recipeData.code}">
        <span id="spnRecipeAddToCart">
            <spring:theme code="shop.these.products" />
            <span class="essential-product-count"> (${recipeData.essentialProduct.size()})</span>
        </span>
    </div>
</div>
<div class="recipe-cart-message">
    <div class="recipe-cart-failure-message recipe-cart-message-heading shownone">
        <spring:theme code="recipe.text.product.failure.message" />
    </div>
    <div class="recipe-cart-success-message shownone recipe-cart-message-heading">
        <spring:theme code="recipe.text.product.success.message" />
    </div>
    <div class="recipe-cart-partial-success-message shownone">
        <spring:theme code="recipe.text.product.partial.success.message" />
        &nbsp;-
        <c:forEach items="${recipeData.essentialProduct}" var="product" varStatus="loop">
            <c:if test="${fn:contains(listOfUnAffectedProducts, product.code)}">
                <span class="recipe-unaffected-product-name">${product.name}</span>
                <c:if test="${!loop.last && listOfUnAffectedProducts.size() > 1}">, </c:if>
            </c:if>
        </c:forEach>
    </div>
</div>
<div class="carousel__component--carousel js-owl-recipereferences  js-owl-carousel">
    <c:forEach items="${recipeData.essentialProduct}" var="product">
        <c:set var="isProductNotAvailable" value="false" />
        <c:if test="${fn:contains(listOfUnAffectedProducts, product.code)}">
            <c:set var="isProductNotAvailable" value="true" />
        </c:if>
        <product:productListerGridItem product="${product}" hasAddToCartComponent="false" isProductNotAvailable="${isProductNotAvailable}" />
    </c:forEach>
</div>
