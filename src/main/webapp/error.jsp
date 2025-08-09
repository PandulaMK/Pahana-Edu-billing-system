<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/error.css">

    <title>Error</title>
</head>
<body>
    <h2>Error Occurred</h2>
    <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
    <a href="<%= request.getContextPath() %>/customer">Back to Customers</a>
</body>
</html>
