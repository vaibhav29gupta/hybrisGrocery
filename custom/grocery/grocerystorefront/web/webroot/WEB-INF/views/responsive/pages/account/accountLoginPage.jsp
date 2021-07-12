<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<template:page pageTitle="${pageTitle}">
	<jsp:attribute name="pageScripts">
		<script src="${themeResourcePath}/js/facebook.socialmedia.js"></script>
		<script src="https://apis.google.com/js/api:client.js"></script>
        <script src="${themeResourcePath}/js/google.socialmedia.js"></script>
	</jsp:attribute>

    <jsp:body>

        <div class="login-register-box">
            <div class="login-register-tiles">
                <button id="loginBtn" class="btn btn-toggler toggle-on"
                        type="button">
                    <i class="fa fa-user" aria-hidden="true"></i>

                    <spring:theme code="login.button.text"/>
                </button>
                <button id="loginNewAccBtn"
                        class="btn btn-toggler">
                    <i class="fas fa-user-plus" aria-hidden="true"></i>

                    <spring:theme code="register.button.text"/>
                </button>
            </div>


            <div class="loginLeftContentSlot">
                <cms:pageSlot position="LeftContentSlot" var="feature" element="div"
                              class="login-left-content-slot">
                    <cms:component component="${feature}" element="div"
                                   class="login-left-content-component"/>
                </cms:pageSlot>
            </div>

            <div class="loginRightContentSlot shownone">
                <cms:pageSlot position="RightContentSlot" var="feature"
                              element="div" class="login-right-content-slot">
                    <cms:component component="${feature}" element="div"
                                   class="login-right-content-component"/>
                </cms:pageSlot>
            </div>
        </div>
    </jsp:body>
</template:page>