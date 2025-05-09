package dialer.utils;

import dialer.variables.Constants;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.nio.file.Paths;
import java.util.Set;

public class DesiredCapabilitiesFactory {

    public DesiredCapabilities caps = new DesiredCapabilities();
    private static final Logger logger = LogManager.getLogger(DesiredCapabilitiesFactory.class);

    public DesiredCapabilities getCaps(String device) {
        String deviceFile = Paths.get(Constants.deviceFarm.toString(), device + ".properties").toString();
        logger.info("Get desired capabilities from device: " + deviceFile);
        PropertiesHelper propertyHelper = new PropertiesHelper(deviceFile);

        Set<Object> keys = propertyHelper.getAllKeys();
        for (Object key : keys) {
            if (!key.equals("port")) {
                logger.debug(key + "=" + propertyHelper.getProperty((String) key));
                caps.setCapability((String) key, propertyHelper.getProperty((String) key));
            }
        }

        return caps;
    }
}
