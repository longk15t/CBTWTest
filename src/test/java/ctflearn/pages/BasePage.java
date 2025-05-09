package ctflearn.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import ctflearn.models.Config;
import ctflearn.models.Env;
import ctflearn.utils.BrowserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {
    private static final Logger logger = LogManager.getLogger(BasePage.class);
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private static final ObjectMapper objMapper = new ObjectMapper();
    private static Config config;
    protected static Env env;

    public void setUp(String configFilePath) throws IOException {
        configureEnvironment(configFilePath);
        logger.info("Setup environment and initialize web driver on " + config.getEnvironment());
        String browser = (System.getProperty("browser") == null) ? config.getBrowser() : System.getProperty("browser");
        driver = BrowserFactory.getDriverFromBrowser(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(config.getImplicitWait()));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(config.getExplicitWait()));
    }

    private void configureEnvironment(String configFilePath) throws IOException {
        config = objMapper.readValue(new File(configFilePath), Config.class);
        String environment = (System.getProperty("env") == null) ? config.getEnvironment() : System.getProperty("env");
        switch (environment) {
            case "dev" -> env = config.getDev();
            case "qa" -> env = config.getQa();
            case "staging" -> env = config.getStaging();
        }
    }

    public void navigateToUrl() {
        logger.info("Navigate to base url " + env.getUrl());
        driver.get(env.getUrl());
    }

    public void teardown() {
        logger.info("Teardown");
        driver.close();
        driver.quit();
    }

    public Env getEnvironment() {
        return env;
    }

    public LoginPage navigateToLoginPage() {
        logger.info("Navigate to login page");
        driver.findElement(By.xpath("//a[@class='nav-link' and @href='/user/login']")).click();
        return new LoginPage();
    }
}
