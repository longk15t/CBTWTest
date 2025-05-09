package ctflearn.listeners;

import ctflearn.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BasePage implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);
    public void onTestStart(ITestResult result) {
        logger.info(result.getMethod().getConstructorOrMethod().getName() + " test method is starting.");
    }

    public void onTestSuccess(ITestResult result) {
        logger.info(result.getMethod().getConstructorOrMethod().getName() + " test method is succeed.");
    }

    public void onTestFailure(ITestResult result) {
        logger.info(result.getMethod().getConstructorOrMethod().getName() + " test method is failed.");
    }

    public void onTestSkipped(ITestResult result) {
        logger.info(result.getMethod().getConstructorOrMethod().getName() + " test method is skipped.");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
        logger.info("Start test " + context.getName());
    }

    public void onFinish(ITestContext context) {
        logger.info("Finish test " + context.getName());
    }
}
