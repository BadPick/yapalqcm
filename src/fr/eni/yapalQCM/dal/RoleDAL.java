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

import fr.eni.yapalQCM.bo.Role;
import fr.eni.yapalQCM.utils.YapalLogger;

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class RoleDAL implements IDAL<Role> {

	Logger logger = YapalLogger.getLogger(this.getClass().getName());
	
	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getLength()
	 */
	@Override
	public int getLength() {
		logger.entering("RoleDAL", "getLength");
		
		int resultat = 0;
		try(Connection cnx = DBConnection.getConnection()) {
			Statement requete = cnx.createStatement();
			ResultSet rs = requete.executeQuery(RoleSQL.GET_LENGTH);
			if(rs.next()){
				resultat = rs.getInt("Total");
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
		}
		
		logger.exiting("RoleDAL", "getLength");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getOne(int)
	 */
	@Override
	public Role getOne(Role r) throws SQLException {
		logger.entering("RoleDAL", "getOne");
		
		Role role = null;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(RoleSQL.GET_ONE);
			cmd.setInt(1, r.getId());
			ResultSet rs = cmd.executeQuery();		
			if(rs.next()){
				role = itemBuilder(rs);
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("RoleDAL", "getOne");
		return role;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getAll()
	 */
	@Override
	public List<Role> getAll() throws SQLException {
		logger.entering("RoleDAL", "getAll");
		
		ArrayList<Role> listeRoles = new ArrayList<Role>();
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(RoleSQL.GET_ALL);
			ResultSet rs = cmd.executeQuery();		
			while(rs.next()){
				listeRoles.add(itemBuilder(rs));
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("RoleDAL", "getAll");
		return listeRoles;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#add(java.lang.Object)
	 */
	@Override
	public boolean add(Role r) throws SQLException {
		logger.entering("RoleDAL", "add");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(RoleSQL.ADD);
			cmd.setString(1, r.getName());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("RoleDAL", "add");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#update(java.lang.Object)
	 */
	@Override
	public boolean update(Role r) throws SQLException {
		logger.entering("RoleDAL", "update");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(RoleSQL.UPDATE);
			cmd.setString(1, r.getName());
			cmd.setInt(2, r.getId());			
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("RoleDAL", "update");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#delete(int)
	 */
	@Override
	public boolean delete(Role r) throws SQLException {
		logger.entering("RoleDAL", "delete");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(RoleSQL.DELETE);
			cmd.setInt(1, r.getId());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("RoleDAL", "delete");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#itemBuilder(java.sql.ResultSet)
	 */
	@Override
	public Role itemBuilder(ResultSet rs) throws SQLException {
		Role role = new Role();
		role.setId(rs.getInt("idRole"));
		role.setName(rs.getString("role"));
		return role;
	}

}
