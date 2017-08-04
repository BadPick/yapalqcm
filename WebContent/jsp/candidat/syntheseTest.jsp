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
	<div id="chrono"></div>
		<p>Les rastas !!</p>
			<p>Les rastas !!</p>
				<p>Les rastas !!</p>
	<h2>Synthèse du test n°<c:out value="${ test.getId() }"/></h2>
	<h3>Candidat : <c:out value="${ sessionScope.user.getPrenom() }"/> <c:out value="${ sessionScope.user.getNom() }"/></h3>
	
	<div>
		<ul>
			<c:forEach items="${ listeQuestions }" var="question" varStatus="statusQues">
				<li><a class="questionParQuestion" id="question${ statusQues.count }" 
						<c:if test = "${ question.isMarquee==true }"> style="color:red"</c:if>
						<c:if test = "${ question.isRepondue==true }"> style="color:green"</c:if>
					>question ${ statusQues.count }</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	
</div>
<script type="text/javascript" src="/yapalQCM/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.4.1/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="/yapalQCM/js/gestionMessages.js"></script>
</body>
</html>
