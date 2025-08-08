<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.pahanaedu.model.Customer" %>
<%@ include file="header.jsp" %>

<h2>Customers</h2>
<a href="addCustomer.jsp" class="btn">Add Customer</a>

<table>
    <tr>
        <th>ID</th><th>Account No</th><th>Name</th><th>Address</th><th>Phone</th><th>Units</th><th>Actions</th>
    </tr>
<%
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    if (customers != null) {
        for (Customer c : customers) {
%>
    <tr>
        <td><%= c.getCustomerId() %></td>
        <td><%= c.getAccountNo() %></td>
        <td><%= c.getName() %></td>
        <td><%= c.getAddress() %></td>
        <td><%= c.getPhone() %></td>
        <td><%= c.getUnits() %></td>
        <td>
    <a href="<%= request.getContextPath() %>/customer/edit?customer_id=<%= c.getCustomerId() %>">Edit</a> |
    <a href="<%= request.getContextPath() %>/customer/delete?customer_id=<%= c.getCustomerId() %>"
       onclick="return confirm('Are you sure you want to delete this customer?');">Delete</a> |
    <a href="<%= request.getContextPath() %>/bills/generate?customer_id=<%= c.getCustomerId() %>"
       onclick="return confirm('Generate bill for this customer?');">Generate Bill</a>
</td>

    </tr>
<%
        }
    }
%>
</table>

<%@ include file="footer.jsp" %>
