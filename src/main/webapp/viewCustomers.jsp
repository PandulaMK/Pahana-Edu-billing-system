<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Customer" %>
<%
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    if (customers == null) {
        response.sendRedirect("customer");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
    <h2>Customer Accounts</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Account No</th>
            <th>Name</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Units</th>
            <th>Edit</th>
			<th>Delete</th>
            
        </tr>
        <%
            for (Customer c : customers) {
        %>
        <tr>
            <td><%= c.getCustomerId() %></td>
            <td><%= c.getAccountNo() %></td>
            <td><%= c.getName() %></td>
            <td><%= c.getAddress() %></td>
            <td><%= c.getPhone() %></td>
            <td><%= c.getUnits() %></td>
            <td><a href="editCustomer.jsp?id=<%= c.getCustomerId() %>">Edit</a></td>
			<td><a href="customer?action=delete&id=<%= c.getCustomerId() %>" onclick="return confirm('Are you sure?')">Delete</a></td>
            
        </tr>
        <%
            }
        %>
    </table><br>
    <a href="addCustomer.jsp">➕ Add New Customer</a>
    
</body>
</html>
