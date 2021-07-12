<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="product-classifications">
	<c:if test="${not empty product.classifications}">
		<c:forEach items="${product.classifications}" var="classification">
			<div class="headline">${fn:escapeXml(classification.name)}</div>
				<div class="product-classification">
						<c:forEach items="${classification.features}" var="feature">
							<div class="pc-row">
								<div class="pc-attrib">${fn:escapeXml(feature.name)}</div>
								<div class="pc-attrib-value">
									<c:forEach items="${feature.featureValues}" var="value" varStatus="status">
										${fn:escapeXml(value.value)}
										<c:choose>
											<c:when test="${feature.range}">
												${not status.last ? '-' : fn:escapeXml(feature.featureUnit.symbol)}
											</c:when>
											<c:otherwise>
												${fn:escapeXml(feature.featureUnit.symbol)}
												${not status.last ? '<br/>' : ''}
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</div>
							</div>
						</c:forEach>
				</div>
		</c:forEach>
	</c:if>
</div>