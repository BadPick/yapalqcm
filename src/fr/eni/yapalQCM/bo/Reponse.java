/**
 * 
 */
package fr.eni.yapalQCM.bo;

/**
 * @author mrault2015
 *
 */
public class Reponse {

	private int id;
	private String enonce;
	private boolean isCorrect;
	
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}



	/**
	 * @return the enonce
	 */
	public String getEnonce() {
		return enonce;
	}



	/**
	 * @param enonce the enonce to set
	 */
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}



	/**
	 * @return the isCorrect
	 */
	public boolean isCorrect() {
		return isCorrect;
	}



	/**
	 * @param isCorrect the isCorrect to set
	 */
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}



	public Reponse() {
		// TODO Auto-generated constructor stub
	}

}
