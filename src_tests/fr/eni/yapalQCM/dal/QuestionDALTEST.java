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

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class QuestionDALTEST implements ITEST {
	public static Question question;
	public static List<Question> questions = new ArrayList<Question>();
	public static QuestionDAL qd;
	
	/**
	 * Méthode en charge d'initialiser les variables de notre classe de test
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		qd = new QuestionDAL();
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
	 * Méthode en charge d'avoir 2 questions dans la table QUESTIONS avant chaque test
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 0 ; i<2 ; i++){
			question.setEnonce("maquestion");
			qd.add(question);
		}
	}

	/**
	 * Méthode en charge de vider la table QUESTIONS et de réinitiliser les identifiants après chaque test.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		questions = qd.getAll();
		for(Question question : questions)
		{
			qd.delete(question);
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
		int result = qd.getLength();
		assertEquals(2, result);
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetOne()
	 */
	@Override
	@Test
	public void testGetOne() throws SQLException {
		Question q = new Question();
		q.setId(3);
		Question qu = qd.getOne(q);
		if(qu!=null){
			fail("Récupération d'un mauvais élément (id innexistant en base de données)");
		}
		
		q.setId(2);
		
		int result = qd.getOne(q).getId();
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
		List<Question> listGA = new ArrayList<Question>();
		listGA = qd.getAll();
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
		question.setEnonce("maquestion");
		
		if(qd.add(question)==false){
			fail("l'insertion a retourné false");			
		}
		
		assertEquals(3, qd.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testUpdate()
	 */
	@Override
	@Test
	public void testUpdate() throws SQLException {
		Question q = new Question();
		q.setId(1);
		q.setEnonce("maquestion");
		
		if(qd.update(q)==false){
			fail("l'update a retourné false");			
		}
		
		q.setId(3);
		
		if(qd.update(q)==true){
			fail("l'udpate est réussi sur un mauvais identifiant");
		}
		
		assertEquals(2, qd.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testDelete()
	 */
	@Override
	@Test
	public void testDelete() throws SQLException {
		Question q = new Question();
		q.setId(3);
		if(qd.delete(q)==true){
			fail("La suppression a réussi sur un mauvais identifiant");
		}
		
		q.setId(1);
		
		if(qd.delete(q)==false){
			fail("La suppression a retourné false");
		}
		
		if(qd.delete(q)==true) {
			if(qd.getLength()<1){
				fail("La suppression a supprimé plus d'un élément");
			}
			else if(qd.getLength()>1){
				fail("La suppression n'a pas été effectuée malgré qu'elle retourne true");
			}
			else{
				assertEquals(1, qd.getLength());				
			}
		}
	}

}
