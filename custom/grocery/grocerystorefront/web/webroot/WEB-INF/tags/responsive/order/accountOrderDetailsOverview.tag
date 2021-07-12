<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<div class="account-section-content account-list account-order-history no-margin-list">
    <div class="account-cards card-select">
        <div class="card header-card">
            <div class="card-heading w100">
                <span class="item-label">
                    <spring:message code="text.account.orderHistory.orderNumber" text="Order Number" />
                </span>
                <span class="item-value"> ${fn:escapeXml(orderData.code)} </span>
            </div>
            <div class="card-eq-body">
                <c:if test="${not empty orderData.statusDisplay}">
                    <div class="item-group o3 w33">
                        <span class="item-label">
                            <spring:theme code="text.account.orderHistory.orderStatus" text="Status" />
                        </span>
                        <span class="item-value">
                            <spring:message code="text.account.order.status.display.${orderData.statusDisplay}" />
                        </span>
                    </div>
                </c:if>
                <div class="item-group o4 w33">
                    <span class="item-label">
                        <spring:theme code="text.account.orderHistory.datePlaced" text="Date Placed" />
                    </span>
                    <span class="item-value">
                        <fmt:formatDate value="${order.created}" dateStyle="medium" timeStyle="short" type="both" />
                    </span>
                </div>
                <div class="item-group o5 w33">
                    <span class="item-label">
                        <spring:theme code="text.account.order.total" text="Totals" />
                    </span>
                    <span class="item-value">
                        <format:price priceData="${order.totalPriceWithTax}" />
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
