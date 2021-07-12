<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/responsive/user" %>

<div class="guest-login-section">
	<c:url value="/checkout/j_spring_security_check" var="loginAndCheckoutActionUrl" />
	<user:login actionNameKey="checkout.login.loginAndCheckout" action="${loginAndCheckoutActionUrl}" />
</div>

