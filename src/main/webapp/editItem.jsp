<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.pahanaedu.model.Item" %>
<%@ include file="header.jsp" %>

<%
    Item item = (Item) request.getAttribute("item");
    if (item == null) {
%>
    <p style="color:red;">Item not found.</p>
<%
        return;
    }
%>

<h2>Edit Item</h2>
<form action="<%= request.getContextPath() %>/item/edit" method="post">
    <input type="hidden" name="id" value="<%= item.getItemId() %>">
    <label>Name: <input type="text" name="item_name" value="<%= item.getItemName() %>" required></label><br>
    <label>Price: <input type="number" step="0.01" name="price" value="<%= item.getPrice() %>" required></label><br>
    <label>Quantity: <input type="number" name="quantity" value="<%= item.getQuantity() %>" required></label><br>
    <button type="submit">Update</button>
</form>

<%@ include file="footer.jsp" %>
