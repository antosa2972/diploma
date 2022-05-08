<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tags:master pageTitle="Order confirmation">
	<head>
		<title><spring:message code="page.order.confirmation"/></title>
	</head>
	<body>
	<br>
	<h2>
		<spring:message code="order.page"/>
	</h2>
	<form method="get" action="${pageContext.servletContext.contextPath}/cart">
		<button class="btn btn-outline-primary">
			<spring:message code="button.back.to.cartPage"/>
		</button>
	</form>
	<div id="error-result">
		<c:if test="${error eq true}">
			<spring:message code="order.error"/>
		</c:if>
	</div>
	<div id="error-result">
		<c:if test="${cart.cartItems.size() eq 0 }">
			<spring:message code="cart.empty.msg"/>
		</c:if>
	</div>
	<table class="table-bordered w-50">
		<tr>
			<td><spring:message code="phone.brand"/></td>
			<td><spring:message code="model"/></td>
			<td><spring:message code="color"/></td>
			<td><spring:message code="phone.display.size"/></td>
			<td><spring:message code="quantity"/></td>
			<td><spring:message code="price"/></td>
		</tr>
		<c:forEach var="cartItem" items="${cart.cartItems}">
			<tr>
				<td><c:out value="${cartItem.phone.brand}"/></td>
				<td><c:out value="${cartItem.phone.model}"/></td>
				<td>
					<c:choose>
						<c:when test="${empty cartItem.phone.colors}">
                                   <span class="result-error">
                                       no info
                                   </span>
						</c:when>
						<c:otherwise>
							<c:forEach var="color" items="${cartItem.phone.colors}">
								<c:out value="${color.code},"/>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</td>
				<td><c:out value="${cartItem.phone.displaySizeInches}"/>"</td>
				<td><c:out value="${cartItem.quantity}"/></td>
				<td><c:out value="${cartItem.price}"/> <spring:message code="usd"/> * ${cartItem.quantity}</td>
			</tr>
		</c:forEach>
		<tr>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td><spring:message code="order.subtotal"/></td>
			<td><c:out value="${cart.totalCost} $"/></td>
		</tr>
		<tr>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td><spring:message code="order.delivery"/></td>
			<td><c:out value="${deliveryPrice} $"/></td>
		</tr>
		<tr>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td><spring:message code="order.total"/></td>
			<td><c:out value="${totalCost} $"/></td>
		</tr>
	</table>
	<br>
	<br>
	<form:form id="place-order" method="post" action="${pageContext.servletContext.contextPath}/order"
				  modelAttribute="orderDataDto">
		<table class="table table-bordered table-borderless w-25">
			<tr>
				<td>
					<spring:message code="order.data.firstName"/>
				</td>
				<td>
					<input type="text" name="firstName"/>
					<tags:error name="firstName" errors="${errors}"/>
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="order.data.lastName"/>
				</td>
				<td>
					<input type="text" name="lastName"/>
					<tags:error name="lastName" errors="${errors}"/>
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="order.data.address"/>
				</td>
				<td>
					<input type="text" name="address"/>
					<tags:error name="address" errors="${errors}"/>
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="order.data.phone"/>
				</td>
				<td>
					<input type="text" name="phone"/>
					<tags:error name="phone" errors="${errors}"/>
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="order.data.additionalInfo"/>
				</td>
				<td>
					<textarea placeholder="Additional information" aria-label="Additional information"
								 name="additionalInfo"></textarea>
				</td>
			</tr>
		</table>
	</form:form>
	<button form="place-order" class="btn btn-outline-primary">
		<spring:message code="order.make.btn"/>
	</button>
	</body>
</tags:master>
