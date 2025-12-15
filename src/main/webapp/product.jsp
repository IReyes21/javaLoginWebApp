<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${isEdit ? 'Edit Product' : 'Add New Product'}</title>
    <style>
        .form-container { max-width: 500px; margin: 20px auto; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .btn {
            padding: 8px 15px;
            text-decoration: none;
            border: 1px solid #ccc;
            background-color: #f8f8f8;
            border-radius: 3px;
            color: #333;
            margin-right: 10px;
        }
        .btn-submit { background-color: #4CAF50; color: white; border-color: #45a049; }
        .btn-cancel { background-color: #f44336; color: white; border-color: #d32f2f; }
    </style>
</head>
<body>

<div class="form-container">
    <h1>${isEdit ? 'Edit Product' : 'Add New Product'}</h1>

    <form action="${pageContext.request.contextPath}/products/${isEdit ? 'update' : 'insert'}" method="post">
        <c:if test="${isEdit}">
            <input type="hidden" name="id" value="${product.productId}">
        </c:if>

        <div class="form-group">
            <label for="name">Product Name:</label>
            <input type="text" id="name" name="name"
                   value="${isEdit ? product.productName : ''}" required>
        </div>

        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" id="description" name="description"
                   value="${isEdit ? product.productDescription : ''}">
        </div>

        <div class="form-group">
            <label for="color">Color:</label>
            <input type="text" id="color" name="color"
                   value="${isEdit ? product.productColor : ''}">
        </div>

        <div class="form-group">
            <label for="size">Size:</label>
            <input type="text" id="size" name="size"
                   value="${isEdit ? product.productSize : ''}">
        </div>

        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" step="0.01" min="0"
                   value="${isEdit ? product.productPrice : ''}" required>
        </div>

        <div class="form-group">
            <input type="submit" value="${isEdit ? 'Update' : 'Create'}" class="btn btn-submit">
            <a href="products/list" class="btn btn-cancel">Cancel</a>
        </div>
    </form>
</div>

</body>
</html>