
/**
 * @description Create mock data
 * @version 1.0.0.0
 * @author aleDsz
 */
public class Fixtures {

    public static Contact newContactData() {
        Contact contact = new Contact();
        contact.name = "Joseph";
        contact.email = "test@aledsz.com.br";
        contact.phoneNumber = "112345678";

        return contact;
    }

    public static Contact createdContactData() {
        Contact contact = new Contact();
        contact.id = 1;
        contact.email = "test@aledsz.com.br";

        return contact;
    }
}
