<%@ page language="java" contentType="text/html; charset=UTF-8
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/yapalcss.css">
</head>
<body>
<c:set var="testList" value="${requestScope['testList']}" scope="page" />
<c:set var="resultList" value="${requestScope['resultList']}" scope="page" />
<c:set var="message" value="${requestScope['message']}" scope="page" />
<div id="message" class="hidden">${message.message}</div>
<div id="messageType" class="hidden">${message.type}</div>
<div class="container">
	<h2>Passer un test</h2>
		<div>
			<c:forEach var="test" items="${testList}">
				<div class="row">
	
					<a href="<%= request.getContextPath() %>/CandidatPasserUnTest?idTest=${test.id}">
					<c:out value="${test.nom} (${test.duree})" />
					</a>
	
				</div>
			</c:forEach>
		</div>
		
	<h2>Consulter mes résultats</h2>
		<div>
			<c:forEach var="result" items="${resultList}">
				<div class="row">
	
					<a href="<%= request.getContextPath() %>/CandidatConsulterResultat?idTest=${result.test.id}&idSession=${result.session.id}">
					<c:out value="${result.test.nom}" />
					</a>
	
				</div>
			</c:forEach>
		</div>
</div>

<script type="text/javascript" src="./js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.4.1/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="./js/gestionMessages.js"></script>
</body>
</html>