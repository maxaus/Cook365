<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table width="100%" cellpadding="0" cellspacing="0" style="height: 100%">
    <tr>
       <td align="left">
           <a href="/"><img src="/img/logo1.jpg" alt="" height="120px" border="0"/></a>
       </td>
        <td align="right">
            <img src="/img/header2.png" height="120px" alt="" align="right" usemap="#headerMap" border="0"/>
            <map name="headerMap" id="headerMap">
                <area shape="rect" coords="10,10,95,120" href="/breakfast.wtf"  alt="Завтрак" title="На завтрак.."/>
                <area shape="rect" coords="105,10,190,120" href="/dinner.wtf"  alt="Обед" title="На обед.."/>
                <area shape="rect" coords="200,10,280,120" href="/supper.wtf"  alt="Ужин" title="На ужин.."/>

            </map>
        </td>
    </tr>
    <tr valign="bottom">
        <td align="left" style="padding-bottom: 4px; *padding-bottom: 1px;">
            <form:form method="post" action="/search.wtf" commandName="searchForm" cssStyle="display: inline;">
                <div class="searchBox">
                    <form:input path="searchString" cssClass="searchBoxInput"/>
                </div>

                <form:hidden path="page"/>
                <img src="/img/arrow_right_double.gif" alt="<spring:message code="search"/>" onclick="$('#searchForm').submit();"
                     class="searchButton"/>


            </form:form>
            <a href="/extended_search_form.wtf" class="m3" style="font-weight: bolder; font-size: 12px;text-decoration:none;
border-bottom:1px dotted #000000;"><spring:message
                    code="search-by-products"/></a>
            <%--<c:if test="${wishListSize > 0}">--%>
            <span class="m2">|</span>

            <a id="wishListLink" href="recipe_wish_list.wtf" class="m2" style="font-size: 9pt; color: #006600; text-decoration:none;
border-bottom:1px dotted #006600;"
               title="<spring:message code="want-to-cook-tooltip"/>"><spring:message
                    code="want-to-cook"/>(${wishListSize})</a>
            <%--</c:if>--%>
        </td>
        <td align="right" style="padding-bottom: 10px; *padding-bottom: 4px;"><img src="/img/but_info.gif" style="margin-right: 3px; margin-bottom: -5px;" alt=""/><a href="/about_project.wtf" style="font-weight: normal; font-size: 12px;text-decoration:none;
border-bottom:1px dotted #000000;"><spring:message code="about-project"/></a></td>
    </tr>
</table>





<%--<div style="display: inline; float: right; margin-right: 10px;" align="right">--%>
    <%----%>
<%--</div>--%>


