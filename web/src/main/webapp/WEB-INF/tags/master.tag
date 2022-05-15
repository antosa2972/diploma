<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ attribute name="pageTitle" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
	<link href='http://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
	<script src="https://code.jquery.com/jquery-1.8.3.js"></script>
	<c:if test="${pageContext.response.locale eq 'en'}">
		<script type="text/javascript">
         <%@include file="/js/main.js"%>
		</script>
	</c:if>
	<c:if test="${pageContext.response.locale eq 'ru'}">
		<script type="text/javascript" charset="UTF-8">
         <%@include file="/js/main_ru.js"%>
		</script>
	</c:if>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
			integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
			  integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
			  crossorigin="anonymous"></script>
</head>
<body>
<nav id="navbar" class="navbar navbar-expand-lg navbar-light nav-borderb sticky-top"
	  style="background-color:deepskyblue">
	<a href="${pageContext.servletContext.contextPath}/product-list" class="navbar-brand"><spring:message
			code="page.phoneshop.logo"/>
	</a>
	<div class="collapse navbar-collapse">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item">
				<a href="${pageContext.servletContext.contextPath}/product-list" class="nav-link"><spring:message
						code="page.homepage"/> </a>
			</li>
			<li class="nav-item">
				<a href="${pageContext.servletContext.contextPath}/hot-prices" class="nav-link"><spring:message
						code="page.hot.prices"/></a>
			</li>
			<li class="nav-item">
				<a href="${pageContext.servletContext.contextPath}/contacts" class="nav-link"><spring:message
						code="page.contacts"/></a>
			</li>
			<li class="nav-item">
				<a href="${pageContext.servletContext.contextPath}/about-us" class="nav-link"><spring:message
						code="page.about.us.page.name"/> </a>
			</li>
		</ul>
		<form method="get" class="form-inline my-2 my-lg-0 mr-auto"
				action="${pageContext.servletContext.contextPath}/product-list">
			<input name="search" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="search"
					 aria-describedby="basic-addon2"
					 value="${not empty param.search ? param.search : ''}">
			<button class="btn btn-outline-light my-2 my-sm-0"><spring:message code="search"/></button>
		</form>
		<div class="mr-lg-auto">
			<a href="?lang=en"><img src="${pageContext.servletContext.contextPath}/images/english.svg" alt="English"
											width="55" height="40"></a>
			<a href="?lang=ru"><img src="${pageContext.servletContext.contextPath}/images/russian.svg" alt="English"
											width="55" height="40"></a>
		</div>
		<span class="navbar-text">
			<sec:authorize access="hasRole('ADMIN')">
				<a href="${pageContext.servletContext.contextPath}/admin/orders">
					<c:out value="${pageContext.request.userPrincipal.name}"/>
				</a>,
				<a href="${pageContext.servletContext.contextPath}/logout"><spring:message code="page.logout"/></a>
			</sec:authorize>

			<sec:authorize access="isAuthenticated() eq false">
				<a href="${pageContext.servletContext.contextPath}/login"
					class="btn btn-light btn-sm"><spring:message
						code="page.login"/></a>
			</sec:authorize>

			<sec:authorize access="hasRole('CUSTOMER')">
				<a style="font-weight: bold"><c:out value="${pageContext.request.userPrincipal.name}"/>,</a>
				<a class="btn btn-light btn-sm" href="${pageContext.servletContext.contextPath}/logout"><spring:message
						code="page.logout"/></a>
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
			</sec:authorize>
		</span>
	</div>
</nav>
<main>
	<jsp:doBody/>
</main>
<footer class="card-footer">
	<div class="bg-light align-content-lg-center" style="text-align:center; position: center;">
		<ul class="social mb-0 list-inline mt-3">
			<li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-facebook-f"></i></a>
			</li>
			<li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-twitter"></i></a></li>
			<li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-instagram"></i></a>
			</li>
			<li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-youtube"></i></a>
			</li>
		</ul>
		<h5>
			(c) Anton Pushnenkov (BSUIR)
		</h5>
	</div>
</footer>
</body>
</html>