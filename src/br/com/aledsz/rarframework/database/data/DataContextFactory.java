package br.com.aledsz.rarframework.database.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author aleDsz
 */
public class DataContextFactory {

    private static Properties properties = null;

    private static void getConfig() throws IOException {
        try {
            File currentDirectory = new File(new File(".").getAbsolutePath());
            InputStream configFile = new FileInputStream(currentDirectory.getCanonicalPath() + "/config.properties");

            properties = new Properties();
            properties.load(configFile);
        } catch (IOException ex) {
            throw ex;
        }
    }

    public static Connection getConnection(String databaseName) throws SQLException, IOException {
        try {
            if (properties == null) {
                getConfig();
            }

            String databaseType;
            String databaseHost;
            String databasePort;
            String databaseUser;
            String databasePassword;
            String databaseDatabase;
            Connection databaseConnection;

            if (databaseName != null) {
                databaseHost = properties.getProperty(String.format("%s.host", databaseName));
                databasePort = properties.getProperty(String.format("%s.port", databaseName));
                databaseType = properties.getProperty(String.format("%s.type", databaseName));
                databaseUser = properties.getProperty(String.format("%s.user", databaseName));
                databasePassword = properties.getProperty(String.format("%s.pwd", databaseName));
                databaseDatabase = properties.getProperty(String.format("%s.db", databaseName));
            } else {
                databaseHost = properties.getProperty("host");
                databasePort = properties.getProperty("port");
                databaseType = properties.getProperty("type");
                databaseUser = properties.getProperty("user");
                databasePassword = properties.getProperty("pwd");
                databaseDatabase = properties.getProperty("db");
            }

            switch (databaseType) {
                case "mysql":
                case "mariadb":
                default:
                    databaseConnection = DriverManager.getConnection(String.format("jdbc:%s://%s:%s/%s", databaseType, databaseHost, databasePort, databaseDatabase));
                    break;

                case "sqlite":
                    databaseConnection = DriverManager.getConnection(String.format("jdbc:%s:%s", databaseType, databaseHost));
                    break;
            }

            return databaseConnection;
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
