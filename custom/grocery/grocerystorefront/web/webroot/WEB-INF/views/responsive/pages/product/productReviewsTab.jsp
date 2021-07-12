<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="product-reviews">
    <div id="tabreview" class="tabhead"></div>
    <div class="tabbody">
        <div class="container-lg">
            <div class="row">
                <div class="col-xs-12">
                    <div class="tab-container">
                        <product:productPageReviewsTab title="${fn:escapeXml(title)}" product="${product}" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
