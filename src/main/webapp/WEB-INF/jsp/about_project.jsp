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
        <td id=leftColumn>
            <jsp:include page="/calendar.wtf?month="/>
            <jsp:include page="/left_menu.jsp"/>
        </td>
        <td id="mainContent">
            <h3 align="center" class="c1"><spring:message code="about-project"/></h3>

            <div class="recipeGroup" style="text-align: justify;">
                <h4>Мы знаем, что вы сегодня будете готовить!</h4>
                <img src="/img/kot.png" alt="kot" title="О проекте Cook365.ru" style="float: left; margin: 5px;">

                <p>Проект Cook365.RU - это новый проект в рунете, который делает акцент на одном из самых насущных
                    вопросов
                    человечества - "Что приготовить сегодня на ужин?" (завтрак или обед, подставляется по-желанию).
                    Постоянно пополняющаяся коллекция рецептов распределена по дням так, что выбрав дату в нашем
                    календаре
                    (всегда слева), вы увидите несколько вариантов того, чем можете сегодня порадовать свою семью.</p>

                <p>Также, конечно, вы найдёте и другие рубрики с рецептами - <a href="/salad.wtf"
                                                                                title="Рецепты салатов">салаты</a>,
                    <a href="/dessert.wtf" title="Рецепты десертов">десерты</a>, <a href="/cocktail.wtf"
                                                                                    title="Рецепты коктейлей">коктейли</a>
                    и
                    т.д.</p>

                <p>Вы всегда можете выбрать несколько блюд, которые вы хотите приготовить, и мы покажем вам список
                    необходимых продуктов, с которым вы можете смело идти в магазин.</p>

                <p>По любым интересующим вас вопросам - смело <a href="mailto:support@cook365.ru">обращайтесь</a>.</p>
            </div>
            <div id="adBig" style="margin: 10px;">
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