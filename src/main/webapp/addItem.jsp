<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<h2>Add Item</h2>
<form class="form-card" action="${pageContext.request.contextPath}/item/add" method="post">
  <label>Name<input type="text" name="item_name" required></label>
  <label>Price<input type="number" step="0.01" name="price" required></label>
  <label>Quantity<input type="number" name="quantity" min="0" required></label>
  <div class="form-actions">
    <button class="btn primary" type="submit">Save</button>
    <a class="btn" href="${pageContext.request.contextPath}/item">Cancel</a>
  </div>
</form>
<%@ include file="footer.jsp" %>
