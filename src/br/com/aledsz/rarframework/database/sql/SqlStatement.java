package br.com.aledsz.rarframework.database.sql;

import java.text.SimpleDateFormat;

/**
 * @description Format value to use on where clause
 * @version 1.0.0.0
 * @author aleDsz
 */
public class SqlStatement {

    public String getWhereValue(Object propValue, String type) {
        String result = null;

        switch (type) {
            case "Byte":
            case "Binary":
            case "Boolean":
            case "Integer":
            case "Short":
            case "Long":
                result = String.format("= %s", propValue.toString());

                break;

            case "Date":
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");

                if (propValue != null) {
                    result = String.format("= '%s'", dateFormat.format(propValue));
                }

                break;

            case "DateTime":
                dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

                if (propValue != null) {
                    result = String.format("= '%s'", dateFormat.format(propValue));
                }

                break;

            case "Time":
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");

                if (propValue != null) {
                    result = String.format("= '%s'", timeFormat.format(propValue));
                }

                break;

            case "Decimal":
            case "Double":
            case "Float":
                result = String.format("= '%s'", propValue.toString());

                break;

            default:
                if (propValue.toString().startsWith("%") || propValue.toString().endsWith("%")) {
                    result = String.format("LIKE '%s'", propValue.toString());
                } else {
                    result = String.format("= '%s'", propValue.toString());
                }
                break;
        }

        return result;
    }
}
