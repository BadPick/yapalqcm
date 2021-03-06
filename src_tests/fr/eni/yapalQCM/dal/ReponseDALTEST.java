/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.eni.yapalQCM.bo.Question;
import fr.eni.yapalQCM.bo.Reponse;

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class ReponseDALTEST implements ITEST {

	public static Reponse reponse;
	public static ReponseDAL rd;
	public static List<Reponse> reponses = new ArrayList<Reponse>();
	public static Question question;
	public static QuestionDAL qd;
	public static List<Question> questions = new ArrayList<Question>();
	
	/**
	 * Méthode en charge d'initialiser les variables de notre classe de test
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rd = new ReponseDAL();
		qd = new QuestionDAL();
		reponse = new Reponse();
		question = new Question();
	}

	/**
	 * Méthode en charge de
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Méthode en charge d'avoir 2 reponses dans la table REPONSES avant chaque test
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 1 ; i<3 ; i++){
			reponse.setEnonce("mareponse");
			reponse.setCorrect(true);
			question.setId(i);
			question.setEnonce("maquestion");
			reponse.setQuestion(question);
			qd.add(question);
			rd.add(reponse);
		}
	}

	/**
	 * Méthode en charge de vider la table REPONSES et de réinitiliser les identifiants après chaque test.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		reponses = rd.getAll();
		for(Reponse reponse : reponses)
		{
			rd.delete(reponse);
		}
		questions = qd.getAll();
		for(Question question : questions)
		{
			qd.delete(question);
		}
		
		try(Connection cnx = DBConnection.getConnection()) {
			Statement cmd = cnx.createStatement();
			cmd.execute("DBCC CHECKIDENT ('REPONSES', RESEED, 0)");
		} catch (SQLException e) {
			System.out.println("Problème de réinitialisation de l'auto-incrément de la table REPONSES");
		}
		try(Connection cnx = DBConnection.getConnection()) {
			Statement cmd = cnx.createStatement();
			cmd.execute("DBCC CHECKIDENT ('QUESTIONS', RESEED, 0)");
		} catch (SQLException e) {
			System.out.println("Problème de réinitialisation de l'auto-incrément de la table QUESTIONS");
		}
	}

	
	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetLength()
	 */
	@Override
	@Test
	public void testGetLength() {
		int result = rd.getLength();
		assertEquals(2, result);
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetOne()
	 */
	@Override
	@Test
	public void testGetOne() throws SQLException {
		reponse.setId(3);
		Reponse re = rd.getOne(reponse);
		if(re!=null){
			fail("Récupération d'un mauvais élément (id innexistant en base de données)");
		}
		
		reponse.setId(2);
		int result = rd.getOne(reponse).getId();
		if(result!=2){
			fail("L'élément ciblé n'a pas été récupéré");
		}
		assertEquals(2, result);
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetAll()
	 */
	@Override
	@Test
	public void testGetAll() throws SQLException {
		List<Reponse> listGA = new ArrayList<Reponse>();
		listGA = rd.getAll();
		if(listGA.size()!=2){
			fail("La requête n'a pas récupéré tous les éléments de la table");
		}
		else{
			assertEquals(2, listGA.size());
		}
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testAdd()
	 */
	@Override
	@Test
	public void testAdd() throws SQLException {
		if(rd.add(reponse)==false){
			fail("l'insertion a retourné false");			
		}
		
		assertEquals(3, rd.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testUpdate()
	 */
	@Override
	@Test
	public void testUpdate() throws SQLException {
		reponse.setId(2);

		if(rd.update(reponse)==false){
			fail("l'update a retourné false");			
		}
		
		reponse.setId(3);
		
		if(rd.update(reponse)==true){
			fail("l'udpate est réussi sur un mauvais identifiant");
		}
		
		assertEquals(2, rd.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testDelete()
	 */
	@Override
	@Test
	public void testDelete() throws SQLException {
		reponse.setId(3);
		
		if(rd.delete(reponse)==true){
			fail("La suppression a réussi sur un mauvais identifiant");
		}
		
		reponse.setId(2);
		
		if(rd.delete(reponse)==false){
			fail("La suppression a retourné false");
		}
		
		if(rd.delete(reponse)==true) {
			if(rd.getLength()<1){
				fail("La suppression a supprimé plus d'un élément");
			}
			else if(rd.getLength()>1){
				fail("La suppression n'a pas été effectuée malgré qu'elle retourne true");
			}
			else{
				assertEquals(1, rd.getLength());				
			}
		}
	}

}
