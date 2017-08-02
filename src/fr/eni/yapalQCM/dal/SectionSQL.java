package fr.eni.yapalQCM.dal;

public abstract class SectionSQL {
	public static String GET_LENGTH="SELECT COUNT(*) FROM SECTIONS;";
	public static String GET_ONE="SELECT * FROM SECTIONS WHERE idTest=? AND idTheme=?;";
	public static String GET_ALL="SELECT * FROM SECTIONS;";
	public static String ADD="INSERT INTO SECTIONS (idTest,idTheme,nombreQuestions) VALUES (idTest=?,idTheme=?,nombreQuestions=?);";
	public static String UPDATE="UPDATE SECTIONS SET nombreQuestions=? WHERE idTest=? AND idTheme=?;";
	public static String DELETE="DELETE FROM SECTIONS WHERE idTest=? AND idTheme=?;";
}
