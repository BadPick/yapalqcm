/**
 * @author wvignoles2017
 * @date 31 juil. 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

import static org.junit.Assert.*;

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

import fr.eni.yapalQCM.dal.DBConnection;

/**
 * @author wvignoles2017
 * @date 31 juil. 2017
 * @version yapalqcm V1.0
 */
public class TestDALTEST implements ITEST{
	public static fr.eni.yapalQCM.bo.Test test;
	public static TestDAL td;
	List<fr.eni.yapalQCM.bo.Test>tests = new ArrayList<fr.eni.yapalQCM.bo.Test>();
	/**
	 * Méthode en charge d'initialiser les variables de notre classe de test
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		td = new TestDAL();
		test = new fr.eni.yapalQCM.bo.Test();
		test.setNom("montest");
		test.setSeuilAcquis(14);
		test.setSeuilEnCoursDacquisition(10);
		test.setDuree(3600);
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
			td.add(test);
		}
	}

	/**
	 * Méthode en charge de vider la table TESTS et de réinitiliser les identifiants après chaque test.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		tests = td.getAll();
		for(fr.eni.yapalQCM.bo.Test test : tests)
		{
			td.delete(test);
		}
		
		try(Connection cnx = DBConnection.getConnection()) {
			Statement cmd = cnx.createStatement();
			cmd.execute("DBCC CHECKIDENT ('TEST', RESEED, 0)");
		} catch (SQLException e) {
			System.out.println("Problème de réinitialisation de l'auto-incrément de la table TEST");;
		}
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.test.ITEST#testGetLength()
	 */
	@Override
	public void testGetLength() {
		int result = td.getLength();
		assertEquals(2, result);
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.test.ITEST#testGetOne()
	 */
	@Override
	@Test
	public void testGetOne() {
		test.setId(3);
		int result = td.getOne(test).getId();
		if(result>0){
			fail("Récupération d'un mauvais élément (id innexistant en base de données)");
		}
		
		test.setId(2);
		result = td.getOne(test).getId();
		if(result!=2){
			fail("L'élément ciblé n'a pas été récupéré");
		}
		assertEquals(2, result);
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.test.ITEST#testGetAll()
	 */
	@Override
	@Test
	public void testGetAll() {
		List<fr.eni.yapalQCM.bo.Test> listGA = new ArrayList<fr.eni.yapalQCM.bo.Test>();
		listGA = td.getAll();
		if(listGA.size()!=2){
			fail("La requête n'a pas récupéré tous les éléments de la table");
		}
		else{
			assertEquals(2, listGA.size());
		}
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.test.ITEST#testAdd()
	 */
	@Override
	@Test
	public void testAdd() {
		if(td.add(test)==false){
			fail("l'insertion a retourné false");			
		}
		
		assertEquals(3, td.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.test.ITEST#testUpdate()
	 */
	@Override
	@Test
	public void testUpdate() {
		test.setId(2);

		if(td.update(test)==false){
			fail("l'update a retourné false");			
		}
		
		test.setId(3);
		
		if(td.update(test)==true){
			fail("l'udpate est réussi sur un mauvais identifiant");
		}
		
		assertEquals(2, td.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.test.ITEST#testDelete()
	 */
	@Override
	@Test
	public void testDelete() {
		test.setId(3);
		if(td.delete(test)==true){
			fail("La suppression a réussi sur un mauvais identifiant");
		}
		
		test.setId(2);
		if(td.delete(test)==false){
			fail("La suppression a retourné false");
		}
		
		if(td.delete(test)==true) {
			if(td.getLength()<1){
				fail("La suppression a supprimé plus d'un élément");
			}
			else if(td.getLength()>1){
				fail("La suppression n'a pas été effectuée malgré qu'elle retourne true");
			}
			else{
				assertEquals(1, td.getLength());				
			}
		}
	}
}
