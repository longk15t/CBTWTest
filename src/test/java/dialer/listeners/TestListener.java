package dialer.listeners;


import dialer.utils.TestNGResultHandling;
import dialer.utils.MobileDriverSetup;
import dialer.utils.ExtentReport.ExtentManager;
import dialer.utils.ExtentReport.ExtentTestManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);
    private static final String screenshotDirectory = System.getProperty("user.dir") + "/target/screenshots/";

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    public void onStart(ITestContext iTestContext) {
        logger.info("Start test: " + iTestContext.getName());
    }

    public void onFinish(ITestContext iTestContext) {
        logger.info("Finished test: " + iTestContext.getName());
        generateReport();
    }

    public void onTestStart(ITestResult iTestResult) {
        logger.info("Test method " + getTestMethodName(iTestResult) + " is starting.");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        logger.info(getTestMethodName(iTestResult) + " test is succeed.");
        ExtentTestManager.getTestMethod().pass("Test passed");
    }

    public void onTestFailure(ITestResult iTestResult) {
        logger.info(getTestMethodName(iTestResult) + " test is failed.");
        String imagePath = screenshotDirectory + iTestResult.getName().trim() + ".png";
        TestNGResultHandling.takeScreenShot(MobileDriverSetup.getDriver(), imagePath);
        ExtentTestManager.getTestMethod().fail("Test failed");
        ExtentTestManager.getTestMethod().info(iTestResult.getThrowable());
        ExtentTestManager.getTestMethod().info("Screenshot file at " + imagePath).addScreenCaptureFromPath(imagePath);
    }

    public void onTestSkipped(ITestResult iTestResult) {
        logger.info(getTestMethodName(iTestResult) + " test is skipped.");
        ExtentTestManager.getTestMethod().skip("Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logger.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
        ExtentTestManager.getTestMethod().fail("Test failed within success percentage " + ITestResult.SUCCESS_PERCENTAGE_FAILURE);
    }

    private void generateReport() {
        ExtentManager.getExtentReports().setSystemInfo("Company", "CBTW");
        ExtentManager.getExtentReports().setSystemInfo("Project name", "Mobile app E2E automation");
        ExtentManager.getExtentReports().flush();
    }
}
