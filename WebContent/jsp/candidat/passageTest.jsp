<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
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
	<c:forEach items="${ listeQuestions }" var="question" varStatus="status">
		<article class="question" id="${ status }" style="display:none">
			<h2>Question n°<c:out value="${ status }"/></h2>
			<h2><c:out value="${ question.enonce }"/></h2>
			<ul>
			<c:forEach items="${ question.reponses }" var="reponse" varStatus="statusRep">
				<input type="checkbox" name="${ statusRep }" id="${ statusRep }" /> <label for="${ statusRep }">${ reponse.enonce }"</label><br>
			</c:forEach>
			</ul>
		</article>
	</c:forEach>
	<button id="questionSuivante" onclick="PageSuivante(numQuestionEnCours)"></button>
	
</div>
<script type="text/javascript">
	function PageSuivante(numQuestion){
		var btnQS = document.getElementById("questionSuivante");
		var questionEnCours = document.getElementById(numQuestion);
		questionEnCours.style.display="none";
		numQuestion++;
		var questionSuivante = document.getElementById(numQuestion);
		questionSuivante.style.display="none";
		
	}
</script>
<script type="text/javascript" src="/yapalQCM/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.4.1/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/gestionMessages.js"></script>
</body>
</html>