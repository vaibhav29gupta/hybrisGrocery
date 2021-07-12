<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="entry" required="true" type="de.hybris.platform.commercefacades.order.data.OrderEntryData"%>
<%@ attribute name="index" required="false" type="java.lang.Integer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="grid" tagdir="/WEB-INF/tags/responsive/grid"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/responsive/order"%>
<%--
    Represents single cart item on cart page
 --%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:set var="errorStatus" value="<%=de.hybris.platform.catalog.enums.ProductInfoStatus.valueOf(\"ERROR\")%>" />
<c:set var="entryNumberHtml" value="${fn:escapeXml(entry.entryNumber)}" />
<c:set var="productCodeHtml" value="${fn:escapeXml(entry.product.code)}" />
<c:set var="quantityHtml" value="${fn:escapeXml(entry.quantity)}" />
<c:if test="${empty index}">
    <c:set property="index" value="${entryNumber}" />
</c:if>
<c:if test="${not empty entry}">
    <c:url value="${entry.product.url}" var="productUrl" />
    <li class="item__list--item cart-item">
        <%-- product image --%>
        <div class="item__image cart-item-image">
            <a href="${fn:escapeXml(productUrl)}">
                <product:productPrimaryImage product="${entry.product}" format="thumbnail" />
            </a>
        </div>
        <%-- product name, code, promotions --%>
        <div class="item__info cart-item-details">
            <ycommerce:testId code="cart_product_name">
                <a href="${fn:escapeXml(productUrl)}">
                    <span class="item__name cart-item-name">${fn:escapeXml(entry.product.name)}</span>
                </a>
            </ycommerce:testId>
            <div class="brand">${fn:escapeXml(entry.product.brand)}<c:if test="${not empty entry.product.weight}">,</c:if>
                <div class="weight">${fn:escapeXml(entry.product.weight)}</div>
            </div>
            <div class="item__code shownone">${productCodeHtml}</div>
            <%-- availability --%>
            <div class="item__stock">
                <c:set var="entryStock" value="${entry.product.stock.stockLevelStatus.code}" />
                <c:forEach items="${entry.product.baseOptions}" var="option">
                    <c:if test="${not empty option.selected and option.selected.url eq entry.product.url}">
                        <c:forEach items="${option.selected.variantOptionQualifiers}" var="selectedOption">
                            <div>
                                <strong>${fn:escapeXml(selectedOption.name)}:</strong>
                                <span>${fn:escapeXml(selectedOption.value)}</span>
                            </div>
                            <c:set var="entryStock" value="${option.selected.stock.stockLevelStatus.code}" />
                        </c:forEach>
                    </c:if>
                </c:forEach>
                <div>
                    <c:choose>
                        <c:when test="${not empty entryStock and entryStock ne 'outOfStock' or entry.product.multidimensional}">
                            <!--                             <span class="stock"> -->
                            <%--                                 <spring:theme code="product.variants.in.stock" /> --%>
                            <!--                             </span> -->
                        </c:when>
                        <c:otherwise>
                            <span class="out-of-stock">
                                <spring:theme code="product.variants.out.of.stock" />
                            </span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <%-- price --%>
                <div class="item__price cart-item-price base-price">
                    <span class="visible-xs visible-sm">
                        <spring:theme code="basket.page.itemPrice" />
                        :
                    </span>
                    <c:choose>
						<c:when test="${entry.product.discounted}">
							<div class="was-now-price">
								<span class="price">
									<format:fromPrice priceData="${entry.product.price}" priceType="SALE" />
								</span>
							    <span class="wasPrice price-line-through">
									<format:fromPrice priceData="${entry.product.price}" />
								</span>
							</div>
						</c:when>
						<c:otherwise>
							<div class="price">
								<format:price priceData="${entry.product.price}" displayFreeForZero="true"/>
							</div>
						</c:otherwise>
					</c:choose>
                </div>
                <div class="item__total js-item-total cart-item-total-price-mobile shownone">
                    <format:price priceData="${entry.totalPrice}" displayFreeForZero="true" />
                </div>
            </div>
            <c:if test="${ycommerce:doesPotentialPromotionExistForOrderEntryOrOrderEntryGroup(cartData, entry)}">
                <c:forEach items="${cartData.potentialProductPromotions}" var="promotion">
                    <c:set var="displayed" value="false" />
                    <c:forEach items="${promotion.consumedEntries}" var="consumedEntry">
                        <c:if test="${not displayed && ycommerce:isConsumedByEntry(consumedEntry,entry) && not empty promotion.description}">
                            <c:set var="displayed" value="true" />
                            <div class="potential-promo">
                                <i class="fa fa-asterisk"></i>
                                <div class="promo">
                                    <ycommerce:testId code="cart_potentialPromotion_label">
                                             ${ycommerce:sanitizeHTML(promotion.description)}
                                         </ycommerce:testId>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </c:if>
            <c:if test="${ycommerce:doesAppliedPromotionExistForOrderEntryOrOrderEntryGroup(cartData, entry)}">
                <c:forEach items="${cartData.appliedProductPromotions}" var="promotion">
                    <c:set var="displayed" value="false" />
                    <c:forEach items="${promotion.consumedEntries}" var="consumedEntry">
                        <c:if test="${not displayed && ycommerce:isConsumedByEntry(consumedEntry,entry) }">
                            <c:set var="displayed" value="true" />
                            <div class="applied-promo">
                                <i class="fa fa-certificate"></i>
                                <div class="promo">
                                    <ycommerce:testId code="cart_appliedPromotion_label">
                                        ${ycommerce:sanitizeHTML(promotion.description)}
                                    </ycommerce:testId>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </c:if>
        </div>
        <%-- quantity --%>
        <div class="item__quantity cart-quantity cart-item-quantity">
            <product:productAddToCart product="${entry.product}" quantity="${quantityHtml}" />
        </div>
        <%-- total --%>
        <ycommerce:testId code="cart_totalProductPrice_label">
            <div class="item__total js-item-total cart-item-total-price">
                <format:price priceData="${entry.totalPrice}" displayFreeForZero="true" />
            </div>
        </ycommerce:testId>
    </li>
</c:if>
