<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<div class="account-section-header account-close-section-header">
    <spring:theme code="text.account.closeAccount.header" />
</div>
<div class="account-section-content">
    <div class="account-section-form ">
        <div class="text">
            <spring:theme code="text.account.closeAccount.retention.info" var="retentionInfoHtml" htmlEscape="false" />
            ${ycommerce:sanitizeHTML(retentionInfoHtml)}
        </div>
    </div>
    <div class="accountActions">
        <button type="button" class="btn btn-primary js-close-account-popup-button" data-popup-title="<spring:theme code="text.account.closeAccount.popup.title"/>">
            <spring:theme code="text.account.closeAccount.button" />
        </button>
    </div>
    <div class="display-none confirm_account_removal">
        <div id="popup_confirm_account_removal" class="js-close-account-popup close-account">
            <div class="modal-details row">
                <spring:theme code="text.account.closeAccount.popup.confirm" />
            </div>
            <div class="modal-actions">
                        <a class="btn btn-primary btn-block js-close-account-action">
                            <spring:theme code="text.account.closeAccount.popup.action" />
                        </a>
                        <a class="btn btn-default btn-block close-close-account">
                            <spring:theme code="text.button.cancel" />
                        </a>
            </div>
        </div>
    </div>
</div>
