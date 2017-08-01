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

import fr.eni.yapalQCM.bo.Question;
import fr.eni.yapalQCM.bo.Reponse;

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class QuestionDALTEST implements ITEST {
	public static Question question;
	public static Reponse reponse;
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
		reponse = new Reponse();
	}

	/**
	 * Méthode en charge de
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Méthode en charge d'avoir 2 tests dans la table TESTS avant chaque test
	 * et
	 * de créer 1 session et 2 TestSession
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 0 ; i<2 ; i++){
			question.setEnonce("maquestion");
			for(int j = 0 ; j<2 ; j++){
				Reponse r = new Reponse();
				r.setEnonce("mareponse");
				r.setCorrect(true);
				question.getReponses().add(r);
			}
			qd.add(question);
		}
	}

	/**
	 * Méthode en charge de vider la table TESTS et de réinitiliser les identifiants après chaque test.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		questions = qd.getAll();
		for(Question question : questions)
		{
			qd.delete(question.getId());
		}
		
		try(Connection cnx = DBConnection.getConnection()) {
			Statement cmd = cnx.createStatement();
			cmd.execute("DBCC CHECKIDENT ('QUESTION', RESEED, 0)");
		} catch (SQLException e) {
			System.out.println("Problème de réinitialisation de l'auto-incrément de la table QUESTION");;
		}
		try(Connection cnx = DBConnection.getConnection()) {
			Statement cmd = cnx.createStatement();
			cmd.execute("DBCC CHECKIDENT ('REPONSE', RESEED, 0)");
		} catch (SQLException e) {
			System.out.println("Problème de réinitialisation de l'auto-incrément de la table REPONSE");;
		}
	}

	
	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetLength()
	 */
	@Override
	public void testGetLength() {
		int result = qd.getLength();
		assertEquals(2, result);
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetOne()
	 */
	@Override
	public void testGetOne() {
		int result = qd.getOne(3).getId();
		if(result>0){
			fail("Récupération d'un mauvais élément (id innexistant en base de données)");
		}
		
		result = qd.getOne(2).getId();
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
	public void testGetAll() {
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
	public void testAdd() {
		question.setEnonce("maquestion");
		for(int j = 0 ; j<2 ; j++){
			Reponse r = new Reponse();
			r.setEnonce("mareponse");
			r.setCorrect(true);
			question.getReponses().add(r);
		}
		
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
	public void testUpdate() {
		question.setId(1);
		question.setEnonce("maquestion");
		for(int j = 0 ; j<2 ; j++){
			Reponse r = new Reponse();
			r.setEnonce("mareponse");
			r.setCorrect(true);
			question.getReponses().add(r);
		}
		
		if(qd.update(question)==false){
			fail("l'update a retourné false");			
		}
		
		question.setId(3);
		
		if(qd.update(question)==true){
			fail("l'udpate est réussi sur un mauvais identifiant");
		}
		
		assertEquals(2, qd.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testDelete()
	 */
	@Override
	public void testDelete() {
		if(qd.delete(3)==true){
			fail("La suppression a réussi sur un mauvais identifiant");
		}
		
		if(qd.delete(1)==false){
			fail("La suppression a retourné false");
		}
		
		if(qd.delete(1)==true) {
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
