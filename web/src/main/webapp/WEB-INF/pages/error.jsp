<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<tags:master pageTitle="404">
<html>
<head>
  <title><spring:message code="error.title"/></title>
</head>
<body>
<c:if test="${status eq '404'}">
  <h3><spring:message code="page.not.found"/></h3>
</c:if>
<c:if test="${status eq '500'}">
  <h3><spring:message code="internal.server.error"/></h3>
</c:if>
<c:if test="${status eq '403'}">
  <h3><spring:message code="access.forbidden"/></h3>
</c:if>
<br>
<spring:message code="contact.admin"/> <a href="${adminMail}"><spring:message code="admin.mail"/></a>
</body>
</html>
</tags:master>
