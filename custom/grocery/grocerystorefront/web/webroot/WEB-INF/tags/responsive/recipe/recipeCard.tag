<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="recipe" required="true" type="org.grocery.facades.recipe.data.RecipeData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="recipe-card">
	<c:url var="recipeUrl" value="${recipe.url}" />
	<a href="${recipeUrl}">
		<div class="image-grid-container">
			<img class="recipe-cards-img" src="${recipe.media.url}">
			<div class="recipe-specs">
				<div class="recipe-spec">
					<span class="recipe-spec-label"> <spring:theme
							code="recipe.serves.text" />
					</span> <span class="recipe-spec-value">${recipe.serves}</span>
				</div>
				<div class="recipe-spec">
					<span class="recipe-spec-label"> <spring:theme
							code="recipe.prep" />
					</span> <span class="recipe-spec-value">${recipe.prepTime}</span>
				</div>
				<div class="recipe-spec">
					<span class="recipe-spec-label"> <spring:theme
							code="recipe.cooking" />
					</span> <span class="recipe-spec-value">${recipe.cookingTime}</span>
				</div>
			</div>
		</div>
		<div class="recipe-card-details">
			<div class="recipe-card-heading">
				<spring:theme code="recipe.text" />
			</div>
			<div class="recipe-card-description">${recipe.name}</div>
		</div>
	</a>
</div>