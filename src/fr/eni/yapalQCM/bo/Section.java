/**
 * 
 */
package fr.eni.yapalQCM.bo;

/**
 * @author mrault2015
 *
 */
public class Section {

	private int id;
	private Theme theme;
	private int nbQuestions;
	
	
	
	/**
	 * @return the theme
	 */
	public Theme getTheme() {
		return theme;
	}



	/**
	 * @param theme the theme to set
	 */
	public void setTheme(Theme theme) {
		this.theme = theme;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getNbQuestions() {
		return nbQuestions;
	}



	public void setNbQuestions(int nbQuestions) {
		this.nbQuestions = nbQuestions;
	}



	public Section() {
		// TODO Auto-generated constructor stub
	}

}
