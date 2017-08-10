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
<div class="col-md-8" id="listeTest" style="display:block">
	<table class="table table-striped table-hover">
		<tr>
			<th colspan="3">Tests : </th>
			<th><button type="button" id="afficherForm" class="btn btn-default" onclick="hideShowLine()">Ajouter un thème</button></th>
			<th></th>
		</tr>
		<c:forEach var="test" items="${listeTests }">
			<tr id="ligneTest-${test.id }">
				<td id="nom-${test.id }">${test.nom }</td>
				<td id="seuilAcquis-${test.id }">Acquis:${test.seuilAcquis}</td>
				<td id="seuilEnCourDacquisition-${test.id }">En cours d'acquisition:${test.seuilEnCoursDacquisition}</td>
				<td>
					<form method="post" action="<%=request.getContextPath()%>/Formateur/Administration/Tests">
						<input type="hidden" name="idTest" value="${test.id }"/>
						<button type="submit" name="typeAction" value="supprimer" class="btn btn-danger">Supprimer</button>
					</form>
				</td>
				<td><button id="modifier-${test.id}" type="button" class="btn btn-primary">Modifier</button></td>
			</tr>		
			<c:forEach var="section" items="${test.sections }">
				<tr name="detail-${test.id }" style="display:none">
					<td></td>
					<td id="nomTheme-${section.theme.id }">${section.theme.nom}</td>
					<td id="nomTheme-${section.theme.id }">nbQuestions :${section.nbQuestions}</td>
					<td></td>
					<td></td>
				</tr>
			</c:forEach>	
		</c:forEach>
	</table>
</div>



<!-- Formulaire -->
<div class="col-md-8" id="formTest" style="display:none">
	<button id="masquerForm" type="button" class="btn btn-success" onclick="hideShowLine()">Retour</button>
	<form method="post" action="<%=request.getContextPath()%>/Formateur/Administration/Tests" onsubmit="return validationFormualire();">
		<input type="hidden" id="idTest" name="idTest"/>
		<label for="nom">Nom du test</label><input type="text" id="nom" name="nom" class="form-control" required/>
		<button type="button" id="ajouterTheme" class="btn">Ajouter un thème</button>
		<ul id="listeThemes" style="list-style-type:square">
			
		</ul>
		<label for="seuilAcquis">Seuil Acquis</label><input type="number" id="seuilAcquis" name="seuilAcquis" class="form-control" requeried/>
		<label for="seuilEnCourDacquisition">Seuil en cours d'acquisition</label><input type="number" id="seuilEnCourDacquisition" name="seuilEnCourDacquisition" class="form-control" requeried/>
		<label for="duree">Durée (minutes)</label><input type="number" id="duree" name="duree" class="form-control" requeried/>
		<button type="submit" name="typeAction" value="ajouter" class="form-control">Ajouter</button>
	</form>
</div>


</div>
<script type="text/javascript" src="/yapalQCM/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/bootstrap.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.4.1/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/gestionMessages.js"></script>
<script type="text/javascript">
/************************************************************/
/******* Bloc D'ajout de thème dans le formualire ***********/
/************************************************************/
var numSection = 0;
$('#ajouterTheme').click(function(){
	nouvelleSection();						
})
function nouvelleSection(){
	var idtheme = "'#theme-"+numSection+"'";
	var theme=document.createElement("li");
	theme.setAttribute("id", "theme-"+numSection);
	theme.innerHTML='<select name="themes">'+
						'<c:forEach var="theme" items="${listeThemes}">'+
							'<option value="${theme.id}">${theme.nom }</option>'+					
						'</c:forEach>'+
					'</select>'+
					'<label>Nombre de questions: </label><input type="number" id="nbQuestions" name="nbQuestions"/>'+
					'<button type="button" id="supprimerTheme-'+numSection+'" class="btn btn-danger" onclick="$('+idtheme+').remove()">Supprimer</button>'
	$('#listeThemes').append(theme);
	numSection++;
}
/************************************************************/
/******* Bascule pour affichage du formulaire ***************/
/************************************************************/
$('#afficherForm').click(function(){
	afficherForm();
})
$('#masquerForm').click(function(){
	masquerForm();
})
function masquerForm(){
	if($('#formTest').is(":visible")){
		$('#formTest').hide("slow");		
		$('#listeTest').show("slow");
	}
}
function afficherForm(){
	if($('#listeTest').is(":visible")){
		$('#listeTest').hide("slow");
		$('#formTest').show("slow");		
	}
}
/************************************************************/
/********* Boutton pour suppression d'un test ***************/
/************************************************************/
$("tr[id^='ligneTest-']").click(function(){
	var detail = $(this).prop('id').replace('ligneTest-','detail-');
	if($("tr[name="+detail+"]").is(":hidden")){		
		$("tr[name="+detail+"]").each(function(){
			$(this).show("slow");
		})
	}
	else{
		$("tr[name="+detail+"]").each(function(){
			$(this).hide();
		})
	}
})
/************************************************************/
/********* Boutton pour suppression d'un test ***************/
/************************************************************/
$("button[id^='modifier-']").click(function(){
	var detail = $(this).prop('id').replace
})
 
 
/************************************************************/
/********* Validation de la saisie du formulaire ************/
/************************************************************/
function validationFormualire(){
	var message="";
	var nom=$('#nom');
	var seuilAcquis=$('#seuilAcquis');
	var seuilEnCourDacquisition=$('#seuilEnCourDacquisition');
	var duree=$('#duree');
	var listeNbQuestion=$("#nbQuestions");
	var flag=true;
	if(nom.prop('value')==''){
		flag=false;
		message="Merci de renseigner un nom.";
	}
	if(seuilAcquis.prop('value')==''){
		flag=false;
		message="Merci de renseigner un seuil d'acquisition.";
	}
	if(seuilEnCourDacquisition.prop('value')==''){
		flag=false;
		message="Merci de renseigner un seuil d'en cours d'acquisition.";
	}
	if(duree.prop('value')==''){
		flag=false;
		message="Merci de renseigner une durée pour le test.";
	}
	listeNbQuestion.each(function(){
		if($(this).prop('value')==''){
			flag=false;
			message="Merci de renseigner un nombre pour chaque thème.";
		}
	})
	if(message!=""){
		notyMessage(message, 'error');
	}
	return flag;
}
</script>

</body>
</html>
