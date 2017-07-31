package fr.eni.yapalQCM.bo;

public class Resultat {

	private long tempsEcoule;
	private float seuilObtenu;
	private Test test;
	private Session session;
	private Utilisateur candidat;
	

	
	
	/**
	 * @return the tempsEcoule
	 */
	public long getTempsEcoule() {
		return tempsEcoule;
	}



	/**
	 * @param tempsEcoule the tempsEcoule to set
	 */
	public void setTempsEcoule(long tempsEcoule) {
		this.tempsEcoule = tempsEcoule;
	}



	/**
	 * @return the seuilObtenu
	 */
	public float getSeuilObtenu() {
		return seuilObtenu;
	}



	/**
	 * @param seuilObtenu the seuilObtenu to set
	 */
	public void setSeuilObtenu(float seuilObtenu) {
		this.seuilObtenu = seuilObtenu;
	}



	/**
	 * @return the test
	 */
	public Test getTest() {
		return test;
	}



	/**
	 * @param test the test to set
	 */
	public void setTest(Test test) {
		this.test = test;
	}



	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}



	/**
	 * @param session the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}



	/**
	 * @return the candidat
	 */
	public Utilisateur getCandidat() {
		return candidat;
	}



	/**
	 * @param candidat the candidat to set
	 */
	public void setCandidat(Utilisateur candidat) {
		this.candidat = candidat;
	}



	public Resultat() {
		// TODO Auto-generated constructor stub
	}

}
