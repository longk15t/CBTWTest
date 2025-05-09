package dialer.tests;

import dialer.screens.DialerApp;
import org.testng.Assert;
import org.testng.annotations.*;

import static dialer.utils.ExtentReport.ExtentTestManager.startTest;

public class DialerTest extends BaseTest {

    public DialerApp dialerApp;

    @BeforeClass
    public void setupBeforeClass() {
        testClass = startTest(this.getClass().getSimpleName(), "Running on device " + getDevices().get(0));
    }

    @Test(description = "Verify the startup title of Dialer app")
    public void verifyEmptyDialTitle() {
        dialerApp = new DialerApp();
        String actualTitle = dialerApp.getStartupTitle();
        Assert.assertEquals(actualTitle,
                "No one on your speed dial yet",
                "Actual main title does not match with expected."
        );
    }
}
