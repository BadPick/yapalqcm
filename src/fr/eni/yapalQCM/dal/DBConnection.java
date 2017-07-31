package fr.eni.yapalQCM.dal;
/**
 * @author wvignoles2017
 * @date 18 juil. 2017
 * @version TP_WEB perso V1.0
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import fr.eni.yapalQCM.properties.Settings;

/**
 * Classe en charge de gérer la connexion à une base de données de type SQL
 * Server
 * 
 * @author wvignoles2017
 * @date 18 juil. 2017
 * @version TP_WEB perso V1.0
 */
public abstract class DBConnection {
	public static Connection getConnection() throws SQLException {
		// Chargement du pilote :
		DriverManager.registerDriver(new SQLServerDriver());
		// Ouverture de la connexion :
		Connection connection = DriverManager.getConnection(Settings.getString("ConnectionString"));
		return connection;
	}
}
