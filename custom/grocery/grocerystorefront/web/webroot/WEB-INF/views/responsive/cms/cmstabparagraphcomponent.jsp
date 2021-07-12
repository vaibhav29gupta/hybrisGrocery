<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<div class="tabhead">
	<h1>${fn:escapeXml(component.title)}</h1>
</div>
<div class="tabbody">${ycommerce:sanitizeHTML(component.content)}</div>
