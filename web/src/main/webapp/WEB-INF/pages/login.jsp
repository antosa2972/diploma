<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<tags:master pageTitle="404">
    <html>
    <head>
        <title><spring:message code="loginPage"/></title>
    </head>
    <body>
    <br>
    <div id="error-result">
        <c:if test="${not empty error}">
            ${error}
        </c:if>
    </div>
    <form action="${pageContext.servletContext.contextPath}/login" method="post">
        <table>
            <tr>
                <td style="border:none"><spring:message code="page.login.username"/></td>
                <td style="border:none">
                    <input type="text" name="username"/>
                </td>
            </tr>
            <tr>
                <td style="border:none"><spring:message code="page.login.password"/></td>
                <td style="border:none">
                    <input type="password" name="password"/>
                </td>
            </tr>
        </table>
        <br>
        <div>
            <button class="btn btn-outline-primary" type="submit"><spring:message code="page.login"/></button>
        </div>
    </form>
    </body>
    </html>
</tags:master>
