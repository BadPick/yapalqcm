package fr.eni.yapalQCM.dal;

public abstract class ThemeSQL {
	public static String GET_LENGTH="SELECT COUNT(*) as Total FROM THEMES;";
	public static String GET_ONE="SELECT * FROM THEMES WHERE idTheme=?;";
	public static String GET_ALL="SELECT * FROM THEMES;";
	public static String ADD="INSERT INTO THEMES (nom) VALUES (?);";
	public static String UPDATE="UPDATE THEMES SET nom=? WHERE idTheme=?;";
	public static String DELETE="DELETE FROM THEMES WHERE idTheme=?;";
}
