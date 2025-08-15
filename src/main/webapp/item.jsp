<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.pahanaedu.model.Item" %>
<%@ include file="header.jsp" %>
<div class="page-header">
  <h2>Items</h2>
  <a href="${pageContext.request.contextPath}/addItem.jsp" class="btn primary">Add Item</a>
</div>
<div class="table-card">
  <table>
    <thead><tr><th>ID</th><th>Name</th><th>Price</th><th>Qty</th><th class="col-actions">Actions</th></tr></thead>
    <tbody>
    <%
      List<Item> items = (List<Item>) request.getAttribute("items");
      if (items != null) for (Item it : items) {
    %>
      <tr>
        <td><%= it.getItemId() %></td>
        <td><%= it.getItemName() %></td>
        <td><%= it.getPrice() %></td>
        <td><%= it.getQuantity() %></td>
        <td class="col-actions">
          <div class="table-actions">
            <a class="btn" href="${pageContext.request.contextPath}/item/edit?id=<%= it.getItemId() %>">Edit</a>
            <a class="btn danger" href="${pageContext.request.contextPath}/item/delete?id=<%= it.getItemId() %>"
               onclick="return confirm('Delete this item?');">Delete</a>
          </div>
        </td>
      </tr>
    <%
      }
    %>
    </tbody>
  </table>
</div>
<%@ include file="footer.jsp" %>
