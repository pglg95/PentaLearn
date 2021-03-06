<%@ page import="pl.pollub.cs.pentalearn.domain.QuestionCreateForm" %><%--
  Created by IntelliJ IDEA.
  User: pglg
  Date: 25-04-2016
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-16" pageEncoding="UTF-16" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="question.create"/></title>
    <script>
        function getDataFromSelect(){
            var hiddenInput=document.getElementById("hiddenInp");
            hiddenInput.value="false";
            var select = document.getElementById("questionCategorySelect");
            var id =parseInt(select.options[select.selectedIndex].value);
            var input=document.getElementById("questionCategoryInput");
            input.value=id;
        }
        function answersNumberChanged9(){
           var hiddenInput=document.getElementById("hiddenInp");
            hiddenInput.value="true";
        }
    </script>
</head>
<body>
<h1><spring:message code="question.create"/></h1>
<form:form method="POST" action="/question_create.html" modelAttribute="form">
    <form:errors path="" element="div"/>
    <div>
        <form:label path="questionText"><spring:message code="question.text"/></form:label>
        <form:textarea path="questionText"/>
        <form:errors path="questionText"/>
    </div>
    <div>
        <form:label path="questionCategoryId"><spring:message code="question.category.name"/></form:label>
        <form:input type="hidden"  path="questionCategoryId" id="questionCategoryInput" />
        <select id="questionCategorySelect">
            <c:forEach var="item" items="${questionCategories}">
                <option value="${item.getId()}">${item.getName()}</option>
            </c:forEach>
        </select>
        <form:errors path="questionCategoryId"/>
    </div>
    <div>
        <%int value=((QuestionCreateForm)request.getAttribute("form")).getAnswersNumber();%>
        <table>
            <tr>
                <td>Proponowana odpowiedz</td>
                <td>Czy odpowiedz jest poprawna?</td>
            </tr>
            <c:forEach begin="1" end="<%=value%>" varStatus="i" >
                <tr>
                    <td><form:input path="answerTexts"></form:input> </td>
                    <td>
                        <form:input value="false"  path="corrects"></form:input>
                    </td>
                </tr>
                <tr>
                    <td><form:errors path="answerTexts"/> </td>
                    <td> <form:errors path="corrects"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div>
        <form:label path="answersNumber"><spring:message code="question.anserwsNumber"/></form:label>
        <form:input type="number" min="2" max="10" path="answersNumber"/>
        <form:errors path="answersNumber"/>

        <form:input type="hidden" id="hiddenInp" path="answersNumberChanged"/>
    </div>
    <div>
        <input type="submit" onclick="getDataFromSelect()"/>
        <input type="submit" value="Zmien ilosc odpowiedzi" onclick="answersNumberChanged9()"/>
    </div>
    </form:form>
</body>
</html>
