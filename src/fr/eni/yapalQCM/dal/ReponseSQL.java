package fr.eni.yapalQCM.dal;

public abstract class ReponseSQL {
	public static String GET_LENGTH="SELECT COUNT(*) FROM REPONSES;";
	public static String GET_ONE="SELECT * FROM REPONSES WHERE idReponse=?;";
	public static String GET_ALL="SELECT * FROM REPONSES;";
	public static String ADD="INSERT INTO REPONSES (enonce,isCorrect,idQuestion) VALUES (enonce=?,isCorrect=?,idQuestion=?);";
	public static String UPDATE="UPDATE REPONSES SET enonce=?,isCorrect=?,idQuestion=? WHERE idReponse=?;";
	public static String DELETE="DELETE FROM REPONSES WHERE idReponse=?;";
}
