<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
	type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="action" tagdir="/WEB-INF/tags/responsive/action"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	
	
<c:url value="${product.url}" var="productPDPurl" />
<c:url value="/cart/add/new" var="addToCartUrl" />

<c:set value="addToList" var="wishlistListPopup" />

<c:if test="${product.multidimensional}">
	<c:set value="js-select-options" var="wishlistListPopup" />
</c:if>
<c:set value="add" var="promotionClass" />
<c:if test="${product.infoTags ne undefined && fn:length(product.infoTags) gt 0 }">
	<c:set value="promotion-list" var="promotionClass" />
</c:if>

<div class="list-item-wrapper ${promotionClass}"padding: 0;">
	<c:forEach items="${product.paymentOptions}" var="paymentOption">
		<c:if test="${paymentOption.paymentType.code eq 'PIF'}">
			<c:set var="aliasCode" value="${paymentOption.aliasCode}"></c:set>
		</c:if>
	</c:forEach>

	<c:set var="productItem" value="product-list__item1" />
	<c:if test="${user.businessNature eq 'AmwayBusinessNature_1'}">
		<c:set var="productItem" value="product-list__item"></c:set>
	</c:if>
	<c:if
		test="${user.businessNature eq 'AmwayBusinessNature_7' || user.businessNature eq 'AmwayBusinessNature_2'}">
		<c:set var="productItem" value="product-list__item2"></c:set>
	</c:if>

	<div class=" ${productItem} js-qty-selector">
		<div class="product-list__item-content amwahover">
			 
			<product:productListerGridItemInfoTags product="${product}" />
			<div class="list-view-right">				
				<div class="product-list__item-detail">
					<p class="product-list__item-title list-title">
						<a class="product-list__item-title" target="_self"
							href="${productPDPurl}"> <c:choose>
								<c:when test="${fn:length(product.name) gt 100 }">
									<span>${fn:substring(product.name, 0, 97)}</span>
									<span>...</span>
								</c:when>
								<c:otherwise>
                                ${product.name}
                                </c:otherwise>
							</c:choose>
						</a>
					</p>
					<div class="item-pvbv">
						<p class="product-list__item-number">
							<c:choose>
								<c:when test="${product.productType eq 'BUNDLED'}">
									<span>#${product.alias}</span>
								</c:when>
								<c:otherwise>
									<span>#${product.alias}&nbsp;|&nbsp;${product.code}</span>

								</c:otherwise>
							</c:choose>
						</p>
					</div>

				</div>

				<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS')">
					<div class="product-list__item-retailprice abo-cost">
						<c:if test="${not empty product.price.formattedValue}">
							<span class="product-list__item-abovalue">${product.price.formattedValue}</span>
						</c:if>
					</div>
				</sec:authorize>

				<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
					<!-- Retail Price -->
					<c:if test="${user.businessNature eq 'AmwayBusinessNature_1'}">
						<div class="product-list__item-retailprice ">
							<span class="product-list__item-abovalue fontWeight600">${product.retailPrice.formattedValue}</span>

							<!-- ABO/IBO Price -->
							<c:if
								test="${not empty product.price.formattedValue && not empty product.retailPrice.formattedValue}">
								<span class="hide-in-desk price-separator">/&nbsp;</span>
							</c:if>
							<span class="product-list__item-abovalue abo-cost">${product.price.formattedValue}</span>
						</div>
						<c:if
							test="${not empty product.price.amwayValue.pointValue && not empty product.price.amwayValue.businessVolume}">
							<div class="product-list__item-retailprice">
								<span class="product-list__item-abovalue pvbv-grid"> <fmt:formatNumber
										type="number" maxFractionDigits="2" minFractionDigits="2"
										groupingUsed="false"
										value="${product.price.amwayValue.pointValue}" /> / <fmt:formatNumber
										type="number" minFractionDigits="2" maxFractionDigits="2"
										groupingUsed="false"
										value="${product.price.amwayValue.businessVolume}" />
								</span>
							</div>
						</c:if>
					</c:if>
					<!-- VIP Client -->
					<c:if test="${user.businessNature eq 'AmwayBusinessNature_2'}">
						<!-- Retail Price -->
						<div class="product-list__item-retailprice abo-cost">

							</span> <span class="product-list__item-abovalue fontWeight600">${product.price.formattedValue}</span>
						</div>
					</c:if>

					<!-- Employee -->
					<c:if test="${user.businessNature eq 'AmwayBusinessNature_7'}">
						<!-- Retail Price -->
						<div class="product-list__item-retailprice abo-cost">
							<span class="product-list__item-abovalue fontWeight600">${product.employeePrice.formattedValue}</span>
						</div>
					</c:if>
				</sec:authorize>
				
		<%-- 		<product:productStockStatusDisplay product="${product}" /> --%>

				<!-- Enabling Quantity selection for both Logged and Non-logged in User -->
				<c:set var="max" value="${product.maxOrderQuantity }" />
				<c:set var="maxString">${product.maxOrderQuantity}</c:set>
				<c:set var="availableStock" value="${product.stock.stockLevel }" />
				<c:if test="${product.isKitWithVariant}">
					<c:set var="max" value="1" />

				</c:if>

				<c:if
					test="${not product.stock.isStockAvailable || product.isKitWithVariant || (not empty product.preLaunchResponse && product.preLaunchResponse.allowedQuantity == 0)}">
					<c:set var="qtyDisabled" value=" disabled"></c:set>
					<c:set var="buttonDisabled" value="disable-button"></c:set>
					<c:set var="limitSum" value="limit-num"></c:set>
				</c:if>

				<div class="product-list__item-title product-list__item-aboprice">
					<span class="amount-cal list-total ${limitSum } ${qtyDisabled}">
						<a class="icon-del opera  ${buttonDisabled }" data="-"> <i
							class="fas fa-minus fa-delete"></i>
					</a> <input id="amount-cal-input" type="tel"
						class="amount-cal-input ${buttonDisabled }" value="1"
						data-max="${max}" maxlength="${fn:length(maxString)}"
						data-available-stock="${availableStock}"
						data-stock-status="${product.stock.stockLevelStatus}"
						data-product-code="${product.code}" /> <a
						class="icon-add opera ${buttonDisabled }" data="+"> <i
							class="fas fa-plus fa-add"></i></a>
					</span>
					<c:choose>
						<c:when
							test="${(product.multidimensional) || (fn:length(product.paymentOptions) gt 1 && product.productType ne 'BUNDLED')}">
							<span class="product-list__item-abovalue list-cart-btn">
								<button class="view-more-details js-select-options select-options-button btn primary add-to-cart"
									data-product-code="${product.code}" type="button">
									<spring:theme code="plp.producttile.select" />
								</button>
							</span>
						</c:when>
						<c:otherwise>
							<c:set var="disableAddToCart"
								value="${!product.stock.isStockAvailable || (not empty product.preLaunchResponse && product.preLaunchResponse.allowedQuantity == 0) || product.productSaleType eq 'AUTOSHIP'}" />
							<span class="product-list__item-abovalue list-cart-btn">
							<form action="${addToCartUrl}" method="post"
								class="add_to_cart_form" name="${product.code}-addToCart">

								<input id="amount-cal-input" type="hidden" maxlength="2"
									size="1" name="qty" class="qty js-qty-selector-input" value="1">
								<input type="hidden" name="aliasCode" value="${aliasCode}" /> <input
									type="hidden" name="productCode" value="${product.code}" /> <input
									type="hidden" name="volumeAbos" value="" /> <input
									type="hidden" name="isKitProduct"
									value="${product.isKitProduct}" /> <input type="hidden"
									class="isChangeSelectionPopup" value="true" /> <input
									type="hidden" name="isHazardous" value="${product.isHazardous}" />

								
									<button class="btn primary add-to-cart" type="button"
										<c:if test="${disableAddToCart}">disabled="disabled"</c:if>>
										<c:choose>
											<c:when
												test="${product.isKitWithVariant || (fn:length(product.paymentOptions) gt 1 && product.productType eq 'BUNDLED')}">
												<spring:theme code="plp.producttile.select" />
											</c:when>
											<c:otherwise>
												<spring:theme code="plp.producttile.cart" />
											</c:otherwise>
										</c:choose>
									</button>
								
							</form>
							</span>
						</c:otherwise>
					</c:choose>
					<div
					class="largest-amount-alert hidden-xs largest-amount-alert-${product.code}"></div>
				</div>

				<div class="amway-theme product-list__item-link">
					<div class="plp-add-to-shopping-list">
					<c:choose>
						<c:when
							test="${(product.multidimensional) || (fn:length(product.paymentOptions) gt 1 && product.productType ne 'BUNDLED')}">
							<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
								
									<div
										class="product-list__item-link-text product-list__item-link-common js-add-list-shopping-button">
										<c:url var="addToShoppingListUrl"
											value="/shopping-lists/data/all" />
										<a class="col-xs-12 ${wishlistListPopup}"
											data-add-to-shopping-list-url="${addToShoppingListUrl}"
											data-product-code="${product.code}"> <i
											class="far fa-heart"></i> <span class="hide-ipdad-view"><spring:theme
													code="plp.producttile.shoppinglist" /></span>
										</a>
									</div>
									<div
										class="cart-detail__dropdown-menu dropdown-menu shopping-list-popup-wrapper plp-add-to-shopping-list-popup shoppinglist-popup"
										role="menu"></div>
								


							</sec:authorize>
						</c:when>
						<c:otherwise>
							<c:set var="disableAddToCart"
								value="${!product.stock.isStockAvailable || (not empty product.preLaunchResponse && product.preLaunchResponse.allowedQuantity == 0)}" />
							<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
								
									<div
										class="product-list__item-link-text product-list__item-link-common js-add-list-shopping-button">
										<c:url var="addToShoppingListUrl"
											value="/shopping-lists/data/all" />
										<c:choose>
											<c:when
												test="${product.stock.stockLevelStatus ne 'noLongerAvailable' && product.stock.stockLevelStatus ne 'notYetAvailable' && !(not empty product.preLaunchResponse && product.preLaunchResponse.allowedQuantity == 0)}">
												<a class="addToList col-xs-12"
													data-add-to-shopping-list-url="${addToShoppingListUrl}"
													data-product-code="${product.code}"> <i
													class="far fa-heart"></i> <span class="hide-ipdad-view"><spring:theme
															code="plp.producttile.shoppinglist" /></span>
												</a>
											</c:when>
											<c:otherwise>
												<span class="addToListDisabled col-xs-12"> <i
													class="far fa-heart"></i> <span class="hide-ipdad-view"><spring:theme
															code="plp.producttile.shoppinglist" /></span>
												</span>
											</c:otherwise>
										</c:choose>
										<div
											class="cart-detail__dropdown-menu dropdown-menu shopping-list-popup-wrapper plp-add-to-shopping-list-popup shoppinglist-popup"
											role="menu"></div>
									</div>
								
							</sec:authorize>
						</c:otherwise>
					</c:choose>
					<sec:authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
						<c:choose>
							<c:when
								test="${product.productSaleType eq 'AUTOSHIP' || product.productSaleType eq 'BOTH'}">
								<c:choose>
									<c:when
										test="${product.stock.stockLevelStatus ne 'noLongerAvailable' && product.stock.stockLevelStatus ne 'notYetAvailable' && (empty product.preLaunchResponse)}">
										<div
											class="product-list__item-link-text product-list__item-link-common text-right">
											<a class="add-to-autoship"
												data-product-code="${product.code}" qty-selected="1"><i
												class="fas fa-truck"></i> <spring:theme
													code="plp.producttile.autoShip" /></a>
										</div>
									</c:when>
									<c:otherwise>
										<div
											class="product-list__item-link-text product-list__item-link-common text-right">
											<a class="disabled"><i
												class="fas fa-truck"></i> <spring:theme
													code="plp.producttile.autoShip" /></a>
										</div>

									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<div
									class="product-list__item-link-text product-list__item-link-common text-right">
									<a class="disabled"><i
										class="fas fa-truck"></i> <spring:theme
											code="plp.producttile.autoShip" /></a>
								</div>
							</c:otherwise>
						</c:choose>
					</sec:authorize>

				</div>

				</div>


			</div>
		</div>
	</div>
</div>