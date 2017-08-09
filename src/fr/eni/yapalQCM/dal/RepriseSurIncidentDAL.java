/**
 * @author wvignoles2017
 * @date 9 août 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import fr.eni.yapalQCM.bo.QuestionEnCoursWrapper;
import fr.eni.yapalQCM.bo.ReponseEnCoursWrapper;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Utilisateur;
import fr.eni.yapalQCM.utils.YapalLogger;

/**
 * @author wvignoles2017
 * @date 9 août 2017
 * @version yapalqcm V1.0
 */
public class RepriseSurIncidentDAL {
	
	Logger logger = YapalLogger.getLogger(this.getClass().getName());
	
	public boolean addReponse(ReponseEnCoursWrapper r, Utilisateur u, Test t, QuestionEnCoursWrapper q) throws SQLException {
		logger.entering("RepriseSurIncidentDAL", "addReponse");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(RepriseSurIncidentSQL.ADDREPONSE);
			cmd.setInt(1, u.getId());	
			cmd.setInt(2, t.getId());
			cmd.setInt(3, q.getIdQuestion());
			cmd.setInt(4, r.getIdReponse());
			cmd.setBoolean(5, r.isChecked());
			cmd.setBoolean(6, q.isMarquageQuestion());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("RepriseSurIncidentDAL", "addReponse");
		return resultat;
	}
	
	public boolean deleteReponse(ReponseEnCoursWrapper r, Utilisateur u, Test t, QuestionEnCoursWrapper q) throws SQLException {
		logger.entering("RepriseSurIncidentDAL", "deleteReponse");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(RepriseSurIncidentSQL.DELETEREPONSE);
			cmd.setInt(1, u.getId());	
			cmd.setInt(2, t.getId());
			cmd.setInt(3, q.getIdQuestion());
			cmd.setInt(4, r.getIdReponse());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("RepriseSurIncidentDAL", "deleteReponse");
		return resultat;
	}
	
	public boolean addTest(Utilisateur u, Test t, long chrono) throws SQLException {
		logger.entering("RepriseSurIncidentDAL", "addTest");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(RepriseSurIncidentSQL.ADDTEST);
			cmd.setInt(1, u.getId());	
			cmd.setInt(2, t.getId());
			cmd.setLong(3, chrono);
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("RepriseSurIncidentDAL", "addTest");
		return resultat;
	}
	
	public boolean deleteTest(Utilisateur u, Test t) throws SQLException {
		logger.entering("RepriseSurIncidentDAL", "deleteTest");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(RepriseSurIncidentSQL.DELETETEST);
			cmd.setInt(1, u.getId());	
			cmd.setInt(2, t.getId());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("RepriseSurIncidentDAL", "deleteTest");
		return resultat;
	}
	
	// TODO UPDATETEST
	// TODO GETONETEST
}
