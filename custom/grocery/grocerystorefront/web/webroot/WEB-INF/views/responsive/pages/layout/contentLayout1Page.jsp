<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<template:page pageTitle="${pageTitle}">
    <cms:pageSlot position="Section1" var="feature">
        <cms:component component="${feature}" element="div" class="" />
    </cms:pageSlot>
    <div class="container__full">
        <div class="row">
            <cms:pageSlot position="Section2A" var="feature" element="div" class="col-md-3">
                <cms:component component="${feature}" />
            </cms:pageSlot>
            <cms:pageSlot position="Section2B" var="feature" element="div" class="col-md-9">
                <cms:component component="${feature}" />
            </cms:pageSlot>
        </div>
    </div>
    <cms:pageSlot position="Section3" var="feature" element="div">
        <cms:component component="${feature}" />
    </cms:pageSlot>
    <cms:pageSlot position="SectionRA" var="feature">
        <cms:component component="${feature}" />
    </cms:pageSlot>
    <cms:pageSlot position="SectionRB" var="feature">
        <cms:component component="${feature}" />
    </cms:pageSlot>
    <cms:pageSlot position="SectionRC" var="feature">
        <cms:component component="${feature}" element="div" class="h4x" />
    </cms:pageSlot>
    <cms:pageSlot position="SectionRD" var="feature">
        <cms:component component="${feature}" element="div" />
    </cms:pageSlot>
    <cms:pageSlot position="SectionRE" var="feature">
        <cms:component component="${feature}" element="div" />
    </cms:pageSlot>
    <cms:pageSlot position="SectionRF" var="feature">
        <cms:component component="${feature}" element="div" class="h4x" />
    </cms:pageSlot>
</template:page>
