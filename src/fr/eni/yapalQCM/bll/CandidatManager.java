package fr.eni.yapalQCM.bll;

import java.util.ArrayList;
import java.util.Date;

import fr.eni.yapalQCM.bo.Resultat;
import fr.eni.yapalQCM.bo.Session;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Utilisateur;

public class CandidatManager {

	/**
	 * méthode en charge de la récupération d'un test le test doit être généré,
	 * le but étant de passer le test
	 * 
	 * @param idTest
	 * @return test
	 */
	public static Test getTest(String idTest) {
		Test test = null;

		// génération du test
		generateTest(idTest);

		return test;
	}

	/**
	 * méthode en charge de la génération du test
	 * 
	 * @param idTest
	 */
	public static void generateTest(String idTest) {

	}

	/**
	 * méthode en charge de la récupération de la liste de tests dispo pour cet
	 * utilisateur les test n'ont pas besoin d'être générés,le but étant
	 * d'afficher le nom et l'id du test
	 * 
	 * @param id
	 * @return ArrayList<Test>
	 */
	public static ArrayList<Test> getTests(Utilisateur candidat) {
		// TESTING liste de tests bidon
		return listeTestBidon();
	}

	/**
	 * méthode en charge de la récupération de la liste de résultat pour cet
	 * utilisateur
	 * 
	 * @param id
	 * @return ArrayList<Resultat>
	 */
	public static ArrayList<Resultat> getResultats(Utilisateur candidat) {
		// TESTING liste de résultats bidon
		return listeResultatsBidon();
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
