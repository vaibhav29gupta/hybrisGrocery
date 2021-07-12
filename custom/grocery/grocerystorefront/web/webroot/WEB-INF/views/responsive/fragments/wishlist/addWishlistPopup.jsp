<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="wishlist" tagdir="/WEB-INF/tags/responsive/wishlist" %>


{

"addToWishlistLayer":"<spring:escapeBody javaScriptEscape="true" htmlEscape="false">
	<spring:htmlEscape defaultHtmlEscape="true">
	
	<ycommerce:testId code="addToCartPopup">
		<div id="addToWishlistLayer" class="add-to-wishlist js-add-to-wishlist-${productCode}">
            <c:choose>
            	<c:when test="${userNotFound}">
            		<div class="user-not-found-error">
            			<spring:theme code="text.wishlist.no.user.found"/>
            		</div>
            	</c:when>
            	<c:otherwise>
            	
            		<div class="wishlist-header">
            			<div class="wishlist-header-text wishlist-title"><spring:theme code="text.wishlist.shopping.list" /></div>
            			<div class="wishlist-header-text wishlist-back shownone">
            				<i class="fa fa-angle-left" aria-hidden="true"></i> 
            				<spring:theme code="text.wishlist.back.btn" />
            			</div>
            			<span class="wishlist-close-btn"><i class="fa fa-times"></i></span>
            			
            		</div>
            		<div class="wishlist-panel js-wishlist-panel-${productCode}" data-product-code="${productCode}">
		                <c:choose>
			                <c:when test="${allAvailableWishlist ne null and not empty allAvailableWishlist}">
								<c:forEach items="${allAvailableWishlist}" var="wishlist" varStatus="loop">
									<div class="wishlist-items js-wishlist-name" data-wishlist-name="${wishlist.key }">
									
										<c:set var="checkboxId" value="${loop.index}-${productCode}" />
										<div class="js-wishlist-items">
											<input type="hidden" class="js-wishlist-name-input" value="${wishlist.key}" >
                                            <input type="hidden" class="js-product-code-input" value="${productCode}">
											<label for="${checkboxId}" class="wishlist-checkbox-label">
												<c:choose>
													<c:when test="${wishlist.value eq true }">
														<input type="checkbox" id="${checkboxId}" class="check-box wishlist-checkbox-input js-checkbox-${fn:escapeXml(checkboxId)}" checked>
														<span class="wishlist-checkbox-span span-checked" ></span>
													</c:when>
													<c:otherwise>
														<input type="checkbox" id="${checkboxId}" class="check-box wishlist-checkbox-input js-checkbox-${fn:escapeXml(checkboxId)}">
														<span class="wishlist-checkbox-span" ></span>
													</c:otherwise>
												
												</c:choose>
                                                <span class="wishlist-checkbox-name">${wishlist.key}</span>
											</label>
										</div>
										<div class="view-wishlist">
											<c:url var="wishlistLink" value="/wishlist/wishlist-detail/${wishlist.key}" />
											<spring:theme code="text.wishlist.view" var="linkForWishlist" arguments="${fn:escapeXml(wishlistLink)}" htmlEscape="false"/>
											<span id="link-for-wishlist">${ycommerce:sanitizeHTML(linkForWishlist)}</span>
										</div>
									</div>
								</c:forEach>
		                    </c:when>
		                    <c:otherwise>
								<div class="wishlist_error_msg">
			                			<spring:theme code="text.no.wishlist.available" />
								</div>
		                    </c:otherwise>
		             	</c:choose>
                	</div>
	                <div class="wishlist-create-panel shownone">
                    <div class="create-wishlist-placeholder">
	                	<wishlist:createWishlist createBtnName="${'js-create-popup-wishlist-btn' }"/>
                    </div>
	                </div>
	                <div class="btn btn-primary wishlist-popup-create-wishlist-btn">
						<spring:theme code="text.wishlist.create.button" />
					</div> 
            	</c:otherwise>
            </c:choose> 
            
         </div>
		</ycommerce:testId>
	</spring:htmlEscape>
</spring:escapeBody>"}


