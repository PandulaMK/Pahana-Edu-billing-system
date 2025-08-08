<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.pahanaedu.model.Item" %>
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
        <td><%= i.getItemId() %></td>
        <td><%= i.getItemName() %></td>
        <td><%= i.getPrice() %></td>
        <td><%= i.getQuantity() %></td>
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

<%@ include file="footer.jsp" %>
