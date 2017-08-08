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
<div id="message" class="hidden">${message.message}</div>
<div id="messageType" class="hidden">${message.type}</div>
<div class="container">
	<div id="chrono" style="visibility:hidden">${ sessionScope.test.getDuree()-tempsEcoule }</div>
	<h2>Synthèse du test n°<c:out value="${ sessionScope.test.getId() }"/></h2>
	<h3>Candidat : <c:out value="${ sessionScope.user.getPrenom() }"/> <c:out value="${ sessionScope.user.getNom() }"/></h3>
	
	<div>
		<ul>
			<c:forEach items="${ sessionScope.questions }" var="question" varStatus="statusQues">
				<form method="post" action="<%=request.getContextPath()%>/Candidat/PasserUnTest" onsubmit="envoyerChronoParam(${ tempsEcoule }, ${ statusQues.count })">
					<input type="hidden" id="idTest" name="idTest" value="${ sessionScope.test.getId() }" />
					<input type="hidden" id="tempsEcoule${ statusQues.count }" name="tempsEcoule" value="" />
					<input type="submit" class="questionParQuestion" id="question${ statusQues.count }" name="questionEnCours" value="${ statusQues.count }">
						question ${ statusQues.count } 
						<c:if test = "${ question.isMarquee()==true }">&#10008;</c:if>
						<c:if test = "${ question.isRepondue()==true }">&#10004;</c:if>
					</input>
				</form>
			</c:forEach>
		</ul>
	</div>
	
</div>
<script type="text/javascript" src="/yapalQCM/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.4.1/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/gestionMessages.js"></script>
<script type="text/javascript" src="/yapalQCM/js/chrono.js"></script>
</body>
</html>
