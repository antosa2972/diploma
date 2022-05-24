<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:master pageTitle="Product List">
	<head>
		<title><spring:message code="title"/></title>
		<script src="https://code.jquery.com/jquery-1.8.3.js"></script>
	</head>
	<body>
	<p>
	<div class="abc">
		<h2>
			<spring:message code="found"/>
			<c:out value="${phoneQuantity}"/> <spring:message code="phones"/>
			<div style="">
				<a href="${pageContext.request.contextPath}/product-list?field=${not empty param.field ? param.field : null}&order=${not empty param.order ? param.order : null}&search=${not empty param.search ? param.search : null}&page=${empty param.page ? 1 : (param.page > 1 ? param.page - 1 : 1)}"><img
						src="${pageContext.servletContext.contextPath}/images/previous.png" alt="Prev Page" height="30"
						width="30"/></a>
				<a href="${pageContext.request.contextPath}/product-list?field=${not empty param.field ? param.field : null}&order=${not empty param.order ? param.order : null}&search=${not empty param.search ? param.search : null}&page=${empty param.page ? (pages eq 1 ? 1 : 2) : (param.page < pages ? param.page + 1 : pages)}"><img
						src="${pageContext.servletContext.contextPath}/images/next.png" alt="Next Page" height="30" width="30"/></a>
			</div>
			<div id="success-result">
			</div>
			<div id="error-result">
			</div>
			<div id="ajax-errors">
			</div>
		</h2>
	</div>
	<tags:phone-list/>
	</body>
</tags:master>