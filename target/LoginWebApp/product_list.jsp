<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
    <style>
        table { border-collapse: collapse; width: 100%; margin: 20px 0; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .btn { padding: 8px 15px; text-decoration: none; border-radius: 3px; margin: 0 5px; }
        .btn-add { background-color: #4CAF50; color: white; }
        .btn-edit { background-color: #2196F3; color: white; }
        .btn-delete { background-color: #f44336; color: white; }
        .header { display: flex; justify-content: space-between; align-items: center; }
        .error { color: red; padding: 10px; background-color: #ffebee; margin: 10px 0; }
    </style>
</head>
<body>

<div style="max-width: 1200px; margin: 0 auto; padding: 20px;">
    <div class="header">
        <h1>Product List</h1>
        <a href="${pageContext.request.contextPath}/products/new" class="btn btn-add">Add New Product</a>
    </div>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Color</th>
            <th>Size</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% int displayNumber = 1; %>
        <c:forEach var="product" items="${products}">
            <tr>
                <td><%= displayNumber++ %></td>
                <td>${product.productName}</td>
                <td>${product.productDescription}</td>
                <td>${product.productColor}</td>
                <td>${product.productSize}</td>
                <td>$${product.productPrice}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/products/edit?id=${product.productId}" class="btn btn-edit">Edit</a>
                    <a href="${pageContext.request.contextPath}/products/delete?id=${product.productId}" class="btn btn-delete">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>