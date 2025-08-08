<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.pahanaedu.factory.DAOFactory" %>
<%@ page import="com.pahanaedu.dao.ICustomerDAO" %>
<%@ page import="com.pahanaedu.model.Customer" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));

    // ✅ Use DAOFactory to get an ICustomerDAO implementation
    ICustomerDAO customerDAO = DAOFactory.getCustomerDAO();

    // ✅ Call method on the instance
    Customer c = customerDAO.getCustomerById(id);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Customer</title>
</head>
<body>
    <h2>Edit Customer</h2>
    <form action="customer" method="post">
        <input type="hidden" name="id" value="<%= c.getCustomerId() %>">

        <label>Account No:</label>
        <input type="text" name="accountNo" value="<%= c.getAccountNo() %>" required><br><br>

        <label>Name:</label>
        <input type="text" name="name" value="<%= c.getName() %>" required><br><br>

        <label>Address:</label>
        <textarea name="address"><%= c.getAddress() %></textarea><br><br>

        <label>Phone:</label>
        <input type="text" name="phone" value="<%= c.getPhone() %>"><br><br>

        <label>Units:</label>
        <input type="number" name="units" value="<%= c.getUnits() %>"><br><br>

        <input type="submit" name="action" value="Update">
    </form>
</body>
</html>
