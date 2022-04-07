<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sping" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:master pageTitle="Contacts page">
	<body>
	<br>
	<br>
		<table id="myTable">
			<tr>
				<td style="border: none">
					<spring:message code="page.contacts.mobile.number.text"/>
				</td>
				<td style="border: none">
				<strong><spring:message code="page.contacts.mobile.number"/></strong>
				</td>
			</tr>
			<tr>
				<td style="border: none"><spring:message code="page.contacts.stationar.number.text"/></td>
				<td style="border: none"><strong><spring:message code="page.contacts.stationar.number"/></strong></td>
			</tr>
			<tr>
				<td style="border: none"><spring:message code="page.contacts.address.text"/></td>
				<td style="border: none"><strong><spring:message code="page.contacts.address"/></strong></td>
			</tr>
			<tr>
				<td style="border: none"><spring:message code="page.contacts.email.text"/></td>
				<td style="border: none"><strong><spring:message code="page.contacts.email"/></strong></td>
			</tr>
			<tr>
				<td style="border: none"><spring:message code="page.contacts.telegram.text"/></td>
				<td style="border: none"><strong><spring:message code="page.contacts.telegram"/></strong></td>
			</tr>
		</table>
	<br>
	</body>
</tags:master>