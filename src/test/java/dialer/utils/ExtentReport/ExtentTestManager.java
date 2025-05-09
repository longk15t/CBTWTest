package dialer.utils.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.util.HashMap;
import java.util.Map;

/**
 * extentTestMap holds the information of thread ids and ExtentTest instances.
 * ExtentReports instance created by calling getExtentReports() method from ExtentManager.
 * At startTest() method, an instance of ExtentTest created and put into extentTestMap with current thread id.
 * At getTest() method, return ExtentTest instance in extentTestMap by using current thread id.
 */
public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentManager.getExtentReports();

    public static synchronized ExtentTest getTestMethod() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testClassName, String additionalInfo) {
        return extent.createTest(testClassName, additionalInfo);
    }

    public static synchronized ExtentTest startMethod(ExtentTest testClass, String testName, String desc) {
        desc = (desc == null || desc.isEmpty()) ? testName : desc;
        ExtentTest testMethod = testClass.createNode(desc, "Test method: " + testName);
        extentTestMap.put((int) Thread.currentThread().getId(), testMethod);
        return testMethod;
    }
}
