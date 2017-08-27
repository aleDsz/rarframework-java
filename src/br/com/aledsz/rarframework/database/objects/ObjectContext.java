package br.com.aledsz.rarframework.database.objects;

import br.com.aledsz.rarframework.database.exceptions.RarFrameworkException;
import java.io.IOException;
import java.lang.reflect.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description Get all metadata from object
 * @version 1.0.0.
 * @author aleDsz
 */
public class ObjectContext<T> {

    private T object = null;

    public ObjectContext(T Object) {
        object = Object;
    }

    public List<Property> getProperties(Boolean getNull) throws IllegalArgumentException, IllegalAccessException, IOException {
        try {
            List<Property> allProps = new ArrayList<>();

            Field[] allFields = object.getClass().getDeclaredFields();

            for (Field field : allFields) {
                DbColumnAttribute attribute = field.getAnnotation(DbColumnAttribute.class);

                if (attribute != null) {
                    Property property = new Property();

                    property.fieldName = attribute.fieldName();
                    property.primaryKey = attribute.primaryKey();
                    property.propName = field.getName();
                    property.size = attribute.size();
                    property.type = field.getType().getName();
                    property.value = field.get(object);

                    allProps.add(property);
                }
            }

            return allProps;
        } catch (IllegalArgumentException ex) {
            RarFrameworkException.LogError(ex);
        }

        return null;
    }

    public String getTable() throws IllegalArgumentException, IOException {
        try {
            String tableName = "";

            DbTableAttribute attribute = object.getClass().getAnnotation(DbTableAttribute.class);

            if (attribute != null) {
                tableName = attribute.tableName();
            }

            return tableName;
        } catch (IllegalArgumentException ex) {
            RarFrameworkException.LogError(ex);
        }

        return null;
    }

    public String getDatabase() throws IllegalArgumentException, IOException {
        try {
            String databaseName = "";

            DbTableAttribute attribute = object.getClass().getAnnotation(DbTableAttribute.class);

            if (attribute != null) {
                databaseName = attribute.databaseName();
            }

            return databaseName;
        } catch (IllegalArgumentException ex) {
            RarFrameworkException.LogError(ex);
        }

        return null;
    }

    public T getObject(ResultSet resultSet) throws IOException, IllegalAccessException, InstantiationException, SQLException {
        try {
            T Object = null;

            if (resultSet.next()) {
                Object = (T) object.getClass().newInstance();

                Field[] allFields = object.getClass().getDeclaredFields();

                for (Field field : allFields) {
                    DbColumnAttribute attribute = field.getAnnotation(DbColumnAttribute.class);

                    if (attribute != null) {
                        field.set(Object, resultSet.getObject(attribute.fieldName()));
                    }
                }
            }

            return Object;
        } catch (IllegalArgumentException ex) {
            RarFrameworkException.LogError(ex);
        }

        return null;
    }

    public List<T> getObjects(ResultSet resultSet) throws IOException, IllegalAccessException, InstantiationException, SQLException {
        try {
            List<T> listObject = null;

            while (resultSet.next()) {
                listObject = new ArrayList<>();

                T Object = (T) object.getClass().newInstance();

                Field[] allFields = object.getClass().getDeclaredFields();

                for (Field field : allFields) {
                    DbColumnAttribute attribute = field.getAnnotation(DbColumnAttribute.class);

                    if (attribute != null) {
                        field.set(Object, resultSet.getObject(attribute.fieldName()));
                    }
                }

                listObject.add(Object);
            }

            return listObject;
        } catch (IllegalArgumentException ex) {
            RarFrameworkException.LogError(ex);
        }

        return null;
    }
}
