
import br.com.aledsz.rarframework.database.DatabaseFactory;
import br.com.aledsz.rarframework.database.command.CommandContext;
import br.com.aledsz.rarframework.database.data.DataContext;
import br.com.aledsz.rarframework.database.enums.TiposSelect;
import br.com.aledsz.rarframework.database.objects.ObjectContext;
import br.com.aledsz.rarframework.database.sql.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author aleDsz
 */
public class ModelDataAccess<T> {

    public void create(T obj) throws Exception {
        try {
            ObjectContext<T> objContext = new ObjectContext<>(obj);
            DataContext dataContext = DatabaseFactory.getInstanceOfDataAccess(objContext.getDatabase());
            SqlStatementInsert<T> sqlStatement = new SqlStatementInsert<>(obj);

            dataContext.begin();

            CommandContext commandContext = new CommandContext(objContext.getDatabase(), sqlStatement.getSql());
            commandContext.executeQuery();

            dataContext.commit();
        } catch (IOException | SQLException ex) {
            throw ex;
        }
    }

    public void save(T obj) throws Exception {
        try {
            ObjectContext<T> objContext = new ObjectContext<>(obj);
            DataContext dataContext = DatabaseFactory.getInstanceOfDataAccess(objContext.getDatabase());
            SqlStatementUpdate<T> sqlStatement = new SqlStatementUpdate<>(obj);

            dataContext.begin();

            CommandContext commandContext = new CommandContext(objContext.getDatabase(), sqlStatement.getSql());
            commandContext.executeQuery();

            dataContext.commit();
        } catch (IOException | SQLException ex) {
            throw ex;
        }
    }

    public void remove(T obj) throws Exception {
        try {
            ObjectContext<T> objContext = new ObjectContext<>(obj);
            DataContext dataContext = DatabaseFactory.getInstanceOfDataAccess(objContext.getDatabase());
            SqlStatementDelete<T> sqlStatement = new SqlStatementDelete<>(obj);

            dataContext.begin();

            CommandContext commandContext = new CommandContext(objContext.getDatabase(), sqlStatement.getSql());
            commandContext.executeQuery();

            dataContext.commit();
        } catch (IOException | SQLException ex) {
            throw ex;
        }
    }

    public T find(T obj) throws Exception {
        try {
            T object = null;

            ObjectContext<T> objContext = new ObjectContext<>(obj);
            DataContext dataContext = DatabaseFactory.getInstanceOfDataAccess(objContext.getDatabase());
            SqlStatementSelect<T> sqlStatement = new SqlStatementSelect<>(obj);

            dataContext.begin();

            CommandContext commandContext = new CommandContext(objContext.getDatabase(), sqlStatement.getSql(TiposSelect.ByKey));
            object = objContext.getObject(commandContext.executeReader());

            dataContext.commit();

            return object;
        } catch (IOException | SQLException ex) {
            throw ex;
        }
    }

    public List<T> findAll(T obj) throws Exception {
        try {
            List<T> objects = null;

            ObjectContext<T> objContext = new ObjectContext<>(obj);
            DataContext dataContext = DatabaseFactory.getInstanceOfDataAccess(objContext.getDatabase());
            SqlStatementSelect<T> sqlStatement = new SqlStatementSelect<>(obj);

            dataContext.begin();

            CommandContext commandContext = new CommandContext(objContext.getDatabase(), sqlStatement.getSql(TiposSelect.All));
            objects = objContext.getObjects(commandContext.executeReader());

            dataContext.commit();

            return objects;
        } catch (IOException | SQLException ex) {
            throw ex;
        }
    }
}
