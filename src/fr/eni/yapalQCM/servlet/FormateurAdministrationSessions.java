package fr.eni.yapalQCM.servlet;

import java.io.IOException;
import java.sql.SQLException;
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
import fr.eni.yapalQCM.bo.Session;
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
		
		try {
			List<Session> listeSessions = SessionsManager.getSessions();
			request.setAttribute("listeSessions", listeSessions);
		} catch (SQLException e) {
			message = ErrorManager.getMessage("Erreur de récupération de la base de donnée: "+e.getMessage(),MessageType.error);
			logger.severe("Erreur de récupération de la base de donnée: "+e.getMessage());
			e.printStackTrace();
		}
		
		
		if (message != null) {
			request.removeAttribute("message");
			request.setAttribute("message", message);
			message=null;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/formateur/administrationSessions.jsp");
		
		rd.forward(request, response);
	}
}
