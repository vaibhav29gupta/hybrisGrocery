<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="isOrderForm" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:set var="requestUrl" value="${pageContext.request.requestURL}" />
<c:set var="baseUrl">${fn:substringBefore(requestUrl,'/WEB-INF')}</c:set>
<c:set var="productUrl" value="${baseUrl}/p/${product.code}" />
<%-- <c:url value="/p/${product.code}" var="productUrl" /> --%>
<div class="share-product">
    <div class="share-icon">
        <i class="fa fa-share" aria-hidden="true"></i><span
        class="shareText"><spring:theme code="product.share" /> </span>
    </div>
    <div class="share-tray">
        <div class="shownone visible-xs whatsapp col-md-1">
            <a target="_blank" href="whatsapp://send?text=${productUrl}" data-action="share/whatsapp/share" title="Share on whatsapp">
                <i class="fa fa-whatsapp fa-lg"></i>
            </a>
        </div>
        <div class="hidden-xs whatsapp-desktop">
            <a target="_blank" href="https://web.whatsapp.com/send?text=${productUrl}" data-action="share/whatsapp/share">
                <i class="fa fa-whatsapp fa-lg" data-toggle="tooltip" title="Share on whatsapp"></i>
            </a>
        </div>
        <div class="facebook">
            <a href="https://www.facebook.com/sharer/sharer.php?u=${productUrl}" target="_blank" class="">
                <i class="fa fa-facebook fa-lg" data-toggle="tooltip" title="Share on facebook"></i>
            </a>
        </div>
        <div class="copy-product-link">
            <a id="copyToClipboard" class="" data-toggle="tooltip" title="Copy text on clipboard">
                <i class="fa fa-copy fa-lg"></i>
            </a>
            <a id="copiedToClipboard" class="display-none" data-toggle="tooltip" title="Copied to clipboard">
                <i class="fa fa-copy fa-lg"></i>
            </a>
        </div>
    </div>
</div>