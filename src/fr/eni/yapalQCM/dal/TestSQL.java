package fr.eni.yapalQCM.dal;

public abstract class TestSQL {
	public static String GET_LENGTH="SELECT COUNT(*) as Total FROM TESTS;";
	public static String GET_ONE="SELECT * FROM TESTS WHERE idTest=?;";
	public static String GET_ALL="SELECT * FROM TESTS;";
	public static String ADD="INSERT INTO TESTS (nomTest,seuilAcquis,seuilEnCoursAcquisition,duree) VALUES (?,?,?,?);";
	public static String UPDATE="UPDATE TESTS SET nomTest=?,seuilAcquis=?,seuilEnCoursAcquisition=?,duree=? WHERE idTest=?;";
	public static String DELETE="DELETE FROM TESTS WHERE idTest=?;";
	public static String GET_MANY_BY_UTILISATEUR="select te.idTest as idTest,nomTest,seuilAcquis,seuilEnCoursAcquisition,duree "
													+"from UTILISATEURS ut "
													+"LEFT JOIN INSCRIPTIONS ip on ut.idUser=ip.idUser "
													+"LEFT JOIN SESSIONS se on ip.idSession=se.idSession "
													+"LEFT JOIN TEST_SESSIONS ts on se.idSession=ts.idSession "
													+"LEFT JOIN TESTS te on ts.idTest=te.idTest "
													+"WHERE ut.idUser=?";
	
	public static String GET_ONE_BY_ID = "SELECT t.*,th.*,q.*,s.nombreQuestions,r.enonce as repEnonce,r.idReponse,r.isCorrect "
													+"FROM TESTS t "
													+"inner join  sections s on t.idTest=s.idTest "
													+"inner join  themes th on s.idTheme = th.idTheme "
													+"inner join  QUESTIONS_THEMES qt on th.idTheme = qt.idTheme "
													+"inner join  QUESTIONS q on q.idQuestion=qt.idQuestion "
													+"inner join  REPONSES r on q.idQuestion=r.idQuestion "
													+"where t.idTest=?";
}
