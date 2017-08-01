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

import fr.eni.yapalQCM.bo.Resultat;
import fr.eni.yapalQCM.bo.Role;
import fr.eni.yapalQCM.bo.Session;
import fr.eni.yapalQCM.bo.Utilisateur;

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class ResultatDALTEST implements ITEST {
	public static Resultat resultat;
	public static Resultat r;
	public static List<Resultat> resultats = new ArrayList<Resultat>();
	public static List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	public static List<Session> sessions = new ArrayList<Session>();
	public static List<fr.eni.yapalQCM.bo.Test> tests = new ArrayList<fr.eni.yapalQCM.bo.Test>();
	public static ResultatDAL rd;
	public static UtilisateurDAL ud;
	public static SessionDAL sd;
	public static TestDAL td;
	
	/**
	 * Méthode en charge d'initialiser les variables de notre classe de test
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rd = new ResultatDAL();
		ud = new UtilisateurDAL();
		sd = new SessionDAL();
		td = new TestDAL();
		
		resultat = new Resultat();
		
		r = new Resultat();
		r.setTempsEcoule(1500);
		r.setSeuilObtenu(10.52f);
		Utilisateur u = new Utilisateur();
		u.setId(2);
		u.setNom("nom");
		u.setPrenom("prenom");
		u.setEmail("mail@mail.com");
		u.setPassword("password");
		u.setRole(new Role());
		r.setCandidat(u);
		Session s = new Session();
		s.setId(2);
		s.setNbPlaces(25);
		s.setTests(new ArrayList<fr.eni.yapalQCM.bo.Test>());
		r.setSession(s);
		fr.eni.yapalQCM.bo.Test t = new fr.eni.yapalQCM.bo.Test();
		t.setId(2);
		t.setDuree(3600);
		t.setNom("test");
		t.setSeuilAcquis(14);
		t.setSeuilEnCoursDacquisition(10);
		r.setTest(t);
	}

	/**
	 * Méthode en charge de
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Méthode en charge d'avoir 2 tests dans la table RESULTATS
	 * 2 tests dans la table UTILISATEURS
	 * 2 tests dans la table SESSIONS
	 * 2 tests dans la table TESTS
	 * avant chaque test
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 0 ; i<2 ; i++){
			resultat.setTempsEcoule(1500);
			resultat.setSeuilObtenu(10.52f);
			Utilisateur u = new Utilisateur();
			u.setNom("nom");
			u.setPrenom("prenom");
			u.setEmail("mail@mail.com");
			u.setPassword("password");
			u.setRole(new Role());
			resultat.setCandidat(u);
			Session s = new Session();
			s.setNbPlaces(25);
			s.setTests(new ArrayList<fr.eni.yapalQCM.bo.Test>());
			resultat.setSession(s);
			fr.eni.yapalQCM.bo.Test t = new fr.eni.yapalQCM.bo.Test();
			t.setDuree(3600);
			t.setNom("test");
			t.setSeuilAcquis(14);
			t.setSeuilEnCoursDacquisition(10);
			resultat.setTest(t);
			ud.add(u);
			sd.add(s);
			td.add(t);
			rd.add(resultat);
		}
	}

	/**
	 * Méthode en charge de vider toutes les tables et de réinitiliser les identifiants après chaque test.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		resultats = rd.getAll();
		for(Resultat resultat : resultats)
		{
			rd.delete(resultat);
		}
		utilisateurs = ud.getAll();
		for(Utilisateur utilisateur : utilisateurs)
		{
			ud.delete(utilisateur);
		}
		sessions = sd.getAll();
		for(Session session : sessions)
		{
			sd.delete(session);
		}
		tests = td.getAll();
		for(fr.eni.yapalQCM.bo.Test test : tests)
		{
			td.delete(test);
		}
		
		try(Connection cnx = DBConnection.getConnection()) {
			Statement cmd = cnx.createStatement();
			cmd.execute("DBCC CHECKIDENT ('QUESTION', RESEED, 0)");
		} catch (SQLException e) {
			System.out.println("Problème de réinitialisation de l'auto-incrément de la table QUESTION");;
		}
		try(Connection cnx = DBConnection.getConnection()) {
			Statement cmd = cnx.createStatement();
			cmd.execute("DBCC CHECKIDENT ('UTILISATEUR', RESEED, 0)");
		} catch (SQLException e) {
			System.out.println("Problème de réinitialisation de l'auto-incrément de la table UTILISATEUR");;
		}
		try(Connection cnx = DBConnection.getConnection()) {
			Statement cmd = cnx.createStatement();
			cmd.execute("DBCC CHECKIDENT ('SESSION', RESEED, 0)");
		} catch (SQLException e) {
			System.out.println("Problème de réinitialisation de l'auto-incrément de la table SESSION");;
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
	public void testGetOne() {
		int result = rd.getOne(r).getCandidat().getId();
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
		List<Resultat> listGA = new ArrayList<Resultat>();
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
	public void testAdd() {
		Resultat res = new Resultat();
		res.setTempsEcoule(1500);
		res.setSeuilObtenu(10.52f);
		
		if(rd.add(res)==false){
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
	public void testUpdate() {
		if(rd.update(r)==false){
			fail("l'update a retourné false");			
		}
		
		r.getCandidat().setId(3);
		
		if(rd.update(resultat)==true){
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
	public void testDelete() {	
		if(rd.delete(r)==true){
			fail("La suppression a réussi sur un mauvais identifiant");
		}
		
		r.getCandidat().setId(2);
		
		if(rd.delete(r)==false){
			fail("La suppression a retourné false");
		}
		
		if(rd.delete(r)==true) {
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
