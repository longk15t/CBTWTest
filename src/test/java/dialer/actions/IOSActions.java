package dialer.actions;


import dialer.interfaces.MobileActions;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class IOSActions implements MobileActions {

    private static final Logger logger = LogManager.getLogger(IOSActions.class);
    public IOSDriver driver;
    By startupTitle = By.id("com.android.dialer:id/empty_list_view_message");
    By arrivedMessage = By.id("arrived message");

    public IOSActions(IOSDriver dr) {
        driver = dr;
    }

    @Override
    public String getMainTitle() {
        //TODO
        return "";
    }

    @Override
    public void dialContact(String contactName) {
        //TODO
    }

    @Override
    public String getStartupTitle() {
        logger.info("Get startup title of Dialer app on Android device");
        return driver.findElement(startupTitle).getText();
    }

    @Override
    public boolean verifyMessageArrived(String msg) {
        logger.info("Verify if message " + "'" + msg + "' is arrived");
        return driver.findElement(arrivedMessage).isDisplayed();
    }
}
