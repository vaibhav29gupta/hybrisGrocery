<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="order" required="true"
	type="de.hybris.platform.commercefacades.order.data.OrderData"%>
<%@ attribute name="storeDetails" required="false"
	type="de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData"%>
<%@ attribute name="statusDate" required="false" type="java.util.Date"%>
<%@ attribute name="isDeliveryModeSet" required="true"
	type="java.lang.Boolean"%>
<%@ attribute name="deliverySlotInfo" required="true"
	type="de.hybris.platform.commercefacades.deliveryslot.info.data.DeliverySlotInfoData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:htmlEscape defaultHtmlEscape="true" />

<div class="account-orderdetail account-order-history no-margin-list">
	<div class="customer-order ">

		<div class="order-delivery-history">
			<ycommerce:testId code="orderDetail_itemHeader_section">
				<div class="well-content">
					<c:choose>
						<c:when test="${isDeliveryModeSet eq false}">
							<ycommerce:testId code="orderDetail_storeDetails_section">
								<order:storeAddressItem deliveryPointOfService="${storeDetails}"
									inProgress="false" statusDate="${statusDate}" />
							</ycommerce:testId>
						</c:when>
						<c:otherwise>
							<div class="order-ship-to">
								<div class="order-shipTo-address">
									<ycommerce:testId code="orderDetail_deliveryAddress_section">
										<div class="label-order">
											<spring:theme code="text.account.order.shipto" />
										</div>
										<div class="value-order">
											<order:addressItem address="${orderData.deliveryAddress}" />
										</div>
									</ycommerce:testId>
								</div>
								<div class="order-shipping-method">
									<ycommerce:testId code="orderDetail_deliveryMethod_section">
										<order:deliveryMethodItem order="${orderData}" />
									</ycommerce:testId>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</ycommerce:testId>
		</div>

		<c:if test="${not empty deliverySlotInfo}">
			<c:choose>
				<c:when test="${isDeliveryModeSet eq true}">
					<div>
						<spring:theme code="text.delivery.date.on" />
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<spring:theme code="text.pickup.date.on" />
					</div>
				</c:otherwise>
			</c:choose>
			<order:deliverySlotInfo
				deliveryDate="${deliverySlotInfo.deliveryDate}"
				deliveryStartTime="${deliverySlotInfo.deliveryStartTime}"
				deliveryEndTime="${deliverySlotInfo.deliveryEndTime}" />
		</c:if>


		<div class="order-entry-details">
			<div class="item__list">
				<div class="hidden-xs">
					<div class="item__list--header cart-list-header">
						<div class="item__image cart-header-image"></div>
						<div class="item__info cart-header-info">
							<spring:theme code="basket.page.item" />
						</div>
						<div class="item__quantity cart-header-quantity">
							<spring:theme code="basket.page.qty" />
						</div>
						<div class="item__total--column cart-header-total">
							<spring:theme code="basket.page.total" />
						</div>
					</div>
				</div>
			</div>
			<c:forEach items="${orderData.entries}" var="entry" varStatus="loop">
				<order:orderEntryDetails orderEntry="${entry}" order="${order}"
					itemIndex="${loop.index}" />
			</c:forEach>
		</div>
	</div>
</div>


