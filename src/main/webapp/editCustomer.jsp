<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.pahanaedu.model.Customer" %>
<%@ include file="header.jsp" %>

<%
    Customer customer = (Customer) request.getAttribute("customer");
    if (customer == null) {
%>
    <p style="color:red;">Customer not found.</p>
<%
        return;
    }
%>

<h2>Edit Customer</h2>
<form action="<%= request.getContextPath() %>/customer/edit" method="post">
    <input type="hidden" name="customer_id" value="<%= customer.getCustomerId() %>">
    <label>Account No: <input type="text" name="account_no" value="<%= customer.getAccountNo() %>" required></label><br>
    <label>Name: <input type="text" name="name" value="<%= customer.getName() %>" required></label><br>
    <label>Address: <input type="text" name="address" value="<%= customer.getAddress() %>" required></label><br>
    <label>Phone: <input type="text" name="phone" value="<%= customer.getPhone() %>" required></label><br>
    <label>Units: <input type="number" name="units" min="0" value="<%= customer.getUnits() %>" required></label><br>
    <button type="submit">Update</button>
</form>

<%@ include file="footer.jsp" %>
