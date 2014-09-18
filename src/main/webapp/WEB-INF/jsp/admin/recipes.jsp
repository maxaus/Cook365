<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title><spring:message code="cook365"/></title>
    <link href="/css/main.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<h2><spring:message code="recipes"/></h2>
<a href="/admin/home.wtf"><spring:message code="main-page"/></a><br/>
<a href="/admin/add_recipe.wtf"><spring:message code="add-recipe"/></a><br/>
<a href="/admin/reindex_recipes.wtf"><spring:message code="reindex-recipes"/></a>

<h3><spring:message code="already-exists"/>:</h3>
<table>
    <tbody>
    <tr>
        <th><spring:message code="name"/></th>
        <th></th>
    </tr>
    <c:forEach var="recipe" items="${recipes}">
        <tr>
            <td>${recipe.title}</td>
            <td>
                <a href="/admin/edit_recipe.wtf?recipeId=${recipe.id}"><spring:message code="edit"/></a>
                <a href="/admin/delete_recipe.wtf?recipeId=${recipe.id}"><spring:message code="delete"/></a>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>