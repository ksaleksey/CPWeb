<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 04.10.2016
  Time: 20:54
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
<form:form  commandName="payment" action="execPaymentPrc">
  <table>

    <tr>Пополняемый счет:</tr>
    <tr>
      <td>Номер счета: </td>
      <td>${payment.account.accnum}</td>
    </tr>
    <tr>
      <td>Собственник: </td>
      <td>${payment.account.person.fam} ${payment.account.person.im} ${payment.account.person.otch}</td>
    </tr>
    <tr>
      <td>Паспорn собственника:</td>
      <td>${payment.account.person.passport}</td>
    </tr>
    <tr>
      <td>Адрес обекта:</td>
      <td>${payment.account.address}</td>
    </tr>

    <tr>
      <td>Сумма платежа: </td>
      <td><form:input path="summ"/></td>
      <td><input type="submit" value="Внести на счет"/></td>
    </tr>

    <tr> <td><form:errors path="summ" cssClass="error"/><td></tr>
    <%--<tr><a href="addAcc">Создать лицевой счет</a></tr>--%>
    <%--<tr><a href="findAcc">Найти лицевой счет</a></tr>--%>
  </table>
</form:form>
</body>
</html>

