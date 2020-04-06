<%--
  Created by IntelliJ IDEA.
  User: aleksey
  Date: 31.03.2020
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Catalog</title>
</head>
<body>

<c:url value="/main" var="mainPage"/>
<c:url value="/cart" var="cartPage"/>
<c:url value="/order" var="orderPage"/>
<c:url value="/product" var="productPage"/>
<c:url value="/catalog" var="catalogPage"/>

<div>
    <h3>Menu</h3>
    <ul>
        <li>
            <a href="${mainPage}">Main</a>
        </li>
        <li>
            <a href="${cartPage}">Cart</a>
        </li>
        <li>
            <a href="${orderPage}">Order</a>
        </li>
        <li>
            <a href="${productPage}">Product</a>
        </li>
        <li>
            <a href="${catalogPage}">Catalog</a>
        </li>
    </ul>
</div>
<div>
    <h3>Catalog</h3>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Cost</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${products.size() != 0}">
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.cost}</td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
