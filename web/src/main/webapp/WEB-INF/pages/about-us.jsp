<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sprng" uri="http://java.sun.com/jsp/jstl/fmt" %>
<tags:master pageTitle="Cart">
	<div class="bg-light">
		<div class="container py-5">
			<div class="row h-100 align-items-center py-5">
				<div class="col-lg-6">
					<h1 class="display-4"><spring:message code="page.about.us.page.name"/></h1>
					<p class="lead text-muted mb-0"><spring:message code="page.about.us.first.text"/></p>
				</div>
				<div class="col-lg-6 d-none d-lg-block"><img src="https://bootstrapious.com/i/snippets/sn-about/illus.png"
																			alt="" class="img-fluid"></div>
			</div>
		</div>
	</div>

	<div class="bg-white py-5">
		<div class="container py-5">
			<div class="row align-items-center mb-5">
				<div class="col-lg-6 order-2 order-lg-1"><i class="fa fa-bar-chart fa-2x mb-3 text-primary"></i>
					<h2 class="font-weight-light"><spring:message code="page.about.us.discounts.info.name"/></h2>
					<p class="font-italic text-muted mb-4"><spring:message code="page.about.us.discounts.info"/></p><a
							href="${pageContext.servletContext.contextPath}/hot-prices"
							class="btn btn-light px-5 rounded-pill shadow-sm">
						<spring:message code="page.hot.prices"/></a>
				</div>
				<div class="col-lg-5 px-5 mx-auto order-1 order-lg-2"><img
						src="https://bootstrapious.com/i/snippets/sn-about/img-1.jpg" alt="" class="img-fluid mb-4 mb-lg-0">
				</div>
			</div>
			<div class="row align-items-center">
				<div class="col-lg-5 px-5 mx-auto"><img src="https://bootstrapious.com/i/snippets/sn-about/img-2.jpg" alt=""
																	 class="img-fluid mb-4 mb-lg-0"></div>
				<div class="col-lg-6"><i class="fa fa-leaf fa-2x mb-3 text-primary"></i>
					<h2 class="font-weight-light"><spring:message code="page.about.us.for.business.info.name"/></h2>
					<p class="font-italic text-muted mb-4"><spring:message code="page.about.us.for.business.info"/></p>
					<a
							href="${pageContext.servletContext.contextPath}/contacts"
							class="btn btn-light px-5 rounded-pill shadow-sm">
						<spring:message code="page.about.us.for.business.info.for.suppliers"/> </a>
					<a
							href="${pageContext.servletContext.contextPath}/quick-order"
							class="btn btn-light px-5 rounded-pill shadow-sm">
						<spring:message code="page.about.us.for.business.info.for.for.buyers"/></a>
				</div>
			</div>
		</div>
	</div>

	<div class="bg-light py-5">
		<div class="container py-5">
			<div class="row mb-4">
				<div class="col-lg-5">
					<h2 class="display-4 font-weight-light"><spring:message
							code="page.about.us.out.founders.and.employees"/></h2>
					<p class="font-italic text-muted"><spring:message
							code="page.about.us.out.founders.and.employees.description"/></p>
				</div>
			</div>
			<div class="row text-center">
				<c:forEach var="employee" items="${employees}">
					<c:choose>
						<c:when test="${employee.employeeType eq 'FEMALE'}">
							<div class="col-xl-3 col-sm-6 mb-5">
								<div class="bg-white rounded shadow-sm py-5 px-4"><img
										src="https://bootstrapious.com/i/snippets/sn-about/avatar-4.png" alt="" width="100"
										class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
									<h5 class="mb-0">${employee.firstName} ${employee.lastName}</h5><span
											class="small text-uppercase text-muted">${employee.position}</span>
									<ul class="social mb-0 list-inline mt-3">
										<li class="list-inline-item"><a href="#" class="social-link"><i
												class="fa fa-facebook-f"></i></a>
										</li>
										<li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-twitter"></i></a>
										</li>
										<li class="list-inline-item"><a href="#" class="social-link"><i
												class="fa fa-instagram"></i></a>
										</li>
									</ul>
								</div>
							</div>
						</c:when>
						<c:when test="${employee.employeeType eq 'MALE'}">
							<div class="col-xl-3 col-sm-6 mb-5">
								<div class="bg-white rounded shadow-sm py-5 px-4"><img
										src="https://bootstrapious.com/i/snippets/sn-about/avatar-3.png" alt="" width="100"
										class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
									<h5 class="mb-0">${employee.firstName} ${employee.lastName}</h5><span
											class="small text-uppercase text-muted">${employee.position}</span>
									<ul class="social mb-0 list-inline mt-3">
										<li class="list-inline-item"><a href="#" class="social-link"><i
												class="fa fa-facebook-f"></i></a>
										</li>
										<li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-twitter"></i></a>
										</li>
										<li class="list-inline-item"><a href="#" class="social-link"><i
												class="fa fa-instagram"></i></a>
										</li>
									</ul>
								</div>
							</div>
						</c:when>
						<c:when test="${employee.employeeType eq 'GEEK'}">
							<div class="col-xl-3 col-sm-6 mb-5">
								<div class="bg-white rounded shadow-sm py-5 px-4"><img
										src="https://bootstrapious.com/i/snippets/sn-about/avatar-2.png" alt="" width="100"
										class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
									<h5 class="mb-0">${employee.firstName} ${employee.lastName}</h5><span
											class="small text-uppercase text-muted">${employee.position}</span>
									<ul class="social mb-0 list-inline mt-3">
										<li class="list-inline-item"><a href="#" class="social-link"><i
												class="fa fa-facebook-f"></i></a>
										</li>
										<li class="list-inline-item"><a href="#" class="social-link"><i class="fa fa-twitter"></i></a>
										</li>
										<li class="list-inline-item"><a href="#" class="social-link"><i
												class="fa fa-instagram"></i></a>
										</li>
									</ul>
								</div>
							</div>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	</div>
	</div>
</tags:master>