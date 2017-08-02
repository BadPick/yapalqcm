/**
 * 
 */
package fr.eni.yapalQCM.bo;

import java.util.ArrayList;

/**
 * @author mrault2015
 *
 */
public class Test {

	private int id;
	private String nom;
	private float seuilAcquis;
	private float seuilEnCoursDacquisition;
	private long duree;
	private ArrayList<Section> sections;
	
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * @return the sections
	 */
	public ArrayList<Section> getSections() {
		return sections;
	}



	/**
	 * @param sections the sections to set
	 */
	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
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
	 * @return the seuilAcquis
	 */
	public float getSeuilAcquis() {
		return seuilAcquis;
	}



	/**
	 * @param seuilAcquis the seuilAcquis to set
	 */
	public void setSeuilAcquis(float seuilAcquis) {
		this.seuilAcquis = seuilAcquis;
	}



	/**
	 * @return the seuilEnCoursDacquisition
	 */
	public float getSeuilEnCoursDacquisition() {
		return seuilEnCoursDacquisition;
	}



	/**
	 * @param seuilEnCoursDacquisition the seuilEnCoursDacquisition to set
	 */
	public void setSeuilEnCoursDacquisition(float seuilEnCoursDacquisition) {
		this.seuilEnCoursDacquisition = seuilEnCoursDacquisition;
	}



	/**
	 * @return the duree
	 */
	public long getDuree() {
		return duree;
	}



	/**
	 * @param duree the duree to set
	 */
	public void setDuree(long duree) {
		this.duree = duree;
	}



	public Test() {
		// TODO Auto-generated constructor stub
	}

}
