
import java.util.List;

/**
 * @author aleDsz
 */
public class ModelService {

    public static <T> void create(T obj) throws Exception {
        try {
            ModelDataAccess<T> dataAccess = new ModelDataAccess<>();
            dataAccess.create(obj);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static <T> void save(T obj) throws Exception {
        try {
            ModelDataAccess<T> dataAccess = new ModelDataAccess<>();
            dataAccess.save(obj);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static <T> void remove(T obj) throws Exception {
        try {
            ModelDataAccess<T> dataAccess = new ModelDataAccess<>();
            dataAccess.remove(obj);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static <T> T find(T obj) throws Exception {
        try {
            ModelDataAccess<T> dataAccess = new ModelDataAccess<>();
            return dataAccess.find(obj);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static <T> List<T> findAll(T obj) throws Exception {
        try {
            ModelDataAccess<T> dataAccess = new ModelDataAccess<>();
            return dataAccess.findAll(obj);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
