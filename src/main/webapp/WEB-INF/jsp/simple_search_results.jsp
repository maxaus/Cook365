<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Cook365 - <spring:message code="search-results"/></title>
    <jsp:include page="/common_including.jsp"/>
    <script type="text/javascript">
        function submitSearchForm(searchString, pageNum) {
            $("#searchString").val(searchString);
            $("#page").val(pageNum);
            $("#searchForm").submit();
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
            <h3 align="center" class="c1"><spring:message code="search-results"/></h3>

            <div class="recipeGroup">
                <c:choose>
                    <c:when test="${searchString==null}">
                        <spring:message code="search-advise"/>
                    </c:when>
                    <c:otherwise>
                        <div><spring:message code="you-searched"/>: <span class="c3">${searchString}</span></div>
                        <div id="results" class="m4">
                            <c:choose>
                                <c:when test="${page.totalElements == 0 || page.numberOfElements == 0}">
                                    <spring:message code="nothing-found"/>
                                </c:when>
                                <c:otherwise>
                                    <table width="100%">
                                        <tbody>
                                        <c:forEach items="${page.content}" var="recipe" varStatus="recipeRow">
                                            <tr>
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
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <div id="pageNav">
                                        <spring:message code="page"/>:
                                        <c:if test="${!page.firstPage}">
                                            <a onclick="submitSearchForm('${searchString}','0');"
                                               style="cursor: pointer; text-decoration: underline;"><img
                                                    src="/img/left-end.png"
                                                    alt="<spring:message code="to-the-beginning"/>"
                                                    title="<spring:message code="to-the-beginning"/>"/></a>
                                            <a onclick="submitSearchForm('${searchString}','${page.number - 1}');"
                                               style="cursor: pointer; text-decoration: underline;"><img
                                                    src="/img/left.png"
                                                    alt="<spring:message code="prev-page"/>"
                                                    title="<spring:message code="prev-page"/>"/></a>
                                        </c:if>
                                            ${page.number + 1}
                                        <c:if test="${!page.lastPage}">
                                            <a onclick="submitSearchForm('${searchString}','${page.number + 1}');"
                                               style="cursor: pointer; text-decoration: underline;"><img
                                                    src="/img/right.png"
                                                    alt="<spring:message code="next-page"/>"
                                                    title="<spring:message code="next-page"/>"/></a>
                                            <a onclick="submitSearchForm('${searchString}','${page.totalPages - 1}');"
                                               style="cursor: pointer; text-decoration: underline;"><img
                                                    src="/img/right-end.png"
                                                    alt="<spring:message code="to-the-end"/>"
                                                    title="<spring:message code="to-the-end"/>"/></a>
                                        </c:if>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div id="adv1" style="margin-top: 10px; padding: 10px; overflow: hidden;" align="center">
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

<script type="text/javascript">
    jQuery(document).ready(function () {
        $("#searchString").val('${searchString}');
    });
</script>
</body>
</html>