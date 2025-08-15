<%@ include file="header.jsp" %>
<section class="grid">
  <a class="card" href="${pageContext.request.contextPath}/customer">
    <h3>Customers</h3><p>Manage customer accounts</p>
  </a>
  <a class="card" href="${pageContext.request.contextPath}/item">
    <h3>Items</h3><p>Manage items</p>
  </a>
  <a class="card" href="${pageContext.request.contextPath}/bills">
    <h3>Bills</h3><p>View generated bills</p>
  </a>
  <c:if test="${sessionScope.role == 'ADMIN'}">
    <a class="card" href="${pageContext.request.contextPath}/admin">
      <h3>Admin</h3><p>Add cashier users</p>
    </a>
  </c:if>
</section>
<%@ include file="footer.jsp" %>
