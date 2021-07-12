<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="store" tagdir="/WEB-INF/tags/responsive/store" %>
<script src="https://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
          
<store:storeSearch errorNoResults="${errorNoResults}"/>
<store:storeListForm searchPageData="${searchPageData}" locationQuery="${locationQuery}" numberPagesShown="${numberPagesShown}" geoPoint="${geoPoint}"/>