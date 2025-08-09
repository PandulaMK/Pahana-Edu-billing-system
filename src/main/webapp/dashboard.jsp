<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="css/dashboard.css">
<link rel="stylesheet" href="css/layout.css">
<%@ include file="header.jsp" %>

<h2>Dashboard</h2>
<p>Welcome, <%= session.getAttribute("user") %>!</p>

<div class="cards">
    <div class="card"><a href="customer">Manage Customers</a></div>
    <div class="card"><a href="item">Manage Items</a></div>
    <div class="card"><a href="bills">View Bills</a></div>
</div>

<%@ include file="footer.jsp" %>
