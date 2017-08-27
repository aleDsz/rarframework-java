package br.com.aledsz.rarframework.database.sql;

import br.com.aledsz.rarframework.database.objects.ObjectContext;
import br.com.aledsz.rarframework.database.objects.Property;
import br.com.aledsz.rarframework.database.sql.querybuilder.UpdateQueryBuilder;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description Create SQL Statement for UPDATE instruction
 * @version 1.0.0.0
 * @author aleDsz
 */
public class SqlStatementUpdate<T> extends SqlStatement {

    T object;
    String sSql = null;

    public SqlStatementUpdate(T obj) {
        object = obj;
    }

    private void createSql() throws Exception, IllegalAccessException {
        try {

            ObjectContext<T> objectContext = new ObjectContext<>(object);
            UpdateQueryBuilder updateQueryBuilder = new UpdateQueryBuilder();
            List<Property> propsList = objectContext.getProperties(true);

            List<Property> pkList = (List<Property>) propsList.stream().filter(o -> o.primaryKey == true).collect(Collectors.toList());
            List<Property> noPkList = (List<Property>) propsList.stream().filter(o -> o.primaryKey == false).collect(Collectors.toList());

            updateQueryBuilder.addFrom(objectContext.getTable());

            if (pkList.isEmpty()) {
                throw new Exception("Informar pelo menos 1 Primary Key");
            }

            pkList.forEach((property) -> {
                updateQueryBuilder.addWhere(String.format("%s %s", property.fieldName, super.getWhereValue(property.value, property.type)));
            });

            noPkList.forEach((property) -> {
                if (property.value != null) {
                    updateQueryBuilder.addField(property.fieldName);
                    updateQueryBuilder.addValue(property.value);
                }
            });

            sSql = updateQueryBuilder.toString();
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
