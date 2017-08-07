/**
 * 
 */
package fr.eni.yapalQCM.bo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

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
	//transient pour Test Session
	private Time heure;
	private boolean isBegin;
	private long tempsEcoule;
	//transient
	private int nbQuestions;
	
	
	/**
	 * Getter pour heure.
	 * @return the heure
	 */
	public Time getHeure() {
		return heure;
	}

	/**
	 * Getter pour heure.
	 * @param heure the heure to set
	 */
	public void setHeure(Time heure) {
		this.heure = heure;
	}

	/**
	 * Getter pour isBegin.
	 * @return the isBegin
	 */
	public boolean isBegin() {
		return isBegin;
	}

	/**
	 * Getter pour isBegin.
	 * @param isBegin the isBegin to set
	 */
	public void setBegin(boolean isBegin) {
		this.isBegin = isBegin;
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

	public Test() {
		sections = new ArrayList<Section>();
	}
	
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

	public void addSection(Section section) {
		section.setTest(this);
		getSections().add(section);	
	}
	
	public int getNbQuestions(){
		int nb = 0;
		for (Section section : sections) {
			nb += section.getNbQuestions();
		}
		nbQuestions = nb;
		return nbQuestions;
	}



	

}
