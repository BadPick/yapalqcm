package fr.eni.yapalQCM.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.yapalQCM.bll.ErrorManager;
import fr.eni.yapalQCM.utils.Message;
import fr.eni.yapalQCM.utils.MessageType;

/**
 * Servlet implementation class AccueilCandidat
 */
@WebServlet("/AccueilCandidat")
public class Template extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Message message = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Template() {
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
			
			
			
			
			//besoin d'afficher un message
			//message = ErrorManager.getMessage("le message",MessageType.success);	
			
			dispatcher = this.getServletContext().getRequestDispatcher("******************");
		} catch (Exception e) {
			//gestion des messages d'erreurs
			message = ErrorManager.getMessage(e);		
		}
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		dispatcher.forward(request, response);
	}

}
