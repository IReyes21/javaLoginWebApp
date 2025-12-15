<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Delete Product</title></head>
<body>
<h2>Delete Product</h2>


<p>Are you sure you want to delete
  <strong>${product.productName}</strong>?
</p>



<form method="post" action="${pageContext.request.contextPath}/products/delete">
  <input type="hidden" name="id" value="${product.productId}" />
  <button type="submit">Yes, Delete</button>
</form>


<a href="${pageContext.request.contextPath}/products">Cancel</a>
</body>
</html>