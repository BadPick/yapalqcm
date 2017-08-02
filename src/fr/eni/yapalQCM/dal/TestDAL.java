/**
 * @author wvignoles2017
 * @date 31 juil. 2017
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

import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.utils.YapalLogger;

/**
 * @author wvignoles2017
 * @date 31 juil. 2017
 * @version yapalqcm V1.0
 */
public class TestDAL implements IDAL<Test> {

	Logger logger = YapalLogger.getLogger(this.getClass().getName());

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getLength()
	 */
	@Override
	public int getLength() {
		logger.entering("TestDAL", "getLength");
		
		int resultat = 0;
		try(Connection cnx = DBConnection.getConnection()) {
			Statement requete = cnx.createStatement();
			ResultSet rs = requete.executeQuery(TestSQL.GET_LENGTH);
			if(rs.next()){
				resultat = rs.getInt("Total");
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
		}
		
		logger.exiting("TestDAL", "getLength");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getOne(int)
	 */
	@Override
	public Test getOne(Test t) throws SQLException {
		logger.entering("TestDAL", "getOne");
		
		Test test = null;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(TestSQL.GET_ONE);
			cmd.setInt(1, t.getId());
			ResultSet rs = cmd.executeQuery();		
			if(rs.next()){
				test = itemBuilder(rs);
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("TestDAL", "getOne");
		return test;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getAll()
	 */
	@Override
	public List<Test> getAll() throws SQLException {
		logger.entering("TestDAL", "getAll");
		
		ArrayList<Test> listeTests = new ArrayList<Test>();
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(TestSQL.GET_ALL);
			ResultSet rs = cmd.executeQuery();		
			while(rs.next()){
				listeTests.add(itemBuilder(rs));
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("TestDAL", "getAll");
		return listeTests;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#add(java.lang.Object)
	 */
	@Override
	public boolean add(Test t) throws SQLException {
		logger.entering("TestDAL", "add");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(TestSQL.ADD);
			cmd.setString(1, t.getNom());
			cmd.setFloat(2, t.getSeuilAcquis());
			cmd.setFloat(3, t.getSeuilEnCoursDacquisition());
			cmd.setLong(4, t.getDuree());		
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("TestDAL", "add");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#update(java.lang.Object)
	 */
	@Override
	public boolean update(Test t) throws SQLException {
		logger.entering("TestDAL", "update");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(TestSQL.UPDATE);
			cmd.setString(1, t.getNom());
			cmd.setFloat(2, t.getSeuilAcquis());
			cmd.setFloat(3, t.getSeuilEnCoursDacquisition());
			cmd.setLong(4, t.getDuree());
			cmd.setInt(5, t.getId());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("TestDAL", "update");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#delete(int)
	 */
	@Override
	public boolean delete(Test t) throws SQLException {
		logger.entering("TestDAL", "delete");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(TestSQL.DELETE);
			cmd.setInt(1, t.getId());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("TestDAL", "delete");
		return resultat;
	}
	
	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#itemBuilder(java.sql.ResultSet)
	 */
	@Override
	public Test itemBuilder(ResultSet rs) throws SQLException {
		Test t = new Test();
		t.setId(rs.getInt("idTest"));
		t.setNom(rs.getString("nomTest"));
		t.setSeuilAcquis(rs.getInt("seuilAcquis"));
		t.setSeuilEnCoursDacquisition(rs.getInt("seuilEnCoursAcquisition"));
		t.setDuree(rs.getLong("duree"));
		return t;
	}
}
