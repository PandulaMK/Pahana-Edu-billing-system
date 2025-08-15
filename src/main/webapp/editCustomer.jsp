<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.pahanaedu.model.Customer" %>
<%@ include file="header.jsp" %>
<%
  Customer c = (Customer) request.getAttribute("customer");
%>
<h2>Edit Customer</h2>
<form class="form-card" action="${pageContext.request.contextPath}/customer/edit" method="post">
  <input type="hidden" name="customer_id" value="<%= c != null ? c.getCustomerId() : 0 %>"/>
  <label>Account No<input type="text" name="account_no" value="<%= c != null ? c.getAccountNo() : "" %>" required></label>
  <label>Name<input type="text" name="name" value="<%= c != null ? c.getName() : "" %>" required></label>
  <label>Address<input type="text" name="address" value="<%= c != null ? c.getAddress() : "" %>" required></label>
  <label>Phone<input type="text" name="phone" value="<%= c != null ? c.getPhone() : "" %>" required></label>
  <label>Units<input type="number" name="units" min="0" value="<%= c != null ? c.getUnits() : 0 %>" required></label>
  <div class="form-actions">
    <button class="btn primary" type="submit">Update</button>
    <a class="btn" href="${pageContext.request.contextPath}/customer">Cancel</a>
  </div>
</form>
<%@ include file="footer.jsp" %>
