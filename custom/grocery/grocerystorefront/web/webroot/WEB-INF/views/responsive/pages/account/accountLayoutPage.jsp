<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<spring:url value="/my-account/update-profile" var="updateProfileUrl" />
<spring:url value="/my-account/update-password" var="updatePasswordUrl" />
<spring:url value="/my-account/update-email" var="updateEmailUrl" />
<spring:url value="/my-account/address-book" var="addressBookUrl" />
<spring:url value="/my-account/payment-details" var="paymentDetailsUrl" />
<spring:url value="/my-account/orders" var="ordersUrl" />
<spring:url value="/wishlist/wishlists" var="wishlistUrl" />
<spring:url value="/wishlist/wishlist-detail" var="wishlistDetailsUrl" />

<template:page pageTitle="${pageTitle}">
    <%-- 	<cms:pageSlot position="SideContent" var="feature" --%>
    <%-- 		class="accountPageSideContent"> --%>
    <%-- 		<cms:component component="${feature}" /> --%>
    <%-- 	</cms:pageSlot> --%>
    <div class="account-pages">
        <div class="left-navigation">
            <div class="account-vertical-menu">
                <li>
                    <div class="user-name js-toggle-child-state" data-child="account-links">
                        <spring:theme code="account.welcome" arguments="${user.firstName},${user.lastName}" />
                        <span class="caret visible-xs visible-sm"></span>
                    </div>
                </li>
                <cms:pageSlot position="SideContent" var="feature">
                    <c:forEach items="${feature.navigationNode.children}" var="childLevel1">
                        <c:forEach items="${childLevel1.entries}" var="entry">
                            <cms:component component="${entry.item}" evaluateRestriction="true" element="li"
                                           class="account-links ${fn:containsIgnoreCase(entry.item.url,cmsPage.label) ? 'active' : ''}"/>
                        </c:forEach>
                    </c:forEach>
                </cms:pageSlot>
            </div>
        </div>
        <div class="main-content">
            <div class="top-content">
                <cms:pageSlot position="TopContent" var="feature" element="div" class="accountPageTopContent">
                    <cms:component component="${feature}" />
                </cms:pageSlot>
            </div>
            <div class="account-section">
                <cms:pageSlot position="BodyContent" var="feature">
                    <cms:component component="${feature}" />
                </cms:pageSlot>
            </div>
            <div class="bottom-content">
                <cms:pageSlot position="BottomContent" var="feature" element="div" class="accountPageBottomContent">
                    <cms:component component="${feature}" />
                </cms:pageSlot>
            </div>
        </div>
    </div>
</template:page>