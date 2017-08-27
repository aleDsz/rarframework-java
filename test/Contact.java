
import br.com.aledsz.rarframework.database.objects.DbColumnAttribute;
import br.com.aledsz.rarframework.database.objects.DbTableAttribute;

/**
 * @author aleDsz
 */
@DbTableAttribute(databaseName = "sqlite", tableName = "contacts")
public class Contact {

    @DbColumnAttribute(fieldName = "id", primaryKey = true, size = 11, type = "Integer")
    public Integer id;

    @DbColumnAttribute(fieldName = "name", primaryKey = false, size = 120, type = "String")
    public String name;

    @DbColumnAttribute(fieldName = "email", primaryKey = true, size = 120, type = "String")
    public String email;

    @DbColumnAttribute(fieldName = "phone", primaryKey = false, size = 30, type = "String")
    public String phoneNumber;
}
