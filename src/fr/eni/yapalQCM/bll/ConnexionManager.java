package fr.eni.yapalQCM.bll;

import java.sql.SQLException;
import java.util.logging.Logger;

import fr.eni.yapalQCM.bo.Role;
import fr.eni.yapalQCM.bo.Utilisateur;
import fr.eni.yapalQCM.dal.RoleDAL;
import fr.eni.yapalQCM.dal.UtilisateurDAL;
import fr.eni.yapalQCM.utils.YapalLogger;

public class ConnexionManager {
	Logger logger = YapalLogger.getLogger(this.getClass().getName());
	/**
	 * Méthode appelant la base de donnée pour vérifier la présence d'un 
	 * Utilisateur.
	 * @param password 
	 * @param login 
	 * @return Utilisateur
	 * @throws SQLException 
	 */
	public Utilisateur getConnexion(String login, String password) throws SQLException {
		logger.entering("ConnexionManager", "getConnexion");
		
		Utilisateur user = null;
		Role role = new Role();
		UtilisateurDAL utilDal = new UtilisateurDAL();
		RoleDAL roleDal = new RoleDAL();
		
		user = utilDal.getConnexion(login, password);	
		if (user!=null) {
			role=user.getRole();			
			try {
				user.setRole(roleDal.getOne(role));
			} catch (SQLException e) {
				logger.severe("Erreur : " + e.getMessage());
				throw e;
			}			
		}
		logger.exiting("ConnexionManager", "getConnexion");
		return user;
	}

}
