package br.com.aledsz.rarframework.database.sql;

import br.com.aledsz.rarframework.database.objects.ObjectContext;
import br.com.aledsz.rarframework.database.objects.Property;
import br.com.aledsz.rarframework.database.sql.querybuilder.InsertQueryBuilder;
import java.util.List;

/**
 * @description Create SQL Statement for INSERT instruction
 * @version 1.0.0.0
 * @author aleDsz
 */
public class SqlStatementInsert<T> extends SqlStatement {

    T object;
    String sSql = null;

    public SqlStatementInsert(T obj) {
        object = obj;
    }

    private void createSql() throws Exception, IllegalAccessException {
        try {

            ObjectContext<T> objectContext = new ObjectContext<>(object);
            InsertQueryBuilder insertQueryBuilder = new InsertQueryBuilder();
            List<Property> propsList = objectContext.getProperties(true);

            insertQueryBuilder.addFrom(objectContext.getTable());

            propsList.forEach((property) -> {
                insertQueryBuilder.addField(property.fieldName);
                insertQueryBuilder.addValue(property.value);
            });

            sSql = insertQueryBuilder.toString();
        } catch (IllegalAccessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String getSql() throws Exception, IllegalAccessException {
        try {
            createSql();
            return sSql;
        } catch (IllegalAccessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
