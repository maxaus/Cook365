<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <td id=leftColumn>
            <jsp:include page="/calendar.wtf?month="/>
            <jsp:include page="/left_menu.jsp"/>
        </td>
        <td id="mainContent">
            <h3 align="center" class="c1"><spring:message code="error"/></h3>

            <div class="recipeGroup" style="text-align: justify;">
                <table>
                    <tr>
                        <td>
                            <img src="/img/warning.png" alt="warning" title="<spring:message code="error"/>" style="float: left; margin: 5px;">
                        </td>
                        <td>
                            <spring:message code="nothing-found"/>
                        </td>
                    </tr>
                </table>
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