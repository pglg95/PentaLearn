<%--
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
            var select = document.getElementById("questionCategorySelect");
            var id =parseInt(select.options[select.selectedIndex].value);
            var input=document.getElementById("questionCategoryInput");
            input.value=id;
        }
       function  generateAnswerFields(){
            var number = document.getElementById("answersNumber").value;
            var container = document.getElementById("container");
            while (container.hasChildNodes()) {
                container.removeChild(container.lastChild);
            }
            for (var i=0;i<number;i++){
                container.appendChild(document.createTextNode("Odpowiedz" + (i+1)));

                var input = document.createElement("input");
                input.type = "text";
                input.name = "member";
                container.appendChild(input);

                var input = document.createElement("input");
                input.type = "checkbox";
                input.name = "cmember";
                container.appendChild(input);

                container.appendChild(document.createElement("br"));
            }
        }
        function getAnswersNumber(){
            return document.getElementById("answersNumber").value;
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

            <form:input path="answerTexts"></form:input>
            <form:errors path="answerTexts"/>
            <form:input path="corrects"></form:input>
            <form:errors path="corrects"/>
            <br>

    </div>
    <div>
        <label><spring:message code="question.anserwsNumber"/></label>
        <input name="dupa" type="number" id="answersNumber" min="1" max="10" value="1" ></input>
        <input type="button" id="button" value="sdfsdf" onclick="generateAnswerFields()" >
    </div>
    <div id="container"></div>
    <div>
        <input type="submit" onclick="getDataFromSelect()"/>
    </div>
</form:form>
</body>
</html>
