<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="hideHeaderLinks" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/responsive/nav"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<%@ taglib prefix="jspcache" uri="/WEB-INF/tld/ehcache.tld" %>
<spring:htmlEscape defaultHtmlEscape="true" />
<cms:pageSlot position="StrapLineBannerSlot" var="component" element="div">
    <cms:component component="${component}" />
</cms:pageSlot>
<%--
<div class="branding-mobile hidden-md hidden-lg">
    <div class="js-mobile-logo">
        <!--populated by JS acc.navigation-->
    </div>
</div>
--%>
<cms:pageSlot position="TopHeaderSlot" var="component" element="div">
    <cms:component component="${component}" />
</cms:pageSlot>

<header class="js-mainHeader site-header">
    <nav class="navigation navigation--top top-deck hidden-xs hidden-sm">
        <div class="nav__left js-site-logo top-deck-left">
        <jspcache:cache key="header-logo">
            <cms:pageSlot position="SiteLogo" var="logo" limit="1">
                <cms:component component="${logo}" element="div" class="yComponentWrapper site-logo" />
            </cms:pageSlot>
        </jspcache:cache>
        </div>
        <div class="nav__right top-deck-right">
        <jspcache:cache key="header-links">
            <cms:pageSlot position="HeaderLinks" var="feature">
                <c:if test="${feature.visible}">
                    <c:if test="${not empty feature.navigationNode && feature.navigationNode ne null}">
                        <ul class="nav-header-ul">
                            <c:forEach items="${feature.navigationNode.children}" var="l1">
                                <c:forEach items="${l1.entries}" var="link">
                                    <li class="nav-header-li">
                                        <cms:component component="${link.item}" evaluateRestriction="true" />
                                    </li>
                                </c:forEach>
                            </c:forEach>
                        </ul>
                    </c:if>
                </c:if>
            </cms:pageSlot>
          </jspcache:cache>
        </div>
    </nav>
    <%-- a hook for the my account links in desktop/wide desktop--%>
    <div class="hidden-xs hidden-sm js-secondaryNavAccount collapse" id="accNavComponentDesktopOne">
        <ul class="nav__links">
        </ul>
    </div>
    <div class="hidden-xs hidden-sm js-secondaryNavCompany collapse" id="accNavComponentDesktopTwo">
        <ul class="nav__links js-nav__links">
        </ul>
    </div>

    <nav class="navigation bottom-deck navigation--middle js-navigation--middle">
        <div class="container-fluid">
            <div class="row">
                <div class="mobile__nav__row mobile__nav__row--table">
                    <div class="mobile__nav__row--table-group">
                        <div class="mobile__nav__row--table-row">
                            <div class="mobile__nav__row--table-cell visible-xs hidden-sm">
                                <button class="mobile__nav__row--btn btn mobile__nav__row--btn-menu js-toggle-sm-navigation" type="button">
                                    <i class="fa fa-bars icon"></i>
                                </button>
                            </div>
                            <div class="mobile__nav__row--table-cell visible-xs mobile__nav__row--seperator">
                                <ycommerce:testId code="header_search_activation_button">
                                    <button class="mobile__nav__row--btn btn mobile__nav__row--btn-search js-toggle-xs-search hidden-sm hidden-md hidden-lg" type="button">
                                        <i class="fa fa-search icon"></i>
                                    </button>
                                </ycommerce:testId>
                            </div>
                            <div class="mobile__nav__row--table-cell hidden-sm hidden-md hidden-lg mobile__nav__row--seperator">
                                <common:shipment />
                            </div>
                            <c:if test="${empty hideHeaderLinks}">
                                <ycommerce:testId code="header_StoreFinder_link">
                                    <div class="mobile__nav__row--table-cell hidden-sm hidden-md hidden-lg mobile__nav__row--seperator">
                                        <c:url value="/store-finder" var="storeFinderUrl" />
                                        <a href="${fn:escapeXml(storeFinderUrl)}" class="mobile__nav__row--btn mobile__nav__row--btn-location btn">
                                            <i class="fa fa-map-marker icon"></i>
                                        </a>
                                    </div>
                                </ycommerce:testId>
                            </c:if>
                            <cms:pageSlot position="MiniCart" var="cart" element="div"
                                class="miniCartSlot componentContainer mobile__nav__row--table hidden-sm hidden-md hidden-lg">
                                <cms:component component="${cart}" element="div" class="mobile__nav__row--table-cell" />
                            </cms:pageSlot>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row bottom-deck-desktop">
                <div class="col-sm-2 site-logo-hidden" >
                    <cms:pageSlot position="SiteLogo" var="logo" limit="1">
                        <cms:component component="${logo}" element="div" class="yComponentWrapper site-logo" />
                    </cms:pageSlot>
                </div>
                <div class="col-xs-12 col-sm-6 bdd-left">
                    <div class="hidden-xs visible-sm mobile-menu">
                        <button class="btn js-toggle-sm-navigation" type="button">
                            <i class="fa fa-bars"></i>
                        </button>
                    </div>
                    <div class="site-search">
                        <cms:pageSlot position="SearchBox" var="component">
                            <cms:component component="${component}" />
                        </cms:pageSlot>
                    </div>
                </div>
                <div class="nav__right col-xs-6 col-xs-6 hidden-xs bdd-right">

                    <ul class="nav__links nav__links--shop_info main-header-features">
                        <li class="hidden-xs shipment-selector-nav">
                            <common:shipment />
                        </li>
                        <li>
                            <c:if test="${empty hideHeaderLinks}">
                                <ycommerce:testId code="header_StoreFinder_link">
                                    <c:url value="/store-finder" var="storeFinderUrl" />
                                    <a class="hidden-xs find-location" href="${fn:escapeXml(storeFinderUrl)}">
                                        <div class="icon">
                                            <i class="fa fa-map-marker" aria-hidden="true"></i>
                                        </div>
                                        <div class="find-location-text hidden-xs hidden-sm">
                                            <spring:theme code="header.find.store.text" />
                                        </div>
                                    </a>
                                </ycommerce:testId>
                            </c:if>
                        </li>

                        <li>
                            <ul class="nav__links nav__links--account">
                                <div class="my-account">
                                    <c:if test="${empty hideHeaderLinks}">
                                        <c:if test="${uiExperienceOverride}">
                                            <li class="backToMobileLink">
                                                <c:url value="/_s/ui-experience?level=" var="backToMobileStoreUrl" />
                                                <a href="${fn:escapeXml(backToMobileStoreUrl)}">
                                                    <spring:theme code="text.backToMobileStore" />
                                                </a>
                                            </li>
                                        </c:if>
                                        <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
                                            <c:set var="maxNumberChars" value="11" />
                                            <c:set var="username" value="${user.firstName} ${user.lastName}" />
                                            <c:if test="${fn:length(user.firstName) + fn:length(user.lastName) gt maxNumberChars}">
                                                <c:set var="username" value="${user.firstName}" />
                                            </c:if>
                                            <li class="logged_in js-logged_in login-link hidden-sm hidden-md hidden-lg">
                                                <ycommerce:testId code="header_LoggedUser">
                                                    <div class="login-text">
                                                        <spring:theme code="account.welcome" arguments="${username}"/>
                                                    </div>
                                                </ycommerce:testId>
                                                <cms:pageSlot position="UserAccountLinks" var="link" >
                                                    <cms:component component="${link}" element="ul"  />
                                                </cms:pageSlot>
                                            </li>

                                        </sec:authorize>

                                        <sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')">
                                            <li class="liOffcanvas">
                                                <ycommerce:testId code="header_Login_link">
                                                    <c:url value="/login" var="loginUrl" />
                                                    <a class="login-link user-login" href="${fn:escapeXml(loginUrl)}">
                                                        <i class="fa fa-user-o" aria-hidden="true"></i>
                                                        <span class="login-text">
                                                            <spring:theme code="header.link.login" />
                                                        </span>
                                                    </a>
                                                </ycommerce:testId>
                                            </li>
                                        </sec:authorize>

                                        <sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
                                            <c:set var="maxNumberChars" value="11" />
                                            <c:set var="username" value="${user.firstName} ${user.lastName}" />
                                            <c:if test="${fn:length(user.firstName) + fn:length(user.lastName) gt maxNumberChars}">
                                                <c:set var="username" value="${user.firstName}" />
                                            </c:if>
                                            <li class="liOffcanvas dropdown">
                                                <div class="user-account-link-dropdown dropdown-toggle user-login" data-toggle="dropdown">
                                                    <i class="fa fa-user-o" aria-hidden="true"></i>
                                                    <div class="login-text">
                                                        <div class="login-label">
                                                            <spring:theme code="header.welcome" />
                                                        </div>
                                                        <div class="login-name-dropdown">
                                                            <span class="login-name">${username}</span>
                                                            <span class="caret"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <ul class="dropdown-menu">
                                                    <cms:pageSlot position="UserAccountLinks" var="feature">
                                                        <c:forEach items="${feature.navigationNode.children}" var="childLevel1">
                                                            <c:forEach items="${childLevel1.entries}" var="entry">
                                                                <cms:component component="${entry.item}"
                                                                               evaluateRestriction="true" element="li"/>
                                                            </c:forEach>
                                                        </c:forEach>
                                                    </cms:pageSlot>
                                                </ul>
                                            </li>
                                        </sec:authorize>
                                    </c:if>
                                </div>
                            </ul>
                        </li>
                        <li>
                            <cms:pageSlot position="MiniCart" var="cart" element="div" class="componentContainer minicartcomp">
                                <cms:component component="${cart}" />
                            </cms:pageSlot>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>

    <a id="skiptonavigation"></a>
    <nav:topNavigation />
</header>
<jspcache:cache key="header-bottom-slot"> 
<cms:pageSlot position="BottomHeaderSlot" var="component" element="div" class="container-fluid">
	<cms:component component="${component}" />
</cms:pageSlot>
</jspcache:cache>
