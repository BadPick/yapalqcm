package fr.eni.yapalQCM.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import fr.eni.yapalQCM.bll.ConnexionManager;
import fr.eni.yapalQCM.bll.ErrorManager;
import fr.eni.yapalQCM.bo.Utilisateur;
import fr.eni.yapalQCM.utils.Message;
import fr.eni.yapalQCM.utils.MessageType;
import fr.eni.yapalQCM.utils.ToolUtilisateur;
import fr.eni.yapalQCM.utils.YapalLogger;

/**
 * Servlet implementation class ConnexionUtilisateur
 */
@WebServlet("/ConnexionUtilisateur")
public class ConnexionUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = YapalLogger.getLogger(this.getClass().getName());
	private Message message = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionUtilisateur() {
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
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("entr�e dans la Servlet ConnexionUtilisateur");
		logger.entering("ConnexionUtilisateur", "processRequest");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		switch (request.getParameter("typeAction")) {
		case "connexion":
			System.out.println("TypeAction => 'connexion'");
			try {
				
				Utilisateur user = null;
				String login = request.getParameter("login");
				String password= request.getParameter("password");
				String encryptPassword = ToolUtilisateur.encryptPassword(password);	
				if (login!="" || password!="") {
					ConnexionManager connexion = new ConnexionManager();
					user=connexion.getConnexion(login,encryptPassword);
					
					if(user==null)
					{
						message = ErrorManager.getMessage("Mauvais login ou mot de passe.",MessageType.error);
						dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
					}else{
						message = ErrorManager.getMessage("Connection valid�e",MessageType.success);
						session.setAttribute("user", user);
						dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
					}
				} else {
					message = ErrorManager.getMessage("Merci de renseigner le login et le mot de passe.",MessageType.error);
					dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
				}
			
			} catch (Exception e) {
				//gestion des messages d'erreurs
				message = ErrorManager.getMessage(e);
				e.printStackTrace();
				logger.severe(e.getMessage());
			}
			
			break;
		case "deconnexion":	
			System.out.println("TypeAction => 'deconnexion'");
			session.invalidate();
			dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			break;
		default:
			System.out.println("!!!Non Trait�!!!");
			break;
		}
		
		if (message != null) {
			request.removeAttribute("message");
			request.setAttribute("message", message);
			message=null;
		}
		logger.exiting("ConnexionUtilisateur", "processRequest");
		System.out.println("Sortie dans la Servlet ConnexionUtilisateur");
		dispatcher.forward(request, response);
	}
}