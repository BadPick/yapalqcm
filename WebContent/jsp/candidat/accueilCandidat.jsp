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
	<c:set var="testList" value="${requestScope['testList']}" scope="page" />
	<c:set var="resultList" value="${requestScope['resultList']}"
		scope="page" />
	<c:set var="message" value="${requestScope['message']}" scope="page" />
	<div id="message" class="hidden">${message.message}</div>
	<div id="messageType" class="hidden">${message.type}</div>
	<div class="container">
		<h1>Bienvenue ${sessionScope.user.prenom } ${sessionScope.user.nom }</h1>
		<h2 class="title">Vos tests :</h2>
		<div>
			<table class="table table-hover">
				<c:forEach var="test" items="${testList}">
					<tr class="test">
						<td class="" colspan="2">${test.nom}(${test.duree})</td>
						<td>${test.nbQuestions } questions</td>
						<td><button type="button" name="details" value="${test.id}" class="form-control">Détails</button></td>
						<td>
							<form action="<%=request.getContextPath()%>/Candidat/PasserUnTest">
								<input type="hidden" name="idTest" value="${ test.id }">
								<button type="submit" class="form-control">Passer ce test</button>
							</form>
						</td>
							<td>
								<c:forEach var="result" items="${resultList}">
									<c:if test="${result.test.id == test.id}">
										<form
											action="<%=request.getContextPath()%>/Candidat/ConsulterResultat">
											<input type="hidden" name="idTest" value="${result.test.id}">
											<button type="submit" class="form-control">Résultats</button>
										</form>
									</c:if>
								</c:forEach>
							</td>
					</tr>
					<c:forEach var="section" items="${test.sections}">
						<tr class="section-${test.id}">
							<td></td>
							<td>${section.theme.nom}</td>
							<td>${section.nbQuestions } questions</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</c:forEach>
				</c:forEach>
			</table>
		</div>
	</div>

	<script type="text/javascript" src="/yapalQCM/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-noty/2.4.1/packaged/jquery.noty.packaged.min.js"></script>
	<script type="text/javascript" src="/yapalQCM/js/gestionMessages.js"></script>
	<script type="text/javascript">
	$( document ).ready(function() {
		$("[class^='section']").hide();
	});
	$("[name='details']").click(function() {
		var id = $(this).val();
		var tmp = '.section-'+id;
		if($(tmp).is(":visible")) {
			$(tmp).hide()
		}else{
			$(tmp).show();
		}
	});
	</script>
</body>
</html>
