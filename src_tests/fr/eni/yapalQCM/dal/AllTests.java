/**
 * @author wvignoles2017
 * @date 2 août 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author wvignoles2017
 * @date 2 août 2017
 * @version yapalqcm V1.0
 */
@RunWith(Suite.class)
@SuiteClasses({ InscriptionDALTEST.class, QuestionDALTEST.class,
		ReponseDALTEST.class, ResultatDALTEST.class, RoleDALTEST.class,
		SectionDALTEST.class, SessionDALTEST.class, TestDALTEST.class,
		ThemeDALTEST.class, UtilisateurDALTEST.class })
public class AllTests {

}
