<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:master pageTitle="Product Details">
	<head>
		<script src="https://code.jquery.com/jquery-1.8.3.js"></script>
		<title><spring:message code="titleDetails"/></title>
	</head>
	<body>
	<div class="body-details">
		<div class="block-left">
			<br>
			<form action="${pageContext.servletContext.contextPath}/product-list" method="get">
				<button class="btn btn-outline-primary"><spring:message code="button.back.to.productList"/></button>
			</form>
			<div id="success-result">
			</div>
			<div id="error-result">
			</div>
			<div id="ajax-errors">
			</div>
			<h3>
				<strong>${phone.model}</strong>
			</h3>
			<img class="img" src="${phone.imageUrl}">
			<br>
			<br>
			<textarea disabled readonly rows="4" cols="50" translate="yes">${phone.description}</textarea>
			<br>
			<br>
			<h3>
				<spring:message code="price"/>:<c:out value="${phone.price}"/>
				<c:if test="${phone.discountPercent != 0}">
					- <c:out value="${phone.discountPercent}"/>%
					<br>

					<div style="color:green">
						<spring:message code="currency"/> <c:out value="${phone.actualPrice}"/>
					</div>
				</c:if>
			</h3>
			<div class="form-inline my-2 my-lg-0 mr-auto">
				<input type="text" id="quantity${phone.id}" name="quantity" class="form-control mr-sm-2"
						 placeholder="quantity" aria-label="quantity" aria-describedby="basic-addon2"
						 value="1">
				<input id="phoneId${phone.id}" name="phoneId" type="hidden" value="${phone.id}"/>
				<div class="input-group-append">
					<button onclick="addToCart(${phone.id})" class="btn btn-outline-primary"><spring:message
							code="addToCart"/></button>
				</div>
				<div class="result-error" id="result${phone.id}"></div>
			</div>
		</div>
		<div class="block-right">
			<h2><spring:message code="display"/></h2>
			<table>
				<tr>
					<td><spring:message code="size"/></td>
					<td>${phone.displaySizeInches}</td>
				</tr>
				<tr>
					<td><spring:message code="resolution"/></td>
					<td>${phone.displayResolution}</td>
				</tr>
				<tr>
					<td><spring:message code="technology"/></td>
					<td>${phone.displayTechnology}</td>
				</tr>
				<tr>
					<td><spring:message code="pixelDensity"/></td>
					<td>${phone.pixelDensity}</td>
				</tr>
			</table>
			<h2><spring:message code="dimensionAndWeight"/></h2>
			<table>
				<tr>
					<td><spring:message code="length"/></td>
					<td>${phone.lengthMm}mm</td>
				</tr>
				<tr>
					<td><spring:message code="width"/></td>
					<td>${phone.widthMm}mm</td>
				</tr>
				<tr>
					<td><spring:message code="color"/></td>
					<td>
						<c:choose>
							<c:when test="${empty phone.colors}">
                                   <span class="result-error">
                                       no info
                                   </span>
							</c:when>
							<c:otherwise>
								<c:forEach var="color" items="${phone.colors}">
									<span class="dot" style="background-color: ${color.code}"></span>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td><spring:message code="weight"/></td>
					<td>${phone.weightGr}</td>
				</tr>
			</table>
			<h2><spring:message code="camera"/></h2>
			<table>
				<tr>
					<td><spring:message code="front"/></td>
					<td>${phone.frontCameraMegapixels} megapixels</td>
				</tr>
				<tr>
					<td><spring:message code="back"/></td>
					<td>${phone.backCameraMegapixels} megapixels</td>
				</tr>
			</table>
			<h2><spring:message code="battery"/></h2>
			<table>
				<tr>
					<td><spring:message code="talkTime"/></td>
					<td>
						<c:choose>
							<c:when test="${empty phone.talkTimeHours}">
                                   <span class="result-error">
                                       no info
                                   </span>
							</c:when>
							<c:otherwise>
								${phone.talkTimeHours} часов
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td><spring:message code="standBy"/></td>
					<td>
						<c:choose>
							<c:when test="${empty phone.standByTimeHours}">
                                   <span class="result-error">
                                       no info
                                   </span>
							</c:when>
							<c:otherwise>
								${phone.standByTimeHours} часов
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td><spring:message code="batteryCapacity"/></td>
					<td>
						<c:choose>
							<c:when test="${empty phone.batteryCapacityMah}">
                                   <span class="result-error">
                                       no info
                                   </span>
							</c:when>
							<c:otherwise>
								${phone.batteryCapacityMah}mAh
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
			<h2><spring:message code="other"/></h2>
			<table>
				<tr>
					<td><spring:message code="deviceType"/></td>
					<td>${phone.deviceType}</td>
				</tr>
				<tr>
					<td><spring:message code="bluetooth"/></td>
					<td>${phone.bluetooth}</td>
				</tr>
			</table>
		</div>
	</div>
	</body>
</tags:master>