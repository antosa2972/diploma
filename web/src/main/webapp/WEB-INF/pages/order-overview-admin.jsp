<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tags:masterLogged pageTitle="Order overview">
	<head>
		<title><spring:message code="page.order.overview"/></title>
	</head>
	<br>
	<h2>
		<spring:message code="order.page"/> ${id}, <spring:message code="admin.status"/>:
		<c:if test="${order.status.name() eq 'NEW'}">
			<spring:message code="order.new"/>
		</c:if>
		<c:if test="${order.status.name() eq 'DELIVERED'}">
			<spring:message code="admin.button.delivered"/>
		</c:if>
		<c:if test="${order.status.name() eq 'IN_DELIVERY'}">
			<spring:message code="admin.button.in.delivery"/>
		</c:if>
		<c:if test="${order.status.name() eq 'REJECTED'}">
			<spring:message code="admin.button.rejected"/>
		</c:if>
	</h2>
	<table class="table-bordered w-50">
		<tr>
			<td><spring:message code="phone.brand"/></td>
			<td><spring:message code="model"/></td>
			<td><spring:message code="color"/></td>
			<td><spring:message code="phone.display.size"/></td>
			<td><spring:message code="quantity"/></td>
			<td><spring:message code="price"/></td>
		</tr>
		<c:forEach var="orderItem" items="${order.orderItems}">
			<tr>
				<td><c:out value="${orderItem.phone.brand}"/></td>
				<td><c:out value="${orderItem.phone.model}"/></td>
				<td>
					<c:choose>
						<c:when test="${empty orderItem.phone.colors}">
                                   <span class="result-error">
                                       no info
                                   </span>
						</c:when>
						<c:otherwise>
							<c:forEach var="color" items="${orderItem.phone.colors}">
								<c:out value="${color.code},"/>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</td>
				<td><c:out value="${orderItem.phone.displaySizeInches}"/>"</td>
				<td><c:out value="${orderItem.quantity}"/></td>
				<td><c:out value="${orderItem.phone.price.multiply(orderItem.quantity)}"/> <spring:message
						code="currency"/></td>
			</tr>
		</c:forEach>
		<tr>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td><spring:message code="order.subtotal"/></td>
			<td><c:out value="${order.subtotal} $"/></td>
		</tr>
		<tr>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td><spring:message code="order.delivery"/></td>
			<td><c:out value="${order.deliveryPrice} $"/></td>
		</tr>
		<tr>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td style="border: none!important;"></td>
			<td><spring:message code="order.total"/></td>
			<td><c:out value="${order.totalPrice} $"/></td>
		</tr>
	</table>
	<br>
	<br>
	<table class="table table-bordered table-borderless w-25">
		<tr>
			<td>
				<spring:message code="order.data.firstName"/>
			</td>
			<td>
				<c:out value="${order.firstName}"/>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="order.data.lastName"/>
			</td>
			<td>
				<c:out value="${order.lastName}"/>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="order.data.address"/>
			</td>
			<td>
				<c:out value="${order.deliveryAddress}"/>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="order.data.phone"/>
			</td>
			<td>
				<c:out value="${order.contactPhoneNo}"/>
			</td>
		</tr>
		<tr>
			<td>
				<spring:message code="order.data.additionalInfo"/>
			</td>
			<td>
				<c:out value="${order.additionalInfo}"/>
			</td>
		</tr>
	</table>
	<form id="change-status-delivered" action="${pageContext.servletContext.contextPath}/admin/orders/${order.id}">
		<input name="orderId" type="hidden" value="${order.id}">
		<input type="hidden" name="orderStatus" value="DELIVERED">
	</form>

	<form id="change-status-rejected" action="${pageContext.servletContext.contextPath}/admin/orders/${order.id}">
		<input name="orderId" type="hidden" value="${order.id}">
		<input type="hidden" name="orderStatus" value="REJECTED">
	</form>

	<form id="change-status-in-delivery"
			action="${pageContext.servletContext.contextPath}/admin/orders/${order.id}">
		<input name="orderId" type="hidden" value="${order.id}">
		<input type="hidden" name="orderStatus" value="IN_DELIVERY">
	</form>
	<c:choose>
		<c:when test="${order.status.name().equals('NEW')}">
			<spring:message code="page.admin.change.order.status"/>
			<button form="change-status-rejected" formmethod="post" class="btn btn-outline-danger">
				<spring:message code="admin.button.rejected"/>
			</button>
			<button form="change-status-in-delivery" formmethod="post" class="btn btn-outline-primary">
				<spring:message code="admin.button.in.delivery"/>
			</button>
			<button disabled form="change-status-delivered" formmethod="post" class="btn btn-outline-success">
				<spring:message code="admin.button.delivered"/>
			</button>
		</c:when>

		<c:when test="${order.status.name().equals('DELIVERED')}">
			<button disabled form="change-status-rejected" formmethod="post" class="btn btn-outline-danger">
				<spring:message code="admin.button.rejected"/>
			</button>
			<button disabled form="change-status-in-delivery" formmethod="post" class="btn btn btn-outline-primary">
				<spring:message code="admin.button.in.delivery"/>
			</button>
			<button disabled form="change-status-delivered" formmethod="post" class="btn btn-success">
				<spring:message code="admin.button.delivered"/>
			</button>
		</c:when>

		<c:when test="${order.status.name().equals('REJECTED')}">
			<button disabled form="change-status-rejected" formmethod="post" class="btn btn btn-danger">
				<spring:message code="admin.button.rejected"/>
			</button>
			<button disabled form="change-status-in-delivery" formmethod="post" class="btn btn btn-outline-primary">
				<spring:message code="admin.button.in.delivery"/>
			</button>
			<button disabled form="change-status-delivered" formmethod="post" class="btn btn-outline-success">
				<spring:message code="admin.button.delivered"/>
			</button>
		</c:when>

		<c:when test="${order.status.name().equals('IN_DELIVERY')}">
			<spring:message code="page.admin.change.order.status"/>
			<button disabled form="change-status-rejected" formmethod="post" class="btn btn btn-outline-danger">
				<spring:message code="admin.button.rejected"/>
			</button>
			<button disabled form="change-status-in-delivery" formmethod="post" class="btn btn btn-primary">
				<spring:message code="admin.button.in.delivery"/>
			</button>
			<button form="change-status-delivered" formmethod="post" class="btn btn-outline-success">
				<spring:message code="admin.button.delivered"/>
			</button>
		</c:when>
	</c:choose>
	<br>
	<form id="back-form"></form>
	<button form="back-form" formmethod="get" formaction="${pageContext.servletContext.contextPath}/admin/orders"
			  class="btn btn-secondary">
		<spring:message code="admin.button.back"/>
	</button>
	<br>
</tags:masterLogged>
