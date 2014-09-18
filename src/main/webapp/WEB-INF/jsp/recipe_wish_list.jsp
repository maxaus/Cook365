<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Cook365 - <spring:message code="generalHeader"/></title>
    <jsp:include page="/common_including.jsp"/>
    <script type="text/javascript">
        function removeFromRecipeWishList(recipeId) {
            $.get('/remove_from_wish_list.wtf?recipeId=' + recipeId, function (data) {
                $("#dialog").html(data);
                $("#dialog").dialog();
                $("#dialog").bind('dialogclose', function (event) {
                    location.reload();
                });

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
            <h3 align="center" class="c1"><spring:message code="want-to-cook-tooltip"/></h3>

            <div id="dialog" title="<spring:message code="recipe-deleted"/>" style="display: none;">

            </div>
            <div class="recipeGroup">
                <c:choose>
                    <c:when test="${recipes != null}">
                        <span class="c2"><spring:message code="recipes"/>:</span>

                        <div id="recipeList">
                            <ul style="list-style: none;">
                                <c:forEach items="${recipes}" var="recipe">
                                    <li style="color: #cccccc; margin-top: 5px;">
                                        <img src="/img/minus-icon.png" alt="-"
                                             title="<spring:message code="delete-recipe-from-list"/>"
                                             style="cursor: pointer; margin-bottom: -7px; height: 22px;"
                                             onclick="removeFromRecipeWishList('${recipe.id}');"/>
                            <span style="color:#000000;">
                                <a href="recipe_details.wtf?recipeId=${recipe.id}">${recipe.title}</a>
                            </span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <c:if test="${productMap != null}">
                            <div id="productList" class="m4">
                                <span class="c2"><spring:message code="all-needed"/>:</span>
                                <table width="33%" class="m4">
                                    <c:forEach items="${productMap}" var="product">
                                        <tr>
                                            <td align="left"><span
                                                    style="font-size: 9pt;padding-left: 20px;">${product.key}</span>
                                            </td>
                                            <td align="right"><span
                                                    style="font-size: 9pt; color: #666666;">... ${product.value}</span>
                                            </td>
                                        </tr>

                                    </c:forEach>
                                </table>
                            </div>
                            <div id="buttons" class="m4">
                                <a href="xls_wish_list.wtf"><img src="/img/excel.png" alt="xls"
                                                                 style="margin-bottom: -12px; border: 0"
                                                                 title="<spring:message code="export-file-excel"/>"/><spring:message code="export-file"/></a>
                            </div>
                            <div id="adv1" style="margin:10px" align="center">
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
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <div style="text-align: justify;">
                            <img src="/img/food.png" alt="food" title="Еда"
                                 style="float: left; margin-right: 5px; border: 1px solid #ffcc66;">
                            <spring:message code="wishlist-description"/>
                        </div>
                        <div id="adBig" style="margin-top: 20px; padding: 10px; overflow: hidden;">
                            <script type="text/javascript"><!--
                            google_ad_client = "ca-pub-5660376134536668";
                            /* BigSq */
                            google_ad_slot = "4631297776";
                            google_ad_width = 336;
                            google_ad_height = 280;
                            //-->
                            </script>
                            <script type="text/javascript"
                                    src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
                            </script>
                        </div>
                    </c:otherwise>
                </c:choose>

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