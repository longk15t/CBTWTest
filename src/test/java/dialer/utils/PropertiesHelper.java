package dialer.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class PropertiesHelper {
    public Properties prop;

    public PropertiesHelper(String propertyFile) {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(propertyFile);
            prop.load(fis);
        } catch (IOException fnfe) {
            fnfe.printStackTrace();
        }
    }

    public Set<Object> getAllKeys(){
        Set<Object> keys = prop.keySet();
        return keys;
    }

    public String getProperty(String property) {
        return prop.getProperty(property);
    }
}
