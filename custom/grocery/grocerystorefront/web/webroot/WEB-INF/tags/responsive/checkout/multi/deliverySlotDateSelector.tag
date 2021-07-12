<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="deliverySlotDate" required="true" type="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- <select id="shippingDelDate" name="shippingDelDate" class="form-control"> -->
<%--     <c:if test="${deliverySlotDate eq null}"> --%>
<!--         <option value=""> -->
<%--             <spring:theme code="checkout.summary.deliverySlot.notavailable" /> --%>
<!--         </option> -->
<%--     </c:if> --%>
<%--     <c:if test="${deliverySlotDate ne null}"> --%>
<!--         <option value=""> -->
<%--             <spring:theme code="checkout.summary.deliveryMode.selectDeliverySlotsEmpty" /> --%>
<!--         </option> -->
<%--     </c:if> --%>
<%--     <c:forEach items="${deliverySlotDate}" var="deliveryDate" varStatus="loop"> --%>
<%--         <multi-checkout:deliverySlotDateDetails deliveryDate="${deliveryDate}" isSelected="${loop.first}" /> --%>
<%--     </c:forEach> --%>
<!-- </select> -->
<div class="delivery-date-selector">
    <div class="custom-select-wrapper js-checkout-select-wrapper">
        <div class="custom-select js-checkout-select">
            <div class="custom-select__trigger js-checkout-select__trigger">
                <c:set var="proceedloop" scope="request" value="${true}" />
                <c:forEach items="${deliverySlotDate}" var="deliveryDate">
                    <c:if test="${not deliveryDate.isHolidayApplicable and proceedloop}">
                        <span>
                            <c:set var="initialvalue" value="${deliveryDate.deliveryDate}" />
                            <c:set var="proceedloop" scope="request" value="${false}" />
                            <fmt:formatDate type="date" dateStyle="long" value="${deliveryDate.deliveryDate}" />
                        </span>
                    </c:if>
                </c:forEach>
                <c:set var="proceedloop" scope="request" value="${true}" />
                <div class="arrow"></div>
            </div>
            <div class="custom-options js-checkout-custom-options">
                <c:forEach items="${deliverySlotDate}" var="deliveryDate" varStatus="loop">
                    <span class="custom-option js-checkout-custom-option ${deliveryDate.deliveryDate eq initialvalue ? 'selected' : '' } ${deliveryDate.isHolidayApplicable ? 'disabled' : ''}"
                        data-value="${deliveryDate.deliveryDate}">
                        <fmt:formatDate type="date" dateStyle="long" value="${deliveryDate.deliveryDate}" />
                    </span>
                </c:forEach>
            </div>
            <input type="hidden" class="set-value-to-input" value="${initialvalue}" id="shippingDelDate" name="shippingDelDate" />
        </div>
    </div>
</div>