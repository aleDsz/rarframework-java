package br.com.aledsz.rarframework.database.sql;

import br.com.aledsz.rarframework.database.enums.TiposSelect;
import br.com.aledsz.rarframework.database.objects.ObjectContext;
import br.com.aledsz.rarframework.database.objects.Property;
import br.com.aledsz.rarframework.database.sql.querybuilder.SelectQueryBuilder;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description Create SQL Statement for SELECT instruction
 * @version 1.0.0.0
 * @author aleDsz
 */
public class SqlStatementSelect<T> extends SqlStatement {

    T object;
    String sSql = null;

    public SqlStatementSelect(T obj) {
        object = obj;
    }

    private void createSql(Boolean isList) throws Exception, IllegalAccessException {
        try {
            ObjectContext<T> objectContext = new ObjectContext<>(object);
            SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder();
            List<Property> propsList = objectContext.getProperties(isList);
            List<Property> fieldsList = objectContext.getProperties(true);

            selectQueryBuilder.addFrom(objectContext.getTable());

            if (isList) {
                List<Property> pkList = (List<Property>) propsList.stream().filter(o -> o.primaryKey == true).collect(Collectors.toList());

                if (pkList.isEmpty()) {
                    throw new Exception("Informar pelo menos 1 Primary Key");
                }
            }

            fieldsList.forEach((property) -> {
                selectQueryBuilder.addField(property.fieldName);
            });

            propsList.forEach((property) -> {
                if (property.value != null) {
                    selectQueryBuilder.addWhere(String.format("%s %s", property.fieldName, super.getWhereValue(property.value, property.type)));
                }
            });

            sSql = selectQueryBuilder.toString();
        } catch (IllegalAccessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String getSql() throws Exception, IllegalAccessException {
        try {
            return getSql(false);
        } catch (IllegalAccessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String getSql(TiposSelect tiposSelect) throws Exception, IllegalAccessException {
        try {
            return getSql(tiposSelect == TiposSelect.All);
        } catch (IllegalAccessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String getSql(Boolean isList) throws Exception, IllegalAccessException {
        try {
            createSql(isList);
            return sSql;
        } catch (IllegalAccessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
