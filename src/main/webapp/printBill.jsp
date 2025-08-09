<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.pahanaedu.model.Bill" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css?v=2">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/printBill.css?v=2">


<%
    Bill bill = (Bill) request.getAttribute("bill");
%>

<html>
<head>
    <title>Bill - <%= bill.getBillNumber() %></title>
    <style>
        body { font-family: Arial, sans-serif; }
        .bill-container { width: 400px; margin: auto; padding: 20px; border: 1px solid #000; }
        h2 { text-align: center; }
        table { width: 100%; border-collapse: collapse; }
        td { padding: 8px; }
        .print-btn { margin-top: 20px; text-align: center; }
        .back-btn { margin-top: 20px; text-align: center; }
    </style>
</head>
<body>
    <div class="bill-container">
        <h2>Bill Receipt</h2>
        <table>
            <tr><td>Bill No:</td><td><%= bill.getBillNumber() %></td></tr>
            <tr><td>Customer ID:</td><td><%= bill.getCustomerId() %></td></tr>
            <tr><td>Units:</td><td><%= bill.getUnitsConsumed() %></td></tr>
            <tr><td>Rate Per Unit:</td><td><%= bill.getRatePerUnit() %></td></tr>
            <tr><td>Total Amount:</td><td><b><%= bill.getTotalAmount() %></b></td></tr>
            <tr><td>Date:</td><td><%= bill.getBillDate() %></td></tr>
        </table>
        <div class="print-btn">
            <button onclick="window.print()">🖨 Print Bill</button>
        </div>
        
        <div class="back-btn">
            <button onclick="window.location.href='<%= request.getContextPath() %>/dashboard.jsp'">🏠 Back to Dashboard</button>
        </div>
    </div>
</body>
</html>
