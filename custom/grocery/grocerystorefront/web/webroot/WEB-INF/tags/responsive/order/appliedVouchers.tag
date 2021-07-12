<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.AbstractOrderData"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<spring:url value="/checkout/multi/cart-review/voucher/apply" var="applyVoucherAction" htmlEscape="false" />
<spring:url value="/checkout/multi/cart-review/voucher/remove" var="removeVoucherAction" htmlEscape="false" />
<c:set var="containerClass">
    <c:choose>
        <c:when test="${not empty errorMsg}">has-error</c:when>
        <c:when test="${not empty successMsg}">has-success</c:when>
        <c:otherwise></c:otherwise>
    </c:choose>
</c:set>
<c:choose>
    <c:when test="${isCartReviewStep ne null and isCartReviewStep eq true}">
        <div class="coupon-section">
            <div class="checkout-coupon-list">
                <span class="coupon-list__headline">
                    <spring:theme code="text.account.order.appliedCoupons" />
                </span>
                <div class="form-group js-voucher-respond ${containerClass}">
                    <spring:theme code="text.voucher.apply.input.placeholder" var="voucherInputPlaceholder" htmlEscape="false" />
                    <%-- 				<label class="control-label cart-voucher__label" for="voucher-code"><spring:theme --%>
                    <%-- 						code="text.voucher.apply.input.label" /></label> --%>
                    <form:form id="applyVoucherForm" action="${applyVoucherAction}" method="post" modelAttribute="voucherForm">
                        <!-- <input type="text" /> -->
                        <form:input cssClass="js-voucher-code cart-voucher__input form-control input-sm" name="voucher-code" id="js-voucher-code-text" maxlength="100"
                            placeholder="${voucherInputPlaceholder}" path="voucherCode" disabled="${disableUpdate}" />
                        <c:if test="${not disableUpdate}">
                            <button type="button" id="js-voucher-apply-btn" class="btn btn-primary btn-small cart-voucher__btn">
                                <spring:theme code="text.voucher.apply.button.label" />
                            </button>
                        </c:if>
                    </form:form>
                    <div class="js-voucher-validation-container help-block cart-voucher__help-block">${fn:escapeXml(errorMsg)}${fn:escapeXml(successMsg)}</div>
                </div>
                <ul id="js-applied-vouchers" class="selected_product_ids clearfix voucher-list">
                    <c:forEach items="${cartData.appliedVouchers}" var="voucher" varStatus="loop">
                        <li class="voucher-list-item">
                            <form:form id="removeVoucherForm${loop.index}" action="${removeVoucherAction}" method="post" modelAttribute="voucherForm">
                                <span class="js-release-voucher voucher-list-item-box" id="voucher-code-${fn:escapeXml(voucher)}">
                                    ${fn:escapeXml(voucher)}
                                    <form:input hidden="hidden" value="${fn:escapeXml(voucher)}" path="voucherCode" />
                                    <span class="fa fa-times js-release-voucher-remove-btn voucher-list-item-remove"></span>
                                </span>
                            </form:form>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </c:when>
    <c:when test="${fn:length(order.appliedVouchers) > 0}">
        <div class="coupon-section">
            <div class="checkout-coupon-list">
                <ycommerce:testId code="orderDetails_appliedVouchers_label">
                    <span class="coupon-list__headline">
                        <spring:theme code="text.account.order.appliedCouponCodes" />
                    </span>
                    <ul class="voucher-list">
                        <c:forEach items="${order.appliedVouchers}" var="voucher">
                            <li class="voucher-list-item applied-voucher">
                                <span class="voucher-list-item-box">${fn:escapeXml(voucher)}</span>
                            </li>
                        </c:forEach>
                        <c:if test="${isCartReviewStep eq null and isCartReviewStep ne true}">
                            <div class="coupon-code-link">
                                <c:forEach items="${checkoutSteps}" var="checkoutStep" varStatus="status">
                                    <c:if test="${checkoutStep.stepNumber < 2}">
                                        <c:url value="${checkoutStep.url}" var="stepUrl" />
                                        <a href="${stepUrl}" class="btn btn-a">
                                            <spring:theme code="checkout.multi.coupon.link" />
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </c:if>
                    </ul>
                </ycommerce:testId>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="coupon-section">
            <div class="checkout-coupon-list">
                <ycommerce:testId code="orderDetails_appliedVouchers_label">
                    <ul class="voucher-list no-vouchers">
                        <c:if test="${isCartReviewStep eq null and isCartReviewStep ne true}">
                            <div class="coupon-code-link">
                                <c:forEach items="${checkoutSteps}" var="checkoutStep" varStatus="status">
                                    <c:if test="${checkoutStep.stepNumber < 2}">
                                        <c:url value="${checkoutStep.url}" var="stepUrl" />
                                        <a href="${stepUrl}" class="btn btn-a">
                                            <spring:theme code="checkout.multi.coupon.link" />
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </c:if>
                    </ul>
                </ycommerce:testId>
            </div>
        </div>
    </c:otherwise>
</c:choose>
