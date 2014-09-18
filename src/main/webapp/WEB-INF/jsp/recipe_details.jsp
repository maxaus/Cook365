<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Cook365 - ${recipe.title}</title>
    <jsp:include page="/common_including.jsp"/>
    <script type="text/javascript">
        function addToRecipeWishList(recipeId) {
            $.get('/add_to_wish_list.wtf?recipeId=' + recipeId, function (data) {
                $("#dialog").attr("title", "Рецепт добавлен");
                $("#dialog").html(data);
                $("#dialog").dialog();
            });
        }

        function openLargeImage(imgSrc, imgTitle) {
            $("#dialog").attr("title", imgTitle);
            $("#dialog").html("<img src=\"" + imgSrc + "\"/>");

            $("#dialog").dialog({modal:true,
                resizable:false,
                draggable:false,
                width:'auto'});
        }
    </script>
    <style type="text/css">
        @media print {
            td#header {
                display: none;
            }

            td#leftColumn {
                display: none;
            }

            td#rightColumn {
                display: none;
            }

            td#footer {
                display: none;
            }

            td#mainContent {
                width: 100%;
                margin: 0;
            }

            table#infoButtons {
                display: none;
            }

            div#recipeButtons {
                display: none;
            }

            div#externalLink {
                display: none;
            }

            div#disqus_thread {
                display: none;
            }

            .dsq-brlink {
                display: none;
            }
        }
    </style>
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
            <h3 align="center" class="c1">${recipe.title}</h3>

            <div id="dialog" title="" style="display: none;">
            </div>
            <div class="recipeGroup">
                <table width="100%" id="infoButtons">
                    <tr>
                        <td align="left">
                            <c:if test="${recipe.vegetarian}"><img src="/img/vegetarian.png" alt=""
                                                                   title="<spring:message code="vegetarian"/>"></c:if>
                        </td>
                        <td align="left">
                            <!-- AddThis Button BEGIN -->
                            <div class="addthis_toolbox addthis_default_style">
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
                        </td>
                        <td align="right">
                            <div id="exportButtons">
                                <img src="/img/printer-icon.png" alt="print"
                                     title="<spring:message code="print-recipe"/>"
                                     style="cursor: pointer;" onclick="window.print();">
                            </div>
                        </td>
                    </tr>
                </table>

                <div id="recipeDescription">
                    <c:if test="${recipe.imagePath != null}">
                        <img src="/image/${recipe.id}.jpg" alt="${recipe.title}" title="${recipe.title}" width="70px"
                             height="70px"
                             style="float: left; margin-right: 10px; margin-bottom: 10px; border: 1px solid #ffcc66; cursor: pointer;"
                             onclick="openLargeImage(this.src, this.title);">
                    </c:if>
                    ${recipe.description}
                </div>
                <div id="recipeIngredients">
                    <h4 style="text-decoration: underline;"><spring:message code="ingredients"/></h4>
                    <ul style="list-style: square;">
                        <c:forEach items="${recipe.ingredients}" var="ingredient">
                            <li style="color: #cccccc;">
                                <span style="color:#000000;">${ingredient.product.name}
                                <c:if
                                        test="${ingredient.value > 0}"> : <fmt:formatNumber type="number"
                                                                                            value="${ingredient.value}"/> ${ingredient.measureUnit.name}.
                                </c:if>
                                </span>
                            </li>
                        </c:forEach></ul>
                </div>
                <div id="recipeButtons" align="right">

                    <img src="/img/want_but.png" alt="<spring:message code="want-to-cook"/>"
                         title="<spring:message code="click-to-add-to-wish-list"/>"
                         onclick="addToRecipeWishList('${recipe.id}');" style="cursor: pointer;"/>

                </div>
                <c:if test="${recipe.externalLink != null && recipe.externalLink != ''}">
                    <div id=externalLink align="right" class="c3"
                         style="font-size: xx-small; margin-bottom: -8px; margin-right: -8px;">
                        <spring:message code="taken-from"/>: ${recipe.externalLink}
                    </div>
                </c:if>
                <div id="adv1" style="margin:10px" align="left">
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
                <div id="disqus_thread"></div>
                <script type="text/javascript">
                    /* * * CONFIGURATION VARIABLES: EDIT BEFORE PASTING INTO YOUR WEBPAGE * * */
                    var disqus_shortname = 'cook365'; // required: replace example with your forum shortname

                    /* * * DON'T EDIT BELOW THIS LINE * * */
                    (function () {
                        var dsq = document.createElement('script');
                        dsq.type = 'text/javascript';
                        dsq.async = true;
                        dsq.src = 'http://' + disqus_shortname + '.disqus.com/embed.js';
                        (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
                    })();
                </script>
                <noscript>Please enable JavaScript to view the <a href="http://disqus.com/?ref_noscript">comments
                    powered by Disqus.</a></noscript>
                <a href="http://disqus.com" class="dsq-brlink">Comments powered by <span
                        class="logo-disqus">Disqus</span></a>
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