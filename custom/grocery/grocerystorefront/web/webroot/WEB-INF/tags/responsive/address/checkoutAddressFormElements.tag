<%@ attribute name="regions" required="true" type="java.util.List"%>
<%@ attribute name="country" required="false" type="java.lang.String"%>
<%@ attribute name="tabIndex" required="false" type="java.lang.Integer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:if test="${country != ' '}">
		<spring:theme var="notEditableOption" code="checkout.multi.country.selector.not.editable" />
		<formElement:formSelectBoxDefaultEnabled idKey="address.title" labelKey="address.title" path="titleCode" mandatory="true" skipBlank="false" skipBlankMessageKey="address.title.none" items="${titles}" selectedValue="${addressForm.titleCode}" selectCSSClass="form-control"/>
		<formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="firstName" inputCSS="form-control" mandatory="true" />
		<formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="lastName" inputCSS="form-control" mandatory="true" />
		<formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="line1" inputCSS="form-control" mandatory="true" />
		<formElement:formInputBox idKey="address.line2" labelKey="address.line2" path="line2" inputCSS="form-control" mandatory="true"/>
		<div id="town-city-input" class="checkout-filled-box" title="${notEditableOption }">
		  <formElement:formInputBox idKey="address.townCity" labelKey="address.region" path="townCity" inputCSS="form-control" tabindex="-1"/>
		</div>
		<div id="post-code-input" class="checkout-filled-box" title="${notEditableOption }">
		  <formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="postcode" inputCSS="form-control" tabindex="-1"/>
		</div>
        <formElement:formInputBox idKey="address.phone" labelKey="address.phone" path="phone" inputCSS="form-control" mandatory="false" />
        
</c:if>
