<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 30.09.2016
  Time: 23:37
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
<h1>Поиск счетов</h1>

<form id = "searchForm" action="findAcc" method="post">
<table>
  <tr>
    <td>
      <label for="fam">Фамилия:</label>
      <input type="text" id="fam" name="fam" value="${fam}" placeholder="Иванов">

      <label for="im">Имя:</label>
      <input type="text" id="im" name="im" value="${im}" placeholder="Иван">

      <label for="otch">Отчество:</label>
      <input type="text" id="otch" name="otch" value="${otch}" placeholder="Иванович">
    </td>
  </tr>
  <tr>
    <td >
      <label for="address">Адрес:</label>
      <input type="text" id="address" name="address" value="${address}" placeholder="Москва,Красная площадь,l">
      <input type="submit" form="searchForm" value="Найти">
    </td>
  </tr>
</table>

</form>

<br/>

  <c:if test="${accounts.size() > 0}">
    <form:form commandName="account" method="POST" action="checkAccPrc">
    <table border="1">
      <tr>

        <th width="100">Номер счета</th>

        <th width="100">Фамилия</th>
        <th width="200">Имя</th>
        <th width="100">Отчество</th>
        <th width="100%">Адрес</th>
        <th width="10">Оплатить</th>
      </tr>
      <c:forEach items="${accounts}" var="acc">
        <tr>

          <td>
            <c:out value="${acc[0].accnum}" />
          </td>
          <td>
            <c:out value="${acc[1].fam}" />
          </td>
          <td>
            <c:out value="${acc[1].im}" />
          </td>
          <td>
            <c:out value="${acc[1].otch}" />
          </td>
          <td>
            <c:out value="${acc[0].address}" />
          </td>
          <td>
            <form:radiobutton path="accnum" value="${acc[0].accnum}" />
          </td>

        </tr>

      </c:forEach>


      <tr>
        <td>
          <input type="submit"  value="Оплатить"/>
        </td>
      </tr>
    </table>
</form:form>
    <br/>
  </c:if>
<tr><td><a href="../CPWeb/">В начало</a></td></tr>

</body>
</html>
