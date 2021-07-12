<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="jspcache" uri="/WEB-INF/tld/ehcache.tld"%>
<template:page pageTitle="${pageTitle}">
	<c:if test="${showEmptyCartPopup eq true}">
		<div class="open-cart-empty"></div>
	</c:if>
	<div class="homepage">
		<jspcache:cache key="header-section1-${currentCountry.isocode}">
			<cms:pageSlot position="Section1" var="feature">
				<cms:component component="${feature}" />
			</cms:pageSlot>
		</jspcache:cache>
		<jspcache:cache key="header-section2A-${currentCountry.isocode}">
			<div class="m2x desktop-only category-banners">
				<cms:pageSlot position="Section2A" var="feature">
					<c:choose>
						<c:when test="${feature.typeCode eq 'CMSLinkComponent'}">
							<a href="${feature.url}">
								<div class="simple-banner simple-banner-container">
									<span class="simple-banner-box">${feature.linkName}</span>
								</div>
							</a>
						</c:when>
						<c:otherwise>
							<cms:component component="${feature}" />
						</c:otherwise>
					</c:choose>
				</cms:pageSlot>
			</div>
		</jspcache:cache>
		<jspcache:cache key="header-section2B-${currentCountry.isocode}">
			<div class="m4x">
				<cms:pageSlot position="Section2B" var="feature">
					<cms:component component="${feature}" />
				</cms:pageSlot>
			</div>
		</jspcache:cache>
		<div class="m2x">
			<cms:pageSlot position="Section2C" var="feature">
				<cms:component component="${feature}" />
			</cms:pageSlot>
		</div>
		<jspcache:cache key="header-section3-${currentCountry.isocode}">
			<cms:pageSlot position="Section3" var="feature">
				<cms:component component="${feature}" element="div"
					class="btm-std-margin" />
			</cms:pageSlot>
		</jspcache:cache>
		<div class="m2x">
			<cms:pageSlot position="Section4" var="feature">
				<cms:component component="${feature}" />
			</cms:pageSlot>
		</div>
		<jspcache:cache key="header-section5-${currentCountry.isocode}">
			<cms:pageSlot position="Section5" var="feature">
				<cms:component component="${feature}" />
			</cms:pageSlot>
		</jspcache:cache>
		<jspcache:cache key="header-section6-${currentCountry.isocode}">
			<div class="m4x">
				<cms:pageSlot position="Section6" var="feature">
					<cms:component component="${feature}" />
				</cms:pageSlot>
			</div>
		</jspcache:cache>
	</div>
</template:page>
