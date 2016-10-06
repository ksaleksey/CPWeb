<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 01.10.2016
  Time: 3:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <%--<link href="/resources/core/css/bootstrap.min.css" rel="stylesheet" />--%>
  <title>Коммунальные платежи</title>
    <style>
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
        </style>
</head>
<body>


    <h1>Коммунальные платежи</h1>
    <form:form  commandName="account" action="checkAccPrc">
    <table>

      <tr>
          <td><form:label path = "accnum">Ведите номер счета:</form:label></td>
          <td><form:input maxlength="9" path="accnum" placeholder="000000000"/></td>
          <td><input type="submit" value="Далее"/></td>

      </tr>
        <tr><form:errors path="accnum" cssClass="error"/></tr>
    <tr><td><a href="addAcc">Создать лицевой счет</a></td></tr>
    <tr><td><a href="findAcc">Найти лицевой счет</a></td></tr>
      </table>
    </form:form>
</body>
</html>
