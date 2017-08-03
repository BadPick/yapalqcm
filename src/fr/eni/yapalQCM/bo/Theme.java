/**
 * 
 */
package fr.eni.yapalQCM.bo;

import java.util.ArrayList;

/**
 * @author mrault2015
 *
 */
public class Theme {

	private int id;
	private String nom;
	private ArrayList<Question> questions;

	public Theme() {
		questions = new ArrayList<Question>();
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}






	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}






	/**
	 * @return the questions
	 */
	public ArrayList<Question> getQuestions() {
		return questions;
	}






	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}






	

}
