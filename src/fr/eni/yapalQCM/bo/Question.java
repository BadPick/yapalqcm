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
	//transient
	private boolean marquee;
	
	
	public Question() {
		reponses = new ArrayList<Reponse>();
	}
	
	/**
	 * @return the marquee
	 */
	public boolean isMarquee() {
		return marquee;
	}

	/**
	 * @param marquee the marquee to set
	 */
	public void setMarquee(boolean marquee) {
		this.marquee = marquee;
	}

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
	
	/**
	 * méthode calculé qui vérifie si une (ou plusieur) réponse est cochée
	 * @return true si une réponse est cochée
	 */
	public boolean isRepondue(){
		if (getReponses()!=null && getReponses().size()>0) {
			for (Reponse r : reponses) {
				if (r.isChecked()) {
					return true;
				}
			}
		}
		return false;
	}

	public void addReponse(Reponse reponse) {
		reponse.setQuestion(this);
		getReponses().add(reponse);		
	}

}
