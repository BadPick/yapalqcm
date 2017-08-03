/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.eni.yapalQCM.bo.Session;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.utils.YapalLogger;

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class SessionDAL implements IDAL<Session> {

	Logger logger = YapalLogger.getLogger(this.getClass().getName());

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getLength()
	 */
	@Override
	public int getLength() {
		logger.entering("SessionDAL", "getLength");
		
		int resultat = 0;
		try(Connection cnx = DBConnection.getConnection()) {
			Statement requete = cnx.createStatement();
			ResultSet rs = requete.executeQuery(SessionSQL.GET_LENGTH);
			if(rs.next()){
				resultat = rs.getInt("Total");
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
		}
		
		logger.exiting("SessionDAL", "getLength");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getOne(int)
	 */
	@Override
	public Session getOne(Session s) throws SQLException {
		logger.entering("SessionDAL", "getOne");
		
		Session session = null;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(SessionSQL.GET_ONE);
			cmd.setInt(1, s.getId());
			ResultSet rs = cmd.executeQuery();		
			if(rs.next()){
				session = itemBuilder(rs);
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("SessionDAL", "getOne");
		return session;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getAll()
	 */
	@Override
	public List<Session> getAll() throws SQLException {
		logger.entering("SessionDAL", "getAll");
		
		ArrayList<Session> listeSessions = new ArrayList<Session>();
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(SessionSQL.GET_ALL);
			ResultSet rs = cmd.executeQuery();		
			while(rs.next()){
				listeSessions.add(itemBuilder(rs));
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("SessionDAL", "getAll");
		return listeSessions;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#add(java.lang.Object)
	 */
	@Override
	public boolean add(Session s) throws SQLException {
		logger.entering("SessionDAL", "add");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			PreparedStatement cmd = cnx.prepareStatement(SessionSQL.ADD, new String[] {"PRODUCT_ID"});
			cmd.setDate(1, (Date) s.getDate());
			cmd.setInt(2, s.getNbPlaces());
			resultat = (cmd.executeUpdate()>0);
			ResultSet rs = cmd.getGeneratedKeys();
			int productId = 0;
			if (rs.next()) {
			   productId = rs.getInt(1);
			   for(Test test : s.getTests()){
				   CallableStatement cmdTest = cnx.prepareCall(SessionSQL.ADDTESTSESSION);
				   cmdTest.setInt(1, productId);
				   cmdTest.setInt(2, test.getId());
				   cmdTest.setTime(3, test.getHeure());
				   cmdTest.setBoolean(4, test.isBegin());
				   cmdTest.setLong(5, test.getTempsEcoule());
				   resultat = (cmdTest.executeUpdate()>0);
			   }
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("SessionDAL", "add");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#update(java.lang.Object)
	 */
	@Override
	public boolean update(Session s) throws SQLException {
		logger.entering("SessionDAL", "update");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(SessionSQL.UPDATE);
			cmd.setDate(1, (Date) s.getDate());
			cmd.setInt(2, s.getNbPlaces());
			cmd.setInt(3, s.getId());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("SessionDAL", "update");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#delete(int)
	 */
	@Override
	public boolean delete(Session s) throws SQLException {
		logger.entering("SessionDAL", "delete");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			for(Test test : s.getTests()){
			   CallableStatement cmdTest = cnx.prepareCall(SessionSQL.DELETETESTSESSION);
			   cmdTest.setInt(1, s.getId());
			   cmdTest.setInt(2, test.getId());
			   resultat = (cmdTest.executeUpdate()>0);
		    }
			CallableStatement cmd = cnx.prepareCall(SessionSQL.DELETE);
			cmd.setInt(1, s.getId());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("SessionDAL", "delete");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#itemBuilder(java.sql.ResultSet)
	 */
	@Override
	public Session itemBuilder(ResultSet rs) throws SQLException {
		Session s = new Session();
		s.setId(rs.getInt("idSession"));
		s.setDate(rs.getDate("date"));
		s.setNbPlaces(rs.getInt("nombrePlaces"));
		return s;
	}

}
