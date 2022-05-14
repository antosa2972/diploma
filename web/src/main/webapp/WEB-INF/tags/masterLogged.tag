<%@ tag trimDirectiveWhitespaces="true" %>
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
            <%@include file="/js/delete-device.js"%>
        </script>
    </c:if>
    <c:if test="${pageContext.response.locale eq 'ru'}">
        <script type="text/javascript" charset="UTF-8">
            <%@include file="/js/delete-device_RU.js"%>
        </script>
    </c:if>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a href="${pageContext.servletContext.contextPath}/admin/main" class="navbar-brand"><spring:message code="page.admin.console"/>
    </a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/admin/orders" class="nav-link"><spring:message code="page.admin.order.management"/></a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/admin/device-operations/add" class="nav-link"><spring:message code="page.admin.add.device"/></a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/admin/device-operations/update-delete" class="nav-link"><spring:message code="page.admin.add.phone"/></a>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link"><spring:message code="page.admin.users"/></a>
            </li>
        </ul>
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