<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<h2>Add Customer</h2>
<form class="form-card" action="${pageContext.request.contextPath}/customer/add" method="post">
  <label>Account No<input type="text" name="account_no" required></label>
  <label>Name<input type="text" name="name" required></label>
  <label>Address<input type="text" name="address" required></label>
  <label>Phone<input type="text" name="phone" required></label>
  <label>Units<input type="number" name="units" min="0" required></label>
  <div class="form-actions">
    <button class="btn primary" type="submit">Save</button>
    <a class="btn" href="${pageContext.request.contextPath}/customer">Cancel</a>
  </div>
</form>
<%@ include file="footer.jsp" %>
