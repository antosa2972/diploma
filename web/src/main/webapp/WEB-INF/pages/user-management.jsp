<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<tags:masterLogged pageTitle="Update/Delete">
	<br>
	<table style="position: absolute; left: 40%; top: 10%">
		<tr>
			<td><spring:message code="page.login.username"/></td>
			<td><spring:message code="page.admin.user.role"/></td>
			<td><spring:message code="action"/></td>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>
					<c:out value="${user.userName}"/>
				</td>
				<td>
					<c:out value="${user.role}"/>
				</td>
				<td>
					<c:if test="${user.isAccountNonLocked eq 1}">
						<div style="color:blue" id="success-blocking${user.userName}"></div>
						<button id="button${user.userName}" class="btn btn-outline-danger"
								  onclick="updateStatus('${user.userName}',${user.isAccountNonLocked},this)">
							<spring:message code="page.admin.block.user"/>
						</button>
					</c:if>
					<c:if test="${user.isAccountNonLocked eq 0}">
						<div style="color:blue" id="success-blocking${user.userName}"></div>
						<button id="button${user.userName}" class="btn btn-outline-primary"
								  onclick="updateStatus('${user.userName}',${user.isAccountNonLocked},this)">
							<spring:message code="page.admin.unblock.user"/>
						</button>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div style="text-align:center; position: center;">
		<a href="${pageContext.request.contextPath}/admin/user-management?page=${empty param.page ? 1 : (param.page > 1 ? param.page - 1 : 1)}"><img
				src="${pageContext.servletContext.contextPath}/images/previous.png" alt="Prev Page" height="30" width="30"/></a>
		<a href="${pageContext.request.contextPath}/admin/user-management?page=${empty param.page ? (pages eq 1 ? 1 : 2) : (param.page <= pages ? param.page + 1 : pages)}"><img
				src="${pageContext.servletContext.contextPath}/images/next.png" alt="Next Page" height="30" width="30"/></a>
	</div>
</tags:masterLogged>