package dialer.tests;


import com.aventstack.extentreports.ExtentTest;
import dialer.utils.AppiumHelper;
import dialer.utils.MobileDriverSetup;
import org.testng.ITestContext;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import static dialer.utils.ExtentReport.ExtentTestManager.startMethod;

public class BaseTest {
    protected List<AppiumHelper> appiumServers = new ArrayList<>();
    protected List<MobileDriverSetup> drivers = new ArrayList<>();
    protected List<String> devices = new ArrayList<>();
    ExtentTest testClass;

    protected void addAppiumServer(AppiumHelper server) {
        appiumServers.add(server);
    }

    protected void addAppiumDriver(MobileDriverSetup appiumDriver) {
        drivers.add(appiumDriver);
    }

    protected void addDevices(String device) {
        devices.add(device);
    }

    protected List<AppiumHelper> getAppiumServer() {
        return appiumServers;
    }

    protected List<MobileDriverSetup> getDrivers() {
        return drivers;
    }

    protected List<String> getDevices() {
        return devices;
    }

    protected void startServer() {
        for (AppiumHelper server : getAppiumServer()) {
            server.start();
        }
    }

    @BeforeSuite(alwaysRun = true)
    @Parameters(value={"device"})
    public void startServer(@Optional("GooglePixel4XL") String device, ITestContext context) {
        addDevices(device);
        addAppiumDriver(new MobileDriverSetup());
        addAppiumServer(new AppiumHelper(device));
        startServer();
    }

    @BeforeTest
    protected void initDriver() {
        for(int i = 0; i < getDrivers().size(); i++) {
            getDrivers().get(i).setUp(getDevices().get(i));
        }
    }

    @AfterTest
    protected void tearDownTest() {
        for (MobileDriverSetup driver : getDrivers()) {
            driver.tearDown();
        }
    }

    @AfterSuite
    protected void stopServer() {
        for (AppiumHelper server : getAppiumServer()) {
            server.stop();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setupBeforeMethod(Method method) {
        Test test = method.getAnnotation(Test.class);
        startMethod(testClass, method.getName(), test.description());
    }
}
