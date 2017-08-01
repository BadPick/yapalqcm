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
			test.setNom("montest");
			test.setSeuilAcquis(14);
			test.setSeuilEnCoursDacquisition(10);
			test.setDuree(3600);
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
			td.delete(test.getId());
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
		int result = td.getOne(3).getId();
		if(result>0){
			fail("Récupération d'un mauvais élément (id innexistant en base de données)");
		}
		
		result = td.getOne(2).getId();
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
		test.setNom("montest");
		test.setSeuilAcquis(14);
		test.setSeuilEnCoursDacquisition(10);
		test.setDuree(3600);
		
		if(td.add(test)==false){
			fail("l'insertion a retourné false");			
		}
		
		test.setSeuilAcquis(-14);
		
		if(td.add(test)==true){
			fail("l'insertion est réussi avec un seuil négatif");			
		}
		
		test.setSeuilAcquis(14);
		test.setSeuilEnCoursDacquisition(15);
		if(td.add(test)==true){
			fail("l'insertion est réussi avec un seuil en cours d'acquisition supérieur au seuil d'acquisition");			
		}
		
		test.setDuree(-3600);
		
		if(td.add(test)==true){
			fail("l'insertion est réussi avec une durée négative");			
		}
		
		test.setDuree(3600);
		test.setNom("    ");
		
		if(td.add(test)==true){
			fail("l'insertion est réussi avec un nom vide (que des espaces)");			
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
		test.setId(1);
		test.setNom("montest");
		test.setSeuilAcquis(14);
		test.setSeuilEnCoursDacquisition(10);
		test.setDuree(3600);
		
		if(td.update(test)==false){
			fail("l'update a retourné false");			
		}
		
		test.setSeuilAcquis(-14);
		
		if(td.update(test)==true){
			fail("l'update est réussi avec un seuil négatif");			
		}
		
		test.setSeuilAcquis(14);
		test.setSeuilEnCoursDacquisition(15);
		
		if(td.update(test)==true){
			fail("l'update est réussi avec un seuil en cours d'acquisition supérieur au seuil d'acquisition");			
		}
		
		test.setDuree(-3600);
		
		if(td.update(test)==true){
			fail("l'update est réussi avec une durée négative");			
		}
		
		test.setDuree(3600);
		test.setNom("    ");
		
		if(td.update(test)==true){
			fail("l'update est réussi avec un nom vide (que des espaces)");			
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
		if(td.delete(3)==true){
			fail("La suppression a réussi sur un mauvais identifiant");
		}
		
		if(td.delete(1)==false){
			fail("La suppression a retourné false");
		}
		
		if(td.delete(1)==true) {
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
