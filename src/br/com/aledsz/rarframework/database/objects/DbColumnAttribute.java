package br.com.aledsz.rarframework.database.objects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description Databse Column Attribute
 * @author aleDsz
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbColumnAttribute {

    public String fieldName();

    public String type();

    public boolean primaryKey();

    public int size();
}
