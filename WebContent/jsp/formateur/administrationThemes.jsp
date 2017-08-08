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
<h2>Administration des thèmes</h2>

<c:set var="themes" value="${requestScope['themes'] }" scope="page"/>

<div id="theme" style="display:none">
	<form action="<%=request.getContextPath()%>/Formateur/Administration/Themes" method="post">
		<input type="text" name="nom" id="themeNom"/>
		<input type="submit" class="btn btn-success" name="typeAction" value="ajouter"/>	
	</form>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>Libellé  <button type="button" class="btn btn-success" onclick="document.getElementById('theme').style.display='block';"><span class="glyphicon-plus"></span></button></th>
		</tr>
	</thead>
	<tbody>					
		<c:forEach items="${themes }" var="theme">
			<tr id="theme-${theme.id }">
				<td>
					<form action="<%=request.getContextPath()%>/Formateur/Administration/Themes" method="post">
						<input type="hidden" name="id" value="${theme.id }" id="themeId-${theme.id }"/>
						<input type="text" name="nom" value="${theme.nom }" id="themeNom-${theme.id }"/>
						<button class="btn btn-danger btn-lg" type="submit" name="typeAction" value="supprimer" onclick="return confirmationSupprimer();"><span class="glyphicon glyphicon-remove"></span></button>
						<button class="btn btn-primary btn-lg" type="submit" name="typeAction" value="modifier"><span class="glyphicon glyphicon-pencil"></span></button>
					</form>		
				</td>
			</tr>			
		</c:forEach>
	</tbody>

</table>
<script type="text/javascript">

function confirmationSupprimer(){
	return confirm("Voulez-vous valider la supression?");
}
</script>

</div>
<script type="text/javascript" src="/yapalQCM/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/bootstrap.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.4.1/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/gestionMessages.js"></script>
</body>
</html>
