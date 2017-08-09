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
import fr.eni.yapalQCM.bll.TestsManager;
import fr.eni.yapalQCM.bll.ThemesManager;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Theme;
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
		try {
			List<Test> listeTests = TestsManager.getTests();
			List<Theme> listeThemes = ThemesManager.getThemes();
			request.setAttribute("listeTests", listeTests);
			request.setAttribute("listeThemes", listeThemes);
		} catch (SQLException e) {
			message=ErrorManager.getMessage("Impossibilité de récupérer la liste des Tests en base.", MessageType.error);
			logger.severe("Erreur de récupération de la date et/ou de l'heure "+e.getMessage());
			e.printStackTrace();
		}	
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/formateur/administrationTests.jsp");
		rd.forward(request, response);
	}

}
