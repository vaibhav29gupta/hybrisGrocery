<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<c:forEach items="${order.unconsignedEntries}" var="entry" varStatus="loop">

<div class="customer-order account-cards order-select">
    <div class="order-status">
        <div class="order-pending-header orderPending">
            <spring:theme code="text.account.order.unconsignedEntry.status.pending" />
        </div>

        <c:choose>
            <c:when test="${entry.deliveryPointOfService ne null}">
                <div class="well-content">
                	<order:storeAddressItem deliveryPointOfService="${entry.deliveryPointOfService}" />
                </div>
            </c:when>
            <c:otherwise>
                <div class="well-content">
                    <div class="row">
                        <div class="col-md-5">
                            <div class="order-ship-to">
                                <div class="label-order">
                                    <spring:theme code="text.account.order.shipto" text="Ship To" />
                                </div>
                                <div class="value-order">
                                    <order:addressItem address="${orderData.deliveryAddress}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

<div class="order-entry-details">
    <div class="item__list">
        <div class="hidden-xs">
            <div class="item__list--header cart-list-header">
                <div class="item__image cart-header-image"></div>
                <div class="item__info cart-header-info"><spring:theme code="basket.page.item"/></div>
                <div class="item__quantity cart-header-quantity"><spring:theme code="basket.page.qty"/></div>
                <div class="item__total--column cart-header-total"><spring:theme code="basket.page.total"/></div>
            </div>
        </div>
        </div>
        <order:orderEntryDetails orderEntry="${entry}" order="${order}" itemIndex="${loop.index}"/>
    
    </div>
  </div>
</c:forEach>