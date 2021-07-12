<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="recipes" tagdir="/WEB-INF/tags/responsive/recipe"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<template:page pageTitle="${pageTitle}">
    <div class="recipe-page">
        <div class="heading">${recipeData.name}</div>
        <div class="recipeDetail-image">
            <img src="${recipeData.media.url}" alt="${recipeData.media.altText}" />
        </div>
        <div class="recipeDetail-infotimes">
            <div class="recipeDetail-infoItem">
                <span class="recipeDetail-infoAmount">${recipeData.serves}</span>
                <spring:theme code="recipe.serves.text" />
            </div>
            <div class="recipeDetail-infoItem">
                <span class="recipeDetail-infoAmount">${recipeData.prepTime}</span>
                <spring:theme code="recipe.minsprep.text" />
            </div>
            <div class="recipeDetail-infoItem">
                <span class="recipeDetail-infoAmount">${recipeData.cookingTime}</span>
                <spring:theme code="recipe.minscook.text" />
            </div>
        </div>
        <div class="recipe-instructions">
            <div class="recipe-ingredients">
                <h3 class="recipe-heading">
                    <spring:theme code="recipe.ingredients.text" />
                </h3>
                <table class="recipe-ingredient">
                    <c:if test="${not empty recipeData.group}">
                        <c:forEach var="ingredientsGroup" items="${recipeData.group}">
                            <tr>
                                <td class="recipe-ingredient-subheading">${ingredientsGroup.groupName}</td>
                            </tr>
                            <c:forEach var="ingredient" items="${ingredientsGroup.ingredients}">
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty ingredient.url}">
                                                <c:url var="productUrl" value="${ingredient.url}" />
                                            </c:when>
                                            <c:otherwise>
                                                <c:url var="productUrl" value="" />
                                            </c:otherwise>
                                        </c:choose>
                                        <c:if test="${not empty ingredient.quantity}">
                                            <span>
                                                <c:out value="${ingredient.quantity}" />
                                            </span>
                                        </c:if>
                                        <a href="${productUrl}" class="recipe-product ${not empty productUrl ? 'recipe-product-link-element' : 'recipe-product-none'}">
                                            <c:if test="${not empty ingredient.name}">
                                                <span>
                                                    <c:out value="${ingredient.name}" />
                                                </span>
                                            </c:if>
                                        </a>
                                        <c:if test="${not empty ingredient.specialInstruction}">
                                            <span>
                                                <c:out value="${ingredient.specialInstruction}" />
                                            </span>
                                        </c:if>
                                        <c:if test="${not empty ingredient.cutStyle}">
                                            <span>
                                                <c:out value="${ingredient.cutStyle}" />
                                            </span>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </c:if>
                    <c:if test="${not empty recipeData.uncategorizedIngredient}">
                        <c:forEach var="ingredient" items="${recipeData.uncategorizedIngredient}">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty ingredient.url}">
                                            <c:url var="productUrl" value="${ingredient.url}" />
                                        </c:when>
                                        <c:otherwise>
                                            <c:url var="productUrl" value="" />
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${not empty ingredient.quantity}">
                                        <span>
                                            <c:out value="${ingredient.quantity}" />
                                        </span>
                                    </c:if>
                                    <a href="${productUrl}" class="recipe-product ${not empty productUrl ? 'recipe-product-link-element' : 'recipe-product-none'}">
                                        <c:if test="${not empty ingredient.name}">
                                            <span>
                                                <c:out value="${ingredient.name}" />
                                            </span>
                                        </c:if>
                                    </a>
                                    <c:if test="${not empty ingredient.specialInstruction}">
                                        <span>
                                            <c:out value="${ingredient.specialInstruction}" />
                                        </span>
                                    </c:if>
                                    <c:if test="${not empty ingredient.cutStyle}">
                                        <span>
                                            <c:out value="${ingredient.cutStyle}" />
                                        </span>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </div>
            <div class="recipe-instr">
                <h3 class="recipe-heading">
                    <spring:theme code="recipe.method.text" />
                </h3>
                <c:forEach var="method" items="${recipeData.method}">
                    <div class="recipe-method">${method}</div>
                </c:forEach>
            </div>
        </div>
        <c:if test="${not empty recipeData.essentialProduct}">
            <div class="recipe-carousel">
                <recipes:recipeEssentialProduct recipeData="${recipeData}" />
            </div>
        </c:if>
        <c:if test="${not empty recipeData.product}">
            <div class="recipe-carousel">
                <div class="recipe-headline">
                    <spring:theme code="product.in.recipes" />
                </div>
                <div class="carousel__component--carousel js-owl-recipereferences
							 js-owl-carousel js-owl-carousel-reference">
                    <c:forEach items="${recipeData.product}" var="product">
                        <product:productListerGridItem product="${product}" hasAddToCartComponent="false" />
                    </c:forEach>
                </div>
            </div>
        </c:if>
    </div>
</template:page>