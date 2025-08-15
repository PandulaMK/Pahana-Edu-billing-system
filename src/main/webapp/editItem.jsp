<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.pahanaedu.model.Item" %>
<%@ include file="header.jsp" %>
<%
  Item it = (Item) request.getAttribute("item");
%>
<h2>Edit Item</h2>
<form class="form-card" action="${pageContext.request.contextPath}/item/edit" method="post">
  <input type="hidden" name="id" value="<%= it != null ? it.getItemId() : 0 %>"/>
  <label>Name<input type="text" name="item_name" value="<%= it != null ? it.getItemName() : "" %>" required></label>
  <label>Price<input type="number" step="0.01" name="price" value="<%= it != null ? it.getPrice() : "" %>" required></label>
  <label>Quantity<input type="number" name="quantity" min="0" value="<%= it != null ? it.getQuantity() : 0 %>" required></label>
  <div class="form-actions">
    <button class="btn primary" type="submit">Update</button>
    <a class="btn" href="${pageContext.request.contextPath}/item">Cancel</a>
  </div>
</form>
<%@ include file="footer.jsp" %>
