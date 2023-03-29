<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Nunca, nunca, nunca, te rindas</h1>
	<br>
	Usuario:
	<sec:authentication property="principal.username"/>
	<br>
	Rol:
	<sec:authentication property="principal.authorities"/>
	<br>
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	<input type="submit" value="Logout">
	</form:form>
	
	<sec:authorize access="hasRole('ADMINISTRADOR')">
	<a href="<c:url value="/administradores" />">Zona de administradores</a>
	</sec:authorize>
	
	<sec:authorize access="hasRole('USUARIO')">
	<a href="<c:url value="/usuarios" />">Zona de usuarios comunes</a>
	</sec:authorize>
</body>
</html>