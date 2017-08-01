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
public interface IDAL<Type> {
	/**
	 * Méthode en charge de compter le nombre d'éléments d'une table en base de données
	 * @param procedureStockee
	 * @return
	 */
	public int getLength();
	
	/**
	 * Méthode en charge de sélectionner un élément en base de données par son identifiant (int)
	 * @param procedureStockee
	 * @param id
	 * @return
	 */
	public Type getOne(int id);
	
	/**
	 * Méthode en charge de sélectionner tous les éléments d'une table
	 * @param procedureStockee
	 * @return
	 */
	public List<Type> getAll();

	/**
	 * Méthode en charge d'insérer un objet en base de données
	 * @param procedureStockee
	 * @param t
	 * @return
	 */
	public boolean add(Type t);
	
	/**
	 * Méthode en charge de mettre un jour un élément en base de données
	 * @param procedureStockee
	 * @param t
	 * @return
	 */
	public boolean update(Type t);
	
	/**
	 * Méthode en charge de supprimer un élément en base de données par son identifiant (int)
	 * @param procedureStockee
	 * @param id
	 * @return
	 */
	public boolean delete(int id);
	
	/**
	 * Méthode en charge de créer un objet via un ResultSet
	 * @param rs
	 * @return
	 */
	public Type itemBuilder(ResultSet rs) throws SQLException;

}
