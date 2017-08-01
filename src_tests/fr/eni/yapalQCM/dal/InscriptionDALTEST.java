/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import fr.eni.yapalQCM.bo.Inscription;
import fr.eni.yapalQCM.bo.Resultat;
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
	public static List<Inscription> resultats = new ArrayList<Inscription>();
	public static List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	public static List<Session> sessions = new ArrayList<Session>();
	public static InscriptionDAL rd;
	public static UtilisateurDAL ud;
	public static SessionDAL sd;

	/**
	 * Méthode en charge d'initialiser les variables de notre classe de test
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	/**
	 * Méthode en charge de
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Méthode en charge d'avoir 2 inscriptions dans la table INSCRIPTIONS avant chaque test
	 * et
	 * de créer 1 session et 2 TestSession
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * Méthode en charge de vider la table INSCRIPTIONS et de réinitiliser les identifiants après chaque test.
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	
	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetLength()
	 */
	@Override
	public void testGetLength() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetOne()
	 */
	@Override
	public void testGetOne() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testGetAll()
	 */
	@Override
	public void testGetAll() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testAdd()
	 */
	@Override
	public void testAdd() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testUpdate()
	 */
	@Override
	public void testUpdate() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.ITEST#testDelete()
	 */
	@Override
	public void testDelete() {
		// TODO Auto-generated method stub

	}

}
