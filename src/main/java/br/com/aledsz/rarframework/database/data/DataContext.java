package br.com.aledsz.rarframework.database.data;

import br.com.aledsz.rarframework.logging.log.LogManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @description Execute SQL queries
 * @version 1.0.0.0
 * @author aleDsz
 */
public final class DataContext {

    private Connection databaseConnection = null;
    private String databaseName = null;

    public DataContext() throws SQLException, IOException {
        try {
            this.databaseName = null;
            connect();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public DataContext(String databaseName) throws SQLException, IOException {
        try {
            this.databaseName = databaseName;
            connect();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void connect() throws SQLException, IOException {
        try {
            if (databaseConnection == null) {
                databaseConnection = DataContextFactory.getConnection(databaseName);
                databaseConnection.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void disconnect() throws SQLException {
        try {
            if (databaseConnection != null) {
                databaseConnection.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void begin() throws SQLException {
        try {
            if (databaseConnection != null) {
                databaseConnection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void commit() throws SQLException {
        try {
            if (databaseConnection != null) {
                databaseConnection.commit();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void rollback() throws SQLException {
        try {
            if (databaseConnection != null) {
                databaseConnection.rollback();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void executeQuery(String sSql) throws SQLException, IOException {
        try {
            if (databaseConnection == null) {
                connect();
            }

            LogManager.LogTrace(String.format("Executing SQL: %s", sSql));

            Statement statement = databaseConnection.createStatement();
            statement.executeUpdate(sSql);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public ResultSet executeReader(String sSql) throws SQLException, IOException {
        try {
            if (databaseConnection == null) {
                connect();
            }

            LogManager.LogTrace(String.format("Executing SQL: %s", sSql));

            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sSql);

            return resultSet;
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
