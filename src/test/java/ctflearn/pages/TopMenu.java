package ctflearn.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

/***
 * This class is for the top menu, which is not a class for a page,
 * but since this top menu always shows up and is not dependent
 * from any pages, and we can access it anytime, so it's worth to have
 * a separated class for it
 */
public class TopMenu extends BasePage {
    private static final Logger logger = LogManager.getLogger(TopMenu.class);
    private static final By challengeDropdownIcon = By.xpath("//a[@id='navbarDropdownMenuLink']/following-sibling::a");
    private static final By challengeDropdownMenu = By.xpath("//div[@aria-labelledby='navbarDropdownMenuLink']");
    private static final By createChallengeMenuItem = By.xpath("//a[@class='dropdown-item' and @href='/challenge/create']");
    private static final By myChallengesMenuItem = By.xpath("//div[@class='dropdown-menu show']/a[@class='dropdown-item' and contains(@href,'/challenge/by')]");
    private static final By profilePic = By.cssSelector("a#profileDropdown");
    private static final By profileMenu = By.xpath("//div[@aria-labelledby='profileDropdown']");
    private static final By logoutMenuItem = By.xpath("//a[@href='/user/logout']");

    public CreateChallengePage navigateToNewChallengePage() {
        logger.info("Navigate to create new challenge page");
        driver.findElement(challengeDropdownIcon).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(challengeDropdownMenu)));
        wait.until(ExpectedConditions.elementToBeClickable(createChallengeMenuItem)).click();
        return new CreateChallengePage();
    }

    public void logout() {
        logger.info("Log out the system");
        driver.findElement(profilePic).click();
        wait.until(ExpectedConditions.attributeToBe(profileMenu, "class", "dropdown-menu show"));
        driver.findElement(logoutMenuItem).click();
    }

    public MyChallengesPage navigateToMyChallengesPage() {
        logger.info("Navigate to My Challenges page");
        driver.findElement(challengeDropdownIcon).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(challengeDropdownMenu)));
        wait.until(ExpectedConditions.elementToBeClickable(myChallengesMenuItem)).click();
        return new MyChallengesPage();
    }
}
