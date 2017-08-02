/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.eni.yapalQCM.bo.Role;
import fr.eni.yapalQCM.bo.Utilisateur;
import fr.eni.yapalQCM.utils.YapalLogger;

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class UtilisateurDAL implements IDAL<Utilisateur> {
	
	Logger logger = YapalLogger.getLogger(this.getClass().getName());

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getLength()
	 */
	@Override
	public int getLength() {
		logger.entering("UtilisateurDAL", "getLength");
		
		int resultat = 0;
		try(Connection cnx = DBConnection.getConnection()) {
			Statement requete = cnx.createStatement();
			ResultSet rs = requete.executeQuery("SELECT COUNT(*) AS Total FROM UTILISATEURS");
			if(rs.next()){
				resultat = rs.getInt("Total");
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
		}
		
		logger.exiting("UtilisateurDAL", "getLength");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getOne(int)
	 */
	@Override
	public Utilisateur getOne(Utilisateur u) throws SQLException {
		logger.entering("UtilisateurDAL", "getOne");
		
		Utilisateur utilisateur = null;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********(?)}");
			cmd.setInt(1, u.getId());
			ResultSet rs = cmd.executeQuery();		
			if(rs.next()){
				utilisateur = itemBuilder(rs);
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("UtilisateurDAL", "getOne");
		return utilisateur;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getAll()
	 */
	@Override
	public List<Utilisateur> getAll() throws SQLException {
		logger.entering("UtilisateurDAL", "getAll");
		
		ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********}");
			ResultSet rs = cmd.executeQuery();		
			while(rs.next()){
				listeUtilisateurs.add(itemBuilder(rs));
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("UtilisateurDAL", "getAll");
		return listeUtilisateurs;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#add(java.lang.Object)
	 */
	@Override
	public boolean add(Utilisateur u) throws SQLException {
		logger.entering("UtilisateurDAL", "add");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********(?,?,?,?,?)}");
			cmd.setString(1, u.getNom());
			cmd.setString(2, u.getPrenom());
			cmd.setString(3, u.getPassword());
			cmd.setDate(4, (Date) u.getDateDeNaissance());
			cmd.setString(5, u.getEmail());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("UtilisateurDAL", "add");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#update(java.lang.Object)
	 */
	@Override
	public boolean update(Utilisateur u) throws SQLException {
		logger.entering("UtilisateurDAL", "update");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********(?,?,?,?,?,?)}");
			cmd.setInt(1, u.getId());
			cmd.setString(2, u.getNom());
			cmd.setString(3, u.getPrenom());
			cmd.setString(4, u.getPassword());
			cmd.setDate(5, (Date) u.getDateDeNaissance());
			cmd.setString(6, u.getEmail());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("UtilisateurDAL", "update");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#delete(int)
	 */
	@Override
	public boolean delete(Utilisateur u) throws SQLException {
		logger.entering("UtilisateurDAL", "delete");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********(?)}");
			cmd.setInt(1, u.getId());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("UtilisateurDAL", "delete");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#itemBuilder(java.sql.ResultSet)
	 */
	@Override
	public Utilisateur itemBuilder(ResultSet rs) throws SQLException {
		Utilisateur u = new Utilisateur();
		u.setId(rs.getInt("idUser"));
		u.setNom(rs.getString("nom"));
		u.setPrenom(rs.getString("prenom"));
		u.setPassword(rs.getString("password"));
		u.setEmail(rs.getString("adresseEmail"));
		u.setDateDeNaissance(rs.getDate("dateNaissance"));
		Role role = new Role();
		role.setId(rs.getInt("idRole"));
		return u;
	}

}
