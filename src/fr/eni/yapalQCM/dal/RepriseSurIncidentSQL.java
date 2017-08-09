/**
 * @author wvignoles2017
 * @date 9 août 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

/**
 * @author wvignoles2017
 * @date 9 août 2017
 * @version yapalqcm V1.0
 */
public abstract class RepriseSurIncidentSQL {
	public static String ADDREPONSE="INSERT INTO REPONSES_ENCOURS (idUser,idTest,idQuestion,idReponse,isCheck,isMarquee) VALUES (?,?,?,?,?,?);";
	public static String DELETEREPONSE="DELETE FROM REPONSES_ENCOURS WHERE idUser=? AND idTest=? AND idQuestion=? AND idReponse=?;";
	public static String ADDTEST="INSERT INTO TESTS_EN_COURS (idUser,idTest,chrono) VALUES (?,?,?);";
	public static String DELETETEST="DELETE FROM TESTS_EN_COURS WHERE idUser=? AND idTest=?;";
	// TODO UPDATETEST
	// TODO GETONETEST
}
