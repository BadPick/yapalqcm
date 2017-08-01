<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/yapalcss.css">
</head>
<body>
	<form action="j_security_check" method="POST">
	<div class="form-group">
		<label for="name">Username:</label> 
		<input type="text" name="j_username" id="name" class="form-control">
		</div>
		<div class="form-group">
		<label for="pwd">Password:</label>
		<input type="password" name="j_password" id="pwd" class="form-control"> 
		</div>
		<input type="submit" value="Login" class="btn form-control">
	</form>
</body>
<script src="./js/jquery-3.2.1.min.js"></script>
</html>