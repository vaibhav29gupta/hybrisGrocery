<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/responsive/common/footer"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspcache" uri="/WEB-INF/tld/ehcache.tld"%>
<c:if test="${component.visible}">
    <div class="footer-top">
        <div class="footer-top-container">
            <jspcache:cache key="footer-header-${currentCountry.isocode}">
                <ul class="footer-links">
                    <c:forEach items="${feature.navigationNode.children}" var="l1">
                        <c:forEach items="${l1.entries}" var="link">
                            <li class="nav-footer-li">
                                <cms:component component="${link.item}" evaluateRestriction="true" />
                            </li>
                        </c:forEach>
                    </c:forEach>
                </ul>
            </jspcache:cache>
            <div class="footer-country-selector">
                <footer:countrySelector countries="${countries}" currentCountry="${currentCountry}" />
            </div>
        </div>
        <jspcache:cache key="footer-follow-${currentCountry.isocode}">
            <div class="follow-us-icon">
                <ul>
                    <c:forEach items="${bottomLeftMedia}" var="element">
                        <c:if test="${not empty element.media && element.media ne null}">
                            <li>
                                <a target="_blank" href="${fn:escapeXml(element.urlLink) }">
                                    <img class="left-section-icon" src="${fn:escapeXml(element.media.url) }">
                                </a>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
        </jspcache:cache>
    </div>
    <jspcache:cache key="footer-links-${currentCountry.isocode}">
        <div class="footer-bottom">
            <div class="center-section-copyright">${fn:escapeXml(notice)}</div>
            <div class="right-section">
                <ul>
                    <c:forEach items="${bottomRightMedia}" var="element">
                        <c:if test="${not empty element.url && element.url ne null}">
                            <li>
                                <img class="right-section-icon" src="${fn:escapeXml(element.url) }">
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </jspcache:cache>
</c:if>