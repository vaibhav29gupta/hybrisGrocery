<%-- <%@ page trimDirectiveWhitespaces="true" %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="quantity" required="true" type="java.lang.Long" %>

<spring:htmlEscape defaultHtmlEscape="true" />
<c:url var="addToCartUrl" value="/cart-wrapper/add" />
<spring:url value="${product.url}/configuratorPage/{/configuratorType}"
	var="configureProductUrl" htmlEscape="false">
	<spring:param name="configuratorType" value="${configuratorType}" />
</spring:url>

<product:addToCartTitle />

<form:form method="post" id="configureForm-${product.code}"
	class="configure_form" action="${configureProductUrl}">
	<c:if test="${product.purchasable}">
		<input type="hidden" maxlength="3" size="1" name="qty"
			class="qty js-qty-selector-input" value="1">
	</c:if>
	<input type="hidden" name="productCodePost"
		value="${fn:escapeXml(product.code)}" />

	<c:if test="${empty showAddToCart ? true : showAddToCart}">
		<c:set var="buttonType">button</c:set>
		<c:if
			test="${product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }">
			<c:set var="buttonType">submit</c:set>
		</c:if>
		<c:choose>
			<c:when test="${fn:contains(buttonType, 'button')}">
				<c:if test="${product.configurable}">
					<button id="configureProduct" type="button"
						class="btn btn-primary btn-block js-enable-btn outOfStock"
						disabled="disabled">
						<spring:theme code="basket.configure.product" />
					</button>
				</c:if>
			</c:when>
			<c:otherwise>
				<c:if test="${product.configurable}">
					<button id="configureProduct" type="${buttonType}"
						class="btn btn-primary btn-block js-enable-btn"
						disabled="disabled" name="configure">
						<spring:theme code="basket.configure.product" />
					</button>
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:if>
</form:form> 

<form:form method="post" id="addToCartForm-${product.code}"
	class="add_to_cart_form" action="${addToCartUrl}">
	<input type="hidden" name="productCodePost"
		value="${fn:escapeXml(product.code)}" />
	<input type="hidden" name="shipmentType"
		value="${fn:escapeXml(shipmentType)}" />
	<c:if test="${empty showAddToCart ? true : showAddToCart}">
		<c:set var="buttonType">button</c:set>
		<c:if
			test="${product.isServiceable and product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }">
			<c:set var="buttonType">submit</c:set>
		</c:if>

		<c:choose>
			<c:when test="${fn:contains(buttonType, 'button')}">
				<div class="not-serviceable">
					<spring:theme code="product.not.serviceable" />
				</div>
			</c:when>
			<c:otherwise>
				<c:set var="isForceInStock"
					value="${product.stock.stockLevelStatus.code eq 'inStock' and empty product.stock.stockLevel}" />
				<c:choose>
					<c:when test="${isForceInStock}">
						<c:set var="maxQty" value="FORCE_IN_STOCK" />
					</c:when>
 					<c:otherwise> 
 						<c:set var="maxQty" value="${product.stock.stockLevel}" /> 
					</c:otherwise> 
				</c:choose>
				
				<c:set var="productCartQuantity"
					value="${quantity}" />
				<c:if
					test="${empty showAddToCart ? true : showAddToCart and product.purchasable}">
					<div
						class="qty-selector default-qty-selector input-group js-qty-selector-${product.code}"
						data-isshipmentset="${isShipmentSet}" >
						<span class="input-group-btn">
							<button
								class="btn btn-default js-qty-selector-minus js-minus-${product.code}"
								type="button" data-product-code="${product.code}"
								<c:if test="${productCartQuantity <= 0}"><c:out value="disabled='disabled'"/></c:if>>
								<i class="fas ${productCartQuantity == 1 ? 'fa-trash' : 'fa-minus' }"></i>
							</button>
						</span> <input type="text" maxlength="3"
							class="form-control js-qty-selector-input js-qty-selector-input-${product.code}"
							size="2" value="${productCartQuantity}"
							data-max="${fn:escapeXml(maxQty)}"
							data-product-code="${product.code}" data-min="0" name="qty"
							 /> <span class="input-group-btn">
							<button
								class="btn btn-default js-qty-selector-plus js-plus-${product.code}"
								type="button" data-product-code="${product.code}"
								<c:if test="${not isForceInStock and productCartQuantity eq fn:escapeXml(maxQty)}"><c:out value="disabled='disabled'" /></c:if>>
								<i class="fas fa-plus"></i>
							</button>
						</span>
					</div>
					 
					<div class="qty-selector input-group quanitySelecter" style="display:none">
						<span class="input-group-btn">
							<button class="btn btn-default quantity-left-minus" type="button" >
								<i class="fas fa-minus"></i>
							</button>
						</span> 
						<input type="hidden" value="${product.code}" class="productCode" />
						<input type="text" maxlength="3" name="quantity" class="form-control js-qty-selector-input input-number" size="2" value="1" min="1"/> <span class="input-group-btn">
							<button class="btn btn-default quantity-right-plus" type="button">
								<i class="fas fa-plus quantity-right-plus"></i>
							</button>
						</span>
					</div>

					<input type="hidden" name="initialQuantity-${product.code}"
						class="js-initial-quantity-${product.code}"
						value="${productCartQuantity}" />
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:if>
</form:form>
