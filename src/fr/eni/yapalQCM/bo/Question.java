/**
 * 
 */
package fr.eni.yapalQCM.bo;

import java.util.ArrayList;

/**
 * @author mrault2015
 *
 */
public class Question {

	private int id;
	private String enonce;
	private ArrayList<Reponse> reponses;
	
	
	/**
	 * @return the reponses
	 */
	public ArrayList<Reponse> getReponses() {
		return reponses;
	}

	/**
	 * @param reponses the reponses to set
	 */
	public void setReponses(ArrayList<Reponse> reponses) {
		this.reponses = reponses;
	}

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

	public Question() {
		// TODO Auto-generated constructor stub
	}

}
