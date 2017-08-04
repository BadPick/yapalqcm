/**
 * @author wvignoles2017
 * @date 1 aoÃ»t 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.eni.yapalQCM.bo.Resultat;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Utilisateur;
import fr.eni.yapalQCM.utils.YapalLogger;

/**
 * @author wvignoles2017
 * @date 1 aoÃ»t 2017
 * @version yapalqcm V1.0
 */
public class ResultatDAL implements IDAL<Resultat> {

	Logger logger = YapalLogger.getLogger(this.getClass().getName());

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getLength()
	 */
	@Override
	public int getLength() {
		logger.entering("ResultatDAL", "getLength");
		
		int resultat = 0;
		try(Connection cnx = DBConnection.getConnection()) {
			Statement requete = cnx.createStatement();
			ResultSet rs = requete.executeQuery(ResultatSQL.GET_LENGTH);
			if(rs.next()){
				resultat = rs.getInt("Total");
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
		}
		
		logger.exiting("ResultatDAL", "getLength");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getOne(int)
	 */
	@Override
	public Resultat getOne(Resultat r) throws SQLException {
		logger.entering("ResultatDAL", "getOne");
		
		Resultat resultat = null;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(ResultatSQL.GET_ONE);
			cmd.setInt(1, r.getTest().getId());
			cmd.setInt(2, r.getSession().getId());
			cmd.setInt(3, r.getCandidat().getId());
			ResultSet rs = cmd.executeQuery();		
			if(rs.next()){
				resultat = itemBuilder(rs);
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("ResultatDAL", "getOne");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getAll()
	 */
	@Override
	public List<Resultat> getAll() throws SQLException {
		logger.entering("ResultatDAL", "getAll");
		
		ArrayList<Resultat> listeResultats = new ArrayList<Resultat>();
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(ResultatSQL.GET_ALL);
			ResultSet rs = cmd.executeQuery();		
			while(rs.next()){
				listeResultats.add(itemBuilder(rs));
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("ResultatDAL", "getAll");
		return listeResultats;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#add(java.lang.Object)
	 */
	@Override
	public boolean add(Resultat r) throws SQLException {
		logger.entering("ResultatDAL", "add");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(ResultatSQL.ADD);
			cmd.setInt(1, r.getCandidat().getId());	
			cmd.setInt(2, r.getTest().getId());
			cmd.setInt(3, r.getSession().getId());
			cmd.setLong(4, r.getTempsEcoule());
			cmd.setFloat(5, r.getSeuilObtenu());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("ResultatDAL", "add");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#update(java.lang.Object)
	 */
	@Override
	public boolean update(Resultat r) throws SQLException {
		logger.entering("ResultatDAL", "update");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(ResultatSQL.UPDATE);
			cmd.setLong(1, r.getTempsEcoule());
			cmd.setFloat(2, r.getSeuilObtenu());
			cmd.setInt(3, r.getCandidat().getId());	
			cmd.setInt(4, r.getTest().getId());
			cmd.setInt(5, r.getSession().getId());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("ResultatDAL", "update");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#delete(int)
	 */
	@Override
	public boolean delete(Resultat r) throws SQLException {
		logger.entering("ResultatDAL", "delete");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(ResultatSQL.DELETE);
			cmd.setInt(1, r.getCandidat().getId());	
			cmd.setInt(2, r.getTest().getId());
			cmd.setInt(3, r.getSession().getId());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("ResultatDAL", "delete");
		return resultat;
	}
	/**
	 * Méthode permettant de récupérer la liste des résultats correspondant à
	 * un candidat.
	 * @param user
	 * @return List<Resultat>
	 * @throws SQLException
	 */
	@SuppressWarnings("null")
	public ArrayList<Resultat> getAllByUtilisateur(Utilisateur user) throws SQLException{
		ArrayList<Resultat> listeResultats=null; 
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(ResultatSQL.GET_MANY_BY_UTILISATEUR);
			cmd.setInt(1, user.getId());	
			ResultSet rs = cmd.executeQuery();
			if (!rs.next() ) {
				logger.severe("Pas d'enregistrement résultats.") ;
			} else {
				listeResultats=new ArrayList<Resultat>();
			    do {
			    	listeResultats.add(itemBuilder(rs));
			    } while (rs.next());
			}			
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		return listeResultats;
	}
	/**
	 * Méthode permettant de récupérer un résultat pour un utilisateur et un test donné.
	 * @param user
	 * @param test
	 * @return Resultat
	 * @throws SQLException
	 */
	public Resultat getOneByUtilisateur(Utilisateur user, Test test) throws SQLException{
		Resultat resultat = null;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(ResultatSQL.GET_MANY_BY_UTILISATEUR_AND_TEST);
			cmd.setInt(1, user.getId());
			cmd.setInt(2, test.getId());
			ResultSet rs = cmd.executeQuery();
			while(rs.next()){
				resultat=itemBuilder(rs);
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		return resultat;
	}
	
	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#itemBuilder(java.sql.ResultSet)
	 */
	@Override
	public Resultat itemBuilder(ResultSet rs) throws SQLException {
		Resultat r = new Resultat();
		r.setTempsEcoule(rs.getLong("tempsEcoule"));
		r.setSeuilObtenu(rs.getFloat("seuilObtenu"));
		return r;
	}
}
