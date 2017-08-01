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

import fr.eni.yapalQCM.bo.Theme;

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class ThemeDALTEST implements ITEST {
	public static Theme theme;
	public static List<Theme> themes = new ArrayList<Theme>();
	public static ThemeDAL td;
	
	/**
	 * Méthode en charge d'initialiser les variables de notre classe de test
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		td = new ThemeDAL();
		theme = new Theme();
		theme.setNom("montheme");
	}

	/**
	 * Méthode en charge de
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Méthode en charge d'avoir 2 themes dans la table THEMES avant chaque test
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 0 ; i<2 ; i++){
			td.add(theme);
		}
	}

	/**
	 * Méthode en charge de vider la table THEMES et de réinitiliser les identifiants après chaque test.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		themes = td.getAll();
		for(Theme theme : themes)
		{
			td.delete(theme);
		}
		
		try(Connection cnx = DBConnection.getConnection()) {
			Statement cmd = cnx.createStatement();
			cmd.execute("DBCC CHECKIDENT ('THEMES', RESEED, 0)");
		} catch (SQLException e) {
			System.out.println("Problème de réinitialisation de l'auto-incrément de la table THEMES");
		}
	}

	
	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetLength()
	 */
	@Override
	@Test
	public void testGetLength() {
		int result = td.getLength();
		assertEquals(2, result);
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetOne()
	 */
	@Override
	@Test
	public void testGetOne() {
		Theme t = new Theme();
		t.setId(3);
		int result = td.getOne(t).getId();
		if(result>0){
			fail("Récupération d'un mauvais élément (id innexistant en base de données)");
		}
		
		t.setId(2);
		
		result = td.getOne(t).getId();
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
	public void testGetAll() {
		List<Theme> listGA = new ArrayList<Theme>();
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
	 * @see fr.eni.yapalQCM.dal.ITEST#testAdd()
	 */
	@Override
	@Test
	public void testAdd() {
		if(td.add(theme)==false){
			fail("l'insertion a retourné false");			
		}
		
		assertEquals(3, td.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testUpdate()
	 */
	@Override
	@Test
	public void testUpdate() {
		Theme t = new Theme();
		t.setNom("montheme2");
		t.setId(1);
		
		if(td.update(t)==false){
			fail("l'update a retourné false");			
		}
		
		t.setId(3);
		
		if(td.update(t)==true){
			fail("l'udpate est réussi sur un mauvais identifiant");
		}
		
		assertEquals(2, td.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testDelete()
	 */
	@Override
	@Test
	public void testDelete() {
		Theme t = new Theme();
		t.setId(3);
		if(td.delete(t)==true){
			fail("La suppression a réussi sur un mauvais identifiant");
		}
		
		t.setId(2);
		
		if(td.delete(t)==false){
			fail("La suppression a retourné false");
		}
		
		if(td.delete(t)==true) {
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
