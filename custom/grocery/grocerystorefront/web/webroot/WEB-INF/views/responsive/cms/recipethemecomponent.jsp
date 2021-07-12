<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="recipe" tagdir="/WEB-INF/tags/responsive/recipe"%>
<spring:htmlEscape defaultHtmlEscape="true" />

<div class="recipe-container">
    <h1 class="recipe-headline">${headline}</h1>
    <div class="recipe-themes">
        <c:forEach var="recipe" items="${recipeList}"> 
           <recipe:recipeCard recipe="${recipe}" />
        </c:forEach>
    </div>
</div>
