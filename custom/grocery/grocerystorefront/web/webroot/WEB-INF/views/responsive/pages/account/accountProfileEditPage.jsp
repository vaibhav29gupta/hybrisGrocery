<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/responsive/formElement"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="account-section-header">
    <spring:theme code="text.account.profile.updatePersonalDetails" />
</div>
<div class="account-section-content">
    <div class="account-section-form">
        <form:form action="update-profile" method="post" modelAttribute="customerProfileUpdateForm">
            <formElement:formSelectBoxDefaultEnabled idKey="profile.title" labelKey="profile.title" path="titleCode" mandatory="true" skipBlank="false"
                skipBlankMessageKey="form.select.none" items="${titleData}" selectCSSClass="form-control" />
            <formElement:formInputBox idKey="profile.firstName" labelKey="profile.firstName" path="firstName" inputCSS="text" mandatory="true" />
            <formElement:formInputBox idKey="profile.lastName" labelKey="profile.lastName" path="lastName" inputCSS="text" mandatory="true" />
   
            <div class="profile-shipment-selector preferred-shipment-section">
    			<storepickup:pickupMode shipmentType="${customerProfileUpdateForm.preferredShipmentMode }" 
    						storeValue="${customerProfileUpdateForm.storeName }" 
    						storeID="${customerProfileUpdateForm.preferredPos }"
    						postalCode="${customerProfileUpdateForm.preferredPostalCode }" 
    						isCheckoutPage="${false }"/>
			</div>
	
            <div class="accountActions preferres-shipment-siblings">
                <button type="submit" class="btn btn-primary btn-block preferred-shipment-btn">
                    <spring:theme code="updatePwd.submit" text="Update Password" />
                </button>
                <button type="button" class="btn btn-default btn-block backToHome">
                    <spring:theme code="text.button.cancel" text="Cancel" />
                </button>
            </div>
        </form:form>
    </div>
</div>