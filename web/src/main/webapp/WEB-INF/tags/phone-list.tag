<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<table id="myTable">
	<thead style="border-top: none">
	<tr>
		<td><spring:message code="phone.image"/></td>
		<td>
			<spring:message code="phone.brand"/>
			<tags:sortLink field="brand" order="asc"/>
			<tags:sortLink field="brand" order="desc"/>
		</td>
		<td>
			<spring:message code="model"/>
			<tags:sortLink field="model" order="asc"/>
			<tags:sortLink field="model" order="desc"/>
		</td>
		<td><spring:message code="color"/></td>
		<td>
			<spring:message code="phone.display.size"/>
			<tags:sortLink field="displaySizeInches" order="asc"/>
			<tags:sortLink field="displaySizeInches" order="desc"/>
		</td>
		<td>
			<spring:message code="price"/>
			<tags:sortLink field="price" order="asc"/>
			<tags:sortLink field="price" order="desc"/>
		</td>
		<td><spring:message code="quantity"/></td>
		<td><spring:message code="action"/></td>
	</tr>
	</thead>
	<c:forEach var="phone" items="${phones}">
		<tr>
			<td>
				<div class="d-flex align-items-center">
					<img src="${phone.imageUrl}" alt="logo" style="width: 100px; height: 95px">
				</div>
			</td>
			<td><c:out value="${phone.brand}"/></td>
			<td>
				<a href="${pageContext.servletContext.contextPath}/product-details/${phone.id}">
					<c:out value="${phone.model}"/>
				</a>
			</td>
			<td>
				<c:if test="${not empty phone.colors}">
					<c:forEach var="color" items="${phone.colors}">
						<span class="dot" style="background-color: ${color.code}"></span>
					</c:forEach>
				</c:if>
			</td>
			<td><c:out value="${phone.displaySizeInches}"/>"</td>
			<td><spring:message code="currency"/> <c:out value="${phone.price}"/>
				<c:if test="${phone.discountPercent != 0}">
				- <c:out value="${phone.discountPercent}"/>%
				<br>

				<div style="color:green">
					<spring:message code="currency"/> <c:out value="${phone.actualPrice}"/>
				</div>
			</td>
			</c:if>
			<td>
				<input class="quantity-input" type="number" id="quantity${phone.id}" name="quantity" value="1"/>
				<div class="result-error" id="result${phone.id}"></div>
				<input id="phoneId${phone.id}" name="phoneId" type="hidden" value="${phone.id}"/>
			</td>
			<td>
				<button onclick="addToCart(${phone.id})" class="btn btn-outline-primary">
					<spring:message code="addToCart"/>
				</button>
			</td>
		</tr>
	</c:forEach>
</table>
</html>