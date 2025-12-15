<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Product List</title>
  <style>
    table {
      border-collapse: collapse;
      margin-top: 15px;
    }
    th, td {
      padding: 8px 12px;
      border: 1px solid black;
      text-align: left;
    }
    th {
      background-color: #f2f2f2;
    }
    .actions a {
      margin-right: 10px;
      text-decoration: none;
    }
  </style>
</head>
<body>

<h2>Product List</h2>

<a href="${pageContext.request.contextPath}/products/new">
  Create New Product
</a>

<table>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
    <th>Actions</th>
  </tr>

  <c:forEach var="product" items="${products}">
    <tr>
      <td>${product.productId}</td>
      <td>${product.productName}</td>
      <td>${product.productPrice}</td>
      <td class="actions">
        <a href="${pageContext.request.contextPath}/products/edit?id=${product.productId}">
          Edit
        </a>
        <a href="${pageContext.request.contextPath}/products/delete?id=${product.productId}">
          Delete
        </a>
      </td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
