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
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Utilisateur;
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
		Resultat resultat = null;
		try {
			test = CandidatManager.getTest(Integer.parseInt(request.getParameter("idTest")));
			if (test!=null) {
				resultat = CandidatManager.getResultat(user, test);
				rd = request.getRequestDispatcher("/jsp/candidat/resultatTest.jsp");				
				request.setAttribute("resultat", resultat);
			}
			else{
				message = ErrorManager.getMessage("Test non reconnu",MessageType.error);
				rd = request.getRequestDispatcher("/error.jsp");
			}
		} catch (NumberFormatException e) {
			logger.severe("Paramètre idTest est au mauvais format pour la fonction Integer.parseInt(): "+e.getMessage());
			message = ErrorManager.getMessage(e);
			e.printStackTrace();
		} catch (SQLException e) {
			logger.severe("erreur requête SQL: "+e.getMessage());
			message = ErrorManager.getMessage(e);
			e.printStackTrace();
		}
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		rd.forward(request, response);
	}
}
