<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<tags:masterLogged pageTitle="Update/Delete">
	<br>
	<div id="success-result">
	</div>
	<div id="error-result">
	</div>
	<br>
	<form method="get" class="form-inline my-2 my-lg-0 mr-auto"
			action="${pageContext.servletContext.contextPath}/admin/device-operations/update-delete">
		<input name="search" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="search"
				 aria-describedby="basic-addon2"
				 value="${not empty param.search ? param.search : ''}">
		<button class="btn btn-outline-primary my-2 my-sm-0"><spring:message code="search"/></button>
	</form>
	<br>
	<table id="myTable">
		<thead style="border-top: none">
		<tr>
			<td><spring:message code="phone.image"/></td>
			<td>
				<spring:message code="phone.brand"/>
			</td>
			<td>
				<spring:message code="model"/>
			</td>
			<td><spring:message code="color"/></td>
			<td>
				<spring:message code="phone.display.size"/>
			</td>
			<td>
				<spring:message code="price"/>
			</td>
			<td><spring:message code="action"/></td>
		</tr>
		</thead>
		<c:forEach var="phone" items="${phones}">
			<tr>
				<td>
					<div class="d-flex align-items-center">
						<img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}"
							  alt="logo" style="width: 85px; height: 85px" class="rounded-circle">
					</div>
				</td>
				<td><c:out value="${phone.brand}"/></td>
				<td>
					<a href="${pageContext.servletContext.contextPath}/update/${phone.id}">
						<c:out value="${phone.model}"/>
					</a>
				</td>
				<td>
					<c:forEach var="color" items="${phone.colors}">
						<c:out value="${color.code}"/><br>
					</c:forEach>
				</td>
				<td><c:out value="${phone.displaySizeInches}"/>"</td>
				<td>$ <c:out value="${phone.price}"/>
					<c:if test="${phone.discountPercent != 0}">
					- <c:out value="${phone.discountPercent}"/>%
					<br>

					<div style="color:green">
						$ <c:out value="${phone.actualPrice}"/>
					</div>
				</td>
				</c:if>
				<td>
					<input id="phoneId${phone.id}" name="phoneId" type="hidden" value="${phone.id}"/>
					<div class="result-error" id="result${phone.id}"></div>
					<button class="btn btn-outline-danger" onclick="deleteDevice(${phone.id},this)">
						<spring:message code="page.admin.delete.device"/>
					</button>
					<button class="btn btn-outline-primary">
						<spring:message code="page.admin.update.device"/>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div style="text-align:center; position: center;">
		<a href="${pageContext.request.contextPath}/admin/device-operations/update-delete?search=${not empty param.search ? param.search : null}&page=${empty param.page ? 1 : (param.page > 1 ? param.page - 1 : 1)}"><img
				src="${pageContext.servletContext.contextPath}/images/previous.png" alt="Prev Page" height="30" width="30"/></a>
		<a href="${pageContext.request.contextPath}/admin/device-operations/update-delete?search=${not empty param.search ? param.search : null}&page=${empty param.page ? (pages eq 1 ? 1 : 2) : (param.page <= pages ? param.page + 1 : pages)}"><img
				src="${pageContext.servletContext.contextPath}/images/next.png" alt="Next Page" height="30" width="30"/></a>
	</div>
</tags:masterLogged>