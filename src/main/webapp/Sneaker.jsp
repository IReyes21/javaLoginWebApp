<!DOCTYPE html>
<html>
<head>
    <title>Sneaker Product</title>
</head>
<body>
<h1>Nike Air Force 1</h1>
<img src="https://cms-cdn.thesolesupplier.co.uk/2021/05/nike-air-force-1-mini-swoosh-white-grey-gold-front_w900.jpg" alt="Nike Air Force 1" width="300" height="300">
<p><strong>Description:</strong> Classic sneaker with iconic style and comfortable fit.</p>
<p><strong>Price for 1:</strong> $100.00</p>

<form action="${pageContext.request.contextPath}/SneakerDiscountServlet" method="post">
    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" required min="1">
    <input type="submit" value="Calculate Price">
</form>

<% if (request.getAttribute("error") != null) { %>
<p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>

</body>
</html>