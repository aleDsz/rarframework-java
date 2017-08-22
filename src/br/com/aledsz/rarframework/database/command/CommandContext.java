package br.com.aledsz.rarframework.database.command;

import br.com.aledsz.rarframework.database.DatabaseFactory;
import br.com.aledsz.rarframework.database.data.DataContext;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * @author aleDsz
 */
public class CommandContext {
    private String command = null;
    private DataContext dataContext = null;
    
    public CommandContext() throws SQLException, IOException {
        try {
            setSql(null);
            dataContext = DatabaseFactory.getInstanceOfDataAccess();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public CommandContext(String databaseName) throws SQLException, IOException {
        try {
            setSql(null);
            dataContext = DatabaseFactory.getInstanceOfDataAccess(databaseName);
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public CommandContext(String databaseName, String sSql) throws SQLException, IOException {
        try {
            setSql(sSql);
            dataContext = DatabaseFactory.getInstanceOfDataAccess(databaseName);
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public void setSql(String sSql) {
        command = sSql;
    }
    
    public void executeQuery() throws SQLException, IOException {
        try {
            dataContext.executeQuery(command);
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public ResultSet executeReader() throws SQLException, IOException {
        try {
            return dataContext.executeReader(command);
        } catch (SQLException ex) {
            throw ex;
        }
    }
}