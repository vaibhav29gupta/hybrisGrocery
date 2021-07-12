<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<ycommerce:testId code="savedCartDetails_itemBody_section">
    <div class="account-savedcarts account-list account-section-content no-margin-list">
        <div class="customer-saved-carts account-cards card-select">
            <div class="saved-cart-details card saved-cart-card item__list">
                <c:forEach items="${savedCartData.entries}" var="entry" varStatus="loop">
                    <order:groceryOrderEntry entry="${entry}" order="${savedCartData}" isSavedCart="true" />
                </c:forEach>
            </div>
        </div>
    </div>
</ycommerce:testId>
<div class="account-savedcarts account-list account-section-content no-margin-list">
    <div class="customer-saved-carts account-cards card-select">
        <div class="saved-cart-details card saved-cart-card">
            <span class="info">
                <spring:theme code="text.account.savedCart.message" />
            </span>
        </div>
    </div>
</div>