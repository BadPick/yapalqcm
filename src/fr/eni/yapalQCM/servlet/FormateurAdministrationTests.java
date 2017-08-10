package fr.eni.yapalQCM.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.yapalQCM.bll.ErrorManager;
import fr.eni.yapalQCM.bll.TestsManager;
import fr.eni.yapalQCM.bll.ThemesManager;
import fr.eni.yapalQCM.bo.Section;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Theme;
import fr.eni.yapalQCM.dal.TestDAL;
import fr.eni.yapalQCM.dal.ThemeDAL;
import fr.eni.yapalQCM.utils.Message;
import fr.eni.yapalQCM.utils.MessageType;
import fr.eni.yapalQCM.utils.YapalLogger;

/**
 * Servlet implementation class FormateurAdministrationTests
 */
@WebServlet("/Formateur/Administration/Tests")
public class FormateurAdministrationTests extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = YapalLogger.getLogger(this.getClass().getName());
	private Message message = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormateurAdministrationTests() {
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
		message = null;
		String typeAction=null;
		// Chargement des éléments nécessaire à la vue.
		try {
			List<Test> listeTests = TestsManager.getTests();
			List<Theme> listeThemes = ThemesManager.getThemes();
			request.setAttribute("listeTests", listeTests);
			request.setAttribute("listeThemes", listeThemes);
		} catch (SQLException e) {
			message=ErrorManager.getMessage("Impossibilité de récupérer la liste des Tests en base.", MessageType.error);
			logger.severe("Impossibilité de récupérer la liste des Tests en base. "+e.getMessage());
			e.printStackTrace();
		}
		
		// traitement des actions des différents formulaires de la vue.
		if(request.getParameter("typeAction")!=null) typeAction=request.getParameter("typeAction");
		if(typeAction!=null){
			switch (typeAction) {
			case "ajouter":
				Test test = new Test();
				if(request.getParameter("nom")!=null){
					test.setNom(request.getParameter("nom"));
				}
				if(request.getParameter("seuilAcquis")!=null){
					test.setSeuilAcquis(Float.parseFloat(request.getParameter("seuilAcquis")));
				}
				if(request.getParameter("seuilEnCourDacquisition")!=null){
					test.setSeuilEnCoursDacquisition(Float.parseFloat(request.getParameter("seuilEnCourDacquisition")));
				}
				if(request.getParameter("duree")!=null){
					test.setDuree(Long.parseLong(request.getParameter("duree")));
				}
				if(request.getParameterValues("themes")!=null){
					ArrayList<Section> sections = new ArrayList<Section>();
					List<Integer> nbQuestions = null;
					if(request.getParameterValues("nbQuestions")!=null 
							&& request.getParameterValues("themes")!=null
							&& request.getParameterValues("nbQuestions").length == request.getParameterValues("themes").length){
						nbQuestions = new ArrayList<Integer>();
						for (String nb : request.getParameterValues("nbQuestions")) {
							nbQuestions.add(Integer.parseInt(nb));
						}				
						int cptQuestion = 0;
						for (String id : request.getParameterValues("themes")) {
							int idTheme = Integer.parseInt(id);
							Theme theme = new Theme();
							Section section = new Section();
							theme.setId(idTheme);			
							section.setTheme(theme);
							section.setNbQuestions(nbQuestions.get(cptQuestion));
							sections.add(section);
							cptQuestion++;
						}
					}
					test.setSections(sections);
				}
				try {
					TestsManager.ajouterTestEtSections(test);
					message=ErrorManager.getMessage("Nouveau test inséré.", MessageType.information);
				} catch (SQLException e) {
					message=ErrorManager.getMessage("Impossibilité d'ajouter un test en base de donnée", MessageType.error);
					logger.severe("Impossibilité d'ajouter un test en base de donnée "+e.getMessage());
					e.printStackTrace();
				}
				break;
			case "modifier":
				
				break;
			case "supprimer":
				
				break;
			default:
				break;
			}
		}
		
		if (message != null) {
			request.setAttribute("message", message);
		}
			
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/formateur/administrationTests.jsp");
		rd.forward(request, response);
	}

}
