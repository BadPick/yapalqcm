package fr.eni.yapalQCM.dal;

public abstract class ResultatSQL {
	public static String GET_LENGTH="SELECT COUNT(*) FROM RESULTATS;";
	public static String GET_ONE="SELECT * FROM RESULTATS WHERE idUser=?,idTest=?,idSession=?;";
	public static String GET_ALL="SELECT * FROM RESULTATS;";
	public static String ADD="INSERT INTO RESULTATS (idUser,idTest,idSession,tempsEcoulee,seuilObtenu) VALUES (idUser=?,idTest=?,idSession=?,tempsEcoulee=?,seuilObtenu=?);";
	public static String UPDATE="UPDATE RESULTATS SET tempsEcoulee=?,seuilObtenu=? WHERE idUser=? AND idTest=? AND idSession=?;";
	public static String DELETE="DELETE FROM RESULTATS WHERE idUser=? AND idTest=? AND idSession=?;";
	public static String GET_MANY_BY_UTILISATEUR="select re.idUser as idUser,re.idTest as idTest,re.idSession as idSession,re.tempsEcoulee as tempsEcoulee,re.seuilObtenu as seuilObtenu "
													+"from UTLISATEURS ut "
													+"LEFT JOIN RESULTATS re on ut.idUser=re.idUser "
													+"where ut.idUser=?";
}
