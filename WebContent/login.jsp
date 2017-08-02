<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="yapalQCM/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="yapalQCM/css/yapalcss.css">
</head>
<body>
	<form action="ConnexionUtilisateur" method="POST">
		<div class="form-group">
		<label for="name">Username:</label> 
		<input type="text" name="j_username" id="name" class="form-control">
		</div>
		<div class="form-group">
		<label for="pwd">Password:</label>
		<input type="password" name="j_password" id="pwd" class="form-control"> 
		</div>
		<div class="form-group">
		<input type="checkbox" name="RemenberMe" value="remember-me"> 
		<label for="pwd">Se souvenir de moi</label>
		</div>
		<input type="submit" value="Login" class="btn form-control">
	</form>
</body>
<script src="yapalQCM/js/jquery-3.2.1.min.js"></script>
</html>