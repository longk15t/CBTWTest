package ctflearn.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyChallengesPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(MyChallengesPage.class);

    public boolean isMyChallengePageDisplay() {
        wait.until(ExpectedConditions.titleContains("Challenges by " + env.getUsername()));
        return driver.getCurrentUrl().contains("/challenge/by/" + env.getUsername());
    }

    public boolean cardExist(String cardName) {
        logger.debug("Check if this card exist: " + cardName);
        String cardHeader = "//div[@class='card-header d-flex']/span[text()='{cardName}']";
        return driver.findElement(By.xpath(cardHeader.replace("{cardName}", cardName))).isDisplayed();
    }
}
