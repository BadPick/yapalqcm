/**
 * 
 */
package fr.eni.yapalQCM.bo;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author mrault2015
 *
 */
public class Session {

	private int id;
	private Date date;
	private int nbPlaces;
	private ArrayList<Test> tests;
	
	
	
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
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}



	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}



	/**
	 * @return the nbPlaces
	 */
	public int getNbPlaces() {
		return nbPlaces;
	}



	/**
	 * @param nbPlaces the nbPlaces to set
	 */
	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}



	/**
	 * @return the tests
	 */
	public ArrayList<Test> getTests() {
		return tests;
	}



	/**
	 * @param tests the tests to set
	 */
	public void setTests(ArrayList<Test> tests) {
		this.tests = tests;
	}



	public Session() {
		// TODO Auto-generated constructor stub
	}

}
