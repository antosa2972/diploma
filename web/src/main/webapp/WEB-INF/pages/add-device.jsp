<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<tags:masterLogged pageTitle="Add device">
	<head>
		<script src="https://code.jquery.com/jquery-1.8.3.js"></script>
		<title><spring:message code="page.admin.add.device"/></title>
	</head>
	<body>
	<c:choose>
		<c:when test="${not empty isUpdatePage and isUpdatePage eq true}">
			<h3>
				<spring:message code="page.admin.update.device"/>
			</h3>
		</c:when>
		<c:otherwise>
			<h3>
				<spring:message code="page.admin.add.device"/>
			</h3>
		</c:otherwise>
	</c:choose>
	<c:if test="${not empty error}">
		<h4>
			<font style="color: red"><c:out value="${error}"/></font>
		</h4>
	</c:if>
	<c:if test="${not empty success}">
		<h4>
			<font style="color:blue"><c:out value="${success}"/></font>
		</h4>
	</c:if>
	<c:choose>
		<c:when test="${empty isUpdatePage or isUpdatePage eq false}">
			<form:form name="add-device-form"
						  action="${pageContext.request.contextPath}/admin/device-operations/add"
						  method="post" modelAttribute="phoneAddDto">
				<div class="body-details">
					<div class="block-left">
						<br>
						<h3><spring:message code="page.admin.main.info"/></h3>
						<div class="input-group mb-3 w-25">
							<table>
								<tr>
									<td>
										<spring:message code="phone.brand"/>
									</td>
									<td>
										<input type="text" name="brand" value="${recentData.brand}"/>
										<c:if test="${not empty errors.getFieldError('brand')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td>
										<spring:message code="model"/>
									</td>
									<td>
										<input type="text" name="model" value="${recentData.model}"/>
										<c:if test="${not empty errors.getFieldError('model')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td>
										<spring:message code="phone.discount.percent"/>
									</td>
									<td>
										<input type="text" name="discountPercent" value="${recentData.discountPercent}"/>
										<c:if test="${not empty errors.getFieldError('discountPercent')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td>
										<spring:message code="phone.image"/>
									</td>
									<td>
										<input type="text" name="imageUrl" value="${recentData.imageUrl}"/>
										<c:if test="${not empty errors.getFieldError('imageUrl')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td>
										<spring:message code="price"/>
									</td>
									<td>
										<input type="text" name="price" value="${recentData.price}"/>
										<c:if test="${not empty errors.getFieldError('price')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td>
										<spring:message code="quantity"/>
									</td>
									<td>
										<input type="text" name="quantity" value="${recentData.quantity}"/>
										<c:if test="${not empty errors.getFieldError('quantity')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if>
									</td>
								</tr>
								<tr>
									<td><spring:message code="page.admin.description"/></td>
									<td><textarea name="description" rows="4" cols="50" style="resize: none">${recentData.description}</textarea><c:if
											test="${not empty errors.getFieldError('frontCameraMegapixels')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if></td>
								</tr>
							</table>
							<br>
							<br>
						</div>
					</div>
					<div class="block-right">
						<h2><spring:message code="display"/></h2>
						<table>
							<tr>
								<td><spring:message code="size"/></td>
								<td><input type="text" name="displaySizeInches" value="${recentData.displaySizeInches}"/>
									<c:if test="${not empty errors.getFieldError('displaySizeInches')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if></td>
							</tr>
							<tr>
								<td><spring:message code="resolution"/></td>
								<td><input type="text" name="displayResolution" value="${recentData.displayResolution}"/> px
									<c:if test="${not empty errors.getFieldError('displayResolution')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if></td>
							</tr>
							<tr>
								<td><spring:message code="technology"/></td>
								<td><input type="text" name="displayTechnology" value="${recentData.displayTechnology}"/>
									<c:if test="${not empty errors.getFieldError('displayTechnology')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if></td>
							</tr>
							<tr>
								<td><spring:message code="pixelDensity"/></td>
								<td><input type="text" name="pixelDensity" value="${recentData.pixelDensity}"/>
									<c:if test="${not empty errors.getFieldError('pixelDensity')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if></td>
							</tr>
						</table>
						<br>
						<h2><spring:message code="dimensionAndWeight"/></h2>
						<table>
							<tr>
								<td><spring:message code="length"/></td>
								<td><input type="text" name="lengthMm" value="${recentData.lengthMm}"/>
									<c:if test="${not empty errors.getFieldError('lengthMm')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if></td>
							</tr>
							<tr>
								<td><spring:message code="width"/></td>
								<td><input type="text" name="widthMm" value="${recentData.widthMm}"/>
									<c:if test="${not empty errors.getFieldError('widthMm')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if></td>
							</tr>
							<tr>
								<td><spring:message code="color"/></td>
								<td>
									<input type="text" name="colors" value="${recentData.colors}"/>
									<c:if test="${not empty errors.getFieldError('colors')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if>
								</td>
							</tr>
							<tr>
								<td><spring:message code="weight"/></td>
								<td><input type="text" name="weightGr" value="${recentData.weightGr}"/>
									<c:if test="${not empty errors.getFieldError('weightGr')}">
									<font style="color:red"><spring:message code="page.admin.validation.invalid.field"/></font>
									</c:if>
							</tr>
						</table>
					</div>
				</div>
				<div style="width: 50%; margin-top: -320px; bottom: 35px">
					<div class="flex-container">
						<div class="flex-child">
							<h2><spring:message code="other"/></h2>
							<table>
								<tr>
									<td><spring:message code="deviceType"/></td>
									<td><input type="text" name="deviceType" value="${recentData.deviceType}"/>
										<c:if test="${not empty errors.getFieldError('deviceType')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td><spring:message code="bluetooth"/></td>
									<td><input type="text" name="bluetooth" value="${recentData.bluetooth}"/>
										<c:if test="${not empty errors.getFieldError('bluetooth')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
							</table>
							<br>
							<button class="btn btn-outline-primary" type="submit"><spring:message
									code="page.admin.add.device"/></button>
						</div>
						<div class="flex-child">
							<h2><spring:message code="battery"/></h2>
							<table>
								<tr>
									<td><spring:message code="talkTime"/></td>
									<td>
										<input type="text" name="talkTimeHours" value="${recentData.talkTimeHours}"/>
										<c:if test="${not empty errors.getFieldError('talkTimeHours')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td><spring:message code="standBy"/></td>
									<td><input type="text" name="standByTimeHours" value="${recentData.standByTimeHours}"/>
										<c:if test="${not empty errors.getFieldError('standByTimeHours')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td><spring:message code="batteryCapacity"/></td>
									<td>
										<input type="text" name="batteryCapacityMah" value="${recentData.batteryCapacityMah}"/>mAh
										<c:if test="${not empty errors.getFieldError('battaryCapacityMah')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
							</table>
						</div>
						<div class="flex-child">
							<h2><spring:message code="camera"/></h2>
							<table>
								<tr>
									<td><spring:message code="front"/></td>
									<td><input type="text" name="frontCameraMegapixels"
												  value="${recentData.frontCameraMegapixels}"/>
										megapixels
										<c:if test="${not empty errors.getFieldError('frontCameraMegapixels')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td><spring:message code="back"/></td>
									<td><input type="text" name="backCameraMegapixels"
												  value="${recentData.backCameraMegapixels}"/>
										megapixels
										<c:if test="${not empty errors.getFieldError('backCameraMegapixels')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</form:form>
		</c:when>
		<c:otherwise>
			<form:form name="update-phone-form"
						  action="${pageContext.request.contextPath}/admin/device-operations/update-delete/update/${id}"
						  method="post" modelAttribute="phoneAddDto">
				<div class="body-details">
					<div class="block-left">
						<br>
						<h3><spring:message code="page.admin.main.info"/></h3>
						<div class="input-group mb-3 w-25">
							<table>
								<tr>
									<td>
										<spring:message code="phone.brand"/>
									</td>
									<td>
										<input type="text" name="brand" value="${recentData.brand}"/>
										<c:if test="${not empty errors.getFieldError('brand')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td>
										<spring:message code="model"/>
									</td>
									<td>
										<input type="text" name="model" value="${recentData.model}"/>
										<c:if test="${not empty errors.getFieldError('model')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td>
										<spring:message code="phone.discount.percent"/>
									</td>
									<td>
										<input type="text" name="discountPercent" value="${recentData.discountPercent}"/>
										<c:if test="${not empty errors.getFieldError('discountPercent')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td>
										<spring:message code="phone.image"/>
									</td>
									<td>
										<input type="text" name="imageUrl" value="${recentData.imageUrl}"/>
										<c:if test="${not empty errors.getFieldError('imageUrl')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td>
										<spring:message code="price"/>
									</td>
									<td>
										<input type="text" name="price" value="${recentData.price}"/>
										<c:if test="${not empty errors.getFieldError('price')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td>
										<spring:message code="quantity"/>
									</td>
									<td>
										<input type="text" name="quantity" value="${quantity.stock}"/>
										<c:if test="${not empty errors.getFieldError('quantity')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if>
									</td>
								</tr>
								<tr>
									<td><spring:message code="page.admin.description"/></td>
									<td><textarea name="description" rows="4" cols="50" style="resize: none">${recentData.description}</textarea><c:if
											test="${not empty errors.getFieldError('frontCameraMegapixels')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if></td>
								</tr>
							</table>
							<br>
							<br>
						</div>
					</div>
					<div class="block-right">
						<h2><spring:message code="display"/></h2>
						<table>
							<tr>
								<td><spring:message code="size"/></td>
								<td><input type="text" name="displaySizeInches" value="${recentData.displaySizeInches}"/>
									<c:if test="${not empty errors.getFieldError('displaySizeInches')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if></td>
							</tr>
							<tr>
								<td><spring:message code="resolution"/></td>
								<td><input type="text" name="displayResolution" value="${recentData.displayResolution}"/> px
									<c:if test="${not empty errors.getFieldError('displayResolution')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if></td>
							</tr>
							<tr>
								<td><spring:message code="technology"/></td>
								<td><input type="text" name="displayTechnology" value="${recentData.displayTechnology}"/>
									<c:if test="${not empty errors.getFieldError('displayTechnology')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if></td>
							</tr>
							<tr>
								<td><spring:message code="pixelDensity"/></td>
								<td><input type="text" name="pixelDensity" value="${recentData.pixelDensity}"/>
									<c:if test="${not empty errors.getFieldError('pixelDensity')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if></td>
							</tr>
						</table>
						<br>
						<h2><spring:message code="dimensionAndWeight"/></h2>
						<table>
							<tr>
								<td><spring:message code="length"/></td>
								<td><input type="text" name="lengthMm" value="${recentData.lengthMm}"/>
									<c:if test="${not empty errors.getFieldError('lengthMm')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if></td>
							</tr>
							<tr>
								<td><spring:message code="width"/></td>
								<td><input type="text" name="widthMm" value="${recentData.widthMm}"/>
									<c:if test="${not empty errors.getFieldError('widthMm')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if></td>
							</tr>
							<tr>
								<td><spring:message code="color"/></td>
								<td>
									<input type="text" name="colors" value="${colors}"/>
									<c:if test="${not empty errors.getFieldError('colors')}">
										<font style="color:red"><spring:message
												code="page.admin.validation.invalid.field"/></font>
									</c:if>
								</td>
							</tr>
							<tr>
								<td><spring:message code="weight"/></td>
								<td><input type="text" name="weightGr" value="${recentData.weightGr}"/>
									<c:if test="${not empty errors.getFieldError('weightGr')}">
									<font style="color:red"><spring:message code="page.admin.validation.invalid.field"/></font>
									</c:if>
							</tr>
						</table>
					</div>
				</div>
				<div style="width: 50%; margin-top: -320px; bottom: 35px">
					<div class="flex-container">
						<div class="flex-child">
							<h2><spring:message code="other"/></h2>
							<table>
								<tr>
									<td><spring:message code="deviceType"/></td>
									<td><input type="text" name="deviceType" value="${recentData.deviceType}"/>
										<c:if test="${not empty errors.getFieldError('deviceType')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td><spring:message code="bluetooth"/></td>
									<td><input type="text" name="bluetooth" value="${recentData.bluetooth}"/>
										<c:if test="${not empty errors.getFieldError('bluetooth')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
							</table>
							<br>
							<button class="btn btn-outline-primary" type="submit"><spring:message
									code="page.admin.update.device"/></button>
						</div>
						<div class="flex-child">
							<h2><spring:message code="battery"/></h2>
							<table>
								<tr>
									<td><spring:message code="talkTime"/></td>
									<td>
										<input type="text" name="talkTimeHours" value="${recentData.talkTimeHours}"/>
										<c:if test="${not empty errors.getFieldError('talkTimeHours')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td><spring:message code="standBy"/></td>
									<td><input type="text" name="standByTimeHours" value="${recentData.standByTimeHours}"/>
										<c:if test="${not empty errors.getFieldError('standByTimeHours')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td><spring:message code="batteryCapacity"/></td>
									<td>
										<input type="text" name="batteryCapacityMah" value="${recentData.batteryCapacityMah}"/>mAh
										<c:if test="${not empty errors.getFieldError('battaryCapacityMah')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
							</table>
						</div>
						<div class="flex-child">
							<h2><spring:message code="camera"/></h2>
							<table>
								<tr>
									<td><spring:message code="front"/></td>
									<td><input type="text" name="frontCameraMegapixels"
												  value="${recentData.frontCameraMegapixels}"/>
										megapixels
										<c:if test="${not empty errors.getFieldError('frontCameraMegapixels')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
								<tr>
									<td><spring:message code="back"/></td>
									<td><input type="text" name="backCameraMegapixels"
												  value="${recentData.backCameraMegapixels}"/>
										megapixels
										<c:if test="${not empty errors.getFieldError('backCameraMegapixels')}">
											<font style="color:red"><spring:message
													code="page.admin.validation.invalid.field"/></font>
										</c:if></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</form:form>
		</c:otherwise>
	</c:choose>
	<br>
	<br>
	<br>
	</body>
</tags:masterLogged>