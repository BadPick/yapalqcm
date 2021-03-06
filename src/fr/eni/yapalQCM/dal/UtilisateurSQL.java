package fr.eni.yapalQCM.dal;

public abstract class UtilisateurSQL {
	public static String GET_LENGTH="SELECT COUNT(*) AS Total FROM UTILISATEURS;";
	public static String GET_ONE="SELECT * FROM UTILISATEURS WHERE idUser=?;";
	public static String GET_ALL="SELECT * FROM UTILISATEURS;";
	public static String ADD="INSERT INTO UTILISATEURS (idRole,password,nom,prenom,dateNaissance,adresseEmail) VALUES (?,?,?,?,?,?);";
	public static String UPDATE="UPDATE UTILISATEURS SET idRole=?,password=?,nom=?,prenom=?,dateNaissance=?,adresseEmail=? WHERE idUser=?;";
	public static String DELETE="DELETE FROM UTILISATEURS WHERE idUser=?;";
	public static String GET_CONNEXION="SELECT * FROM UTILISATEURS WHERE adresseEmail=? AND password=?;";
}
