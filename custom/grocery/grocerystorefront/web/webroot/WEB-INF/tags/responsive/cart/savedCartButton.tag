<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="titleKey" required="false" type="java.lang.String"%>
<%@ attribute name="buttonType" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/my-account/saved-carts/" var="savedCartPageUrl" htmlEscape="false"/>
<c:set var="titleKey" value="${not empty titleKey? titleKey : 'basket.switch.cart' }" />
<c:set var="buttonType" value="${not empty buttonType? buttonType : 'btn-ac' }" />
<a href="${savedCartPageUrl}" class="btn ${buttonType}">
        <spring:theme code="${titleKey}" />
</a>