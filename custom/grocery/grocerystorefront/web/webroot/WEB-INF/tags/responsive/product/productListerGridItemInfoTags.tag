<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
	type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/responsive/action"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
<c:choose>
	<c:when
		test="${product.infoTags ne undefined && fn:length(product.infoTags) gt 0 }">
		<c:choose>
			<c:when test="${fn:length(product.infoTags) eq 1 }">
				<div class="plp-item-label"
					style="width: 150px; background-color: ${product.infoTags[0].color};">
					<span>${product.infoTags[0].tagName}</span> <span
						style="top: 0px; right: -18px;" class="right-white-triangle"></span>
				</div>
			</c:when>
			<c:when test="${fn:length(product.infoTags) gt 1 }">
				<div class="tag-group">
                <div class="tags no-space">
                    <span class="${product.infoTags[0].color}-tag tag1" style="background-color: ${product.infoTags[0].color}; border-color: ${product.infoTags[0].color}">${product.infoTags[0].tagName}</span>
                    <span class="${product.infoTags[1].color}-tag tag2"  style="background-color: ${product.infoTags[1].color}; border-color: ${product.infoTags[1].color}">${product.infoTags[1].tagName}</span>
                </div>
            </div> 
			</c:when>
		</c:choose>
	</c:when>
</c:choose>
</sec:authorize>