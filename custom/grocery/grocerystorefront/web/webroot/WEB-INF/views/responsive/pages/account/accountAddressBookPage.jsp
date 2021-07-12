<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<c:set var="noBorder" value="" />
<c:if test="${not empty addressData}">
    <c:set var="noBorder" value="no-border" />
</c:if>
<div class="account-section-header ${noBorder}">
    <spring:theme code="text.account.addressBook" />
    <ycommerce:testId code="addressBook_addNewAddress_button">
        <div class="account-section-header-add pull-right">
            <a href="add-address">
                <spring:theme code="text.account.addressBook.addAddress" />
            </a>
        </div>
    </ycommerce:testId>
</div>
<div class="account-addressbook account-list account-section-content">
    <c:if test="${empty addressData}">
        <div class="account-cards card-select">
            <div class="card address-card">
                <div class="account-section-content content-empty">
                    <spring:theme code="text.account.addressBook.noSavedAddresses" />
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${not empty addressData}">
        <div class="account-cards card-select">
            <c:forEach items="${addressData}" var="address">
                <div class="address-card-main">
                    <div class="card address-card">
                        <ul>
                            <li>
                                <strong>${fn:escapeXml(address.title)}&nbsp;${fn:escapeXml(address.firstName)}&nbsp;${fn:escapeXml(address.lastName)}
                                    <c:if test="${address.defaultAddress}">
                                        <i class="fa fa-certificate default-address" title="<spring:theme code="text.default" />"></i>
                                    </c:if>
                                </strong>
                            </li>
                            <li>${fn:escapeXml(address.line1)}</li>
                            <c:if test="${not empty fn:escapeXml(address.line2)}">
                                <li>${fn:escapeXml(address.line2)}</li>
                            </c:if>
                            <li>${fn:escapeXml(address.town)}&nbsp;${fn:escapeXml(address.region.name)}</li>
                            <li>${fn:escapeXml(address.country.name)}&nbsp;${fn:escapeXml(address.postalCode)}</li>
                            <li>${fn:escapeXml(address.phone)}</li>
                        </ul>
                        <div class="account-cards-actions">
                            <ycommerce:testId code="addressBook_editAddress_button">
                                <a class="action-links edit-address" href="edit-address/${fn:escapeXml(ycommerce:encodeUrl(address.id))}">
                                    <i class="fa fa-pencil-square"></i>
                                </a>
                            </ycommerce:testId>
                            <ycommerce:testId code="addressBook_removeAddress_button">
                                <a class="action-links remove-address removeAddressFromBookButton" data-address-id="${fn:escapeXml(address.id)}">
                                    <i class="fa fa-minus-square"></i>
                                </a>
                            </ycommerce:testId>
                        </div>
                    </div>
                    <div id="popup_confirm_address_removal_${fn:escapeXml(address.id)}" class="account-address-removal-popup shownone">
                        <div class="addressItem">
                            <div class="remove-address-title">
                                <spring:theme code="text.address.delete.popup.title" />
                            </div>
                            <div class="remove-address-subtitle">
                                <spring:theme code="text.address.remove.following" />
                            </div>
                            <div class="modal-actions">
                                <ycommerce:testId code="addressRemove_delete_button">
                                    <a class="btn btn-primary btn-block" data-address-id="${fn:escapeXml(address.id)}"
                                        href="remove-address/${fn:escapeXml(ycommerce:encodeUrl(address.id))}">
                                        <spring:theme code="text.address.delete" />
                                    </a>
                                </ycommerce:testId>
                                <a class="btn btn-default btn-block hide-remove-address-popup" data-address-id="${fn:escapeXml(address.id)}">
                                    <spring:theme code="text.button.cancel" />
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>