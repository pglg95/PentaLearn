<%--
  Created by IntelliJ IDEA.
  User: pglg
  Date: 24-04-2016
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-16" pageEncoding="UTF-16" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title><spring:message code="question.category.create"/></title>
</head>
<body>
    <h1><spring:message code="question.category.create"/></h1>
    <form:form method="POST" action="/question_category_create.html" modelAttribute="form">
        <form:errors path="" element="div"/>
        <div>
            <form:label path="name"><spring:message code="question.category.name"/></form:label>
            <form:input path="name"/>
            <form:errors path="name"/>
        </div>
        <div>
            <input type="submit"/>
        </div>
    </form:form>
</body>
</html>
