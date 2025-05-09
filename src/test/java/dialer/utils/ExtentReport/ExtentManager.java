package dialer.utils.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("target/extent-reports/index.html");
        reporter.config().setReportName("Summary Test Report");
        extentReports.attachReporter(reporter);
        return extentReports;
    }
}
