/**
 * @author wvignoles2017
 * @date 31 juil. 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

import java.sql.SQLException;

import org.junit.Test;

/**
 * @author wvignoles2017
 * @date 31 juil. 2017
 * @version yapalqcm V1.0
 */
public interface ITEST {
	@Test
	public void testGetLength();
	/**
	 * Méthode en charge de tester la méthode getOne (récupération d'un élément par son id)
	 */
	@Test
	public void testGetOne() throws SQLException;
	/**
	 * Méthode en charge de tester la méthode getAll (récupération de tous les éléments d'une table)
	 */
	@Test
	public void testGetAll() throws SQLException;
	/**
	 * Méthode en charge de tester la méthode add (insertion d'un élément en base de données)
	 */
	@Test
	public void testAdd() throws SQLException;
	/**
	 * Méthode en charge de tester la méthode update (modification d'un élément en base de données)
	 */
	@Test
	public void testUpdate() throws SQLException;
	/**
	 * Méthode en charge de tester la méthode delete (suppression d'un élément en base de données)
	 */
	@Test
	public void testDelete() throws SQLException;
}
