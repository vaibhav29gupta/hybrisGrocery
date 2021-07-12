<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty product.ingredients}">
    <div class="product-ingredients">
        <div class="tabhead">${fn:escapeXml(title)}</div>
        <div class="tabbody">
            <div class="container-lg">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="tab-container">
                            <product:productIngredientsTab product="${product}" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
