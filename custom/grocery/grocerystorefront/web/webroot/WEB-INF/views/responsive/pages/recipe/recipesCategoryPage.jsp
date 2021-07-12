<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="recipe" tagdir="/WEB-INF/tags/responsive/recipe"%>

<template:page pageTitle="${pageTitle}">
	<div class="recipes-category-page">
		<div class="recipe-category-image">
			<img class="recipe-category-banner-img"
				src="${recipeCategoryEntries.image.url}" />
			<div class="overlay-image"></div>
			<div class="recipe-category-banner-content">
				<div class="recipe-overlay-headline">${recipeCategoryEntries.name}</div>
				<div class="recipe-overlay-subheadline">${recipeCategoryEntries.description}</div>
			</div>
		</div>
		<div class="recipe-container">
			<div class="recipe-themes">
				<c:forEach var="recipe" items="${recipeCategoryEntries.recipes}">
					<recipe:recipeCard recipe="${recipe}" />
				</c:forEach>
			</div>
		</div>
	</div>
</template:page>