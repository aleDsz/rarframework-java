package br.com.aledsz.rarframework.database.sql.querybuilder;

/**
 * @description Build String SQL for UPDATE instruction
 * @version 1.0.0.0
 * @author aleDsz
 */
public class UpdateQueryBuilder extends QueryBuilder {

    @Override
    public String toString() {
        try {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(String.format("UPDATE %s", getFromClause()));
            stringBuilder.append(String.format(" SET %s", getSetClause()));
            stringBuilder.append(String.format(" WHERE %s", getWhereClause()));

            return stringBuilder.toString();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
