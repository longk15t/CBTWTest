package ctflearn.pages;

import ctflearn.models.Challenge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CreateChallengePage extends BasePage {
    private static final Logger logger = LogManager.getLogger(CreateChallengePage.class);
    private static final By cardForm = By.cssSelector("div.card-body form#create");
    private static final By titleField = By.cssSelector("input#title");
    private static final By flagField = By.cssSelector("input#flag");
    private static final By descriptionField = By.xpath("//textarea[@name='description']");
    private static final By attachFile = By.cssSelector("input#file-upload");
    private static final By category = By.cssSelector("select#category.form-control");
    private static Select categoryDropdown;
    private static final By points = By.cssSelector("select#points.form-control");
    private static Select pointsDropdown;
    private static final By howToSolveField = By.cssSelector("textarea#howtosolve");
    private static final By submitButton = By.xpath("//button[@type='submit']");

    public boolean isCreateChallengeFormDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(cardForm))).isDisplayed();
    }

    public void createChallenge(Challenge challenge) {
        logger.info("Create new challenge");
        driver.findElement(titleField).clear();
        driver.findElement(titleField).sendKeys(challenge.getTitle());
        driver.findElement(flagField).sendKeys(challenge.getFlag());
        driver.findElement(descriptionField).clear();
        driver.findElement(descriptionField).sendKeys(challenge.getDescription());
        if (challenge.getAttachFile() != null && !challenge.getAttachFile().isEmpty()) {
            driver.findElement(attachFile).sendKeys(challenge.getAttachFile());
        }
        categoryDropdown = new Select(driver.findElement(category));
        categoryDropdown.selectByValue(challenge.getCategory());
        pointsDropdown = new Select(driver.findElement(points));
        pointsDropdown.selectByValue(String.valueOf(challenge.getPoints()));
        driver.findElement(howToSolveField).sendKeys(challenge.getHowWeSolveThis());
        driver.findElement(submitButton).click();
    }
}
