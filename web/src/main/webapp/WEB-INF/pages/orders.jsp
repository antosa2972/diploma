<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<tags:masterLogged pageTitle="Orders">
    <html>
    <head>
        <title><spring:message code="admin.ordersPage"/></title>
    </head>
    <body>
    <h2>
        <spring:message code="admin.ordersPage"/>:
    </h2>
    <table>
        <tr>
            <td>
                <spring:message code="admin.orderNumber"/>
            </td>
            <td>
                <spring:message code="admin.customer"/>
            </td>
            <td>
                <spring:message code="admin.phoneNum"/>
            </td>
            <td>
                <spring:message code="admin.address"/>
            </td>
            <td>
                <spring:message code="admin.date"/>
            </td>
            <td>
                <spring:message code="admin.totalPrice"/>
            </td>
            <td>
                <spring:message code="admin.status"/>
            </td>
        </tr>
        <c:forEach var="order" items="${orderList}">
            <tr>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/admin/orders/${order.id}">
                        <c:out value="${order.id}"/>
                    </a>
                </td>
                <td>
                    <c:out value="${order.firstName}"/>
                    <c:out value="${order.lastName}"/>
                </td>
                <td>
                    <c:out value="${order.contactPhoneNo}"/>
                </td>
                <td>
                    <c:out value="${order.deliveryAddress}"/>
                </td>
                <td>
                    <c:out value="${order.date.toString()}"/>
                </td>
                <td>
                    <c:out value="${order.totalPrice}"/>
                </td>
                <td>
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
                </td>
            </tr>
        </c:forEach>
    </table>
    </body>
    </html>
</tags:masterLogged>
