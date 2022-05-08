<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<tags:masterLogged pageTitle="Main Admin">
	<div style="text-align: center; margin-top: 0">
		<img style="width: 200px;height: 200px" src="${pageContext.servletContext.contextPath}/images/phoneshop-logo.jpg" alt="Phoneshop Logo"/>
		<h2>
			<br>
			<spring:message code="page.admin.greetings1"/>
			<b><c:out value="${username}"/>.</b>
			<br>
			<spring:message code="page.admin.greeting2"/>
		</h2>
	</div>
	<ul class="admin-list">
		<li><a class="font-weight-bold h2" href="${pageContext.servletContext.contextPath}/admin/orders"><spring:message
				code="page.admin.order.management"/></a></li>
		<li><a class="font-weight-bold h2" href="#"><spring:message code="page.admin.users"/></a></li>
		<li><a class="font-weight-bold h2" href="#"><spring:message code="page.admin.add.phone"/></a></li>
	</ul>
</tags:masterLogged>