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
import fr.eni.yapalQCM.bll.ThemesManager;
import fr.eni.yapalQCM.bo.Theme;
import fr.eni.yapalQCM.utils.Message;
import fr.eni.yapalQCM.utils.MessageType;
import fr.eni.yapalQCM.utils.YapalLogger;

/**
 * Servlet implementation class FormateurAdministrationThemes
 */
@WebServlet("/Formateur/Administration/Themes")
public class FormateurAdministrationThemes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = YapalLogger.getLogger(this.getClass().getName());
	private Message message = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormateurAdministrationThemes() {
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
		
		// R�cup�ration de la liste de th�me en base de donn�e.
		try {
			List<Theme> themes = ThemesManager.getThemes();
			if (themes==null) {
				message = ErrorManager.getMessage("Pas de th�mes en base de donn�e",MessageType.information);
			}
			request.setAttribute("themes", themes);
			
		} catch (SQLException e) {
			logger.severe("erreur requete SQL ThemeDAL : "+e.getMessage());
			e.printStackTrace();
		}
		
		// Administration des th�mes : suppresion, modification, suppression.
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/formateur/administrationThemes.jsp");
		
		rd.forward(request, response);
	}
	
}
