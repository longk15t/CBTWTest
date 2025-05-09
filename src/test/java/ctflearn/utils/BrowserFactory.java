package ctflearn.utils;

import ctflearn.exceptions.InvalidBrowserNameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    public static WebDriver getDriverFromBrowser(String browser) {
        WebDriver driver;
        switch(browser.toLowerCase()) {
            case "chrome" -> driver = new ChromeDriver();
            case "edge" -> driver = new EdgeDriver();
            case "firefox", "ff" -> driver = new FirefoxDriver();
            default -> throw new InvalidBrowserNameException("Unexpected value: " + browser.toLowerCase());
        }
        return driver;
    }
}
