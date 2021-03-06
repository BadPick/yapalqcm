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

import fr.eni.yapalQCM.bo.Section;
import fr.eni.yapalQCM.bo.Theme;

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class SectionDALTEST implements ITEST {
	public static Section section;
	public static Section sec;
	public static List<Section> sections = new ArrayList<Section>();
	public static SectionDAL sd;
	public static Theme theme;
	public static List<Theme> themes = new ArrayList<Theme>();
	public static ThemeDAL themedal;
	public static fr.eni.yapalQCM.bo.Test test;
	public static TestDAL td;
	public static List<fr.eni.yapalQCM.bo.Test> tests = new ArrayList<fr.eni.yapalQCM.bo.Test>();
	
	/**
	 * Méthode en charge d'initialiser les variables de notre classe de test
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sd = new SectionDAL();
		td = new TestDAL();
		themedal = new ThemeDAL();
		section = new Section();
		
		sec = new Section();
		test = new fr.eni.yapalQCM.bo.Test();
		theme = new Theme();
		sec.setNbQuestions(15);
		sec.setTheme(theme);
		sec.setTest(test);
	}

	/**
	 * Méthode en charge de
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Méthode en charge d'avoir 2 sections dans la table SECTIONS avant chaque test
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 1 ; i<3 ; i++){
			test.setNom("montest");
			test.setSeuilAcquis(14);
			test.setSeuilEnCoursDacquisition(10);
			test.setDuree(3600);
			
			theme.setNom("theme");
			
			themedal.add(theme);
			td.add(test);
			
			section.setNbQuestions(15);
			theme.setId(i);
			section.setTheme(theme);
			test.setId(i);
			section.setTest(test);
			
			sd.add(section);
		}
	}

	/**
	 * Méthode en charge de vider la table SECTIONS et de réinitiliser les identifiants après chaque test.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		sections = sd.getAll();
		for(Section section : sections)
		{
			sd.delete(section);
		}
		tests = td.getAll();
		for(fr.eni.yapalQCM.bo.Test test : tests)
		{
			td.delete(test);
		}
		themes = themedal.getAll();
		for(Theme theme : themes)
		{
			themedal.delete(theme);
		}
		
		try(Connection cnx = DBConnection.getConnection()) {
			Statement cmd = cnx.createStatement();
			cmd.execute("DBCC CHECKIDENT ('TESTS', RESEED, 0)");
		} catch (SQLException e) {
			System.out.println("Problème de réinitialisation de l'auto-incrément de la table TESTS");
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
		int result = sd.getLength();
		assertEquals(2, result);
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetOne()
	 */
	@Override
	@Test
	public void testGetOne() throws SQLException {
		Section s = new Section();
		s.setTest(new fr.eni.yapalQCM.bo.Test());
		s.getTest().setId(3);
		s.setTheme(new Theme());
		s.getTheme().setId(3);
		Section se = sd.getOne(s);
		if(se!=null){
			fail("Récupération d'un mauvais élément (id innexistant en base de données)");
		}
		
		s.getTest().setId(2);
		s.getTheme().setId(2);
		
		int result = sd.getOne(s).getTest().getId();
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
		List<Section> listGA = new ArrayList<Section>();
		listGA = sd.getAll();
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
		sec.getTest().setId(3);
		td.add(sec.getTest());
		sec.getTheme().setId(3);
		themedal.add(sec.getTheme());
		if(sd.add(sec)==false){
			fail("l'insertion a retourné false");			
		}
		
		assertEquals(3, sd.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testUpdate()
	 */
	@Override
	@Test
	public void testUpdate() throws SQLException {
		Section s = new Section();
		s.setNbQuestions(10);
		s.setTest(new fr.eni.yapalQCM.bo.Test());
		s.getTest().setId(2);
		s.setTheme(new Theme());
		s.getTheme().setId(2);
		
		if(sd.update(s)==false){
			fail("l'update a retourné false");			
		}
		
		assertEquals(2, sd.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testDelete()
	 */
	@Override
	@Test
	public void testDelete() throws SQLException {
		Section s = new Section();
		s.setTest(new fr.eni.yapalQCM.bo.Test());
		s.getTest().setId(2);
		s.setTheme(new Theme());
		s.getTheme().setId(2);
		
		if(sd.delete(s)==false){
			fail("La suppression a retourné false");
		}
		
		if(sd.delete(s)==true) {
			if(sd.getLength()<1){
				fail("La suppression a supprimé plus d'un élément");
			}
			else if(sd.getLength()>1){
				fail("La suppression n'a pas été effectuée malgré qu'elle retourne true");
			}
			else{
				assertEquals(1, sd.getLength());				
			}
		}
	}

}
