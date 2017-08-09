package fr.eni.yapalQCM.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.eni.yapalQCM.bo.Question;
import fr.eni.yapalQCM.bo.QuestionEnCoursWrapper;
import fr.eni.yapalQCM.bo.ReponseEnCoursWrapper;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Utilisateur;
import fr.eni.yapalQCM.dal.RepriseSurIncidentDAL;

/**
 * Servlet implementation class EnregistrementResultatsEnCours
 */
@WebServlet("/EnregistrementResultatsEnCours")
public class EnregistrementResultatsEnCours extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnregistrementResultatsEnCours() {
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

	@SuppressWarnings("unchecked")
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		
		// Récupération du string à convertir en Json et création des objets (Wrapper)
		String json = request.getParameter("data");
		Gson gson = new GsonBuilder().create();
		QuestionEnCoursWrapper qw = gson.fromJson(json, QuestionEnCoursWrapper.class);

		// Récupération des données en session
		ArrayList<Question> listeQuestions = (ArrayList<Question>) session.getAttribute("questions");
		Test test = (Test) session.getAttribute("test");
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
		
		// Récupération de l'id de la question "réelle"
		qw.setIdQuestion(listeQuestions.get(qw.getNumQuestion()).getId());
		
		// Insertion du Test En Cours
		RepriseSurIncidentDAL rsiDAL = new RepriseSurIncidentDAL();
		try {
			rsiDAL.deleteTest(utilisateur, test);
			rsiDAL.addTest(utilisateur, test, qw.getTempsEcoule());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int increment = 0;
		for(ReponseEnCoursWrapper rw : qw.getReponses()){
			// Récupération de l'id de la réponse réelle
			rw.setIdReponse(listeQuestions.get(qw.getNumQuestion()).getReponses().get(increment).getId());
			
			try {
				// Suppression avant insertion
				rsiDAL.deleteReponse(rw, utilisateur, test, qw);
				
				// Insertion de chaque Reponse En Cours
				rsiDAL.addReponse(rw, utilisateur, test, qw);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			increment++;
		}
	}
	
}
