package fr.eni.yapalQCM.dal;

public abstract class RoleSQL {
	public static String GET_LENGTH="SELECT COUNT(*) AS Total FROM ROLES;";
	public static String GET_ONE="SELECT * FROM ROLES WHERE idRole=?;";
	public static String GET_ALL="SELECT * FROM ROLES;";
	public static String ADD="INSERT INTO ROLES (role) VALUES (?);";
	public static String UPDATE="UPDATE ROLES SET role=? WHERE idRole=?;";
	public static String DELETE="DELETE FROM ROLES WHERE idRole=?;";
}
