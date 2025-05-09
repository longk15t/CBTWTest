package ctflearn.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    private static final By loginHeading = By.xpath("//div[@class='card-header']/span");
    private static final By usernameField = By.cssSelector("input#identifier");
    private static final By passwordField = By.cssSelector("input#password");
    private static final By loginButton = By.xpath("//button[@type='submit']");
    public DashboardPage login() {
        logger.info("Login to system by username " + env.getUsername());
        driver.findElement(usernameField).sendKeys(env.getUsername());
        driver.findElement(passwordField).sendKeys(env.getPassword());
        driver.findElement(loginButton).click();
        return new DashboardPage();
    }

    public boolean isLoginPageDisplayed() {
        boolean result = true;
        wait.until(ExpectedConditions.presenceOfElementLocated(loginHeading));
        result &= driver.findElement(loginHeading).isDisplayed();
        result &= driver.getCurrentUrl().contains("/user/login");
        result &= driver.getTitle().contains("Login");
        return result;
    }
}
