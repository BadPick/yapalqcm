<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../../css/yapalcss.css">
</head>
<body>
<c:set var="testList" value="${requestScope['testList']}" scope="page" />
<c:set var="resultList" value="${requestScope['resultList']}" scope="page" />

<div class="container">
	<h2>Passer un test</h2>
		<div>
			<c:forEach var="test" items="${testList}">
				<div class="row">
	
					<a href="<%= request.getContextPath() %>/PasserUnTest?idTest=${test.id}">
					<c:out value="${test.nom} (${test.duree})" />
					</a>
	
				</div>
			</c:forEach>
		</div>
	<h2>Consulter mes résultats</h2>
		<div>
			<c:forEach var="result" items="${resultList}">
				<div class="row">
	
					<a href="<%= request.getContextPath() %>/PasserUnTest?idTest=${result.test.id}&idSession=${result.session.id}">
					<c:out value="${result.test.nom}" />
					</a>
	
				</div>
			</c:forEach>
		</div>
</div>
</body>
</html>