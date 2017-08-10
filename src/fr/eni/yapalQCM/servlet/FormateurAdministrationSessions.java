package fr.eni.yapalQCM.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.yapalQCM.bll.ErrorManager;
import fr.eni.yapalQCM.bll.SessionsManager;
import fr.eni.yapalQCM.bll.TestsManager;
import fr.eni.yapalQCM.bo.Session;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.utils.Message;
import fr.eni.yapalQCM.utils.MessageType;
import fr.eni.yapalQCM.utils.YapalLogger;

/**
 * Servlet implementation class FormateurAdministrationSessions
 */
@WebServlet("/Formateur/Administration/Sessions")
public class FormateurAdministrationSessions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = YapalLogger.getLogger(this.getClass().getName());
	private Message message = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormateurAdministrationSessions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response); 
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeAction = request.getParameter("typeAction");
		int idSession = 0;
		message = null;
		if(typeAction!=null){
			switch (typeAction) {
			case "ajouter":				
				try {
					Session session = new Session();
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
					Date heure = new SimpleDateFormat("HH:mm").parse("00:00");
					ArrayList<Test> tests = new ArrayList<Test>();
					int nbPlaces = Integer.parseInt(request.getParameter("nbPlaces"));
					session.setDate(date);
					session.setNbPlaces(nbPlaces);
					for (String id : request.getParameterValues("idTest")) {
						int idTest = Integer.parseInt(id);
						Test test = TestsManager.getTest(idTest);
						tests.add(test);
					}				
					session.setTests(tests);
					SessionsManager.ajouterSession(session);
					message=ErrorManager.getMessage("session ajoutée", MessageType.information);
				} catch (ParseException e) {
					message=ErrorManager.getMessage("mauvais format d'heure et/ou de date", MessageType.error);
					logger.severe("Erreur de récupération de la date et/ou de l'heure "+e.getMessage());
					e.printStackTrace();
				} catch (SQLException e) {
					message=ErrorManager.getMessage("Erreur d'accés à la base de données", MessageType.error);
					logger.severe("Erreur d'accés à la base de données "+e.getMessage());
					e.printStackTrace();
				}				
				break;
			case "modifier":
				try {
					Session session = new Session();
					idSession = Integer.parseInt(request.getParameter("idSession"));
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
					Date heure = new SimpleDateFormat("HH:mm").parse("00:00");					
					ArrayList<Test> tests = new ArrayList<Test>();
					int nbPlaces = Integer.parseInt(request.getParameter("nbPlaces"));
					session.setId(idSession);
					session.setDate(date);
					session.setNbPlaces(nbPlaces);
					for (String id : request.getParameterValues("idTest")) {
						int idTest = Integer.parseInt(id);
						Test test = TestsManager.getTest(idTest);
						tests.add(test);
					}				
					session.setTests(tests);
					SessionsManager.modifierSession(session);
					message=ErrorManager.getMessage("session modifiée", MessageType.information);
				} catch (ParseException e) {
					message=ErrorManager.getMessage("mauvais format d'heure et/ou de date", MessageType.error);
					logger.severe("Erreur de récupération de la date et/ou de l'heure "+e.getMessage());
					e.printStackTrace();
				} catch (SQLException e) {
					message=ErrorManager.getMessage("Erreur d'accés à la base de données Session déjà réalisée modification impossible", MessageType.error);
					logger.severe("Erreur d'accés à la base de données "+e.getMessage());
					e.printStackTrace();
				}				
				break;
			case "supprimer":				
				try {
					idSession = Integer.parseInt(request.getParameter("idSession"));
					SessionsManager.suprimerSession(idSession);
					message=ErrorManager.getMessage("session supprimée", MessageType.information);
				} catch (SQLException e) {
					message=ErrorManager.getMessage("Erreur d'accés à la base de données", MessageType.error);
					logger.severe("Erreur d'accés à la base de données "+e.getMessage());
					e.printStackTrace();
				}
				
				break;
			default:
				break;
			}
		}
		
		try {
			List<Session> listeSessions = SessionsManager.getSessions();
			List<Test> listeTests = TestsManager.getTests();
			request.setAttribute("listeSessions", listeSessions);
			request.setAttribute("listeTests", listeTests);
		} catch (SQLException e) {
			message = ErrorManager.getMessage("Erreur de r�cup�ration de la base de donn�e: "+e.getMessage(),MessageType.error);
			logger.severe("Erreur de r�cup�ration de la base de donn�e: "+e.getMessage());
			e.printStackTrace();
		}
		
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/formateur/administrationSessions.jsp");
		
		rd.forward(request, response);
	}
}
