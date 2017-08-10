/**
 * @author wvignoles2017
 * @date 31 juil. 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.eni.yapalQCM.bo.Question;
import fr.eni.yapalQCM.bo.Reponse;
import fr.eni.yapalQCM.bo.Section;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Theme;
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
			Statement cmd = cnx.createStatement();
			ResultSet rs =cmd.executeQuery(TestSQL.GET_ALL);		
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

	public List<Test> getTests() throws SQLException{
		logger.entering("TestDAL","getTests");
		
		List<Test> liste = new ArrayList<Test>();
		try(Connection cnx = DBConnection.getConnection()){
			Statement cmd = cnx.createStatement();
			ResultSet rs = cmd.executeQuery(TestSQL.GET_TESTS);
			liste=itemBuilderWithoutSuestion(rs);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		logger.exiting("TestDAL", "getTests");
		return liste;
		
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
		t.setSeuilAcquis(rs.getFloat("seuilAcquis"));
		t.setSeuilEnCoursDacquisition(rs.getFloat("seuilEnCoursAcquisition"));
		t.setDuree(rs.getLong("duree"));
		return t;
	}

	public ArrayList<Test> getManyBy(int idCandidat) throws SQLException {
		logger.entering("TestDAL", "getManyBy");
		ArrayList<Test> tests = new ArrayList<Test>();	
		Test test = null;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(TestSQL.GET_MANY_BY_UTILISATEUR);
			cmd.setInt(1, idCandidat);
			ResultSet rs = cmd.executeQuery();		
			if (rs.next()) {
				tests = itemBuilderWithoutSuestion(rs);	
			}
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("TestDAL", "getManyBy");
		
		return tests;
	}
	
	private ArrayList<Test> itemBuilderWithoutSuestion(ResultSet rs) throws SQLException {
		ArrayList<Test> tests = new ArrayList<Test>();
		
		Test test = null;
		Section section = null;
		Theme theme = null;
		int lastThemeId = 0;
		int lastTestId = 0;
		while (rs.next()) {
			
			if (lastTestId!= rs.getInt("idTest")) {
				lastTestId = rs.getInt("idTest");
				test = new Test();
				test.setId(rs.getInt("idTest"));
				test.setNom(rs.getString("nomTest"));
				test.setSeuilAcquis(rs.getInt("seuilAcquis"));
				test.setSeuilEnCoursDacquisition(rs.getInt("seuilEnCoursAcquisition"));
				test.setDuree(rs.getLong("duree"));	
				tests.add(test);
			}
			if (lastThemeId != rs.getInt("idTheme")) {
				lastThemeId = rs.getInt("idTheme");
				
				theme = new Theme();
				theme.setId(rs.getInt("idTheme"));
				theme.setNom(rs.getString("nom"));
				
				section = new Section();
				section.setNbQuestions(rs.getInt("nombreQuestions"));
				section.setTheme(theme);
				
				test.addSection(section);
			}
		}
		return tests;
	}

	public Test getOne(int idTest) throws SQLException {
		logger.entering("TestDAL", "getOne");
		
		Test test = null;
		try(Connection cnx = DBConnection.getConnection()) {
			CallableStatement cmd = cnx.prepareCall(TestSQL.GET_ONE_BY_ID);
			cmd.setInt(1, idTest);
			ResultSet rs = cmd.executeQuery();					
			test = itemBuilderComplet(rs);
		} catch (SQLException e) {
			logger.severe("Erreur : " + e.getMessage());
			throw e;
		}
		
		logger.exiting("TestDAL", "getOne");
		return test;
	}

	private Test itemBuilderComplet(ResultSet rs) throws SQLException {
		Test test = null;
		Section section = null;
		Theme theme = null;
		Question question = null;
		Reponse reponse = null;
		int lastThemeId = 0;
		int lastQuestionId = 0;
		boolean addQuestion;
		while (rs.next()) {
			addQuestion = false;
			if (test==null) {
				test = new Test();
				test.setId(rs.getInt("idTest"));
				test.setNom(rs.getString("nomTest"));
				test.setSeuilAcquis(rs.getInt("seuilAcquis"));
				test.setSeuilEnCoursDacquisition(rs.getInt("seuilEnCoursAcquisition"));
				test.setDuree(rs.getLong("duree"));			
			}
			if (lastThemeId != rs.getInt("idTheme")) {
				lastThemeId = rs.getInt("idTheme");
				
				theme = new Theme();
				theme.setId(rs.getInt("idTheme"));
				theme.setNom(rs.getString("nom"));
				
				section = new Section();
				section.setNbQuestions(rs.getInt("nombreQuestions"));
				section.setTheme(theme);
				
				test.addSection(section);
			}

				if (lastQuestionId != rs.getInt("idQuestion")) {
					addQuestion = true;
					lastQuestionId = rs.getInt("idQuestion");
							
					question = new Question();
					question.setId(rs.getInt("idQuestion"));
					question.setEnonce(rs.getString("enonce"));
									
				}
					
				reponse = new Reponse();
				reponse.setId(rs.getInt("idReponse"));
				reponse.setEnonce(rs.getString("repEnonce"));
				reponse.setCorrect(rs.getBoolean("isCorrect"));
				
				question.addReponse(reponse);
				
				if(addQuestion){
					theme.getQuestions().add(question);				
				}
			}			
		return test;
	}
}
