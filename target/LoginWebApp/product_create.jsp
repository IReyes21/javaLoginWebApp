<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Create Product</title></head>
<body>
<h2>Create Product</h2>


<form method="post"
      action="${pageContext.request.contextPath}/products/insert">

  Name:
  <input type="text" name="name" required /><br><br>

  Description:
  <input type="text" name="description" /><br><br>

  Color:
  <input type="text" name="color" /><br><br>

  Size:
  <input type="text" name="size" /><br><br>

  Price:
  <input type="number" step="0.01" name="price" required /><br><br>

  <button type="submit">Create</button>
</form>


<a href="${pageContext.request.contextPath}/products">Cancel</a>
</body>
</html>