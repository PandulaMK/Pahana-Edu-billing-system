<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.pahanaedu.model.Bill" %>
<%@ include file="header.jsp" %>

<h2>Bills</h2>


<table>
    <tr>
        <th>Bill No</th><th>Customer ID</th><th>Units</th><th>Rate</th><th>Total</th><th>Date</th>
    </tr>
<%
    List<Bill> bills = (List<Bill>) request.getAttribute("bills");
    if (bills != null) {
        for (Bill b : bills) {
%>
    <tr>
        <td><%= b.getBillNumber() %></td>
        <td><%= b.getCustomerId() %></td>
        <td><%= b.getUnitsConsumed() %></td>
        <td><%= b.getRatePerUnit() %></td>
        <td><%= b.getTotalAmount() %></td>
        <td><%= b.getBillDate() %></td>
    </tr>
<%
        }
    }
%>
</table>

<%@ include file="footer.jsp" %>
