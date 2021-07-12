<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<button class="btn btn-a js-save-cart-link">
    <spring:theme code="basket.save.cart" />
</button>