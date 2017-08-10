<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/yapalQCM/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/yapalQCM/css/yapalcss.css">
</head>


<body>
	<%@include file="/menu.jsp"%>

	<!-- Affichage d'un message si besoin -->
	<div id="message" class="hidden">${message.message}</div>
	<div id="messageType" class="hidden">${message.type}</div>

	<div class="containeur">
	
	<div class="row">
		<div class="col-sm-6"><h2>${ sessionScope.test.getNom() }</h2></div>
		<div class="col-sm-6 divChrono"><div id="chrono" style="visibility: hidden">${ sessionScope.test.getDuree()-tempsEcoule }</div></div>
	</div>


		<!-- Formulaire d'envoi des données du test -->
		<form method="get"
			action="<%=request.getContextPath()%>/Candidat/PasserUnTest"
			onsubmit="envoyerChrono(${ tempsEcoule })">
			<input type="hidden" name="idTestSynthese"
				value="${ sessionScope.test.getId() }" /> <input type="hidden"
				id="tempsEcoule" name="tempsEcoule" value="" />

			<!-- Barre de boutons qui affichent l'états des questions -->
			<div id="recap">
				<p id="questionEnCours" style="display: none">${ questionEnCours }</p>
				<c:forEach items="${ sessionScope.questions }" var="question"
					varStatus="statusRecap">

					<!-- Champs caché pour envoyer la valeur de la question marquée ou non -->
					<input id="inputRecap${ statusRecap.count }" type="hidden"
						name="questionMarquee-${ question.getId() }"
						value="<c:if test="${ question.isMarquee()==true }">1</c:if>" />

					<!-- Boutons qui affichent l'états des questions -->
					<button type="button" class="btn btn-standard2"
						id="btnRecap${ statusRecap.count }"
						onclick="RetourQuestion(${ statusRecap.count })"
						<c:if test="${ question.isMarquee()==true }">style="background:#f0e8d1"</c:if>>${ statusRecap.count }</button>
				</c:forEach>
			</div>

			<!-- Chargement de toutes les questions et leurs réponses -->
			<c:forEach items="${ sessionScope.questions }" var="question"
				varStatus="statusQues">

				<!-- Questions : -->
				<div class="questionParQuestion" id="question${ statusQues.count }"
					<c:if test = "${ statusQues.count > 1 }"> style="display:none"</c:if>>
					<!-- Numeros et énoncés : -->
					<h2>
						Question n°
						<c:out value="${ statusQues.count }" />
					</h2>
					<h3>
						<c:out value="${ question.getEnonce() }" />
					</h3>
					<div class="input-group">
						<div id="blockReponses-${ statusQues.count }">
							<ul>
								<!-- Réponses avec plusieurs réponses possibles (checkbox) : -->
								<c:if test="${ question.isPlusieursReponses()==true }">
									<p class="italic">Plusieurs réponses possibles</p>
									<c:forEach items="${ question.getReponses() }" var="reponse"
										varStatus="statusRep">
										<label>
										<input type="checkbox"
											name="reponseSelected-${ reponse.getId() }"
											id="${ statusRep.count }"
											<c:if test="${ reponse.isChecked()==true }"> checked</c:if> >
										${ reponse.getEnonce() }</label>
										<br>

									</c:forEach>
								</c:if>

								<!-- Réponses avec qu'une seule réponse possible (bouton radio) : -->
								<c:if test="${ question.isPlusieursReponses()==false }">
									<p class="italic">Une seule réponse possible</p>
									<c:forEach items="${ question.getReponses() }" var="reponse"
										varStatus="statusRep">
										<label>
										<input type="radio"
											name="reponseSelected-${ question.getId() }"
											id="${ statusRep.count }" value="${ statusRep.count }"
											<c:if test="${ reponse.isChecked()==true }"> checked</c:if> />
										${ reponse.getEnonce() }</label>
										<br>

									</c:forEach>
								</c:if>
							</ul>
						</div>
					</div>

					<!-- 3 boutons : Question précédente / Marquer / Question suivante -->
					
					<!-- barre de navigation -->
					<div class="row navigation">
						
					<!-- Question précédente : -->
					<button type="button" class="btn btn-standard"
						<c:if test = "${ statusQues.count == 1 }">disabled</c:if>
						id="questionPrecedente" onclick="ChangementPage('precedente')">Question
						précédente</button>

					<!-- Marquer : -->
					<button type="button" class="btn btn-standard btnMarque"
						id="marquageQuestion${ statusQues.count }"
						onclick="MarqueQuestion()">
						<c:choose>
							<c:when test="${ question.isMarquee()==true }">
						        Enlever le marquage
						    </c:when>
							<c:otherwise>
						        Marquer
						    </c:otherwise>
						</c:choose>
					</button>

					<!-- Question suivante : -->
					<button type="button" class="btn btn-standard"
						<c:if test = "${ statusQues.count == sessionScope.questions.size() }">disabled</c:if>
						id="questionSuivante" onclick="ChangementPage('suivante')">Question
						suivante</button>
						
					</div>
				</div>
			</c:forEach>

			<!-- 2 boutons : Page de synthèse / Valider le test -->
			<div class="row validation">
			<!-- Page de synthèse -->
			<button class="btn btn-standard-reverse" id="pageSynthese" type="submit"
				name="pageSynthese" value="Page de synthese" >Page de synthese</button>
			<!-- Valider le test -->
			<button class="btn btn-standard-reverse" id="validerTest" type="submit"
				name="validerTest" value="Valider le test" >Valider le test</button>
			</div>
		</form>
	</div>


	<script type="text/javascript" src="/yapalQCM/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.4.1/packaged/jquery.noty.packaged.min.js"></script>
	<script type="text/javascript" src="/yapalQCM/js/gestionMessages.js"></script>
	<script type="text/javascript" src="/yapalQCM/js/chrono.js"></script>
	<script type="text/javascript">
	// Arrivée sur la question cliquée dans la page de synthèse
	$( document ).ready(function Chargement(listeQuestions){
		RetourQuestion('${ questionEnCours }');
		/*A VERIFIER si on peut supprimer cette ligne*/
	});

	// Numéro de suivi de la question en cours
	var numQuestion = 1;

	// Marquage ou démarquage d'une question si click sur "Marquer / Enlever le marquage"
	function MarqueQuestion() {
		var btnRecapQuestion = document.getElementById("btnRecap" + numQuestion);
		var inputRecapQuestion = document.getElementById("inputRecap" + numQuestion);
		var btnMarquage = document.getElementById("marquageQuestion" + numQuestion);
		
		if (btnMarquage.textContent == "Enlever le marquage"){
			btnRecapQuestion.style.background = "none";
			btnMarquage.textContent = "Marquer";
			inputRecapQuestion.value = "";
		} else {
			btnRecapQuestion.style.background = "#f0e8d1";
			btnMarquage.textContent = "Enlever le marquage";
			inputRecapQuestion.value = "1";
		}			
	}

	// Basculement d'une question à une autre en fonction d'un sens via les boutons : Question précédente / Question suivante
	function ChangementPage(sens) {
		var questionEnCours = document.getElementById("question" + numQuestion);
		var btnRecapQuestionEnCours = document.getElementById("btnRecap" + numQuestion);
		$('#question' + numQuestion).hide("slow");
		btnRecapQuestionEnCours.style.border = "1px solid grey";
		EnvoiDonneesAjax();
		
		if(sens=="suivante"){
			numQuestion++;			
		}else{
			numQuestion--;	
		}
		
		var questionSuivante = document.getElementById("question" + numQuestion);
		var btnRecapQuestionSuivante = document.getElementById("btnRecap" + numQuestion);
		$('#question' + numQuestion).show("slow");
		btnRecapQuestionSuivante.style.border = "2px solid black";	
	}

	// Basculement sur la question cliquée dans la barre de suivi d'état des questions
	function RetourQuestion(questionClick) {
		var questionEnCours = document.getElementById("question" + numQuestion);
		var btnRecapQuestionEnCours = document.getElementById("btnRecap" + numQuestion);
		questionEnCours.style.display = "none";
		btnRecapQuestionEnCours.style.border = "1px solid grey";
		EnvoiDonneesAjax();
		
		numQuestion=questionClick;
		
		var questionSuivante = document.getElementById("question" + numQuestion);
		var btnRecapQuestionSuivante = document.getElementById("btnRecap" + numQuestion);
		questionSuivante.style.display = "block";
		btnRecapQuestionSuivante.style.border = "2px solid black";
	}
	
	// Envoi des changements en AJAX :
	function EnvoiDonneesAjax() {

		/* VALUES */
	    var numeroQuestion = numQuestion;
	    if($('#inputRecap'+numQuestion).val()==0){
		    var marquageQuestion = false;
	    } else {
	    	var marquageQuestion = true;
	    }
	    var tempsEcoule = $('#tempsEcoule').val();

		var blockReponses = $('#blockReponses-'+numQuestion);
	    var increment = 1;
	    var reponses = [];
	    var reponse;
	    blockReponses.find("input[name^='reponseSelected-']").each(function(){
	    	reponse = {numReponse:increment,checked:this.checked}
	    	reponses.push(reponse);
			increment++;
	    });
	    
		var dataString = {
		numQuestion:numeroQuestion,
		marquageQuestion:marquageQuestion,
		tempsEcoule:tempsEcoule,
		reponses:reponses				    
		};
		
		var dataJson = JSON.stringify(dataString);
	 	if (dataJson != '') {
	 		$.ajax({
	 			type: 'POST',
	 		    url: "<%=request.getContextPath()%>/EnregistrementResultatsEnCours",
	 		    data: {data:dataJson}
	 			});
		}
		
	}
	
	
	</script>
</body>
</html>