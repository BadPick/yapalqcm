<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Déclaration de variables -->
<c:set var="user" value="${sessionScope['user']}" scope="page" />

<div class="header">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div>

			<div class="navbar-header"></div>
			<div id="navbar" class="navbar-collapse collapse">
				<div>
					<div>
						<div>
							<div class="logo col-sm-2">
								<a href="<%=request.getContextPath()%>/Candidat/Accueil" ><img alt="YapalQCM" src="/yapalQCM/img/Logo_Yapal_QCM_Light.png" class="logo"></a>
							</div>
						</div>
					</div>
					<div>

						<c:if test="${user == null}">
							<!-- formulaire de connexion -->
							<form class="navbar-form navbar-right"
								action="<%=request.getContextPath()%>/ConnexionUtilisateur" method="post">
								<div class="form-group">
									<input type="text" name="login" id="inputlogin"
										class="form-control" placeholder="Login..." required
										autofocus>
								</div>
								<div class="form-group">
									<input type="password" name="password" id="inputPassword"
										class="form-control" placeholder="Password..."
										autocomplete="off" required>
								</div>
								<button id="btn-connexion" class="form-control" type="submit"
									name="typeAction" value="connexion">Connexion</button>
							</form>
						</c:if>

						<c:if test="${user != null}">
							<!-- formulaire de Déconnexion -->
							<form class="navbar-form navbar-right"
								action="<%=request.getContextPath()%>/ConnexionUtilisateur" method="post">
								<div class="form-group">
									<div class="col-sm-4">
										<div class="userName" title="">${user.nom} ${user.prenom}</div>
									</div>
								</div>
								<div class="form-group">
									<button id="btn-connexion" class="form-control" type="submit"
										name="typeAction" value="deconnexion">Déconnexion</button>
								</div>
							</form>

						</c:if>
					</div>

				</div>

			</div>


		</div>
	</nav>
</div>
