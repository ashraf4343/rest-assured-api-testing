import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Utils {

    public static void setEnvironment(String key,String value) throws ConfigurationException {
        PropertiesConfiguration config=new PropertiesConfiguration("./src/test/resources/config.properties");

        config.setProperty(key,value);
        config.save();
    }
}
