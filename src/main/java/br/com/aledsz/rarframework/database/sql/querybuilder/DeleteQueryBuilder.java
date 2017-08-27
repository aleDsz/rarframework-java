package br.com.aledsz.rarframework.database.sql.querybuilder;

/**
 * @description Build String SQL for DELETE instruction
 * @version 1.0.0.0
 * @author aleDsz
 */
public class DeleteQueryBuilder extends QueryBuilder {

    @Override
    public String toString() {
        try {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(String.format("DELETE", getFromClause()));
            stringBuilder.append(String.format(" FROM %s", getFromClause()));
            stringBuilder.append(String.format(" WHERE %s", getWhereClause()));

            return stringBuilder.toString();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
