<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Update Product</title></head>
<body>
<h2>Update Product</h2>


<form method="post" action="${pageContext.request.contextPath}/products/update">
  <input type="hidden" name="id" value="${product.productId}" />

  Name:
  <input type="text" name="name" value="${product.productName}" />

  Description:
  <input type="text" name="description" value="${product.productDescription}" />

  Color:
  <input type="text" name="color" value="${product.productColor}" />

  Size:
  <input type="text" name="size" value="${product.productSize}" />

  Price:
  <input type="number" step="0.01" name="price" value="${product.productPrice}" />

  <button type="submit">Update</button>
</form>

<a href="${pageContext.request.contextPath}/products">Cancel</a>
</body>
</html>