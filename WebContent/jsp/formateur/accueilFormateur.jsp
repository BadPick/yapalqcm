<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/yapalQCM/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/yapalQCM/css/yapalcss.css">

</head>
<body>
<%@include file="/menu.jsp"%>
<c:set var="message" value="${requestScope['message']}" scope="page" />
<c:set var="user" value="${sessionScope['user'] }" scope="page"/>
<div id="message" class="hidden">${message.message}</div>
<div id="messageType" class="hidden">${message.type}</div>
<div class="container">

<h1>Espace Formateur: ${user.nom } ${user.prenom }</h1>


<div class="dropdown">
	<button class="btn btn-default dropdown-toggle" type="button" id="menuCreation" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    Création
    <span class="caret"></span>
  </button>
	<ul class="dropdown-menu" aria-labelledby="menuCreation">
		<li><a href="#">Administration / recherche des Sessions</a></li>
		<li><a href="#">Administration / recherche des Tests</a></li>
		<li><a href="#">Administration / recherche des Thèmes</a></li>
		<li><a href="#">Administration / recherche des Questions/réponses</a></li>
		<li><a href="#">Administration / recherche des Utilisateur</a></li>
	</ul>
</div>
<div class="dropdown">
	<button class="btn btn-default dropdown-toggle" type="button" id="menuInscription" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    Inscription
    <span class="caret"></span>
  </button>
<ul class="dropdown-menu" aria-labelledby="menuInscription">
	<li><a href="#">Inscription d'un candidat</a></li>
	<li><a href="#">Consultation des inscriptions</a></li>
</ul>
</div>
<div class="dropdown">
	<button class="btn btn-default dropdown-toggle" type="button" id="menuResultat" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    Inscription
    <span class="caret"></span>
  </button>
<ul class="dropdown-menu" aria-labelledby="menuResultat">
	<li><a href="#">Consultation des résultats</a></li>
</ul>
	
</div>
<script type="text/javascript" src="/yapalQCM/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/bootstrap.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.4.1/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/gestionMessages.js"></script>
</body>
</html>
