package dialer.utils;

import dialer.actions.AndroidActions;
import dialer.variables.Constants;
import dialer.actions.IOSActions;
import dialer.variables.TargetPlatforms;
import dialer.interfaces.MobileActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class MobileDriverSetup {

    private static final Logger logger = LogManager.getLogger(MobileDriverSetup.class);
    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    public DesiredCapabilitiesFactory capsFactory = new DesiredCapabilitiesFactory();
    public PropertiesHelper propertyHelper;
    public static MobileActions actions;

    public void setUp(String device) {
        logger.info("Setup Appium driver for device: " + device);
        String deviceFile = Paths.get(Constants.deviceFarm.toString(), device + ".properties").toString();
        propertyHelper = new PropertiesHelper(deviceFile);
        String platformName = propertyHelper.getProperty("platformName");
        String port = propertyHelper.getProperty("port");

        DesiredCapabilities caps = capsFactory.getCaps(device);

        try {
            if (platformName.equalsIgnoreCase(String.valueOf(TargetPlatforms.android))) {
                logger.info("Init Android driver on threadId " + Thread.currentThread().getId());
                driver.set(new AndroidDriver(new URL("http://127.0.0.1:" + port + "/wd/hub"), caps));
                actions = new AndroidActions((AndroidDriver) getDriver());
            } else {
                logger.info("Init iOS driver on threadId " + Thread.currentThread().getId());
                driver.set(new IOSDriver(new URL("http://127.0.0.1:" + port + "/wd/hub"), caps));
                actions = new IOSActions((IOSDriver) getDriver());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void tearDown() {
        logger.info("Terminate Appium driver on threadId " + Thread.currentThread().getId());
        getDriver().quit();
        driver.remove();
    }

    public static AppiumDriver getDriver() {
        return driver.get();
    }
}
