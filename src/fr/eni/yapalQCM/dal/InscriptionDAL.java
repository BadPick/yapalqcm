/**
 * @author wvignoles2017
 * @date 1 août 2017
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

import fr.eni.yapalQCM.bo.Inscription;
import fr.eni.yapalQCM.bo.Session;
import fr.eni.yapalQCM.bo.Utilisateur;
import fr.eni.yapalQCM.utils.YapalLogger;

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class InscriptionDAL implements IDAL<Inscription> {

	Logger logger = YapalLogger.getLogger(this.getClass().getName());

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getLength()
	 */
	@Override
	public int getLength() {
		logger.entering("InscriptionDAL", "getLength");
		
		int resultat = 0;
		try(Connection cnx = DBConnection.getConnection()) {
			Statement requete = cnx.createStatement();
			ResultSet rs = requete.executeQuery("SELECT COUNT(*) AS Total FROM INSCRIPTIONS");
			if(rs.next()){
				resultat = rs.getInt("Total");
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
		}
		
		logger.exiting("InscriptionDAL", "getLength");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getOne(int)
	 */
	@Override
	public Inscription getOne(Inscription i) throws SQLException {
		logger.entering("InscriptionDAL", "getOne");
		
		Inscription inscription = null;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********(?,?)}");
			cmd.setInt(1, i.getCandidat().getId());
			cmd.setInt(2, i.getSession().getId());
			ResultSet rs = cmd.executeQuery();		
			if(rs.next()){
				inscription = itemBuilder(rs);
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("InscriptionDAL", "getOne");
		return inscription;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getAll()
	 */
	@Override
	public List<Inscription> getAll() throws SQLException {
		logger.entering("InscriptionDAL", "getAll");
		
		ArrayList<Inscription> listeInscriptions = new ArrayList<Inscription>();
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********}");
			ResultSet rs = cmd.executeQuery();		
			while(rs.next()){
				listeInscriptions.add(itemBuilder(rs));
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("InscriptionDAL", "getAll");
		return listeInscriptions;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#add(java.lang.Object)
	 */
	@Override
	public boolean add(Inscription i) throws SQLException {
		logger.entering("InscriptionDAL", "add");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********(?,?)}");
			cmd.setInt(1, i.getCandidat().getId());
			cmd.setInt(2, i.getSession().getId());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("InscriptionDAL", "add");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#update(java.lang.Object)
	 */
	@Override
	public boolean update(Inscription i) throws SQLException {
		logger.entering("InscriptionDAL", "update");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********(?,?)}");
			cmd.setInt(1, i.getCandidat().getId());
			cmd.setInt(2, i.getSession().getId());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("InscriptionDAL", "update");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#delete(int)
	 */
	@Override
	public boolean delete(Inscription i) throws SQLException {
		logger.entering("InscriptionDAL", "delete");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********(?,?)}");
			cmd.setInt(1, i.getCandidat().getId());
			cmd.setInt(2, i.getSession().getId());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("InscriptionDAL", "delete");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#itemBuilder(java.sql.ResultSet)
	 */
	@Override
	public Inscription itemBuilder(ResultSet rs) throws SQLException {
		Inscription i = new Inscription();
		Utilisateur u = new Utilisateur();
		u.setId(rs.getInt("idUser"));
		Session s = new Session();
		s.setId(rs.getInt("idSession"));
		i.setCandidat(u);
		i.setSession(s);
		return i;
	}

}
