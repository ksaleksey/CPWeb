<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/resources/core/css/bootstrap.min.css" rel="stylesheet" />
	<title>Возникла непредвиденная ошибка (((</title>
	<style>
		body{
			/*background-image: url("resources/core/images/errorcat.jpg");*/
			background: url('resources/core/images/errorcat.jpg') top repeat-x;
			background-size: 150px;
		}
	</style>
</head>

<body>
			
	<table>
		<tr>
			<h1>Возникла непредвиденная ошибка</h1>
		</tr>
		<tr>
			<h3>${}</h3>
		</tr>

		<tr>

				<c:out value="${stackTrace}"/>

		</tr>

	</table>

	<%--<div class="container">--%>

		<%--<h1>Error Page</h1>--%>

		<%--<p>${innerEx.message}</p>--%>
		<%--<!-- Exception: ${innerEx.message}.--%>
		  	<%--<c:forEach items="${innerEx.stackTrace}" var="stackTrace">--%>
				<%--${stackTrace} --%>
			<%--</c:forEach>--%>
	  	<%---->--%>

	<%--</div>--%>

	<%--<jsp:include page="fragments/footer.jsp" />--%>
	<tr><td><a href="../CPWeb/">В начало</a></td></tr>
</body>
</html>