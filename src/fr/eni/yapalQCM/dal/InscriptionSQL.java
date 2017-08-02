package fr.eni.yapalQCM.dal;

public abstract class InscriptionSQL {

	public static String GET_LENGTH="SELECT COUNT(*) FROM INSCRIPTIONS;";
	public static String GET_ONE="SELECT * FROM INSCRIPTIONS WHERE idUser=? AND idSession=?;";
	public static String GET_ALL="SELECT * FROM INSCRIPTIONS;";
	public static String ADD="INSERT INTO INSCRIPTIONS (idUser,idSession) VALUES (idUser=?,idSession=?);";
	public static String UPDATE="UPDATE INSCRIPTIONS SET idUser=?, idSession=? WHERE idUser=? AND idSession=?;";
	public static String DELETE="DELETE FROM INSCRIPTIONS WHERE idUser=? AND idSession=?;";
}
