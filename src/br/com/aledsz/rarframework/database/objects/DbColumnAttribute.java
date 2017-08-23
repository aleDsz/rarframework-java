package br.com.aledsz.rarframework.database.objects;

/**
 * @description Databse Column Attribute
 * @author aleDsz
 */
public @interface DbColumnAttribute {
    public String fieldName();
    public String type();
    public boolean primaryKey();
    public int size();
}