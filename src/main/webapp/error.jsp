<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- error.jsp -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h2>Error Occurred</h2>
    <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
    <a href="viewCustomers.jsp">Back to Customers</a>
</body>
</html>
