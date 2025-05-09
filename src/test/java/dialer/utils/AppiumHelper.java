package dialer.utils;

import dialer.variables.Constants;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.nio.file.Paths;


public class AppiumHelper {

    private static final Logger logger = LogManager.getLogger(AppiumHelper.class);
    public AppiumDriverLocalService service;
    public int port;

    public AppiumHelper(String device) {
        String deviceFile = Paths.get(Constants.deviceFarm.toString(), device + ".properties").toString();
        PropertiesHelper propertyHelper = new PropertiesHelper(deviceFile);
        port = Integer.parseInt(propertyHelper.getProperty("port"));
    }

    public void start() {
        logger.info("Start Appium server");

        //Build the Appium service
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1"); //or localhost
        builder.usingPort(port);

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public void stop() {
        logger.info("Stop Appium server");
        service.stop();
    }
}
