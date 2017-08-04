package fr.eni.yapalQCM.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.yapalQCM.bll.CandidatManager;
import fr.eni.yapalQCM.bll.ErrorManager;
import fr.eni.yapalQCM.bo.Resultat;
import fr.eni.yapalQCM.bo.Role;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Utilisateur;
import fr.eni.yapalQCM.utils.Message;
import fr.eni.yapalQCM.utils.MessageType;

/**
 * Servlet implementation class CandidatAccueil
 */
@WebServlet("/Candidat/Accueil")
public class CandidatAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Message message = null;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidatAccueil() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		//TESTING (simulation d'un candidat en session)
		/*Role role = new Role();
		role.setId(1);
		role.setName("Candidat");
		Utilisateur user = new Utilisateur();
		user.setId(1);
		user.setNom("Doe");
		user.setPrenom("Jhon");
		user.setDateDeNaissance(new Date());
		user.setEmail("jd@gmail.com");		
		user.setRole(role);
		session.setAttribute("user", user);*/
		
		
		
		try {
			//récupération du user en session
			Utilisateur candidat = (Utilisateur) session.getAttribute("user");
			
			//récupération de la liste de tests dispo pour ce candidat
			ArrayList<Test> tests = CandidatManager.getTests(candidat);
			request.setAttribute("testList", tests);
			
			//récupération de la liste de résultats dispo pour ce candidat
			ArrayList<Resultat> resultats = CandidatManager.getResultats(candidat);
			request.setAttribute("resultList", resultats);			
			
			//besoin d'afficher un message
			//message = ErrorManager.getMessage("le message",MessageType.success);	
			dispatcher = getServletContext().getRequestDispatcher("/jsp/candidat/accueilCandidat.jsp");		
		} catch (Exception e) {
			//gestion des messages d'erreurs
			message = ErrorManager.getMessage(e);		
		}
		
		if (message != null) {
			request.removeAttribute("message");
			request.setAttribute("message", message);
			message=null;
		}
		dispatcher.forward(request, response);
	}

}
