<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Cook365 - <spring:message code="generalHeader"/></title>
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
        <td id="leftColumn">
            <jsp:include page="/calendar.wtf?month="/>
            <jsp:include page="/left_menu.jsp"/>
        </td>
        <td id="mainContent">
            <jsp:include page="/news.wtf"/>
        </td>
        <td id="rightColumn">
            <!-- AddThis Button BEGIN -->
            <div class="addthis_toolbox addthis_default_style" style="margin-top: 35px;">
                <a class="addthis_button_preferred_1"></a>
                <a class="addthis_button_preferred_2"></a>
                <a class="addthis_button_preferred_3"></a>
                <a class="addthis_button_preferred_4"></a>
                <a class="addthis_button_compact"></a>
                <a class="addthis_counter addthis_bubble_style"></a>
            </div>
            <script type="text/javascript"
                    src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=ra-4ee6bf2131fe4573"></script>
            <!-- AddThis Button END -->
            <br/>
            <a href="/add_recipe.wtf"><spring:message code="add-recipe"/></a>
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