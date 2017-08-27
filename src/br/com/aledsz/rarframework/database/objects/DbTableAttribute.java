package br.com.aledsz.rarframework.database.objects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description Databse Table Attribute
 * @author aleDsz
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbTableAttribute {

    public String databaseName();

    public String tableName();
}
