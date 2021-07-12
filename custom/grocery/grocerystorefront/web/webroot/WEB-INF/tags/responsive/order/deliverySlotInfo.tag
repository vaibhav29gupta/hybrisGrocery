<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order"%>
<%@ attribute name="deliveryDate" required="false" type="java.util.Date"%>
<%@ attribute name="deliveryStartTime" required="false" type="java.util.Date"%>
<%@ attribute name="deliveryEndTime" required="false" type="java.util.Date"%>

<div>
<fmt:formatDate value="${deliveryDate}" dateStyle="medium" timeStyle="short" type="date"/>
</div>
<div>
<fmt:formatDate value="${deliveryStartTime}" dateStyle="medium" timeStyle="short" type="time"/>-
<fmt:formatDate value="${deliveryEndTime}" dateStyle="medium" timeStyle="short" type="time"/>
</div>