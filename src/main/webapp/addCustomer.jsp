<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="css/form-styles.css">
<%@ include file="header.jsp" %>

<h2>Add Customer</h2>
<form action="customer/add" method="post">
    <input type="hidden" name="action" value="create">
    <label>Account No: <input type="text" name="account_no" required></label><br>
    <label>Name: <input type="text" name="name" required></label><br>
    <label>Address: <input type="text" name="address" required></label><br>
    <label>Phone: <input type="text" name="phone" required></label><br>
    <label>Units: <input type="number" name="units" min="0" required></label><br>
    <button type="submit">Save</button>
</form>

<%@ include file="footer.jsp" %>
