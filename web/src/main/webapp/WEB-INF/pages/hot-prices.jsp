<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tags:master pageTitle="Product List">
	<head>
		<title><spring:message code="title"/></title>
		<script src="https://code.jquery.com/jquery-1.8.3.js"></script>
	</head>
	<body>
	<p>
	<div id="tableContainer-1">
		<div id="tableContainer-2">
			<tags:phone-list/>
		</div>
	</div>
	</body>
</tags:master>