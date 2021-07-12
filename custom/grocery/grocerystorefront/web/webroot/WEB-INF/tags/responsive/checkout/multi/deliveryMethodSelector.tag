<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="deliveryMethods" required="true" type="java.util.List"%>
<%@ attribute name="selectedDeliveryMethodId" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/responsive/checkout/multi"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:forEach items="${deliveryMethods}" var="deliveryMethod">
    <div class="checkout-delivery-mode ${deliveryMethod.code eq selectedDeliveryMethodId ? 'selected' : ''}">
        <input type="radio" id="${fn:escapeXml(deliveryMethod.code)}" class="checkout-delivery-radio" name="delivery_method" value="${fn:escapeXml(deliveryMethod.code)}"
            ${deliveryMethod.code eq selectedDeliveryMethodId ? 'checked="checked"' : ''} />
        <div class="delivery-mode">
            <div class="delivery-mode-name">${fn:escapeXml(deliveryMethod.name)}</div>
            <div class="delivery-mode-description">${fn:escapeXml(deliveryMethod.description)}</div>
            <div class="delivery-mode-cost">${fn:escapeXml(deliveryMethod.deliveryCost.formattedValue)}</div>
        </div>
    </div>
</c:forEach>
<%--     <option value="${fn:escapeXml(deliveryMethod.code)}" ${isSelected ? 'selected="selected"' : ''}> --%>
        <%--         ${fn:escapeXml(deliveryMethod.name)}&nbsp;-&nbsp;${fn:escapeXml(deliveryMethod.description)}&nbsp;-&nbsp;${fn:escapeXml(deliveryMethod.deliveryCost.formattedValue)} --%>
<!-- </option> -->
<!-- <select id="delivery_method" name="delivery_method" class="form-control"> -->
<%-- 		<multi-checkout:deliveryMethodDetails deliveryMethod="${deliveryMethod}" isSelected="${deliveryMethod.code eq selectedDeliveryMethodId}"/> --%>
<!-- </select> -->
