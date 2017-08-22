package br.com.aledsz.rarframework.database;

import br.com.aledsz.rarframework.database.data.DataContext;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @description Get DataContext instance
 * @version 1.0.0.0
 * @author aleDsz
 */
public class DatabaseFactory {
    
    private static DataContext _instanceOfDataContext = null;
    
    public static DataContext getInstanceOfDataAccess() throws SQLException, IOException {
        try {
            return getInstanceOfDataAccess(null);
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public static DataContext getInstanceOfDataAccess(String databaseName) throws SQLException, IOException {
        try {
            if (_instanceOfDataContext == null) {
                _instanceOfDataContext = new DataContext(databaseName);
            }

            return _instanceOfDataContext;
        } catch (SQLException ex) {
            throw ex;
        }
    }
}