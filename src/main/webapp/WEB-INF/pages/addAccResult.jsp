<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 03.10.2016
  Time: 3:21
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

</head>

<body>
    <h1>Новый лицевой счет</h1>
<%--<form:form  commandName="account" action="checkAccPrc">--%>
  <table>

  <tr>Создан новый счет:</tr>
  <tr>
  <td>Номер счета: </td>
  <td>${accnum}</td>
  </tr>
  <tr>
  <td>Собственник: </td>
  <td>${fam} ${im} ${otch}</td>
  </tr>
  <tr>
  <td>Серия и номер паспорта собственника:</td>
  <td>${passport}</td>
  </tr>
  <tr>
  <td>Адрес обекта:</td>
  <td>${address}</td>
  </tr>
  <%--<td><input type="submit" value="Пополнить счет"/></td>--%>
  </tr>
    <tr><td><a href="../CPWeb/">В начало</a></td></tr>

  </table>
  <%--</form:form>--%>
</body>
</html>
