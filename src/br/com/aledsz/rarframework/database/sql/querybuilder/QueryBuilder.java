package br.com.aledsz.rarframework.database.sql.querybuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @description Build Query
 * @version 1.0.0.0
 * @author aleDsz
 */
public class QueryBuilder {

    protected List<String> valueList = new ArrayList<>();
    protected List<String> fieldList = new ArrayList<>();
    protected List<String> fromList = new ArrayList<>();
    protected List<String> whereList = new ArrayList<>();
    protected List<String> groupList = new ArrayList<>();
    protected List<String> orderList = new ArrayList<>();

    public void addValue(String value) {
        try {
            valueList.add(value);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void addField(String field) {
        try {
            fieldList.add(field);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void addFrom(String from) {
        try {
            fromList.add(from);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void addWhere(String where) {
        try {
            whereList.add(where);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void addGroup(String group) {
        try {
            groupList.add(group);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void addOrder(String order) {
        try {
            orderList.add(order);
        } catch (Exception ex) {
            throw ex;
        }
    }

    protected String getQuotedValue(Object propValue) {
        if (propValue == null) {
            return "null";
        }

        switch (propValue.getClass().getTypeName().toLowerCase()) {
            case "string":
                return String.format("'%s'", propValue);

            case "double":
            case "float":
            case "decimal":
                return String.format("'%.2f'", propValue);

            case "date":
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return String.format("'%s'", dateFormat.format(propValue));

            case "time":
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
                return String.format("'%s'", timeFormat.format(propValue));

            case "datetime":
                dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                return String.format("'%s'", dateFormat.format(propValue));

            default:
                return String.format("%s", propValue);
        }
    }

    protected String getValueClause() {
        try {
            String sSql = "";
            return valueList.stream().map((value) -> String.format("%s, ", getQuotedValue(value))).reduce(sSql, String::concat).substring(0, sSql.lastIndexOf(", "));
        } catch (Exception ex) {
            throw ex;
        }
    }

    protected String getFieldClause() {
        try {
            String sSql = "";
            return fieldList.stream().map((field) -> String.format("%s, ", field)).reduce(sSql, String::concat).substring(0, sSql.lastIndexOf(", "));
        } catch (Exception ex) {
            throw ex;
        }
    }

    protected String getFromClause() {
        try {
            String sSql = "";
            return fromList.stream().map((from) -> String.format("%s, ", from)).reduce(sSql, String::concat).substring(0, sSql.lastIndexOf(", "));
        } catch (Exception ex) {
            throw ex;
        }
    }

    protected String getWhereClause() {
        try {
            String sSql = "";
            return whereList.stream().map((where) -> String.format("%s AND ", where)).reduce(sSql, String::concat).substring(0, sSql.lastIndexOf(", "));
        } catch (Exception ex) {
            throw ex;
        }
    }

    protected String getGroupClause() {
        try {
            String sSql = "";
            return groupList.stream().map((group) -> String.format("%s, ", group)).reduce(sSql, String::concat).substring(0, sSql.lastIndexOf(", "));
        } catch (Exception ex) {
            throw ex;
        }
    }

    protected String getOrderClause() {
        try {
            String sSql = "";
            return orderList.stream().map((order) -> String.format("%s, ", order)).reduce(sSql, String::concat).substring(0, sSql.lastIndexOf(", "));
        } catch (Exception ex) {
            throw ex;
        }
    }

    protected String getSetClause() {
        try {
            String sSql = "";

            for (int i = 0; i < fieldList.size(); i++) {
                sSql += String.format("%s = %s, ", fieldList.get(i), getQuotedValue(valueList.get(i)));
            }

            return sSql;
        } catch (Exception ex) {
            throw ex;
        }
    }

}
