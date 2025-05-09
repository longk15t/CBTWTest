package ctflearn.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(DashboardPage.class);
    private static final By topMenuBar = By.cssSelector("div#navbarSupportedContent");

    public boolean isDashboardPageDisplay() {
        wait.until(ExpectedConditions.titleContains("Dashboard"));
        return driver.getCurrentUrl().contains("dashboard");
    }

    public TopMenu waitForTopMenuDisplay() {
        logger.debug("Wait for top menu display");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(topMenuBar)));
        return new TopMenu();
    }
}
