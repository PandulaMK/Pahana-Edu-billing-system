<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pahanaedu.model.User" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= user.getUsername() %>!</h2>
    <p>Role: <%= user.getRole() %></p>
    <a href="logout">Logout</a><br>
    <a href="addCustomer.jsp">➕ Add Customer</a><br>
	<a href="customer">📋 View Customers</a><br>
	<a href="addItem.jsp">➕ Add Item</a><br>
	<a href="item">📋 View Items</a><br>

</body>
</html>
