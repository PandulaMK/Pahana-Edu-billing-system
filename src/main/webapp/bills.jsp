<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.pahanaedu.model.Bill" %>
<%@ include file="header.jsp" %>
<div class="page-header"><h2>Bills</h2></div>
<div class="table-card">
  <table>
    <thead><tr><th>Bill No</th><th>Date</th><th>Customer ID</th><th>Units</th><th>Rate</th><th>Total</th></tr></thead>
    <tbody>
    <%
      List<Bill> bills = (List<Bill>) request.getAttribute("bills");
      if (bills != null) for (Bill b : bills) {
    %>
      <tr>
        <td><%= b.getBillNumber() %></td>
        <td><%= b.getBillDate() %></td>
        <td><%= b.getCustomerId() %></td>
        <td><%= b.getUnitsConsumed() %></td>
        <td><%= b.getRatePerUnit() %></td>
        <td><%= b.getTotalAmount() %></td>
      </tr>
    <%
      }
    %>
    </tbody>
  </table>
</div>
<%@ include file="footer.jsp" %>
