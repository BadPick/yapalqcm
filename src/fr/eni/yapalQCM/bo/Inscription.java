package fr.eni.yapalQCM.bo;

public class Inscription {

	private Utilisateur candidat;
	private Session session;

	public Inscription() {
		// TODO Auto-generated constructor stub
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

}

