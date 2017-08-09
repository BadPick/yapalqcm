/**
 * @author wvignoles2017
 * @date 9 août 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.bo;

import java.io.Serializable;

/**
 * @author wvignoles2017
 * @date 9 août 2017
 * @version yapalqcm V1.0
 */
public class ReponseEnCoursWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numReponse;
	private int idReponse;
	private boolean checked;
	/**
	 * Constructeur.
	 */
	public ReponseEnCoursWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Getter pour numReponse.
	 * @return the numReponse
	 */
	public int getNumReponse() {
		return numReponse;
	}
	/**
	 * Getter pour numReponse.
	 * @param numReponse the numReponse to set
	 */
	public void setNumReponse(int numReponse) {
		this.numReponse = numReponse;
	}
	/**
	 * Getter pour idReponse.
	 * @return the idReponse
	 */
	public int getIdReponse() {
		return idReponse;
	}
	/**
	 * Getter pour idReponse.
	 * @param idReponse the idReponse to set
	 */
	public void setIdReponse(int idReponse) {
		this.idReponse = idReponse;
	}
	/**
	 * Getter pour checked.
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}
	/**
	 * Getter pour checked.
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
}
