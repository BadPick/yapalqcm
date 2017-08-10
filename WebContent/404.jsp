<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>erreur 404</title>
<link rel="stylesheet" type="text/css" href="/yapalQCM/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/yapalQCM/css/yapalcss.css">
</head>
<body>
<%@include file="/menu.jsp"%>
<c:set var="message" value="${requestScope['message']}" scope="page" />
<div id="message" class="hidden">${message.message}</div>
<div id="messageType" class="hidden">${message.type}</div>
<div class="containeur">
	<div style="margin:200px auto 0px auto">
        <p id="compteur" style="font-size:50px; text-align: center">Erreur</p>
        <p id="description" style="margin:50px auto 50px auto; font-size:30px; text-align: center"></p>
        <form action="<%=request.getContextPath()%>/index.jsp" method="post">
        	<button href="<%=request.getContextPath()%>/Candidat/Accueil" type="submit" class="btn btn-standard-reverse" id="retour" style="margin: auto; display: block; visibility:hidden">Page d'accueil</button>
   		</form>
    </div>
</div>
    <script>
    var compteurElt = document.getElementById("compteur");
    var compteur = 0;

    function diminuerCompteur() {

        compteurElt.textContent = "Erreur ";

        // Augmente le compteur jusqu'à 404
        if (compteur < 404) {
            ++compteur;
            compteurElt.textContent += compteur;
        } else {
            // Annule l'exécution répétée
            clearInterval(intervalId);
            // Modifie le texte de l'erreur
            compteurElt.textContent = "Erreur 404";
            compteurElt.style.fontWeight = "bold";
            var description = document.getElementById("description");
            description.textContent = "Page introuvable...";
            var retour = document.getElementById("retour");
            retour.style.visibility = "visible";
            // Modification du texte de l'erreur au bout de 2 secondes
            setTimeout(function () {
                erreur.textContent = "Ouuuuuups !!!";
            }, 2000);
        }
    }

    var intervalId = setInterval(diminuerCompteur, 1);
    </script>
    <script type="text/javascript" src="/yapalQCM/js/jquery-3.2.1.min.js"></script>

</body>
</html>