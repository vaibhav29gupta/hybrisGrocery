<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:htmlEscape defaultHtmlEscape="true" />

<c:if test="${not empty recipesCategory && recipesCategory ne null}">
	<div
		class="recipe-categories carousel__component--carousel js-owl-recipecategorycomponentreferences
							 js-owl-carousel js-owl-carousel-reference">
		<c:forEach var="categories" items="${recipesCategory}">
			<c:if
				test="${not empty categories.thumbnail && categories.thumbnail ne null}">
				<div class="recipe-item">
					<c:url var="recipesCategoryUrl" value="${categories.url}" />
					<span class="recipe-category-thumbnail"> <a
						href="${recipesCategoryUrl}"> <img class="recipe-circular-img"
							src="${categories.thumbnail.downloadUrl}" /> <span
							class="recipe-count"> ${categories.recipesCount} <span
								class="recipes-label"> <spring:theme code="recipe.text" />
							</span>
						</span>
					</a>
					</span> <a href="${recipesCategoryUrl}"> <span
						class="recipe-category-description">${categories.name}</span>
					</a>
				</div>
			</c:if>
		</c:forEach>
	</div>
</c:if>
