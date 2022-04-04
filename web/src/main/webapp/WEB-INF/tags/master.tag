<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
	<link href='http://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
	<script src="https://code.jquery.com/jquery-1.8.3.js"></script>
	<script type="text/javascript">
      <%@include file="/js/main.js"%>
	</script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
			integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
			  integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
			  crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a href="${pageContext.servletContext.contextPath}/productList" class="navbar-brand"><spring:message
			code="page.phoneshop.logo"/>
	</a>
	<div class="collapse navbar-collapse">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item">
				<a href="#" class="nav-link">Homepage</a>
			</li>
			<li class="nav-item">
				<a href="#" class="nav-link">Hot Prices</a>
			</li>
			<li class="nav-item">
				<a href="#" class="nav-link">Contacts</a>
			</li>
			<li class="nav-item">
				<a href="#" class="nav-link">About us</a>
			</li>
		</ul>
		<form method="get" class="form-inline my-2 my-lg-0 mr-auto"
				action="${pageContext.servletContext.contextPath}/productList">
			<input name="search" class="form-control mr-sm-2" type="search" placeholder="search" aria-label="search"
					 aria-describedby="basic-addon2"
					 value="${not empty param.search ? param.search : ''}">
			<button class="btn btn-outline-success my-2 my-sm-0"><spring:message code="search"/></button>
		</form>
		<span class="navbar-text">
			<sec:authorize access="hasRole('ADMIN')">
				<a href="${pageContext.servletContext.contextPath}/admin/orders">
					<sec:authentication property="principal.username"/>
				</a>,
				<a href="${pageContext.servletContext.contextPath}/logout"><spring:message code="page.logout"/></a>
			</sec:authorize>
			<sec:authorize access="hasRole('ADMIN') eq false">
				<a href="${pageContext.servletContext.contextPath}/login"
					class="btn btn-outline-primary btn-sm"><spring:message
						code="page.login"/></a>
			</sec:authorize>
			<a href="${pageContext.servletContext.contextPath}/cart">
				<spring:message code="cart"/>
				<div id="cart-quantity" class="l">
					<c:out value="${cart.totalQuantity}"/>,
				</div>
				<spring:message code="items"/>:
				<div id="cart-totalCost" class="l">
					<c:out value="${cart.totalCost}"/>
				</div>
				<spring:message code="usd"/>
			</a>
		</span>
	</div>
</nav>
<main>
	<jsp:doBody/>
</main>
<footer>
	<p>
		(c) Expert soft
	</p>
</footer>
</body>
</html>