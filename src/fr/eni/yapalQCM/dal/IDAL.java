/**
 * @author wvignoles2017
 * @date 31 juil. 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author wvignoles2017
 * @date 31 juil. 2017
 * @version yapalqcm V1.0
 */
public interface IDAL<Type1> {
	/**
	 * Méthode en charge de compter le nombre d'éléments d'une table en base de données
	 * @return
	 */
	public int getLength() throws SQLException;
	
	/**
	 * Méthode en charge de sélectionner un élément en base de données par son identifiant (int)
	 * @param t
	 * @return
	 */
	public Type1 getOne(Type1 t) throws SQLException;
	
	/**
	 * Méthode en charge de sélectionner tous les éléments d'une table
	 * @return
	 */
	public List<Type1> getAll() throws SQLException;

	/**
	 * Méthode en charge d'insérer un objet en base de données
	 * @param t
	 * @return
	 */
	public boolean add(Type1 t) throws SQLException;
	
	/**
	 * Méthode en charge de mettre un jour un élément en base de données
	 * @param t
	 * @return
	 */
	public boolean update(Type1 t) throws SQLException;
	
	/**
	 * Méthode en charge de supprimer un élément en base de données par son identifiant (int)
	 * @param t
	 * @return
	 */
	public boolean delete(Type1 t) throws SQLException;
	
	/**
	 * Méthode en charge de créer un objet via un ResultSet
	 * @param rs
	 * @return
	 */
	public Type1 itemBuilder(ResultSet rs) throws SQLException;

}
