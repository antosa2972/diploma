<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<tags:master pageTitle="Cart">
    <head>
        <title><spring:message code="cart.page"/></title>
    </head>
    <body>
    <br>
    <form method="get" action="${pageContext.servletContext.contextPath}/product-list">
        <button class="btn btn-outline-primary">
            <spring:message code="button.back.to.productList"/>
        </button>
    </form>
    <h2><spring:message code="cart"/></h2>
    <div id="error-result">
        <c:if test="${error eq true}">
            <spring:message code="cart.update.error"/>
        </c:if>
    </div>
    <div id="error-result">
        <c:if test="${empty cart}">
            <spring:message code="cart.empty.msg"/>
        </c:if>
    </div>
    <div id="success-result">
        <c:if test="${successUpdate eq true}">
            <spring:message code="cart.update.success"/>
        </c:if>
    </div>
    <div id="success-result">
        <c:if test="${successDelete eq true}">
            <spring:message code="cart.delete.success"/>
        </c:if>
    </div>
    <form:form id="update-form" method="post" action="${pageContext.servletContext.contextPath}/cart"
               modelAttribute="phoneArrayDto">
        <table>
            <thead>
            <tr>
                <td>
                    <spring:message code="phone.brand"/>
                </td>
                <td>
                    <spring:message code="model"/>
                </td>
                <td>
                    <spring:message code="color"/>
                </td>
                <td>
                    <spring:message code="phone.display.size"/>
                </td>
                <td>
                    <spring:message code="price"/>
                </td>
                <td>
                    <spring:message code="quantity"/>
                </td>
                <td>
                    <spring:message code="action"/>
                </td>
            </tr>
            </thead>
            <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="status">
                <tr>
                    <td>
                        <c:out value="${cartItem.phone.brand}"/>
                    </td>
                    <td>
                        <c:out value="${cartItem.phone.model}"/>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${empty cartItem.phone.colors}">
                                   <span class="result-error">
                                       no info
                                   </span>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="color" items="${cartItem.phone.colors}">
                                    <span class="dot" style="background-color: ${color.code}"></span>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:out value="${cartItem.phone.displaySizeInches}"/>"
                    </td>
                    <td>
                        <c:out value="${cartItem.phone.actualPrice}"/> <spring:message code="currency"/>
                    </td>
                    <td>
                        <input class="quantity-input" type="text" id="quantity${cartItem.phone.id}" name="quantity"
                               value="${not empty errorsId && errorsId.contains(cartItem.phone.id) ? paramValues['cartItem.quantity'][status.index] : cartItem.quantity }"/>
                        <div class="result-error" id="result${cartItem.phone.id}"></div>
                        <c:if test="${fn:contains(errorsId, cartItem.phone.id)}">
                            <div class="result-error">
                                <spring:message code="cart.wrong.input.or.out.of.stock"/>
                            </div>
                        </c:if>
                        <input id="phoneId${cartItem.phone.id}" name="phoneId" type="hidden"
                               value="${cartItem.phone.id}"/>
                    </td>
                    <td>
                        <button class="btn btn-danger"
                                formaction="${pageContext.servletContext.contextPath}/cart/${cartItem.phone.id}">
                            <spring:message code="cart.delete.btn"/>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form:form>
    <button form="update-form" class="btn btn-outline-primary">
        <spring:message code="cart.update.btn"/>
    </button>
    <button form="goToOrder" class="btn btn-outline-primary">
        <spring:message code="order.make.btn"/>
    </button>

    <form id="goToOrder" action="${pageContext.servletContext.contextPath}/order" method="get"/>
    </body>
</tags:master>