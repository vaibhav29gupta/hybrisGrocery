<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="countries" required="true" type="java.util.Collection"%>
<%@ attribute name="currentCountry" required="true" type="de.hybris.platform.core.model.c2l.CountryModel"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:if test="${fn:length(countries) >= 1}">
    <c:url value="/_s/country" var="setCountryActionUrl" />
    <form:form action="${setCountryActionUrl}" method="post" id="country-form">
        <div class="custom-select-wrapper js-language-select-wrapper">
            <div class="custom-select js-language-select">
                <div class="custom-select__trigger js-language-select__trigger">
                    <c:forEach items="${countries}" var="country">
                        <c:if test="${country.isocode == currentCountry.isocode}">
                            <span> ${country.name} </span>
                        </c:if>
                    </c:forEach>
                    <div class="arrow"></div>
                </div>
                <div class="custom-options js-language-custom-options">
                    <c:forEach items="${countries}" var="country" varStatus="loop">
                        <c:if test="${country.isocode != currentCountry.isocode}">
                            <span class="custom-option js-language-custom-option ${count.isocode == currentCountry.isocode ? 'selected' : '' }" data-value="${country.isocode}"> ${country.name} </span>
                        </c:if>
                    </c:forEach>
                </div>
                <input type="hidden" class="set-value-to-input" value="${initialvalue}" id="country-selector" name="code" />
            </div>
        </div>
    </form:form>
</c:if>