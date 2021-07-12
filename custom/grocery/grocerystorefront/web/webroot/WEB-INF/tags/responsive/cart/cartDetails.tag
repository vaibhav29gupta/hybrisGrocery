<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="emptyCartImageUrl" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="grid" tagdir="/WEB-INF/tags/responsive/grid"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart"%>
<c:url value="/" var="homePageUrl" />
<c:url value="/cart/checkout" var="checkoutUrl" />
<spring:htmlEscape defaultHtmlEscape="true" />
<div class="cart-length" data-cartlength="${fn:length(cartData.entries)}"></div>
<c:choose>
    <c:when test="${not empty cartData.entries }">
        <div class="mini-cart js-mini-cart">
            <ycommerce:testId code="mini-cart-popup">
                <div class="mini-cart-body">
                    <ol class="cart-layer-list mini-cart-list">
                        <c:forEach items="${cartData.entries}" var="entry" end="${cartData.totalItems - 1}">
                            <c:url value="${entry.product.url}" var="entryProductUrl" />
                            <li class="cart-layer-item cart-item-${entry.product.code }" data-item-code="${entry.product.code }">
                                <div class="cart-icon">
                                    <a href="${entryProductUrl}">
                                        <product:productPrimaryImage product="${entry.product}" format="thumbnail" />
                                    </a>
                                </div>
                                <div class="cart-details">
                                    <a class="name" href="${entryProductUrl}">${fn:escapeXml(entry.product.name)}</a>
                                    <div class="cart-additional-details">
                                        <div class="cart-brand">${fn:escapeXml(entry.product.brand)}<c:if test="${not empty entry.product.weight}">,</c:if>
                                        </div>
                                        <div class="cart-weight">${fn:escapeXml(entry.product.weight)}</div>
                                    </div>
                                </div>
                                <div class="cart-quantity">
                                    <product:productAddToCart product="${entry.product}" quantity="${fn:escapeXml(entry.quantity)}" />
                                </div>
                                <div class="cart-price">
                                    <format:price priceData="${entry.totalPrice}" />
                                </div>
                            </li>
                        </c:forEach>
                        <li class="saved-cart-actions">
                            <cart:newCartButton />
                            <c:if test="${not empty savedCartCount and savedCartCount ne 0}">
                                <cart:savedCartButton />
                            </c:if>
                        </li>
                        <li class="save-cart-form shownone">
                            <spring:url value="/cart/save" var="actionUrl" htmlEscape="false" />
                            <cart:saveCartModal titleKey="text.save.cart.title" actionUrl="${actionUrl}" messageKey="basket.save.cart.info.msg" />
                        </li>
                    </ol>
                </div>
            </ycommerce:testId>
        </div>
    </c:when>
    <c:otherwise>
        <div class="cart-layer-empty-cart">
            <div class="heading">
                <spring:theme code="empty.cart" />
            </div>
            <div class="sub-heading">
                <spring:theme code="empty.cart.message" />
            </div>
            <div class="cart-layer-empty-cart-body">
                <img class="card-empty-image" src="${emptyCartImageUrl}" alt="Card image cap">
            </div>
        </div>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${not empty cartData.entries }">
        <div class="fixed-cart-footer express_checkout_mini_cart">
            <c:if test="${expressCheckoutAllowed}">
                <div class="checkbox ">
                    <label>
                        <c:url value="/checkout/multi/express" var="expressCheckoutUrl" scope="session"/>
                        <input type="checkbox" class="express-checkout-checkbox"
                               data-express-checkout-url="${fn:escapeXml(expressCheckoutUrl)}">
                        <spring:theme text="I would like to Express checkout" code="cart.expresscheckout.checkbox"/>
                    </label>
                </div>
            </c:if>
        </div>
        <div class="fixed-cart-footer show-checkout">
                <a class="btn btn-default rounded js-continue-shopping-button"
                   data-continue-shopping-url="${fn:escapeXml(homePageUrl)}">
                    <spring:theme text="Continue Shopping" code="general.continue.shopping"/>
                </a>
                <a class="btn btn-primary rounded js-continue-checkout-button" data-checkout-url="${checkoutUrl}">
                    <spring:theme text="Checkout" code="general.checkout.shopping"/>
                </a>

        </div>
    </c:when>
    <c:otherwise>
        <div class="fixed-cart-footer ${not empty savedCartCount and savedCartCount ne 0 ? 'show-checkout' : ''}">
            <c:if test="${not empty savedCartCount and savedCartCount ne 0}">
                <cart:savedCartButton titleKey="basket.restore.cart" buttonType="btn-neutral" />
            </c:if>
            <a class="btn btn-primary rounded js-shopping-button" href="${fn:escapeXml(homePageUrl)}">
                <spring:theme text="Continue Shopping" code="general.continue.shopping" />
            </a>
        </div>
    </c:otherwise>
</c:choose>
