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

<div class="containeur">

<h1>Espace Formateur: ${user.nom } ${user.prenom }</h1>
<%@include file="/jsp/formateur/menuFormateur.jsp"%>
<h2>Administration des thèmes</h2>

<c:set var="themes" value="${requestScope['themes'] }" scope="page"/>
<c:set var="index" value="1" scope="page"/>


<table class="table table-striped">
	<thead>
		<tr>
			<th>Libellé</th>
			<th> <button type="button" class="btn btn-default btn-standard" onclick="hideShowLine()">Ajouter un thème</button></th>
			<th></th>
		</tr>
	</thead>
	<tbody>		
		<tr id="theme">		
			<form action="<%=request.getContextPath()%>/Formateur/Administration/Themes" method="post">
				<td><input type="text" name="nom" id="themeNom" class="subtilInput" required/></td>
				<td><button type="submit" class="btn btn-default btn-standard" name="typeAction" value="ajouter">Valider</button></td>
			</form>
			<td><button type="button" class="btn btn-default btn-standard" name="typeAction" value="annuler" onclick="hideShowLine()">Annuler</button></td>		
		</tr>			
		<c:forEach items="${themes }" var="theme">
			<tr id="theme-${theme.id }">
					<form action="<%=request.getContextPath()%>/Formateur/Administration/Themes" method="post">
						<input type="hidden" name="id" value="${theme.id }" id="themeId-${theme.id }"/>
						<td><input type="text" name="nom" value="${theme.nom }" id="themeNom-${theme.id }" class="input-${index} subtilInput"/></td>
						<td><button class="btn btn-default btn-standard" type="submit" name="typeAction" value="supprimer" onclick="return confirmationSupprimer();">Supprimer</button></td>
						<td><button class="save-${index} btn btn-default btn-standard" type="submit" name="typeAction" value="modifier">Sauvegarder</button></td>
					</form>		
			</tr>
			<c:set var="index" value="${index + 1}" scope="page"/>		
		</c:forEach>
	</tbody>

</table>


</div>
<script type="text/javascript" src="/yapalQCM/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/bootstrap.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.4.1/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/gestionMessages.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
	$("button[class^='save-']").each(function(){
		$(this).hide();
	})
	hideShowLine();
});
$("input[class^='input-']").on("change paste keyup", function(){
	var inputClass = $(this).attr('class').split(" ")[0];
	var btnClass = inputClass.replace('input-','save-');
	$('.'+btnClass).show();
})
function confirmationSupprimer(){
	return alert("Voulez-vous valider la supression?");
	//notyDialogConfirm("Voulez-vous valider la supression?",valideSuppr,null);
}

function valideSuppr(){
	
}

function hideShowLine(){
	if($('#theme').is(":visible")) {
		$('#theme').hide()
	}else{
		$('#theme').show();
	}
}
</script>
</body>
</html>
