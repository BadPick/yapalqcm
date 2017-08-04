package fr.eni.yapalQCM.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.yapalQCM.bo.Resultat;
import fr.eni.yapalQCM.bo.Session;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Utilisateur;
import fr.eni.yapalQCM.dal.ResultatDAL;
import fr.eni.yapalQCM.dal.SessionDAL;
import fr.eni.yapalQCM.dal.TestDAL;

public class CandidatManager {

	/**
	 * méthode en charge de la récupération d'un test le test doit être généré,
	 * le but étant de passer le test
	 * 
	 * @param idTest
	 * @return test
	 * @throws SQLException 
	 */
	public static Test getTest(int idTest) throws SQLException {
//		TestDAL testDal = new TestDAL();
//		Test test = new Test();
//		test.setId(idTest);
//		test = testDal.getOne(test);
			
		// génération du test
		//generateTest(idTest);

		return null;
	}

	/**
	 * méthode en charge de la génération du test
	 * 
	 * @param idTest
	 */
	public static void generateTest(int idTest) {

	}

	/**
	 * méthode en charge de la récupération de la liste de tests dispo pour cet
	 * utilisateur les test n'ont pas besoin d'être générés,le but étant
	 * d'afficher le nom et l'id du test
	 * 
	 * @param id
	 * @return ArrayList<Test>
	 * @throws SQLException 
	 */
	public static ArrayList<Test> getTests(Utilisateur candidat) throws SQLException {
		TestDAL testDal = new TestDAL();
		ArrayList<Test> tests = testDal.getManyBy(candidat.getId());
		return tests;
	}
		
	/**
	 * Récupération d'un résultat pour un test et un candidat donné.
	 * @param candidat
	 * @param test
	 * @return
	 * @throws SQLException
	 */
	public static Resultat getResultat(Utilisateur candidat,Test test) throws SQLException{
		Resultat resultat = null;
		ResultatDAL resultatDal = new ResultatDAL();
		SessionDAL sessionDal = new SessionDAL();
		resultat = resultatDal.getOneByUtilisateur(candidat, test);
		resultat.setCandidat(candidat);
		resultat.setSession(sessionDal.getOne(resultat.getSession()));
		resultat.setTest(test);
		return resultat;
	}
	
	/**
	 * Récupération d'une liste de résultats pour un candidat donné.
	 * @param candidat
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Resultat> getResultats(Utilisateur candidat) throws SQLException{
		ArrayList<Resultat> listeResultats = new ArrayList<Resultat>();
		ResultatDAL resultatDal = new ResultatDAL();
		SessionDAL sessionDal = new SessionDAL();
		TestDAL testDal = new TestDAL();
		listeResultats = resultatDal.getAllByUtilisateur(candidat);
		for (Resultat resultat : listeResultats) {
			resultat.setCandidat(candidat);
			resultat.setSession(sessionDal.getOne(resultat.getSession()));
			resultat.setTest(testDal.getOne(resultat.getTest()));
		}
		return listeResultats;
	}
	
	
	
	public static ArrayList<Test> listeTestBidon() {
		// TESTING liste de tests bidon
		ArrayList<Test> tests = new ArrayList<Test>();
		for (int i = 0; i < 10; i++) {
			Test test = new Test();
			test.setId(i);
			test.setNom("Test numéro " + i);
			test.setDuree(i * 2500);
			tests.add(test);
		}
		return tests;
	}
		
	public static ArrayList<Resultat> listeResultatsBidon() {
		ArrayList<Test> tests = listeTestBidon();
		// TESTING liste de resultats bidon
		ArrayList<Resultat> results = new ArrayList<Resultat>();
		Test test = null;
		for (int i = 0; i < 10; i++) {
			Resultat result = new Resultat();
			result.setSeuilObtenu(0.5f * i);
			result.setTempsEcoule(1500 * i);
			for (Test t : tests) {
				if (t.getId()==i) {
					test = t;
					break;
				}
			}
			result.setTest(test);
			results.add(result);
		}
		return results;
	}

	
	
}
