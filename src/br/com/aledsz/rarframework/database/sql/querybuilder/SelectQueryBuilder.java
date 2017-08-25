package br.com.aledsz.rarframework.database.sql.querybuilder;

/**
 * @description Build String SQL for SELECT instruction
 * @version 1.0.0.0
 * @author aleDsz
 */
public class SelectQueryBuilder extends QueryBuilder {

    @Override
    public String toString() {
        try {
            StringBuilder stringBuilder = new StringBuilder();

            if (super.fieldList.size() > 0) {
                stringBuilder.append(String.format("  SELECT %s%s", super.getFieldClause(), System.getProperty("line.separator")));
            } else {
                stringBuilder.append("SELECT *");
            }

            if (super.fromList.size() > 0) {
                stringBuilder.append(String.format("    FROM %s%s", super.getFromClause(), System.getProperty("line.separator")));
            }

            if (super.whereList.size() > 0) {
                stringBuilder.append(String.format("   WHERE %s%s", super.getWhereClause(), System.getProperty("line.separator")));
            }

            if (super.orderList.size() > 0) {
                stringBuilder.append(String.format("ORDER BY %s%s", super.getOrderClause(), System.getProperty("line.separator")));
            }

            if (super.groupList.size() > 0) {
                stringBuilder.append(String.format("GROUP BY %s", super.getGroupClause(), System.getProperty("line.separator")));
            }

            return stringBuilder.toString();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
