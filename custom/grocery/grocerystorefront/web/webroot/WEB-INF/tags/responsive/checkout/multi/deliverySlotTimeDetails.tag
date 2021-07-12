<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="deliveryTime" required="true" type="de.hybris.platform.commercefacades.deliveryslot.data.DeliverySlotTimeConfigData" %>
<%@ attribute name="isSelected" required="false" type="java.lang.Boolean" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<option value="${fn:escapeXml(deliveryTime.openingTime)}-${fn:escapeXml(deliveryTime.closingTime)}">
	<fmt:formatDate value="${deliveryTime.openingTime}" pattern="hh:mm a" />&nbsp;-&nbsp;<fmt:formatDate value="${deliveryTime.closingTime}" pattern="hh:mm a" />
</option>