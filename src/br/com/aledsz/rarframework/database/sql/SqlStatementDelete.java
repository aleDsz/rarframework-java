package br.com.aledsz.rarframework.database.sql;

import br.com.aledsz.rarframework.database.objects.ObjectContext;
import br.com.aledsz.rarframework.database.objects.Property;
import br.com.aledsz.rarframework.database.sql.querybuilder.DeleteQueryBuilder;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description Create SQL Statement for DELETE instruction
 * @version 1.0.0.0
 * @author aleDsz
 */
public class SqlStatementDelete<T> extends SqlStatement {

    T object;
    String sSql = null;

    public SqlStatementDelete(T obj) {
        object = obj;
    }

    private void createSql() throws Exception, IllegalAccessException {
        try {

            ObjectContext<T> objectContext = new ObjectContext<>(object);
            DeleteQueryBuilder deleteQueryBuilder = new DeleteQueryBuilder();
            List<Property> propsList = objectContext.getProperties(false);

            deleteQueryBuilder.addFrom(objectContext.getTable());

            List<Property> pkList = (List<Property>) propsList.stream().filter(o -> o.primaryKey == true).collect(Collectors.toList());

            if (pkList.isEmpty()) {
                throw new Exception("Informar pelo menos 1 Primary Key");
            }

            pkList.forEach((property) -> {
                deleteQueryBuilder.addWhere(String.format("%s %s", property.fieldName, super.getWhereValue(property.value, property.type)));
            });

            sSql = deleteQueryBuilder.toString();
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
