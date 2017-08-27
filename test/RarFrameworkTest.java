
import br.com.aledsz.rarframework.database.data.DataContextFactory;
import br.com.aledsz.rarframework.database.objects.*;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @description CI tests
 * @verion 1.0.0.0
 * @author aleDsz
 */
public class RarFrameworkTest {

    @Before
    public void testConnect() throws Throwable {
        Connection databaseConnection = DataContextFactory.getConnection("sqlite");
        Assert.assertNotNull(databaseConnection);
    }

    @Before
    public void testPropertyList() throws Throwable {
        ObjectContext<Contact> objectContext = new ObjectContext<>(new Contact());
        List<Property> listProps = objectContext.getProperties(true);
        Assert.assertTrue(listProps.size() > 0);
    }

    @Test
    public void testCreateData() throws Throwable {
        Contact contact = Fixtures.newContactData();
        ModelService.create(contact);

        contact = Fixtures.createdContactData();
        contact = ModelService.find(contact);

        Assert.assertNotNull(contact);
    }

    @Test
    public void testSaveData() throws Throwable {
        Contact contact = Fixtures.createdContactData();
        contact.name = "Alexandre de Souza";
        ModelService.save(contact);

        contact = Fixtures.createdContactData();
        contact = ModelService.find(contact);

        Assert.assertNotNull(contact);
        Assert.assertEquals("Alexandre de Souza", contact.name);
    }

    @Test
    public void testFindData() throws Throwable {
        Contact contact = Fixtures.createdContactData();
        contact = ModelService.find(contact);

        Assert.assertNotNull(contact);
    }

    @Test
    public void testFindAllData() throws Throwable {
        List<Contact> listContacts = ModelService.findAll(new Contact());

        Assert.assertNotNull(listContacts);
    }

    @Test
    public void testRemoveData() throws Throwable {
        List<Contact> listContacts = ModelService.findAll(new Contact());

        listContacts.forEach((contact) -> {
            try {
                ModelService.remove(contact);
            } catch (Exception ex) {
                Logger.getLogger(RarFrameworkTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        listContacts = ModelService.findAll(new Contact());

        Assert.assertNull(listContacts);
    }
}
