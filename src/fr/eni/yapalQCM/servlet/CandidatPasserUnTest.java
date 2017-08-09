package fr.eni.yapalQCM.servlet;

import java.io.IOException;
import java.util.ArrayList;

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
import fr.eni.yapalQCM.bo.Resultat;
import fr.eni.yapalQCM.bo.Section;
import fr.eni.yapalQCM.bo.Session;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Utilisateur;
import fr.eni.yapalQCM.dal.ResultatDAL;
import fr.eni.yapalQCM.utils.Message;
import fr.eni.yapalQCM.utils.MessageType;

/**
 * Servlet implementation class CandidatPasserUnTest
 */
@WebServlet("/Candidat/PasserUnTest")
public class CandidatPasserUnTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Message message = null;   
	private long tempsEcoule;
	private int questionEnCours;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidatPasserUnTest() {
        super();
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
		
		if(session.getAttribute("user")==null){
			message = ErrorManager.getMessage("Votre session a expiré.",MessageType.information);
			request.setAttribute("message", message);
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		else{
			// Réinitialisation des réponses et marquages fait par un stagiaire :
			if(session.getAttribute("questions")!=null && request.getParameter("idTest")==null){
				for(Question question : (ArrayList<Question>)session.getAttribute("questions")){
					question.setRepondue(false);
					question.setMarquee(false);
					
					for(Reponse reponse : question.getReponses()){
						reponse.setChecked(false);
					}
				}
			}
			
			try {
				// Si test démarré :
				// Données à envoyer vers page du Passage de Test :
				if (request.getParameter("idTest")!=null) {
					//Récupération du test généré
					if(session.getAttribute("questions")==null){
						Test test = CandidatManager.getTest(Integer.parseInt(request.getParameter("idTest")));
						tempsEcoule = test.getDuree();
						ArrayList<Question> listeQuestions = new ArrayList<Question>();
						for(Section section : test.getSections()){
							for(Question question : section.getTheme().getQuestions()){
								question.checkNbreReponses();
								listeQuestions.add(question);
							}
						}
						session.setAttribute("questions", listeQuestions);
						session.setAttribute("test", test);
					}
					
					// Récupération du temps écoulé
					if(request.getParameter("tempsEcoule")!=null){
						tempsEcoule = Long.parseLong(request.getParameter("tempsEcoule"));
					}else{
						tempsEcoule=0;					
					}
					
					// Récupération de la question cliquée sur la page de synthèse
					if(request.getParameter("questionEnCours")!=null){
						questionEnCours = Integer.parseInt(request.getParameter("questionEnCours"));
					}else{
						questionEnCours = 1;
					}
					request.setAttribute("tempsEcoule", tempsEcoule);
					request.setAttribute("questionEnCours", questionEnCours);					
					this.getServletContext().getRequestDispatcher("/jsp/candidat/passageTest.jsp").forward(request, response);
				}
				
				// Analyse du test en cours pour création de la page de synthèse
				if ("Page de synthese".equals(request.getParameter("pageSynthese")) && request.getParameter("idTestSynthese")!=null){
					for(Question question : (ArrayList<Question>)session.getAttribute("questions")){
						// Vérification si la question a été marqué ou non
						if("1".equals(request.getParameter("questionMarquee-"+question.getId()))){
							question.setMarquee(true);
						}
						else{
							question.setMarquee(false);
						}
						
						// Vérification si la question a été répondue ou non
						for(Reponse reponse : question.getReponses()){
							if(question.isPlusieursReponses()){
								if(request.getParameter("reponseSelected-"+reponse.getId())!=null){
									reponse.setChecked(true);
									question.setRepondue(true);
								}
								else{
									reponse.setChecked(false);
								}							
							} else {
								int numReponse;
								if(request.getParameter("reponseSelected-"+question.getId())!=null){
									numReponse = Integer.parseInt(request.getParameter("reponseSelected-"+question.getId()))-1;
									question.getReponses().get(numReponse).setChecked(true);		
									question.setRepondue(true);
									break;
								}
								else{
									reponse.setChecked(false);
								}	
							}
						}
					}
					
					request.setAttribute("tempsEcoule", request.getParameter("tempsEcoule"));
					this.getServletContext().getRequestDispatcher("/jsp/candidat/syntheseTest.jsp").forward(request, response);
				}
				
				// Analyse du test terminé pour création de la page de résultat et du stockage en base de données
				if ("Valider le test".equals(request.getParameter("validerTest")) && request.getParameter("idTestSynthese")!=null){
					boolean correct;
					int score = 0;
					boolean acquis = false;
					boolean enCoursAcquisition = false;
					String acquisition = null;
					for(Question question : (ArrayList<Question>)session.getAttribute("questions")){
						correct = false;
						if(question.isPlusieursReponses()){
							for(Reponse reponse : question.getReponses()){
								if(request.getParameter("reponseSelected-"+reponse.getId())!=null && reponse.isCorrect() == true){
									correct = true;
								}
								if(request.getParameter("reponseSelected-"+reponse.getId())==null && reponse.isCorrect() == false){
									correct = true;
								}
								if(request.getParameter("reponseSelected-"+reponse.getId())!=null && reponse.isCorrect() == false){
									correct = false;
									break;
								}
								if(request.getParameter("reponseSelected-"+reponse.getId())==null && reponse.isCorrect() == true){
									correct = false;
									break;
								}
							}
						}
						if(!question.isPlusieursReponses()){
							int numReponse;
							if(request.getParameter("reponseSelected-"+question.getId())!=null){
								numReponse = Integer.parseInt(request.getParameter("reponseSelected-"+question.getId()))-1;
								if(question.getReponses().get(numReponse).isCorrect()){
									correct = true;
								}
							}
						}
						if (correct){
							score++;
						}
					}
					
					int nbreQuestions = ((ArrayList<Question>) session.getAttribute("questions")).size();
					
					if(score*100/nbreQuestions>=Math.round(((Test)session.getAttribute("test")).getSeuilEnCoursDacquisition())){
						enCoursAcquisition = true;
					}
					
					if(score*100/nbreQuestions>=Math.round(((Test)session.getAttribute("test")).getSeuilAcquis())){
						acquis = true;
					}
					
					if(acquis){
						acquisition = "Acquis";
					}
					else if(!acquis && enCoursAcquisition){
						acquisition = "En cours d'acquisition";
					}
					else{
						acquisition = "Non-acquis";
					}
					
					tempsEcoule = Long.parseLong(request.getParameter("tempsEcoule"));
					
					// Insertion du résultat du test en base de données
					Resultat resultat = new Resultat();
					Session sessionResult = new Session();
					sessionResult.setId(1);
					resultat.setSession(sessionResult);
					resultat.setCandidat((Utilisateur)session.getAttribute("user"));
					resultat.setTest((Test)session.getAttribute("test"));
					resultat.setSeuilObtenu((float)score*100/nbreQuestions);
					resultat.setTempsEcoule(tempsEcoule);
					ResultatDAL rd = new ResultatDAL();
					rd.add(resultat);
					
					// Renvoi des données du résultat pour affichage de la page résultat
					request.setAttribute("score", score);
					request.setAttribute("nbreQuestions", nbreQuestions);
					request.setAttribute("acquisition", acquisition);
					request.setAttribute("tempsEcoule", tempsEcoule);
					this.getServletContext().getRequestDispatcher("/jsp/candidat/resultatTest.jsp").forward(request, response);
				}
				
				//besoin d'afficher un message
				//message = ErrorManager.getMessage("le message",MessageType.success);	
				
				
			} catch (Exception e) {
				//gestion des messages d'erreurs
				message = ErrorManager.getMessage(e);
			}
			
			if (message != null) {
				request.setAttribute("message", message);
			}
			
			this.getServletContext().getRequestDispatcher("/jsp/candidat/accueilCandidat.jsp").forward(request, response);
		}
	}

}
