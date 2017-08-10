package fr.eni.yapalQCM.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.dal.TestDAL;

public class TestsManager {
	
	/**
	 * Classe permettant de récupérer l'ensemble des tests (sans la liste
	 * de questions et de réponses!!!).
	 * @return
	 * @throws SQLException 
	 */
	public static List<Test> getTests() throws SQLException{
		List<Test> liste = new ArrayList<Test>();
		
			TestDAL testDal = new TestDAL();
			liste=testDal.getTests();
		
		return liste;
	}

	public static Test getTest(int idTest) throws SQLException {
		Test test = null;
		
		TestDAL testDal = new TestDAL();
		test=testDal.getOne(idTest);
		
		return test;
	}

	public static void ajouterTestEtSections(Test test) throws SQLException {
		TestDAL testDal = new TestDAL();
		testDal.add(test);
	}

	public static void supprimerTest(int idTest) throws SQLException {
		TestDAL testDal = new TestDAL();
		Test test = new Test();
		test.setId(idTest);
		testDal.delete(test);
		
	}
}
