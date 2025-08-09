<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.pahanaedu.model.Item" %>
<<<<<<< HEAD
<%@ include file="header.jsp" %>

<h2>Items</h2>
<a href="addItem.jsp" class="btn">Add Item</a>

<table>
    <tr>
        <th>ID</th><th>Name</th><th>Price</th><th>Quantity</th><th>Actions</th>
    </tr>
<%
    List<Item> items = (List<Item>) request.getAttribute("items");
    if (items != null) {
        for (Item i : items) {
%>
    <tr>
=======

<%@ include file="header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/views.css?v=5">

<div class="page-header">
  <h2>Items</h2>
  <a href="${pageContext.request.contextPath}/addItem.jsp" class="btn btn-primary">Add Item</a>
</div>

<div class="table-card">
  <table>
    <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th class="col-actions">Actions</th>
      </tr>
    </thead>
    <tbody>
      <%
        List<Item> items = (List<Item>) request.getAttribute("items");
        if (items != null) {
          for (Item i : items) {
      %>
      <tr>
>>>>>>> origin/css
        <td><%= i.getItemId() %></td>
        <td><%= i.getItemName() %></td>
        <td><%= i.getPrice() %></td>
        <td><%= i.getQuantity() %></td>
<<<<<<< HEAD
        <td>
            <a href="<%= request.getContextPath() %>/item/edit?id=<%= i.getItemId() %>">Edit</a> |
            <a href="<%= request.getContextPath() %>/item/delete?id=<%= i.getItemId() %>"
   onclick="return confirm('Delete this item?');">Delete</a>

        </td>
    </tr>
<%
        }
    }
%>
</table>
=======
        <td class="col-actions">
          <div class="table-actions">
            <a class="btn btn-edit"
               href="<%= request.getContextPath() %>/item/edit?id=<%= i.getItemId() %>">Edit</a>
            <a class="btn btn-delete"
               href="<%= request.getContextPath() %>/item/delete?id=<%= i.getItemId() %>"
               onclick="return confirm('Delete this item?');">Delete</a>
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
>>>>>>> origin/css

<%@ include file="footer.jsp" %>
