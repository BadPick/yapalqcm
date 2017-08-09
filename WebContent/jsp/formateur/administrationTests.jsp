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
<c:set var="listeTests" value="${requestScope['listeTests'] }"/>
<c:set var="listeThemes" value="${requestScope['listeThemes'] }"/>
<div class="container">

<h1>Espace Formateur: ${user.nom } ${user.prenom }</h1>
<%@include file="/jsp/formateur/menuFormateur.jsp"%>
<h2>Administration des Tests</h2>
<!-- Liste de tests -->
<ul>
	<c:forEach var="test" items="${listeTests }">
		<li>${test.nom } -- Nombre de questions: ${test.nbQuestions } -- Acquis:${test.seuilAcquis} -- En cours d'acquisition:${test.seuilEnCoursDacquisition}
			<ul>
				<c:forEach var="section" items="${test.sections }">
					<li>${section.theme.nom} -- nbQuestions :${section.nbQuestions}</li>
				</c:forEach>
			</ul>
		</li>
	</c:forEach>
</ul>

<!-- Formulaire -->
<div class="col-md-8" id="formTest" style="display:block">
	<form method="post" action="<%=request.getContextPath()%>/Formateur/Administration/Tests">
		<label for="nom">Nom du test</label><input type="text" id="nom" name="nom" class="form-control"/>
		<button type="button" id="ajouterTheme" class="btn">Ajouter une thème</button>
		<ul id="listeThemes" style="list-style-type:square">
			<li id="theme">
				<select name="theme">
					<c:forEach var="theme" items="${listeThemes}">
						<option value="${theme.id}">${theme.nom }</option>					
					</c:forEach>
				</select>
				<label>Nombre de questions: </label><input type="number" id="nbQuestions" name="nbQuestions"/>
				<button type="button" class="btn btn-danger" id="supprimerTheme">Supprimer</button>
			</li>
		</ul>
		<label for="seuilAcquis">Seuil Acquis</label><input type="number" id="seuilAcquis" name="seuilAcquis" class="form-control"/>
		<label for="seuilEnCourDacquisition">Seuil Acquis</label><input type="number" id="seuilEnCourDacquisition" name="seuilEnCourDacquisition" class="form-control"/>
		<label for="duree">Durée (minutes)</label><input type="number" id="duree" name="duree" class="form-control"/>
		<button type="submit" name="typeAction" value="ajouter" class="form-control">Ajouter</button>
	</form>
</div>


</div>
<script type="text/javascript" src="/yapalQCM/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/bootstrap.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.4.1/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/gestionMessages.js"></script>
<script type="text/javascript">
$('#supprimerTheme').click(function(){
	var liTheme = 'theme-'+$(this).prop('id');
	alert(liTheme);
	$('#'+liTheme).remove();
})
$('#ajouterTheme').click(function(){
	var liTheme=document.createElement("li");
	liTheme.innerHTML
})


</script>

</body>
</html>
