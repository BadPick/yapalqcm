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

import fr.eni.yapalQCM.bo.Utilisateur;
import fr.eni.yapalQCM.bo.Role;

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class UtilisateurDALTEST implements ITEST {
	public static Utilisateur utilisateur;
	public static List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	public static UtilisateurDAL ud;
	public static Role role;
	
	/**
	 * Méthode en charge d'initialiser les variables de notre classe de test
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ud = new UtilisateurDAL();
		utilisateur = new Utilisateur();
		utilisateur.setNom("util");
		utilisateur.setPrenom("isateur");
		utilisateur.setEmail("util@util.com");
		utilisateur.setPassword("myutil");
		role = new Role();
		role.setName("role");
		utilisateur.setRole(role);
	}

	/**
	 * Méthode en charge de
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Méthode en charge d'avoir 2 utilisateurs dans la table UTILISATEURS avant chaque test
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		for(int i = 0 ; i<2 ; i++){
			ud.add(utilisateur);
		}
	}

	/**
	 * Méthode en charge de vider la table UTILISATEURS et de réinitiliser les identifiants après chaque test.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		utilisateurs = ud.getAll();
		for(Utilisateur utilisateur : utilisateurs)
		{
			ud.delete(utilisateur);
		}
		
		try(Connection cnx = DBConnection.getConnection()) {
			Statement cmd = cnx.createStatement();
			cmd.execute("DBCC CHECKIDENT ('UTILISATEURS', RESEED, 0)");
		} catch (SQLException e) {
			System.out.println("Problème de réinitialisation de l'auto-incrément de la table UTILISATEURS");
		}
	}

	
	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetLength()
	 */
	@Override
	@Test
	public void testGetLength() {
		int result = ud.getLength();
		assertEquals(2, result);
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetOne()
	 */
	@Override
	@Test
	public void testGetOne() {
		Utilisateur u = new Utilisateur();
		u.setId(3);
		int result = ud.getOne(u).getId();
		if(result>0){
			fail("Récupération d'un mauvais élément (id innexistant en base de données)");
		}
		
		u.setId(2);
		
		result = ud.getOne(u).getId();
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
		List<Utilisateur> listGA = new ArrayList<Utilisateur>();
		listGA = ud.getAll();
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
		if(ud.add(utilisateur)==false){
			fail("l'insertion a retourné false");			
		}
		
		assertEquals(3, ud.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testUpdate()
	 */
	@Override
	@Test
	public void testUpdate() {
		Utilisateur u = new Utilisateur();
		u = new Utilisateur();
		u.setNom("util");
		u.setPrenom("isateur");
		u.setEmail("util@util.com");
		u.setPassword("myutil");
		Role r = new Role();
		r.setName("role");
		u.setRole(role);
		u.setId(1);
		
		if(ud.update(u)==false){
			fail("l'update a retourné false");			
		}
		
		u.setId(3);
		
		if(ud.update(u)==true){
			fail("l'udpate est réussi sur un mauvais identifiant");
		}
		
		assertEquals(2, ud.getAll().size());
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testDelete()
	 */
	@Override
	@Test
	public void testDelete() {
		Utilisateur u = new Utilisateur();
		u.setId(3);
		if(ud.delete(u)==true){
			fail("La suppression a réussi sur un mauvais identifiant");
		}
		
		u.setId(2);
		
		if(ud.delete(u)==false){
			fail("La suppression a retourné false");
		}
		
		if(ud.delete(u)==true) {
			if(ud.getLength()<1){
				fail("La suppression a supprimé plus d'un élément");
			}
			else if(ud.getLength()>1){
				fail("La suppression n'a pas été effectuée malgré qu'elle retourne true");
			}
			else{
				assertEquals(1, ud.getLength());				
			}
		}
	}

}
