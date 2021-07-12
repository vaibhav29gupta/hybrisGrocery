<%@ page trimDirectiveWhitespaces="true" contentType="application/json"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart"%>

{ "error":"${error}",
"entries":[
	<c:forEach items="${cartData.entries}" var="entry" varStatus="entryNumber">
		{
			"productCode" : "${ycommerce:encodeJSON(entry.product.code)}",
			"quantity" : "${ycommerce:encodeJSON(entry.quantity)}"
		}<c:if test="${!entryNumber.last}">,</c:if>
	</c:forEach>
],
"listOfUnAffectedProducts":[
	<c:forEach items="${listOfUnAffectedProducts}" var="productCode" varStatus="entryNumber">
		"${ycommerce:encodeJSON(productCode)}"<c:if test="${!entryNumber.last}">,</c:if>
	</c:forEach>
],
"cartLayer":"<spring:escapeBody javaScriptEscape="true" htmlEscape="false">
	<spring:htmlEscape defaultHtmlEscape="true"><cart:cartDetails cartData="${cartData }" 
	emptyCartImageUrl="${emptyCartImage.url  }" /></spring:htmlEscape></spring:escapeBody>"
}
