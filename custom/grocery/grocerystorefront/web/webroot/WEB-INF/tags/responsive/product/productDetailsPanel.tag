<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row productDetailsPanel">
    <div class="col-sm-1 col-lg-1"></div>
    <div class="col-xs-10 col-xs-push-1 col-sm-5 col-sm-push-0 col-lg-4">
        <product:productImagePanel galleryImages="${galleryImages}" />
    </div>
    <div class="col-sm-1"></div>
    <div class="clearfix hidden-sm hidden-md hidden-lg"></div>
    <div class="col-sm-5 col-lg-4 product-width">
        <product:productEmphasis product="${product}" />
        <div class="border-pdp ${sessionScope.borderColor}">
            <div class="product-main-info">
                <div class="product-details page-title">
                    <ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
                        <div class="name">${fn:escapeXml(product.name)}</div>
                        <div class="brand">${fn:escapeXml(product.brand)}<c:if test="${not empty product.brand}">,</c:if>
                        </div>
                        <div class="weight">${fn:escapeXml(product.weight)}</div>
                    </ycommerce:testId>
                    <product:productReviewSummary product="${product}" showLinks="true" />
                </div>
                <div class="product-details">
                    <%-- 					<product:productPromotionSection product="${product}" /> --%>
                    <ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
                        <product:productPricePanel product="${product}" />
                    </ycommerce:testId>
                    <div class="description">${ycommerce:sanitizeHTML(product.summary)}</div>
                </div>
                <cms:pageSlot position="VariantSelector" var="component" element="div" class="page-details-variants-select">
                    <cms:component component="${component}" />
                </cms:pageSlot>
                <c:if test="${product.variantType eq null}">
                    <div class="product-actions" data-productcode="${product.code}">
                        <cms:pageSlot position="AddToCart" var="component">
                            <cms:component component="${component}" />
                        </cms:pageSlot>
                        <div class="product-secondary-actions">
                            <div class="product-wishlist">
                                <product:productWishlistPanel product="${product}" />
                            </div>
                            <product:productSharePanel product="${product}" />
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <div class="col-sm-1 col-lg-3"></div>
</div>