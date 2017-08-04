package fr.eni.yapalQCM.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.yapalQCM.bll.CandidatManager;
import fr.eni.yapalQCM.bll.ErrorManager;
import fr.eni.yapalQCM.bo.Question;
import fr.eni.yapalQCM.bo.Section;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.utils.Message;

/**
 * Servlet implementation class CandidatPasserUnTest
 */
@WebServlet("/Candidat/PasserUnTest")
public class CandidatPasserUnTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Message message = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidatPasserUnTest() {
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
		Test test = null;
		ArrayList<Question> listeQuestions = new ArrayList<Question>();
		
		try {
			//R�cup�ration du test g�n�r�
			if (request.getParameter("idTest")!=null) {
				test = CandidatManager.getTest(Integer.valueOf(request.getParameter("1")));
				for(Section section : test.getSections()){
					for(Question question : section.getTheme().getQuestions()){
						listeQuestions.add(question);
					}
				}
			}
			request.setAttribute("listeQuestions", listeQuestions);
			
			
			
			//besoin d'afficher un message
			//message = ErrorManager.getMessage("le message",MessageType.success);	
			
			dispatcher = getServletContext().getRequestDispatcher("/jsp/candidat/passageTest.jsp");
		} catch (Exception e) {
			//gestion des messages d'erreurs
			message = ErrorManager.getMessage(e);
			dispatcher = getServletContext().getRequestDispatcher("/jsp/candidat/accueilCandidat.jsp");
		}
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		dispatcher.forward(request, response);
	}

}
