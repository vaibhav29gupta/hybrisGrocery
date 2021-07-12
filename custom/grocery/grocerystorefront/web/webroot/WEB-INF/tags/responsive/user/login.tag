<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String"%>
<%@ attribute name="action" required="true" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:set var="hideDescription" value="checkout.login.loginAndCheckout" />

<div class="login-page__headline shownone">
	<spring:theme code="login.title" />
</div>

<c:choose>
	<c:when test="${actionNameKey ne hideDescription}">
		<p class="shownone">
			<spring:theme code="login.description" />
		</p>
	</c:when>
	<c:otherwise>
	
	</c:otherwise>
</c:choose>

<c:if test="${isFBLoginEnable || isGoogleLoginEnable || isOTPLoginEnable }">

    <div class="social-signin-container">
    
        <div class="social-signin">
        
        	<c:if test="${isFBLoginEnable }">
	            <button type="button" class="btn btn-primary btn-social" id="facebook">
	                <i class="fa fa-facebook icon-social" aria-hidden="true"></i>
	                <span><spring:theme code="text.social.signin.facebook" /></span>
	            </button>
				<input type="hidden" id="facebookAppId" value='${facebookAppId}'/>
				<input type="hidden" id="facebookApiVersion" value='${facebookApiVersion}'/>
	        </c:if>
	        
	        <c:if test="${isGoogleLoginEnable }">
	            <button type="button" class="btn btn-primary btn-social" id="google">
	                <i class="fa fa-google icon-social" aria-hidden="true"></i>
	                <span><spring:theme code="text.social.signin.google" /></span>
	            </button>
				<input type="hidden" id="googleClientId" value='${googleClientId}'/>
			</c:if>
	        
	        <c:if test="${isOTPLoginEnable }">
	            <button type="button" class="btn btn-primary btn-social" id="otp">
	                <i class="fa fa-mobile icon-social" aria-hidden="true"></i>
	                <span><spring:theme code="text.social.signin.otp" /></span>
	            </button>
	        </c:if>
        </div>
        <div class="social-divider">
            <span>or</span>
        </div>    
    </div>

</c:if>

<form:form action="${action}" method="post" modelAttribute="loginForm">
	<c:if test="${not empty message}">
		<span class="has-error"> <spring:theme code="${message}" />
		</span>
	</c:if>	
	
		<formElement:formInputBox idKey="j_username" labelKey="login.email"
			path="j_username" mandatory="true" />
		<formElement:formPasswordBox idKey="j_password"
			labelKey="login.password" path="j_password" inputCSS="form-control"
			mandatory="true" />
	<div class="login-actions">
	
		<div class="forgotten-password">
			<ycommerce:testId code="login_forgotPassword_link">
				<a href="#" data-link="<c:url value='/login/pw/request'/>" class="js-password-forgotten" data-cbox-title="<spring:theme code="forgottenPwd.title"/>">
					<spring:theme code="login.link.forgottenPwd" />
				</a>
			</ycommerce:testId>
		</div>
		
		<ycommerce:testId code="loginAndCheckoutButton">
			<button type="submit" class="btn btn-primary btn-block login-checkout-btn">
				<spring:theme code="${actionNameKey}" />
			</button>
		</ycommerce:testId>
        <c:if test="${expressCheckoutAllowed}">
            <button type="submit" class="btn btn-neutral btn-block expressCheckoutButton">
                <spring:theme code="text.expresscheckout.header" />
            </button>
            <input id="expressCheckoutCheckbox" name="expressCheckoutEnabled" type="checkbox" class="form left doExpressCheckout display-none" />
        </c:if>
    </div>

	

</form:form>

