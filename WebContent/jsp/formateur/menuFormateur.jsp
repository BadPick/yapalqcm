<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- Menu du Formateur -->
<div class="navigation menu-formateur">
<div class="btn-group">
  <button type="button" class="btn btn-standard dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Administrations <span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
    <li class="dropdown-item" ><a href="<%=request.getContextPath()%>/Formateur/Administration/Sessions">Administration des Sessions</a></li>
    <li class="dropdown-item" ><a href="<%=request.getContextPath()%>/Formateur/Administration/Tests">Administration des Tests</a></li>
    <li class="dropdown-item" ><a href="<%=request.getContextPath()%>/Formateur/Administration/Themes">Administration des Thèmes</a></li>
    <li class="dropdown-item" ><a href="<%=request.getContextPath()%>/Formateur/Administration/QuestionsReponses">Administration des Questions/réponses</a></li>
    <li class="dropdown-item" ><a href="<%=request.getContextPath()%>/Formateur/Administration/Utilisateurs">Administration des Utilisateur</a></li>
  </ul>
</div>

<div class="btn-group">
  <button type="button" class="btn btn-standard dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Inscriptions <span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
    <li class="dropdown-item"><a href="<%=request.getContextPath()%>/Formateur/Inscription/Candidats">Inscription d'un candidat</a></li>
	<li class="dropdown-item"><a href="<%=request.getContextPath()%>/Formateur/Inscriptions">Consultation des inscriptions</a></li>
  </ul>
</div>
<div class="btn-group">
  <button type="button" class="btn btn-standard dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Résultats <span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
	<li class="dropdown-item"><a href="<%=request.getContextPath()%>/Formateur/Resultats">Consultation des résultats</a></li>
  </ul>
</div>
</div>
<!-- Fin du Menu du Formateur -->