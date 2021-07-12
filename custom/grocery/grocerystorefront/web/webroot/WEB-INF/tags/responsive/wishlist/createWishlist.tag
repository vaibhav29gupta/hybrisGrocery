<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ attribute name="createBtnName" required="true" type="java.lang.String"%>
<%@ attribute name="placeHolderOptional" required="false" type="java.lang.String"%>
<%@ attribute name="iconstyle" required="false" type="java.lang.String"%>
<c:choose>
    <c:when test="${createBtnName eq 'js-rename-page-wishlist-btn' }">
        <c:set var="buttonText">
            <spring:theme code="text.wishlist.rename.button" />
        </c:set>
        <c:set var="jsActionBtn" value="shownone" />
    </c:when>
    <c:when test="${createBtnName eq 'js-create-page-wishlist-btn' }">
        <c:set var="buttonText">
            <i class="fa fa-check"></i>
        </c:set>
        <c:set var="jsActionBtn" value="${createBtnName }" />
    </c:when>
    <c:otherwise>
        <c:set var="buttonText">
            <spring:theme code="text.wishlist.create" />
        </c:set>
        <c:set var="jsActionBtn" value="${createBtnName }" />
    </c:otherwise>
</c:choose>
<spring:theme code="text.wishlist.input.box.placeholder" var="wishlistPlaceholder" />
<div class="create-inputbox-container">
    <input type="text" placeholder="${ placeHolderOptional != null ? placeHolderOptional : wishlistPlaceholder }" class="form-control create-wishlist-name"
        value="${ placeHolderOptional != null ? placeHolderOptional : "" }">
    <div class="btn-${iconstyle}  btn btn-primary create-wishlist-btn disabled-btn ${jsActionBtn}">${buttonText }</div>
</div>
<div class="wishlist-name-validation shownone"></div>
<!--div for error msg  -->
<input type="hidden" class="miniCharacters" value="<spring:theme code="text.create.wishlist.minimum.characters" />">
<input type="hidden" class="duplicate" value="<spring:theme code="text.create.wishlist.name.duplicate" />">