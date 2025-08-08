<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>

<h2>Add Item</h2>
<form action="item/add" method="post">
    <input type="hidden" name="action" value="create">
    <label>Name: <input type="text" name="item_name" required></label><br>
    <label>Price: <input type="number" name="price" step="0.01" required></label><br>
    <label>Quantity: <input type="number" name="quantity" min="0" required></label><br>
    <button type="submit">Save</button>
</form>

<%@ include file="footer.jsp" %>
