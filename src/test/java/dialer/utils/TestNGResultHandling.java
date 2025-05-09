package dialer.utils;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;

public class TestNGResultHandling {

    public static void takeScreenShot(WebDriver driver, PropertiesHelper envProperties, String path) {
        String width = envProperties.getProperty("screenshotWidth");
        String height = envProperties.getProperty("screenshotHeight");

        if (width == null) {
            width = "1000";
        }

        if (height == null) {
            height = "450";
        }

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File screenshotName = new File(path);
            FileUtils.copyFile(scrFile, screenshotName);
            Reporter.log("<br> <img src=" + screenshotName
                    + " class=\"img-rounded\" width=" + width
                    + " height=" + height + "/> <br>"
            );
            Reporter.log("<a href=" + screenshotName + "></a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void takeScreenShot(WebDriver driver, String path) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File screenshotName = new File(path);
            FileUtils.copyFile(scrFile, screenshotName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
