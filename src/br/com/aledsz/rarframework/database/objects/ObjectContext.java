package br.com.aledsz.rarframework.database.objects;

import br.com.aledsz.rarframework.database.exceptions.RarFrameworkException;
import java.lang.reflect.*;
import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author aleDsz
 */
public class ObjectContext<T> {
    private T object = null;
    
    public ObjectContext(T Object) {
        object = Object;
    }
    
    public List<Property> getProperties(Boolean getNull) throws IllegalArgumentException {
        try {
            List<Property> allProps = new ArrayList<Property>();
        
            Field[] allFields = object.getClass().getDeclaredFields();

            for (Field field : allFields) {
                Annotation[] annotations = field.getDeclaredAnnotations();

                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().isAnnotationPresent(DbColumnAttribute.class)) {
                        DbColumnAttribute attribute = annotation.annotationType().getAnnotation(DbColumnAttribute.class);

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
                }
            }

            return allProps;
        } catch (Exception ex) {
            RarFrameworkException.LogError(ex);
        }
        
        return null;
    }
    
    public String getTable() throws IllegalArgumentException {
        try {
            String tableName = "";
        
            Field[] allFields = object.getClass().getDeclaredFields();

            Annotation[] annotations = object.getClass().getDeclaredAnnotations();

            for (Annotation annotation : annotations) {
                if (annotation.annotationType().isAnnotationPresent(DbTableAttribute.class)) {
                    DbTableAttribute attribute = annotation.annotationType().getAnnotation(DbTableAttribute.class);

                    if (attribute != null) {
                        tableName = attribute.tableName();
                    }
                }
            }

            return tableName;
        } catch (Exception ex) {
            RarFrameworkException.LogError(ex);
        }
        
        return null;
    }
}