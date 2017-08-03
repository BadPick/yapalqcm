package fr.eni.yapalQCM.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import fr.eni.yapalQCM.bo.Resultat;
import fr.eni.yapalQCM.bo.Session;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Utilisateur;
import fr.eni.yapalQCM.dal.TestDAL;

public class CandidatManager {

	/**
	 * m�thode en charge de la r�cup�ration d'un test le test doit �tre g�n�r�,
	 * le but �tant de passer le test
	 * 
	 * @param idTest
	 * @return test
	 * @throws SQLException 
	 */
	public static Test getTest(int idTest) throws SQLException {
		TestDAL testDal = new TestDAL();
		Test test = testDal.getOne(idTest);
		
		// g�n�ration du test
		generateTest(idTest);
		return test;
	}

	/**
	 * m�thode en charge de la g�n�ration du test
	 * 
	 * @param idTest
	 */
	public static void generateTest(int idTest) {

	}

	/**
	 * m�thode en charge de la r�cup�ration de la liste de tests dispo pour cet
	 * utilisateur les test n'ont pas besoin d'�tre g�n�r�s,le but �tant
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
	 * m�thode en charge de la r�cup�ration de la liste de r�sultat pour cet
	 * utilisateur
	 * 
	 * @param id
	 * @return ArrayList<Resultat>
	 */
	public static ArrayList<Resultat> getResultats(Utilisateur candidat) {
		// TESTING liste de r�sultats bidon
		return listeResultatsBidon();
	}

	public static ArrayList<Test> listeTestBidon() {
		// TESTING liste de tests bidon
		ArrayList<Test> tests = new ArrayList<Test>();
		for (int i = 0; i < 10; i++) {
			Test test = new Test();
			test.setId(i);
			test.setNom("Test num�ro " + i);
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
