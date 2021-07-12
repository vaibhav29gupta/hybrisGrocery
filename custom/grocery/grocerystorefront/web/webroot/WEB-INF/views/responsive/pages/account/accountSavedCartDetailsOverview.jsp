<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<div class="account-section-content account-list no-margin-list">
    <div class="account-cards card-select">
        <div class="card header-card saved-cart-header">
            <div class="card-heading w100">
                <span class="item-label">
                    <spring:message code="text.account.savedCart.name" />
                </span>
                <span class="item-value"> ${fn:escapeXml(savedCartData.name)} </span>
            </div>
            <div class="card-eq-body">
                <div class="item-group o3 w33">
                    <span class="item-label">
                        <spring:theme code="text.account.savedCart.id" text="Status" />
                    </span>
                    <span class="item-value"> ${fn:escapeXml(savedCartData.code)} </span>
                </div>
                <div class="item-group o4 w33">
                    <span class="item-label">
                        <spring:theme code="text.account.savedCart.dateSaved" />
                    </span>
                    <span class="item-value">
                        <fmt:formatDate value="${savedCartData.saveTime}" dateStyle="medium" timeStyle="short" type="both" />
                    </span>
                </div>
                <div class="item-group o5 w33">
                    <span class="item-label">
                        <spring:theme code="text.account.savedCart.qty" text="Quantity" />
                    </span>
                    <span class="item-value"> ${fn:length(savedCartData.entries)} </span>
                </div>
            </div>
            <div class="card-eq-body">
                <c:if test="${not empty savedCartData.description}">
                    <div class="item-group o6 w100">
                        <span class="item-label">
                            <spring:theme code="text.account.savedCart.description" />
                        </span>
                        <span class="item-value"> ${fn:escapeXml(savedCartData.description)} </span>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="card header-card saved-cart-header">
            <div class="card-eq-body saved-cart-details-actions">
                <c:if test="${fn:length(savedCartData.entries) > 0}">
                    <div class="item-group o6 w33">
                        <a id="restoreButton" href="#" class="js-restore-saved-cart restore-item-link btn btn-neutral btn-block" data-savedcart-id="${fn:escapeXml(savedCartData.code)}"
                            data-restore-popup-title="<spring:theme code='text.account.savedcart.restore.popuptitle'/>">
                                <spring:theme code="text.account.savedCart.restore" />
                        </a>
                    </div>
                </c:if>
                <div class="item-group o6 w33 edit-cart-form">
                    <a href="#" class="js-update-saved-cart edit btn btn-default btn-block">
                            <spring:theme code="text.account.savedCart.edit" />
                    </a>
                    <spring:url value="/my-account/saved-carts/{/cartId}/edit" var="actionUrl" htmlEscape="false">
                        <spring:param name="cartId" value="${savedCartData.code}" />
                    </spring:url>
                    <div class="shownone save-cart-form">
                        <cart:saveCartModal actionUrl="${actionUrl}" titleKey="text.account.savedCart.edit.title" />
                    </div>
                </div>
                <div class="item-group o6 w33">
                    <a href="#" class="js-delete-saved-cart remove-item-link btn btn-negative" data-savedcart-id="${fn:escapeXml(savedCartData.code)}"
                        data-delete-popup-title="<spring:theme code='text.account.savedcart.delete.popuptitle'/>">
                        <spring:theme code="text.account.savedCart.delete" />
                    </a>
                    <cart:savedCartDeleteModal savedCart="${savedCartData}" />
                </div>
            </div>
        </div>
    </div>
</div>