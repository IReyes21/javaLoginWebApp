<!DOCTYPE html>
<html>
<head>
    <title>Sneaker Order Result</title>
</head>
<body>
<h1>Order Summary</h1>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color: red;"><%= error %></p>
<a href="Sneaker.jsp">Go Back</a>
<%
} else {
    Integer quantity = (Integer) request.getAttribute("quantity");
    Double price = (Double) request.getAttribute("price");
    Double discount = (Double) request.getAttribute("discount");
    String finalTotal = (String) request.getAttribute("finalTotal");
%>
<p><strong>Sneaker:</strong> Nike Air Force 1</p>
<p><strong>Quantity:</strong> <%= quantity %></p>
<p><strong>Price per item:</strong> $<%= String.format("%.2f", price) %></p>
<p><strong>Discount:</strong> <%= discount != null ? discount + "%" : "0%" %></p>
<p><strong>Final Total:</strong> $<%= finalTotal %></p>

<a href="Sneaker.jsp">Place Another Order</a>
<% } %>
</body>
</html>