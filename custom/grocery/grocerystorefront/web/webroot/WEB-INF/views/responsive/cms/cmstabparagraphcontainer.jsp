<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<div class="product-tabs">
	<c:forEach var="component" items="${components}">
		<cms:component component="${component}" />
	</c:forEach>
</div>
