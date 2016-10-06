<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 30.09.2016
  Time: 23:16
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
<h1>Новый лицевой счет</h1>
<form:form  commandName="account" action="addAccPrc">
  <table>

    <tr>
      <td><form:label path = "person.fam">Фамилия:</form:label></td>
      <td><form:input path = "person.fam" placeholder="Фамилия"/></td>
      <td><form:errors path = "person.fam" cssClass="error"/></td>
    </tr>
    <tr>
      <td><form:label path = "person.im">Имя:</form:label></td>
      <td><form:input path = "person.im" placeholder="Имя"/></td>
      <td><form:errors path = "person.im" cssClass="error"/></td>
    </tr>
    <tr>
      <td><form:label path = "person.otch">Отчество:</form:label></td>
      <td><form:input path = "person.otch" placeholder="Отчество"/></td>
      <td><form:errors path = "person.otch" cssClass="error"/></td>
    </tr>
    <tr>
      <td><form:label path = "person.passport">Серия и номер паспорта:</form:label></td>
      <td><form:input path = "person.passport" placeholder="0000000000"/></td>
      <td><form:errors path = "person.passport" cssClass="error"/></td>
    </tr>
    <tr>
      <td><form:label path = "address">Адрес:</form:label></td>
      <td><form:input path = "address" placeholder="Адрес"/></td>
      <td><form:errors path = "address" cssClass="error"/></td>
    </tr>
    <tr>
      <td>
        <input type="submit" value="Создать">
      </td>
    </tr>

  </table>
</form:form>

</body>
</html>
