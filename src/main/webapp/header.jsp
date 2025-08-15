<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Pahana Billing</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<header class="topbar">
  <div class="brand">
    <a href="${pageContext.request.contextPath}/dashboard.jsp">Pahana<span>Billing</span></a>
  </div>
  <nav class="nav">
    <a href="${pageContext.request.contextPath}/customer">Customers</a>
    <a href="${pageContext.request.contextPath}/item">Items</a>
    <a href="${pageContext.request.contextPath}/bills">Bills</a>
    <c:if test="${sessionScope.role == 'ADMIN'}">
      <a href="${pageContext.request.contextPath}/admin">Admin</a>
    </c:if>
    <a class="logout" href="${pageContext.request.contextPath}/login.jsp">Logout</a>
  </nav>
</header>
<main class="container">
  <c:if test="${not empty sessionScope.flash}">
    <div class="flash success">${sessionScope.flash}</div>
    <c:remove var="flash" scope="session"/>
  </c:if>
  <c:if test="${not empty error}">
    <div class="flash error">${error}</div>
  </c:if>
