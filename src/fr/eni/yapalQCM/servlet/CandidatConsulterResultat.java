package fr.eni.yapalQCM.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

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
import fr.eni.yapalQCM.bo.Session;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Utilisateur;
import fr.eni.yapalQCM.dal.ResultatDAL;
import fr.eni.yapalQCM.utils.Message;
import fr.eni.yapalQCM.utils.MessageType;
import fr.eni.yapalQCM.utils.YapalLogger;

/**
 * Servlet implementation class CandidatConsulterResultat
 */
@WebServlet("/Candidat/ConsulterResultat")
public class CandidatConsulterResultat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Message message = null;
	Logger logger = YapalLogger.getLogger(this.getClass().getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidatConsulterResultat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestProcess(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		Utilisateur user = null;
		user = (Utilisateur) session.getAttribute("user");
		RequestDispatcher rd=null;
		Test test=null;
		message = null;
		Resultat resultat = null;
		
		try {
			test = CandidatManager.getTest(Integer.parseInt(request.getParameter("idTest")),(Utilisateur)session.getAttribute("user"));
			if (test!=null) {
				resultat = CandidatManager.getResultat(user, test);
				rd = request.getRequestDispatcher("/jsp/candidat/resultatTest.jsp");				
				request.setAttribute("resultat", resultat);
				int score = (int)((resultat.getSeuilObtenu()*test.getNbQuestions())/100);
				String acquisition = "Non acquis";
				if (resultat.getSeuilObtenu()>=test.getSeuilAcquis()) {
					acquisition = "Acquis";
				}else if (resultat.getSeuilObtenu()>=test.getSeuilEnCoursDacquisition()) {
					acquisition = "En cours d'acuisition";
				}
				
				// Renvoi des données du résultat pour affichage de la page résultat
				request.setAttribute("score", score);
				request.setAttribute("nbreQuestions", test.getNbQuestions());
				request.setAttribute("acquisition", acquisition);
				request.setAttribute("tempsEcoule", resultat.getTempsEcoule());
				request.setAttribute("testId", request.getParameter("idTestSynthese"));
			}
			else{
				message = ErrorManager.getMessage("Test non reconnu",MessageType.error);
				rd = request.getRequestDispatcher("/error.jsp");
			}
		} catch (SQLException e) {
			logger.severe("erreur requ�te SQL: "+e.getMessage());
			message = ErrorManager.getMessage(e);
			e.printStackTrace();
			this.getServletContext().getRequestDispatcher("/Candida/tAccueil").forward(request, response);
		}
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		this.getServletContext().getRequestDispatcher("/jsp/candidat/resultatTest.jsp").forward(request, response);
	}
}
