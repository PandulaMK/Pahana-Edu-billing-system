<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.pahanaedu.model.Bill" %>
<%@ include file="header.jsp" %>
<%
  Bill bill = (Bill) request.getAttribute("bill");
%>
<div class="bill-card">
  <h2>Bill</h2>
  <div class="bill-grid">
    <p><b>Bill No:</b> <%= bill != null ? bill.getBillNumber() : "" %></p>
    <p><b>Date:</b> <%= bill != null ? bill.getBillDate() : "" %></p>
    <p><b>Customer ID:</b> <%= bill != null ? bill.getCustomerId() : "" %></p>
    <p><b>Units:</b> <%= bill != null ? bill.getUnitsConsumed() : "" %></p>
    <p><b>Rate/Unit:</b> <%= bill != null ? bill.getRatePerUnit() : "" %></p>
    <p><b>Total:</b> <%= bill != null ? bill.getTotalAmount() : "" %></p>
  </div>
  <div class="form-actions">
    <button class="btn" onclick="window.print()">Print</button>
    <a class="btn" href="${pageContext.request.contextPath}/bills">Back to Bills</a>
  </div>
</div>
<%@ include file="footer.jsp" %>
