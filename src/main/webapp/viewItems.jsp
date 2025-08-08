<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Item" %>
<%
    List<Item> items = (List<Item>) request.getAttribute("items");
    if (items == null) {
        response.sendRedirect("item");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Item List</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Item Inventory</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Item Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <%
            for (Item item : items) {
        %>
        <tr>
            <td><%= item.getItemId() %></td>
            <td><%= item.getItemName() %></td>
            <td><%= item.getPrice() %></td>
            <td><%= item.getQuantity() %></td>
            <td><a href="editItem.jsp?id=<%= item.getItemId() %>">Edit</a></td>
            <td><a href="item?action=delete&id=<%= item.getItemId() %>" onclick="return confirm('Are you sure you want to delete this item?')">Delete</a></td>
        </tr>
        <%
            }
        %>
    </table>
    <br>
    <a href="addItem.jsp">➕ Add New Item</a>
</body>
</html>