<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Cook365 - меню на день, ${day}.${month}.${year}</title>
    <jsp:include page="/common_including.jsp"/>
    <script type="text/javascript">
        function openAdditionalRecipes(type, addButtonId) {

            $('#' + addButtonId).attr("src", "/img/add_but_disabled.png");

            var trId = '';
            if (type == 'breakfast') {
                trId = 'breakfastRecipe';
            } else if (type == 'dinner') {
                trId = 'dinnerRecipe';
            } else if (type == 'supper') {
                trId = 'supperRecipe';
            }

            $.each(
                    $('tr[id^=' + trId + ']'),
                    function (intIndex, objValue) {
                        $('#' + objValue.id).show();
                    });
        }
    </script>
</head>
<body>
<table width="100%" class="mainTable">
    <tbody>
    <tr>
        <td id="header" colspan="3">
            <jsp:include page="/header.wtf"/>
        </td>
    </tr>
    <tr>
        <td id=leftColumn>
            <jsp:include page="/calendar.wtf?month="/>
            <jsp:include page="/left_menu.jsp"/>
        </td>
        <td id="mainContent">
            <h3 align="center" class="c1"><spring:message code="menu-for-day"/>, ${day}.${month}.${year}</h3>
            <c:choose>
                <c:when test="${fn:length(breakfastRecipes) == 0 && fn:length(dinnerRecipes) == 0 && fn:length(supperRecipes) == 0}">
                    <div class="recipeGroup">
                        <div style="text-align: center;">
                            <img src="/img/empty-dish.jpeg" alt="<spring:message code="no-recipes"/>"><br/>
                            <spring:message code="nothing-found2"/>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div id="breakfastRecipes" class="recipeGroup">
                        <h4 align="center" class="recipeGroupTitle"><spring:message code="breakfast"/></h4>
                        <table width="100%">
                            <tbody>
                            <c:forEach items="${breakfastRecipes}" var="recipe" varStatus="recipeRow">

                                <c:choose>
                                    <c:when test="${recipeRow.index == 0}">
                                        <tr id="breakfastRecipe${recipeRow.index}">
                                    </c:when>
                                    <c:otherwise>
                                        <tr id="breakfastRecipe${recipeRow.index}" style="display: none;">
                                    </c:otherwise>
                                </c:choose>
                                <td width="80px">
                                    <a href="/recipe_details.wtf?recipeId=${recipe.id}">
                                        <img src="/image/${recipe.id}.jpg" alt="${recipe.title}"
                                             title="${recipe.title}"
                                             width="70px"
                                             height="70px" border="1"
                                             style="border-color: #ffcc66; vertical-align: top;"/>
                                    </a>

                                </td>
                                <td align="left">
                                    <a href="/recipe_details.wtf?recipeId=${recipe.id}"
                                       style="color: #cc3300"><strong>${recipe.title}</strong></a><br/>
                                        ${recipe.shortDescription}
                                </td>
                                </tr>
                                <c:if test="${recipeRow.index == 0 && fn:length(breakfastRecipes) > 1}">
                                    <tr>
                                        <td colspan="2" align="right"
                                            style="border-top: 1px dotted #ffcc66; margin-top: 5px; padding-top: 5px;">
                                            <img
                                                    id="addButton1" src="/img/add_but.png" alt="+Ещё"
                                                    title="<spring:message code="show-more-variants"/>"
                                                    style="cursor: pointer;"
                                                    onclick="openAdditionalRecipes('breakfast', 'addButton1');">
                                        </td>
                                    </tr>
                                </c:if>

                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div id="dinnerRecipes" class="recipeGroup">
                        <h4 align="center" class="recipeGroupTitle"><spring:message code="dinner"/></h4>
                        <table width="100%">
                            <tbody>
                            <c:forEach items="${dinnerRecipes}" var="recipe" varStatus="recipeRow">

                                <c:choose>
                                    <c:when test="${recipeRow.index == 0}">
                                        <tr id="dinnerRecipe${recipeRow.index}">
                                    </c:when>
                                    <c:otherwise>
                                        <tr id="dinnerRecipe${recipeRow.index}" style="display: none;">
                                    </c:otherwise>
                                </c:choose>
                                <td width="80px">
                                    <a href="/recipe_details.wtf?recipeId=${recipe.id}">
                                        <img src="/image/${recipe.id}.jpg" alt="${recipe.title}"
                                             title="${recipe.title}"
                                             width="70px"
                                             height="70px" border="1"
                                             style="border-color: #ffcc66; vertical-align: top;"/>
                                    </a>

                                </td>
                                <td align="left">
                                    <a href="/recipe_details.wtf?recipeId=${recipe.id}"
                                       style="color: #cc3300"><strong>${recipe.title}</strong></a><br/>
                                        ${recipe.shortDescription}
                                </td>
                                </tr>
                                <c:if test="${recipeRow.index == 0 && fn:length(dinnerRecipes) > 1}">
                                    <tr>
                                        <td colspan="2" align="right"
                                            style="border-top: 1px dotted #ffcc66; margin-top: 5px; padding-top: 5px;">
                                            <img
                                                    id="addButton2" src="/img/add_but.png" alt="+Ещё"
                                                    title="<spring:message code="show-more-variants"/>"
                                                    style="cursor: pointer;"
                                                    onclick="openAdditionalRecipes('dinner', 'addButton2');">
                                        </td>
                                    </tr>
                                </c:if>

                            </c:forEach>
                            </tbody>
                        </table>

                    </div>

                    <div id="supperRecipes" class="recipeGroup">
                        <h4 align="center" class="recipeGroupTitle"><spring:message code="supper"/></h4>
                        <table width="100%">
                            <tbody>
                            <c:forEach items="${supperRecipes}" var="recipe" varStatus="recipeRow">

                                <c:choose>
                                    <c:when test="${recipeRow.index == 0}">
                                        <tr id="supperRecipe${recipeRow.index}">
                                    </c:when>
                                    <c:otherwise>
                                        <tr id="supperRecipe${recipeRow.index}" style="display: none;">
                                    </c:otherwise>
                                </c:choose>
                                <td width="80px">
                                    <a href="/recipe_details.wtf?recipeId=${recipe.id}">
                                        <img src="/image/${recipe.id}.jpg" alt="${recipe.title}"
                                             title="${recipe.title}"/>
                                    </a>
                                </td>
                                <td align="left">
                                    <a href="/recipe_details.wtf?recipeId=${recipe.id}"><strong>${recipe.title}</strong></a><br/>
                                        ${recipe.shortDescription}
                                </td>
                                </tr>
                                <c:if test="${recipeRow.index == 0 && fn:length(supperRecipes) > 1}">
                                    <tr>
                                        <td colspan="2" align="right"
                                            style="border-top: 1px dotted #ffcc66; margin-top: 5px; padding-top: 5px;">
                                            <img
                                                    id="addButton3" src="/img/add_but.png" alt="+Ещё"
                                                    title="<spring:message code="show-more-variants"/>"
                                                    style="cursor: pointer;"
                                                    onclick="openAdditionalRecipes('supper', 'addButton3');">
                                        </td>
                                    </tr>
                                </c:if>

                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>

            <div id="adv2" style="margin:10px" align="center">
                <script type="text/javascript"><!--
                google_ad_client = "ca-pub-5660376134536668";
                /* DayMenuBanner1 */
                google_ad_slot = "2147381525";
                google_ad_width = 468;
                google_ad_height = 60;
                //-->
                </script>
                <script type="text/javascript"
                        src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
                </script>
            </div>
        </td>
        <td id="rightColumn">
            <jsp:include page="/right_column.jsp"/>
        </td>
    </tr>
    <tr>
        <td id="footer" colspan="3">
            <jsp:include page="/footer.jsp"/>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>






