/**
 * 
 */
package fr.eni.yapalQCM.bo;

/**
 * @author mrault2015
 *
 */
public class Section {

	private Test test;
	private Theme theme;
	private int nbQuestions;
	
	public Section() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Getter pour test.
	 * @return the test
	 */
	public Test getTest() {
		return test;
	}



	/**
	 * Getter pour test.
	 * @param test the test to set
	 */
	public void setTest(Test test) {
		this.test = test;
	}



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



	public int getNbQuestions() {
		return nbQuestions;
	}



	public void setNbQuestions(int nbQuestions) {
		this.nbQuestions = nbQuestions;
	}



	

}
