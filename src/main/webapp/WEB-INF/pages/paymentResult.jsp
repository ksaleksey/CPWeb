<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 04.10.2016
  Time: 22:23
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
<%--<form:form  commandName="payment" action="execPaymentPrc">--%>
  <table>

    <tr>Счет успешно пополнен</tr>
    <tr>
      <td>Номер счета: </td>
      <td>${accnum}</td>
    </tr>
    <tr>
      <td>Сумма платежа: </td>
      <td>${summ}</td>
    </tr>
    <tr>
      <td>Время совершения операции </td>
      <td>${time}</td>
    </tr>
      <tr><td><a href="../CPWeb/">В начало</a></td></tr>

  </table>
</body>
</html>

