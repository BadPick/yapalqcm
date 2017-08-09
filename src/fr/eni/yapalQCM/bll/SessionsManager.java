package fr.eni.yapalQCM.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.yapalQCM.bo.Session;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.dal.SessionDAL;
import fr.eni.yapalQCM.dal.TestDAL;

public class SessionsManager {
	static SessionDAL sessionDal =new SessionDAL();
	static TestDAL testDal = new TestDAL();
	
	public static List<Session> getSessions() throws SQLException{
		List<Session> liste = new ArrayList<Session>();
		liste = sessionDal.getAll();
		
		for (Session session : liste) {
			ArrayList<Test> tests = new ArrayList<Test>();
			List<Integer> idTests = new ArrayList<Integer>();
			idTests = sessionDal.listeTests(session.getId());
				for (Integer idTest : idTests) {
					tests.add(testDal.getOne(idTest));
				}
			session.setTests(tests);
		}
		
		return liste;
	}

	public static boolean ajouterSession(Session session) throws SQLException {		
		return sessionDal.add(session);	
	}
	
	public static boolean suprimerSession(int idSession) throws SQLException{
		for (int idTest : sessionDal.listeTests(idSession)) {
			sessionDal.deleteTestSession(idTest,idSession);
		};
		return sessionDal.deleteSession(idSession);		
	}

	public static boolean modifierSession(Session session) throws SQLException {
		return sessionDal.update(session);
		
	}
}
