<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="recipe" tagdir="/WEB-INF/tags/responsive/recipe"%>
<spring:htmlEscape defaultHtmlEscape="true" />

<div class="recipe-highlights">
	<div class="recipe-highlights-left">
		<c:forEach var="recipe" items="${recipeList}">
			<recipe:recipeCard recipe="${recipe}" />
		</c:forEach>
	</div>
	<div class="recipe-highlights-right">
		<c:url var="hightlightedRecipeUrl" value="/r/${highlightRecipe.code}" />
		<a href="${hightlightedRecipeUrl}"> <img
			class="highlight-recipe-img" src="${highlightRecipe.media.url}" />
			<div class="image-overlay"></div>
			<div class="highlight-recipe-text">
				<div class="recipe-of-the-day">
					<spring:theme code="recipe.of.the.day" />
				</div>${highlightRecipe.name}</div>
		</a>
	</div>
</div>
