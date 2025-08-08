<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="false" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Item</title>
    <style>
        .error { color: red; }
    </style>
</head>
<body>
    <h2>Add New Item</h2>
    <% if (request.getAttribute("errorMessage") != null) { %>
        <p class="error"><%= request.getAttribute("errorMessage") %></p>
    <% } %>
    <form action="item" method="post">
        <label>Item Name:</label>
        <input type="text" name="item_name" required><br><br>

        <label>Price:</label>
        <input type="number" name="price" step="0.01" min="0" required><br><br>

        <label>Quantity:</label>
        <input type="number" name="quantity" min="0" value="0" required><br><br>

        <input type="submit" value="Add Item">
    </form>
    <br>
    <a href="viewItems.jsp">View All Items</a>
</body>
</html>