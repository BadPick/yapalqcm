package fr.eni.yapalQCM.dal;

public abstract class SessionSQL {	
	public static String DELETE_TEST_SESSIONS = "DELETE FROM TEST_SESSIONS WHERE idTest=? AND idSession=?";
	public static String GET_LENGTH="SELECT COUNT(*) as Total FROM SESSIONS;";
	public static String GET_ONE="SELECT * FROM SESSIONS WHERE idSession=?;";
	public static String GET_ALL="SELECT * FROM SESSIONS;";
	public static String ADD="INSERT INTO SESSIONS (date,nombrePlaces) VALUES (?,?);";
	public static String UPDATE="UPDATE SESSIONS SET date=?,nombrePlaces=? WHERE idSession=?;";
	public static String DELETE="DELETE FROM SESSIONS WHERE idSession=?;";
	public static String ADDTESTSESSION="INSERT INTO TEST_SESSIONS VALUES(?,?,?,?,?);";
	public static String DELETETESTSESSION="DELETE FROM TEST_SESSIONS WHERE idSession=? AND idTest=?;";
	public static String GET_TESTS ="SELECT * from TEST_SESSIONS where idSession=?;";
	public static String DELETE_SESSIONS="DELETE FROM SESSIONS WHERE idSession=?";
}
