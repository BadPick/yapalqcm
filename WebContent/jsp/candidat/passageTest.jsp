<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/yapalQCM/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/yapalQCM/css/yapalcss.css">
</head>


<body>
<%@include file="/menu.jsp"%>
<c:set var="message" value="${requestScope['message']}" scope="page" />
<c:set var="numQuestionEnCours" value="1" scope="page"/>
<div id="message" class="hidden">${message.message}</div>
<div id="messageType" class="hidden">${message.type}</div>
<div class="container">
<h1>coucou</h1>
	<div id="chrono"></div>
	<form method="post" action="<%=request.getContextPath()%>/Candidat/PasserUnTest">
		<input type="hidden" name="idTestSynthese" value="${ test.getId() }"/>
		<div id="recap">
			<c:forEach items="${ listeQuestions }" var="question" varStatus="statusRecap">
				<input id="inputRecap${ statusRecap.count }" type="hidden" name="questionMarquee-${ question.getId() }" value=""/>
				<button type="button" class="btn btn-default" id="btnRecap${ statusRecap.count }">${ statusRecap.count }</button>
			</c:forEach>
		</div>
		<c:forEach items="${ listeQuestions }" var="question" varStatus="statusQues">
			<div class="questionParQuestion" id="question${ statusQues.count }" <c:if test = "${ statusQues.count > 1 }"> style="display:none"</c:if>>
				<h2>Question n°<c:out value="${ statusQues.count }"/></h2>
				<h2><c:out value="${ question.getEnonce() }"/></h2>
				<div class="input-group">
				<ul>
					<c:forEach items="${ question.getReponses() }" var="reponse" varStatus="statusRep">
						<span class="input-group-addon">
							<input type="checkbox" name="reponseSelected-${ reponse.getId() }" id="${ statusRep.count }" /> <label for="${ statusRep.count }">${ reponse.getEnonce() }"</label><br>
						</span>
					</c:forEach>
				</ul>
				</div>
				<button type="button" class="btn btn-default" <c:if test = "${ statusQues.count == 1 }">disabled</c:if> id="questionPrecedente" onclick="PagePrecedente()">Question précédente</button>
				<button type="button" class="btn btn-default" id="marquageQuestion${ statusQues.count }" onclick="MarqueQuestion()">Marquer</button>
				<button type="button" class="btn btn-default" <c:if test = "${ statusQues.count == listeQuestions.size() }">disabled</c:if> id="questionSuivante" onclick="PageSuivante()">Question suivante</button>
			</div>
		</c:forEach>
		<input class="btn btn-default" id="validerTest" type="submit" name="validerTest" value="Valider le test"/>
	</form>
</div>

<script type="text/javascript">
	var numQuestion = 1;
	function MarqueQuestion(){
		var btnRecapQuestion = document.getElementById("btnRecap" + numQuestion);
		var inputRecapQuestion = document.getElementById("inputRecap" + numQuestion);
		var btnMarquage = document.getElementById("marquageQuestion" + numQuestion);
		if(btnMarquage.textContent == "Marquer"){
			btnRecapQuestion.style.background="red";
			btnMarquage.textContent="Enlever le marquage";
			inputRecapQuestion.value="1";
		}else{
			btnRecapQuestion.style.background="none";
			btnMarquage.textContent="Marquer";
			inputRecapQuestion.value="";
		}
		
	}

	function PageSuivante(){
		var questionEnCours = document.getElementById("question" + numQuestion);
		questionEnCours.style.display="none";
		numQuestion++;
		var questionSuivante = document.getElementById("question" + numQuestion);
		questionSuivante.style.display="block";
	}
	
	function PagePrecedente(){
		var questionEnCours = document.getElementById("question" + numQuestion);
		questionEnCours.style.display="none";
		numQuestion--;
		var questionSuivante = document.getElementById("question" + numQuestion);
		questionSuivante.style.display="block";
	}
</script>
<script type="text/javascript" src="/yapalQCM/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.4.1/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/gestionMessages.js"></script>
</body>
</html>