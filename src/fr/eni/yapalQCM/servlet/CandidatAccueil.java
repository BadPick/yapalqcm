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
			
		try {
			//r�cup�ration du user en session
			Utilisateur candidat = (Utilisateur) session.getAttribute("user");
			
			//r�cup�ration de la liste de tests dispo pour ce candidat
			ArrayList<Test> tests = CandidatManager.getTests(candidat);
			ArrayList<Resultat> resultats=new ArrayList<Resultat>();
			request.setAttribute("testList", tests);
			for (Test test : tests) {
				//r�cup�ration de la liste de r�sultats dispo pour ce candidat	
				Resultat resultat = null;
				resultat = CandidatManager.getResultat(candidat,test);
				if (resultat!=null) {
					resultats.add(resultat);					
				}
			}
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
