<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.pahanaedu.model.Customer" %>

<%@ include file="header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/views.css?v=4">

<div class="page-header">
  <h2>Customers</h2>
  <a href="${pageContext.request.contextPath}/addCustomer.jsp" class="btn btn-primary">Add Customer</a>
</div>

<div class="table-card">
  <table>
    <thead>
      <tr>
        <th>ID</th>
        <th>Account No</th>
        <th>Name</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Units</th>
        <th class="col-actions">Actions</th>
      </tr>
    </thead>
    <tbody>
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
        <td class="col-actions">
          <div class="table-actions">
            <a class="btn btn-edit"
               href="<%= request.getContextPath() %>/customer/edit?customer_id=<%= c.getCustomerId() %>">Edit</a>
            <a class="btn btn-delete"
               href="<%= request.getContextPath() %>/customer/delete?customer_id=<%= c.getCustomerId() %>"
               onclick="return confirm('Are you sure you want to delete this customer?');">Delete</a>
            <a class="btn btn-outline"
               href="<%= request.getContextPath() %>/bills/generate?customer_id=<%= c.getCustomerId() %>"
               onclick="return confirm('Generate bill for this customer?');">Generate Bill</a>
          </div>
        </td>
      </tr>
      <%
          }
        }
      %>
    </tbody>
  </table>
</div>

<%@ include file="footer.jsp" %>
