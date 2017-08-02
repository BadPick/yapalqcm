package fr.eni.yapalQCM.dal;

public abstract class QuestionSQL {
	public static String GET_LENGTH="SELECT COUNT(*) FROM QUESTIONS;";
	public static String GET_ONE="SELECT * FROM QUESTIONS WHERE idQuestion=?;";
	public static String GET_ALL="SELECT * FROM QUESTIONS;";
	public static String ADD="INSERT INTO QUESTIONS (enonce,image) VALUES (enonce=?,image=?);";
	public static String UPDATE="UPDATE QUESTIONS SET enonce=?,image=? WHERE idQuestion=?;";
	public static String DELETE="DELETE FROM QUESTIONS WHERE idQuestion=?;";
}
