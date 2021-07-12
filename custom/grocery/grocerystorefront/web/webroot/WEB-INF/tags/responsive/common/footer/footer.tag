<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/responsive/common/footer"%>

<footer>
<%--     <footer:chatbot /> --%>
    <cms:pageSlot position="Footer" var="feature">
        <cms:component component="${feature}"/>
    </cms:pageSlot>
</footer>