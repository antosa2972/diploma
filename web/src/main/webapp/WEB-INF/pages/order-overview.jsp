<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tags:master pageTitle="Order overview">
    <head>
        <title><spring:message code="page.order.overview"/></title>
    </head>
    <body>
    <br>
    <h2>
        <spring:message code="order.page"/> ${id} <spring:message code="admin.status"/>:
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
                                <span class="dot" style="background-color: ${color.code}"></span>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><c:out value="${orderItem.phone.displaySizeInches}"/>"</td>
                <td><c:out value="${orderItem.quantity}"/></td>
                <td><c:out value="${orderItem.phone.actualPrice.multiply(orderItem.quantity)}"/> <spring:message code="currency"/></td>
            </tr>
        </c:forEach>
        <tr>
            <td style="border: none!important;"></td>
            <td style="border: none!important;"></td>
            <td style="border: none!important;"></td>
            <td style="border: none!important;"></td>
            <td><spring:message code="order.subtotal"/></td>
            <td><c:out value="${order.subtotal}"/> <spring:message code="currency"/></td>
        </tr>
        <tr>
            <td style="border: none!important;"></td>
            <td style="border: none!important;"></td>
            <td style="border: none!important;"></td>
            <td style="border: none!important;"></td>
            <td><spring:message code="order.delivery"/></td>
            <td><c:out value="${order.deliveryPrice}"/> <spring:message code="currency"/></td>
        </tr>
        <tr>
            <td style="border: none!important;"></td>
            <td style="border: none!important;"></td>
            <td style="border: none!important;"></td>
            <td style="border: none!important;"></td>
            <td><spring:message code="order.total"/></td>
            <td><c:out value="${order.totalPrice}"/> <spring:message code="currency"/></td>
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
    <form action="${pageContext.servletContext.contextPath}/product-list">
        <button class="btn btn-outline-primary">
            <spring:message code="button.back.to.productList"/>
        </button>
    </form>
    </body>
</tags:master>
