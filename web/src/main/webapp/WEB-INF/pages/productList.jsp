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
    <hr>
    <p>
    <h2>
        <spring:message code="found"/>
        <c:out value="${phoneQuantity}"/> <spring:message code="phones"/>
    </h2>
    <div id="success-result">
    </div>
    <div id="error-result">
    </div>
    <div id="ajax-errors">
    </div>
    <table border="1px">
        <thead>
        <tr>
            <td><spring:message code="image"/></td>
            <td>
                <spring:message code="brand"/>
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
                <spring:message code="displaySize"/>
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
                    <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
                </td>
                <td><c:out value="${phone.brand}"/></td>
                <td><a href="${pageContext.servletContext.contextPath}/productDetails/${phone.id}"><c:out
                        value="${phone.model}"/></a></td>
                <td>
                    <c:forEach var="color" items="${phone.colors}">
                        <c:out value="${color.code}"/><br>
                    </c:forEach>
                </td>
                <td><c:out value="${phone.displaySizeInches}"/>"</td>
                <td>$ <c:out value="${phone.price}"/></td>
                <td>
                    <input class="quantity-input" type="text" id="quantity${phone.id}" name="quantity" value="1"/>
                    <div class="result-error" id="result${phone.id}"></div>
                    <input id="phoneId${phone.id}" name="phoneId" type="hidden" value="${phone.id}"/>
                </td>
                <td>
                    <button onclick="addToCart(${phone.id})" class="btn btn-success">
                        <spring:message code="addToCart"/>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="pages-links">
        <div class="pages-links">
            <a href="${pageContext.request.contextPath}/productList?field=${not empty param.field ? param.field : null}&order=${not empty param.order ? param.order : null}&search=${not empty param.search ? param.search : null}&page=${empty param.page ? 1 : (param.page > 1 ? param.page - 1 : 1)}"><spring:message code="previousPage"/></a>
            <a href="${pageContext.request.contextPath}/productList?field=${not empty param.field ? param.field : null}&order=${not empty param.order ? param.order : null}&search=${not empty param.search ? param.search : null}&page=${empty param.page ? (pages eq 1 ? 1 : 2) : (param.page < pages ? param.page + 1 : pages)}"><spring:message code="nextPage"/></a>
        </div>
    </div>
    </body>
</tags:master>