<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="deliverySlotTime" required="true" type="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- <select id="shippingDelTime" name="shippingDelTime" class="form-control"> -->
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
<%--     <c:forEach items="${deliverySlotTime}" var="deliveryTime" varStatus="loop"> --%>
<%--         <multi-checkout:deliverySlotTimeDetails deliveryTime="${deliveryTime}" isSelected="${loop.first}" /> --%>
<%--     </c:forEach> --%>
<!-- </select> -->
<div class="checkout-delivery-slots">
    <c:forEach items="${deliverySlotTime}" var="deliveryTime" varStatus="loop">
        <div class="checkout-delivery-slot ${loop.first ? 'selected' : ''}">
            <input type="radio" id="${fn:escapeXml(deliveryTime.code)}" class="checkout-deliveryslot-radio" name="shippingDelTime"
                value="${fn:escapeXml(deliveryTime.openingTime)}-${fn:escapeXml(deliveryTime.closingTime)}" ${loop.first ? 'checked="checked"' : ''} />
            <div class="delivery-slot">
                <div class="delivery-slot-name">
                    <fmt:formatDate value="${deliveryTime.openingTime}" timeStyle="short" pattern="HH:mm" />
                    &nbsp;-&nbsp;
                    <fmt:formatDate value="${deliveryTime.closingTime}" timeStyle="short" pattern="HH:mm" />
                </div>
            </div>
        </div>
    </c:forEach>
</div>