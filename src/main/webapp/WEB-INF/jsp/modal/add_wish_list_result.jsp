<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<p><spring:message code="recipe-added-to-wishlist"/></p>
<script type="text/javascript">
    jQuery(document).ready(function() {
        var linkHtml = $("#wishListLink").html();
        var parts = linkHtml.match(/(\D+)\((\d+)\)$/);
        $("#wishListLink").html(parts[1] + '(' + ${fn:length(recipeIds)} + ')');
    });
</script>
