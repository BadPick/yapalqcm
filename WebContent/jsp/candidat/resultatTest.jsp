<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YapalQCM</title>
<link rel="stylesheet" type="text/css" href="/yapalQCM/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/yapalQCM/css/yapalcss.css">
</head>
<body>
<%@include file="/menu.jsp"%>
	<c:set var="message" value="${requestScope['message']}" scope="page" />
	<div id="message" class="hidden">${message.message}</div>
	<div id="messageType" class="hidden">${message.type}</div>
<div class="container">
	<h2>Résultat du test n°<c:out value="${ test.getId() }"/></h2>
	<h3>Candidat : <c:out value="${ sessionScope.user.getPrenom() }"/> <c:out value="${ sessionScope.user.getNom() }"/></h3>
	<p>Score final du test : ${ score }/${ nbreQuestions }</p>
	<p>Temps écoulé : ${ tempsEcoule }</p>
	<p>Résultat : ${ acquisition }</p>
	
</div>
<script type="text/javascript" src="/yapalQCM/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.4.1/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/gestionMessages.js"></script>
</body>
</html>
