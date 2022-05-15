<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<tags:master pageTitle="404">
	<html>
	<head>
		<title><spring:message code="page.registration.register"/></title>
	</head>
	<body>
	<br>
	<div id="error-result">
		<c:if test="${not empty error}">
			<spring:message code="page.registration.error.while.registration"/>
		</c:if>
		<c:if test="${not empty errorEmpty}">
			<spring:message code="page.registration.error.while.registration.empty"/>
		</c:if>
	</div>
	<form action="${pageContext.servletContext.contextPath}/register" method="post" onchange="checkPassword(this)">
		<div style="color:red" id="password-mismatch"></div>
		<table>
			<tr>
				<td style="border:none"><spring:message code="page.login.username"/></td>
				<td style="border:none">
					<input type="text" name="username"/>
				</td>
			</tr>
			<tr>
				<td style="border:none"><spring:message code="page.login.password"/></td>
				<td style="border:none">
					<input name="password1" type="password"/>
					<div style="color:red" id="password1-err"></div>
				</td>
			</tr>
			<tr>
				<td style="border:none"><spring:message code="page.registration.repeat.password"/></td>
				<td style="border:none">
					<input name="password2" type="password"/>
					<div style="color:red" id="password2-err"></div>
				</td>
			</tr>
		</table>
		<br>
		<div>
			<button class="btn btn-outline-primary" type="submit"><spring:message
					code="page.registration.register"/></button>
		</div>
	</form>
	</body>
	</html>
</tags:master>
