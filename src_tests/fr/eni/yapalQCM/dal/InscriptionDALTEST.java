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

import fr.eni.yapalQCM.bo.Inscription;
import fr.eni.yapalQCM.bo.Role;
import fr.eni.yapalQCM.bo.Session;
import fr.eni.yapalQCM.bo.Utilisateur;

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class InscriptionDALTEST implements ITEST {
	
	public static Inscription inscription;
	public static Inscription i;
	public static List<Inscription> inscriptions = new ArrayList<Inscription>();
	public static List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	public static List<Session> sessions = new ArrayList<Session>();
	public static InscriptionDAL id;
	public static UtilisateurDAL ud;
	public static SessionDAL sd;
	public static RoleDAL rd;
	
	/**
	 * Méthode en charge d'initialiser les variables de notre classe de test
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		id = new InscriptionDAL();
		ud = new UtilisateurDAL();
		sd = new SessionDAL();
		rd = new RoleDAL();
		
		inscription = new Inscription();
		
		i = new Inscription();
		Utilisateur u = new Utilisateur();
		u.setId(2);
		u.setNom("nom");
		u.setPrenom("prenom");
		u.setEmail("mail@mail.com");
		u.setPassword("password");
		Role r = new Role();
		r.setId(1);
		u.setRole(r);
		i.setCandidat(u);
		Session s = new Session();
		s.setId(2);
		s.setNbPlaces(25);
		s.setTests(new ArrayList<fr.eni.yapalQCM.bo.Test>());
		i.setSession(s);
	}

	/**
	 * Méthode en charge de
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Méthode en charge d'avoir 2 tests dans la table INSCRIPTIONS
	 * 2 tests dans la table UTILISATEURS
	 * 2 tests dans la table SESSIONS
	 * avant chaque test
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 1 ; i<3 ; i++){
			Utilisateur u = new Utilisateur();
			u.setId(i);
			u.setNom("nom");
			u.setPrenom("prenom");
			u.setEmail("mail@mail.com");
			u.setPassword("password");
			Role r = new Role();
			r.setId(i);
			r.setName("monrole");
			u.setRole(r);
			inscription.setCandidat(u);
			Session s = new Session();
			s.setId(i);
			s.setNbPlaces(25);
			s.setTests(new ArrayList<fr.eni.yapalQCM.bo.Test>());
			inscription.setSession(s);
			rd.add(r);
			ud.add(u);
			sd.add(s);
			id.add(inscription);
		}
	}

	/**
	 * Méthode en charge de vider toutes les tables et de réinitiliser les identifiants après chaque test.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		inscriptions = id.getAll();
		for(Inscription inscription : inscriptions)
		{
			id.delete(inscription);
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
		
		try(Connection cnx = DBConnection.getConnection()) {
			Statement cmd = cnx.createStatement();
			cmd.execute("DBCC CHECKIDENT ('QUESTIONS', RESEED, 0)");
		} catch (SQLException e) {
			System.out.println("Problème de réinitialisation de l'auto-incrément de la table INSCRIPTIONS");
		}
		try(Connection cnx = DBConnection.getConnection()) {
			Statement cmd = cnx.createStatement();
			cmd.execute("DBCC CHECKIDENT ('UTILISATEURS', RESEED, 0)");
		} catch (SQLException e) {
			System.out.println("Problème de réinitialisation de l'auto-incrément de la table UTILISATEURS");
		}
		try(Connection cnx = DBConnection.getConnection()) {
			Statement cmd = cnx.createStatement();
			cmd.execute("DBCC CHECKIDENT ('SESSIONS', RESEED, 0)");
		} catch (SQLException e) {
			System.out.println("Problème de réinitialisation de l'auto-incrément de la table SESSIONS");
		}
	}

	
	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetLength()
	 */
	@Override
	@Test
	public void testGetLength() {
		int result = id.getLength();
		assertEquals(2, result);
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetOne()
	 */
	@Override
	@Test
	public void testGetOne() throws SQLException {
		int result = id.getOne(i).getCandidat().getId();
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
		List<Inscription> listGA = new ArrayList<Inscription>();
		listGA = id.getAll();
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
		if(id.add(inscription)==false){
			fail("l'insertion a retourné false");			
		}
		
		assertEquals(3, id.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testUpdate()
	 */
	@Override
	@Test
	public void testUpdate() throws SQLException {
		if(id.update(i)==false){
			fail("l'update a retourné false");			
		}
		
		i.getCandidat().setId(3);
		
		if(id.update(inscription)==true){
			fail("l'udpate est réussi sur un mauvais identifiant");
		}
		
		assertEquals(2, id.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testDelete()
	 */
	@Override
	@Test
	public void testDelete() throws SQLException {	
		if(id.delete(i)==true){
			fail("La suppression a réussi sur un mauvais identifiant");
		}
		
		i.getCandidat().setId(2);
		
		if(id.delete(i)==false){
			fail("La suppression a retourné false");
		}
		
		if(id.delete(i)==true) {
			if(id.getLength()<1){
				fail("La suppression a supprimé plus d'un élément");
			}
			else if(id.getLength()>1){
				fail("La suppression n'a pas été effectuée malgré qu'elle retourne true");
			}
			else{
				assertEquals(1, id.getLength());				
			}
		}
	}

}
