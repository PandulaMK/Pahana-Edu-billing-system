<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<h2>Admin Dashboard</h2>
<form class="form-card" method="post" action="${pageContext.request.contextPath}/admin/cashier/add">
  <label>Cashier Username<input type="text" name="username" required></label>
  <label>Temporary Password<input type="password" name="password" required></label>
  <div class="form-actions">
    <button class="btn primary" type="submit">Create Cashier</button>
  </div>
</form>
<%@ include file="../footer.jsp" %>
