<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title><spring:message code="cook365"/></title>
    <link href="/css/main.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery-1.6.2.js"></script>
    <script type="text/javascript" src="/js/jquery.validate.js"></script>
</head>
<body>
<form:form method="post" action="edit_product.wtf" commandName="product" id="editProductForm">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td><form:label path="name"><spring:message code="name"/></form:label></td>
            <td><form:input path="name" cssClass="inputBorder"/><form:errors path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="liquid"><spring:message code="liquid"/></form:label></td>
            <td><form:checkbox path="liquid"/><form:errors path="liquid"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message code="save"/>"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
<script type="text/javascript">
    jQuery(document).ready(function() {
        $("#editProductForm").validate({
            focusInvalid: false,
            focusCleanup: true,
            rules: {
                "name": {
                    required : true
                }
            },
            messages: {
                "name": {
                    required: "<spring:message code="fill-in-the-name-of-the-product"/>"
                }
            }
        });
    });
</script>
</html>