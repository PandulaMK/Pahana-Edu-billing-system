<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body class="login-page">
  <div class="login-card">
    <h2>Pahana Edu Book Shop</h2>
    <c:if test="${not empty error}"><div class="flash error">${error}</div></c:if>
    <form method="post" action="${pageContext.request.contextPath}/login">
      <label>Username
        <input type="text" name="username" required />
      </label>
      <label>Password
        <input type="password" name="password" required />
      </label>
      <button type="submit">Login</button>
    </form>
  </div>
</body>
</html>
