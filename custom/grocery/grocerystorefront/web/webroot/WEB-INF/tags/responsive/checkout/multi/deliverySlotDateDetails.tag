<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="deliveryDate" required="true" type="de.hybris.platform.commercefacades.deliveryslot.data.DeliverySlotDayConfigData" %>
<%@ attribute name="isSelected" required="false" type="java.lang.Boolean" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<option value="${fn:escapeXml(deliveryDate.deliveryDate)}" ${deliveryDate.isHolidayApplicable ? 'style="color:red"' : ''}>
	${deliveryDate.deliveryDate}
</option>