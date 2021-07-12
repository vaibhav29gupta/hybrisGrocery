<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${ not empty backgroundColor or not empty textColor}">
	<style>
.strapBanner {
	background: ${backgroundColor} !important;
	color: ${textColor} !important;
}
.strapBanner a {
	color: ${textColor} !important;
}
</style>
</c:if>
<c:url value="${component.urlLink.url}" var="url" />
<c:if test="${component.visible}">
	<div class='strapBanner js-strapline-component'>
		<div class='emptyDiv'></div>
		<a href="${url}">${textName}&nbsp;</a>
		<c:if test="${component.isCrossButtonRequired}">
			<i class="fa fa-close close-div" aria-hidden="true"></i>
		</c:if>
	</div>
</c:if>

