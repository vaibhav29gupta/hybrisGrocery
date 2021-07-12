<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<div class="account-savedcarts account-list account-section-content">
    <div class="customer-saved-carts account-cards card-select">
        <div class="saved-cart-actions card saved-cart-card">
            <ycommerce:testId code="savedCartDetails_backToSavedCarts_button">
                <spring:url value="/my-account/saved-carts" var="savedCartsUrl" htmlEscape="false" />
                <a href="${fn:escapeXml(savedCartsUrl)}">
                    <button type="button" class="btn btn-a savedCartBackBtn btn-block">
                        <spring:theme code="text.account.savedCart.backToSavedCarts" />
                    </button>
                </a>
            </ycommerce:testId>
        </div>
    </div>
</div>