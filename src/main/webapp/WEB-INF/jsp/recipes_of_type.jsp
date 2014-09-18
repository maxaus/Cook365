<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cook365 - <spring:message code="${type}"/></title>
    <jsp:include page="/common_including.jsp"/>
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
            <h3 align="center" class="c1"><spring:message code="${type}"/></h3>

            <div class="recipeGroup">
                <c:choose>
                    <c:when test="${page.totalElements == 0}">
                        <div style="text-align: center;">
                            <img src="/img/empty-dish.jpeg" alt="<spring:message code="no-recipes"/>"><br/>
                            <spring:message code="nothing-found2"/>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <table cellpadding="3px">
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
                        <c:if test="${page.totalPages > 1}">
                            <div id="pageNav">
                                <spring:message code="page"/> :
                                <c:if test="${!page.firstPage}">
                                    <a href="/${type}.wtf?page=0"><img src="/img/left-end.png" alt="<spring:message code="to-the-beginning"/>"
                                                                       title="<spring:message code="to-the-beginning"/>"/></a>
                                    <a href="/${type}.wtf?page=${page.number - 1}"><img src="/img/left.png"
                                                                                        alt="<spring:message code="prev-page"/>"
                                                                                        title="<spring:message code="prev-page"/>"/></a>
                                </c:if>
                                <span style="margin-left: 5px; margin-right: 5px;">${page.number + 1}</span>
                                <c:if test="${!page.lastPage}">
                                    <a href="/${type}.wtf?page=${page.number + 1}"><img src="/img/right.png"
                                                                                        alt="<spring:message code="next-page"/>"
                                                                                        title="<spring:message code="next-page"/>"/></a>
                                    <a href="/${type}.wtf?page=${page.totalPages - 1}"><img src="/img/right-end.png"
                                                                                            alt="<spring:message code="to-the-end"/>"
                                                                                            title="<spring:message code="to-the-end"/>"/></a>
                                </c:if>
                            </div>
                        </c:if>

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
</body>
</html>