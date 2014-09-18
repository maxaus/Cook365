<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title><spring:message code="sign-in"/></title>
    <link href="/css/main.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery-1.6.2.js"></script>
    <script type="text/javascript" src="/js/jquery.validate.js"></script>
</head>
<body>
<form method="POST" action="j_spring_security_check" id="loginForm">
    <table border="0" class="main_table">

        <tr>
            <td><label for="j_username"><spring:message code="email"/></label></td>
            <td><input type="text" name="j_username" id="j_username" class="inputBorder"></td>
        </tr>
        <tr>
            <td><label for="j_password"><spring:message code="password"/></label></td>
            <td><input type="password" name="j_password" id="j_password" class="inputBorder"></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="checkbox" name="_spring_security_remember_me"/>
                <span>
                    <spring:message code="remember-me"/>
                </span>
            </td>
        </tr>

    </table>
    <div align="left" style="margin-left: 165px">
        <input type="submit" value="<spring:message code="ok"/>">
    </div>
</form>
<script type="text/javascript">
    jQuery(document).ready(function() {
        $.validator.addMethod('restrictChar', function (value) {
            return value.match(/^[a-zA-Z0-9_.\-@\s]+$/);
        }, "<spring:message code="value-contains-inapropriate-symbols"/>");
        $.validator.addMethod('script', function (value) {
            return !value.match(/<script/);
        }, "<spring:message code="value-contains-javascript"/>");
        $("#loginForm").validate({
            focusInvalid: false,
            focusCleanup: true,
            onkeyup: false,
            onfocusout: false,
            rules: {
                "j_username" : {
                    required : true,
                    restrictChar: true,
                    script: true
                },
                "j_password" : {
                    required : true,
                    restrictChar: true,
                    script: true
                }
            },
            messages: {
                "j_username": {
                    required: "<spring:message code="enter-the-email"/>"
                },
                "j_password": {
                    required: "<spring:message code="enter-the-password"/>"
                }
            }
        });
    });
</script>
</body>
</html>