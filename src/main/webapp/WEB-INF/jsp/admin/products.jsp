<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title><spring:message code="cook365"/></title>
    <link href="/css/main.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<h2><spring:message code="products"/></h2>
<a href="/admin/home.wtf"><spring:message code="main-page"/></a><br/>
<a href="/admin/add_product.wtf"><spring:message code="add-product"/></a>

<h3><spring:message code="already-exists"/>:</h3>
<c:choose>
    <c:when test="${page.totalElements == 0}">
        <spring:message code="no-products"/>.
    </c:when>
    <c:otherwise>
        <table style="border: 1px solid #cccccc; width: 500px; height: 600px;">
            <tbody>
            <tr>
                <th><spring:message code="name"/></th>
                <th></th>
            </tr>
            <c:forEach var="product" items="${page.content}">
                <tr>
                    <td>${product.name}</td>
                    <td>
                        <a href="/admin/edit_product.wtf?productId=${product.id}"><spring:message code="edit"/></a>
                        <a href="/admin/delete_product.wtf?productId=${product.id}"><spring:message code="delete"/></a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <c:if test="${page.totalPages > 1}">
            <div id="pageNav" style="width: 300px;">
                <spring:message code="page"/> :
                <c:if test="${!page.firstPage}">
                    <a href="/admin/products.wtf?page=0"><img src="/img/left-end.png" alt="<spring:message code="to-the-beginning"/>"
                                                              title="<spring:message code="to-the-beginning"/>"/></a>
                    <a href="/admin/products.wtf?page=${page.number - 1}"><img src="/img/left.png"
                                                                               alt="<spring:message code="prev-page"/>"
                                                                               title="<spring:message code="prev-page"/>"/></a>
                </c:if>
                <span style="margin-left: 5px; margin-right: 5px;">${page.number + 1}</span>
                <c:if test="${!page.lastPage}">
                    <a href="/admin/products.wtf?page=${page.number + 1}"><img src="/img/right.png"
                                                                               alt="<spring:message code="next-page"/>"
                                                                               title="<spring:message code="next-page"/>"/></a>
                    <a href="/admin/products.wtf?page=${page.totalPages - 1}"><img src="/img/right-end.png"
                                                                                   alt="<spring:message code="to-the-end"/>"
                                                                                   title="<spring:message code="to-the-end"/>"/></a>
                </c:if>
            </div>
        </c:if>
    </c:otherwise>
</c:choose>
</body>
</html>