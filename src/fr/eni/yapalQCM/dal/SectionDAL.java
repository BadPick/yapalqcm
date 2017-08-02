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

import fr.eni.yapalQCM.bo.Section;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Theme;
import fr.eni.yapalQCM.utils.YapalLogger;

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class SectionDAL implements IDAL<Section> {

	Logger logger = YapalLogger.getLogger(this.getClass().getName());

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getLength()
	 */
	@Override
	public int getLength() {
		logger.entering("SectionDAL", "getLength");
		
		int resultat = 0;
		try(Connection cnx = DBConnection.getConnection()) {
			Statement requete = cnx.createStatement();
			ResultSet rs = requete.executeQuery("SELECT COUNT(*) AS Total FROM SECTIONS");
			if(rs.next()){
				resultat = rs.getInt("Total");
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
		}
		
		logger.exiting("SectionDAL", "getLength");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getOne(int)
	 */
	@Override
	public Section getOne(Section s) throws SQLException {
		logger.entering("SectionDAL", "getOne");
		
		Section section = null;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********(?,?)}");
			cmd.setInt(1, s.getTest().getId());
			cmd.setInt(2, s.getTheme().getId());
			ResultSet rs = cmd.executeQuery();		
			if(rs.next()){
				section = itemBuilder(rs);
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("SectionDAL", "getOne");
		return section;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getAll()
	 */
	@Override
	public List<Section> getAll() throws SQLException {
		logger.entering("SectionDAL", "getAll");
		
		ArrayList<Section> listeSections = new ArrayList<Section>();
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********}");
			ResultSet rs = cmd.executeQuery();		
			while(rs.next()){
				listeSections.add(itemBuilder(rs));
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("SectionDAL", "getAll");
		return listeSections;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#add(java.lang.Object)
	 */
	@Override
	public boolean add(Section s) throws SQLException {
		logger.entering("SectionDAL", "add");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********(?,?,?)}");
			cmd.setInt(1, s.getTest().getId());
			cmd.setInt(2, s.getTheme().getId());
			cmd.setInt(3, s.getNbQuestions());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("SectionDAL", "add");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#update(java.lang.Object)
	 */
	@Override
	public boolean update(Section s) throws SQLException {
		logger.entering("SectionDAL", "update");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********(?,?,?)}");
			cmd.setInt(1, s.getTest().getId());
			cmd.setInt(2, s.getTheme().getId());
			cmd.setInt(3, s.getNbQuestions());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("SectionDAL", "update");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#delete(int)
	 */
	@Override
	public boolean delete(Section s) throws SQLException {
		logger.entering("SectionDAL", "delete");
		
		boolean resultat = false;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall("{CALL **********(?,?)}");
			cmd.setInt(1, s.getTest().getId());
			cmd.setInt(2, s.getTheme().getId());
			resultat = (cmd.executeUpdate()>0);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("SectionDAL", "delete");
		return resultat;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#itemBuilder(java.sql.ResultSet)
	 */
	@Override
	public Section itemBuilder(ResultSet rs) throws SQLException {
		Section s = new Section();
		Test test = new Test();
		test.setId(rs.getInt("idTest"));
		Theme t = new Theme();
		t.setId(rs.getInt("idTheme"));
		s.setNbQuestions(rs.getInt("nombreQuestions"));
		s.setTheme(t);
		return s;
	}

}
