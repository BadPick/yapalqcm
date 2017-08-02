package fr.eni.yapalQCM.dal;

public abstract class SessionSQL {
	public static String GET_LENGTH="SELECT COUNT(*) FROM SESSIONS;";
	public static String GET_ONE="SELECT * FROM SESSIONS WHERE idSession=?;";
	public static String GET_ALL="SELECT * FROM SESSIONS;";
	public static String ADD="INSERT INTO SESSIONS (date,nombrePlaces) VALUES (date=?,nombrePlaces=?);";
	public static String UPDATE="UPDATE SESSIONS SET date=?,nombrePlaces=? WHERE idSession=?;";
	public static String DELETE="DELETE FROM SESSIONS WHERE idSession=?;";
}
