<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="entry" required="true" type="de.hybris.platform.commercefacades.order.data.OrderEntryData"%>
<%@ attribute name="showPotentialPromotions" required="false" type="java.lang.Boolean"%>
<%@ attribute name="showHead" required="false" type="java.lang.Boolean"%>
<%@ attribute name="entrynumber" required="false" type="javax.servlet.jsp.jstl.core.LoopTagStatus"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:url value="${entry.product.url}" var="productUrl" />
<li class="cta-less-product ${entrynumber.count > 3 ? 'hide-initial' : ''}">
    <div class="thumb">
        <a href="${fn:escapeXml(productUrl)}">
            <product:productPrimaryImage product="${entry.product}" format="thumbnail" />
        </a>
    </div>
    <div class="item-details">
        <div class="essential-details">
            <div class="details">
                <div class="name">
                    <a href="${fn:escapeXml(productUrl)}">${fn:escapeXml(entry.product.name)}</a>
                </div>
                <div class="cart-brand">${fn:escapeXml(entry.product.brand)}<c:if test="${not empty entry.product.weight}">,</c:if>
                    <div class="cart-weight">${fn:escapeXml(entry.product.weight)}</div>
                </div>
                <div class="item-price base-price">
                    <span class="label-spacing">
                        <spring:theme code="order.itemPrice" />
                        :
                    </span>
                     <c:choose>
						<c:when test="${entry.product.discounted}">
							<div class="was-now-price">
							    <span class="item-price">
									<format:fromPrice priceData="${entry.product.price}" priceType="SALE" />
								</span>
							    <span class="wasPrice price-line-through">
									<format:fromPrice priceData="${entry.product.price}" />
								</span>
							</div>
						</c:when>
						<c:when test="${cartData.appliedProductPromotions ne null}">
						      <format:price priceData="${entry.product.price}" displayFreeForZero="true"/>
						</c:when>
						<c:otherwise>
							<div class="item-price">
								<format:price priceData="${entry.product.price}" displayFreeForZero="true"/>
							</div>
						</c:otherwise>
					</c:choose>
                </div>
                <div class="qty">
                    <span>
                        <spring:theme code="basket.page.qty" />
                        :
                    </span>${fn:escapeXml(entry.quantity)}</div>
            </div>
            <div class="price">
                <format:price priceData="${entry.totalPrice}" displayFreeForZero="true" />
            </div>
        </div>
        <div class="promotional-details">
            <c:if test="${ycommerce:doesPotentialPromotionExistForOrderEntryOrOrderEntryGroup(cartData, entry) && showPotentialPromotions}">
                <c:forEach items="${cartData.potentialProductPromotions}" var="promotion">
                    <c:set var="displayed" value="false" />
                    <c:forEach items="${promotion.consumedEntries}" var="consumedEntry">
                        <c:if test="${not displayed && ycommerce:isConsumedByEntry(consumedEntry,entry)}">
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
                        <c:if test="${not displayed && ycommerce:isConsumedByEntry(consumedEntry,entry)}">
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
    </div>
</li>