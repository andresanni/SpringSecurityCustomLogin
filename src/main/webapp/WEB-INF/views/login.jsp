<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h2>Login</h2>
	
	<form:form action = "${pageContext.request.contextPath}/autenticacionUsuario" method="POST">
	<p>Usuario: <input type="text" name="username"></p>
	<p>Contraseña: <input type="password" name="password"></p>
	<input type="submit" value="Login">
	
	<c:if test="${param.error!=null}">
 	<strong>Credenciales incorrectas</strong>
	</c:if>

	</form:form>
</body>
</html>