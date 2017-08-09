/**
 * @author wvignoles2017
 * @date 9 août 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.bo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author wvignoles2017
 * @date 9 août 2017
 * @version yapalqcm V1.0
 */
public class QuestionEnCoursWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numQuestion;
	private int idQuestion;
	private boolean marquageQuestion;
	private long tempsEcoule;
	private ArrayList<ReponseEnCoursWrapper> reponses;
	/**
	 * Constructeur.
	 */
	public QuestionEnCoursWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Getter pour numQuestion.
	 * @return the numQuestion
	 */
	public int getNumQuestion() {
		return numQuestion;
	}
	/**
	 * Getter pour numQuestion.
	 * @param numQuestion the numQuestion to set
	 */
	public void setNumQuestion(int numQuestion) {
		this.numQuestion = numQuestion;
	}
	/**
	 * Getter pour marquageQuestion.
	 * @return the marquageQuestion
	 */
	public boolean isMarquageQuestion() {
		return marquageQuestion;
	}
	/**
	 * Getter pour marquageQuestion.
	 * @param marquageQuestion the marquageQuestion to set
	 */
	public void setMarquageQuestion(boolean marquageQuestion) {
		this.marquageQuestion = marquageQuestion;
	}
	/**
	 * Getter pour tempsEcoule.
	 * @return the tempsEcoule
	 */
	public long getTempsEcoule() {
		return tempsEcoule;
	}
	/**
	 * Getter pour tempsEcoule.
	 * @param tempsEcoule the tempsEcoule to set
	 */
	public void setTempsEcoule(long tempsEcoule) {
		this.tempsEcoule = tempsEcoule;
	}
	/**
	 * Getter pour reponses.
	 * @return the reponses
	 */
	public ArrayList<ReponseEnCoursWrapper> getReponses() {
		return reponses;
	}
	/**
	 * Getter pour reponses.
	 * @param reponses the reponses to set
	 */
	public void setReponses(ArrayList<ReponseEnCoursWrapper> reponses) {
		this.reponses = reponses;
	}
	/**
	 * Getter pour idQuestion.
	 * @return the idQuestion
	 */
	public int getIdQuestion() {
		return idQuestion;
	}
	/**
	 * Getter pour idQuestion.
	 * @param idQuestion the idQuestion to set
	 */
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuestionEnCoursWrapper [numQuestion=");
		builder.append(numQuestion);
		builder.append(", idQuestion=");
		builder.append(idQuestion);
		builder.append(", marquageQuestion=");
		builder.append(marquageQuestion);
		builder.append(", tempsEcoule=");
		builder.append(tempsEcoule);
		builder.append(", reponses=");
		builder.append(reponses);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
