package br.com.aledsz.rarframework.database.sql.querybuilder;

/**
 * @description Build String SQL for INSERT instruction
 * @version 1.0.0.0
 * @author aleDsz
 */
public class InsertQueryBuilder extends QueryBuilder {

    @Override
    public String toString() {
        try {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(String.format("INSERT INTO %s ", getFromClause()));
            stringBuilder.append(String.format("(%s)", getFieldClause()));
            stringBuilder.append(String.format(" VALUES "));
            stringBuilder.append(String.format("(%s)", getValueClause()));

            return stringBuilder.toString();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
