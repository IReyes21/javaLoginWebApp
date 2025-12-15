<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Customer Page</title>
</head>
<body>
<h1>Customer List</h1>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Favorite Meal</th>
    </tr>

    <c:forEach var="cust" items="${customers}">
        <tr>
            <td>${cust.ID}</td>
            <td>${cust.firstName}</td>
            <td>${cust.lastName}</td>
            <td>${cust.favoriteMeal}</td>
        </tr>
    </c:forEach>
</table>

<hr/>

<h2>Add New Customer</h2>
<form action="${pageContext.request.contextPath}/customers" method="post">
    <label>First Name:
        <input type="text" name="firstName" />
    </label><br/><br/>

    <label>Last Name:
        <input type="text" name="lastName" />
    </label><br/><br/>

    <label>Favorite Meal:
        <input type="text" name="favoriteMeal" />
    </label><br/><br/>

    <input type="submit" value="Save Customer" />
</form>

</body>
</html>
