<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.pahanaedu.factory.DAOFactory" %>
<%@ page import="com.pahanaedu.dao.IItemDAO" %>
<%@ page import="com.pahanaedu.model.Item" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    
    // Use DAOFactory to get an IItemDAO implementation
    IItemDAO itemDAO = DAOFactory.getItemDAO();
    
    // Call method on the instance
    Item item = itemDAO.getItemById(id);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Item</title>
</head>
<body>
    <h2>Edit Item</h2>
    <form action="item" method="post">
        <input type="hidden" name="id" value="<%= item.getItemId() %>">

        <label>Item Name:</label>
        <input type="text" name="item_name" value="<%= item.getItemName() %>" required><br><br>

        <label>Price:</label>
        <input type="number" name="price" step="0.01" min="0" value="<%= item.getPrice() %>" required><br><br>

        <label>Quantity:</label>
        <input type="number" name="quantity" min="0" value="<%= item.getQuantity() %>" required><br><br>

        <input type="submit" name="action" value="Update">
    </form>
    <br>
    <a href="viewItems.jsp">Back to Item List</a>
</body>
</html>