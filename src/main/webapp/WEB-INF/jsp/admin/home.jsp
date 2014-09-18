<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title><spring:message code="cook365"/></title>
    <link href="/css/main.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div align="right">
    <a href="../j_spring_security_logout" style="color: #000"><spring:message code="exit"/></a>
</div>
<div style="width: 50%; border-bottom: 1px #000000 solid; padding: 10px;">
    <div style="display: inline; border: 1px #006633 solid; padding: 10px; background-color: #ffcc66">
        <a href="/admin/recipes.wtf"><spring:message code="recipes"/></a>
    </div>
    <div style="display: inline; border: 1px #006633 solid; margin-left: 3px; padding: 10px; background-color: #ffcc66">
        <a href="/admin/products.wtf"><spring:message code="products"/></a>
    </div>
</div>
</body>

</html>