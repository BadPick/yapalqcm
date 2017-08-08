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
<%@include file="/jsp/formateur/menuFormateur.jsp"%>
<h2>Administration des Sessions</h2>
<c:set var="listeSessions" value="${requestScope['listeSessions'] }"/>
<c:set var="listeTests" value="${requestScope['listeTests'] }"/>

<!-- Bloc pour affichage de la liste des sessions -->
	<div class="col-md-8" id="liste_session" style="display:block">
		<button type="button" class="btn btn-success" onclick="afficherNouvelleSession()">Nouvelle Session</button>
		<ul>
			<c:forEach var="session" items="${listeSessions }">
				<li>${session.date } - ${session.nbPlaces } places disponibles.	
				<div style="display:none">
					<form id="formSession-${session.id }" method="post" action="<%=request.getContextPath()%>/Formateur/Administration/Sessions">
						<input type="hidden" name="idSession" value="${session.id }"/>
					</form>
				</div>	
				<button form="formSession-${session.id }" type="submit" class="btn btn-error" name="typeAction" value="supprimer">Supprimer</button>
				<button type="button" class="btn btn-primary" onclick="modifierSession(${session.id})">Modifier</button>
					<c:forEach var="test" items="${session.tests }">
						<ul>
							<li>Test: ${test.nom } - Dur�e: ${test.duree }</li>	
						</ul>
					</c:forEach>	
					<div id="idObject-${session.id }" style="display:none">
						<input type="hidden" id="idSession" value="${session.id }"/>
						<input type="hidden" id="date" value="${session.date }"/>
						<input type="hidden" id="nbPlaces" value="${session.nbPlaces }"/>
						<c:forEach var="test" items="${session.tests }">
							<input type="hidden" id="idTest" value="${test.id }"/> 	
						</c:forEach>
					</div>		
				</li>				
			</c:forEach>
		</ul>
	</div>
	
	<!-- Bloc pour �dition d'un session -->
	<div class="col-md-8" id="nouvelle_session" style="display:none">
	<button type="button" class="btn btn-success" onclick="afficherListeSessions()">Retour</button>
		<h2>Nouvelle Session:</h2>
		<form id="formNvSession" method="post" action="<%=request.getContextPath()%>/Formateur/Administration/Sessions">
			<div class="form-group col-md-8">
				<input type="hidden" name="idSession" id="idSession"/>
				<label for="date">Date de la Session: </label><input type="date" name="date" id="date" class="form-control" value=""/>
				<label for="heure">Heure de passage du test</label><input type="time" name="heure" id="heure" class="form-control" value=""/>
				<label for="nbPlaces">Nombre de places: </label><input type="number" min="5" max="30" name="nbPlaces" id="nbPlaces" class="form-control" value=""/>
				<fieldset>
				<legend>S�lection du test de la session:</legend>
					<c:forEach items="${listeTests }" var="test">
						<input type="checkbox" name="idTest" value="${test.id }" id="idTest-${test.id }"/>${test.nom }
					</c:forEach>
				</fieldset>
				<button type="submit" id="formNvMod" name="typeAction" value="ajouter" class="form-control">Nouvelle Session</button>
			</div>
		</form>
	</div>

</div>
<script type="text/javascript" src="/yapalQCM/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/bootstrap.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.4.1/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/gestionMessages.js"></script>

<script type="text/javascript">
function afficherNouvelleSession(){	
	$('#liste_session').hide();
	$('#nouvelle_session').show();	
}
function afficherListeSessions(){
	$('#liste_session').show();
	$('#nouvelle_session').hide();
}
</script>
</body>
</html>
