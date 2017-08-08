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
	private boolean repondue;
	private boolean plusieursReponses;
	


	public Question() {
		reponses = new ArrayList<Reponse>();
	}
	
	
	
	
	/**
	 * Getter pour plusieursReponses.
	 * @return the plusieursReponses
	 */
	public boolean isPlusieursReponses() {
		return plusieursReponses;
	}




	/**
	 * Getter pour plusieursReponses.
	 * @param plusieursReponses the plusieursReponses to set
	 */
	public void setPlusieursReponses(boolean plusieursReponses) {
		this.plusieursReponses = plusieursReponses;
	}




	/**
	 * @return the repondue
	 */
	public boolean isRepondue() {
		return repondue;
	}
	
	/**
	 * Getter pour repondue.
	 * @param repondue the repondue to set
	 */
	public void setRepondue(boolean repondue) {
		this.repondue = repondue;
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
	 * m�thode calcul� qui v�rifie si une (ou plusieur) r�ponse est coch�e
	 * @return true si une r�ponse est coch�e
	 */
	public void checkRepondue(){
		repondue=false;
		if (getReponses()!=null && getReponses().size()>0) {
			for (Reponse r : getReponses()) {
				if (r.isChecked()) {
					repondue=true;
				}
			}
		}
	}
	
	public void checkNbreReponses(){
		this.setPlusieursReponses(false);
		int nbreReponses = 0;
		if (getReponses()!=null && getReponses().size()>0) {
			for (Reponse r : getReponses()) {
				if (r.isCorrect()) {
					nbreReponses++;
				}
				if(nbreReponses>1){
					this.setPlusieursReponses(true);
				}
			}
		}
	}

	public void addReponse(Reponse reponse) {
		reponse.setQuestion(this);
		getReponses().add(reponse);		
	}

}
