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
import fr.eni.yapalQCM.bo.Reponse;
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
	private ArrayList<Question> listeQuestions = null;
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
		Test test = null;
		
		try {
			//R�cup�ration du test g�n�r�
			if (request.getParameter("idTest")!=null) {
				if(listeQuestions==null){
					test = CandidatManager.getTest(1);
					listeQuestions = new ArrayList<Question>();
					for(Section section : test.getSections()){
						for(Question question : section.getTheme().getQuestions()){
							listeQuestions.add(question);
						}
					}
				}
				request.setAttribute("test", test);
				request.setAttribute("listeQuestions", listeQuestions);
				this.getServletContext().getRequestDispatcher("/jsp/candidat/passageTest.jsp").forward(request, response);
			}
			
			if ("Valider le test".equals(request.getParameter("validerTest")) && request.getParameter("idTestSynthese")!=null){
				for(Question question : listeQuestions){
					if(request.getParameter("questionMarquee-"+question.getId())!=null){
						question.setMarquee(true);
					}
					else{
						question.setMarquee(false);
					}
					for(Reponse reponse : question.getReponses()){
						if(request.getParameter("reponseSelected-"+reponse.getId())!=null){
							reponse.setChecked(true);
							question.isRepondue();
						}
						else{
							reponse.setChecked(false);
						}
					}
				}
				request.setAttribute("test", test);
				request.setAttribute("listeQuestions", listeQuestions);
				this.getServletContext().getRequestDispatcher("/jsp/candidat/syntheseTest.jsp").forward(request, response);
			}
			
			
			//besoin d'afficher un message
			//message = ErrorManager.getMessage("le message",MessageType.success);	
			
			
		} catch (Exception e) {
			//gestion des messages d'erreurs
			message = ErrorManager.getMessage(e);
			this.getServletContext().getRequestDispatcher("/jsp/candidat/accueilCandidat.jsp").forward(request, response);
		}
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		
		this.getServletContext().getRequestDispatcher("/jsp/candidat/accueilCandidat.jsp").forward(request, response);
	}

}
